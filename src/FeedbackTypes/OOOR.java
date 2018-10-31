package FeedbackTypes;

import java.util.*;

public class OOOR extends Feedback {
    Set<Clause> clauses = new HashSet<>();
    public OOOR(String guess) {
        super("OOOR", guess);
        translate();
        super.clauses = clauses;
    }

    private void translate() {
        StringBuilder temp = new StringBuilder();
        String[] atomString= guess.split(",");
        List<Atom> atomList = new ArrayList<>();

        //Getting the complement, since the original atom is not used in bool. trans.


        List<Atom> negGuess = new ArrayList<>();
        for (String s: atomString) {
            Atom a = new Atom(s);
            atomList.add(a);
            negGuess.add(a.getComplement());
        }
        temp.append(generateAndClauses(negGuess));

        List<List<Atom>> posAtoms = new ArrayList<>();
        List<List<Atom>> negAtoms = new ArrayList<>();
        for (Atom a:atomList) {
            posAtoms .add(generateAtoms(a,false));
            negAtoms.add(generateAtoms(a,true));
        }
        temp.append(generateCases(posAtoms,negAtoms));
        super.boolTrans =temp.substring(0,temp.lastIndexOf("&&"));
    }

    private String generateCases(List<List<Atom>> posAtoms, List<List<Atom>> negAtoms) {
        StringBuilder temp = new StringBuilder();
        List<Atom> Alist = Arrays.asList(negAtoms.get(0).get(2),negAtoms.get(0).get(3),
                posAtoms.get(0).get(2),posAtoms.get(0).get(3));
        List<Atom> Blist = Arrays.asList(negAtoms.get(1).get(0),negAtoms.get(1).get(2));
        List<Atom> Clist = Arrays.asList(negAtoms.get(2).get(0),negAtoms.get(2).get(3),
                posAtoms.get(2).get(0),posAtoms.get(2).get(3));
        List<Atom> Dlist = Arrays.asList(negAtoms.get(3).get(1),posAtoms.get(3).get(1));

        List<List<Atom>> allCases = new ArrayList<>();

        List<Atom> case1 = new ArrayList<>();
        case1.addAll(Alist.subList(0,2));
        case1.add(Clist.get(3));
        case1.add(Dlist.get(0));
        allCases.add(case1);

        List<Atom> case2 = new ArrayList<>();
        case2.addAll(Alist.subList(1,3));
        case2.add(Clist.get(0));
        allCases.add(case2);

        List<Atom> case3 = new ArrayList<>();
        case3.add(Alist.get(0));
        case3.add(Alist.get(3));
        case3.add(Clist.get(0));
        case3.add(Dlist.get(1));
        allCases.add(case3);

        List<Atom> case4 = new ArrayList<>();
        case4.add(Alist.get(0));
        case4.add(Alist.get(3));
        case4.add(Clist.get(0));
        case4.add(Clist.get(3));
        allCases.add(case4);


        List<Atom> case5 = new ArrayList<>();
        case5.add(Alist.get(0));
        case5.addAll(Clist.subList(2,4));
        case5.add(Dlist.get(0));
        allCases.add(case5);


        List<Atom> case67 = new ArrayList<>();
        case67.addAll(Blist);

        for (int i = 0; i <allCases.size() ; i++) {
            temp.append(generateOrClause(allCases.get(i)));
        }

        temp.append(generateAndClauses(case67));
        return temp.toString();
    }

    /*
!b_x &&
!b_z
 */


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


    private  String generateOrClause(List<Atom> atoms){
        StringBuilder temp = new StringBuilder();
        temp.append("(");
        for (Atom a: atoms ) {
            temp.append(a.stringRep + " || ");
        }
        addClauseFromList(atoms);

        String res = temp.substring(0,temp.lastIndexOf("||")) + ") &&";
        return res + "\n";
    }


    private  String generateAndClauses(List<Atom> atoms){
        StringBuilder temp = new StringBuilder();

        for (Atom a: atoms ) {
            temp.append(a.stringRep + " && \n");
            Set<Atom> s = new HashSet<>();
            s.add(a);
            Clause c = new Clause(s);
            clauses.add(c);
        }

        return temp.toString();
    }

    private void addClauseFromList(List<Atom> list){
        Set<Atom> atomSet = new HashSet<>();
        atomSet.addAll(list);
        clauses.add(new Clause(atomSet));
    }

/*!a_x &&
!b_y &&
!c_z &&
!d_w&&


( !a_w || !d_y || !a_z || c_w) &&
 ( !a_w || a_z || !c_x) &&
  (a_w || d_y || !a_z || !c_x) &&
  (a_w || !a_z || !c_x ||c_w) &&
( !d_y || !a_z || c_x || c_w) &&
!b_x &&
!b_z
 */

    @Override
    public int noXOR() {
        return 43;
    }

}
