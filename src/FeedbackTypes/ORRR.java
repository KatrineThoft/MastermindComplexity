package FeedbackTypes;

import java.util.*;
//Class representing feedback of the type ORRR
public class ORRR extends Feedback {
    Set<Clause> clauses= new HashSet<>();

    public ORRR(String guess) {
        super("ORRR", guess);
        translate();
        super.clauses = clauses;
    }

    @Override
    public int noXOR() {
        return 11;
    }

    //Method translating a guess from into a Boolean translation
    //Saves all data in Clause objects.
    public void translate(){
        //Creating atom objects from the input string
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
        //temp.append(generateAndClauses(negGuess));

        List<List<Atom>> posAtoms = new ArrayList<>();
        List<List<Atom>> negAtoms = new ArrayList<>();
        for (Atom a:atomList) {
            posAtoms .add(generateAtoms(a,false));
            negAtoms.add(generateAtoms(a,true));
        }

        temp.append(generateNegDxCases(posAtoms,negAtoms));
        temp.append(generateNegDzCases(posAtoms,negAtoms));
        temp.append(generateNegAwCases(posAtoms,negAtoms));
        temp.append(generateNegBwCases(posAtoms,negAtoms));
        temp.append(generateNegDyCases(posAtoms,negAtoms));
        temp.append(generateNegAzCases(posAtoms,negAtoms));
        temp.append(generateNegCyCases(posAtoms,negAtoms));
        temp.append(generateNegAyCases(posAtoms,negAtoms));
        temp.append(generateRestCases(posAtoms,negAtoms));
        super.boolTrans =temp.substring(0,temp.lastIndexOf("&&"));
    }

