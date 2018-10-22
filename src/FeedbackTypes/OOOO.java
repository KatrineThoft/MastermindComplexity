package FeedbackTypes;

import java.util.*;

public class OOOO extends Feedback{
    Set<Clause> clauses = new HashSet<>();
    public OOOO(String guess) {
        super("OOOO", guess);
        translate();
        super.clauses = clauses;
    }

    private void translate() {

        String[] atomString= guess.split(",");
        List<Atom> posAtoms = new ArrayList<>();
        //Getting the original atom
        for (String s: atomString) {
            Atom a = new Atom(s);
            posAtoms.add(a);
        }

        List<List<Atom>> allPosAtomsList = new ArrayList<>();
        List<List<Atom>> allNegAtomsList = new ArrayList<>();
        for (Atom a:posAtoms) {
            allPosAtomsList.add(generateAtoms(a,false));
            allNegAtomsList.add(generateAtoms(a,true));
        }


        StringBuilder res = new StringBuilder();

        List<Atom> case1 = new ArrayList<>();
        case1.add(allNegAtomsList.get(0).get(0));
        case1.add(allNegAtomsList.get(1).get(1));
        case1.add(allNegAtomsList.get(2).get(2));
        case1.add(allNegAtomsList.get(3).get(3));

        //res.append(generateAndClause(case1));

        res.append(generateNegBCases(allNegAtomsList,allPosAtomsList));

        super.boolTrans = res.substring(0,res.lastIndexOf("&&"));
    }

