package FeedbackTypes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GRRR extends Feedback {
    Set<Clause> clauses = new HashSet<>();
    public GRRR(String guess) {
        super("GRRR", guess);
        translate();
        super.clauses =clauses;
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

        super.boolTrans = res.substring(0,res.lastIndexOf("&&"));
    }

    private String generateSingleOrClauses(Atom a, Atom b) {
        addClause(a,b);
        return "(" + a.stringRep + " || " + b.stringRep + ") && \n";
    }

    private String generateOrClause(List<Atom> atoms) {
        StringBuilder temp = new StringBuilder();
        temp.append("(");
        for (Atom a : atoms) {
            temp.append(a.stringRep + " || ");
        }
        addClauseFromList(atoms);
        return temp.substring(0, temp.lastIndexOf("||")) + ") && \n";
    }

    @Override
    public int noXOR() {
        return 0;
    }


    private void addClause(Atom a, Atom b){
        Set<Atom> atomSet = new HashSet<>();
        atomSet.add(a);
        atomSet.add(b);
        clauses.add(new Clause(atomSet));
    }
    private void addClauseFromList(List<Atom> list){
        Set<Atom> atomSet = new HashSet<>();
        atomSet.addAll(list);
        clauses.add(new Clause(atomSet));
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
