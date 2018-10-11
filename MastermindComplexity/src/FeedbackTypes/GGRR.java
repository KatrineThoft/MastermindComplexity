package FeedbackTypes;

import java.util.*;

public class GGRR extends Feedback {
    String boolTrans;
    public GGRR(String guess) {
        super("GGRR", guess);
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


    public void translate(){
        String[] atomString= guess.split(",");
        List<Atom> posAtoms = new ArrayList<>();
        //Getting the original atom
        for (String s: atomString) {
            Atom a = new Atom(s);
            posAtoms.add(a);
        }


        //Generating all necessary atoms
        List<Atom> allAs = new ArrayList<>();
        allAs.add(posAtoms.get(0).getComplement());
        allAs.addAll(generateAtoms(posAtoms.get(0),true));

        List<Atom> allBs = Collections.singletonList(new Atom("!" + posAtoms.get(1).color + "_x"));
        List<Atom> allCs = Arrays.asList(new Atom("!"+posAtoms.get(2).color +"_x"),
                                         new Atom("!"+posAtoms.get(2).color +"_w"));
        List<Atom> allDs =  new ArrayList<>();
        allDs.addAll(generateAtoms(posAtoms.get(3),true));
        allDs.set(1, posAtoms.get(3).getComplement());


        String posCClauses = generateOrClauses(posAtoms.get(2),allAs.get(0),
                allDs.subList(1,allDs.size()),allCs.subList(1,allCs.size()));


        List<Atom> case1 = new ArrayList<>();
        case1.add(allCs.get(0));
        case1.add(allAs.get(2));
        case1.add(posAtoms.get(3));

        posCClauses += generateOrClauses(posAtoms.get(2),posAtoms.get(0), case1.subList(0,2), case1.subList(2,case1.size()));
        posCClauses += generateSingleOrClauses(posAtoms.get(2),posAtoms.get(1));

        String negCClauses = generateOrClauses(posAtoms.get(2).getComplement(),posAtoms.get(3),allAs.subList(2,3),allDs.subList(0,1));

        String bClauses = generateSingleOrClauses(posAtoms.get(1),allBs.get(0));
        bClauses += generateSingleOrClauses(posAtoms.get(1), posAtoms.get(3));
        bClauses += generateSingleOrClauses(posAtoms.get(1), allAs.get(1));

        boolTrans =posCClauses + negCClauses + bClauses;
    }


    private List<Atom> generateAtoms(Atom a, Boolean negated){
        List<Atom> res =new ArrayList<>();
        String[] pos  = {"x","y","z","w"};
        String neg ="";
        if(negated){
            neg = "!";
        }
        for (int i = 0; i <pos.length ; i++) {
            if (!a.position.equals(pos[i])){
                res.add(new Atom(neg+a.color+"_"+pos[i]));
            }
        }

        return res;
    }


    private String generateOrClauses(Atom a, Atom b, List<Atom> list1, List<Atom> list2){
        StringBuilder temp = new StringBuilder();
        String ab = a.stringRep + " || " + b.stringRep;

            for (Atom aList1 : list1) {
                temp.append("(" + ab + " || " + aList1.stringRep + ") && \n");
            }

            for (Atom aList2 : list2) {
                temp.append("(" + ab + " || " + aList2.stringRep + ") && \n");
            }

            return temp.toString();

    }


    private String generateSingleOrClauses(Atom a, Atom b){
        return "("+ a.stringRep + " || " + b.stringRep + ") && \n";
    }
    /*
    ( ! a_x || ! d_z ||  c_z) &&
    ( ! a_x || ! d_w ||  c_z) &&
    ( ! a_x || ! c_w ||  c_z) &&
    ( a_x || ! c_x ||  c_z) &&
    ( a_x ||  d_w ||  c_z) &&
    ( a_x ||  c_z || ! a_z) &&


    ( ! d_x ||  d_w || ! c_z) &&
    ( d_w || ! c_z || ! a_w) &&
    ( ! b_x ||  b_y) &&
    ( d_w ||  b_y) &&
    ( c_z ||  b_y) &&
    ( ! a_y ||  b_y)
     */
}


