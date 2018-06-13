package kata.exceptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScientificPaperAggregator {

    private final ArXivRepository arXivRepository;
    private final JSTORRepository jstorRepository;
    private final MobileReaderApp mobileReaderApp;
    private final ErrorNotifier errorNotifier;

    public ScientificPaperAggregator(ArXivRepository arXivRepository, JSTORRepository jstorRepository,
                                     MobileReaderApp mobileReaderApp, ErrorNotifier errorNotifier) {
        this.arXivRepository = arXivRepository;
        this.jstorRepository = jstorRepository;

        this.mobileReaderApp = mobileReaderApp;
        this.errorNotifier = errorNotifier;
    }

    public void aggregateNewScientificPapers() {
        List<ScientificPaper> newScientificPapersFromArxiv = new ArrayList<>();
        int retryCounter = 0;
        do {
            try {
                newScientificPapersFromArxiv = arXivRepository.getNewScientificPapers();
                break;
            } catch (IOException e) {
                errorNotifier.notifyError("ArXiv failed", e);
                retryCounter++;
            }
        } while (retryCounter < 3);

        List<ScientificPaper> newScientificPapersFromJSTOR = new ArrayList<>();

        retryCounter = 0;
        do {
            try {
                newScientificPapersFromJSTOR = jstorRepository.getNewScientificPapers();
                break;
            } catch (RuntimeException e) {
                errorNotifier.notifyError("JSTOR failed", e);
                retryCounter++;
            }
        } while (retryCounter < 3);


        List<ScientificPaper> aggregatedNewScientificPapers = new ArrayList<>();
        aggregatedNewScientificPapers.addAll(newScientificPapersFromArxiv);
        aggregatedNewScientificPapers.addAll(newScientificPapersFromJSTOR);
        mobileReaderApp.pushContent(aggregatedNewScientificPapers);
    }
}