    private String generateNegBCases(List<List<Atom>> negAtoms, List<List<Atom>> posAtoms) {
        StringBuilder temp = new StringBuilder();
        Atom negB = negAtoms.get(1).get(0);
        Atom negC = negAtoms.get(2).get(0);

        List<Atom> Alist = Arrays.asList(negAtoms.get(0).get(1),negAtoms.get(0).get(2),negAtoms.get(0).get(3),
                posAtoms.get(0).get(1),posAtoms.get(0).get(2),posAtoms.get(0).get(3));
        List<Atom> Blist = Arrays.asList(negAtoms.get(1).get(2),negAtoms.get(1).get(3),
                posAtoms.get(1).get(2),posAtoms.get(1).get(3));
        List<Atom> Clist = Arrays.asList(negAtoms.get(2).get(1),negAtoms.get(2).get(3),
               posAtoms.get(2).get(1),posAtoms.get(2).get(3));
        List<Atom> Dlist = Arrays.asList(negAtoms.get(3).get(0),negAtoms.get(3).get(1),negAtoms.get(3).get(2),
               posAtoms.get(3).get(0),posAtoms.get(3).get(1),posAtoms.get(3).get(2));

        List<List<Atom>> allCases = new ArrayList<>();


       /*List <Atom> case1 = new ArrayList<>();
       case1.addAll(Alist.subList(0,2));
       case1.add(Blist.get(0));
       case1.add(Clist.get(0));
       case1.addAll(Dlist.subList(0,2));
       case1.add(Dlist.get(5));
        allCases.add(case1);

        List <Atom> case2 = new ArrayList<>();
        case2.add(Alist.get(0));
        case2.add(Alist.get(2));
        case2.addAll(Blist.subList(0,2));
        case2.addAll(Clist.subList(1,3));
        case2.addAll(Dlist.subList(0,2));
        allCases.add(case2);


        List <Atom> case3 = new ArrayList<>();
        case3.add(Alist.get(0));
        case3.add(Alist.get(2));
        case3.add(Blist.get(1));
        case3.add(Clist.get(0));
        case3.add(Clist.get(3));
        case3.addAll(Dlist.subList(0,2));
        allCases.add(case3);


        List <Atom> case4 = new ArrayList<>();
        case4.addAll(Alist.subList(0,2));
        case4.add(Blist.get(0));
        case4.add(Clist.get(0));
        case4.add(Dlist.get(0));
        case4.add(Dlist.get(2));
        case4.add(Dlist.get(4));
        allCases.add(case4);

        List <Atom> case5 = new ArrayList<>();
        case5.addAll(Alist.subList(1,3));
        case5.add(Alist.get(2));
        case5.add(Blist.get(0));
        case5.add(Blist.get(3));
        case5.add(Clist.get(1));
        case5.add(Dlist.get(0));
        case5.add(Dlist.get(2));
        allCases.add(case5);

        List <Atom> case6 = new ArrayList<>();
        case6.addAll(Alist.subList(1,4));
        case6.addAll(Blist.subList(1,3));
        case6.add(Clist.get(0));
        case6.add(Clist.get(3));
        case6.addAll(Dlist.subList(1,4));
        allCases.add(case6);

        List <Atom> case7 = new ArrayList<>();
        case7.add(Blist.get(0));
        case7.add(Blist.get(3));
        case7.add(Clist.get(0));
        case7.add(Clist.get(3));
        case7.addAll(Dlist.subList(1,4));
        allCases.add(case7);

        List <Atom> case8 = new ArrayList<>();
        case8.addAll(Alist.subList(0,2));
        case8.add(Blist.get(0));
        case8.add(Clist.get(0));
        case8.addAll(Dlist.subList(1,4));
        allCases.add(case8);
        List <Atom> case9 = new ArrayList<>();
        case9.addAll(Alist.subList(3,5));
        case9.add(Blist.get(0));
        case9.add(Clist.get(0));
        case9.addAll(Dlist.subList(1,4));
        allCases.add(case9);

        List <Atom> case10 = new ArrayList<>();
        case10.add(Alist.get(0));
        case10.add(Alist.get(2));
        case10.add(Blist.get(1));
        case10.add(Clist.get(0));
        case10.add(Clist.get(3));
        case10.addAll(Dlist.subList(3,5));
        allCases.add(case10);
        List <Atom> case11 = new ArrayList<>();
        case11.addAll(Blist.subList(0,2));
        case11.add(Clist.get(1));
        case11.add(Dlist.get(3));
        case11.add(Dlist.get(5));
        allCases.add(case11);

        List <Atom> case12 = new ArrayList<>();
        case12.addAll(Alist.subList(1,3));
        case12.add(Blist.get(0));
        case12.add(Blist.get(3));
        case12.add(Clist.get(1));
        case12.add(Dlist.get(3));
        case12.add(Dlist.get(5));
        allCases.add(case12);*/

        List <Atom> case13 = new ArrayList<>();
        case13.add(Alist.get(5));
        case13.addAll(Blist.subList(0,2));
        case13.add(Clist.get(1));
        case13.add(Dlist.get(3));
        allCases.add(case13);

        List <Atom> case14 = new ArrayList<>();
        case14.add(Alist.get(0));
        case14.add(Alist.get(2));
        case14.addAll(Blist.subList(1,3));
        case14.addAll(Clist.subList(0,2));
        case14.add(Dlist.get(3));
        allCases.add(case14);
        for (int i = 0; i <allCases.size() ; i++) {
            temp.append(generateMultiOrClauses(negB, negC,allCases.get(i), allCases.get(i+1)));
            i++;
        }
        return temp.toString();
    }
/*         d_x  || ! c_w || ! b_w ||  a_w ||  a_y) &&
(      || d_z || ! c_w || ! b_w ||  b_z ||  c_y) &&
(      || ! c_w || ! b_w ||  b_z ||  a_y ||  c_y) &&
(      || ! c_w || ! b_w ||  a_w ||  a_y ||  c_y) &&
(      || ! c_w ||  b_w || ! b_z || ! a_w || ! a_z ||  a_y ||  c_y) &&
(      || ! b_w ||  b_z || ! a_w ||  a_z || ! a_y || ! c_y) &&
 */

    @Override
    public int noXOR() {
        return 8;
    }




    private String generateMultiOrClauses(Atom a, Atom b, List<Atom> list1, List<Atom> list2){
        StringBuilder temp = new StringBuilder();
        StringBuilder temp2 = new StringBuilder();
        String ab = a.stringRep + " || " + b.stringRep;
        temp.append("(" + ab + " || " );

        for (Atom aList1 : list1) {
            temp.append(aList1.stringRep + " || ");
        }
        addClause(a,b,list1);

        String res = temp.substring(0,temp.lastIndexOf("||"));
        res+=") && \n";


        temp2.append("(" + ab + " || " );
        for (Atom aList2 : list2) {
            temp2.append(aList2.stringRep + " || ");
        }
        addClause(a,b,list2);

        res+= temp2.substring(0,temp2.lastIndexOf("||")) + ") && \n";
        return res;

    }


