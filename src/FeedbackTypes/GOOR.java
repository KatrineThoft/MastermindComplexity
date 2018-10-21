package FeedbackTypes;

import java.util.HashSet;
import java.util.Set;

public class GOOR extends Feedback {
    Set<Clause> clauses = new HashSet<>();
    public GOOR(String guess) {
        super("GOOR", guess);
        translate();
        super.clauses = clauses;
    }

    private void translate() {
        super.boolTrans = "Hello!";
    }

    @Override
    public int getBoolTransDepth() {
        return 0;
    }

    @Override
    public int noXOR() {
        return 0;
    }

    private void addClause(Atom a, Atom b, Atom c){
        Set<Atom> atomSet = new HashSet<>();
        atomSet.add(a);
        atomSet.add(b);
        atomSet.add(c);
        clauses.add(new Clause(atomSet));
    }


}
