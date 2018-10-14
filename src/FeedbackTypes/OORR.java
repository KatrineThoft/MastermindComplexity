package FeedbackTypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class OORR extends Feedback{
    String boolTrans;

    public OORR(String guess) {
        super("OORR", guess);
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

    private void translate() {
        String[] atomString= guess.split(",");
        List<Atom> posAtoms = new ArrayList<>();
        //Getting the original atom
        for (String s: atomString) {
            Atom a = new Atom(s);
            posAtoms.add(a);
        }

        List<List<Atom>> allPosAtomsList = new ArrayList<List<Atom>>();
        List<List<Atom>> allNegAtomsList = new ArrayList<List<Atom>>();
        for (Atom a:posAtoms) {
            allPosAtomsList.add(generateAtoms(a,false));
            allNegAtomsList.add(generateAtoms(a,true));
        }


        StringBuilder res = new StringBuilder();

        res.append(generateNegAClauses(allNegAtomsList,allPosAtomsList));
       /* res.append(generatePosAClauses(allNegAtomsList,allPosAtomsList));
        res.append(generateNegBClauses(allNegAtomsList,allPosAtomsList));
        res.append(generatePosBClauses(allNegAtomsList,allPosAtomsList));
        res.append(generateNegCClauses(allNegAtomsList,allPosAtomsList));
        res.append(generatePosCClauses(allNegAtomsList,allPosAtomsList));
        res.append(generateNegDxClauses(allNegAtomsList,allPosAtomsList));
        res.append(generatePosDxClauses(allNegAtomsList,allPosAtomsList));
        res.append(generateNegDyClauses(allNegAtomsList,allPosAtomsList));
        res.append(generatePosDyClauses(allNegAtomsList,allPosAtomsList));*/



        boolTrans = res.substring(0,res.lastIndexOf("&&"));

    }

    private String generateNegAClauses(List<List<Atom>> negAtoms, List<List<Atom>> posAtoms) {
        StringBuilder temp = new StringBuilder();
        Atom negA = negAtoms.get(0).get(0);
        Atom negC = negAtoms.get(2).get(0);
        Atom posC = posAtoms.get(2).get(0);
        Atom negBx = negAtoms.get(1).get(0);
        Atom negBz = negAtoms.get(1).get(2);
        Atom negBw = negAtoms.get(1).get(3);

        List<Atom> Alist = Arrays.asList(negAtoms.get(0).get(1),negAtoms.get(0).get(2),negAtoms.get(0).get(3),
                posAtoms.get(0).get(1),posAtoms.get(0).get(2),posAtoms.get(0).get(3));
        List<Atom> Clist = Arrays.asList(negAtoms.get(2).get(1),negAtoms.get(2).get(3),
                posAtoms.get(2).get(1),posAtoms.get(2).get(3));
        List<Atom> Dlist = Arrays.asList(negAtoms.get(3).get(0),negAtoms.get(3).get(1),  negAtoms.get(3).get(2));

        List<List> allCases = new ArrayList<>();


        //cases w. neg Ax and neg Bx
        List<Atom> case1 = new ArrayList<>();
        case1.add(Dlist.get(1));

        List<Atom> case2 = new ArrayList<>();
        case2.add(Dlist.get(2));

        //Cases w. neg Ax and neg Cx 
        List<Atom> case3 = new ArrayList<>();
        case3.addAll(Alist.subList(1,4));
        case3.add(Clist.get(3));
        allCases.add(case3);

        List<Atom> case4 = new ArrayList<>();
        case4.addAll(Alist.subList(0,2));
        case4.add(Alist.get(5));
        case4.add(Clist.get(2));
        allCases.add(case4);


       List<Atom> case5 = new ArrayList<>();
        case5.add(Alist.get(4));
        case5.addAll(Clist.subList(0,2));
        allCases.add(case5);

        List<Atom> case6 = new ArrayList<>();
        case6.add(Alist.get(0));
        case6.add(Alist.get(2));
        case6.add(Alist.get(4));
        case6.addAll(Clist.subList(2,4));
        allCases.add(case6);


        List<Atom> case7 = new ArrayList<>();
        case7.add(Alist.get(5));
        case7.addAll(Clist.subList(1,3));
        allCases.add(case7);

        List<Atom> case8 = new ArrayList<>();
        case8.add(Alist.get(3));
        case8.add(Clist.get(0));
        case8.add(Clist.get(3));
        allCases.add(case8);

        //Cases w. negAx and Cx
       List<Atom> case9 = new ArrayList<>();
        case9.addAll(Alist.subList(0,2));
        case9.addAll(Clist.subList(1,3));
        allCases.add(case9);

        List<Atom> case10 = new ArrayList<>();
        case10.addAll(Alist.subList(1,3));
        case10.add(Clist.get(0));
        case10.add(Clist.get(3));
        allCases.add(case10);

        List<Atom> case11 = new ArrayList<>();
        case11.addAll(Alist.subList(3,5));
        case11.addAll(Clist.subList(1,3));
        allCases.add(case11);

        List<Atom> case12 = new ArrayList<>();
        case12.addAll(Alist.subList(4,6));
        case12.add(Clist.get(0));
        case12.add(Clist.get(3));
        allCases.add(case12);

        List<Atom> case13 = new ArrayList<>();
        case13.add(Alist.get(0));
        case13.add(Alist.get(2));
        case13.addAll(Clist.subList(0,2));
        allCases.add(case13);

        List<Atom> case14 = new ArrayList<>();
        case14.add(Alist.get(3));
        case14.add(Alist.get(5));
        case14.addAll(Clist.subList(0,2));
        allCases.add(case14);

        //cases w. neg Ax and neg Bz

        List<Atom> case15 = new ArrayList<>();
        case15.add(Dlist.get(1));


        List<Atom> case16 = new ArrayList<>();
        case16.add(Dlist.get(0));


        //Cases w. neg Ax and neg Bw

        List<Atom> case17 = new ArrayList<>();
        case17.add(Dlist.get(0));

        List<Atom> case18 = new ArrayList<>();
        case18.add(Dlist.get(2));


        List<Atom> case19 = new ArrayList<>();
        case19.add(negA);
        case19.add(negBw);
        case19.add(Dlist.get(1));

        //Case w. all pos A's except Ax
        List<Atom> case20 = new ArrayList<>();
        case20.add(negA);
        case20.addAll(Alist.subList(3,6));



       temp.append(generateMultiOrClauses(negA, negBx, case1, case2));

        for (int i = 0; i <allCases.size()/2; i++) {
            temp.append(generateMultiOrClauses(negA, negC, allCases.get(i), allCases.get(i + 1)));
            i++;
        }

        for (int i = allCases.size()/2; i <allCases.size()-1 ; i++) {
            temp.append(generateMultiOrClauses(negA, posC, allCases.get(i), allCases.get(i + 1)));
            i++;
        }

        temp.append(generateMultiOrClauses(negA, negBz, case15, case16));
        temp.append(generateMultiOrClauses(negA, negBw, case17, case18));
        temp.append(generateOrClause(case19));
        temp.append(generateOrClause(case20));


        return temp.toString();
    }

    private String generateSingleOrClauses(Atom a, Atom b){
        return "("+ a.stringRep + " || " + b.stringRep + ") && \n";
    }



    private String generateMultiOrClauses(Atom a, Atom b, List<Atom> list1, List<Atom> list2){
        StringBuilder temp = new StringBuilder();
        StringBuilder temp2 = new StringBuilder();
        String ab = a.stringRep + " || " + b.stringRep;
        temp.append("(" + ab + " || " );

        for (Atom aList1 : list1) {
            temp.append(aList1.stringRep + " || ");
        }

        String res = temp.substring(0,temp.lastIndexOf("||"));
        res+=") && \n";


        temp2.append("(" + ab + " || " );
        for (Atom aList2 : list2) {
            temp2.append(aList2.stringRep + " || ");
        }

        res+= temp2.substring(0,temp2.lastIndexOf("||")) + ") && \n";
        return res;

    }

    private  String generateOrClause(List<Atom> atoms){
        StringBuilder temp = new StringBuilder();
        temp.append("(");
        for (Atom a: atoms ) {
            temp.append(a.stringRep + " || ");
        }

        String res = temp.substring(0,temp.lastIndexOf("||")) + ") &&";
        return res + "\n";
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


    /* OBS: c_y = x
    ( !a_x || !b_x || !d_y) &&
    ( !a_x || !b_x || !d_z) &&

    ( !a_x || !c_x || !a_z || c_w || a_y || !a_w) &&
    ( !a_x || !c_x || !a_z || !a_y || a_w || c_y) &&
    ( !a_x || !c_x || a_z || !c_w || !x) &&
    ( !a_x || !c_x || a_z || c_w || !a_y || !a_w || c_y) &&
    ( !a_x || !c_x || !c_w || a_w || c_y) &&
    ( !a_x || !c_x || c_w || a_y || !c_y) &&

    ( !a_x || c_x || !a_z || !c_w || !a_y || c_y) &&
    ( !a_x || c_x || !a_z || c_w || !a_w || !c_y) &&
     ( !a_x || c_x || a_z || !c_w || a_y || c_y) &&
     ( !a_x || c_x || a_z || c_w || a_w || !c_y) &&
     ( !a_x || c_x || !c_w || !a_y || !a_w || !c_y) &&
     ( !a_x || c_x || !c_w || a_y || a_w || !c_y) &&

    ( !a_x || !d_x || !b_w) &&
    ( !a_x || !d_x || !b_z) &&

    ( !a_x || !d_y || !b_w) &&
     ( !a_x || !d_y || !b_z) &&

     ( !a_x || !d_z || !b_w) &&

     ( !a_x || a_z || a_y || a_w) &&

      (a_x || !b_x || !c_x || d_w || b_w || !c_w || b_y || !a_w || c_y || c_z) &&
      (a_x || !b_x || !c_x || d_w || c_w || !a_y || b_y || !c_y || c_z) &&
      (a_x || !b_x || c_x || d_w || !b_w || !b_z || !a_z || c_w || b_y || a_w || !c_y || c_z) &&
      (a_x || !b_x || c_x || d_w || !b_w || !b_z || a_z || c_w || b_y || !a_w || !c_y || c_z) &&
      (a_x || !b_x || c_x || d_w || b_w || b_z || !a_z || c_w || b_y || a_w || !c_y || c_z) &&
    (a_x || !b_x || c_x || d_w || b_w || b_z || a_z || c_w || b_y || !a_w || !c_y || c_z) &&
    (a_x || !b_x || c_x || d_w || b_z || !a_z || !c_w || a_y || b_y || c_y || c_z) &&
    (a_x || !b_x || c_x || d_w || b_z || a_z || !c_w || !a_y || b_y || c_y || c_z) &&

    (a_x || b_x || !c_x || d_w || !b_w || !c_w || b_y || !a_w || c_y || c_z) &&
    (a_x || b_x || c_x || d_w || !b_w || b_z || !a_z || c_w || b_y || a_w || !c_y || c_z) &&
    (a_x || b_x || c_x || d_w || !b_w || b_z || a_z || c_w || b_y || !a_w || !c_y || c_z) &&
    (a_x || b_x || c_x || d_w || b_w || !b_z || !a_z || c_w || b_y || a_w || !c_y || c_z) &&
    (a_x || b_x || c_x || d_w || b_w || !b_z || a_z || c_w || b_y || !a_w || !c_y || c_z) &&
    (a_x || b_x || c_x || d_w || !b_z || !a_z || !c_w || a_y || b_y || c_y || c_z) &&
    (a_x || b_x || c_x || d_w || !b_z || a_z || !c_w || !a_y || b_y || c_y || c_z) &&

    (a_x || !c_x || d_w || !b_w || b_z || !a_z || c_w || !a_y || b_y || !a_w || c_y || c_z) &&
    (a_x || !c_x || d_w || !b_w || b_z || !a_z || c_w || a_y || b_y || a_w || c_y || c_z) &&
    (a_x || !c_x || d_w || !b_w || b_z || a_z || c_w || !a_y || b_y || a_w || c_y || c_z) &&
    (a_x || !c_x || d_w || !b_w || b_z || a_z || c_w || a_y || b_y || !a_w || c_y || c_z) &&
    (a_x || !c_x || d_w || b_w || !b_z || !a_z || c_w || !a_y || b_y || !a_w || c_y || c_z) &&
    (a_x || !c_x || d_w || b_w || !b_z || !a_z || c_w || a_y || b_y || a_w || c_y || c_z) &&
    (a_x || !c_x || d_w || b_w || !b_z || a_z || c_w || !a_y || b_y || a_w || c_y || c_z) &&
    (a_x || !c_x || d_w || b_w || !b_z || a_z || c_w || a_y || b_y || !a_w || c_y || c_z) && (a_x || !c_x || d_w || !b_z || !a_z || !c_w || b_y || !c_y || c_z) && (a_x || c_x || d_w || !b_w || !c_w || !a_y || b_y || a_w || !c_y || c_z) && (a_x || c_x || d_w || !b_w || !c_w || a_y || b_y || !a_w || !c_y || c_z) &&

    ( !b_x || !c_x || !b_w || !c_w || a_w || c_y) &&
    ( !b_x || !c_x || b_w || b_z || a_z || c_w || a_y || a_w || c_y) &&

    ( !b_x || c_x || d_x || d_y || d_z || !b_z || a_z || a_y || x) &&
    ( !b_x || c_x || d_x || d_y || !b_w || c_w || a_y || a_w || c_y) &&
    ( !b_x || c_x || d_x || d_z || b_w || !b_z || a_z || c_w || a_w) &&
    ( !b_x || c_x || d_x || d_z || b_w || !b_z || a_z || c_w || c_y) &&
    ( !b_x || c_x || d_x || !b_w || b_z || a_z || c_w || a_w) &&
    ( !b_x || c_x || d_x || !b_w || b_z || c_w || a_w || c_y) &&
    ( !b_x || c_x || !b_w || b_z || !a_z || c_w || !a_w || !c_y) &&
    ( !b_x || c_x || b_w || !b_z || !a_z || c_w || !a_w || !c_y) &&
    ( !b_x || c_x || !b_z || !a_z || !c_w || !a_y || c_y) &&

    ( !b_x || !d_x || d_y || !d_z || !b_z) &&
    ( !b_x || !d_x || d_y || d_z || b_w || b_z) &&
    ( !b_x || !d_x || !d_z || b_w || !b_z) &&
    ( !b_x || d_x || !d_y || d_z || b_w || !b_z) &&
    ( !b_x || d_x || !d_y || !b_w || b_z) &&
    ( !b_x || d_x || d_y || !d_z || !b_w) &&

    ( !b_x || !d_y || !d_z || b_w || b_z) &&
    ( !b_x || !d_y || !d_w) &&
    ( !b_x || !d_y || !c_w) &&
    ( !b_x || !d_y || !b_y) &&
     ( !b_x || !d_y || !c_y) &&
     ( !b_x || !d_y || !c_z) &&

    ( !b_x || !d_z || !d_w) &&
    ( !b_x || !d_z || !c_w) &&
    ( !b_x || !d_z || !b_y) &&
    ( !b_x || !d_z || !c_y) &&
    ( !b_x || !d_z || !c_z) &&

     (b_x || !c_x || d_x || d_y || c_w || a_y || !c_y) &&
     (b_x || !c_x || d_x || b_w || !c_w || a_w || c_y) &&

     (b_x || c_x || !d_x || !d_y || d_z || a_y || c_y) &&
     (b_x || c_x || !d_x || d_y || !d_z || b_z || a_z) &&
     (b_x || c_x || d_x || d_y || d_z || b_z || !a_z || !a_y || c_y) &&
     (b_x || c_x || d_x || d_y || d_z || b_z || a_z || a_y || c_y) &&
     (b_x || c_x || d_x || d_y || b_w || c_w || !a_y || !a_w || c_y) &&
     (b_x || c_x || d_x || d_y || b_w || c_w || a_y || a_w || c_y) &&
     (b_x || c_x || d_x || d_z || b_w || b_z || !a_z || c_w || !a_w) &&
     (b_x || c_x || d_x || d_z || b_w || b_z || a_z || c_w || a_w) &&
    (b_x || c_x || d_z || !b_w || !b_z || !a_z || c_w || !a_w) &&
     (b_x || c_x || d_z || !b_w || !b_z || a_z || c_w || a_w) &&
     (b_x || c_x || b_w || b_z || a_z || c_w || !a_y || a_w || !c_y) &&

     (b_x || !d_x || !d_y || d_z || !b_w) &&
     (b_x || !d_x || !d_y || b_w || !b_z) &&
     (b_x || !d_x || d_y || !d_z || !b_w || b_z) &&

     (b_x || d_x || !d_y || d_z || b_w || b_z || a_z || !a_y || a_w) &&
     (b_x || d_x || d_y || !d_z || b_w || !b_z) &&

     (b_x || !d_y || d_z || !b_w || !b_z) &&

     ( !c_x || !d_x || !d_y || c_w || !c_y) &&
     ( !c_x || !d_x || d_y || d_z || c_w || c_y) &&

     ( !c_x || !d_y || !b_w) &&
    ( !c_x || !d_y || !b_z) &&
    ( !c_x || !d_y || !a_z) &&
    ( !c_x || !d_y || !a_y) &&
    ( !c_x || !d_y || !a_w) &&

    ( !c_x || !d_z || !b_w) &&
    ( !c_x || !d_z || !b_z) &&
    ( !c_x || !d_z || !a_z) &&
    ( !c_x || !d_z || !a_y) &&
    ( !c_x || !d_z || !a_w) &&

    ( !c_x || d_z || b_z || a_z || !c_w || !c_y) &&

     ( !c_x || !d_w || !a_z || c_w || a_y || !a_w) &&
     ( !c_x || !d_w || !a_z || !a_y || a_w || c_y) &&
     ( !c_x || !d_w || a_z || !c_w || !c_y) &&
    ( !c_x || !d_w || a_z || c_w || !a_y || !a_w || c_y) &&
    ( !c_x || !d_w || !c_w || a_w || c_y) &&
    ( !c_x || !d_w || c_w || a_y || !x) &&

    ( !c_x || !b_w || !b_z || !a_z || c_w || !a_y || a_w || c_y) &&
    ( !c_x || !b_w || !b_z || a_z || c_w || !a_y || !a_w || c_y) &&

    ( !c_x || !a_z || c_w || a_y || !b_y || !a_w) &&
    ( !c_x || !a_z || c_w || a_y || !a_w || !c_z) &&
     ( !c_x || !a_z || !a_y || !b_y || a_w || c_y) &&
     ( !c_x || !a_z || !a_y || a_w || c_y || !c_z) &&

     ( !c_x || a_z || !c_w || !b_y || !c_y) &&
     ( !c_x || a_z || !c_w || !c_y || !c_z) &&
     ( !c_x || a_z || c_w || !a_y || !b_y || !a_w || c_y) &&
     ( !c_x || a_z || c_w || !a_y || !a_w || c_y || !c_z) &&

     ( !c_x || !c_w || !b_y || a_w || c_y) &&
     ( !c_x || !c_w || a_w || c_y || !c_z) &&

     ( !c_x || c_w || a_y || !b_y || !c_y) &&
     ( !c_x || c_w || a_y || !c_y || !c_z) &&

   (c_x || d_x || !d_y || d_z || c_w || !c_y) &&
    (c_x || d_y || b_w || !c_w || a_y || a_w || !c_y) &&
    (c_x || b_w || !c_w || !a_y || !a_w || !c_y) &&

    (c_x || !d_w || !a_z || !c_w || !a_y || c_y) &&
    (c_x || !d_w || !a_z || c_w || !a_w || !c_y) &&
    (c_x || !d_w || a_z || !c_w || a_y || c_y) &&
    (c_x || !d_w || a_z || c_w || a_w || !c_y) &&
    (c_x || !d_w || !c_w || !a_y || !a_w || !c_y) &&
    (c_x || !d_w || !c_w || a_y || a_w || !c_y) &&

    (c_x || !a_z || !c_w || !a_y || !b_y || c_y) &&
     (c_x || !a_z || !c_w || !a_y || c_y || !c_z) &&
     (c_x || !a_z || c_w || !b_y || !a_w || !c_y) &&
     (c_x || !a_z || c_w || !a_w || !c_y || !c_z) &&

     (c_x || a_z || !c_w || a_y || !b_y || c_y) &&
     (c_x || a_z || !c_w || a_y || c_y || !c_z) &&
     (c_x || a_z || c_w || !b_y || a_w || !c_y) &&
     (c_x || a_z || c_w || a_w || !c_y || !c_z) &&
     (c_x || !c_w || !a_y || !b_y || !a_w || !x) &&
     (c_x || !c_w || !a_y || !a_w || !c_y || !c_z) &&
     (c_x || !c_w || a_y || !b_y || a_w || !c_y) &&
     (c_x || !c_w || a_y || a_w || !c_y || !c_z) &&

     ( !d_x || !d_y || !d_z || b_w || c_w || a_w) &&
     ( !d_x || d_y || d_z || !b_w || !b_z) &&

     ( !d_x || !d_w || !b_w) &&
     ( !d_x || !d_w || !b_z) &&

     ( !d_x || !b_w || !c_w) &&
     ( !d_x || !b_w || !b_y) &&
     ( !d_x || !b_w || !c_y) &&
     ( !d_x || !b_w || !c_z) &&

     ( !d_x || !b_z || !c_w) &&
     ( !d_x || !b_z || !b_y) &&
     ( !d_x || !b_z || !c_y) &&
     ( !d_x || !b_z || !c_z) &&

     ( !d_x || !a_z || !c_w) &&
     ( !d_x || !a_z || !c_y) &&

     ( !d_x || !c_w || !a_y) &&
     ( !d_x || !c_w || !a_w) &&
     ( !d_x || !a_y || !x) &&
     ( !d_x || !a_w || !cy) &&
     (d_x || !d_y || !d_z || !b_w || b_z) &&
     (d_x || !d_y || !d_z || b_z || !a_z || !a_y) &&
     (d_x || !d_y || !d_z || b_z || a_z || a_y || x) &&

     ( !d_y || !d_w || !b_w) &&
     ( !d_y || !d_w || !b_z) &&

    ( !d_y || !b_w || !c_w) &&
    ( !d_y || !b_w || !b_y) &&
    ( !d_y || !b_w || !c_z) &&

    ( !d_y || !b_z || !c_w) &&
    ( !d_y || !b_z || !b_y) &&
     ( !d_y || !b_z || !c_y) &&
    ( !d_y || !b_z || !c_z) &&

    ( !d_y || !a_z || !c_w) &&
    ( !d_y || !c_w || !a_w) &&

    (d_y || d_z || !b_w || !b_z || !a_z || c_w || a_y || !a_w || x) &&
    (d_y || d_z || !b_w || !b_z || a_z || c_w || a_y || a_w || x) &&
    (d_y || d_z || b_w || b_z || !a_z || c_w || !a_y || a_w || x) &&
    (d_y || d_z || b_w || b_z || !a_z || c_w || a_y || !a_w || x) &&
    (d_y || d_z || b_w || b_z || a_z || c_w || !a_y || !a_w || c_y) &&
    (d_y || d_z || b_w || b_z || a_z || c_w || a_y || a_w || c_y) &&

    ( !d_z || !d_w || !b_w) &&
    ( !d_z || !b_w || !c_w) &&
    ( !d_z || !b_w || !b_y) &&
    ( !d_z || !b_w || !c_y) &&
    ( !d_z || !b_w || !c_z) &&

    ( !d_z || !a_z || !c_w) &&
    ( !d_z || !a_z || !c_y) && (

     !d_z || !c_w || !a_y) &&
     ( !d_z || !c_w || !a_w) &&

     ( !d_z || !a_w || !c_y) &&

     ( !d_w || a_z || a_y || a_w) &&

     (a_z || a_y || !b_y || a_w) &&
    (a_z || a_y || a_w || !c_z)
     */

}