    private String generate4OrClauses(Atom a, Atom b,Atom c,Atom d, List<List<Atom>> cases){
        StringBuilder temp2 = new StringBuilder();
        String ab = a.stringRep + " || " + b.stringRep + " || " + c.stringRep + " || " + d.stringRep;

        for (List<Atom> list:cases
                ) {
            String temp ="(" + ab + " || ";
            for (Atom at : list) {
                temp += at.stringRep + " || ";
            }
            addClause4Atoms(a,b,c,d,list);
            temp2.append(temp.substring(0,temp.lastIndexOf("||")) +") && \n");
        }
        return temp2.toString();

    }

    private  String generateOrClause(List<Atom> atoms){
        StringBuilder temp = new StringBuilder();
        temp.append("(");
        for (Atom a: atoms ) {
            temp.append(a.stringRep + " || ");
        }
        addClause(atoms.get(0),atoms.get(0),atoms);
        String res = temp.substring(0,temp.lastIndexOf("||")) + ") &&";
        return res + "\n";
    }

    private  String generateAndClause(List<Atom> atoms){
        StringBuilder temp = new StringBuilder();

        for (Atom a: atoms ) {
            temp.append(a.stringRep + " && ");
        }
        addClause(atoms.get(0),atoms.get(0),atoms);
        return temp.toString() + "&&\n";
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

    private void addClause(Atom a, Atom b, List<Atom> list){
        Set<Atom> atomSet = new HashSet<>();
        atomSet.add(a);
        atomSet.add(b);
        atomSet.addAll(list);
        clauses.add(new Clause(atomSet));
    }

    private void addClause4Atoms(Atom a, Atom b,Atom c, Atom d, List<Atom> list){
        Set<Atom> atomSet = new HashSet<>();
        atomSet.add(a);
        atomSet.add(b);
        atomSet.add(c);
        atomSet.add(d);
        atomSet.addAll(list);
        clauses.add(new Clause(atomSet));
    }


    /*
    ( ! b_x  || ! c_x  || ! d_x  || !d_y || d_z || ! b_z || ! a_z || ! a_y || ! c_y) &&
    ( ! b_x  || ! c_x  || ! d_x  || !d_y || ! c_w || ! b_w || ! b_z || ! a_w || ! a_y ||  c_y) &&
    ( ! b_x  || ! c_x  || ! d_x  || !d_y ||  c_w || ! b_w || ! a_w || ! a_y || ! c_y) &&
    ( ! b_x  || ! c_x  || ! d_x  || d_y || !d_z || ! b_z || ! a_z || ! a_y || ! c_y) &&
    ( ! b_x  || ! c_x  || ! d_x  || !d_z || ! c_w ||  b_w || ! b_z || ! a_w || ! a_z) &&
    ( ! b_x  || ! c_x  ||  d_x  || !d_y || !d_z ||  c_w || ! b_w ||  b_z || ! a_w || ! a_z ||  a_y || ! c_y) &&
    ( ! b_x  || ! c_x  ||  d_x  || !d_y || !d_z ||  c_w ||  b_w || ! b_z || ! c_y) &&
    ( ! b_x  || ! c_x  ||  d_x  || !d_y || !d_z || ! b_z || ! a_z || ! a_y || ! c_y) &&
    ( ! b_x  || ! c_x  ||  d_x  || !d_y || !d_z || ! b_z ||  a_z ||  a_y || ! c_y) &&
    ( ! b_x  || ! c_x  ||  d_x  || d_y ||  c_w || ! b_w || ! a_w || ! a_y || ! c_y) &&
    ( ! b_x  || ! c_x  ||  d_x  || d_z || ! c_w || ! b_w ||  b_z) &&
    ( ! b_x  || ! c_x  ||  d_x  || d_z || ! c_w ||  b_w || ! b_z || ! a_w || ! a_z) &&
    ( ! b_x  || ! c_x  ||  d_x  || ! c_w || ! b_w || ! b_z ||  a_w) &&
    ( ! b_x  || ! c_x  ||  d_x  || ! c_w || ! b_w ||  b_z || ! a_w || ! a_y || ! c_y) &&
    ( ! b_x  || ! c_x  ||  d_x  || ! c_w || ! b_w ||  a_w ||  a_y) &&
    ( ! b_x  || ! c_x  || d_z || ! c_w || ! b_w ||  b_z ||  c_y) &&
    ( ! b_x  || ! c_x  || ! c_w || ! b_w ||  b_z ||  a_y ||  c_y) &&
    ( ! b_x  || ! c_x  || ! c_w || ! b_w ||  a_w ||  a_y ||  c_y) &&
    ( ! b_x  || ! c_x  || ! c_w ||  b_w || ! b_z || ! a_w || ! a_z ||  a_y ||  c_y) &&
    ( ! b_x  || ! c_x  || ! b_w ||  b_z || ! a_w ||  a_z || ! a_y || ! c_y) &&

    ( ! b_x  ||  c_x  || ! d_x  || !d_y || d_z || ! c_w || ! b_w ||  b_z || ! c_y) &&
    ( ! b_x  ||  c_x  || ! d_x  || !d_y || d_z || ! c_w ||  b_w || ! b_z || ! a_w || ! a_z ||  a_y || ! c_y) &&
    ( ! b_x  ||  c_x  || ! d_x  || !d_y || d_z || ! b_z || ! a_z || ! a_y ||  c_y) &&
    ( ! b_x  ||  c_x  || ! d_x  || !d_y || ! c_w || ! b_w || ! b_z || ! a_w || ! a_y || ! c_y) &&
    ( ! b_x  ||  c_x  || ! d_x  || !d_y || ! c_w || ! b_w ||  a_w ||  a_y || ! c_y) &&
    ( ! b_x  ||  c_x  || ! d_x  || d_y || !d_z || ! b_w ||  b_z || ! a_w || ! a_z) &&
    ( ! b_x  ||  c_x  || ! d_x  || !d_z ||  c_w || ! b_w ||  b_z || ! a_w || ! a_z) &&
    ( ! b_x  ||  c_x  || ! d_x  || !d_z ||  c_w ||  b_w || ! b_z) &&
    ( ! b_x  ||  c_x  || ! d_x  || !d_z || ! b_z ||  a_z) &&
    ( ! b_x  ||  c_x  ||  d_x  || !d_y || !d_z || ! b_z || ! a_z || ! a_y ||  c_y) &&
    ( ! b_x  ||  c_x  ||  d_x  || d_y || ! c_w || ! b_z || ! a_w || ! a_y || ! c_y) &&
    ( ! b_x  ||  c_x  || ! c_w || ! b_z || ! a_w ||  a_z || ! a_y || ! c_y) &&

    ( ! b_x  || ! d_x  || !d_y || !d_z || ! c_w || ! b_w ||  a_w || ! a_z || ! a_y || ! c_y) &&
    ( ! b_x  || ! d_x  || !d_y || d_z ||  b_w || ! b_z ||  a_w || ! a_z || ! a_y) &&
    ( ! b_x  || ! d_x  || d_y || !d_z || ! b_w ||  b_z || ! a_w || ! a_z ||  a_y) &&
    ( ! b_x  || ! d_x  || d_y || !d_z ||  b_w || ! b_z) &&
    ( ! b_x  || ! d_x  || d_y || !d_z || ! b_z ||  a_z ||  a_y) &&

    ( ! b_x  ||  d_x  || !d_y || !d_z || ! b_z ||  a_w || ! a_z || ! a_y) &&
    ( ! b_x  || !d_y || !d_z || ! c_w || ! b_w || ! b_z || ! a_w ||  a_z || ! a_y || ! c_y) &&
    ( ! b_x  || d_y || ! c_w ||  b_w || ! b_z || ! a_w || ! a_y || ! c_y) &&

    ( b_x  || ! c_x  || ! d_x  || !d_y || !d_z ||  b_w || ! c_y) &&
    ( b_x  || ! c_x  || ! d_x  || !d_y || d_z || ! c_w ||  b_w || ! a_w || ! a_y ||  c_y) &&
    ( b_x  || ! c_x  || ! d_x  || !d_y || d_z ||  b_z || ! c_y) &&
    ( b_x  || ! c_x  || ! d_x  || !d_y || ! c_w || ! b_w || ! b_z ||  a_w || ! a_z || ! a_y ||  c_y) &&
    ( b_x  || ! c_x  || ! d_x  || !d_y ||  a_y || ! c_y) &&
    ( b_x  || ! c_x  || ! d_x  || d_y || !d_z || ! b_w || ! b_z || ! a_w ||  a_z || ! a_y || ! c_y) &&
    ( b_x  || ! c_x  || ! d_x  || d_y || !d_z ||  b_z || ! a_z || ! a_y || ! c_y) &&
    ( b_x  || ! c_x  ||  d_x  || !d_y || !d_z || ! c_w ||  b_w || ! a_w || ! a_y) &&
    ( b_x  || ! c_x  ||  d_x  || d_y || ! c_w || ! b_w || ! b_z) &&
    ( b_x  || ! c_x  ||  d_x  || ! c_w || ! b_w || ! b_z || ! a_w || ! a_z) &&

    ( b_x  ||  c_x  || ! d_x  || !d_z || ! b_w || ! b_z || ! a_w || ! a_z) &&
    ( b_x  ||  c_x  || ! d_x  || !d_z ||  c_y) &&
    ( b_x  ||  c_x  ||  d_x  || d_z) &&
    ( b_x  ||  c_x  ||  d_x  ||  c_w) &&
    ( b_x  ||  c_x  ||  d_x  ||  b_z) &&
    ( b_x  ||  c_x  ||  d_x  ||  a_y) &&
    ( b_x  ||  c_x  || d_z || ! c_w || ! b_w || ! b_z ||  a_w || ! a_z || ! a_y || ! c_y) &&
    ( b_x  ||  c_x  || d_z || ! c_w ||  b_w || ! a_w || ! a_y || ! c_y) &&
    ( b_x  ||  c_x  ||  b_z ||  a_z) &&
    ( b_x  ||  c_x  ||  b_z ||  c_y) &&
    ( b_x  ||  c_x  ||  a_y ||  c_y) &&

    ( b_x  || ! d_x  || !d_z ||  b_w ||  a_w) &&

    ( b_x  ||  d_x  || !d_y || !d_z ||  c_w || ! b_w || ! b_z || ! a_w ||  a_z || ! a_y) &&
    ( b_x  ||  d_x  || !d_y || !d_z ||  b_z || ! a_z || ! a_y) &&
    ( b_x  ||  d_x  || d_y ||  c_w ||  b_w) &&
    ( b_x  ||  d_x  || d_y ||  a_y) &&
    ( b_x  ||  d_x  || d_z ||  b_w ||  a_w) &&

    ( b_x  || !d_y || !d_z ||  b_z || ! a_z || ! a_y ||  c_y) &&

    ( b_x  || ! b_w || ! b_z || ! a_w || ! a_z ||  a_y) &&

    ( b_x  ||  b_w ||  b_z) &&
    ( b_x  ||  b_w ||  a_w ||  a_y) &&

    ( b_x  ||  b_z ||  a_z ||  a_y) &&

    ( ! c_x  || ! d_x  || !d_y || !d_z || ! c_w || ! a_w || ! a_z ||  a_y || ! c_y) &&
    ( ! c_x  || ! d_x  || !d_y || d_z || ! c_w || ! a_w ||  a_z || ! a_y ||  c_y) &&
    ( ! c_x  || ! d_x  || !d_y || d_z ||  c_w || ! c_y) &&
    ( ! c_x  || ! d_x  || !d_y || d_z ||  a_z ||  a_y || ! c_y) &&
    ( ! c_x  || ! d_x  || !d_y ||  c_w ||  a_w ||  a_y || ! c_y) &&
    ( ! c_x  || ! d_x  || d_y || !d_z ||  a_w || ! a_z || ! a_y || ! c_y) &&
    ( ! c_x  ||  d_x  || !d_y || !d_z || ! c_w ||  b_w || ! a_w ||  a_z || ! a_y ||  c_y) &&
    ( ! c_x  ||  d_x  || d_y || ! c_w || ! b_w || ! b_z ||  c_y) &&
    ( ! c_x  ||  d_x  || ! c_w || ! b_w || ! b_z ||  a_w ||  a_z) &&
    ( c_x  || ! d_x  || d_y || !d_z ||  c_y) &&
    ( c_x  || ! d_x  || !d_z ||  a_w ||  a_z) &&

    ( c_x  ||  d_x  || !d_y || !d_z || ! c_w || ! a_w || ! a_z ||  a_y || ! c_y) &&
    ( c_x  ||  d_x  || d_y ||  b_z ||  a_w) &&
    ( c_x  ||  d_x  || d_z ||  c_w) &&
    ( c_x  ||  d_x  || d_z ||  a_z) &&
    ( c_x  ||  d_x  ||  c_w ||  a_w) &&

    ( c_x  || d_y ||  b_z ||  c_y) &&

    ( c_x  || d_z || ! c_w || ! a_w ||  a_z || ! a_y || ! c_y) &&

    ( c_x  ||  c_w ||  c_y) &&

    ( c_x  ||  b_z ||  a_w ||  a_z) &&
    ( c_x  ||  b_z ||  a_z ||  c_y) &&

    ( c_x  ||  a_z ||  a_y ||  c_y) && (

     ! d_x  || !d_y || !d_z || ! b_w || ! b_z || ! a_w ||  a_z || ! a_y ||  c_y) &&
     ( ! d_x  || d_y || !d_z ||  b_w ||  c_y) &&
     ( ! d_x  || !d_z ||  b_w ||  a_w ||  a_z) &&

    ( d_x  || !d_y || !d_z || ! c_w ||  b_z || ! a_w || ! a_z || ! a_y || ! c_y) &&
    ( d_x  || !d_y || !d_z ||  c_w ||  a_w || ! a_z || ! a_y) && ( d_x  || d_y || d_z) &&
    ( d_x  || d_y ||  a_w ||  a_y) && ( d_x  || d_z ||  a_w ||  a_z) &&

    ( !d_y || !d_z || ! c_w || ! b_w || ! b_z || ! a_w || ! a_z ||  a_y || ! c_y) &&
    ( !d_y || !d_z || ! c_w ||  b_w ||  b_z || ! a_w || ! a_z || ! c_y) &&
    ( !d_y || !d_z ||  c_w || ! b_w || ! b_z || ! a_w ||  a_z || ! a_y ||  c_y) &&
    ( !d_y || !d_z ||  c_w ||  b_z || ! a_z || ! a_y ||  c_y) &&
    ( !d_y || !d_z ||  c_w ||  a_w || ! a_z || ! a_y ||  c_y) &&

    (d_y || d_z || ! c_w || ! b_w || ! b_z ||  a_w || ! a_z || ! a_y || ! c_y) &&
    (d_y || d_z || ! c_w ||  b_w || ! a_w || ! a_y || ! c_y) &&
    (d_y || d_z || ! c_w || ! a_w ||  a_z || ! a_y || ! c_y) &&
    (d_y || d_z ||  c_w ||  c_y) &&
    (d_y || d_z || ! b_w || ! b_z || ! a_w || ! a_z ||  a_y) &&
    (d_y || d_z ||  b_w ||  b_z) &&

    (d_y || d_z ||  b_z ||  c_y) &&
    (d_y ||  a_y ||  c_y) &&

    (d_y ||  c_w ||  b_w ||  c_y) &&
    (d_y ||  b_w ||  b_z ||  a_w) &&
    (d_y ||  b_w ||  b_z ||  c_y) &&
    (d_y ||  b_w ||  a_w ||  a_y) &&



     (d_z ||  c_w || ! b_w || ! b_z || ! a_w || ! a_z) &&
     (d_z ||  c_w ||  b_w ||  b_z) &&
     (d_z ||  c_w ||  a_w ||  a_z) &&
     (d_z ||  b_z ||  a_z)

     ( c_w || ! b_w || ! b_z || ! a_w || ! a_z ||  a_y ||  c_y) &&
     ( c_w ||  b_w ||  b_z ||  c_y) &&
     ( c_w ||  b_w ||  a_w)  &&

    ( b_w ||  b_z ||  a_w ||  a_z) &&
    ( b_w ||  b_z ||  a_z ||  c_y) &&

    ( b_z ||  a_z ||  a_y ||  c_y) &&
    ( a_w ||  a_z ||  a_y)

     */
}
