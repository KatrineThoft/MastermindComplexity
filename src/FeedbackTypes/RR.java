package FeedbackTypes;

public class RR extends Feedback {
    public RR(String type, String guess) {
        super(type, guess);
    }

    @Override
    public int noXOR() {
        return 0;
    }
}
