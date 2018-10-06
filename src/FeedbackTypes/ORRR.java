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
        formula.addAll(atomList);

        Set<Atom> atomSetA = generateAtoms(atomList.get(0),true);
        Set<Atom> atomSetB = generateAtoms(atomList.get(1), true);
        Set<Atom> atomSetC = generateAtoms(atomList.get(2),true);
        Set<Atom> atomSetD = generateAtoms(atomList.get(3), true);

        Set<Atom> case1 = new HashSet<>();
        case1.addAll(atomSetA);
        case1.addAll(atomSetB);
        case1.addAll(atomSetC);

        Set<Atom> case2 = new HashSet<>();
        case2.addAll(atomSetA);
        case2.addAll(atomSetB);
        case2.addAll(atomSetD);

        Set<Atom> case3 = new HashSet<>();
        case3.addAll(atomSetA);
        case3.addAll(atomSetC);
        case3.addAll(atomSetD);

        Set<Atom> case4 = new HashSet<>();
        case4.addAll(atomSetB);
        case4.addAll(atomSetC);
        case4.addAll(atomSetD);


        Set<Atom> posA =  generateAtoms(atomList.get(0),false);
        Set<Atom> posB =  generateAtoms(atomList.get(1),false);
        Set<Atom> posC =  generateAtoms(atomList.get(2),false);
        Set<Atom> posD =  generateAtoms(atomList.get(3),false);


        StringBuilder trans = new StringBuilder();
        for (Atom a: formula) {
            trans.append(a.stringRep + " && ");
        }
        trans.append("((");

        trans.append(addCases(case1,posD)+"XOR \n");
        trans.append(addCases(case2,posC)+"XOR \n");
        trans.append(addCases(case3,posB)+"XOR \n");
        trans.append(addCases(case4,posA)+")");

        boolTrans = trans.toString();
    }

    private String addCases(Set<Atom> negAtoms, Set<Atom> posAtoms) {

        String res="";
        for (Atom a: negAtoms) {
            res+=a.stringRep + " && ";
        }

        res+=("(");

        for (Atom a:posAtoms) {

            res += a.stringRep + " XOR ";
        }

        res = res.substring(0,res.lastIndexOf("XOR"));
        res +="))";

        return res;
    }


    private Set<Atom> generateAtoms(Atom a, boolean isNegated){
        Set<Atom> res = new HashSet<>();

        String neg = "";
        if(isNegated){
            neg = "!";
        }

        for (String p:positions) {
            if (!a.position.equals(p)){
                res.add(new Atom(neg+a.color+"_"+p));
            }
        }
        return res;
    }
}
