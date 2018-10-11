package FeedbackTypes;

import java.util.Set;

public class GGOR extends Feedback {
    public GGOR(String guess) {
        super("GGOR", guess);
    }

    @Override
    public String getBoolTrans() {
        return null;
    }

    @Override
    public Set<String> splitBoolTrans() {
        return null;
    }

    @Override
    public int getBoolTransDepth() {
        return 0;
    }

    @Override
    public int noSymbols() {
        return 0;
    }

    @Override
    public int noOperators() {
        return 0;
    }

    @Override
    public int noXOR() {
        return 0;
    }
}
