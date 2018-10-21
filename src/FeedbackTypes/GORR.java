package FeedbackTypes;

import java.util.*;

public class GORR extends Feedback {
    Set<Clause> clauses = new HashSet<>();
    public GORR(String guess) {
        super("GORR", guess);
        translate();
        super.clauses = clauses;
    }
    @Override
    public int noXOR() {
        return 0;
    }



    private void translate(){
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

        String clauseString = generateNegAClauses(allPosAtomsList,  allNegAtomsList);
         clauseString += generatePosAClauses(allPosAtomsList,  allNegAtomsList);
       clauseString += generateNegBClauses(allPosAtomsList,  allNegAtomsList);
        clauseString += generatePosBClauses(allPosAtomsList,  allNegAtomsList);
         clauseString += generateNegCClauses(allPosAtomsList,  allNegAtomsList);
         clauseString += generatePosCClauses(allPosAtomsList,  allNegAtomsList);


        Atom negD = allNegAtomsList.get(3).get(3);
        Atom negA = allNegAtomsList.get(0).get(0);
        Atom negB = allNegAtomsList.get(1).get(1);
        Atom negC =allNegAtomsList.get(2).get(2);

        String singleOrClauses = generateSingleOrClauses(negD,negC);
        singleOrClauses += generateSingleOrClauses(negD,negB);
        singleOrClauses += generateSingleOrClauses(negD,negA);
        singleOrClauses += generateSingleOrClauses(negB,negC);
        singleOrClauses += generateSingleOrClauses(negA,negC);
        singleOrClauses += generateSingleOrClauses(negA,negB);

        String multOr = generateOrClause(posAtoms);

    super.boolTrans = clauseString +singleOrClauses+ multOr.substring(0,multOr.lastIndexOf("&&"));

    }


    private String generateNegCClauses(List<List<Atom>> posAtoms, List<List<Atom>> negAtoms) {
        StringBuilder res = new StringBuilder();
        Atom negC = negAtoms.get(2).get(0);
        Atom negB = negAtoms.get(1).get(1);
        List<Atom> Alist = Arrays.asList(negAtoms.get(0).get(2),negAtoms.get(0).get(3),
                posAtoms.get(0).get(2),posAtoms.get(0).get(3));
        List<Atom> Clist = Arrays.asList(negAtoms.get(2).get(3),posAtoms.get(2).get(3));
        List<Atom> Dlist = Arrays.asList(negAtoms.get(3).get(0), negAtoms.get(3).get(2),
                posAtoms.get(3).get(0),posAtoms.get(3).get(2));
        List<List<Atom>> allCases = new ArrayList<>();

        List<Atom> case1 = new ArrayList<>();
        case1.addAll(Dlist.subList(0,2));
        case1.add(Clist.get(0));
        case1.addAll(Alist.subList(0,2));
        allCases.add(case1);


        List<Atom> case2 = new ArrayList<>();
        case2.addAll(Dlist.subList(0,2));
        case2.add(Clist.get(0));
        case2.addAll(Alist.subList(2,4));
        allCases.add(case2);

        List<Atom> case3 = new ArrayList<>();
        case3.addAll(Dlist.subList(0,2));
        case3.add(Clist.get(1));
        case3.addAll(Alist.subList(1,3));
       allCases.add(case3);


        List<Atom> case4 = new ArrayList<>();
        case4.addAll(Dlist.subList(0,2));
        case4.add(Clist.get(1));
        case4.add(Alist.get(0));
        case4.add(Alist.get(3));
        allCases.add(case4);

        List<Atom> case5 = new ArrayList<>();
        case5.add(Dlist.get(0));
        case5.add(Dlist.get(3));
        case5.add(Clist.get(0));
        case5.addAll(Alist.subList(1,3));
        allCases.add(case5);


        List<Atom> case6 = new ArrayList<>();
        case6.add(Dlist.get(0));
        case6.add(Dlist.get(3));
        case6.add(Clist.get(0));
        case6.add(Alist.get(0));
        case6.add(Alist.get(3));
        allCases.add(case6);

        List<Atom> case7 = new ArrayList<>();
        case7.add(Dlist.get(0));
        case7.add(Dlist.get(3));
        case7.add(Clist.get(1));
        case7.addAll(Alist.subList(0,2));
         allCases.add(case7);


        List<Atom> case8 = new ArrayList<>();
        case8.add(Dlist.get(0));
        case8.add(Dlist.get(3));
        case8.add(Clist.get(1));
        case8.addAll(Alist.subList(2,4));
         allCases.add(case8);

        List<Atom> case9 = new ArrayList<>();
        case9.addAll(Dlist.subList(1,3));
        case9.add(Clist.get(0));
        case9.addAll(Alist.subList(1,3));
        allCases.add(case9);


        List<Atom> case10 = new ArrayList<>();
        case10.addAll(Dlist.subList(1,3));
        case10.add(Clist.get(0));
        case10.add(Alist.get(0));
        case10.add(Alist.get(3));
        allCases.add(case10);

        List<Atom> case11 = new ArrayList<>();
        case11.addAll(Dlist.subList(1,3));
        case11.add(Clist.get(1));
        case11.addAll(Alist.subList(0,2));
        allCases.add(case11);


        List<Atom> case12 = new ArrayList<>();
        case12.addAll(Dlist.subList(1,3));
        case12.add(Clist.get(1));
        case12.addAll(Alist.subList(2,4));
        allCases.add(case12);

        List<Atom> case13 = new ArrayList<>();
        case13.addAll(Dlist.subList(2,4));
        case13.add(Clist.get(0));
        case13.addAll(Alist.subList(0,2));
       allCases.add(case13);


        List<Atom> case14 = new ArrayList<>();
        case14.addAll(Dlist.subList(2,4));
        case14.add(Clist.get(0));
        case14.addAll(Alist.subList(2,4));
        allCases.add(case14);

        List<Atom> case15 = new ArrayList<>();
        case15.addAll(Dlist.subList(2,4));
        case15.add(Clist.get(1));
        case15.addAll(Alist.subList(1,3));
        allCases.add(case15);


        List<Atom> case16 = new ArrayList<>();
        case16.addAll(Dlist.subList(2,4));
        case16.add(Clist.get(1));
        case16.add(Alist.get(0));
        case16.add(Alist.get(3));
        allCases.add(case16);


        for (int i = 0; i < ((allCases.size())/2) ; i++) {
            res.append(generateMultiOrClauses(negC,negB, allCases.get(i), allCases.get(i + 1)));
        }

        return res.toString();
    }


