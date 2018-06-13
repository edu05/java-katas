package kata.exceptions;

import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ScientificPaperAggregatorTest {

    @Test
    public void shouldAggregatePapersFromArXivAndJSTOR() throws Exception {
        ArXivRepository arXivRepository = mock(ArXivRepository.class);
        JSTORRepository jstorRepository = mock(JSTORRepository.class);
        MobileReaderApp mobileReaderApp = mock(MobileReaderApp.class);

        ScientificPaper newPaper = new ScientificPaper();
        ScientificPaper anotherNewPaper = new ScientificPaper();
        when(arXivRepository.getNewScientificPapers()).thenReturn(Arrays.asList(newPaper));
        when(jstorRepository.getNewScientificPapers()).thenReturn(Arrays.asList(anotherNewPaper));

        ScientificPaperAggregator scientificPaperAggregator = new ScientificPaperAggregator(arXivRepository, jstorRepository, mobileReaderApp);

        scientificPaperAggregator.aggregateNewScientificPapers();

        verify(mobileReaderApp).pushContent(Arrays.asList(newPaper, anotherNewPaper));
    }

    @Test
    public void shouldRetryUpToThreeTimesToRetrieveNewPapersFromArXiv() throws Exception {
        ArXivRepository arXivRepository = mock(ArXivRepository.class);
        JSTORRepository jstorRepository = mock(JSTORRepository.class);
        MobileReaderApp mobileReaderApp = mock(MobileReaderApp.class);

        ScientificPaper newPaper = new ScientificPaper();
        when(arXivRepository.getNewScientificPapers()).thenThrow(new IOException("File too big"));
        when(jstorRepository.getNewScientificPapers()).thenReturn(Arrays.asList(newPaper));

        ScientificPaperAggregator scientificPaperAggregator = new ScientificPaperAggregator(arXivRepository, jstorRepository, mobileReaderApp);

        scientificPaperAggregator.aggregateNewScientificPapers();

        verify(mobileReaderApp).pushContent(Arrays.asList(newPaper));
        verify(arXivRepository, times(3)).getNewScientificPapers();
    }

    @Test
    public void shouldStopRetryingRetrievingNewPapersFromArXivAfterSuccessfulDownload() throws Exception {
        ArXivRepository arXivRepository = mock(ArXivRepository.class);
        JSTORRepository jstorRepository = mock(JSTORRepository.class);
        MobileReaderApp mobileReaderApp = mock(MobileReaderApp.class);

        ScientificPaper newPaper = new ScientificPaper();
        ScientificPaper anotherPaper = new ScientificPaper();
        when(arXivRepository.getNewScientificPapers()).thenThrow(new IOException("File too big")).thenReturn(Arrays.asList(anotherPaper));
        when(jstorRepository.getNewScientificPapers()).thenReturn(Arrays.asList(newPaper));

        ScientificPaperAggregator scientificPaperAggregator = new ScientificPaperAggregator(arXivRepository, jstorRepository, mobileReaderApp);

        scientificPaperAggregator.aggregateNewScientificPapers();

        verify(mobileReaderApp).pushContent(Arrays.asList(anotherPaper, newPaper));
        verify(arXivRepository, times(2)).getNewScientificPapers();
    }

    @Test
    public void shouldRetryUpToThreeTimesToRetrieveNewPapersFromJSTOR() throws Exception {
        ArXivRepository arXivRepository = mock(ArXivRepository.class);
        JSTORRepository jstorRepository = mock(JSTORRepository.class);
        MobileReaderApp mobileReaderApp = mock(MobileReaderApp.class);

        ScientificPaper newPaper = new ScientificPaper();
        when(arXivRepository.getNewScientificPapers()).thenReturn(Arrays.asList(newPaper));
        when(jstorRepository.getNewScientificPapers()).thenThrow(new NullPointerException());

        ScientificPaperAggregator scientificPaperAggregator = new ScientificPaperAggregator(arXivRepository, jstorRepository, mobileReaderApp);

        scientificPaperAggregator.aggregateNewScientificPapers();

        verify(mobileReaderApp).pushContent(Arrays.asList(newPaper));
        verify(jstorRepository, times(3)).getNewScientificPapers();
    }

    @Test
    public void shouldStopRetryingRetrievingNewPapersFromJSTORAfterSuccessfulDownload() throws Exception {
        ArXivRepository arXivRepository = mock(ArXivRepository.class);
        JSTORRepository jstorRepository = mock(JSTORRepository.class);
        MobileReaderApp mobileReaderApp = mock(MobileReaderApp.class);

        ScientificPaper newPaper = new ScientificPaper();
        ScientificPaper anotherPaper = new ScientificPaper();
        when(arXivRepository.getNewScientificPapers()).thenReturn(Arrays.asList(newPaper));
        when(jstorRepository.getNewScientificPapers()).thenThrow(new NullPointerException()).thenReturn(Arrays.asList(anotherPaper));

        ScientificPaperAggregator scientificPaperAggregator = new ScientificPaperAggregator(arXivRepository, jstorRepository, mobileReaderApp);

        scientificPaperAggregator.aggregateNewScientificPapers();

        verify(mobileReaderApp).pushContent(Arrays.asList(newPaper, anotherPaper));
        verify(jstorRepository, times(2)).getNewScientificPapers();
    }
}