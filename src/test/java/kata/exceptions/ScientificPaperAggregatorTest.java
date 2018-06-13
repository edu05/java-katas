package kata.exceptions;

import org.junit.Test;

import java.util.Arrays;

import static org.mockito.Mockito.mock;
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
}