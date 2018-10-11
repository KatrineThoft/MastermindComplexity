package FeedbackTypes;

import java.util.Set;

public class OORR extends Feedback{
    String boolTrans;

    public OORR(String guess) {
        super("OORR", guess);
        translate();
    }


    @Override
    public String getBoolTrans() {
        return boolTrans;
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

    private void translate() {


    }

}