    private String generatePosCClauses(List<List<Atom>> posAtoms, List<List<Atom>> negAtoms) {
        StringBuilder res = new StringBuilder();
        Atom negB = negAtoms.get(1).get(1);
        Atom posC = posAtoms.get(2).get(0);
        List<Atom> Alist = Arrays.asList(negAtoms.get(0).get(2),negAtoms.get(0).get(3),
                posAtoms.get(0).get(2),posAtoms.get(0).get(3));
        List<Atom> Clist = Arrays.asList(negAtoms.get(2).get(3),posAtoms.get(2).get(3));
        List<Atom> Dlist = Arrays.asList(negAtoms.get(3).get(0), negAtoms.get(3).get(2),
                posAtoms.get(3).get(0),posAtoms.get(3).get(2));
        List<List<Atom>> allCases = new ArrayList<>();

        List<Atom> case1 = new ArrayList<>();
        case1.addAll(Dlist.subList(0,2));
        case1.add(Clist.get(0));
        case1.addAll(Alist.subList(1,3));
        allCases.add(case1);

        List<Atom> case2 = new ArrayList<>();
        case2.addAll(Dlist.subList(0,2));
        case2.add(Clist.get(0));
        case2.add(Alist.get(0));
        case2.add(Alist.get(3));
        allCases.add(case2);

        List<Atom> case3 = new ArrayList<>();
        case3.addAll(Dlist.subList(0,2));
        case3.add(Clist.get(1));
        case3.addAll(Alist.subList(0,2));
        allCases.add(case3);

        List<Atom> case4 = new ArrayList<>();
        case4.addAll(Dlist.subList(0,2));
        case4.add(Clist.get(1));
        case4.addAll(Alist.subList(2,4));
        allCases.add(case4);

        List<Atom> case5 = new ArrayList<>();
        case5.add(Dlist.get(0));
        case5.add(Dlist.get(3));
        case5.add(Clist.get(0));
        case5.addAll(Alist.subList(0,2));
        allCases.add(case5);

        List<Atom> case6 = new ArrayList<>();
        case6.add(Dlist.get(0));
        case6.add(Dlist.get(3));
        case6.add(Clist.get(0));
        case6.addAll(Alist.subList(2,4));
        allCases.add(case6);

        List<Atom> case7 = new ArrayList<>();
        case7.add(Dlist.get(0));
        case7.add(Dlist.get(3));
        case7.add(Clist.get(1));
        case7.addAll(Alist.subList(1,3));
        allCases.add(case7);

        List<Atom> case8 = new ArrayList<>();
        case8.add(Dlist.get(0));
        case8.add(Dlist.get(3));
        case8.add(Clist.get(1));
        case8.add(Alist.get(0));
        case8.add(Alist.get(3));
        allCases.add(case8);

        List<Atom> case9 = new ArrayList<>();
        case9.addAll(Dlist.subList(1,3));
        case9.add(Clist.get(0));
        case9.addAll(Alist.subList(0,2));
        allCases.add(case9);

        List<Atom> case10 = new ArrayList<>();
        case10.addAll(Dlist.subList(1,3));
        case10.add(Clist.get(0));
        case10.addAll(Alist.subList(2,4));
        allCases.add(case10);

        List<Atom> case11 = new ArrayList<>();
        case11.add(Dlist.get(1));
        case11.add(Dlist.get(2));
        case11.add(Clist.get(1));
        case11.addAll(Alist.subList(1,3));
       allCases.add(case11);

        List<Atom> case12 = new ArrayList<>();
        case12.add(Dlist.get(1));
        case12.add(Dlist.get(2));
        case12.add(Clist.get(1));
        case12.add(Alist.get(0));
        case12.add(Alist.get(3));
        allCases.add(case12);

        List<Atom> case13 = new ArrayList<>();
        case13.addAll(Dlist.subList(2,4));
        case13.add(Clist.get(0));
        case13.addAll(Alist.subList(1,3));
        allCases.add(case13);

        List<Atom> case14 = new ArrayList<>();
        case14.addAll(Dlist.subList(2,4));
        case14.add(Clist.get(0));
        case14.add(Alist.get(0));
        case14.add(Alist.get(3));
       allCases.add(case14);

        List<Atom> case15 = new ArrayList<>();
        case15.addAll(Dlist.subList(2,4));
        case15.add(Clist.get(1));
        case15.addAll(Alist.subList(0,2));
        allCases.add(case15);

        List<Atom> case16 = new ArrayList<>();
        case16.addAll(Dlist.subList(2,4));
        case16.add(Clist.get(1));
        case16.addAll(Alist.subList(2,4));
        allCases.add(case16);

        for (int i = 0; i < ((allCases.size())/2) ; i++) {
            res.append(generateMultiOrClauses(posC, negB, allCases.get(i), allCases.get(i + 1)));
        }

        return res.toString();
    }