    //Methods generating the clauses of the Boolean translation one clause at a time
    private String generateNegAyCases(List<List<Atom>> posAtoms, List<List<Atom>> negAtoms) {
        StringBuilder temp = new StringBuilder();
        Atom negAy= negAtoms.get(0).get(1);
        List<Atom> Alist = Arrays.asList(negAtoms.get(0).get(2),posAtoms.get(0).get(3));
        List<Atom> Blist = Arrays.asList(negAtoms.get(1).get(0), negAtoms.get(1).get(2));
        List<Atom> Clist = Arrays.asList(negAtoms.get(2).get(0));

        List<Atom> case1 = new ArrayList<>();
        case1.add(negAy);
        case1.addAll(Alist);


        List<Atom> cases = new ArrayList<>();
        cases.addAll(Blist);
        cases.addAll(Clist);

        temp.append(generateOrClause(case1));

        temp.append(generateMultiOrClauses(negAy, cases));
        return temp.toString();

    }
    private String generateNegCyCases(List<List<Atom>> posAtoms, List<List<Atom>> negAtoms) {
        StringBuilder temp = new StringBuilder();
        Atom negCy= negAtoms.get(2).get(1);
        List<Atom> Alist = Arrays.asList(negAtoms.get(0).get(1));
        List<Atom> Blist = Arrays.asList(negAtoms.get(1).get(0), negAtoms.get(1).get(2));
        List<Atom> Clist = Arrays.asList(negAtoms.get(2).get(0), posAtoms.get(2).get(0),
                posAtoms.get(2).get(3));

        List<Atom> case1 = new ArrayList<>();
        case1.add(negCy);
        case1.addAll(Clist.subList(1,3));

        List<Atom> case2 = new ArrayList<>();
        case2.add(negCy);
        case2.add(Clist.get(0));
        case2.add(Clist.get(2));;

        List<Atom> cases = new ArrayList<>();
        cases.addAll(Alist);
        cases.addAll(Blist);

        temp.append(generateOrClause(case1));
        temp.append(generateOrClause(case2));

        temp.append(generateMultiOrClauses(negCy, cases));
        return temp.toString();

    }
    private String generateNegAzCases(List<List<Atom>> posAtoms, List<List<Atom>> negAtoms) {
        StringBuilder temp = new StringBuilder();
        Atom negAz= negAtoms.get(0).get(2);

        List<Atom> Blist = Arrays.asList(negAtoms.get(1).get(0), negAtoms.get(1).get(2));
        List<Atom> Clist = Arrays.asList(negAtoms.get(2).get(0), negAtoms.get(2).get(1));


        List<Atom> cases = new ArrayList<>();
        cases.addAll(Blist);
        cases.addAll(Clist);


        temp.append(generateMultiOrClauses(negAz, cases));
        return temp.toString();

    }
    private String generateRestCases(List<List<Atom>> posAtoms, List<List<Atom>> negAtoms) {
        StringBuilder temp = new StringBuilder();
        Atom negBx= negAtoms.get(1).get(0);
        Atom negBz= negAtoms.get(1).get(2);
        Atom posDx= posAtoms.get(3).get(0);


        List<Atom> Alist = Arrays.asList(posAtoms.get(0).get(1), posAtoms.get(0).get(2),
                posAtoms.get(0).get(3));
        List<Atom> Blist = Arrays.asList(negAtoms.get(1).get(2), posAtoms.get(1).get(0),
                posAtoms.get(1).get(2),posAtoms.get(1).get(3));
        List<Atom> Clist = Arrays.asList(negAtoms.get(2).get(0), posAtoms.get(2).get(0),
                posAtoms.get(2).get(1), posAtoms.get(2).get(3));
        List<Atom> Dlist = Arrays.asList(negAtoms.get(3).get(1), negAtoms.get(3).get(2),
                posAtoms.get(3).get(1),posAtoms.get(3).get(2));

        List<Atom> case1 = new ArrayList<>();
        case1.add(negBx);
        case1.add(Blist.get(0));
        case1.add(Blist.get(3));

        List<Atom> BxCases = new ArrayList<>();
        BxCases.add(Clist.get(0));
        BxCases.add(Clist.get(3));

        List<Atom> BzCases = new ArrayList<>();
        BzCases.add(Clist.get(0));
        BzCases.add(Clist.get(3));

        List<Atom> case2 = new ArrayList<>();
        case2.add(Clist.get(0));
        case2.addAll(Clist.subList(2,4));

        List<Atom> case3 = new ArrayList<>();
        case3.add(posDx);
        case3.addAll(Dlist.subList(0,2));

        List<Atom> case4 = new ArrayList<>();
        case4.add(posDx);
        case4.addAll(Alist);
        case4.addAll(Blist.subList(1,4));
        case4.addAll(Clist.subList(1,4));
        case4.addAll(Dlist.subList(2,4));

        temp.append(generateOrClause(case1));
        temp.append(generateMultiOrClauses(negBx, BxCases));
        temp.append(generateMultiOrClauses(negBz, BzCases));
        temp.append(generateOrClause(case2));
        temp.append(generateOrClause(case3));
        temp.append(generateOrClause(case4));

        return temp.toString();

    }
    private String generateNegDyCases(List<List<Atom>> posAtoms, List<List<Atom>> negAtoms) {

        StringBuilder temp = new StringBuilder();
        Atom negDy= negAtoms.get(3).get(1);

        List<Atom> Alist = Arrays.asList(negAtoms.get(0).get(1), negAtoms.get(0).get(2));
        List<Atom> Blist = Arrays.asList(negAtoms.get(1).get(0), negAtoms.get(1).get(2));
        List<Atom> Clist = Arrays.asList(negAtoms.get(2).get(0), negAtoms.get(2).get(1),
                posAtoms.get(2).get(3));


        List<Atom> cases = new ArrayList<>();
        cases.addAll(Alist);
        cases.addAll(Blist);
        cases.addAll(Clist);

        temp.append(generateMultiOrClauses(negDy, cases));
        return temp.toString();

    }
    private String generateNegBwCases(List<List<Atom>> posAtoms, List<List<Atom>> negAtoms) {

        StringBuilder temp = new StringBuilder();
        Atom negBw= negAtoms.get(1).get(3);

        List<Atom> Alist = Arrays.asList(negAtoms.get(0).get(1), negAtoms.get(0).get(2));
        List<Atom> Blist = Arrays.asList(negAtoms.get(1).get(0), negAtoms.get(1).get(2),
                posAtoms.get(1).get(0), posAtoms.get(1).get(2));
        List<Atom> Clist = Arrays.asList(negAtoms.get(2).get(0), negAtoms.get(2).get(1),
                posAtoms.get(2).get(3));
        List<Atom> Dlist = Arrays.asList(negAtoms.get(3).get(1));

        List<Atom> case1 = new ArrayList<>();
        case1.add(negBw);
        case1.addAll(Blist.subList(1,3));

        List<Atom> case2 = new ArrayList<>();
        case2.add(negBw);
        case2.add(Blist.get(0));
        case2.add(Blist.get(3));

        List<Atom> cases = new ArrayList<>();
        cases.addAll(Alist);
        cases.addAll(Clist);
        cases.addAll(Dlist);

        temp.append(generateOrClause(case1));
        temp.append(generateOrClause(case2));
        temp.append(generateMultiOrClauses(negBw, cases));
        return temp.toString();

    }
    private String generateNegAwCases(List<List<Atom>> posAtoms, List<List<Atom>> negAtoms) {

        StringBuilder temp = new StringBuilder();
        Atom negAw= negAtoms.get(0).get(3);

        List<Atom> Alist = Arrays.asList(negAtoms.get(0).get(1), negAtoms.get(0).get(2),
                posAtoms.get(0).get(1), posAtoms.get(0).get(2));
        List<Atom> Blist = Arrays.asList(negAtoms.get(1).get(0), negAtoms.get(1).get(2),
                negAtoms.get(1).get(3));
        List<Atom> Clist = Arrays.asList(negAtoms.get(2).get(0), negAtoms.get(2).get(1));
        List<Atom> Dlist = Arrays.asList(negAtoms.get(3).get(1));

        List<Atom> case1 = new ArrayList<>();
        case1.add(negAw);
        case1.addAll(Alist.subList(1,3));

        List<Atom> case2 = new ArrayList<>();
        case2.add(negAw);
        case2.add(Alist.get(0));
        case2.add(Alist.get(3));

        List<Atom> cases = new ArrayList<>();
        cases.addAll(Blist);
        cases.addAll(Clist);
        cases.addAll(Dlist);

        temp.append(generateOrClause(case1));
        temp.append(generateOrClause(case2));
        temp.append(generateMultiOrClauses(negAw, cases));
        return temp.toString();

    }
    private String generateNegDzCases(List<List<Atom>> posAtoms, List<List<Atom>> negAtoms) {

        StringBuilder temp = new StringBuilder();
        Atom negDz = negAtoms.get(3).get(2);

        List<Atom> Alist = Arrays.asList(negAtoms.get(0).get(1), negAtoms.get(0).get(2),
                negAtoms.get(0).get(3));
        List<Atom> Blist = Arrays.asList(negAtoms.get(1).get(0), negAtoms.get(1).get(2),
                negAtoms.get(1).get(3));
        List<Atom> Clist = Arrays.asList(negAtoms.get(2).get(0), negAtoms.get(2).get(1),
                posAtoms.get(2).get(3));


        List<Atom> cases = new ArrayList<>();
        cases.addAll(Alist);
        cases.addAll(Blist);
        cases.addAll(Clist);


        temp.append(generateMultiOrClauses(negDz, cases));
        return temp.toString();

    }
    private String generateNegDxCases(List<List<Atom>> posAtoms, List<List<Atom>> negAtoms) {
        StringBuilder temp = new StringBuilder();
        Atom negDx = negAtoms.get(3).get(0);

        List<Atom> Alist = Arrays.asList(negAtoms.get(0).get(1), negAtoms.get(0).get(2),
                negAtoms.get(0).get(3));
        List<Atom> Blist = Arrays.asList(negAtoms.get(1).get(0), negAtoms.get(1).get(2),
                negAtoms.get(1).get(3));
        List<Atom> Clist = Arrays.asList(negAtoms.get(2).get(0), negAtoms.get(2).get(1),
                posAtoms.get(2).get(3));
        List<Atom> Dlist = Arrays.asList(negAtoms.get(3).get(1), negAtoms.get(3).get(2),
                posAtoms.get(3).get(1),posAtoms.get(3).get(2));


        List<Atom> case1 = new ArrayList<>();
        case1.add(negDx);
        case1.addAll(Dlist.subList(1,3));

        List<Atom> case2 = new ArrayList<>();
        case2.add(negDx);
        case2.add(Dlist.get(0));
        case2.add(Dlist.get(3));

        List<Atom> cases = new ArrayList<>();
        cases.addAll(Alist);
        cases.addAll(Blist);
        cases.addAll(Clist);



        temp.append(generateOrClause(case1));
        temp.append(generateOrClause(case2));
        temp.append(generateMultiOrClauses(negDx, cases));
        return temp.toString();
    }


