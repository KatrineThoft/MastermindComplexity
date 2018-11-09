package FeedbackTypes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
//Class representing feedback of the type RR
public class RR extends Feedback {
    Set<Clause> clauses = new HashSet<>();
    public RR(String guess) {
        super("RR", guess);
        translate();
        super.clauses =clauses;

    }

    //Method translating a guess from into a Boolean translation
    //Saves all data in Clause objects.
    private void translate() {
        String[] atomString= guess.split(",");
        List<Atom> atomList = new ArrayList<>();
        //Getting the complement, since the original atom is not used in bool. trans.
        for (String s: atomString) {
            Atom a1 = new Atom(s);
            atomList.add(a1);
            atomList.addAll(generateAtoms(a1,true));

        }
       List<Atom> allCases = new ArrayList<>();
        allCases.addAll(atomList.subList(1,3));
        allCases.addAll(atomList.subList(4,6));


        //Generating the cases:
        StringBuilder trans = new StringBuilder();
        for (Atom ca: allCases) {
                trans.append(generateAndClauses(ca));

        }

        super.boolTrans = trans.substring(0,trans.lastIndexOf("&&"));
    }

    //Adds clauses to data set
    private void addClause(Atom a){
            Set<Atom> atomSet = new HashSet<>();
            atomSet.add(a);
            clauses.add(new Clause(atomSet));

    }


    //Generates a single AND clause
    private String generateAndClauses(Atom a){
        addClause(a);
        return a.stringRep + " && \n " ;
    }

    @Override
    public int noXOR() {
        return 0;
    }

    //Generates Atom objects
    // creates Atoms for each position for one color
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
            } else {
                if(negated){
                    res.add(a.getComplement());
                }
                else {
                    res.add(a);
                }
            }
        }
        return res;
    }

}
