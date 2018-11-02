package FeedbackTypes;

import java.util.*;

public class GOOR extends Feedback {
    Set<Clause> clauses = new HashSet<>();
    public GOOR(String guess) {
        super("GOOR", guess);
        translate();
        super.clauses = clauses;
    }

    private void translate() {
        String[] atomString= guess.split(",");
        List<Atom> atomList = new ArrayList<>();
        //Getting the original atom
        for (String s: atomString) {
            Atom a = new Atom(s);
            atomList.add(a);
        }

        List<List<Atom>> posAtoms = new ArrayList<>();
        List<List<Atom>> negAtoms = new ArrayList<>();
        for (Atom a:atomList) {
            posAtoms.add(generateAtoms(a,false));
            negAtoms.add(generateAtoms(a,true));
        }


        StringBuilder res = new StringBuilder();



        res.append(generateNegCzCases(negAtoms,posAtoms));
        res.append(generatePosCzCases(negAtoms,posAtoms));
        res.append(generateNegDxCases(negAtoms,posAtoms));
        res.append(generatePosDxCases(negAtoms,posAtoms));
        res.append(generatePosAyCases(negAtoms,posAtoms));


        super.boolTrans = res.substring(0,res.lastIndexOf("&&"));
    }

    private String generatePosAyCases(List<List<Atom>> negAtoms, List<List<Atom>> posAtoms) {
        StringBuilder temp = new StringBuilder();
        Atom posAy = posAtoms.get(0).get(1);


        List<Atom> Alist = Arrays.asList(posAtoms.get(0).get(0));
        List<Atom> Blist = Arrays.asList(negAtoms.get(1).get(1));
        List<Atom> Clist = Arrays.asList(negAtoms.get(2).get(1),negAtoms.get(2).get(3),
                posAtoms.get(2).get(1),posAtoms.get(2).get(3));
        List<Atom> Dlist = Arrays.asList(negAtoms.get(3).get(3), posAtoms.get(3).get(1));
        List<List<Atom>> allCases = new ArrayList<>();


        List<Atom> case1 = new ArrayList<>();
        case1.add(posAy);
        case1.add(Dlist.get(0));
        allCases.add(case1);
        List<Atom> case2 = new ArrayList<>();
        case2.add(posAy);
        case2.add(Blist.get(0));
        allCases.add(case2);

        List<Atom> case3 = new ArrayList<>();
        case3.add(posAy);
        case3.add(Dlist.get(1));
        allCases.add(case3);

        List<Atom> case4 = new ArrayList<>();
        case4.add(posAy);
        case4.add(Alist.get(0));
        allCases.add(case4);

        List<Atom> case5 = new ArrayList<>();
        case5.add(posAy);
        case5.addAll(Clist.subList(0,2));
        allCases.add(case5);

        List<Atom> case6 = new ArrayList<>();
        case6.add(posAy);
        case6.addAll(Clist.subList(2,4));
        allCases.add(case6);

        for (List<Atom> allCase : allCases) {
            temp.append(generateOrClause(allCase));
        }
        return temp.toString();
    }
    private String generatePosDxCases(List<List<Atom>> negAtoms, List<List<Atom>> posAtoms) {

        StringBuilder temp = new StringBuilder();
        Atom posDx = posAtoms.get(3).get(0);
        Atom negBz = negAtoms.get(1).get(2);
        Atom posBz = posAtoms.get(1).get(2);

        List<Atom> Alist = Arrays.asList(negAtoms.get(0).get(0));
        List<Atom> Blist = Arrays.asList(negAtoms.get(1).get(1), negAtoms.get(1).get(3)
                ,posAtoms.get(1).get(3));
        List<Atom> Clist = Arrays.asList(negAtoms.get(2).get(0), negAtoms.get(2).get(1),negAtoms.get(2).get(3),
                posAtoms.get(2).get(0),posAtoms.get(2).get(1),posAtoms.get(2).get(3));
        List<Atom> Dlist = Arrays.asList(negAtoms.get(3).get(3), posAtoms.get(3).get(1));
        List<List<Atom>> allCases = new ArrayList<>();

        List<Atom> case1 = new ArrayList<>();
        case1.add(Alist.get(0));
        case1.add(Blist.get(1));
        case1.add(Clist.get(3));
        case1.add(Dlist.get(0));
        allCases.add(case1);

        List<Atom> case2 = new ArrayList<>();
        case2.add(Alist.get(0));
        case2.add(Blist.get(2));
        case2.add(Clist.get(0));
        case2.add(Dlist.get(0));
        allCases.add(case2);


        List<Atom> case3 = new ArrayList<>();
        case3.addAll(Blist.subList(0,2));
        case3.add(Clist.get(3));
        allCases.add(case3);

        List<Atom> case4 = new ArrayList<>();
        case4.add(Blist.get(0));
        case4.add(Blist.get(2));
        case4.add(Clist.get(0));
        allCases.add(case4);

        List<Atom> case5 = new ArrayList<>();
        case5.add(Alist.get(0));
        case5.add(Blist.get(1));
        case5.add(Clist.get(3));
        case5.add(Dlist.get(1));
        allCases.add(case5);

        List<Atom> case6 = new ArrayList<>();
        case6.add(Alist.get(0));
        case6.add(Blist.get(2));
        case6.add(Clist.get(0));
        case6.add(Dlist.get(1));
        allCases.add(case6);

        List<Atom> case7 = new ArrayList<>();
        case7.add(Alist.get(0));
        case7.add(Blist.get(1));
        case7.addAll(Clist.subList(1,4));
        allCases.add(case7);

        List<Atom> case8 = new ArrayList<>();
        case8.add(Alist.get(0));
        case8.add(Blist.get(2));
        case8.addAll(Clist.subList(0,3));
        allCases.add(case8);

        List<Atom> case9 = new ArrayList<>();
        case9.add(Alist.get(0));
        case9.add(Blist.get(1));
        case9.addAll(Clist.subList(3,6));
        allCases.add(case9);

        List<Atom> case10 = new ArrayList<>();
        case10.add(Alist.get(0));
        case10.add(Blist.get(2));
        case10.add(Clist.get(0));
        case10.addAll(Clist.subList(4,6));
        allCases.add(case10);

        List<Atom> case11 = new ArrayList<>();
        case11.add(Alist.get(0));
        case11.add(Blist.get(1));
        case11.add(Clist.get(0));
        case11.add(Dlist.get(0));
        allCases.add(case11);

        List<Atom> case12 = new ArrayList<>();
        case12.add(Alist.get(0));
        case12.add(Blist.get(2));
        case12.add(Clist.get(3));
        case12.add(Dlist.get(0));
        allCases.add(case12);

        List<Atom> case13 = new ArrayList<>();
        case13.addAll(Blist.subList(0,2));
        case13.add(Clist.get(0));
        allCases.add(case13);

        List<Atom> case14 = new ArrayList<>();
        case14.add(Blist.get(0));
        case14.add(Blist.get(2));
        case14.add(Clist.get(3));
        allCases.add(case14);

        List<Atom> case15 = new ArrayList<>();
        case15.add(Alist.get(0));
        case15.add(Blist.get(1));
        case15.add(Clist.get(0));
        case15.add(Dlist.get(1));
        allCases.add(case15);

        List<Atom> case16 = new ArrayList<>();
        case16.add(Alist.get(0));
        case16.add(Blist.get(2));
        case16.add(Clist.get(3));
        case16.add(Dlist.get(1));
        allCases.add(case16);

        List<Atom> case17 = new ArrayList<>();
        case17.add(Alist.get(0));
        case17.add(Blist.get(1));
        case17.addAll(Clist.subList(0,3));
        allCases.add(case17);

        List<Atom> case18 = new ArrayList<>();
        case18.add(Alist.get(0));
        case18.add(Blist.get(2));
        case18.addAll(Clist.subList(1,4));
        allCases.add(case18);

        List<Atom> case19 = new ArrayList<>();
        case19.add(Alist.get(0));
        case19.add(Blist.get(1));
        case19.add(Clist.get(0));
        case19.addAll(Clist.subList(4,6));
        allCases.add(case19);

        List<Atom> case20 = new ArrayList<>();
        case20.add(Alist.get(0));
        case20.add(Blist.get(2));
        case20.addAll(Clist.subList(3,6));
        allCases.add(case20);

        for (int i = 0; i < 10; i++) {
            temp.append(generateMultiOrClauses(posDx, negBz, allCases.get(i), allCases.get(i + 1)));
            i++;
        }
        for (int i = 10; i < allCases.size(); i++) {
            temp.append(generateMultiOrClauses(posDx, posBz, allCases.get(i), allCases.get(i + 1)));
            i++;
        }
        return temp.toString();
    }

