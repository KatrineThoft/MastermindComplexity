package FeedbackTypes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OO extends Feedback {
    Set<Clause> clauses = new HashSet<>();
    public OO(String guess) {
        super("OO", guess);
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
            Atom a = new Atom("!"+s);
            atomList.add(a);
            atomList.addAll(generateAtoms(a,false));

        }


        //Adding all the negated atoms to a set
        Set<Atom> formula = new HashSet<>();
        formula.add(atomList.get(0));
        formula.addAll(atomList.subList(2,5));


        addClause(formula);

        StringBuilder trans = new StringBuilder();
        for (Atom a: formula) {
            trans.append(a.stringRep + " && ");
        }

        super.boolTrans = trans.substring(0,trans.lastIndexOf("&&"));
    }

    //Adds clauses to data set
    private void addClause(Set<Atom> list){
        for (Atom a: list) {
            Set<Atom> atomSet = new HashSet<>();
            atomSet.add(a);
            clauses.add(new Clause(atomSet));
        }
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