    private String generatePosBClauses(List<List<Atom>>  posAtoms, List<List<Atom>> negAtoms) {
        StringBuilder res = new StringBuilder();
        Atom posB = posAtoms.get(1).get(0);
        Atom negC = negAtoms.get(2).get(2);
        List<Atom> Alist = Arrays.asList(negAtoms.get(0).get(1),negAtoms.get(0).get(3),
                posAtoms.get(0).get(1),posAtoms.get(0).get(3));
        List<Atom> Blist = Arrays.asList(negAtoms.get(1).get(3),posAtoms.get(1).get(3));
        List<Atom> Dlist = Arrays.asList(negAtoms.get(3).get(0), negAtoms.get(3).get(1),
                posAtoms.get(3).get(0),posAtoms.get(3).get(1));
        List<List<Atom>> allCases = new ArrayList<>();

        List<Atom> case1 = new ArrayList<>();
        case1.addAll(Dlist.subList(0,2));
        case1.add(Blist.get(0));
        case1.addAll(Alist.subList(1,3));
        allCases.add(case1);

        List<Atom> case2 = new ArrayList<>();
        case2.addAll(Dlist.subList(0,2));
        case2.add(Blist.get(0));
        case2.add(Alist.get(0));
        case2.add(Alist.get(3));
        allCases.add(case2);

        List<Atom> case3 = new ArrayList<>();
        case3.addAll(Dlist.subList(0,2));
        case3.add(Blist.get(1));
        case3.addAll(Alist.subList(0,2));
        allCases.add(case3);

        List<Atom> case4 = new ArrayList<>();
        case4.addAll(Dlist.subList(0,2));
        case4.add(Blist.get(1));
        case4.addAll(Alist.subList(2,4));
       allCases.add(case4);

        List<Atom> case5 = new ArrayList<>();
        case5.add(Dlist.get(0));
        case5.add(Dlist.get(3));
        case5.add(Blist.get(0));
        case5.addAll(Alist.subList(0,2));
        allCases.add(case5);

        List<Atom> case6 = new ArrayList<>();
        case6.add(Dlist.get(0));
        case6.add(Dlist.get(3));
        case6.add(Blist.get(0));
        case6.addAll(Alist.subList(2,4));
        allCases.add(case6);

        List<Atom> case7 = new ArrayList<>();
        case7.add(Dlist.get(0));
        case7.add(Dlist.get(3));
        case7.add(Blist.get(1));
        case7.addAll(Alist.subList(1,3));
        allCases.add(case7);

        List<Atom> case8 = new ArrayList<>();
        case8.add(Dlist.get(0));
        case8.add(Dlist.get(3));
        case8.add(Blist.get(1));
        case8.add(Alist.get(0));
        case8.add(Alist.get(3));
        allCases.add(case8);

        List<Atom> case9 = new ArrayList<>();
        case9.addAll(Dlist.subList(1,3));
        case9.add(Blist.get(0));
        case9.addAll(Alist.subList(0,2));
        allCases.add(case9);

        List<Atom> case10 = new ArrayList<>();
        case10.addAll(Dlist.subList(1,3));
        case10.add(Blist.get(0));
        case10.addAll(Alist.subList(2,4));
        allCases.add(case10);

        List<Atom> case11 = new ArrayList<>();
        case11.addAll(Dlist.subList(1,3));
        case11.add(Blist.get(1));
        case11.addAll(Alist.subList(1,3));
        allCases.add(case11);

        List<Atom> case12 = new ArrayList<>();
        case12.addAll(Dlist.subList(1,3));
        case12.add(Blist.get(1));
        case12.add(Alist.get(0));
        case12.add(Alist.get(3));
        allCases.add(case12);

        List<Atom> case13 = new ArrayList<>();
        case13.addAll(Dlist.subList(2,4));
        case13.add(Blist.get(0));
        case13.addAll(Alist.subList(1,3));
        allCases.add(case13);

        List<Atom> case14 = new ArrayList<>();
        case14.addAll(Dlist.subList(2,4));
        case14.add(Blist.get(0));
        case14.add(Alist.get(0));
        case14.add(Alist.get(3));
        allCases.add(case14);

        List<Atom> case15 = new ArrayList<>();
        case15.addAll(Dlist.subList(2,4));
        case15.add(Blist.get(1));
        case15.addAll(Alist.subList(0,2));
        allCases.add(case15);

        List<Atom> case16 = new ArrayList<>();
        case16.addAll(Dlist.subList(2,4));
        case16.add(Blist.get(1));
        case16.addAll(Alist.subList(2,4));
        allCases.add(case16);


        for (int i = 0; i < ((allCases.size())/2) ; i++) {
            res.append(generateMultiOrClauses(posB, negC, allCases.get(i), allCases.get(i + 1)));
        }
        return res.toString();
    }