    private String generateNegDxCases(List<List<Atom>> negAtoms, List<List<Atom>> posAtoms) {

        StringBuilder temp = new StringBuilder();
        Atom negDx = negAtoms.get(3).get(0);
        Atom negBz = negAtoms.get(1).get(2);
        Atom posBz = posAtoms.get(1).get(2);

        List<Atom> Alist = Arrays.asList(negAtoms.get(0).get(0));
        List<Atom> Blist = Arrays.asList(negAtoms.get(1).get(1), negAtoms.get(1).get(3)
                ,posAtoms.get(1).get(3));
        List<Atom> Clist = Arrays.asList(negAtoms.get(2).get(0), negAtoms.get(2).get(1),negAtoms.get(2).get(3),
                posAtoms.get(2).get(0),posAtoms.get(2).get(1),posAtoms.get(2).get(3));
        List<Atom> Dlist = Arrays.asList(negAtoms.get(3).get(3), posAtoms.get(3).get(1));
        List<List<Atom>> allCases = new ArrayList<>();

        List<Atom> case1 = new ArrayList<>();
        case1.add(Alist.get(0));
        case1.add(Blist.get(1));
        case1.add(Clist.get(0));
        case1.add(Dlist.get(0));
        allCases.add(case1);

        List<Atom> case2 = new ArrayList<>();
        case2.add(Alist.get(0));
        case2.add(Blist.get(2));
        case2.add(Clist.get(3));
        case2.add(Dlist.get(0));
        allCases.add(case2);

        List<Atom> case3 = new ArrayList<>();
        case3.addAll(Blist.subList(0,2));
        case3.add(Clist.get(0));
        allCases.add(case3);

        List<Atom> case4 = new ArrayList<>();
        case4.add(Blist.get(0));
        case4.add(Blist.get(2));
        case4.add(Clist.get(3));
        allCases.add(case4);

        List<Atom> case5 = new ArrayList<>();
        case5.add(Alist.get(0));
        case5.add(Blist.get(1));
        case5.add(Clist.get(0));
        case5.add(Dlist.get(1));
        allCases.add(case5);

        List<Atom> case6 = new ArrayList<>();
        case6.add(Alist.get(0));
        case6.add(Blist.get(2));
        case6.add(Clist.get(3));
        case6.add(Dlist.get(1));
        allCases.add(case6);

        List<Atom> case7 = new ArrayList<>();
        case7.add(Alist.get(0));
        case7.add(Blist.get(1));
        case7.addAll(Clist.subList(0,3));
        allCases.add(case7);

        List<Atom> case8 = new ArrayList<>();
        case8.add(Alist.get(0));
        case8.add(Blist.get(2));
        case8.addAll(Clist.subList(1,4));
        allCases.add(case8);

        List<Atom> case9 = new ArrayList<>();
        case9.add(Alist.get(0));
        case9.add(Blist.get(1));
        case9.add(Clist.get(0));
        case9.addAll(Clist.subList(4,6));
        allCases.add(case9);

        List<Atom> case10 = new ArrayList<>();
        case10.add(Alist.get(0));
        case10.add(Blist.get(2));
        case10.addAll(Clist.subList(3,6));
        allCases.add(case10);

        List<Atom> case11 = new ArrayList<>();
        case11.add(Alist.get(0));
        case11.add(Blist.get(1));
        case11.add(Clist.get(3));
        case11.add(Dlist.get(0));
        allCases.add(case11);

        List<Atom> case12 = new ArrayList<>();
        case12.add(Alist.get(0));
        case12.add(Blist.get(2));
        case12.add(Clist.get(0));
        case12.add(Dlist.get(0));
        allCases.add(case12);

        List<Atom> case13 = new ArrayList<>();
        case13.addAll(Blist.subList(0,2));
        case13.add(Clist.get(3));
        allCases.add(case13);

        List<Atom> case14 = new ArrayList<>();
        case14.add(Blist.get(0));
        case14.add(Blist.get(2));
        case14.add(Clist.get(0));
        allCases.add(case14);
        List<Atom> case15 = new ArrayList<>();
        case15.add(Alist.get(0));
        case15.add(Blist.get(1));
        case15.add(Clist.get(3));
        case15.add(Dlist.get(1));
        allCases.add(case15);

        List<Atom> case16 = new ArrayList<>();
        case16.add(Alist.get(0));
        case16.add(Blist.get(2));
        case16.add(Clist.get(0));
        case16.add(Dlist.get(1));
        allCases.add(case16);

        List<Atom> case17 = new ArrayList<>();
        case17.add(Alist.get(0));
        case17.add(Blist.get(1));
        case17.addAll(Clist.subList(1,4));
        allCases.add(case17);

        List<Atom> case18 = new ArrayList<>();
        case18.add(Alist.get(0));
        case18.add(Blist.get(2));
        case18.addAll(Clist.subList(0,3));
        allCases.add(case18);

        List<Atom> case19 = new ArrayList<>();
        case19.add(Alist.get(0));
        case19.add(Blist.get(1));
        case19.addAll(Clist.subList(3,6));
        allCases.add(case19);

        List<Atom> case20 = new ArrayList<>();
        case20.add(Alist.get(0));
        case20.add(Blist.get(2));
        case20.add(Clist.get(0));
        case20.addAll(Clist.subList(4,6));
        allCases.add(case20);

        for (int i = 0; i < 10; i++) {
            temp.append(generateMultiOrClauses(negDx, negBz, allCases.get(i), allCases.get(i + 1)));
            i++;
        }
        for (int i = 10; i < allCases.size(); i++) {
            temp.append(generateMultiOrClauses(negDx, posBz, allCases.get(i), allCases.get(i + 1)));
            i++;
        }
        return temp.toString();
    }


