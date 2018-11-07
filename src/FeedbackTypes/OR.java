package FeedbackTypes;

public class OR extends Feedback {
    public OR(String type, String guess) {
        super(type, guess);
    }

    @Override
    public int noXOR() {
        return 0;
    }
}
