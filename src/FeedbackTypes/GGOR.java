package FeedbackTypes;

import java.util.*;
//Class representing feedback of the type GGOR
public class GGOR extends Feedback {
    Set<Clause> clauses = new HashSet<>();
    public GGOR(String guess) {
        super("GGOR", guess);
        translate();
        super.clauses = clauses;
    }

    //Method translating a guess from into a Boolean translation
    //Saves all data in Clause objects.
    private void translate() {
        StringBuilder temp = new StringBuilder();
        String[] atomString= guess.split(",");
        List<Atom> atomList = new ArrayList<>();

        //Getting the complement, since the original atom is not used in bool. trans.

        for (String s: atomString) {
            Atom a = new Atom(s);
            atomList.add(a);
        }

        List<List<Atom>> posAtoms = new ArrayList<>();
        List<List<Atom>> negAtoms = new ArrayList<>();
        for (Atom a:atomList) {
            posAtoms .add(generateAtoms(a,false));
            negAtoms.add(generateAtoms(a,true));
        }
        temp.append(generateCases(posAtoms,negAtoms));
        super.boolTrans =temp.substring(0,temp.lastIndexOf("&&"));
    }

    //Used by translate() to genereate the Boolean translation
    //one OR clause at at time
    private String generateCases(List<List<Atom>> posAtoms, List<List<Atom>> negAtoms) {

        StringBuilder temp = new StringBuilder();



        List<Atom> Alist = Arrays.asList(negAtoms.get(0).get(0), negAtoms.get(0).get(3),
                posAtoms.get(0).get(0), posAtoms.get(0).get(2));
        List<Atom> Blist = Arrays.asList(negAtoms.get(1).get(1), negAtoms.get(1).get(3),
                posAtoms.get(1).get(1));
        List<Atom> Clist = Arrays.asList(negAtoms.get(2).get(0), negAtoms.get(2).get(2),
                posAtoms.get(2).get(2));
        List<Atom> Dlist = Arrays.asList(negAtoms.get(3).get(1), negAtoms.get(3).get(3),
                posAtoms.get(3).get(0),posAtoms.get(3).get(1),posAtoms.get(3).get(3));

        List<List<Atom>> allCases = new ArrayList<>();
        List<Atom> case1 = new ArrayList<>();
        case1.addAll(Alist.subList(1,3));
        case1.add(Blist.get(0));
        case1.add(Clist.get(1));
        case1.add(Dlist.get(0));
        case1.add(Dlist.get(2));
        case1.add(Dlist.get(4));
        allCases.add(case1);

        List<Atom> case2 = new ArrayList<>();
        case2.add(Alist.get(0));
        case2.addAll(Blist.subList(1,3));
        case2.add(Clist.get(1));
        case2.addAll(Dlist.subList(3,5));
        allCases.add(case2);

        List<Atom> case3 = new ArrayList<>();
        case3.addAll(Alist.subList(2,4));
        case3.add(Blist.get(0));
        case3.add(Clist.get(0));
        case3.add(Clist.get(2));
        case3.addAll(Dlist.subList(0,2));
        allCases.add(case3);

        for (int i = 0; i <allCases.size() ; i++) {
            temp.append(generateOrClause(allCases.get(i)));

        }
        return temp.toString();
    }

    @Override
    public int noXOR() {
        return 11;
    }


    //Generates a singe OR clause
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

    //Adds clauses to data set
    private void addClauseFromList(List<Atom> list){
        Set<Atom> atomSet = new HashSet<>();
        atomSet.addAll(list);
        clauses.add(new Clause(atomSet));
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
