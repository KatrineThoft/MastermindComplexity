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
    public int noXOR() {
        return 74;
    }

    private void addClause(Atom a, Atom b, Atom c){
        Set<Atom> atomSet = new HashSet<>();
        atomSet.add(a);
        atomSet.add(b);
        atomSet.add(c);
        clauses.add(new Clause(atomSet));
    }

    /* d = dw
    ( !c_z || !d_x || !d || !b_z || !b_w || !c_x) &&
    ( !c_z || !d_x || !d || !b_z || b_w || c_x) &&
    ( !c_z || !d_x || !d || b_z || !b_w || c_x) &&
    ( !c_z || !d_x || !d || b_z || b_w || !c_x) &&
    ( !c_z || !d_x || !b_z || !a_x || !b_w || !c_x) &&
     ( !c_z || !d_x || !b_z || !a_x || b_w || c_x) &&
      ( !c_z || !d_x || b_z || !a_x || !b_w || c_x) &&
      ( !c_z || !d_x || b_z || !a_x || b_w || !c_x) &&
      ( !c_z || d_x || !d || !b_z || !b_w || c_x) &&
      ( !c_z || d_x || !d || !b_z || b_w || !c_x) &&
      ( !c_z || d_x || !d || b_z || !b_w || !c_x) &&
      ( !c_z || d_x || !d || b_z || b_w || c_x) &&
      ( !c_z || d_x || !b_z || !a_x || !b_w || c_x) &&
      ( !c_z || d_x || !b_z || !a_x || b_w || !c_x) &&
      ( !c_z || d_x || b_z || !a_x || !b_w || !c_x) &&
      ( !c_z || d_x || b_z || !a_x || b_w || c_x) &&
      ( !c_z || d || b_y || !b_z || !b_x || a_x || c_x) &&
      ( !c_z || d || b_y || !b_z || b_x || a_x || !c_x) &&
      ( !c_z || d || b_y || b_z || !b_x || a_x || !c_x) &&
      ( !c_z || d || b_y || b_z || b_x || a_x || c_x) &&
      ( !c_z || a_y) &&

        (c_z || !d_x || !d || b_y || !b_z || b_x || a_x) &&
         (c_z || !d_x || !d || b_y || b_z || !b_x || a_x) &&
         (c_z || !d_x || d || b_y || !b_z || !d_y || !c_y || !a_y || !a_x || !b_w || c_x || c_w) &&
         (c_z || !d_x || d || b_y || !b_z || !d_y || !c_y || !a_y || !a_x || b_w || !c_x || c_w) &&
          (c_z || !d_x || d || b_y || !b_z || !d_y || c_y || !a_y || !a_x || !b_w || c_x || !c_w) &&
          (c_z || !d_x || d || b_y || !b_z || !d_y || c_y || !a_y || !a_x || b_w || !c_x || !c_w) &&
          (c_z || !d_x || d || b_y || b_z || !d_y || !c_y || !a_y || !a_x || !b_w || !c_x || c_w) &&
          (c_z || !d_x || d || b_y || b_z || !d_y || !c_y || !a_y || !a_x || b_w || c_x || c_w) &&
          (c_z || !d_x || d || b_y || b_z || !d_y || c_y || !a_y || !a_x || !b_w || !c_x || !c_w) &&
          (c_z || !d_x || d || b_y || b_z || !d_y || c_y || !a_y || !a_x || b_w || c_x || !c_w) &&
          (c_z || !d_x || d || !b_z || a_x || !b_w || !c_x) &&
          (c_z || !d_x || d || !b_z || a_x || b_w || c_x) &&
           (c_z || !d_x || d || b_z || a_x || !b_w || c_x) &&
           (c_z || !d_x || d || b_z || a_x || b_w || !c_x) &&
            (c_z || d_x || !d || b_y || !b_z || !b_x || a_x) &&
            (c_z || d_x || !d || b_y || b_z || b_x || a_x) &&
             (c_z || d_x || d || b_y || !b_z || !d_y || !c_y || !a_y || !a_x || !b_w || !c_x || c_w) &&
              (c_z || d_x || d || b_y || !b_z || !d_y || !c_y || !a_y || !a_x || b_w || c_x || c_w) &&
              (c_z || d_x || d || b_y || !b_z || !d_y || c_y || !a_y || !a_x || !b_w || !c_x || !c_w) &&
               (c_z || d_x || d || b_y || !b_z || !d_y || c_y || !a_y || !a_x || b_w || c_x || !c_w) &&
               (c_z || d_x || d || b_y || b_z || !d_y || !c_y || !a_y || !a_x || !b_w || c_x || c_w) &&
                (c_z || d_x || d || b_y || b_z || !d_y || !c_y || !a_y || !a_x || b_w || !c_x || c_w) &&
                 (c_z || d_x || d || b_y || b_z || !d_y || c_y || !a_y || !a_x || !b_w || c_x || !c_w) &&
                  (c_z || d_x || d || b_y || b_z || !d_y || c_y || !a_y || !a_x || b_w || !c_x || !c_w) &&
                   (c_z || d_x || d || !b_z || a_x || !b_w || c_x) &&
                    (c_z || d_x || d || !b_z || a_x || b_w || !c_x) &&
                    (c_z || d_x || d || b_z || a_x || !b_w || !c_x) &&
                    (c_z || d_x || d || b_z || a_x || b_w || c_x) &&

                     ( !d_x || !d || !b_z || !a_x || !b_w || !c_x) &&
                      ( !d_x || !d || !b_z || !a_x || b_w || c_x) &&
                       ( !d_x || !d || b_z || !a_x || !b_w || c_x) &&
                        ( !d_x || !d || b_z || !a_x || b_w || !c_x) &&
                         ( !d_x || !b_y || !b_z || !b_w || !c_x) &&
                          ( !d_x || !b_y || !b_z || b_w || c_x) &&
                           ( !d_x || !b_y || b_z || !b_w || c_x) &&
                            ( !d_x || !b_y || b_z || b_w || !c_x) &&
                             ( !d_x || !b_z || d_y || !a_x || !b_w || !c_x) &&
                              ( !d_x || !b_z || d_y || !a_x || b_w || c_x) &&
                               ( !d_x || !b_z || !c_y || !a_x || !b_w || !c_x || !c_w) &&
                               ( !d_x || !b_z || !c_y || !a_x || b_w || c_x || !c_w) &&
                                ( !d_x || !b_z || c_y || !a_x || !b_w || !c_x || c_w) &&
                               ( !d_x || !b_z || c_y || !a_x || b_w || c_x || c_w) &&
                               ( !d_x || b_z || d_y || !a_x || !b_w || c_x) &&
                                ( !d_x || b_z || d_y || !a_x || b_w || !c_x) &&
                                 ( !d_x || b_z || !c_y || !a_x || !b_w || c_x || !c_w) &&
                                ( !d_x || b_z || !c_y || !a_x || b_w || !c_x || !c_w) &&
                                 ( !d_x || b_z || c_y || !a_x || !b_w || c_x || c_w) &&
                                  ( !d_x || b_z || c_y || !a_x || b_w || !c_x || c_w) &&
                                   (d_x || !d || !b_z || !a_x || !b_w || c_x) &&
                                   (d_x || !d || !b_z || !a_x || b_w || !c_x) &&
                                    (d_x || !d || b_z || !a_x || !b_w || !c_x) &&
                                     (d_x || !d || b_z || !a_x || b_w || c_x) &&
                                      (d_x || !b_y || !b_z || !b_w || c_x) &&
                                       (d_x || !b_y || !b_z || b_w || !c_x) &&
                                        (d_x || !b_y || b_z || !b_w || !c_x) &&
                                         (d_x || !b_y || b_z || b_w || c_x) &&
                                          (d_x || !b_z || d_y || !a_x || !b_w || c_x) &&
                                           (d_x || !b_z || d_y || !a_x || b_w || !c_x) &&
                                            (d_x || !b_z || !c_y || !a_x || !b_w || c_x || !c_w) &&
                                             (d_x || !b_z || !c_y || !a_x || b_w || !c_x || !c_w) &&
                                              (d_x || !b_z || c_y || !a_x || !b_w || c_x || c_w) &&
                                               (d_x || !b_z || c_y || !a_x || b_w || !c_x || c_w) &&
                                                (d_x || b_z || d_y || !a_x || !b_w || !c_x) &&
                                                (d_x || b_z || d_y || !a_x || b_w || c_x) &&
                                                 (d_x || b_z || !c_y || !a_x || !b_w || !c_x || !c_w) &&
                                                  (d_x || b_z || !c_y || !a_x || b_w || c_x || !c_w) &&
                                                   (d_x || b_z || c_y || !a_x || !b_w || !c_x || c_w) &&
                                                    (d_x || b_z || c_y || !a_x || b_w || c_x || c_w) &&
                                                     ( !d || a_y) &&
    ( !b_y || a_y)
     && (d_y || a_y)
    && ( !c_y || a_y|| !c_w) &&
    (c_y || a_y || c_w)
     && (a_y || a_x)
     */

}
