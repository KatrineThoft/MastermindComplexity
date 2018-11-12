package ProofMethods;

import FeedbackTypes.Atom;
import FeedbackTypes.Clause;
import FeedbackTypes.Feedback;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class NaturalDeduction {
    Set<Clause> currentClauses;
    Set<Clause> allClauses;
    Set<Clause> usedClauses;
    Clause conclusion;
    int noSteps;
    private int noTotalClauses;
    String resultString;


    public NaturalDeduction(Feedback feedback, String conclusionString){
        this.currentClauses = feedback.getClauses();
        this.conclusion = createConclusionClause(conclusionString);
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

    public NaturalDeduction(Feedback feedback, Set<Clause> premises, Clause conclusion){
        this.currentClauses = feedback.getClauses();
        this.currentClauses.addAll(premises);
        this.conclusion = conclusion;
        noTotalClauses = currentClauses.size();
        this.usedClauses = new HashSet<>();
        this.allClauses = currentClauses;
        derive();
    }

    private void derive() {
       while(moreRulesToApply()){
           currentClauses.addAll(applyRules());
           allClauses.addAll(applyRules());
           removeUsedClauses();
       }
       System.out.println("No. currentclauses: "+currentClauses.size());
        for (Clause c :currentClauses) {
            resultString += c.toString();
        }
    }

    private void removeUsedClauses() {
        int size = currentClauses.size();
        this.usedClauses.addAll(currentClauses.stream().filter(c->!c.isResolved()).collect(Collectors.toSet()));
        this.currentClauses = currentClauses.stream().filter(c->!c.isResolved()).collect(Collectors.toSet());
        System.out.println("No. clauses removed: "+(size-currentClauses.size()));

    }

    private boolean moreRulesToApply() {
        System.out.println("All in conclusion? "+currentClauses.containsAll(conclusion.getAtoms()));
        return !currentClauses.containsAll(conclusion.getAtoms());
    }



    private Set<Clause> applyRules() {
        Set<Clause> newClauses = new HashSet<>();
        for (Clause c: currentClauses) {
            if (!c.isResolved()) {
                System.out.println("In if "+ c.toString()+" not resolved");
                newClauses.addAll(containsConclusionAtoms(c));
            }
        }
        return newClauses;
    }

    private Set<Clause> containsConclusionAtoms(Clause c) {
        Set<Clause> newClauses = new HashSet<>();
        for (Atom a:c.getAtoms()) {
            if (conclusion.contains(a) && !a.isResolved){
                System.out.println("In if "+ c.toString()+" contains atom "+a.stringRep);
                noSteps++;
                Set<Atom> atomSet = new HashSet<>();
                atomSet.add(a);
                c.resolveAtom(a);
                System.out.println( "Resolved? "+c.isResolved());

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
