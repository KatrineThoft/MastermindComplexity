package FeedbackTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GRRR extends Feedback {
    String boolTrans;

    public GRRR(String guess) {
        super("GRRR", guess);
        translate();
    }

    private void translate() {
        StringBuilder res = new StringBuilder();

        String[] atomString = guess.split(",");
        List<Atom> posAtoms = new ArrayList<>();
        List<Atom> negAtoms = new ArrayList<>();
        //Getting the original atom
        for (String s : atomString) {
            Atom a = new Atom(s);
            posAtoms.add(a);
            negAtoms.add(a.getComplement());

        }

        res.append(generateOrClause(posAtoms));

        for (int i = 0; i <negAtoms.size() ; i++) {
                    res.append(generateSingleOrClauses(negAtoms.get(0), negAtoms.get(i)));
        }
        for (int i = 2; i <negAtoms.size() ; i++) {
            res.append(generateSingleOrClauses(negAtoms.get(1), negAtoms.get(i)));
        }

        res.append(generateSingleOrClauses(negAtoms.get(2),negAtoms.get(3)));

        boolTrans = res.substring(0,res.lastIndexOf("&&"));
    }

    private String generateSingleOrClauses(Atom a, Atom b) {
        return "(" + a.stringRep + " || " + b.stringRep + ") && \n";
    }

    private String generateOrClause(List<Atom> atoms) {
        StringBuilder temp = new StringBuilder();
        temp.append("(");
        for (Atom a : atoms) {
            temp.append(a.stringRep + " || ");
        }
        return temp.substring(0,temp.lastIndexOf("||"))+") &&";
    }
/*


   ( !c_z || !d_w)
 */


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

    /*( !a_x || !b_y) &&
    ( !a_x || !c_z) &&
    ( !a_x || !d_w) &&

    (a_x || b_y || c_z || d_w) &&

    ( !b_y || !c_z) &&
   ( !b_y || !d_w) &&

   ( !c_z || !d_w)

     */
}