    private String generateNegBClauses(List<List<Atom>> posAtoms, List<List<Atom>> negAtoms) {
        StringBuilder res = new StringBuilder();
        Atom negB = negAtoms.get(1).get(0);
        Atom negC = negAtoms.get(2).get(2);
        List<Atom> Alist = Arrays.asList(negAtoms.get(0).get(1),negAtoms.get(0).get(3),
                posAtoms.get(0).get(1),posAtoms.get(0).get(3));
        List<Atom> Blist = Arrays.asList(negAtoms.get(1).get(3),posAtoms.get(1).get(3));
        List<Atom> Dlist = Arrays.asList(negAtoms.get(3).get(0), negAtoms.get(3).get(1),
                posAtoms.get(3).get(0),posAtoms.get(3).get(1));
       List<List<Atom>> allCases = new ArrayList<>();


       List<Atom> case1 = new ArrayList<>();
       case1.addAll(Dlist.subList(0,2));
       case1.add(Blist.get(0));
       case1.addAll(Alist.subList(0,2));
       allCases.add(case1);

        List<Atom> case2 = new ArrayList<>();
        case2.addAll(Dlist.subList(0,2));
        case2.add(Blist.get(0));
        case2.addAll(Alist.subList(2,4));
       allCases.add(case2);

        List<Atom> case3 = new ArrayList<>();
        case3.addAll(Dlist.subList(0,2));
        case3.add(Blist.get(1));
        case3.addAll(Alist.subList(1,3));
        allCases.add(case3);

        List<Atom> case4 = new ArrayList<>();
        case4.addAll(Dlist.subList(0,2));
        case4.add(Blist.get(1));
        case4.add(Alist.get(0));
        case4.add(Alist.get(3));
        allCases.add(case4);


        List<Atom> case5 = new ArrayList<>();
        case5.add(Dlist.get(0));
        case5.add(Dlist.get(3));
        case5.add(Blist.get(0));
        case5.addAll(Alist.subList(1,3));
        allCases.add(case5);

        List<Atom> case6 = new ArrayList<>();
        case6.add(Dlist.get(0));
        case6.add(Dlist.get(3));
        case6.add(Blist.get(0));
        case6.add(Alist.get(0));
        case6.add(Alist.get(3));
       allCases.add(case6);

        List<Atom> case7 = new ArrayList<>();
        case7.add(Dlist.get(0));
        case7.add(Dlist.get(3));
        case7.add(Blist.get(1));
        case7.addAll(Alist.subList(0,2));
       allCases.add(case7);

        List<Atom> case8 = new ArrayList<>();
        case8.add(Dlist.get(0));
        case8.add(Dlist.get(3));
        case8.add(Blist.get(1));
        case8.addAll(Alist.subList(2,4));
       allCases.add(case8);

        List<Atom> case9 = new ArrayList<>();
        case9.add(Dlist.get(2));
        case9.add(Dlist.get(1));
        case9.add(Blist.get(0));
        case9.addAll(Alist.subList(1,3));
        allCases.add(case9);

        List<Atom> case10 = new ArrayList<>();
        case10.add(Dlist.get(2));
        case10.add(Dlist.get(1));
        case10.add(Blist.get(0));
        case10.add(Alist.get(0));
        case10.add(Alist.get(3));
       allCases.add(case10);

        List<Atom> case11 = new ArrayList<>();
        case11.add(Dlist.get(2));
        case11.add(Dlist.get(1));
        case11.add(Blist.get(1));
        case11.addAll(Alist.subList(0,2));
        allCases.add(case11);

        List<Atom> case12 = new ArrayList<>();
        case12.add(Dlist.get(2));
        case12.add(Dlist.get(1));
        case12.add(Blist.get(1));
        case12.addAll(Alist.subList(2,4));
        allCases.add(case12);

        List<Atom> case13 = new ArrayList<>();
        case13.addAll(Dlist.subList(2,4));
        case13.add(Blist.get(0));
        case13.addAll(Alist.subList(0,2));
        allCases.add(case13);

        List<Atom> case14 = new ArrayList<>();
        case14.addAll(Dlist.subList(2,4));
        case14.add(Blist.get(0));
        case14.addAll(Alist.subList(2,4));
       allCases.add(case14);

        List<Atom> case15 = new ArrayList<>();
        case15.addAll(Dlist.subList(2,4));
        case15.add(Blist.get(1));
        case15.addAll(Alist.subList(1,3));
        allCases.add(case15);

        List<Atom> case16 = new ArrayList<>();
        case16.addAll(Dlist.subList(2,4));
        case16.add(Blist.get(1));
        case16.add(Alist.get(0));
        case16.add(Alist.get(3));
        allCases.add(case16);

        for (int i = 0; i < ((allCases.size())/2) ; i++) {
            res.append(generateMultiOrClauses(negB, negC, allCases.get(i), allCases.get(i + 1)));
        }
        return res.toString();
    }

