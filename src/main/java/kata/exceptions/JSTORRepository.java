package kata.exceptions;

import java.util.List;

public class JSTORRepository {

    public List<ScientificPaper> getNewScientificPapers() {
        throw new NullPointerException();
    }
}