    //Generates a OR clauses which all contains the same two Atoms
    private String generateMultiOrClauses(Atom a, List<Atom> list1){
        StringBuilder temp = new StringBuilder();

        for (Atom aList1 : list1) {
            temp.append( "("+ a.stringRep + " || "+aList1.stringRep +") &&\n");
            addClause(a,aList1);
        }
        return temp.toString();

    }


    //Generates a singe OR clause from a list of Atoms
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
    private void addClause(Atom a, Atom b){
        Set<Atom> atomSet = new HashSet<>();
        atomSet.add(a);
        atomSet.add(b);
        clauses.add(new Clause(atomSet));
    }

    //Generates a singe AND clause from a list of Atoms
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


    //translate in not cnf format
    private void translateNotCNF(){
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

        Set<Atom> atomSetA = generateAtoms1(atomList.get(0),true);
        Set<Atom> atomSetB = generateAtoms1(atomList.get(1), true);
        Set<Atom> atomSetC = generateAtoms1(atomList.get(2),true);
        Set<Atom> atomSetD = generateAtoms1(atomList.get(3), true);

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


        Set<Atom> posA =  generateAtoms1(atomList.get(0),false);
        Set<Atom> posB =  generateAtoms1(atomList.get(1),false);
        Set<Atom> posC =  generateAtoms1(atomList.get(2),false);
        Set<Atom> posD =  generateAtoms1(atomList.get(3),false);


        StringBuilder trans = new StringBuilder();
        for (Atom a: formula) {
            trans.append(a.stringRep + " && ");
        }
        trans.append("((");

        trans.append(addCases(case1,posD)+"XOR \n");
        trans.append(addCases(case2,posC)+"XOR \n");
        trans.append(addCases(case3,posB)+"XOR \n");
        trans.append(addCases(case4,posA)+")");

        super.boolTrans = trans.toString();
    }
    private Set<Atom> generateAtoms1(Atom a, boolean isNegated){
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

    //Generates Atom objects
    // creates Atoms for each position for one color
    List<Atom> generateAtoms(Atom a, Boolean negated){
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


}