    private String generatePosCzCases(List<List<Atom>> negAtoms, List<List<Atom>> posAtoms) {
        StringBuilder temp = new StringBuilder();
        Atom posCz = posAtoms.get(2).get(2);
        Atom negDx = negAtoms.get(3).get(0);
        Atom posDx = posAtoms.get(3).get(0);

        List<Atom> Alist = Arrays.asList(negAtoms.get(0).get(0),negAtoms.get(0).get(1),
                posAtoms.get(0).get(0));
        List<Atom> Blist = Arrays.asList(negAtoms.get(1).get(0), negAtoms.get(1).get(2),
                negAtoms.get(1).get(3), posAtoms.get(1).get(0),posAtoms.get(1).get(1),
                posAtoms.get(1).get(2),posAtoms.get(1).get(3));
        List<Atom> Clist = Arrays.asList(negAtoms.get(2).get(0), negAtoms.get(2).get(1),negAtoms.get(2).get(3),
                posAtoms.get(2).get(0),posAtoms.get(2).get(1),posAtoms.get(2).get(3));
        List<Atom> Dlist = Arrays.asList(negAtoms.get(3).get(1),negAtoms.get(3).get(3),
                 posAtoms.get(3).get(3));
        List<List<Atom>> allCases = new ArrayList<>();

        List<Atom> case1 = new ArrayList<>();
        case1.add(Alist.get(2));
        case1.add(Blist.get(1));
        case1.addAll(Blist.subList(3,5));
        case1.add(Dlist.get(1));
        allCases.add(case1);
        List<Atom> case2 = new ArrayList<>();
        case2.add(Alist.get(2));
        case2.add(Blist.get(0));
        case2.addAll(Blist.subList(4,6));
        case2.add(Dlist.get(1));
        allCases.add(case2);

        List<Atom> case3 = new ArrayList<>();
        case3.addAll(Alist.subList(0,2));
        case3.addAll(Blist.subList(1,3));
        case3.add(Blist.get(4));
        case3.add(Clist.get(1));
        case3.add(Clist.get(3));
        case3.add(Clist.get(5));
        case3.add(Dlist.get(0));
        case3.add(Dlist.get(2));
        allCases.add(case3);
        List<Atom> case4 = new ArrayList<>();
        case4.addAll(Alist.subList(0,2));
        case4.add(Blist.get(1));
        case4.add(Blist.get(4));
        case4.add(Blist.get(6));
        case4.addAll(Clist.subList(0,2));
        case4.add(Clist.get(5));
        case4.add(Dlist.get(0));
        case4.add(Dlist.get(2));
        allCases.add(case4);

        List<Atom> case5 = new ArrayList<>();
        case5.addAll(Alist.subList(0,2));
        case5.addAll(Blist.subList(1,3));
        case5.add(Blist.get(4));
        case5.add(Clist.get(2));
        case5.addAll(Clist.subList(3,5));
        case5.add(Dlist.get(0));
        case5.add(Dlist.get(2));
        allCases.add(case5);
        List<Atom> case6 = new ArrayList<>();
        case6.addAll(Alist.subList(0,2));
        case6.add(Blist.get(1));
        case6.add(Blist.get(4));
        case6.add(Blist.get(6));
        case6.add(Clist.get(0));
        case6.add(Clist.get(2));
        case6.add(Clist.get(4));
        case6.add(Dlist.get(0));
        case6.add(Dlist.get(2));
        allCases.add(case6);
        List<Atom> case7 = new ArrayList<>();
        case7.addAll(Alist.subList(0,2));
        case7.add(Blist.get(2));
        case7.addAll(Blist.subList(3,5));
        case7.addAll(Clist.subList(0,2));
        case7.add(Clist.get(5));
        case7.add(Dlist.get(0));
        case7.add(Dlist.get(2));
        allCases.add(case7);

        List<Atom> case8 = new ArrayList<>();
        case8.addAll(Alist.subList(0,2));
        case8.addAll(Blist.subList(4,7));
        case8.add(Clist.get(1));
        case8.add(Clist.get(3));
        case8.add(Clist.get(5));
        case8.add(Dlist.get(0));
        case8.add(Dlist.get(2));
        allCases.add(case8);
        List<Atom> case9 = new ArrayList<>();
        case9.addAll(Alist.subList(0,2));
        case9.add(Blist.get(2));
        case9.addAll(Blist.subList(4,6));
        case9.add(Clist.get(0));
        case9.add(Clist.get(2));
        case9.add(Clist.get(4));
        case9.add(Dlist.get(0));
        case9.add(Dlist.get(2));
        allCases.add(case9);

        List<Atom> case10 = new ArrayList<>();
        case10.addAll(Alist.subList(0,2));
        case10.addAll(Blist.subList(4,7));
        case10.addAll(Clist.subList(2,5));
        case10.add(Dlist.get(0));
        case10.add(Dlist.get(2));
        allCases.add(case10);
        List<Atom> case11 = new ArrayList<>();
        case11.add(Alist.get(2));
        case11.addAll(Blist.subList(1,3));
        case11.add(Clist.get(0));
        case11.add(Dlist.get(2));
        allCases.add(case11);

        List<Atom> case12 = new ArrayList<>();
        case12.add(Alist.get(2));
        case12.add(Blist.get(1));
        case12.add(Blist.get(6));
        case12.add(Clist.get(3));
        case12.add(Dlist.get(2));
        allCases.add(case12);

        List<Atom> case13 = new ArrayList<>();
        case13.add(Alist.get(2));
        case13.add(Blist.get(2));
        case13.add(Blist.get(5));
        case13.add(Clist.get(3));
        case13.add(Dlist.get(2));
        allCases.add(case13);

        List<Atom> case14 = new ArrayList<>();
        case14.add(Alist.get(2));
        case14.add(Blist.get(5));
        case14.add(Blist.get(6));
        case14.add(Clist.get(0));
        case14.add(Dlist.get(2));
        allCases.add(case14);

        List<Atom> case15 = new ArrayList<>();
        case15.add(Alist.get(2));
        case15.addAll(Blist.subList(0,2));
        case15.add(Blist.get(4));
        case15.add(Dlist.get(1));
        allCases.add(case15);

        List<Atom> case16 = new ArrayList<>();
        case16.add(Alist.get(2));
        case16.addAll(Blist.subList(3,6));
        case16.add(Dlist.get(1));
        allCases.add(case16);

        List<Atom> case17 = new ArrayList<>();
        case17.addAll(Alist.subList(0,2));
        case17.add(Blist.get(1));
        case17.add(Blist.get(2));
        case17.add(Blist.get(4));
        case17.addAll(Clist.subList(0,2));
        case17.add(Clist.get(5));
        case17.add(Dlist.get(0));
        case17.add(Dlist.get(2));
        allCases.add(case17);

        List<Atom> case18 = new ArrayList<>();
        case18.addAll(Alist.subList(0,2));
        case18.add(Blist.get(1));
        case18.add(Blist.get(4));
        case18.add(Blist.get(6));
        case18.add(Clist.get(1));
        case18.add(Clist.get(3));
        case18.add(Clist.get(5));
        case18.add(Dlist.get(0));
        case18.add(Dlist.get(2));
        allCases.add(case18);
        List<Atom> case19 = new ArrayList<>();
        case19.addAll(Alist.subList(0,2));
        case19.addAll(Blist.subList(1,3));
        case19.add(Blist.get(4));
        case19.add(Clist.get(0));
        case19.add(Clist.get(2));
        case19.add(Clist.get(4));
        case19.add(Dlist.get(0));
        case19.add(Dlist.get(2));
        allCases.add(case19);

        List<Atom> case20 = new ArrayList<>();
        case20.addAll(Alist.subList(0,2));
        case20.add(Blist.get(1));
        case20.add(Blist.get(4));
        case20.add(Blist.get(6));
        case20.addAll(Clist.subList(2,5));
        case20.add(Dlist.get(0));
        case20.add(Dlist.get(2));
        allCases.add(case20);
        List<Atom> case21 = new ArrayList<>();
        case21.addAll(Alist.subList(0,2));
        case21.add(Blist.get(2));
        case21.addAll(Blist.subList(4,6));
        case21.add(Clist.get(1));
        case21.add(Clist.get(3));
        case21.add(Clist.get(5));
        case21.add(Dlist.get(0));
        case21.add(Dlist.get(2));
        allCases.add(case21);

        List<Atom> case22 = new ArrayList<>();
        case22.addAll(Alist.subList(0,2));
        case22.addAll(Blist.subList(4,7));
        case22.addAll(Clist.subList(0,2));
        case22.add(Clist.get(5));
        case22.add(Dlist.get(0));
        case22.add(Dlist.get(2));
        allCases.add(case22);
        List<Atom> case23 = new ArrayList<>();
        case23.addAll(Alist.subList(0,2));
        case23.add(Blist.get(2));
        case23.addAll(Blist.subList(4,6));
        case23.addAll(Clist.subList(2,5));
        case23.add(Dlist.get(0));
        case23.add(Dlist.get(2));
        allCases.add(case23);

        List<Atom> case24 = new ArrayList<>();
        case24.addAll(Alist.subList(0,2));
        case24.addAll(Blist.subList(4,7));
        case24.add(Clist.get(0));
        case24.add(Clist.get(2));
        case24.add(Clist.get(4));
        case24.add(Dlist.get(0));
        case24.add(Dlist.get(2));
        allCases.add(case24);

        List<Atom> case25 = new ArrayList<>();
        case25.add(Alist.get(2));
        case25.addAll(Blist.subList(1,3));
        case25.add(Clist.get(3));
        case25.add(Dlist.get(2));
        allCases.add(case25);

        List<Atom> case26 = new ArrayList<>();
        case26.add(Alist.get(2));
        case26.add(Blist.get(1));
        case26.add(Blist.get(6));
        case26.add(Clist.get(0));
        case26.add(Dlist.get(2));
        allCases.add(case26);

        List<Atom> case27 = new ArrayList<>();
        case27.add(Alist.get(2));
        case27.add(Blist.get(2));
        case27.add(Blist.get(5));
        case27.add(Clist.get(0));
        case27.add(Dlist.get(2));
        allCases.add(case27);

        List<Atom> case28 = new ArrayList<>();
        case28.add(Alist.get(2));
        case28.addAll(Blist.subList(5,7));
        case28.add(Clist.get(3));
        case28.add(Dlist.get(2));
        allCases.add(case28);

        for (int i = 0; i < 14; i++) {
            temp.append(generateMultiOrClauses(posCz, negDx, allCases.get(i), allCases.get(i + 1)));
            i++;
        }
        for (int i = 14; i < allCases.size(); i++) {
            temp.append(generateMultiOrClauses(posCz, posDx, allCases.get(i), allCases.get(i + 1)));
            i++;
        }
        return temp.toString();
    }
    private String generateNegCzCases(List<List<Atom>> negAtoms, List<List<Atom>> posAtoms) {
        StringBuilder temp = new StringBuilder();
        Atom negCz = negAtoms.get(2).get(2);
        Atom posBy = posAtoms.get(1).get(1);
        Atom posBx = posAtoms.get(1).get(0);

        List<Atom> Alist = Arrays.asList(negAtoms.get(0).get(0), posAtoms.get(0).get(0),
                posAtoms.get(0).get(1));
        List<Atom> Blist = Arrays.asList(negAtoms.get(1).get(0), negAtoms.get(1).get(2),
                negAtoms.get(1).get(3), posAtoms.get(1).get(2),posAtoms.get(1).get(3));
        List<Atom> Clist = Arrays.asList(negAtoms.get(2).get(0), posAtoms.get(2).get(0));
        List<Atom> Dlist = Arrays.asList(negAtoms.get(3).get(0),negAtoms.get(3).get(3),
                posAtoms.get(3).get(0), posAtoms.get(3).get(3));
        List<List<Atom>> allCases = new ArrayList<>();

        List<Atom> case1 = new ArrayList<>();
        case1.addAll(Blist.subList(1,3));
        case1.add(Clist.get(0));
        case1.add(Dlist.get(1));
        allCases.add(case1);


        List<Atom> case2 = new ArrayList<>();
        case2.add(Blist.get(1));
        case2.add(Blist.get(4));
        case2.add(Clist.get(1));
        case2.add(Dlist.get(1));
        allCases.add(case2);


        List<Atom> case3 = new ArrayList<>();
        case3.addAll(Blist.subList(2,4));
        case3.add(Clist.get(0));
        case3.add(Dlist.get(1));
        allCases.add(case3);

        List<Atom> case4 = new ArrayList<>();
        case4.addAll(Blist.subList(3,5));
        case4.add(Clist.get(0));
        case4.add(Dlist.get(1));
        allCases.add(case4);

        List<Atom> case5 = new ArrayList<>();
        case5.add(Alist.get(0));
        case5.addAll(Blist.subList(1,3));
        case5.add(Clist.get(0));
        allCases.add(case5);

        List<Atom> case6 = new ArrayList<>();
        case6.add(Alist.get(0));
        case6.add(Blist.get(1));
        case6.add(Blist.get(4));
        case6.add(Clist.get(1));
        allCases.add(case6);

        List<Atom> case7 = new ArrayList<>();
        case7.add(Alist.get(0));
        case7.addAll(Blist.subList(2,4));
        case7.add(Clist.get(1));
        allCases.add(case7);

        List<Atom> case8 = new ArrayList<>();
        case8.add(Alist.get(0));
        case8.addAll(Blist.subList(3,5));
        case8.add(Clist.get(0));
        allCases.add(case8);

        List<Atom> case9 = new ArrayList<>();
        case9.addAll(Blist.subList(1,3));
        case9.add(Clist.get(1));
        case9.add(Dlist.get(1));
        allCases.add(case9);

        List<Atom> case10 = new ArrayList<>();
        case10.add(Blist.get(1));
        case10.add(Blist.get(4));
        case10.add(Clist.get(0));
        case10.add(Dlist.get(1));
        allCases.add(case10);

        List<Atom> case11 = new ArrayList<>();
        case11.addAll(Blist.subList(2,4));
        case11.add(Clist.get(0));
        case11.add(Dlist.get(1));
        allCases.add(case11);

        List<Atom> case12 = new ArrayList<>();
        case12.addAll(Blist.subList(3,5));
        case12.add(Clist.get(1));
        case12.add(Dlist.get(1));
        allCases.add(case12);

        List<Atom> case13 = new ArrayList<>();
        case13.add(Alist.get(0));
        case13.addAll(Blist.subList(1,3));
        case13.add(Clist.get(1));
        allCases.add(case13);

        List<Atom> case14 = new ArrayList<>();
        case14.add(Alist.get(0));
        case14.add(Blist.get(1));
        case14.add(Blist.get(4));
        case14.add(Clist.get(0));
        allCases.add(case14);
        List<Atom> case15 = new ArrayList<>();
        case15.add(Alist.get(0));
        case15.add(Blist.get(2));
        case15.add(Blist.get(3));
        case15.add(Clist.get(0));
        allCases.add(case15);

        List<Atom> case16 = new ArrayList<>();
        case16.add(Alist.get(0));
        case16.add(Blist.get(3));
        case16.add(Blist.get(4));
        case16.add(Clist.get(1));
        allCases.add(case16);

        List<Atom> case17 = new ArrayList<>();
        case17.add(Alist.get(1));
        case17.addAll(Blist.subList(0,2));
        case17.add(posBy);
        case17.add(Clist.get(1));
        allCases.add(case17);

        List<Atom> case18 = new ArrayList<>();
        case18.add(Alist.get(1));
        case18.add(Blist.get(1));
        case18.add(posBx);
        case18.add(posBy);
        case18.add(Clist.get(0));
        allCases.add(case18);

        List<Atom> case19 = new ArrayList<>();
        case19.add(Alist.get(1));
        case19.add(Blist.get(0));
        case19.add(posBy);
        case19.add(Blist.get(3));
        case19.add(Clist.get(0));
        allCases.add(case19);

        List<Atom> case20 = new ArrayList<>();
        case20.add(Alist.get(1));
        case20.add(posBx);
        case20.add(posBy);
        case20.add(Blist.get(3));
        case20.add(Clist.get(1));
        allCases.add(case20);

        List<Atom> case21 = new ArrayList<>();
        case21.add(negCz);
        case21.add(Alist.get(2));

        for (int i = 0; i < 8; i++) {
            temp.append(generateMultiOrClauses(negCz, Dlist.get(0), allCases.get(i), allCases.get(i + 1)));
            i++;
        }
        for (int i = 8; i < 16; i++) {
            temp.append(generateMultiOrClauses(negCz, Dlist.get(2), allCases.get(i), allCases.get(i + 1)));
            i++;
        }
        for (int i = 16; i < 20; i++) {
            temp.append(generateMultiOrClauses(negCz, Dlist.get(3), allCases.get(i), allCases.get(i + 1)));
            i++;
        }
        temp.append(generateOrClause(case21));
        return temp.toString();
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

    private void addClause(Atom a, Atom b, Atom c){
        Set<Atom> atomSet = new HashSet<>();
        atomSet.add(a);
        atomSet.add(b);
        atomSet.add(c);
        clauses.add(new Clause(atomSet));
    }

    @Override
    public int noXOR() {
        return 74;
    }


}
