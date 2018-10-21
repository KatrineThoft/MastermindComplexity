package FeedbackTypes;

import java.util.*;

public class GOOO extends Feedback {
    Set<Clause> clauses = new HashSet<>();

    public GOOO(String guess) {
        super("GOOO", guess);
        translate();
        super.clauses =clauses;
    }

    @Override
    public int noXOR() {
        return 0;
    }


    private void translate(){
        String[] atomString= guess.split(",");
        List<Atom> posAtoms = new ArrayList<>();

        //Getting the complement, since the original atom is not used in bool. trans.
        for (String s: atomString) {
            Atom a = new Atom(s);
            posAtoms.add(a);
        }

        //Creating lists with all atoms:
        List<Atom> posA = generateAtoms(posAtoms.get(0),false);
        List<Atom> negA = generateAtoms(posAtoms.get(0),true);
        List<Atom> posB = generateAtoms(posAtoms.get(1),false);
        List<Atom> negB = generateAtoms(posAtoms.get(1),true);
        List<Atom> posC = generateAtoms(posAtoms.get(2),false);
        List<Atom> negC = generateAtoms(posAtoms.get(2),true);
        List<Atom> posD = generateAtoms(posAtoms.get(3),false);
        List<Atom> negD = generateAtoms(posAtoms.get(3),true);

        //Splitting translation into cases:

        //Case1: ax:
        List<Atom> case1 = Arrays.asList(negA.get(0),negB.get(2),negB.get(3),negC.get(1),negC.get(3),negD.get(1),negD.get(2));

        String negAString = generateOrClause(case1);

        negAString += generateMultiOrClauses(posA.get(0).getComplement(),posD.get(1),posC.subList(1,2), posD.subList(2,3));
        negAString += generateMultiOrClauses(negA.get(0),posB.get(2),posB.subList(3,posB.size()), posD.subList(2,3));
        negAString += generateMultiOrClauses(negA.get(0),posC.get(3),posB.subList(3,posB.size()),posC.subList(1,2));
        negAString += generateSingleOrClauses(negA.get(0),negB.get(1));
        negAString += generateSingleOrClauses(negA.get(0),negC.get(2));
        negAString += generateSingleOrClauses(negA.get(0),negD.get(3));


        //Case2 ax is not negated:
        List<Atom> case2 = Arrays.asList(posA.get(0),negA.get(2),negA.get(3),
                negC.get(0),negC.get(3),negD.get(0),negD.get(2),negD.get(3));
        String posAString =generateOrClause(case2);

        List<Atom> case2b = new ArrayList<>();
        case2b.addAll(Arrays.asList(posA.get(0),posC.get(2),posC.get(0),posD.get(0),posD.get(3),
                posA.get(0),posC.get(2),posC.get(0),posD.get(3),posC.get(3)));

        posAString += generateOrClause(case2b.subList(0,5));
        posAString +=generateOrClause(case2b.subList(5,case2b.size()));

        List<Atom> case2c =  new ArrayList<>();
        case2c.addAll(Arrays.asList(posA.get(0),posC.get(2),posC.get(0),posA.get(2),
                posA.get(0),posC.get(2),posC.get(0), posD.get(2),posD.get(3)));

       posAString += generateOrClause(case2c.subList(0,4));
       posAString += generateOrClause(case2c.subList(4,case2c.size()));

        List<Atom> case2d =  new ArrayList<>();
        case2d.addAll(Arrays.asList(posA.get(0),posC.get(2),posA.get(2),posD.get(2),posD.get(3),
                posA.get(0),posC.get(2),posA.get(3), posC.get(3),posD.get(3)));
        posAString += generateOrClause(case2d.subList(0,5));
        posAString += generateOrClause(case2d.subList(5,case2d.size()));


        List<Atom> case2e = new ArrayList<>();
        case2e.addAll(Arrays.asList(posA.get(0), posC.get(2), posA.get(2),posD.get(2),posD.get(3),
                posA.get(0),posC.get(2),posA.get(3),posD.get(2),posD.get(3)));

        posAString += generateOrClause(case2e.subList(0,5));
        posAString += generateOrClause(case2e.subList(5,case2e.size()));

        List<Atom> case2f = new ArrayList<>();
        case2f.addAll(Arrays.asList(posA.get(0),posC.get(2),posA.get(2),posA.get(3),posD.get(3),
                posA.get(0), posC.get(2),posB.get(1),posD.get(3)));

        posAString += generateOrClause(case2f.subList(0,5));
        posAString += generateOrClause(case2f.subList(5,case2f.size()));


        List<Atom> case2g = new ArrayList<>();
        case2g.addAll(Arrays.asList(posA.get(0),posB.get(1),posA.get(1),posB.get(0),
                posA.get(0),posB.get(1),posC.get(2),posD.get(3)));
        posAString += generateOrClause(case2g.subList(0,4));
        posAString += generateOrClause(case2g.subList(4,case2g.size()));


        String negBString = generateOrClause(Arrays.asList(negB.get(0),negA.get(1),negA.get(2),negB.get(2),
                negC.get(0), negC.get(1),negD.get(3)));
        negBString +=generateOrClause(Arrays.asList(negB.get(0), negA.get(1), negA.get(3), negB.get(3),
                negC.get(2), negD.get(0), negD.get(1)));

        String posBString = generateMultiOrClauses(posB.get(0),negD.get(3), Arrays.asList(posC.get(0)),Arrays.asList(posB.get(2)));
        posBString += generateMultiOrClauses(posB.get(0),negC.get(2), Arrays.asList(posD.get(0)),Arrays.asList(posB.get(3)));


        String posDString = generateMultiOrClauses(posD.get(1),negC.get(2),Arrays.asList(posD.get(0)),Arrays.asList(posA.get(1)));
        posDString +=generateOrClause(Arrays.asList(posD.get(1),posB.get(1),posB.get(3),posD.get(3)));



        String negDString = generateMultiOrClauses(negD.get(3),posC.get(1),Arrays.asList(posC.get(0)),Arrays.asList(posA.get(1)));
        negDString += generateMultiOrClauses(negD.get(3),posA.get(2),Arrays.asList(posA.get(1)),Arrays.asList(posB.get(2)));
        negDString+=generateSingleOrClauses(negD.get(3),negC.get(2));
        negDString+=generateSingleOrClauses(negD.get(3),negB.get(1));

        String negCString = generateMultiOrClauses(negC.get(2),posA.get(3),Arrays.asList(posB.get(3)),Arrays.asList(posA.get(1)));
        negCString += generateSingleOrClauses(negC.get(2),negB.get(1));

        String posCString = generateOrClause(Arrays.asList(posC.get(2),posC.get(3),posD.get(2),posD.get(3)));
        posCString += generateOrClause(Arrays.asList(posC.get(2),posB.get(1),posB.get(2),posC.get(1)));

        super.boolTrans = negAString +posAString +negBString+posBString+posDString +negDString +negCString+ posCString;

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

    private String generateMultiOrClauses(Atom a, Atom b, List<Atom> list1, List<Atom> list2){
        StringBuilder temp = new StringBuilder();
        String ab = a.stringRep + " || " + b.stringRep;

        for (Atom aList1 : list1) {
            temp.append("(" + ab + " || " + aList1.stringRep + ") && \n");
            addClause(a,b,aList1);
        }

        for (Atom aList2 : list2) {
            temp.append("(" + ab + " || " + aList2.stringRep + ") && \n");
           addClause(a,b,aList2);
        }

        return temp.toString();

    }

    private String generateSingleOrClauses(Atom a, Atom b){
        addClause(a,b,b);
        return "("+ a.stringRep + " || " + b.stringRep + ") && \n";
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

    private void addClauseFromList(List<Atom> list){
        Set<Atom> atomSet = new HashSet<>();
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

    /*
    ( !a_x || !d_y || !d_z || !c_w || !b_w || !b_z || !c_y) &&
    ( !a_x || d_y || d_z) &&
    ( !a_x || d_y || c_y) &&
    ( !a_x || d_z || b_z) &&
    ( !a_x || !dw) &&
    ( !a_x || c_w || b_w) &&
    ( !a_x || c_w || c_y) &&
    ( !a_x || !c_z) &&
    ( !a_x || b_w || b_z) &&
    ( !a_x || !b_y) &&

    (a_x || b_x || a_y || b_y) &&
    (a_x || !c_x || !d_x || !d_z || dw || !c_w || c_z || !a_w || !a_z) &&
    (a_x || c_x || d_x || dw || c_z) &&
    (a_x || c_x || dw || c_w || c_z) &&
    (a_x || c_x || c_z || a_z) &&
    (a_x || d_x || d_z || dw || c_z) &&
    (a_x || d_x || dw || a_w) &&
    (a_x || d_z || dw || c_z || a_z) &&
    (a_x || dw || c_w || c_z || a_w) &&
    (a_x || dw || c_z || a_w || a_z) &&
    (a_x || dw || c_z || b_y) &&

    ( !b_x || !c_x || !dw || !b_z || !a_z || !a_y || !c_y) &&
    ( !b_x || !d_x || !d_y || !c_z || !b_w || !a_w || !a_y) &&

    (b_x || c_x || !dw) &&
    (b_x || d_x || !c_z) &&
    (b_x || !dw || b_z) &&
    (b_x || !c_z || b_w) &&

    (d_x || d_y || !c_z) &&
    (d_y || dw || b_w || b_y) &&
    (d_y || !c_z || a_y) &&

     (c_x || !dw || c_y) &&
    ( !dw || !c_z) &&
    ( !dw || b_z || a_z) &&
    ( !dw || a_z || a_y) &&
    ( !dw || a_y || c_y) &&
    (!dw || !b_y) &&

    ( !c_z || b_w || a_w) &&
    ( !c_z || a_w || a_y) &&
    ( !c_z || !b_y) &&

     (d_z || dw || c_w || c_z) &&
    (c_z || b_z || c_y || b_y)
     */
}
