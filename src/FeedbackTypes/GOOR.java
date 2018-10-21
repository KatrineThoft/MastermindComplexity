package FeedbackTypes;

import java.util.Set;

public class GOOR extends Feedback {
    public GOOR(String guess) {
        super("GOOR", guess);
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
