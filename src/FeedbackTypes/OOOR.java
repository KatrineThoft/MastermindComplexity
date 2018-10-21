package FeedbackTypes;

import java.util.HashSet;
import java.util.Set;

public class OOOR extends Feedback {
    Set<Clause> clauses = new HashSet<>();
    public OOOR(String guess) {
        super("OOOR", guess);
        translate();
        super.clauses = clauses;
    }

    private void translate() {
        super.boolTrans = "Heya!";
    }


    @Override
    public int getBoolTransDepth() {
        return 0;
    }


    @Override
    public int noXOR() {
        return 0;
    }

}
