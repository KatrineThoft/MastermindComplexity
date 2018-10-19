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

        /*res.append(generateNegAClauses(allNegAtomsList,allPosAtomsList));
        res.append(generatePosAClauses(allNegAtomsList,allPosAtomsList));
        res.append(generateNegBClauses(allNegAtomsList,allPosAtomsList));
        res.append(generatePosBClauses(allNegAtomsList,allPosAtomsList));
        res.append(generateNegCClauses(allNegAtomsList,allPosAtomsList));*/
       res.append(generatePosCClauses(allNegAtomsList,allPosAtomsList));
       /* res.append(generateNegDxClauses(allNegAtomsList,allPosAtomsList));
        res.append(generatePosDxClauses(allNegAtomsList,allPosAtomsList));
        res.append(generateNegDyClauses(allNegAtomsList,allPosAtomsList));
        res.append(generatePosDyClauses(allNegAtomsList,allPosAtomsList));*/

        boolTrans = res.substring(0,res.lastIndexOf("&&"));

    }

    private String generatePosCClauses(List<List<Atom>> negAtoms, List<List<Atom>> posAtoms) {
        return null;
    }

    private String generateNegCClauses(List<List<Atom>> negAtoms, List<List<Atom>> posAtoms) {
        StringBuilder temp = new StringBuilder();
        Atom negC = negAtoms.get(2).get(0);
        Atom negAz = negAtoms.get(0).get(2);
        Atom posAz = posAtoms.get(0).get(2);
        Atom negBw = negAtoms.get(1).get(3);
        Atom negCw = negAtoms.get(2).get(3);
        Atom posCw = posAtoms.get(2).get(3);
        Atom negDx = negAtoms.get(3).get(0);
        Atom negDy = negAtoms.get(3).get(1);
        Atom negDz = negAtoms.get(3).get(2);
        Atom negDw = negAtoms.get(3).get(3);
        Atom posDz = posAtoms.get(3).get(2);
        Atom posDy =posAtoms.get(3).get(1);

        List<Atom> Alist = Arrays.asList(negAtoms.get(0).get(1), negAtoms.get(0).get(3),
                posAtoms.get(0).get(1),posAtoms.get(0).get(3));
        List<Atom> Blist = Arrays.asList(negAtoms.get(1).get(1), negAtoms.get(1).get(2),
                posAtoms.get(1).get(2));
        List<Atom> Clist = Arrays.asList(negAtoms.get(2).get(1), negAtoms.get(2).get(2),
                posAtoms.get(2).get(1));

        List<List> allCases = new ArrayList<>();


//Cases w. neg Cx and neg Dx
        List<Atom> case1= new ArrayList<>();
        case1.add(Clist.get(0));
        case1.add(posCw);
        case1.add(negDy);

        List<Atom> case2= new ArrayList<>();
        case2.add(Clist.get(2));
        case2.add(posCw);
        case2.add(posDy);
        case2.add(posDz);

    //Cases w. neg Cx and neg Dy
        List<Atom> case3= new ArrayList<>();
        case3.add(Blist.get(1));
        allCases.add(case3);

        List<Atom> case4= new ArrayList<>();
        case4.add(negAz);
        allCases.add(case4);

        List<Atom> case5= new ArrayList<>();
        case5.add(Alist.get(0));
        allCases.add(case5);

        List<Atom> case6= new ArrayList<>();
        case6.add(Alist.get(1));
        allCases.add(case6);

        //Cases w. neg Cx and neg Bw
        List<Atom> case7= new ArrayList<>();
        case7.add(negDy);
        allCases.add(case7);

        List<Atom> case8= new ArrayList<>();
        case8.add(negDz);
        allCases.add(case8);

        List<Atom> case9= new ArrayList<>();
        case9.add(Alist.get(0));
        case9.add(negAz);
        case9.add(Alist.get(3));
        case9.add(Blist.get(1));
        case9.add(Clist.get(2));
        case9.add(posCw);
        allCases.add(case9);

        List<Atom> case10= new ArrayList<>();
        case10.addAll(Alist.subList(0,2));
        case10.add(posAz);
        case10.add(Blist.get(1));
        case10.add(Clist.get(2));
        case10.add(posCw);
        allCases.add(case10);

        //Cases w. neg Cx and neg Dz
        List<Atom> case11= new ArrayList<>();
        case11.add(Blist.get(1));
        allCases.add(case11);

        List<Atom> case12= new ArrayList<>();
        case12.add(negAz);
        allCases.add(case12);

        List<Atom> case13= new ArrayList<>();
        case13.add(Alist.get(0));
        allCases.add(case13);

        List<Atom> case14= new ArrayList<>();
        case14.add(Alist.get(1));
        allCases.add(case14);

        //Cases w. neg Cx and neg Dw
        List<Atom> case15= new ArrayList<>();
        case15.add(negAz);
        case15.addAll(Alist.subList(1,3));
        case15.add(posCw);
        allCases.add(case15);

        List<Atom> case16= new ArrayList<>();
        case16.add(Alist.get(0));
        case16.add(negAz);
        case16.add(Alist.get(3));
        case16.add(Clist.get(2));
        allCases.add(case16);

        List<Atom> case17= new ArrayList<>();
        case17.add(posAz);
        case17.add(Clist.get(0));
        case17.add(negCw);
        allCases.add(case17);

        List<Atom> case18= new ArrayList<>();
        case18.addAll(Alist.subList(0,2));
        case18.add(posAz);
        case18.add(Clist.get(2));
        case18.add(posCw);
        allCases.add(case18);

        List<Atom> case19= new ArrayList<>();
        case19.add(Alist.get(3));
        case19.add(negCw);
        case19.add(Clist.get(2));
        allCases.add(case19);

        List<Atom> case20= new ArrayList<>();
        case20.add(Alist.get(2));
        case20.add(Clist.get(0));
        case20.add(posCw);
        allCases.add(case20);

        //Cases w. neg Cx and neg Az
        List<Atom> case21= new ArrayList<>();
        case21.addAll(Alist.subList(1,3));
        case21.add(Blist.get(0));
        case21.add(posCw);
        allCases.add(case21);

        List<Atom> case22= new ArrayList<>();
        case22.addAll(Alist.subList(1,3));
        case22.add(posCw);
        case22.add(Clist.get(1));
        allCases.add(case22);

        List<Atom> case23= new ArrayList<>();
        case23.add(Alist.get(0));
        case23.add(Alist.get(3));
        case23.add(Blist.get(0));
        case23.add(Clist.get(2));
        allCases.add(case23);

        List<Atom> case24= new ArrayList<>();
        case24.add(Alist.get(0));
        case24.add(Alist.get(3));
        case24.addAll(Clist.subList(1,3));
        allCases.add(case24);


        //Cases w. neg Cx and pos Az
        List<Atom> case25= new ArrayList<>();
        case25.add(Blist.get(0));
        case25.add(Clist.get(0));
        case25.add(negCw);
        allCases.add(case25);

        List<Atom> case26= new ArrayList<>();
        case26.addAll(Clist.subList(0,2));
        case26.add(negCw);
        allCases.add(case26);

        List<Atom> case27= new ArrayList<>();
        case27.add(Alist.get(0));
        case27.add(Alist.get(1));
        case27.add(Blist.get(0));
        case27.add(posCw);
        case27.add(Clist.get(2));
        allCases.add(case27);

        List<Atom> case28= new ArrayList<>();
        case28.add(Alist.get(0));
        case28.add(Alist.get(1));
        case28.add(posCw);
        case28.addAll(Clist.subList(1,3));
        allCases.add(case28);

        //Cases w. Neg Cx and neg Cw
        List<Atom> case29= new ArrayList<>();
        case29.add(Alist.get(3));
        case29.add(Blist.get(0));
        case29.add(Clist.get(2));


        List<Atom> case30= new ArrayList<>();
        case30.add(Alist.get(3));
        case30.addAll(Clist.subList(1,3));


        //Cases w. Neg Cx and posCw
        List<Atom> case31= new ArrayList<>();
        case31.add(Alist.get(2));
        case31.add(Blist.get(0));
        case31.add(Clist.get(0));


        List<Atom> case32= new ArrayList<>();
        case32.add(Alist.get(2));
        case32.addAll(Clist.subList(0,2));


        //last case
        List<Atom> case33= new ArrayList<>();
        case33.add(negC);
        case33.add(negDz);
        case33.add(posAz);
        case33.add(Blist.get(2));
        case33.add(Clist.get(0));
        case33.add(negCw);



         temp.append(generateMultiOrClauses(negC, negDx, case1, case2));
        for (int i = 0; i <4; i++) {
            temp.append(generateMultiOrClauses(negC, negDy, allCases.get(i), allCases.get(i + 1)));
            i++;
        }
        for (int i = 4; i <8; i++) {
            temp.append(generateMultiOrClauses(negC, negBw, allCases.get(i), allCases.get(i + 1)));
            i++;
        }
        for (int i = 8; i <12; i++) {
            temp.append(generateMultiOrClauses(negC, negDz, allCases.get(i), allCases.get(i + 1)));
            i++;
        }
        for (int i = 12; i <18; i++) {
            temp.append(generateMultiOrClauses(negC, negDw, allCases.get(i), allCases.get(i + 1)));
            i++;
        }
        for (int i = 18; i <22; i++) {
            temp.append(generateMultiOrClauses(negC, negAz, allCases.get(i), allCases.get(i + 1)));
            i++;
        }
        for (int i = 22; i <26; i++) {
            temp.append(generateMultiOrClauses(negC, posAz, allCases.get(i), allCases.get(i + 1)));
            i++;
        }

        temp.append(generateMultiOrClauses(negC, negCw, case29, case30));
       temp.append(generateMultiOrClauses(negC, posCw, case31, case32));
        temp.append(generateOrClause(case33));
        return temp.toString();

    }

    private String generatePosBClauses(List<List<Atom>> negAtoms, List<List<Atom>> posAtoms) {
        StringBuilder temp = new StringBuilder();
        Atom posB = posAtoms.get(1).get(0);
        Atom negC = negAtoms.get(2).get(0);
        Atom posC = posAtoms.get(2).get(0);
        Atom negDx = negAtoms.get(3).get(0);
        Atom posDx = posAtoms.get(3).get(0);
        Atom negDy = negAtoms.get(3).get(1);

        List<Atom> Alist = Arrays.asList(negAtoms.get(0).get(1),negAtoms.get(0).get(2),negAtoms.get(0).get(3),
                posAtoms.get(0).get(1),posAtoms.get(0).get(2),posAtoms.get(0).get(3));
        List<Atom> Blist = Arrays.asList(negAtoms.get(1).get(2),negAtoms.get(1).get(3),
                posAtoms.get(1).get(2),posAtoms.get(1).get(3));
        List<Atom> Clist = Arrays.asList(negAtoms.get(2).get(1),negAtoms.get(2).get(3),
                posAtoms.get(2).get(1),posAtoms.get(2).get(3));
        List<Atom> Dlist = Arrays.asList(negAtoms.get(3).get(2),
                posAtoms.get(3).get(1),posAtoms.get(3).get(2));
        List<List> allCases = new ArrayList<>();

        //Cases w. pos Bx and neg Cx
        List<Atom> case1 = new ArrayList<>();
        case1.add(Alist.get(3));
        case1.add(Clist.get(0));
        case1.add(Clist.get(3));
        case1.add(posDx);
        case1.add(Dlist.get(1));

        List<Atom> case2 = new ArrayList<>();
        case2.add(Alist.get(5));
        case2.add(Blist.get(3));
        case2.addAll(Clist.subList(1,3));
        case2.add(posDx);

        //Cases w. pos Bx and pos Cx
        List<Atom> case3 = new ArrayList<>();
        case3.add(Alist.get(3));
        case3.add(Clist.get(2));
        case3.add(negDx);
        case3.add(negDy);
        case3.add(Dlist.get(2));
        allCases.add(case3);

        List<Atom> case4 = new ArrayList<>();
        case4.add(Alist.get(4));
        case4.add(Blist.get(2));
        case4.add(negDx);
        case4.addAll(Dlist.subList(0,2));
        allCases.add(case4);

        List<Atom> case5 = new ArrayList<>();
        case5.addAll(Alist.subList(0,2));
        case5.add(Blist.get(2));
        case5.add(Clist.get(2));
        case5.add(posDx);
        case5.addAll(Dlist.subList(1,3));
        allCases.add(case5);

        List<Atom> case6 = new ArrayList<>();
        case6.addAll(Alist.subList(3,5));
        case6.add(Blist.get(2));
        case6.add(Clist.get(2));
        case6.add(posDx);
        case6.addAll(Dlist.subList(1,3));
        allCases.add(case6);

        List<Atom> case7 = new ArrayList<>();
        case7.add(Alist.get(0));
        case7.add(Alist.get(2));
        case7.add(Blist.get(3));
        case7.add(Clist.get(2));
        case7.add(Clist.get(3));
        case7.add(posDx);
        case7.add(Dlist.get(1));
        allCases.add(case7);

        List<Atom> case8 = new ArrayList<>();
        case8.add(Alist.get(3));
        case8.add(Alist.get(5));
        case8.add(Blist.get(3));
        case8.add(Clist.get(2));
        case8.add(Clist.get(3));
        case8.add(posDx);
        case8.add(Dlist.get(1));
        allCases.add(case8);

        List<Atom> case9 = new ArrayList<>();
        case9.addAll(Alist.subList(1,3));
        case9.addAll(Blist.subList(2,4));
        case9.add(Clist.get(3));
        case9.add(posDx);
        case9.add(Dlist.get(2));
        allCases.add(case9);

        List<Atom> case10 = new ArrayList<>();
        case10.addAll(Alist.subList(4,6));
        case10.addAll(Blist.subList(2,4));
        case10.add(Clist.get(3));
        case10.add(posDx);
        case10.add(Dlist.get(2));
        allCases.add(case10);

        List<Atom> case11 = new ArrayList<>();
        case11.addAll(Alist.subList(1,3));
        case11.addAll(Blist.subList(0,2));
        case11.add(Clist.get(3));
        case11.add(Dlist.get(2));
        allCases.add(case11);

        List<Atom> case12 = new ArrayList<>();
        case12.add(Alist.get(0));
        case12.addAll(Alist.subList(4,6));
        case12.addAll(Blist.subList(2,4));
        case12.add(Clist.get(0));
        case12.add(Clist.get(3));
        allCases.add(case12);

        //Cases w. pos Bx and neg Dx
        List<Atom> case13 = new ArrayList<>();
        case13.add(Blist.get(0));
        case13.add(Blist.get(3));
        case13.add(negDy);

        List<Atom> case14 = new ArrayList<>();
        case14.add(Blist.get(1));
        case14.add(Blist.get(2));
        case14.addAll(Dlist.subList(0,2));

        //Cases w. pos Bx and pos Dx
        List<Atom> case15 = new ArrayList<>();
        case15.add(Alist.get(0));
        case15.addAll(Alist.subList(4,6));
        case15.addAll(Blist.subList(2,4));
        case15.add(negDy);
        case15.add(Dlist.get(2));

        List<Atom> case16 = new ArrayList<>();
        case16.add(Blist.get(0));
        case16.add(Blist.get(3));
        case16.addAll(Dlist.subList(0,2));

        //Cases w. pos Bx and neg Dy
        List<Atom> case17 = new ArrayList<>();
        case17.addAll(Blist.subList(0,2));
        case17.add(Dlist.get(2));

        List<Atom> case18 = new ArrayList<>();
        case18.add(Blist.get(1));
        case18.add(negDx);
        case18.add(Dlist.get(2));

       temp.append(generateMultiOrClauses(posB, negC, case1, case2));
        for (int i = 0; i <10; i++) {
            temp.append(generateMultiOrClauses(posB, posC, allCases.get(i), allCases.get(i + 1)));
            i++;
        }

        temp.append(generateMultiOrClauses(posB, negDx, case13, case14));
        temp.append(generateMultiOrClauses(posB, posDx, case15, case16));
       temp.append(generateMultiOrClauses(posB, negDy, case17, case18));
        return temp.toString();
    }


    private String generateNegBClauses(List<List<Atom>> negAtoms, List<List<Atom>> posAtoms) {
        StringBuilder temp = new StringBuilder();
        Atom negB = negAtoms.get(1).get(0);
        Atom negC = negAtoms.get(2).get(0);
        Atom posC = posAtoms.get(2).get(0);
        Atom negDx = negAtoms.get(3).get(0);
        Atom posDx = posAtoms.get(3).get(0);
        Atom negDy = negAtoms.get(3).get(1);
        Atom negDz = negAtoms.get(3).get(2);

        List<Atom> Alist = Arrays.asList(negAtoms.get(0).get(2),negAtoms.get(0).get(3),
                posAtoms.get(0).get(1),posAtoms.get(0).get(2),posAtoms.get(0).get(3));
        List<Atom> Blist = Arrays.asList(negAtoms.get(1).get(1),negAtoms.get(1).get(2),negAtoms.get(1).get(3),
                posAtoms.get(1).get(2),posAtoms.get(1).get(3));
        List<Atom> Clist = Arrays.asList(negAtoms.get(2).get(1),negAtoms.get(2).get(2),negAtoms.get(2).get(3),
                posAtoms.get(2).get(1),posAtoms.get(2).get(3));
        List<Atom> Dlist = Arrays.asList(negAtoms.get(3).get(2),negAtoms.get(3).get(3),
                posAtoms.get(3).get(1),posAtoms.get(3).get(2));
        List<List> allCases = new ArrayList<>();

//Generating cases w. neg Bx and neg Cx
        List<Atom> case1 = new ArrayList<>();
        case1.add(Alist.get(4));
        case1.add(Blist.get(2));
        case1.add(Clist.get(2));
       case1.add(Clist.get(3));

        List<Atom> case2 = new ArrayList<>();
        case2.addAll(Alist.subList(2,5));
        case2.addAll(Blist.subList(3,5));
        case2.addAll(Clist.subList(3,5));

        //Generating cases w. neg Bx and pos Cx

        List<Atom> case3 = new ArrayList<>();
        case3.addAll(Alist.subList(2,4));
        case3.add(Blist.get(1));
        case3.add(Clist.get(3));
        case3.add(posDx);
        case3.addAll(Dlist.subList(2,4));
        allCases.add(case3);

        List<Atom> case4 = new ArrayList<>();
        case4.add(Alist.get(2));
        case4.add(Alist.get(4));
        case4.add(Blist.get(2));
        case4.addAll(Clist.subList(3,5));
        case4.add(posDx);
        case4.add(Dlist.get(2));
        allCases.add(case4);

        List<Atom> case5 = new ArrayList<>();
        case5.addAll(Alist.subList(3,5));
        case5.add(Blist.get(1));
        case5.add(Blist.get(4));
        case5.add(Clist.get(4));
        case5.add(posDx);
        case5.add(Dlist.get(3));
        allCases.add(case5);

        List<Atom> case6 = new ArrayList<>();
        case6.add(Alist.get(3));
        case6.add(Blist.get(1));
        case6.add(Blist.get(4));
        case6.addAll(Clist.subList(3,5));
        case6.add(posDx);
        case6.add(Dlist.get(3));
        allCases.add(case6);

        List<Atom> case7 = new ArrayList<>();
        case7.add(Alist.get(4));
        case7.add(Blist.get(2));
        case7.add(Blist.get(3));
        case7.addAll(Clist.subList(3,5));
        case7.add(posDx);
        allCases.add(case7);

        List<Atom> case8 = new ArrayList<>();
        case8.add(Alist.get(0));
        case8.add(Alist.get(1));
        case8.add(Blist.get(2));
        case8.add(Blist.get(3));
        case8.add(Clist.get(0));
        case8.add(Clist.get(4));
        allCases.add(case8);


        List<Atom> case9 = new ArrayList<>();
        case9.addAll(Alist.subList(0,2));
        case9.add(Blist.get(4));
        case9.add(Blist.get(1));
        case9.add(Clist.get(0));
        case9.add(Clist.get(4));
        allCases.add(case9);

        List<Atom> case10 = new ArrayList<>();
        case10.add(negAtoms.get(0).get(1)); //neg Ay
        case10.add(Alist.get(0));
        case10.add(Blist.get(1));
        case10.addAll(Clist.subList(2,4));
        allCases.add(case10);

        //Cases w. neg Bx and neg D x
        List<Atom> case11 = new ArrayList<>();
        case11.addAll(Dlist.subList(2,4));
        case11.addAll(Blist.subList(3,5));

        List<Atom> case12 = new ArrayList<>();
        case12.add(Dlist.get(0));
        case12.add(Blist.get(1));
        case12.add(Blist.get(4));

        //Cases w. neg Bx and pos Dx
        List<Atom> case13 = new ArrayList<>();
        case13.add(negDy);
        case13.add(Dlist.get(3));
        case13.add(Blist.get(1));
        case13.add(Blist.get(4));

        List<Atom> case14 = new ArrayList<>();
        case14.addAll(Alist.subList(3,5));
        case14.addAll(Blist.subList(2,4));
        case14.add(posC);
        case14.add(Clist.get(4));

        List<Atom> case15 = new ArrayList<>();
        case15.add(negDy);
        case15.addAll(Blist.subList(2,4));

        List<Atom> case16 = new ArrayList<>();
        case16.add(Blist.get(2));
        case16.add(Dlist.get(0));
        case16.add(Dlist.get(2));

        //Cases w. neg Bx and neg Dy
        List<Atom> case17 = new ArrayList<>();
        case17.addAll(Blist.subList(3,5));
        case17.add(Dlist.get(0));

        List<Atom> case18 = new ArrayList<>();
        case18.add(Dlist.get(1));

        List<Atom> case19 = new ArrayList<>();
        case19.add(Clist.get(2));

        List<Atom> case20 = new ArrayList<>();
        case20.add(Blist.get(0));

        List<Atom> case21 = new ArrayList<>();
        case21.add(Clist.get(0));

        List<Atom> case22 = new ArrayList<>();
        case22.add(Clist.get(1));

        //Cases w.  neg Bx and neg Dz
        List<Atom> case23 = new ArrayList<>();
        case23.add(Dlist.get(1));

        List<Atom> case24 = new ArrayList<>();
        case24.add(Clist.get(2));

        List<Atom> case25 = new ArrayList<>();
        case25.add(Blist.get(1));
        case25.add(negDx);
        case25.add(Dlist.get(2));

        List<Atom> case26 = new ArrayList<>();
        case26.add(Blist.get(0));

        List<Atom> case27 = new ArrayList<>();
        case27.add(Clist.get(0));


        List<Atom> case28 = new ArrayList<>();
        case28.add(Clist.get(1));

     temp.append(generateMultiOrClauses(negB, negC, case1, case2));
    for (int i = 0; i <8; i++) {
            temp.append(generateMultiOrClauses(negB, posC, allCases.get(i), allCases.get(i + 1)));
            i++;
        }
        temp.append(generateMultiOrClauses(negB, negDx, case11, case12));
        temp.append(generateMultiOrClauses(negB, posDx, case13, case14));
        temp.append(generateMultiOrClauses(negB, posDx, case15, case16));
        temp.append(generateMultiOrClauses(negB, negDy, case17, case18));
        temp.append(generateMultiOrClauses(negB, negDy, case19, case20));
        temp.append(generateMultiOrClauses(negB, negDy, case21, case22));
        temp.append(generateMultiOrClauses(negB, negDz, case23, case24));
        temp.append(generateMultiOrClauses(negB, negDz, case25, case26));
        temp.append(generateMultiOrClauses(negB, negDz, case27, case28));
        return temp.toString();
    }


    private String generatePosAClauses(List<List<Atom>> negAtoms, List<List<Atom>> posAtoms) {
        StringBuilder temp = new StringBuilder();
        Atom posA = posAtoms.get(0).get(0);
        Atom posB = posAtoms.get(1).get(1);
        Atom posC = posAtoms.get(2).get(2);
        Atom posD = posAtoms.get(3).get(3);

        List<Atom> Alist = Arrays.asList(negAtoms.get(0).get(1),negAtoms.get(0).get(2),negAtoms.get(0).get(3),
                posAtoms.get(0).get(1),posAtoms.get(0).get(2),posAtoms.get(0).get(3));
        List<Atom> Blist = Arrays.asList(negAtoms.get(1).get(0),negAtoms.get(1).get(2),negAtoms.get(1).get(3),
                posAtoms.get(1).get(0),posAtoms.get(1).get(2),posAtoms.get(1).get(3));
        List<Atom> Clist = Arrays.asList(negAtoms.get(2).get(0),negAtoms.get(2).get(1),negAtoms.get(2).get(3),
                posAtoms.get(2).get(0),posAtoms.get(2).get(1),posAtoms.get(2).get(3));

        List<List<Atom>> allCases = new ArrayList<>();

        List<Atom> case1 = new ArrayList<>();
        case1.add(Alist.get(2));
        case1.add(Blist.get(0));
        case1.add(Blist.get(5));
        case1.add(Clist.get(0));
        case1.add(Clist.get(2));
        case1.add(Clist.get(4));
        allCases.add(case1);

        List<Atom> case2 = new ArrayList<>();
        case2.add(Alist.get(0));
        case2.add(Blist.get(0));
        case2.addAll(Clist.subList(0,2));
        case2.add(Clist.get(5));
        allCases.add(case2);

        List<Atom> case3 = new ArrayList<>();
        case3.add(Alist.get(2));
        case3.add(Blist.get(2));
        case3.add(Blist.get(3));
        case3.add(Clist.get(0));
        case3.add(Clist.get(2));
        case3.add(Clist.get(4));
        allCases.add(case3);

        List<Atom> case4 = new ArrayList<>();
        case4.addAll(Alist.subList(1,4));
        case4.add(Blist.get(2));
        case4.add(Blist.get(4));
        case4.add(Clist.get(0));
        case4.addAll(Clist.subList(4,6));
        allCases.add(case4);

        List<Atom> case5 = new ArrayList<>();
        case5.add(Alist.get(1));
        case5.add(Alist.get(3));
        case5.add(Alist.get(5));
        case5.add(Blist.get(2));
        case5.add(Blist.get(4));
        case5.add(Clist.get(0));
        case5.addAll(Clist.subList(4,6));
        allCases.add(case5);

        List<Atom> case6 = new ArrayList<>();
        case6.add(Alist.get(0));
        case6.addAll(Alist.subList(4,6));
        case6.add(Blist.get(2));
        case6.add(Blist.get(4));
        case6.add(Clist.get(0));
        case6.addAll(Clist.subList(4,6));
        allCases.add(case6);

        List<Atom> case7 = new ArrayList<>();
        case7.add(Alist.get(2));
        case7.addAll(Alist.subList(3,5));
        case7.add(Blist.get(2));
        case7.add(Blist.get(4));
        case7.add(Clist.get(0));
        case7.addAll(Clist.subList(4,6));

        allCases.add(case7);

        List<Atom> case8 = new ArrayList<>();
        case8.addAll(Alist.subList(0,3));
        case8.add(Blist.get(1));
        case8.add(Blist.get(5));
        case8.add(Clist.get(0));
        case8.addAll(Clist.subList(4,6));
        allCases.add(case8);

        List<Atom> case9 = new ArrayList<>();
        case9.add(Alist.get(1));
        case9.add(Alist.get(3));
        case9.add(Alist.get(5));
        case9.add(Blist.get(1));
        case9.add(Blist.get(5));
        case9.add(Clist.get(0));
        case9.addAll(Clist.subList(4,6));
        allCases.add(case9);

        List<Atom> case10 = new ArrayList<>();
        case10.add(Alist.get(0));
        case10.addAll(Alist.subList(4,6));
        case10.add(Blist.get(1));
        case10.add(Blist.get(5));
        case10.add(Clist.get(0));
        case10.addAll(Clist.subList(4,6));
        allCases.add(case10);

        List<Atom> case11 = new ArrayList<>();
        case11.add(Alist.get(2));
        case11.addAll(Alist.subList(3,5));
        case11.add(Blist.get(1));
        case11.add(Blist.get(5));
        case11.add(Clist.get(0));
        case11.addAll(Clist.subList(4,6));
        allCases.add(case11);

        List<Atom> case12 = new ArrayList<>();
        case12.add(Alist.get(1));
        case12.add(Blist.get(1));
        case12.addAll(Clist.subList(0,3));
        allCases.add(case12);


        List<Atom> case13 = new ArrayList<>();
        case13.add(Alist.get(0));
        case13.add(Alist.get(5));
        case13.add(Blist.get(2));
        case13.addAll(Clist.subList(1,4));
        allCases.add(case13);

        List<Atom> case14 = new ArrayList<>();
        case14.add(Alist.get(2));
        case14.add(Alist.get(3));
        case14.add(Blist.get(2));
        case14.addAll(Clist.subList(1,4));
        allCases.add(case14);

        List<Atom> case15 = new ArrayList<>();
        case15.add(Alist.get(1));
        case15.add(Alist.get(5));
        case15.addAll(Blist.subList(2,5));
        case15.add(Clist.get(1));
        case15.add(Clist.get(3));
        case15.add(Clist.get(5));
        allCases.add(case15);

        List<Atom> case16 = new ArrayList<>();
        case16.add(Alist.get(2));
        case16.add(Alist.get(4));
        case16.addAll(Blist.subList(2,5));
        case16.add(Clist.get(1));
        case16.add(Clist.get(3));
        case16.add(Clist.get(5));
        allCases.add(case16);


        List<Atom> case17 = new ArrayList<>();
        case17.add(Alist.get(1));
        case17.add(Alist.get(5));
        case17.add(Blist.get(1));
        case17.add(Blist.get(3));
        case17.add(Blist.get(5));
        case17.add(Clist.get(1));
        case17.add(Clist.get(3));
        case17.add(Clist.get(5));
        allCases.add(case17);

        List<Atom> case18 = new ArrayList<>();
        case18.add(Alist.get(2));
        case18.add(Alist.get(4));
        case18.add(Blist.get(1));
        case18.add(Blist.get(3));
        case18.add(Blist.get(5));
        case18.add(Clist.get(1));
        case18.add(Clist.get(3));
        case18.add(Clist.get(5));
        allCases.add(case18);


        List<Atom> case19 = new ArrayList<>();
        case19.add(Alist.get(1));
        case19.add(Alist.get(3));
        case19.add(Blist.get(1));
        case19.add(Blist.get(3));
        case19.addAll(Clist.subList(2,5));
        allCases.add(case19);

        List<Atom> case20 = new ArrayList<>();
        case20.add(Alist.get(0));
        case20.add(Alist.get(4));
        case20.add(Blist.get(1));
        case20.add(Blist.get(3));
        case20.addAll(Clist.subList(2,5));
        allCases.add(case20);

        List<Atom> case21 = new ArrayList<>();
        case21.add(Alist.get(1));
        case21.add(Alist.get(5));
        case21.addAll(Blist.subList(0,3));
        case21.add(Clist.get(1));
        case21.add(Clist.get(3));
        case21.add(Clist.get(5));
        allCases.add(case21);

        List<Atom> case22 = new ArrayList<>();
        case22.add(Alist.get(2));
        case22.add(Alist.get(4));
        case22.addAll(Blist.subList(0,3));
        case22.add(Clist.get(1));
        case22.add(Clist.get(3));
        case22.add(Clist.get(5));
        allCases.add(case22);

        List<Atom> case23 = new ArrayList<>();
        case23.add(Alist.get(1));
        case23.add(Alist.get(5));
        case23.add(Blist.get(0));
        case23.addAll(Blist.subList(4,6));
        case23.add(Clist.get(1));
        case23.add(Clist.get(3));
        case23.add(Clist.get(5));
        allCases.add(case23);

        List<Atom> case24 = new ArrayList<>();
        case24.add(Alist.get(2));
        case24.add(Alist.get(4));
        case24.add(Blist.get(0));
        case24.addAll(Blist.subList(4,6));
        case24.add(Clist.get(1));
        case24.add(Clist.get(3));
        case24.add(Clist.get(5));
        allCases.add(case24);

        List<Atom> case25 = new ArrayList<>();
        case25.add(Alist.get(1));
        case25.add(Alist.get(3));
        case25.add(Blist.get(0));
        case25.add(Blist.get(4));
        case25.addAll(Clist.subList(2,5));
        allCases.add(case25);

        List<Atom> case26 = new ArrayList<>();
        case26.add(Alist.get(0));
        case26.add(Alist.get(4));
        case26.add(Blist.get(0));
        case26.add(Blist.get(4));
        case26.addAll(Clist.subList(2,5));
        allCases.add(case26);

        temp.append(generate4OrClauses(posA,posB,posC,posD,allCases));

        return temp.toString();
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


    private String generate4OrClauses(Atom a, Atom b,Atom c,Atom d, List<List<Atom>> cases){
        StringBuilder temp2 = new StringBuilder();
        String ab = a.stringRep + " || " + b.stringRep + " || " + c.stringRep + " || " + d.stringRep;

        for (List<Atom> list:cases
             ) {
            String temp ="(" + ab + " || ";
            for (Atom at : list) {
                temp += at.stringRep + " || ";
            }
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
