package FeedbackTypes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ORRR extends Feedback {
    String boolTrans;

    public ORRR(String guess) {
        super("ORRR", guess);
        translate();
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
    public String prettyPrint() {
        return null;
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

    private void translate(){
        //Creating atom objects from the input string
        String[] atomString= guess.split(",");
        List<Atom> atomList = new ArrayList<>();
        //Getting the complement
        for (String s: atomString) {
            Atom a = new Atom("!"+s);
            atomList.add(a);
        }



        Set<Atom> formula = new HashSet<>();
        StringBuilder trans = new StringBuilder();
        for (Atom a: formula) {
            trans.append(a.stringRep + " && ");
        }

        boolTrans = trans.substring(0,trans.lastIndexOf("&&"));
    }
}