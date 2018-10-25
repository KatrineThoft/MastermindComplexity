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

        res.append(generateAndClause(case1));
        res.append(generateNegBNegCCases(allNegAtomsList,allPosAtomsList));
        res.append(generateNegBPosCCases(allNegAtomsList,allPosAtomsList));
        res.append(generateNegBRestCases(allNegAtomsList,allPosAtomsList));
        res.append(generatePosBAndCCases(allNegAtomsList,allPosAtomsList));
        res.append(generatePosBRestCases(allNegAtomsList,allPosAtomsList));
        res.append(generateNegCCases(allNegAtomsList,allPosAtomsList));
        res.append(generatePosCCases(allNegAtomsList,allPosAtomsList));
        res.append(generateDxCases(allNegAtomsList,allPosAtomsList));
        res.append(generateDyCases(allNegAtomsList,allPosAtomsList));
        res.append(generateRestCases(allNegAtomsList,allPosAtomsList));


        super.boolTrans = res.substring(0,res.lastIndexOf("&&"));
    }

    private String generateRestCases(List<List<Atom>> negAtoms, List<List<Atom>> posAtoms) {
        StringBuilder temp = new StringBuilder();
        Atom posDz = posAtoms.get(3).get(2);
        Atom posCw = posAtoms.get(2).get(3);
        Atom posBw = posAtoms.get(1).get(3);
        Atom posCy = posAtoms.get(2).get(1);
        Atom posAw = posAtoms.get(0).get(3);
        Atom posAz = posAtoms.get(0).get(2);
        Atom posAy = posAtoms.get(0).get(1);



        List<Atom> Alist = Arrays.asList(negAtoms.get(0).get(2), negAtoms.get(0).get(3),
                posAtoms.get(0).get(1), posAtoms.get(0).get(2), posAtoms.get(0).get(3));
        List<Atom> Blist = Arrays.asList(negAtoms.get(1).get(2), negAtoms.get(1).get(3),
                posAtoms.get(1).get(2));

        List<Atom> case1 = new ArrayList<>();
        case1.addAll(Alist.subList(0,2));
        case1.addAll(Blist.subList(0,2));

        List<Atom> case2 = new ArrayList<>();
        case2.add(Blist.get(2));
        case2.add(posBw);

        List<Atom> case3a = new ArrayList<>();
        case3a.add(posAw);
        case3a.add(posCw);

        List<Atom> case3b = new ArrayList<>();
        case3b.add(Blist.get(2));


        List<Atom> case4 = new ArrayList<>();
        case4.addAll(Alist.subList(0,3));
        case4.addAll(Blist.subList(0,2));

        List<Atom> case5 = new ArrayList<>();
        case5.add(posBw);
        case5.add(Blist.get(2));

        List<Atom> case6 = new ArrayList<>();
        case6.add(posCw);
        case6.add(posAw);
        case6.add(posBw);

        List<Atom> case7 = new ArrayList<>();
        case7.add(posAw);
        case7.add(Blist.get(2));

        List<Atom> case8 = new ArrayList<>();
        case8.add(Blist.get(2));
        case8.add(posCy);

        List<Atom> case9 = new ArrayList<>();
        case9.add(Blist.get(2));
        case9.add(posCy);

        List<Atom> case10 = new ArrayList<>();
        case10.add(posAw);

        temp.append(generateMultiOrClauses(posDz, posCw, case1, case2));
        temp.append(generateMultiOrClauses(posDz, posAz, case3a, case3b));
        temp.append(generateMultiOrClauses(posCw, posCy, case4, case5));
        temp.append(generateOrClause(case6));
        temp.append(generateMultiOrClauses(posBw, posAz, case7, case8));
        temp.append(generateMultiOrClauses(posAz, posAy, case9, case10));
        return temp.toString();

    }
    private String generateDyCases(List<List<Atom>> negAtoms, List<List<Atom>> posAtoms) {
        StringBuilder temp = new StringBuilder();
        Atom negDy = negAtoms.get(3).get(1);
        Atom posDy = posAtoms.get(3).get(1);
        Atom posCy = posAtoms.get(2).get(1);
        Atom negDz = negAtoms.get(3).get(2);
        Atom posDz = posAtoms.get(3).get(2);


        List<Atom> Alist = Arrays.asList(negAtoms.get(0).get(1), negAtoms.get(0).get(2), negAtoms.get(0).get(3),
                posAtoms.get(0).get(1), posAtoms.get(0).get(2), posAtoms.get(0).get(3));
        List<Atom> Blist = Arrays.asList(negAtoms.get(1).get(2), negAtoms.get(1).get(3),
                posAtoms.get(1).get(2), posAtoms.get(1).get(3));
        List<Atom> Clist = Arrays.asList(negAtoms.get(2).get(1), negAtoms.get(2).get(3),
                posAtoms.get(2).get(3));
        List<Atom> Dlist = Arrays.asList(negAtoms.get(3).get(2), posAtoms.get(3).get(2));
        List<List<Atom>> allCases = new ArrayList<>();

        List<Atom> case1 = new ArrayList<>();
        case1.addAll(Alist.subList(1, 4));
        case1.addAll(Blist.subList(0, 2));
        case1.addAll(Clist.subList(0, 2));
        allCases.add(case1);

        List<Atom> case2 = new ArrayList<>();
        case2.addAll(Alist.subList(1, 3));
        case2.addAll(Blist.subList(2, 4));
        case2.addAll(Clist.subList(0, 2));
        allCases.add(case2);

        List<Atom> case3 = new ArrayList<>();
        case3.add(Alist.get(0));
        case3.add(Alist.get(2));
        case3.add(Alist.get(4));
        case3.addAll(Blist.subList(0, 2));
        case3.add(posCy);
        case3.add(Clist.get(2));
        allCases.add(case3);

        List<Atom> case4 = new ArrayList<>();
        case4.addAll(Alist.subList(0, 2));
        case4.add(Blist.get(2));
        case4.add(posCy);
        case4.add(Clist.get(2));
        allCases.add(case4);

        List<Atom> case5 = new ArrayList<>();
        case5.add(negDy);
        case5.add(negDz);
        case5.addAll(Alist.subList(0, 2));
        case5.add(Alist.get(5));
        case5.add(posCy);
        case5.add(Clist.get(2));


        List<Atom> case6 = new ArrayList<>();
        case6.addAll(Alist.subList(0, 2));
        case6.add(Alist.get(5));
        case6.addAll(Blist.subList(0, 2));
        case6.addAll(Clist.subList(0, 2));
        allCases.add(case6);

        List<Atom> case7 = new ArrayList<>();
        case7.add(Alist.get(0));
        case7.add(Alist.get(2));
        case7.add(Blist.get(3));
        case7.addAll(Clist.subList(0, 2));
        allCases.add(case7);

        List<Atom> case8 = new ArrayList<>();
        case8.add(Alist.get(0));
        case8.add(Alist.get(2));
        case8.add(Alist.get(4));
        case8.addAll(Clist.subList(0, 2));
        allCases.add(case8);

        List<Atom> case9 = new ArrayList<>();
        case9.add(posCy);
        case9.add(Clist.get(2));
        allCases.add(case9);

        List<Atom> case10 = new ArrayList<>();
        case10.addAll(Alist.subList(1, 4));
        case10.addAll(Blist.subList(0, 2));
        allCases.add(case10);

        List<Atom> case11 = new ArrayList<>();
        case11.addAll(Blist.subList(2, 4));
        allCases.add(case11);

        List<Atom> case12 = new ArrayList<>();
        case12.add(Blist.get(2));
        case12.add(posDz);

        List<Atom> case13 = new ArrayList<>();
        case13.add(Alist.get(3));

        List<Atom> case14 = new ArrayList<>();
        case14.add(posCy);
        case14.add(Clist.get(2));
        allCases.add(case14);

        List<Atom> case15 = new ArrayList<>();
        case15.add(Alist.get(5));
        case15.add(Blist.get(2));
        allCases.add(case15);

        List<Atom> case16 = new ArrayList<>();
        case16.add(Blist.get(2));
        case16.add(posCy);
        allCases.add(case16);

        List<Atom> case17 = new ArrayList<>();
        case17.add(Alist.get(3));
        case17.add(Alist.get(5));
        allCases.add(case17);

        for (int i = 0; i < 4; i++) {
            temp.append(generateMultiOrClauses(negDy, negDz, allCases.get(i), allCases.get(i + 1)));
            i++;
        }
        temp.append(generateOrClause(case5));
        for (int i = 4; i < allCases.size() - 4; i++) {
            temp.append(generateMultiOrClauses(posDy, posDz, allCases.get(i), allCases.get(i + 1)));
            i++;
        }
        temp.append(generateMultiOrClauses(posDy, posCy, case12, case13));

        for (int i = allCases.size() - 4; i < allCases.size(); i++) {
            temp.append(generateMultiOrClauses(posDy, Blist.get(3), allCases.get(i), allCases.get(i + 1)));
            i++;
        }
        return temp.toString();
    }
    private String generateDxCases(List<List<Atom>> negAtoms, List<List<Atom>> posAtoms) {
        StringBuilder temp = new StringBuilder();
        Atom negDx = negAtoms.get(3).get(0);
        Atom posDx= posAtoms.get(3).get(0);
        Atom negDz= negAtoms.get(3).get(2);
        Atom negDy= negAtoms.get(3).get(1);
        Atom posDy = posAtoms.get(3).get(1);



        List<Atom> Alist = Arrays.asList(negAtoms.get(0).get(1),negAtoms.get(0).get(2),negAtoms.get(0).get(3),
                posAtoms.get(0).get(1),posAtoms.get(0).get(2),posAtoms.get(0).get(3));
        List<Atom> Blist = Arrays.asList(negAtoms.get(1).get(2),negAtoms.get(1).get(3),
                posAtoms.get(1).get(2),posAtoms.get(1).get(3));
        List<Atom> Clist = Arrays.asList(negAtoms.get(2).get(1),negAtoms.get(2).get(3),
                posAtoms.get(2).get(1),posAtoms.get(2).get(3));
        List<Atom> Dlist = Arrays.asList(negAtoms.get(3).get(2), posAtoms.get(3).get(2));

        List<Atom> case1 = new ArrayList<>();
        case1.add(Alist.get(0));
        case1.add(Alist.get(2));
        case1.add(Alist.get(4));
        case1.addAll(Blist.subList(0,2));
        case1.add(Clist.get(2));
        case1.add(negDy);

        List<Atom> case2 = new ArrayList<>();
        case2.add(Blist.get(3));
        case2.add(Clist.get(2));
        case2.add(posDy);

        List<Atom> case3 = new ArrayList<>();
        case3.add(negDx);
        case3.add(Dlist.get(0));
        case3.add(Alist.get(4));
        case3.add(Alist.get(5));
        case3.add(Blist.get(3));

        List<Atom> case4 = new ArrayList<>();
        case4.addAll(Alist.subList(0,3));
        case4.add(Blist.get(2));
        case4.addAll(Clist.subList(0,2));
        case4.add(Dlist.get(0));

        List<Atom> case5 = new ArrayList<>();
        case5.addAll(Alist.subList(0,2));
        case5.add(Alist.get(5));
        case5.add(Clist.get(3));
        case5.add(Dlist.get(0));

        List<Atom> case6 = new ArrayList<>();
        case6.add(Dlist.get(1));

        List<Atom> case7 = new ArrayList<>();
        case7.add(Alist.get(3));
        case7.add(Alist.get(5));

        List<Atom> case8 = new ArrayList<>();
        case8.add(posDx);
        case8.add(Dlist.get(1));
        case8.add(Alist.get(4));
        case8.add(Alist.get(5));

            temp.append(generateMultiOrClauses(negDx, negDz,case1, case2));
            temp.append(generateOrClause(case3));
            temp.append(generateMultiOrClauses(posDx, negDy,case4, case5));
            temp.append(generateMultiOrClauses(posDx, posDy,case6, case7));
            temp.append(generateOrClause(case8));
            return temp.toString();
     }
    private String generatePosCCases(List<List<Atom>> negAtoms, List<List<Atom>> posAtoms) {
        StringBuilder temp = new StringBuilder();
        Atom posC = posAtoms.get(2).get(0);
        Atom negDx = negAtoms.get(3).get(0);
        Atom posDx= posAtoms.get(3).get(0);
        Atom posCy= posAtoms.get(2).get(1);
        Atom posAz= posAtoms.get(0).get(2);


        List<Atom> Alist = Arrays.asList(negAtoms.get(0).get(1),negAtoms.get(0).get(2),negAtoms.get(0).get(3),
                posAtoms.get(0).get(1),posAtoms.get(0).get(2),posAtoms.get(0).get(3));
        List<Atom> Blist = Arrays.asList(posAtoms.get(1).get(2));
        List<Atom> Clist = Arrays.asList(negAtoms.get(2).get(1),negAtoms.get(2).get(3),
                posAtoms.get(2).get(1),posAtoms.get(2).get(3));
        List<Atom> Dlist = Arrays.asList(negAtoms.get(3).get(1),negAtoms.get(3).get(2),
                posAtoms.get(3).get(1),posAtoms.get(3).get(2));

        List<Atom> case1 = new ArrayList<>();
        case1.add(Clist.get(2));
        case1.add(Dlist.get(1));
        case1.add(Dlist.get(2));

        List<Atom> case2 = new ArrayList<>();
        case2.addAll(Alist.subList(4,6));
        case2.add(Dlist.get(1));

        List<Atom> case3 = new ArrayList<>();
        case3.addAll(Alist.subList(1,4));
        case3.addAll(Clist.subList(0,2));
        case3.addAll(Dlist.subList(0,2));

        List<Atom> case4 = new ArrayList<>();
        case4.add(Alist.get(5));
        case4.add(Blist.get(0));
        case4.add(Dlist.get(2));

        List<Atom> case5 = new ArrayList<>();
        case5.add(Clist.get(3));
        case5.add(Dlist.get(3));

        List<Atom> case6 = new ArrayList<>();
        case6.add(Alist.get(4));
        case6.add(Dlist.get(3));

        List<Atom> case7 = new ArrayList<>();
        case7.add(Blist.get(0));
        case7.add(Dlist.get(2));

        List<Atom> case8 = new ArrayList<>();
        case8.add(Clist.get(3));


        List<Atom> case9 = new ArrayList<>();
        case9.add(Alist.get(0));
        case9.add(Alist.get(2));
        case9.add(Clist.get(0));
        case9.add(Clist.get(1));
        case9.add(Dlist.get(3));

        List<Atom> case10 = new ArrayList<>();
        case10.add(Alist.get(5));
        case10.add(Blist.get(0));

        List<Atom> case11 = new ArrayList<>();
        case11.add(Blist.get(0));
        case11.add(Clist.get(2));

        List<Atom> case12 = new ArrayList<>();
        case12.add(Alist.get(3));
        case12.add(Clist.get(2));

        List<Atom> case13 = new ArrayList<>();
        case13.add(posC);
        case13.add(Alist.get(5));
        case13.add(Clist.get(3));
        case13.add(posDx);
        temp.append(generateMultiOrClauses(posC, negDx,case1, case2));
        temp.append(generateMultiOrClauses(posC, posDx,case3, case4));
        temp.append(generateMultiOrClauses(posC, posDx,case5, case6));
        temp.append(generateMultiOrClauses(posC, posCy,case7, case8));
        temp.append(generateMultiOrClauses(posC, posAz,case9, case10));
        temp.append(generateMultiOrClauses(posC, posAz,case11, case12));
        temp.append(generateOrClause(case13));
        return temp.toString();
    }
    private String generateNegCCases(List<List<Atom>> negAtoms, List<List<Atom>> posAtoms) {
        StringBuilder temp = new StringBuilder();
        Atom negC = negAtoms.get(2).get(0);
        Atom negDx = negAtoms.get(3).get(0);
        Atom posDx= posAtoms.get(3).get(0);


        List<Atom> Alist = Arrays.asList(negAtoms.get(0).get(1),negAtoms.get(0).get(2),negAtoms.get(0).get(3),
                posAtoms.get(0).get(1),posAtoms.get(0).get(2),posAtoms.get(0).get(3));
        List<Atom> Blist = Arrays.asList(negAtoms.get(1).get(2),negAtoms.get(1).get(3),
               posAtoms.get(1).get(3));
        List<Atom> Clist = Arrays.asList(negAtoms.get(2).get(1),negAtoms.get(2).get(3),
                posAtoms.get(2).get(1),posAtoms.get(2).get(3));
        List<Atom> Dlist = Arrays.asList(negAtoms.get(3).get(1),negAtoms.get(3).get(2),
                posAtoms.get(3).get(1),posAtoms.get(3).get(2));

        List<Atom> case1 = new ArrayList<>();
        case1.addAll(Alist.subList(1,4));
        case1.addAll(Clist.subList(0,2));
        case1.addAll(Dlist.subList(0,2));

        List<Atom> case2 = new ArrayList<>();
        case2.add(Alist.get(0));
        case2.add(Alist.get(2));
        case2.add(Alist.get(4));
        case2.add(Clist.get(1));
        case2.add(Clist.get(2));
        case2.add(Dlist.get(0));
        case2.add(Dlist.get(3));

        List<Atom> case3 = new ArrayList<>();
        case3.add(Clist.get(0));
        case3.add(Clist.get(3));
        case3.add(Dlist.get(0));
        case3.add(Dlist.get(3));

        List<Atom> case4 = new ArrayList<>();
        case4.add(Alist.get(3));
        case4.add(Alist.get(4));
        case4.add(Clist.get(0));
        case4.add(Dlist.get(0));
        case4.add(Dlist.get(3));

        List<Atom> case5 = new ArrayList<>();
        case5.add(Alist.get(3));
        case5.add(Alist.get(5));
        case5.add(Clist.get(0));
        case5.add(Clist.get(3));
        case5.add(Dlist.get(0));

        List<Atom> case6 = new ArrayList<>();
        case6.addAll(Alist.subList(0,2));
        case6.add(Alist.get(5));
        case6.add(Clist.get(0));
        case6.add(Dlist.get(1));
        case6.add(Dlist.get(2));

        List<Atom> case7 = new ArrayList<>();
        case7.add(Alist.get(0));
        case7.add(Alist.get(2));
        case7.add(Alist.get(4));
        case7.add(Blist.get(2));
        case7.add(Clist.get(0));
        case7.add(Clist.get(3));
        case7.addAll(Dlist.subList(0,2));

        List<Atom> case8 = new ArrayList<>();
        case8.addAll(Blist.subList(0,2));
        case8.add(Clist.get(1));
        case8.add(Clist.get(2));
        case8.add(Dlist.get(2));

        List<Atom> case9 = new ArrayList<>();
        case9.add(negC);
        case9.add(posDx);
        case9.addAll(Alist.subList(4,6));
        case9.addAll(Blist.subList(0,2));
        case9.add(Clist.get(1));


        temp.append(generateMultiOrClauses(negC, negDx,case1, case2));
        temp.append(generateMultiOrClauses(negC, negDx,case3, case4));
        temp.append(generateMultiOrClauses(negC, negDx,case5, case6));
        temp.append(generateMultiOrClauses(negC, posDx,case7, case8));
        temp.append(generateOrClause(case9));


        return temp.toString();
    }
    private String generatePosBRestCases(List<List<Atom>> negAtoms, List<List<Atom>> posAtoms) {
        StringBuilder temp = new StringBuilder();
        Atom posB = posAtoms.get(1).get(0);
        Atom negDx = negAtoms.get(3).get(0);
        Atom posDx= posAtoms.get(3).get(0);
        Atom negDy = negAtoms.get(3).get(1);
        Atom posBw = posAtoms.get(1).get(3);
        Atom posAy= posAtoms.get(0).get(1);

        List<Atom> Alist = Arrays.asList(negAtoms.get(0).get(1),negAtoms.get(0).get(2),negAtoms.get(0).get(3),
                posAtoms.get(0).get(1),posAtoms.get(0).get(2),posAtoms.get(0).get(3));
        List<Atom> Blist = Arrays.asList(negAtoms.get(1).get(2),negAtoms.get(1).get(3),
                posAtoms.get(1).get(2),posAtoms.get(1).get(3));
        List<Atom> Clist = Arrays.asList(posAtoms.get(2).get(0),
                posAtoms.get(2).get(1),posAtoms.get(2).get(3));
        List<Atom> Dlist = Arrays.asList(negAtoms.get(3).get(1),negAtoms.get(3).get(2),
               posAtoms.get(3).get(1),posAtoms.get(3).get(2));

        List<Atom> case1 = new ArrayList<>();
        case1.add(Alist.get(5));
        case1.add(Blist.get(3));
        case1.add(Dlist.get(1));

        List<Atom> case2 = new ArrayList<>();
        case2.addAll(Clist.subList(0,2));
        case2.add(Dlist.get(1));

        List<Atom> case3 = new ArrayList<>();
        case3.add(Alist.get(0));
        case3.add(Alist.get(2));
        case3.add(Alist.get(4));
        case3.addAll(Blist.subList(0,2));
        case3.add(Clist.get(2));
        case3.addAll(Dlist.subList(0,2));

        List<Atom> case4 = new ArrayList<>();
        case4.add(Blist.get(3));
        case4.add(Clist.get(2));
        case4.add(Dlist.get(2));

        List<Atom> case5 = new ArrayList<>();
        case5.add(Alist.get(3));
        case5.add(Dlist.get(2));

        List<Atom> case6 = new ArrayList<>();
        case6.add(Alist.get(5));
        case6.add(Blist.get(3));
        case6.add(Dlist.get(3));

        List<Atom> case7 = new ArrayList<>();
        case7.addAll(Alist.subList(0,2));
        case7.add(Blist.get(2));
        case7.add(Dlist.get(1));
        case7.add(posDx);

        List<Atom> case8 = new ArrayList<>();
        case8.addAll(Alist.subList(0,2));
        case8.add(Blist.get(2));
        case8.add(Clist.get(1));
        case8.add(Dlist.get(1));

        List<Atom> case9 = new ArrayList<>();
        case9.addAll(Alist.subList(1,3));
        case9.addAll(Blist.subList(0,2));

        List<Atom> case10 = new ArrayList<>();
        case10.add(Alist.get(4));
        case10.add(Blist.get(2));

        List<Atom> case11 = new ArrayList<>();
        case11.add(Blist.get(2));

        List<Atom> case12 = new ArrayList<>();
        case12.add(Alist.get(3));
        case12.add(Alist.get(5));

        temp.append(generateMultiOrClauses(posB, negDx,case1, case2));
        temp.append(generateMultiOrClauses(posB, posDx,case3, case4));
        temp.append(generateMultiOrClauses(posB, posDx,case5, case6));
        temp.append(generateMultiOrClauses(posB, negDy,case7, case8));
        temp.append(generateMultiOrClauses(posB, posAy,case9, case10));
        temp.append(generateMultiOrClauses(posB, posBw,case11, case12));

        return temp.toString();
    }
    private String generatePosBAndCCases(List<List<Atom>> negAtoms, List<List<Atom>> posAtoms) {
        StringBuilder temp = new StringBuilder();
        Atom posB = posAtoms.get(1).get(0);
        Atom negC = negAtoms.get(2).get(0);
        Atom posC = posAtoms.get(2).get(0);

        List<Atom> Alist = Arrays.asList(negAtoms.get(0).get(1),negAtoms.get(0).get(2),negAtoms.get(0).get(3),
                posAtoms.get(0).get(1),posAtoms.get(0).get(2),posAtoms.get(0).get(3));
        List<Atom> Blist = Arrays.asList(negAtoms.get(1).get(2),negAtoms.get(1).get(3),
                posAtoms.get(1).get(2),posAtoms.get(1).get(3));
        List<Atom> Clist = Arrays.asList(negAtoms.get(2).get(1),negAtoms.get(2).get(3),
                posAtoms.get(2).get(1),posAtoms.get(2).get(3));
        List<Atom> Dlist = Arrays.asList(negAtoms.get(3).get(0),negAtoms.get(3).get(1),negAtoms.get(3).get(2),
                posAtoms.get(3).get(0),posAtoms.get(3).get(1),posAtoms.get(3).get(2));
        List<List<Atom>> allCases = new ArrayList<>();

        List<Atom> case1 = new ArrayList<>();
        case1.add(Blist.get(3));
        case1.add(Clist.get(0));
        case1.addAll(Dlist.subList(0,3));
        allCases.add(case1);

        List<Atom> case2 = new ArrayList<>();
        case2.add(Alist.get(0));
        case2.add(Alist.get(2));
        case2.add(Blist.get(3));
        case2.addAll(Clist.subList(1,3));
        case2.addAll(Dlist.subList(0,2));
        case2.add(Dlist.get(5));
        allCases.add(case2);

        List<Atom> case3 = new ArrayList<>();
        case3.add(Blist.get(2));
        case3.add(Clist.get(0));
        case3.addAll(Dlist.subList(0,2));
        case3.add(Dlist.get(5));
        allCases.add(case3);

        List<Atom> case4 = new ArrayList<>();
        case4.addAll(Alist.subList(0,2));
        case4.add(Alist.get(5));
        case4.addAll(Blist.subList(0,2));
        case4.addAll(Clist.subList(1,3));
        case4.addAll(Dlist.subList(0,2));
        allCases.add(case4);

        List<Atom> case5 = new ArrayList<>();
        case5.add(Alist.get(3));
        case5.add(Clist.get(0));
        case5.addAll(Dlist.subList(0,2));
        allCases.add(case5);

        List<Atom> case6 = new ArrayList<>();
        case6.add(Alist.get(0));
        case6.add(Alist.get(2));
        case6.add(Alist.get(4));
        case6.addAll(Blist.subList(0,2));
        case6.add(Clist.get(0));
        case6.add(Dlist.get(0));
        case6.add(Dlist.get(2));
        case6.add(Dlist.get(4));
        allCases.add(case6);

        List<Atom> case7 = new ArrayList<>();
        case7.addAll(Alist.subList(0,2));
        case7.add(Blist.get(2));
        case7.add(Clist.get(0));
        case7.add(Dlist.get(0));
        case7.add(Dlist.get(2));
        case7.add(Dlist.get(4));
        allCases.add(case7);

        List<Atom> case8 = new ArrayList<>();
        case8.add(Alist.get(0));
        case8.add(Alist.get(2));
        case8.add(Blist.get(3));
        case8.add(Clist.get(1));
        case8.addAll(Dlist.subList(1,4));
        allCases.add(case8);

        List<Atom> case9 = new ArrayList<>();
        case9.addAll(Blist.subList(0,2));
        case9.add(Clist.get(1));
        case9.addAll(Dlist.subList(3,5));
        allCases.add(case9);

        List<Atom> case10 = new ArrayList<>();
        case10.addAll(Alist.subList(1,3));
        case10.addAll(Blist.subList(0,2));
        case10.add(Clist.get(1));
        case10.add(Dlist.get(3));
        allCases.add(case10);


        List<Atom> case11 = new ArrayList<>();
        case11.addAll(Alist.subList(1,3));
        case11.addAll(Blist.subList(0,2));
        case11.add(Dlist.get(0));
        case11.add(Dlist.get(2));
        allCases.add(case11);

        List<Atom> case12 = new ArrayList<>();
        case12.add(Dlist.get(3));
        case12.add(Dlist.get(5));
        allCases.add(case12);

        List<Atom> case13 = new ArrayList<>();
        case13.add(Clist.get(3));
        case13.add(Dlist.get(3));
        allCases.add(case13);

        List<Atom> case14 = new ArrayList<>();
        case14.add(Blist.get(2));
        case14.add(Dlist.get(3));
        allCases.add(case14);

        List<Atom> case15 = new ArrayList<>();
        case15.add(Alist.get(3));
        case15.add(Dlist.get(3));
        allCases.add(case15);

        List<Atom> case16 = new ArrayList<>();
        case16.addAll(Alist.subList(0,2));
        case16.add(Alist.get(5));
        case16.addAll(Blist.subList(0,2));
        case16.addAll(Clist.subList(0,2));
        case16.add(Dlist.get(5));
        allCases.add(case16);

        List<Atom> case17 = new ArrayList<>();
        case17.add(Alist.get(0));
        case17.add(Alist.get(2));
        case17.add(Blist.get(3));
        case17.addAll(Clist.subList(0,2));
        case17.add(Dlist.get(5));
        allCases.add(case17);

        List<Atom> case18 = new ArrayList<>();
        case18.add(Alist.get(4));
        case18.add(Blist.get(2));
        allCases.add(case18);

        List<Atom> case19 = new ArrayList<>();
        case19.add(Blist.get(2));
        case19.add(Clist.get(2));
        allCases.add(case19);

        List<Atom> case20 = new ArrayList<>();
        case20.add(Alist.get(3));
        case20.add(Clist.get(2));
        allCases.add(case20);

        for (int i = 0; i <allCases.size()/2; i++) {
            temp.append(generateMultiOrClauses(posB, negC,allCases.get(i), allCases.get(i+1)));
          i++;
        }
        for (int i = allCases.size()/2; i <allCases.size() ; i++) {
            temp.append(generateMultiOrClauses(posB, posC,allCases.get(i), allCases.get(i+1)));
            i++;
        }
        return temp.toString();
    }
    private String generateNegBRestCases(List<List<Atom>> negAtoms, List<List<Atom>> posAtoms) {
        StringBuilder temp = new StringBuilder();
        Atom negB = negAtoms.get(1).get(0);
        Atom negD = negAtoms.get(3).get(0);
        Atom posA =  posAtoms.get(0).get(3);
        Atom negA =  negAtoms.get(0).get(3);

        List<Atom> Alist = Arrays.asList(negAtoms.get(0).get(1),negAtoms.get(0).get(2),negAtoms.get(0).get(3),
                posAtoms.get(0).get(1),posAtoms.get(0).get(2),posAtoms.get(0).get(3));
        List<Atom> Blist = Arrays.asList(negAtoms.get(1).get(2),negAtoms.get(1).get(3),
                posAtoms.get(1).get(2),posAtoms.get(1).get(3));
        List<Atom> Clist = Arrays.asList(negAtoms.get(2).get(1),negAtoms.get(2).get(3));
        List<Atom> Dlist = Arrays.asList(negAtoms.get(3).get(1),negAtoms.get(3).get(2),
               posAtoms.get(3).get(1),posAtoms.get(3).get(2));

        List<Atom> case1 = new ArrayList<>();
        case1.addAll(Alist.subList(0,2));
        case1.add(Alist.get(5));
        case1.add(Blist.get(0));
        case1.add(Blist.get(3));
        case1.add(Dlist.get(0));
        case1.add(Dlist.get(3));

        List<Atom> case2 = new ArrayList<>();
        case2.addAll(Alist.subList(1,4));
        case2.add(Blist.get(1));
        case2.add(Blist.get(2));
        case2.add(Dlist.get(1));
        case2.add(Dlist.get(2));
        List<Atom> case3 = new ArrayList<>();
        case3.add(Blist.get(0));
        case3.add(Blist.get(3));
        case3.addAll(Dlist.subList(1,3));

        List<Atom> case4 = new ArrayList<>();
        case4.addAll(Alist.subList(3,5));
        case4.add(Blist.get(0));
        case4.addAll(Dlist.subList(1,3));

        List<Atom> case5 = new ArrayList<>();
        case5.addAll(Alist.subList(0,2));
        case5.add(Blist.get(0));
        case5.add(posAtoms.get(3).get(0));
        case5.addAll(Dlist.subList(0,2));

        List<Atom> case6 = new ArrayList<>();
        case6.addAll(Alist.subList(0,2));
        case6.add(Blist.get(1));
        case6.addAll(Clist.subList(0,2));
        case6.add(negD);
        case6.addAll(Dlist.subList(0,2));

        List<Atom> case7 = new ArrayList<>();
        case7.add(Alist.get(0));
        case7.add(Alist.get(2));
        case7.add(Alist.get(4));
        case7.addAll(Blist.subList(0,2));
        case7.addAll(Clist.subList(0,2));
        case7.addAll(Dlist.subList(0,2));

        List<Atom> case8 = new ArrayList<>();
        case8.add(Alist.get(0));
        case8.add(Alist.get(2));
        case8.add(Blist.get(0));
        case8.add(Blist.get(3));
        case8.addAll(Clist.subList(0,2));
        case8.add(Dlist.get(2));

        temp.append(generateMultiOrClauses(negB, negD,case1, case2));
        temp.append(generateMultiOrClauses(negB, negD,case3, case4));

        temp.append(generateMultiOrClauses(negB, posA,case5, case6));
        temp.append(generateMultiOrClauses(negB, negA,case7, case8));
        return temp.toString();
    }
    private String generateNegBPosCCases(List<List<Atom>> negAtoms, List<List<Atom>> posAtoms) {
        StringBuilder temp = new StringBuilder();
        Atom negB = negAtoms.get(1).get(0);
        Atom posC = posAtoms.get(2).get(0);

        List<Atom> Alist = Arrays.asList(negAtoms.get(0).get(1),negAtoms.get(0).get(2),negAtoms.get(0).get(3),
                posAtoms.get(0).get(1),posAtoms.get(0).get(2),posAtoms.get(0).get(3));
        List<Atom> Blist = Arrays.asList(negAtoms.get(1).get(2),negAtoms.get(1).get(3),
                posAtoms.get(1).get(2),posAtoms.get(1).get(3));
        List<Atom> Clist = Arrays.asList(negAtoms.get(2).get(1),negAtoms.get(2).get(3),
                posAtoms.get(2).get(1),posAtoms.get(2).get(3));
        List<Atom> Dlist = Arrays.asList(negAtoms.get(3).get(0),negAtoms.get(3).get(1),negAtoms.get(3).get(2),
                posAtoms.get(3).get(0),posAtoms.get(3).get(1),posAtoms.get(3).get(2));

        List<List<Atom>> allCases = new ArrayList<>();

        List<Atom> case1 = new ArrayList<>();
        case1.addAll(Blist.subList(1,3));
        case1.addAll(Clist.subList(0,2));
        case1.addAll(Dlist.subList(0,2));
        case1.add(Dlist.get(5));
        allCases.add(case1);

        List<Atom> case2 = new ArrayList<>();
        case2.addAll(Alist.subList(1,4));
        case2.add(Blist.get(0));
        case2.add(Blist.get(3));
        case2.addAll(Clist.subList(0,2));
        case2.addAll(Dlist.subList(0,2));
        case2.add(Dlist.get(5));
        allCases.add(case2);

        List<Atom> case3 = new ArrayList<>();
        case3.addAll(Alist.subList(0,2));
        case3.add(Blist.get(0));
        case3.add(Clist.get(2));
        case3.addAll(Dlist.subList(0,2));
        case3.add(Dlist.get(5));
        allCases.add(case3);

        List<Atom> case4 = new ArrayList<>();
        case4.add(Alist.get(0));
        case4.add(Alist.get(2));
        case4.addAll(Blist.subList(0,2));
        case4.addAll(Clist.subList(0,2));
        case4.addAll(Dlist.subList(0,2));
        allCases.add(case4);
        List<Atom> case5 = new ArrayList<>();
        case5.add(Alist.get(3));
        case5.add(Alist.get(5));
        case5.add(Blist.get(1));
        case5.addAll(Clist.subList(0,2));
        case5.addAll(Dlist.subList(0,2));
        allCases.add(case5);

        List<Atom> case6 = new ArrayList<>();
        case6.addAll(Alist.subList(1,3));
        case6.addAll(Blist.subList(1,3));
        case6.add(Dlist.get(0));
        case6.add(Dlist.get(2));
        case6.add(Dlist.get(4));
        allCases.add(case6);

        List<Atom> case7 = new ArrayList<>();
        case7.addAll(Alist.subList(1,3));
        case7.addAll(Blist.subList(1,3));
        case7.add(Clist.get(3));
        case7.add(Dlist.get(0));
        case7.add(Dlist.get(2));
        allCases.add(case7);

        List<Atom> case8 = new ArrayList<>();
        case8.add(Blist.get(0));
        case8.add(Blist.get(3));
        case8.add(Clist.get(3));
        case8.add(Dlist.get(0));
        case8.add(Dlist.get(2));
        allCases.add(case8);

        List<Atom> case9 = new ArrayList<>();
        case9.add(Alist.get(4));
        case9.add(Blist.get(0));
        case9.add(Dlist.get(0));
        case9.add(Dlist.get(2));
        allCases.add(case9);

        List<Atom> case10 = new ArrayList<>();
        case10.addAll(Alist.subList(0,2));
        case10.add(Blist.get(0));
        case10.add(Clist.get(2));
        case10.addAll(Dlist.subList(1,4));
        allCases.add(case10);

        List<Atom> case11 = new ArrayList<>();
        case11.add(Alist.get(0));
        case11.add(Alist.get(2));
        case11.add(Blist.get(0));
        case11.addAll(Clist.subList(0,2));
        case11.addAll(Dlist.subList(3,5));
        allCases.add(case11);

        List<Atom> case12 = new ArrayList<>();
        case12.add(Alist.get(0));
        case12.add(Alist.get(2));
        case12.add(Alist.get(4));
        case12.add(Blist.get(0));
        case12.addAll(Clist.subList(0,2));
        allCases.add(case12);

        for (int i = 0; i <allCases.size() ; i++) {
            temp.append(generateMultiOrClauses(negB, posC,allCases.get(i), allCases.get(i+1)));
            i++;
        }
        return temp.toString();
    }
    private String generateNegBNegCCases(List<List<Atom>> negAtoms, List<List<Atom>> posAtoms) {
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


       List <Atom> case1 = new ArrayList<>();
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
        allCases.add(case12);

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

        List <Atom> case15 = new ArrayList<>();
        case15.add(Alist.get(3));
        case15.add(Alist.get(5));
        case15.add(Blist.get(1));
        case15.add(Clist.get(1));
        case15.add(Dlist.get(3));
        allCases.add(case15);

        List <Atom> case16 = new ArrayList<>();
        case16.addAll(Blist.subList(1,3));
        case16.addAll(Clist.subList(1,3));
        case16.add(Dlist.get(5));
        allCases.add(case16);

        List <Atom> case17 = new ArrayList<>();
        case17.add(Alist.get(3));
        case17.addAll(Blist.subList(1,3));
        case17.addAll(Clist.subList(1,3));
        allCases.add(case17);

        List <Atom> case18 = new ArrayList<>();
        case18.add(Alist.get(3));
        case18.add(Alist.get(5));
        case18.add(Blist.get(1));
        case18.addAll(Clist.subList(1,3));
        allCases.add(case18);

        List <Atom> case19 = new ArrayList<>();
        case19.addAll(Alist.subList(1,4));
        case19.add(Blist.get(0));
        case19.add(Blist.get(3));
        case19.addAll(Clist.subList(1,3));
        allCases.add(case19);

        List <Atom> case20 = new ArrayList<>();
        case20.add(Alist.get(0));
        case20.add(Alist.get(2));
        case20.add(Alist.get(4));
        case20.addAll(Blist.subList(1,3));
        case20.add(Clist.get(0));
        allCases.add(case20);
        for (int i = 0; i <allCases.size() ; i++) {
            temp.append(generateMultiOrClauses(negB, negC,allCases.get(i), allCases.get(i+1)));
            i++;
        }
        return temp.toString();
    }


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
}
