package FeedbackTypes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
//Class representing feedback of the type OR
public class OR extends Feedback {
    Set<Clause> clauses = new HashSet<>();
    public OR(String guess) {
        super("OR", guess);
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
            Atom a1 = new Atom("!"+s);
            atomList.add(a1);
            atomList.addAll(generateAtoms(a1,false));

        }

        Set<List<Atom>> allCases = new HashSet<>();

        //Generating the cases:
        List<Atom> case1 = new ArrayList<>();
        case1.add(atomList.get(0));
        allCases.add(case1);

        List<Atom> case2 = new ArrayList<>();
        case2.add(atomList.get(3));
        allCases.add(case2);

        List<Atom> case3 = new ArrayList<>();
        case3.add(atomList.get(2));
        case3.add(atomList.get(4));
        allCases.add(case3);

        List<Atom> case4 = new ArrayList<>();
        case4.add(atomList.get(2).getComplement());
        case4.add(atomList.get(4).getComplement());
        allCases.add(case4);


        StringBuilder trans = new StringBuilder();
        for (List<Atom> ca: allCases) {
            if(ca.size() <2){
                trans.append(generateAndClauses(ca));
            } else {
                trans.append(generateSingleOrClauses(ca));
            }
        }

        super.boolTrans = trans.substring(0,trans.lastIndexOf("&&"));
    }

    //Adds clauses to data set
    private void addClause(List<Atom> list){
        for (Atom a: list) {
            Set<Atom> atomSet = new HashSet<>();
            atomSet.add(a);
            clauses.add(new Clause(atomSet));
        }
    }

    //Generates a singe OR clause from a list of Atoms
    private String generateSingleOrClauses(List<Atom> list){
        addClause(list);
        return "("+ list.get(0).stringRep + " || " + list.get(1).stringRep + ") && \n";
    }

    //Generates a singe AND clause from a list of Atoms
    private String generateAndClauses(List<Atom> list){
        addClause(list);
        return list.get(0).stringRep + " && \n " ;
    }

    @Override
    public int noXOR() {
        return 1;
    }

    //Generates Atom objects
    // creates Atoms for each position for one color
    private List<Atom> generateAtoms(Atom a, Boolean negated){
        List<Atom> res =new ArrayList<>();
        String[] pos  = {"x","y"};
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


