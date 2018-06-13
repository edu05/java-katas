package kata.exceptions;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class ScientificPaperAggregatorTest {

    private ArXivRepository arXivRepository = mock(ArXivRepository.class);
    private JSTORRepository jstorRepository = mock(JSTORRepository.class);
    private MobileReaderApp mobileReaderApp = mock(MobileReaderApp.class);
    private ErrorNotifier errorNotifier = mock(ErrorNotifier.class);
    private ScientificPaperAggregator scientificPaperAggregator = new ScientificPaperAggregator(arXivRepository, jstorRepository, mobileReaderApp, errorNotifier);

    @Test
    public void shouldAggregatePapersFromArXivAndJSTOR() throws Exception {
        ScientificPaper newPaper = new ScientificPaper();
        ScientificPaper anotherNewPaper = new ScientificPaper();
        when(arXivRepository.getNewScientificPapers()).thenReturn(Arrays.asList(newPaper));
        when(jstorRepository.getNewScientificPapers()).thenReturn(Arrays.asList(anotherNewPaper));

        scientificPaperAggregator.aggregateNewScientificPapers();

        verify(mobileReaderApp).pushContent(Arrays.asList(newPaper, anotherNewPaper));
    }

    @Test
    public void shouldRetryUpToThreeTimesToRetrieveNewPapersFromArXiv() throws Exception {
        ScientificPaper newPaper = new ScientificPaper();
        when(arXivRepository.getNewScientificPapers()).thenThrow(new IOException("File too big"));
        when(jstorRepository.getNewScientificPapers()).thenReturn(Arrays.asList(newPaper));

        scientificPaperAggregator.aggregateNewScientificPapers();

        verify(mobileReaderApp).pushContent(Arrays.asList(newPaper));
        verify(arXivRepository, times(3)).getNewScientificPapers();
    }

    @Test
    public void shouldStopRetryingRetrievingNewPapersFromArXivAfterSuccessfulDownload() throws Exception {
        ScientificPaper newPaper = new ScientificPaper();
        ScientificPaper anotherPaper = new ScientificPaper();
        when(arXivRepository.getNewScientificPapers()).thenThrow(new IOException("File too big")).thenReturn(Arrays.asList(anotherPaper));
        when(jstorRepository.getNewScientificPapers()).thenReturn(Arrays.asList(newPaper));

        scientificPaperAggregator.aggregateNewScientificPapers();

        verify(mobileReaderApp).pushContent(Arrays.asList(anotherPaper, newPaper));
        verify(arXivRepository, times(2)).getNewScientificPapers();
    }

    @Test
    public void shouldRetryUpToThreeTimesToRetrieveNewPapersFromJSTOR() throws Exception {
        ScientificPaper newPaper = new ScientificPaper();
        when(arXivRepository.getNewScientificPapers()).thenReturn(Arrays.asList(newPaper));
        when(jstorRepository.getNewScientificPapers()).thenThrow(new NullPointerException());

        scientificPaperAggregator.aggregateNewScientificPapers();

        verify(mobileReaderApp).pushContent(Arrays.asList(newPaper));
        verify(jstorRepository, times(3)).getNewScientificPapers();
    }

    @Test
    public void shouldStopRetryingRetrievingNewPapersFromJSTORAfterSuccessfulDownload() throws Exception {
        ScientificPaper newPaper = new ScientificPaper();
        ScientificPaper anotherPaper = new ScientificPaper();
        when(arXivRepository.getNewScientificPapers()).thenReturn(Arrays.asList(newPaper));
        when(jstorRepository.getNewScientificPapers()).thenThrow(new NullPointerException()).thenReturn(Arrays.asList(anotherPaper));

        scientificPaperAggregator.aggregateNewScientificPapers();

        verify(mobileReaderApp).pushContent(Arrays.asList(newPaper, anotherPaper));
        verify(jstorRepository, times(2)).getNewScientificPapers();
    }

    @Test
    public void shouldNotifyWhenArXivMaxesOutOnRetries() throws Exception {
        ScientificPaper newPaper = new ScientificPaper();
        IOException ioException = new IOException("File too big");
        when(arXivRepository.getNewScientificPapers()).thenThrow(ioException);
        when(jstorRepository.getNewScientificPapers()).thenReturn(Arrays.asList(newPaper));

        scientificPaperAggregator.aggregateNewScientificPapers();

        verify(errorNotifier, times(3)).notifyError("ArXiv failed", ioException);
    }

    //similarly for JSTOR

}