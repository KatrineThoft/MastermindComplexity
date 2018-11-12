package FeedbackTypes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
//Class representing feedback of the type GGOO
public class GGOO extends Feedback{
    Set<Clause> clauses = new HashSet<>();

    public GGOO(String guess) {
        super("GGOO", guess);
        translate();
        super.clauses = clauses;
    }

    @Override
    public int noXOR() {
        return 5;
    }

    //Method translating a guess from into a Boolean translation
    //Saves all data in Clause objects.
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
            negAtoms.add(a.getComplement());
        }

        List<Atom> allAs = generateAtoms(posAtoms.get(0));
        allAs.add(negAtoms.get(0));
        List<Atom> allBs = generateAtoms(posAtoms.get(1));
        allBs.add(negAtoms.get(1));
        List<Atom> allCs = generateAtoms(posAtoms.get(2));
        allCs.add(negAtoms.get(2));
        List<Atom> allDs = generateAtoms(posAtoms.get(3));
        allDs.add(negAtoms.get(3));

        //Generating clauses with !ax
        String negACases = generateOrClauses(negAtoms.get(0),negAtoms.get(1),
                allCs.subList(2,allCs.size()),allDs.subList(2,allDs.size()));

        negACases += generateOrClauses(negAtoms.get(0),negAtoms.get(2),
                allBs.subList(2,allCs.size()-1),allDs.subList(2,allDs.size()));


        //getting all atoms in the clauses with ax and by
        List<Atom> case1 = new ArrayList<>();
        case1.add(allAs.get(1));
        case1.add(allBs.get(0));

        //getting all atoms in the clauses with ax and cz
        List<Atom> case2 = new ArrayList<>();
        case2.add(allAs.get(2));
        case2.add(allCs.get(0));
        case2.add(posAtoms.get(3));


        String posACases = generateOrClauses(posAtoms.get(0),posAtoms.get(1),case1, posAtoms.subList(2,4));
        posACases += generateOrClauses(posAtoms.get(0),posAtoms.get(2), case2.subList(0,2), case2.subList(2,3));



        List<Atom> case3 = new ArrayList<>();
        case3.add(allAs.get(2));
        case3.add(allDs.get(0));
        case3.add(allDs.get(3));


        List<Atom> case4 = new ArrayList<>();
        case4.add(allBs.get(1));
        case4.add(allCs.get(1));
        case4.add(posAtoms.get(3));

        String bCases = generateOrClauses(negAtoms.get(1),negAtoms.get(2),case3.subList(0,2),case3.subList(2,3));
        bCases += generateOrClauses(posAtoms.get(1),posAtoms.get(2),case4.subList(0,2),case4.subList(2,3));


        super.boolTrans = negACases+posACases +bCases.substring(0,bCases.lastIndexOf("&&"));

    }

    //Generates Atom objects
    // creates Atoms for each position for one color
    private List<Atom> generateAtoms(Atom a){
        List<Atom> res =new ArrayList<>();
        String[] pos  = {"x","y","z","w"};
        String neg ="";

        for (int i = 0; i <pos.length ; i++) {
            if (!a.position.equals(pos[i])){
                res.add(new Atom(neg+a.color+"_"+pos[i]));
            } else {
                    res.add(a);
                }
            }
        return res;
    }


    //Method used by translate() to generate clauses
    private String generateOrClauses(Atom a, Atom b, List<Atom> list1, List<Atom> list2){
        StringBuilder temp = new StringBuilder();
        String ab = a.stringRep + " || " + b.stringRep;

        for (Atom aList1 : list1) {
            temp.append("(" + ab + " || " + aList1.stringRep + ") && \n");
            addClause(a,b,aList1);
        }

        for (Atom aList2 : list2) {
            temp.append("(" + ab + " || " + aList2.stringRep + ") && \n");
            addClause(a,b,aList2);
        }

        return   temp.toString();
    }

    //Adds clauses to data set
    private void addClause(Atom a, Atom b, Atom c){
        Set<Atom> atomSet = new HashSet<>();
        atomSet.add(a);
        atomSet.add(b);
        atomSet.add(c);
        clauses.add(new Clause(atomSet));
    }

}
