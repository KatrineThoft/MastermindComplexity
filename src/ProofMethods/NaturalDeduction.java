package ProofMethods;

import FeedbackTypes.Atom;
import FeedbackTypes.Clause;
import FeedbackTypes.Feedback;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
//Class containing the Natural Deduction method
public class NaturalDeduction {
    private Set<Clause> currentClauses;
    private Set<Clause> allClauses; //TODO: Implement update
    private Set<Clause> usedClauses;
    private Set<Clause> missingAtomsClauses;
    private Set<Atom> missingAtoms;
    private Clause conclusion;
    private int noSteps;
    private int noTotalClauses;
    private String resultString;


    //Constructor
    public NaturalDeduction(Feedback feedback, String conclusionString){
        this.currentClauses = feedback.getClauses();
        this.conclusion = createConclusionClause(conclusionString);
        System.out.println("Conclusion: "+conclusion.toString());
        noTotalClauses = currentClauses.size();
        this.usedClauses = new HashSet<>();
        this.allClauses = currentClauses;
        derive();

    }

    //Creates a Clause object representing the conclusion
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


    //Method applying the rules to the given feedback clauses
    private void derive() {
        Clause res = new Clause();
           currentClauses.addAll(applyORRule());
           removeUsedClauses();
                res = applyANDRule(false);
                currentClauses.add(res);
                if(res.getAtoms().size() <conclusion.getAtoms().size()){
                    findMissingAtoms(res);
                    currentClauses.addAll(searchForMissing());
                    Clause newRes = applyANDRule(true);
                    res.addAllAtoms(newRes.getAtoms());
                    currentClauses.add(res);
                }
            resultString = res.toString();
        }

    //Searches for positions in conclusion w. no atoms found in curren tclauses
    private void findMissingAtoms(Clause res) {
        Set<String> missingPos = new HashSet<>();
        for (Atom a:res.getAtoms()) {
            missingPos.add(a.getPosition());
        }

        Set<Clause> findClauses = new HashSet<>();
         missingAtoms = new HashSet<>();

            for (Clause c : currentClauses) {
                for (Atom a : c.getAtoms()) {
                    if (!missingPos.contains(a.getPosition()) && a.getNegated().equals(true)){
                        findClauses.add(c);
                        missingAtoms.add(a);
                    }
            }
        }
        missingAtomsClauses = findClauses;
    }

    //Removes clauses from currentclauses that have been used already
    private void removeUsedClauses() {
        this.usedClauses.addAll(currentClauses.stream().filter(Clause::isResolved).collect(Collectors.toSet()));
        this.currentClauses = currentClauses.stream().filter(c->!c.isResolved()).collect(Collectors.toSet());
    }


    //Methods implementing the rules of Natural Deduction
    private Clause applyANDRule(boolean isMissing) {
       Clause resultClause = new Clause();
       Set<Atom> atoms = new HashSet<>();
       atoms = isMissing ? missingAtoms : conclusion.getAtoms();

        for (Clause c: currentClauses) {
            for (Atom a: atoms) {
                if (c.contains(a)){
                    resultClause.addAtom(a); //AND rule
                }
            }
        }
        return resultClause;
    }
    private Set<Clause> applyORRule() {
        Set<Clause> newClauses = new HashSet<>();
        for (Clause c: currentClauses) {
            if (!c.isResolved()) {
                newClauses.addAll(containsConclusionAtoms(c));
            }
        }
        return newClauses;
    }

    //Method searching for negated atoms for positions which are unfilled in conclusion
    private Set<Clause> searchForMissing() {
        Set<Clause> newClauses = new HashSet<>();
        for (Clause c1 : missingAtomsClauses) {
            for (Atom a : missingAtoms) {
                if (c1.getAtoms().contains(a)) {
                    noSteps++;
                    Set<Atom> atomSet = new HashSet<>();
                    atomSet.add(a);
                    c1.resolveAtom(a);
                    Clause c2 = new Clause(atomSet);
                    newClauses.add(c2); //Elim or rule
                }
            }

        }
        return newClauses;
    }

    //Method searching for atoms contained in conclusion
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

    //Getter methods for different properties of the proof
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

    //Used to print the current clauses in the proof
    private void printClauses() { for (Clause c: currentClauses) {System.out.println(c.toString()); } }


}
