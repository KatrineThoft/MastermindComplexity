package FeedbackTypes;

import java.util.Set;

public class OOOR extends Feedback {
    public OOOR(String guess) {
        super("OOOR", guess);
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
