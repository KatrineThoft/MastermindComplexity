package FeedbackTypes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GGOO extends Feedback{
    String boolTrans;
    Set<Atom> allAtoms;

    public GGOO(String guess) {
        super("GGOO", guess);
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
        String[] atomString= guess.split(",");
        List<Atom> posAtoms = new ArrayList<>();
        //Getting the original atom
        for (String s: atomString) {
            Atom a = new Atom(s);
            posAtoms.add(a);
        }

        List<Atom> negAtoms = new ArrayList<>();
        for (Atom a: posAtoms) {
            Atom x =a.getComplement();
            posAtoms.add(x);
        }

        String allAs = generateAtoms(posAtoms.get(0));
        String allBs = generateAtoms(posAtoms.get(1));
        String allCs = generateAtoms(posAtoms.get(2));
        List<Atom> allDs = generateAtoms(posAtoms.get(3));

    }

    private List<Atom> generateAtoms(Atom a){
        List<Atom> res =new ArrayList<>();
        for (String p:positions) {
            if (!a.position.equals(p)){
                res.add(new Atom(a.color+"_"+p));
            }
        }
        return res;
    }

    private String generateOrClauses(Atom a){
        StringBuilder temp = new StringBuilder();




        return temp.toString();
    }
    /*
    ( !a_x || d_y || !c_z) &&
    ( !a_x || d_z || !b_y) &&
    ( !a_x || !d_w || !c_z) &&
    ( !a_x || !d_w || !b_y) &&
    ( !a_x || c_w || !b_y) &&
    ( !a_x || !c_z || b_w) &&
    ( !a_x || !c_z || !b_y) &&

    (a_x || b_x || b_y) &&
    (a_x || c_x || c_z) &&
    (a_x || d_w || c_z) &&
    (a_x || d_w || b_y) &&
    (a_x || c_z || a_z) &&
    (a_x || c_z || b_y) &&
    (a_x || a_y || b_y) &&

    (d_x || !c_z || !b_y) &&
    ( !d_w || !c_z || !b_y) &&
    (d_w || c_z || b_y) &&

    ( !c_z || a_w || !b_y) &&
    (c_z || b_z || b_y) &&
    (c_z || c_y || b_y)
     */
}
