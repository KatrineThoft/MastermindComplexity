package ProofMethods;

import FeedbackTypes.Atom;
import FeedbackTypes.Clause;
import FeedbackTypes.Feedback;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class NaturalDeduction {
    Set<Clause> currentClauses;
    Set<Clause> allClauses;
    Set<Clause> usedClauses;
    Set<Clause> missingAtomsClauses;
    Set<Atom> missingAtoms;
    Clause conclusion;
    int noSteps;
    private int noTotalClauses;
    String resultString;


    public NaturalDeduction(Feedback feedback, String conclusionString){
        this.currentClauses = feedback.getClauses();
        this.conclusion = createConclusionClause(conclusionString);
        System.out.println("Conclusion: "+conclusion.toString());
        noTotalClauses = currentClauses.size();
        this.usedClauses = new HashSet<>();
        this.allClauses = currentClauses;
        derive();

    }

    private Clause createConclusionClause(String str) {
        String[] atomString= str.split(",");
        Set<Atom> atoms = new HashSet<>();
        //Getting the original atom
        for (String s: atomString) {
            Atom a = new Atom(s);
            atoms.add(a);
        }
        return new Clause(atoms);
    }


    private void derive() {
        Clause res = new Clause();
           currentClauses.addAll(applyORRule(false));
           allClauses.addAll(applyORRule(false));
           removeUsedClauses();
           if (!allAtomsNotFound()){
               System.out.println("All atom not in conclusion");
               //Look for the positions where there is an atom missing in the conclusion,
               // look for negated atoms on that position
               findMissingAtoms();
               currentClauses.addAll(searchForMissing());
               allClauses.addAll(applyORRule(true));
               removeUsedClauses();
           } else{
               //Applying AND rule
               System.out.println("All atom in conclusion");
                res = applyANDRule();
           }

            resultString = res.toString();
        }

    private void findMissingAtoms() {
        Set<Atom> missing = new HashSet<>();
        for (Atom a:conclusion.getAtoms()) {
            if (!currentClauses.contains(a)){
                missing.add(a);
            }
        }
        Set<String> missingPos =new HashSet<>();
        if (!missing.isEmpty()){
            for (Atom a:missing) {
                missingPos.add(a.getPosition());
            }
        }

        Set<Clause> findClauses = new HashSet<>();
         missingAtoms = new HashSet<>();

        for (String pos:missingPos) {
            for (Clause c : currentClauses) {
                for (Atom a : c.getAtoms()) {
                    if (a.getPosition().equals(pos) && a.getNegated().equals(true)){
                        findClauses.add(c);
                        missingAtoms.add(a);
                    }
                }
            }
        }
        missingAtomsClauses = findClauses;
    }


    private Clause applyANDRule() {
       Clause resultClause = new Clause();
        for (Clause c: currentClauses) {
            for (Atom a: conclusion.getAtoms()) {
                if (c.contains(a)){
                    resultClause.addAtom(a);
                }
            }
        }
        return resultClause;
    }

    private void removeUsedClauses() {
        int size = currentClauses.size();
        this.usedClauses.addAll(currentClauses.stream().filter(c->c.isResolved()).collect(Collectors.toSet()));
        this.currentClauses = currentClauses.stream().filter(c->!c.isResolved()).collect(Collectors.toSet());
        for (Clause c:usedClauses
             ) {
            System.out.println(c.toString() +" removed" );
        }

    }

    private boolean allAtomsNotFound() {
        return currentClauses.containsAll(conclusion.getAtoms());
    }



    private Set<Clause> applyORRule(boolean findMissing) {
        Set<Clause> newClauses = new HashSet<>();
        for (Clause c: currentClauses) {
            if (!c.isResolved()) {
                newClauses.addAll(containsConclusionAtoms(c));
            }
        }
        return newClauses;
    }

    private Set<Clause> searchForMissing() {
        Set<Clause> newClauses = new HashSet<>();
        for (Clause c1 : missingAtomsClauses) {
            for (Atom a : missingAtoms) {
                if (c1.getAtoms().contains(a)) {
                    noSteps++;
                    Set<Atom> atomSet = new HashSet<>();
                    atomSet.add(a);
                    c1.resolveAtom(a);
                    newClauses.add(new Clause(atomSet)); //Elim or rule
                }
            }

        }
        return newClauses;
    }

    private Set<Clause> containsConclusionAtoms(Clause c) {
        Set<Clause> newClauses = new HashSet<>();
        for (Atom a:c.getAtoms()) {
            if (conclusion.contains(a) && !a.isResolved){
                noSteps++;
                Set<Atom> atomSet = new HashSet<>();
                atomSet.add(a);
                c.resolveAtom(a);
                newClauses.add(new Clause(atomSet)); //Elim or rule
            } else{
                a.isResolved=true;
            }
        }
        noTotalClauses+=newClauses.size();
       return newClauses;
    }

    public int getNoTotalClauses() {
        return noTotalClauses;
    }

    public Clause getConclusion() {
        return conclusion;
    }

    public int getNoSteps() {
        return noSteps;
    }
    public Set<Clause> getCurrentClauses() {
        return currentClauses;
    }

    public String getResultString() {
        return resultString;
    }
}
