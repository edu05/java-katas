package kata.exceptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScientificPaperAggregator {

    private final ArXivRepository arXivRepository;
    private final JSTORRepository jstorRepository;
    private final MobileReaderApp mobileReaderApp;

    public ScientificPaperAggregator(ArXivRepository arXivRepository, JSTORRepository jstorRepository, MobileReaderApp mobileReaderApp) {
        this.arXivRepository = arXivRepository;
        this.jstorRepository = jstorRepository;

        this.mobileReaderApp = mobileReaderApp;
    }

    public void aggregateNewScientificPapers() {
        List<ScientificPaper> newScientificPapersFromArxiv = new ArrayList<>();
        try {
            newScientificPapersFromArxiv = arXivRepository.getNewScientificPapers();
        } catch (IOException e) {
            //what should we do here?
        }

        List<ScientificPaper> newScientificPapersFromJSTOR = jstorRepository.getNewScientificPapers();

        List<ScientificPaper> aggregatedNewScientificPapers = new ArrayList<>();
        aggregatedNewScientificPapers.addAll(newScientificPapersFromArxiv);
        aggregatedNewScientificPapers.addAll(newScientificPapersFromJSTOR);
        mobileReaderApp.pushContent(aggregatedNewScientificPapers);
    }
}
