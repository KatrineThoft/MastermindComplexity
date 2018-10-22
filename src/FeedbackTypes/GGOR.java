package FeedbackTypes;

import java.util.Set;

public class GGOR extends Feedback {
    public GGOR(String guess) {
        super("GGOR", guess);
    }

    @Override
    public int noXOR() {
        return 11;
    }


}
