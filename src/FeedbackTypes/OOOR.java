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
/*!a_x &&
!b_y &&
!c_z &&
!d_w&&
( !a_w || !d_y || !a_z || c_w) &&
 ( !a_w || a_z || !c_x) &&
  (a_w || d_y || !a_z || !c_x) &&
  (a_w || !a_z || !c_x ||c_w) &&
( !d_y || !a_z || c_x || c_w) &&
!b_x &&
!b_z
 */

    @Override
    public int noXOR() {
        return 43;
    }

}
