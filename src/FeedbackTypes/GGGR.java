
package FeedbackTypes;

import java.util.*;
//Class representing feedback of the type GGGR
public class GGGR extends Feedback {
    Set<Atom> formula = new HashSet<>();
    Set<Clause> clauses = new HashSet<>();

    public GGGR(String guess) {
        super("FeedbackTypes.GGGR",guess);
        translate();
        super.clauses = clauses;
    }


    @Override
    public int noXOR() {
        return 3;
    }


    //Method translating a guess from into a Boolean translation
    //Saves all data in Clause objects.
    private void translate(){
        String[] atomString= guess.split(",");
        List<Atom> atomList = new ArrayList<>();
        StringBuilder temp = new StringBuilder();
        temp.append("(");
        //Getting the complement, since the original atom is not used in bool. trans.
        Clause clause = new Clause();

        for (String s: atomString) {
            Atom a = new Atom("!"+s);
            atomList.add(a);
            temp.append(a.stringRep + "||");
            clause.addAtom(a);
        }
       clauses.add(clause);


        String res = temp.substring(0,temp.lastIndexOf("||")) + ") && \n";

        List<Atom> posAtoms = new ArrayList<>();
        for (Atom a: atomList) {
            Atom x =a.getComplement();
            posAtoms.add(x);
        }

        Atom a = posAtoms.get(0);
        Atom b = posAtoms.get(1);
        Atom c =posAtoms.get(2);
        res += createOrClauses(posAtoms,a) + " && ";
        res += createOrClauses(posAtoms,b) + " && ";
        res += createOrClauses(posAtoms,c);

        //Adding all the negated atoms to a set
        formula.addAll(atomList);
        formula.addAll(posAtoms);

        super.boolTrans = res.substring(0,res.lastIndexOf("\n "));
    }


    public Set<Atom> getAtoms(){
          return formula;
      }

    //Method used by translate() to generate clauses
    private String createOrClauses(List<Atom> atomList, Atom x){
          StringBuilder temp2 = new StringBuilder();
          for (int i = 1; i < atomList.size(); i++) {
              Atom a = atomList.get(i);
              if (!x.equals(a)) {
                  temp2.append("(" + x.stringRep + " || " + a.stringRep + ") && ");
                  Set<Atom> atomset = new HashSet<>();
                  atomset.add(x);
                  atomset.add(a);
                  clauses.add(new Clause(atomset));
              }
          }

          String res = temp2.substring(0,temp2.lastIndexOf("&&"))+ "\n";
        return res;
      }
}