    private String generatePosAClauses(List<List<Atom>> posAtoms, List<List<Atom>> negAtoms) {
        StringBuilder res = new StringBuilder();
        Atom posA = posAtoms.get(0).get(0);
        Atom posC = posAtoms.get(2).get(2);
        List<Atom> Alist = Arrays.asList(negAtoms.get(0).get(1),negAtoms.get(0).get(2),
                            posAtoms.get(0).get(1),posAtoms.get(0).get(2));
        List<Atom> Blist = Arrays.asList(negAtoms.get(1).get(0),negAtoms.get(1).get(2),posAtoms.get(1).get(0),
                            posAtoms.get(1).get(1),posAtoms.get(1).get(2));
        List<Atom> Clist = Arrays.asList(negAtoms.get(2).get(0), negAtoms.get(2).get(1),
                            posAtoms.get(2).get(0),posAtoms.get(2).get(1));

        List<List<Atom>> allCases = new ArrayList<>();

        List<Atom> case1 = new ArrayList<>();
        case1.addAll(Clist.subList(0,2));
        case1.addAll(Blist.subList(0,2));
        case1.add(Blist.get(3));
        case1.addAll(Alist.subList(0,2));
        allCases.add(case1);

        List<Atom> case2 = new ArrayList<>();
        case2.add(Clist.get(0));
        case2.add(Clist.get(3));
        case2.addAll(Blist.subList(0,2));
        case2.add(Blist.get(3));
        case2.addAll(Alist.subList(1,3));
        allCases.add(case2);

        List<Atom> case3 = new ArrayList<>();
        case3.add(Clist.get(0));
        case3.add(Clist.get(3));
        case3.addAll(Blist.subList(0,2));
        case3.add(Blist.get(3));
        case3.add(Alist.get(0));
        case3.add(Alist.get(3));
       allCases.add(case3);

        List<Atom> case4 = new ArrayList<>();
        case4.addAll(Clist.subList(0,2));
        case4.addAll(Blist.subList(0,2));
        case4.add(Blist.get(3));
        case4.addAll(Alist.subList(2,4));
       allCases.add(case4);

        List<Atom> case5 = new ArrayList<>();
        case5.add(Clist.get(0));
        case5.add(Clist.get(3));
        case5.add(Blist.get(0));
        case5.addAll(Blist.subList(3,5));
        case5.addAll(Alist.subList(0,2));
        allCases.add(case5);

        List<Atom> case6 = new ArrayList<>();
        case6.addAll(Clist.subList(0,2));
        case6.add(Blist.get(0));
        case6.addAll(Blist.subList(3,5));
        case6.addAll(Alist.subList(0,2));
        allCases.add(case6);


        List<Atom> case7 = new ArrayList<>();
        case7.addAll(Clist.subList(0,2));
        case7.add(Blist.get(0));
        case7.addAll(Blist.subList(3,5));
        case7.add(Alist.get(0));
        case7.add(Alist.get(3));
       allCases.add(case7);

        List<Atom> case8 = new ArrayList<>();
        case8.add(Clist.get(0));
        case8.add(Clist.get(3));
        case8.add(Blist.get(0));
        case8.addAll(Blist.subList(3,5));
        case8.addAll(Alist.subList(2,4));
        allCases.add(case8);

        List<Atom> case9 = new ArrayList<>();
        case9.addAll(Clist.subList(2,4));
        case9.addAll(Blist.subList(0,2));
        case9.add(Blist.get(3));
        case9.addAll(Alist.subList(0,2));
        allCases.add(case9);

        List<Atom> case10 = new ArrayList<>();
        case10.addAll(Clist.subList(1,3));
        case10.addAll(Blist.subList(0,2));
        case10.add(Blist.get(3));
        case10.addAll(Alist.subList(1,3));
        allCases.add(case10);

        List<Atom> case11 = new ArrayList<>();
        case11.addAll(Clist.subList(1,3));
        case11.addAll(Blist.subList(0,2));
        case11.add(Blist.get(3));
        case11.add(Alist.get(0));
        case11.add(Alist.get(3));
        allCases.add(case11);

        List<Atom> case12 = new ArrayList<>();
        case12.addAll(Clist.subList(2,4));
        case12.addAll(Blist.subList(0,2));
        case12.add(Blist.get(3));
        case12.addAll(Alist.subList(2,4));
        allCases.add(case12);

        List<Atom> case13 = new ArrayList<>();
        case13.addAll(Clist.subList(1,3));
        case13.add(Blist.get(0));
        case13.addAll(Blist.subList(3,5));
        case13.addAll(Alist.subList(0,2));
        allCases.add(case13);

        List<Atom> case14 = new ArrayList<>();
        case14.addAll(Clist.subList(2,4));
        case14.add(Blist.get(0));
        case14.addAll(Blist.subList(3,5));
        case14.addAll(Alist.subList(1,3));
        allCases.add(case14);

        List<Atom> case15 = new ArrayList<>();
        case15.addAll(Clist.subList(2,4));
        case15.add(Blist.get(0));
        case15.addAll(Blist.subList(3,5));
        case15.add(Alist.get(0));
        case15.add(Alist.get(3));
        allCases.add(case15);

        List<Atom> case16 = new ArrayList<>();
        case16.addAll(Clist.subList(1,3));
        case16.add(Blist.get(0));
        case16.addAll(Blist.subList(3,5));
        case16.addAll(Alist.subList(2,4));
        allCases.add(case16);

        List<Atom> case17 = new ArrayList<>();
        case17.add(Clist.get(0));
        case17.add(Clist.get(3));
        case17.add(Blist.get(1));
        case17.addAll(Blist.subList(2,4));
        case17.addAll(Alist.subList(0,2));
        allCases.add(case17);

        List<Atom> case18 = new ArrayList<>();
        case18.addAll(Clist.subList(0,2));
        case18.add(Blist.get(1));
        case18.addAll(Blist.subList(2,4));
        case18.addAll(Alist.subList(1,3));
        allCases.add(case18);

        List<Atom> case19 = new ArrayList<>();
        case19.addAll(Clist.subList(0,2));
        case19.addAll(Blist.subList(1,4));
        case19.add(Alist.get(0));
        case19.add(Alist.get(3));
        allCases.add(case19);

        List<Atom> case20 = new ArrayList<>();
        case20.add(Clist.get(0));
        case20.add(Clist.get(3));
        case20.add(Blist.get(1));
        case20.addAll(Blist.subList(2,4));
        case20.addAll(Alist.subList(2,4));
        allCases.add(case20);

        List<Atom> case21 = new ArrayList<>();
        case21.addAll(Clist.subList(0,2));
        case21.addAll(Blist.subList(2,5));
        case21.addAll(Alist.subList(0,2));
        allCases.add(case21);

        List<Atom> case22 = new ArrayList<>();
        case22.add(Clist.get(0));
        case22.add(Clist.get(3));
        case22.addAll(Blist.subList(2,5));
        case22.addAll(Alist.subList(1,3));
        allCases.add(case22);

        List<Atom> case23 = new ArrayList<>();
        case23.add(Clist.get(0));
        case23.add(Clist.get(3));
        case23.addAll(Blist.subList(2,5));
        case23.add(Alist.get(0));
        case23.add(Alist.get(3));
       allCases.add(case23);

        List<Atom> case24 = new ArrayList<>();
        case24.addAll(Clist.subList(0,2));
        case24.addAll(Blist.subList(2,5));
        case24.addAll(Alist.subList(2,4));
       allCases.add(case24);

        List<Atom> case25 = new ArrayList<>();
        case25.add(Clist.get(2));
        case25.add(Clist.get(1));
        case25.addAll(Blist.subList(1,4));
        case25.addAll(Alist.subList(0,2));
        allCases.add(case25);

        List<Atom> case26 = new ArrayList<>();
        case26.addAll(Clist.subList(2,4));
        case26.addAll(Blist.subList(1,4));
        case26.addAll(Alist.subList(1,3));
        allCases.add(case26);

        List<Atom> case27 = new ArrayList<>();
        case27.addAll(Clist.subList(2,4));
        case27.addAll(Blist.subList(1,4));
        case27.add(Alist.get(0));
        case27.add(Alist.get(3));
        allCases.add(case27);

        List<Atom> case28 = new ArrayList<>();
        case28.addAll(Clist.subList(1,3));
        case28.addAll(Blist.subList(1,4));
        case28.addAll(Alist.subList(2,4));
        allCases.add(case28);

        List<Atom> case29 = new ArrayList<>();
        case29.addAll(Clist.subList(2,4));
        case29.addAll(Blist.subList(2,5));
        case29.addAll(Alist.subList(0,2));
        allCases.add(case29);

        List<Atom> case30 = new ArrayList<>();
        case30.addAll(Clist.subList(1,3));
        case30.addAll(Blist.subList(2,5));
        case30.addAll(Alist.subList(1,3));
        allCases.add(case30);

        List<Atom> case31 = new ArrayList<>();
        case31.addAll(Clist.subList(1,3));
        case31.addAll(Blist.subList(2,5));
        case31.add(Alist.get(0));
        case31.add(Alist.get(3));
        allCases.add(case31);

        List<Atom> case32 = new ArrayList<>();
        case32.addAll(Clist.subList(2,4));
        case32.addAll(Blist.subList(2,5));
        case32.addAll(Alist.subList(2,4));
        allCases.add(case32);

        //System.out.println("#cases: " +allCases.size());
        for (int i = 0; i < ((allCases.size())/2) ; i++) {
            res.append(generateMultiOrClauses(posA,posC, allCases.get(i), allCases.get(i + 1)));
        }
        return res.toString();
    }


