package FeedbackTypes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RRRR extends Feedback {
    String boolTrans;

    public RRRR(String guess) {
        super("RRRR", guess);
        translate();
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


    //Translating from input guess and feedback to a boolean formula
    private void translate(){
        //Creating atom objects from the input string
        String[] atomString= guess.split(",");
        List<Atom> atomList = new ArrayList<>();
        //Getting the complement, since the original atom is not used in bool. trans.
        for (String s: atomString) {
            Atom a = new Atom("!"+s);
            atomList.add(a);
        }

        //Adding all the negated atoms to a set
        Set<Atom> formula = new HashSet<>();
        formula.addAll(atomList);

        //Generating all the new knowledge
        for (Atom a: atomList) {
            for (String p: positions) {
                if(!a.position.equals(p)){
                   formula.add(new Atom("!"+a.color +"_"+p));
                }
            }
        }

        //Creating a string with all
        StringBuilder trans = new StringBuilder();
        for (Atom a: formula) {
            trans.append(a.stringRep + " && ");
        }

        boolTrans = trans.substring(0,trans.lastIndexOf("&&"));
    }
}
