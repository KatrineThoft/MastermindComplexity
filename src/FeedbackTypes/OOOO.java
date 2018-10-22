package FeedbackTypes;

import java.util.HashSet;
import java.util.Set;

public class OOOO extends Feedback{
    Set<Clause> clauses = new HashSet<>();
    public OOOO(String guess) {
        super("OOOO", guess);
        translate();
        super.clauses = clauses;
    }

    private void translate() {
        super.boolTrans = "Hejsa!";
    }


    @Override
    public int noXOR() {
        return 8;
    }


    /*
    ! a_x  &&  ! b_y&& !d_w && && ! c_z &&
    ( ! b_x  || ! c_x  || ! d_x  || !d_y || d_z || ! b_z || ! a_z || ! a_y || ! c_y) &&
    ( ! b_x  || ! c_x  || ! d_x  || !d_y || ! c_w || ! b_w || ! b_z || ! a_w || ! a_y ||  c_y) &&
    ( ! b_x  || ! c_x  || ! d_x  || !d_y ||  c_w || ! b_w || ! a_w || ! a_y || ! c_y) &&
    ( ! b_x  || ! c_x  || ! d_x  || d_y || !d_z || ! b_z || ! a_z || ! a_y || ! c_y) &&
    ( ! b_x  || ! c_x  || ! d_x  || !d_z || ! c_w ||  b_w || ! b_z || ! a_w || ! a_z) &&
    ( ! b_x  || ! c_x  ||  d_x  || !d_y || !d_z ||  c_w || ! b_w ||  b_z || ! a_w || ! a_z ||  a_y || ! c_y) &&
    ( ! b_x  || ! c_x  ||  d_x  || !d_y || !d_z ||  c_w ||  b_w || ! b_z || ! c_y) &&
    ( ! b_x  || ! c_x  ||  d_x  || !d_y || !d_z || ! b_z || ! a_z || ! a_y || ! c_y) &&
    ( ! b_x  || ! c_x  ||  d_x  || !d_y || !d_z || ! b_z ||  a_z ||  a_y || ! c_y) &&
    ( ! b_x  || ! c_x  ||  d_x  || d_y ||  c_w || ! b_w || ! a_w || ! a_y || ! c_y) &&
    ( ! b_x  || ! c_x  ||  d_x  || d_z || ! c_w || ! b_w ||  b_z) &&
    ( ! b_x  || ! c_x  ||  d_x  || d_z || ! c_w ||  b_w || ! b_z || ! a_w || ! a_z) &&
    ( ! b_x  || ! c_x  ||  d_x  || ! c_w || ! b_w || ! b_z ||  a_w) &&
    ( ! b_x  || ! c_x  ||  d_x  || ! c_w || ! b_w ||  b_z || ! a_w || ! a_y || ! c_y) &&
    ( ! b_x  || ! c_x  ||  d_x  || ! c_w || ! b_w ||  a_w ||  a_y) &&
    ( ! b_x  || ! c_x  || d_z || ! c_w || ! b_w ||  b_z ||  c_y) &&
    ( ! b_x  || ! c_x  || ! c_w || ! b_w ||  b_z ||  a_y ||  c_y) &&
    ( ! b_x  || ! c_x  || ! c_w || ! b_w ||  a_w ||  a_y ||  c_y) &&
    ( ! b_x  || ! c_x  || ! c_w ||  b_w || ! b_z || ! a_w || ! a_z ||  a_y ||  c_y) &&
    ( ! b_x  || ! c_x  || ! b_w ||  b_z || ! a_w ||  a_z || ! a_y || ! c_y) &&

    ( ! b_x  ||  c_x  || ! d_x  || !d_y || d_z || ! c_w || ! b_w ||  b_z || ! c_y) &&
    ( ! b_x  ||  c_x  || ! d_x  || !d_y || d_z || ! c_w ||  b_w || ! b_z || ! a_w || ! a_z ||  a_y || ! c_y) &&
    ( ! b_x  ||  c_x  || ! d_x  || !d_y || d_z || ! b_z || ! a_z || ! a_y ||  c_y) &&
    ( ! b_x  ||  c_x  || ! d_x  || !d_y || ! c_w || ! b_w || ! b_z || ! a_w || ! a_y || ! c_y) &&
    ( ! b_x  ||  c_x  || ! d_x  || !d_y || ! c_w || ! b_w ||  a_w ||  a_y || ! c_y) &&
    ( ! b_x  ||  c_x  || ! d_x  || d_y || !d_z || ! b_w ||  b_z || ! a_w || ! a_z) &&
    ( ! b_x  ||  c_x  || ! d_x  || !d_z ||  c_w || ! b_w ||  b_z || ! a_w || ! a_z) &&
    ( ! b_x  ||  c_x  || ! d_x  || !d_z ||  c_w ||  b_w || ! b_z) &&
    ( ! b_x  ||  c_x  || ! d_x  || !d_z || ! b_z ||  a_z) &&
    ( ! b_x  ||  c_x  ||  d_x  || !d_y || !d_z || ! b_z || ! a_z || ! a_y ||  c_y) &&
    ( ! b_x  ||  c_x  ||  d_x  || d_y || ! c_w || ! b_z || ! a_w || ! a_y || ! c_y) &&
    ( ! b_x  ||  c_x  || ! c_w || ! b_z || ! a_w ||  a_z || ! a_y || ! c_y) &&

    ( ! b_x  || ! d_x  || !d_y || !d_z || ! c_w || ! b_w ||  a_w || ! a_z || ! a_y || ! c_y) &&
    ( ! b_x  || ! d_x  || !d_y || d_z ||  b_w || ! b_z ||  a_w || ! a_z || ! a_y) &&
    ( ! b_x  || ! d_x  || d_y || !d_z || ! b_w ||  b_z || ! a_w || ! a_z ||  a_y) &&
    ( ! b_x  || ! d_x  || d_y || !d_z ||  b_w || ! b_z) &&
    ( ! b_x  || ! d_x  || d_y || !d_z || ! b_z ||  a_z ||  a_y) &&

    ( ! b_x  ||  d_x  || !d_y || !d_z || ! b_z ||  a_w || ! a_z || ! a_y) &&
    ( ! b_x  || !d_y || !d_z || ! c_w || ! b_w || ! b_z || ! a_w ||  a_z || ! a_y || ! c_y) &&
    ( ! b_x  || d_y || ! c_w ||  b_w || ! b_z || ! a_w || ! a_y || ! c_y) &&

    ( b_x  || ! c_x  || ! d_x  || !d_y || !d_z ||  b_w || ! c_y) &&
    ( b_x  || ! c_x  || ! d_x  || !d_y || d_z || ! c_w ||  b_w || ! a_w || ! a_y ||  c_y) &&
    ( b_x  || ! c_x  || ! d_x  || !d_y || d_z ||  b_z || ! c_y) &&
    ( b_x  || ! c_x  || ! d_x  || !d_y || ! c_w || ! b_w || ! b_z ||  a_w || ! a_z || ! a_y ||  c_y) &&
    ( b_x  || ! c_x  || ! d_x  || !d_y ||  a_y || ! c_y) &&
    ( b_x  || ! c_x  || ! d_x  || d_y || !d_z || ! b_w || ! b_z || ! a_w ||  a_z || ! a_y || ! c_y) &&
    ( b_x  || ! c_x  || ! d_x  || d_y || !d_z ||  b_z || ! a_z || ! a_y || ! c_y) &&
    ( b_x  || ! c_x  ||  d_x  || !d_y || !d_z || ! c_w ||  b_w || ! a_w || ! a_y) &&
    ( b_x  || ! c_x  ||  d_x  || d_y || ! c_w || ! b_w || ! b_z) &&
    ( b_x  || ! c_x  ||  d_x  || ! c_w || ! b_w || ! b_z || ! a_w || ! a_z) &&

    ( b_x  ||  c_x  || ! d_x  || !d_z || ! b_w || ! b_z || ! a_w || ! a_z) &&
    ( b_x  ||  c_x  || ! d_x  || !d_z ||  c_y) &&
    ( b_x  ||  c_x  ||  d_x  || d_z) &&
    ( b_x  ||  c_x  ||  d_x  ||  c_w) &&
    ( b_x  ||  c_x  ||  d_x  ||  b_z) &&
    ( b_x  ||  c_x  ||  d_x  ||  a_y) &&
    ( b_x  ||  c_x  || d_z || ! c_w || ! b_w || ! b_z ||  a_w || ! a_z || ! a_y || ! c_y) &&
    ( b_x  ||  c_x  || d_z || ! c_w ||  b_w || ! a_w || ! a_y || ! c_y) &&
    ( b_x  ||  c_x  ||  b_z ||  a_z) &&
    ( b_x  ||  c_x  ||  b_z ||  c_y) &&
    ( b_x  ||  c_x  ||  a_y ||  c_y) &&

    ( b_x  || ! d_x  || !d_z ||  b_w ||  a_w) &&

    ( b_x  ||  d_x  || !d_y || !d_z ||  c_w || ! b_w || ! b_z || ! a_w ||  a_z || ! a_y) &&
    ( b_x  ||  d_x  || !d_y || !d_z ||  b_z || ! a_z || ! a_y) &&
    ( b_x  ||  d_x  || d_y ||  c_w ||  b_w) &&
    ( b_x  ||  d_x  || d_y ||  a_y) &&
    ( b_x  ||  d_x  || d_z ||  b_w ||  a_w) &&

    ( b_x  || !d_y || !d_z ||  b_z || ! a_z || ! a_y ||  c_y) &&

    ( b_x  || ! b_w || ! b_z || ! a_w || ! a_z ||  a_y) &&

    ( b_x  ||  b_w ||  b_z) &&
    ( b_x  ||  b_w ||  a_w ||  a_y) &&

    ( b_x  ||  b_z ||  a_z ||  a_y) &&

    ( ! c_x  || ! d_x  || !d_y || !d_z || ! c_w || ! a_w || ! a_z ||  a_y || ! c_y) &&
    ( ! c_x  || ! d_x  || !d_y || d_z || ! c_w || ! a_w ||  a_z || ! a_y ||  c_y) &&
    ( ! c_x  || ! d_x  || !d_y || d_z ||  c_w || ! c_y) &&
    ( ! c_x  || ! d_x  || !d_y || d_z ||  a_z ||  a_y || ! c_y) &&
    ( ! c_x  || ! d_x  || !d_y ||  c_w ||  a_w ||  a_y || ! c_y) &&
    ( ! c_x  || ! d_x  || d_y || !d_z ||  a_w || ! a_z || ! a_y || ! c_y) &&
    ( ! c_x  ||  d_x  || !d_y || !d_z || ! c_w ||  b_w || ! a_w ||  a_z || ! a_y ||  c_y) &&
    ( ! c_x  ||  d_x  || d_y || ! c_w || ! b_w || ! b_z ||  c_y) &&
    ( ! c_x  ||  d_x  || ! c_w || ! b_w || ! b_z ||  a_w ||  a_z) &&
    ( c_x  || ! d_x  || d_y || !d_z ||  c_y) &&
    ( c_x  || ! d_x  || !d_z ||  a_w ||  a_z) &&

    ( c_x  ||  d_x  || !d_y || !d_z || ! c_w || ! a_w || ! a_z ||  a_y || ! c_y) &&
    ( c_x  ||  d_x  || d_y ||  b_z ||  a_w) &&
    ( c_x  ||  d_x  || d_z ||  c_w) &&
    ( c_x  ||  d_x  || d_z ||  a_z) &&
    ( c_x  ||  d_x  ||  c_w ||  a_w) &&

    ( c_x  || d_y ||  b_z ||  c_y) &&

    ( c_x  || d_z || ! c_w || ! a_w ||  a_z || ! a_y || ! c_y) &&

    ( c_x  ||  c_w ||  c_y) &&

    ( c_x  ||  b_z ||  a_w ||  a_z) &&
    ( c_x  ||  b_z ||  a_z ||  c_y) &&

    ( c_x  ||  a_z ||  a_y ||  c_y) && (

     ! d_x  || !d_y || !d_z || ! b_w || ! b_z || ! a_w ||  a_z || ! a_y ||  c_y) &&
     ( ! d_x  || d_y || !d_z ||  b_w ||  c_y) &&
     ( ! d_x  || !d_z ||  b_w ||  a_w ||  a_z) &&

      ( d_x  || !d_y || !d_z || ! c_w ||  b_z || ! a_w || ! a_z || ! a_y || ! c_y) &&
      ( d_x  || !d_y || !d_z ||  c_w ||  a_w || ! a_z || ! a_y) && ( d_x  || d_y || d_z) &&
    ( d_x  || d_y ||  a_w ||  a_y) && ( d_x  || d_z ||  a_w ||  a_z) &&

    ( !d_y || !d_z || ! c_w || ! b_w || ! b_z || ! a_w || ! a_z ||  a_y || ! c_y) &&
    ( !d_y || !d_z || ! c_w ||  b_w ||  b_z || ! a_w || ! a_z || ! c_y) &&
    ( !d_y || !d_z ||  c_w || ! b_w || ! b_z || ! a_w ||  a_z || ! a_y ||  c_y) &&
    ( !d_y || !d_z ||  c_w ||  b_z || ! a_z || ! a_y ||  c_y) &&
    ( !d_y || !d_z ||  c_w ||  a_w || ! a_z || ! a_y ||  c_y) &&

    (d_y || d_z || ! c_w || ! b_w || ! b_z ||  a_w || ! a_z || ! a_y || ! c_y) &&
    (d_y || d_z || ! c_w ||  b_w || ! a_w || ! a_y || ! c_y) &&
    (d_y || d_z || ! c_w || ! a_w ||  a_z || ! a_y || ! c_y) &&
    (d_y || d_z ||  c_w ||  c_y) &&
    (d_y || d_z || ! b_w || ! b_z || ! a_w || ! a_z ||  a_y) &&
    (d_y || d_z ||  b_w ||  b_z) &&
    (d_y || d_z ||  b_z ||  c_y) &&

    (d_y ||  c_w ||  b_w ||  c_y) &&
    (d_y ||  b_w ||  b_z ||  a_w) &&
    (d_y ||  b_w ||  b_z ||  c_y) &&
    (d_y ||  b_w ||  a_w ||  a_y) &&

     (d_y ||  a_y ||  c_y) &&

     (d_z ||  c_w || ! b_w || ! b_z || ! a_w || ! a_z) &&
     (d_z ||  c_w ||  b_w ||  b_z) &&
     (d_z ||  c_w ||  a_w ||  a_z) &&
     (d_z ||  b_z ||  a_z)

     ( c_w || ! b_w || ! b_z || ! a_w || ! a_z ||  a_y ||  c_y) &&
     ( c_w ||  b_w ||  b_z ||  c_y) &&
     ( c_w ||  b_w ||  a_w)  &&

    ( b_w ||  b_z ||  a_w ||  a_z) &&
    ( b_w ||  b_z ||  a_z ||  c_y) &&

    ( b_z ||  a_z ||  a_y ||  c_y) &&
    ( a_w ||  a_z ||  a_y)

     */
}