    private String generateNegAClauses(List<List<Atom>> posAtoms, List<List<Atom>> negAtoms) {
        Atom negA = negAtoms.get(0).get(0);
        List<Atom> Dlist = Arrays.asList(negAtoms.get(3).get(1),negAtoms.get(3).get(2),posAtoms.get(3).get(1),posAtoms.get(3).get(2));
        List<Atom> Clist = Arrays.asList(negAtoms.get(2).get(1),negAtoms.get(2).get(3),posAtoms.get(2).get(1),posAtoms.get(2).get(3));
        List<Atom> Blist = Arrays.asList(negAtoms.get(1).get(2),negAtoms.get(1).get(3),posAtoms.get(1).get(2),posAtoms.get(1).get(3));

        //All cases with neg Ax and neg dy
        List<List<Atom>> allCases = new ArrayList<>();
        List<Atom> case1 = new ArrayList<>();
        case1.add(Dlist.get(1));
        case1.addAll(Clist.subList(0,2));
        case1.addAll(Blist.subList(0,2));
        
        allCases.add(case1);

        List<Atom> case2 =  new ArrayList<>();
        case2.add(Dlist.get(1));
        case2.add(Clist.get(2));
        case2.add(Clist.get(1));
        case2.add(Blist.get(2));
        case2.add(Blist.get(1));

       allCases.add(case2);

        List<Atom> case3  =  new ArrayList<>();
        case3.add(Dlist.get(1));
        case3.add(Clist.get(2));
        case3.addAll(case1.subList(2,4));
        case3.add(Blist.get(3));

       allCases.add(case3);

        List<Atom> case4  =  new ArrayList<>();
        case4.add(Dlist.get(1));
        case4.addAll(Clist.subList(1,2));
        case4.addAll(Blist.subList(2,4));
        allCases.add(case4);
        
        List<Atom> case5 = new ArrayList<>();
        case5.add(Dlist.get(1));
        case5.addAll(Clist.subList(2,4));
        case5.addAll(Blist.subList(0,2));
       allCases.add(case5);

        List<Atom> case6 = new ArrayList<>();
        case6.add(Dlist.get(1));
        case6.add(Clist.get(0));
        case6.add(Clist.get(3));
        case6.addAll(Blist.subList(1,3));
       allCases.add(case6);

        List<Atom> case7 = new ArrayList<>();
        case7.add(Dlist.get(1));
        case7.add(Clist.get(0));
        case7.add(Clist.get(3));
        case7.add(Blist.get(0));
        case7.add(Blist.get(3));
        allCases.add(case7);

        List<Atom> case8 = new ArrayList<>();

        case8.add(Dlist.get(1));
        case8.addAll(Clist.subList(2,4));
        case8.addAll(Blist.subList(2,4));

        allCases.add(case8);

        List<Atom> case9 = new ArrayList<>();
        case9.add(Dlist.get(3));
        case9.add(Clist.get(2));
        case9.add(Clist.get(1));
        case9.addAll(Blist.subList(0,2));

       allCases.add(case9);

        List<Atom> case10 = new ArrayList<>();
        case10.add(Dlist.get(3));
        case10.add(Clist.get(1));
        case10.add(Clist.get(0));
        case10.addAll(Blist.subList(1,3));

        allCases.add(case10);

        List<Atom> case11 = new ArrayList<>();
        case11.addAll(case10.subList(0,3));
        case11.add(Blist.get(0));
        case11.add(Blist.get(3));

        allCases.add(case11);

        List<Atom> case12 = new ArrayList<>();
        case12.addAll(case10.subList(0,2));
        case12.add(Clist.get(2));
        case12.addAll(Blist.subList(2,4));

        allCases.add(case12);

        List<Atom> case13 = new ArrayList<>();
        case13.add(Dlist.get(3));
        case13.add(Clist.get(3));
        case13.add(Clist.get(0));
        case13.addAll(Blist.subList(0,2));

        allCases.add(case13);
        List<Atom> case14 = new ArrayList<>();
        case14.add(Dlist.get(3));
        case14.addAll(Clist.subList(2,4));
        case14.addAll(Blist.subList(1,3));

        allCases.add(case14);


        List<Atom> case15 = new ArrayList<>();
        case15.add(Dlist.get(3));
        case15.addAll(Clist.subList(2,4));
        case15.add(Blist.get(0));
        case15.add(Blist.get(3));

        allCases.add(case15);
        List<Atom> case16 = new ArrayList<>();
        case16.add(Dlist.get(3));
        case16.add(Clist.get(0));
        case16.add(Clist.get(3));
        case16.addAll(Blist.subList(1,3));

        allCases.add(case16);


        //All cases with neg Ax and Dy
        List<Atom> case17 = new ArrayList<>();
        case17.add(Dlist.get(1));
        case17.addAll(Clist.subList(1,3));
        case17.addAll(Blist.subList(0,2));
        allCases.add(case17);

        List<Atom> case18 = new ArrayList<>();
        case18.add(Dlist.get(1));
        case18.addAll(Clist.subList(0,2));
        case18.addAll(Blist.subList(1,3));
       allCases.add(case18);

        List<Atom> case19 = new ArrayList<>();
        case19.add(Dlist.get(1));
        case19.addAll(Clist.subList(0,2));
        case19.add(Blist.get(0));
        case19.add(Blist.get(3));
        allCases.add(case19);

        List<Atom> case20 = new ArrayList<>();
        case20.add(Dlist.get(1));
        case20.addAll(Clist.subList(1,3));
        case20.addAll(Blist.subList(2,4));
        allCases.add(case20);

        List<Atom> case21 = new ArrayList<>();
        case21.add(Dlist.get(1));
        case21.add(Clist.get(0));
        case21.add(Clist.get(3));
        case21.addAll(Blist.subList(0,2));
       allCases.add(case21);

        List<Atom> case22 = new ArrayList<>();
        case22.add(Dlist.get(1));
        case22.addAll(Clist.subList(2,4));
        case22.addAll(Blist.subList(1,3));
        allCases.add(case22);

        List<Atom> case23 = new ArrayList<>();
        case23.add(Dlist.get(1));
        case23.addAll(Clist.subList(2,4));
        case23.add(Blist.get(0));
        case23.add(Blist.get(3));
        allCases.add(case23);

        List<Atom> case24 = new ArrayList<>();
        case24.add(Dlist.get(1));
        case24.add(Clist.get(0));
        case24.add(Clist.get(3));
        case24.addAll(Blist.subList(2,4));
        allCases.add(case24);

        List<Atom> case25 = new ArrayList<>();
        case25.add(Dlist.get(3));
        case25.addAll(Clist.subList(0,2));
        case25.addAll(Blist.subList(0,2));
        allCases.add(case25);

        List<Atom> case26 = new ArrayList<>();
        case26.add(Dlist.get(3));
        case26.addAll(Clist.subList(1,3));
        case26.add(Blist.get(1));
        case26.add(Blist.get(2));
        allCases.add(case26);

        List<Atom> case27 = new ArrayList<>();
        case27.add(Dlist.get(3));
        case27.addAll(Clist.subList(1,3));
        case27.add(Blist.get(0));
        case27.add(Blist.get(3));
       allCases.add(case27);

        List<Atom> case28 = new ArrayList<>();
        case28.add(Dlist.get(3));
        case28.addAll(Clist.subList(0,2));
        case28.addAll(Blist.subList(2,4));
       allCases.add(case28);

        List<Atom> case29 = new ArrayList<>();
        case29.add(Dlist.get(3));
        case29.addAll(Clist.subList(2,4));
        case29.addAll(Blist.subList(0,2));
         allCases.add(case29);

        List<Atom> case30 = new ArrayList<>();
        case30.add(Dlist.get(3));
        case30.add(Clist.get(0));
        case30.add(Clist.get(3));
        case30.addAll(Blist.subList(1,3));
        allCases.add(case30);

        List<Atom> case31 = new ArrayList<>();
        case31.add(Dlist.get(3));
        case31.add(Clist.get(0));
        case31.add(Clist.get(3));
        case31.add(Blist.get(0));
        case31.add(Blist.get(3));
        allCases.add(case31);

        List<Atom> case32 = new ArrayList<>();
        case32.add(Dlist.get(3));
        case32.addAll(Clist.subList(2,4));
        case32.addAll(Blist.subList(2,4));
        allCases.add(case32);
        //Generating all the clauses
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < ((allCases.size())/2)-1 ; i++) {
            res.append(generateMultiOrClauses(negA, Dlist.get(0), allCases.get(i), allCases.get(i + 1)));
        }
       // res.append("---------------- <<Look under me>>------------------\n");
        for (int i = (allCases.size())/2; i < ((allCases.size())-1); i++) {
            res.append(generateMultiOrClauses(negA, Dlist.get(2), allCases.get(i), allCases.get(i + 1)));
        }
        return res.toString();
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

