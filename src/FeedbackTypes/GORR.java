package FeedbackTypes;

import java.util.Set;

public class GORR extends Feedback {
    public GORR(String guess) {
        super("GORR", guess);
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
/*
( !a_x || !d_y || !d_z || !c_w || !b_w || !b_z || !c_y) &&
( !a_x || !d_y || !d_z || !c_w || !b_w || b_z || c_y) &&
( !a_x || !d_y || !d_z || !c_w || b_w || !b_z || c_y) &&
( !a_x || !d_y || !d_z || !c_w || b_w || b_z || !c_y) &&
( !a_x || !d_y || !d_z || c_w || !b_w || !b_z || c_y) &&
( !a_x || !d_y || !d_z || c_w || !b_w || b_z || !c_y) &&
( !a_x || !d_y || !d_z || c_w || b_w || !b_z || !c_y) &&
( !a_x || !d_y || !d_z || c_w || b_w || b_z || c_y) &&
( !a_x || !d_y || d_z || !c_w || !b_w || !b_z || c_y) &&
( !a_x || !d_y || d_z || !c_w || !b_w || b_z || !c_y) &&
( !a_x || !d_y || d_z || !c_w || b_w || !b_z || !c_y) &&
( !a_x || !d_y || d_z || !c_w || b_w || b_z || c_y) &&
( !a_x || !d_y || d_z || c_w || !b_w || !b_z || !c_y) &&
( !a_x || !d_y || d_z || c_w || !b_w || b_z || c_y) &&
( !a_x || !d_y || d_z || c_w || b_w || !b_z || c_y) &&
( !a_x || !d_y || d_z || c_w || b_w || b_z || !c_y) &&
( !a_x || d_y || !d_z || !c_w || !b_w || !b_z || c_y) &&
( !a_x || d_y || !d_z || !c_w || !b_w || b_z || !c_y) &&
( !a_x || d_y || !d_z || !c_w || b_w || !b_z || !c_y) &&
( !a_x || d_y || !d_z || !c_w || b_w || b_z || c_y) &&
( !a_x || d_y || !d_z || c_w || !b_w || !b_z || !c_y) &&
( !a_x || d_y || !d_z || c_w || !b_w || b_z || c_y) &&
( !a_x || d_y || !d_z || c_w || b_w || !b_z || c_y) &&
( !a_x || d_y || !d_z || c_w || b_w || b_z || !c_y) &&
( !a_x || d_y || d_z || !c_w || !b_w || !b_z || !c_y) &&
( !a_x || d_y || d_z || !c_w || !b_w || b_z || c_y) &&
( !a_x || d_y || d_z || !c_w || b_w || !b_z || c_y) &&
( !a_x || d_y || d_z || !c_w || b_w || b_z || !c_y) &&
( !a_x || d_y || d_z || c_w || !b_w || !b_z || c_y) &&
( !a_x || d_y || d_z || c_w || !b_w || b_z || !c_y) &&
( !a_x || d_y || d_z || c_w || b_w || !b_z || !c_y) &&
( !a_x || d_y || d_z || c_w || b_w || b_z || c_y) &&
( !a_x || !d_w) && ( !a_x || !c_z) && ( !a_x || !b_y) &&

(a_x || !b_x || !c_x || c_z || !b_z || !a_z || !a_y || !c_y || b_y) &&
(a_x || !b_x || !c_x || c_z || !b_z || !a_z || a_y || c_y || b_y) &&
(a_x || !b_x || !c_x || c_z || !b_z || a_z || !a_y || c_y || b_y) &&
(a_x || !b_x || !c_x || c_z || !b_z || a_z || a_y || !c_y || b_y) &&
(a_x || !b_x || !c_x || c_z || b_z || !a_z || !a_y || c_y || b_y) &&
(a_x || !b_x || !c_x || c_z || b_z || !a_z || a_y || !c_y || b_y) &&
(a_x || !b_x || !c_x || c_z || b_z || a_z || !a_y || !c_y || b_y) &&
(a_x || !b_x || !c_x || c_z || b_z || a_z || a_y || c_y || b_y) &&
(a_x || !b_x || c_x || c_z || !b_z || !a_z || !a_y || c_y || b_y) &&
(a_x || !b_x || c_x || c_z || !b_z || !a_z || a_y || !c_y || b_y) &&
(a_x || !b_x || c_x || c_z || !b_z || a_z || !a_y || !c_y || b_y) &&
(a_x || !b_x || c_x || c_z || !b_z || a_z || a_y || c_y || b_y) &&
(a_x || !b_x || c_x || c_z || b_z || !a_z || !a_y || !c_y || b_y) &&
(a_x || !b_x || c_x || c_z || b_z || !a_z || a_y || c_y || b_y) &&
(a_x || !b_x || c_x || c_z || b_z || a_z || !a_y || c_y || b_y) &&
(a_x || !b_x || c_x || c_z || b_z || a_z || a_y || !c_y || b_y) &&
(a_x || b_x || !c_x || c_z || !b_z || !a_z || !a_y || c_y || b_y) &&
(a_x || b_x || !c_x || c_z || !b_z || !a_z || a_y || !c_y || b_y) &&
(a_x || b_x || !c_x || c_z || !b_z || a_z || !a_y || !c_y || b_y) &&
(a_x || b_x || !c_x || c_z || !b_z || a_z || a_y || c_y || b_y) &&
(a_x || b_x || !c_x || c_z || b_z || !a_z || !a_y || !c_y || b_y) &&
(a_x || b_x || !c_x || c_z || b_z || !a_z || a_y || c_y || b_y) &&
(a_x || b_x || !c_x || c_z || b_z || a_z || !a_y || c_y || b_y) &&
(a_x || b_x || !c_x || c_z || b_z || a_z || a_y || !c_y || b_y) &&
(a_x || b_x || c_x || c_z || !b_z || !a_z || !a_y || !c_y || b_y) &&
(a_x || b_x || c_x || c_z || !b_z || !a_z || a_y || c_y || b_y) &&
(a_x || b_x || c_x || c_z || !b_z || a_z || !a_y || c_y || b_y) &&
(a_x || b_x || c_x || c_z || !b_z || a_z || a_y || !c_y || b_y) &&
(a_x || b_x || c_x || c_z || b_z || !a_z || !a_y || c_y || b_y) &&
(a_x || b_x || c_x || c_z || b_z || !a_z || a_y || !c_y || b_y) &&
(a_x || b_x || c_x || c_z || b_z || a_z || !a_y || !c_y || b_y) &&
(a_x || b_x || c_x || c_z || b_z || a_z || a_y || c_y || b_y) &&
(a_x || d_w || c_z || b_y) &&

( !b_x || !d_x || !d_y || !c_z || !b_w || !a_w || !a_y) &&
( !b_x || !d_x || !d_y || !c_z || !b_w || a_w || a_y) &&
( !b_x || !d_x || !d_y || !c_z || b_w || !a_w || a_y) &&
( !b_x || !d_x || !d_y || !c_z || b_w || a_w || !a_y) &&
( !b_x || !d_x || d_y || !c_z || !b_w || !a_w || a_y) &&
( !b_x || !d_x || d_y || !c_z || !b_w || a_w || !a_y) &&
( !b_x || !d_x || d_y || !c_z || b_w || !a_w || !a_y) &&
( !b_x || !d_x || d_y || !c_z || b_w || a_w || a_y) &&
( !b_x || d_x || !d_y || !c_z || !b_w || !a_w || a_y) &&
( !b_x || d_x || !d_y || !c_z || !b_w || a_w || !a_y) &&
( !b_x || d_x || !d_y || !c_z || b_w || !a_w || !a_y) &&
( !b_x || d_x || !d_y || !c_z || b_w || a_w || a_y) &&
( !b_x || d_x || d_y || !c_z || !b_w || !a_w || !a_y) &&
( !b_x || d_x || d_y || !c_z || !b_w || a_w || a_y) &&
( !b_x || d_x || d_y || !c_z || b_w || !a_w || a_y) &&
( !b_x || d_x || d_y || !c_z || b_w || a_w || !a_y) &&

(b_x || !d_x || !d_y || !c_z || !b_w || !a_w || a_y) &&
(b_x || !d_x || !d_y || !c_z || !b_w || a_w || !a_y) &&
(b_x || !d_x || !d_y || !c_z || b_w || !a_w || !a_y) &&
(b_x || !d_x || !d_y || !c_z || b_w || a_w || a_y) &&
(b_x || !d_x || d_y || !c_z || !b_w || !a_w || !a_y) &&
(b_x || !d_x || d_y || !c_z || !b_w || a_w || a_y) &&
(b_x || !d_x || d_y || !c_z || b_w || !a_w || a_y) &&
(b_x || !d_x || d_y || !c_z || b_w || a_w || !a_y) &&
(b_x || d_x || !d_y || !c_z || !b_w || !a_w || !a_y) &&
(b_x || d_x || !d_y || !c_z || !b_w || a_w || a_y) &&
(b_x || d_x || !d_y || !c_z || b_w || !a_w || a_y) &&
(b_x || d_x || !d_y || !c_z || b_w || a_w || !a_y) &&
(b_x || d_x || d_y || !c_z || !b_w || !a_w || a_y) &&
(b_x || d_x || d_y || !c_z || !b_w || a_w || !a_y) &&
(b_x || d_x || d_y || !c_z || b_w || !a_w || !a_y) &&
(b_x || d_x || d_y || !c_z || b_w || a_w || a_y) &&

( !c_x || !d_x || !d_z || !c_w || !a_w || !a_z || !b_y) &&
 ( !c_x || !d_x || !d_z || !c_w || a_w || a_z || !b_y) &&
 ( !c_x || !d_x || !d_z || c_w || !a_w || a_z || !b_y) &&
 ( !c_x || !d_x || !d_z || c_w || a_w || !a_z || !b_y) &&
 ( !c_x || !d_x || d_z || !c_w || !a_w || a_z || !b_y) &&
 ( !c_x || !d_x || d_z || !c_w || a_w || !a_z || !b_y) &&
( !c_x || !d_x || d_z || c_w || !a_w || !a_z || !b_y) &&
( !c_x || !d_x || d_z || c_w || a_w || a_z || !b_y) &&
( !c_x || d_x || !d_z || !c_w || !a_w || a_z || !b_y) &&
( !c_x || d_x || !d_z || !c_w || a_w || !a_z || !b_y) &&
( !c_x || d_x || !d_z || c_w || !a_w || !a_z || !b_y) &&
( !c_x || d_x || !d_z || c_w || a_w || a_z || !b_y) &&
( !c_x || d_x || d_z || !c_w || !a_w || !a_z || !b_y) &&
( !c_x || d_x || d_z || !c_w || a_w || a_z || !b_y) &&
( !c_x || d_x || d_z || c_w || !a_w || a_z || !b_y) &&
( !c_x || d_x || d_z || c_w || a_w || !a_z || !b_y) &&

(c_x || !d_x || !d_z || !c_w || !a_w || a_z || !b_y) &&
(c_x || !d_x || !d_z || !c_w || a_w || !a_z || !b_y) &&
(c_x || !d_x || !d_z || c_w || !a_w || !a_z || !b_y) &&
(c_x || !d_x || !d_z || c_w || a_w || a_z || !b_y) &&
(c_x || !d_x || d_z || !c_w || !a_w || !a_z || !b_y) &&
(c_x || !d_x || d_z || !c_w || a_w || a_z || !b_y) &&
(c_x || !d_x || d_z || c_w || !a_w || a_z || !b_y) &&
(c_x || !d_x || d_z || c_w || a_w || !a_z || !b_y) &&
(c_x || d_x || !d_z || !c_w || !a_w || !a_z || !b_y) &&
(c_x || d_x || !d_z || !c_w || a_w || a_z || !b_y) &&
(c_x || d_x || !d_z || c_w || !a_w || a_z || !b_y) &&
(c_x || d_x || !d_z || c_w || a_w || !a_z || !b_y) &&
(c_x || d_x || d_z || !c_w || !a_w || a_z || !b_y) &&
(c_x || d_x || d_z || !c_w || a_w || !a_z || !b_y) &&
(c_x || d_x || d_z || c_w || !a_w || !a_z || !b_y) &&
(c_x || d_x || d_z || c_w || a_w || a_z || !b_y) &&

( !d_w || !c_z) &&
( !d_w || !b_y) &&
( !c_z || !b_y)
 */