    private String generateSingleOrClauses(Atom a, Atom b){
        addClause(a,b,b);
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
        addClauseFromList(a,b,list1);

        String res = temp.substring(0,temp.lastIndexOf("||"));
        res+=") && \n";


        temp2.append("(" + ab + " || " );
        for (Atom aList2 : list2) {
            temp2.append(aList2.stringRep + " || ");
        }
        addClauseFromList(a,b,list2);

        res+= temp2.substring(0,temp2.lastIndexOf("||")) + ") && \n";
        return res;

    }

    private  String generateOrClause(List<Atom> atoms){
        StringBuilder temp = new StringBuilder();
        temp.append("(");
        for (Atom a: atoms ) {
            temp.append(a.stringRep + " || ");
        }
        addClauseFromList(atoms.get(0),atoms.get(0),atoms);
        String res = temp.substring(0,temp.lastIndexOf("||")) + ") &&";
        return res + "\n";
    }

    private void addClause(Atom a, Atom b, Atom c){
        Set<Atom> atomSet = new HashSet<>();
        atomSet.add(a);
        atomSet.add(b);
        atomSet.add(c);
        clauses.add(new Clause(atomSet));
    }
    private void addClauseFromList(Atom a, Atom b, List<Atom> list){
        Set<Atom> atomSet = new HashSet<>();
        atomSet.add(a);
        atomSet.add(b);
        atomSet.addAll(list);
        clauses.add(new Clause(atomSet));
    }
}

/*
------ done to here -----


(c_x || !d_x || !d_z || !c_w || !a_w || a_z || !b_y) &&
(c_x || !d_x || !d_z || !c_w || a_w || !a_z || !b_y) &&
(c_x || !d_x || !d_z || c_w || !a_w || !a_z || !b_y) &&
(c_x || !d_x || !d_z || c_w || a_w || a_z || !b_y) &&
(c_x || !d_x || d_z || !c_w || !a_w || !a_z || !b_y) &&
(c_x || !d_x || d_z || !c_w || a_w || a_z || !b_y) &&
(c_x || !d_x || d_z || c_w || !a_w || a_z || !b_y) &&
(c_x || !d_x || d_z || c_w || a_w || !a_z || !b_y) &&
(c_x || d_x || !d_z || !c_w || !a_w || !a_z || !b_y) &&
(c_x || d_x || !d_z || !c_w || a_w || a_z || !b_y) &&
(c_x || d_x || !d_z || c_w || !a_w || a_z || !b_y) &&
(c_x || d_x || !d_z || c_w || a_w || !a_z || !b_y) &&
(c_x || d_x || d_z || !c_w || !a_w || a_z || !b_y) &&
(c_x || d_x || d_z || !c_w || a_w || !a_z || !b_y) &&
(c_x || d_x || d_z || c_w || !a_w || !a_z || !b_y) &&
(c_x || d_x || d_z || c_w || a_w || a_z || !b_y) &&
------ done from here -----

 */
