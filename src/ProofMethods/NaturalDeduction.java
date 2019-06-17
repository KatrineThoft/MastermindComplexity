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
    private Set<Clause> allClauses;
    private Set<Clause> usedClauses;
    private Set<Clause> missingAtomsClauses;
    private Set<Atom> missingAtoms;
    private Clause conclusion;
    private int noBranches;
    private int noSteps;
    private int noTotalClauses;
    private String resultString;
    Set<Clause> res = new HashSet<>();
    private Clause negClause;


    //Constructor
    public NaturalDeduction(Feedback feedback, String conclusionString){
        this.currentClauses = feedback.getClauses();
        this.conclusion = createConclusionClause(conclusionString);
        noTotalClauses = currentClauses.size();
        noBranches=0;
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


    //Finds negated atoms in relevant positions to be used in disjunctive elim
    private void findNegPos(){
        Clause negClause =new Clause();
        for (Atom a:conclusion.getAtoms()) {
            String pos = a.getPosition();
            for (Clause c: currentClauses) {
                if(c.getNegAtomByPos(pos)!=null){
                    negClause.addAtom(c.getNegAtomByPos(pos));
                    break;
                }

            }
        }
        System.out.println("Neg clause: "+negClause.toString());
        this.negClause = negClause;
    }

    //Method applying the rules to the given feedback clauses
    private void derive() {
           findNegPos();
           currentClauses.addAll(applyORRule());
           removeUsedClauses();
            res = applyANDRule(false);
            allClauses.addAll(res);
            allClauses.addAll(currentClauses);
            currentClauses.addAll(res);
                if(res.size() <conclusion.getAtoms().size()){
                    findMissingAtoms(res);
                    Set<Clause> missing = new HashSet<>();
                    missing = searchForMissing();
                    res.addAll(applyANDRule(true));
                    currentClauses.addAll(res);
                    allClauses.addAll(res);
                    allClauses.addAll(missing);
                }
            resultString = res.toString();
        }

    //Searches for positions in conclusion w. no atoms found in curren tclauses
    private void findMissingAtoms(Set<Clause> res) {
        Set<String> missingPos = new HashSet<>();
        for (Clause c: res) {
            for (Atom a : c.getAtoms()) {
                missingPos.add(a.getPosition());
            }
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
    private Set<Clause> applyANDRule(boolean isMissing) {
       Set<Clause> resultClause = new HashSet<>();
       Set<Atom> atoms = new HashSet<>();
       atoms = isMissing ? missingAtoms : conclusion.getAtoms();
        for (Clause c: currentClauses) {
            for (Atom a: atoms) {
                if (c.contains(a) && atomNotFound(a,resultClause)){
                    noSteps++;
                    Set<Atom> atom = new HashSet();
                    atom.add(a);
                    resultClause.add(new Clause(atom)); //Conjunction rule
                }
            }
        }
        return resultClause;
    }

    private boolean atomNotFound(Atom a, Set<Clause> clauseSet) {

            for (Clause c : res) {
                for (Atom a1 : c.getAtoms()) {
                    if (a1.equals(a)) {
                        return false;
                    }
                }
            }
        for (Clause c:clauseSet) {
            for (Atom a1 : c.getAtoms()) {
                if (a1.equals(a)) {
                    return false;
                }
            }
        }
        return true;
    }

    private Set<Clause> applyORRule() {
        Set<Clause> newClauses = new HashSet<>();
        for (Clause c: currentClauses) {
            if (!c.isResolved()) {
                noBranches++;
                noSteps++;
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
            if (conclusion.contains(a) && !a.isResolved && negClause.getNegAtomByPos(a.getPosition())!= null){
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
    public int getNoClauses() {
        return res.size();
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
    public int getNoBranches(){return noBranches;}
    public int noAtoms(){
        int res=0;
        for (Clause c: allClauses) {
            res+=c.getAtoms().size();
        }
        return res;
    }
    public int noTotalSymbols(){
        int res=0;
        for (Clause c: allClauses) {
            res+=c.getAtoms().size();
        }
        return 2*res-1;
    }
    public int noSymbols(){
       return res.size()*2-1;
    }
    //Used to print the current clauses in the proof
    private void printClauses() { for (Clause c: currentClauses) {System.out.println(c.toString()); } }

    public String getComplexity(){
        StringBuilder compl = new StringBuilder();
        compl.append("No. clauses total: "+getNoTotalClauses() +"\n" );
        compl.append("No. clauses in result: "+getNoClauses() +"\n" );
        compl.append("No. atoms: "+noAtoms() +"\n" );
        compl.append("No. steps: "+getNoSteps() +"\n" );
        compl.append("No. branches: "+getNoBranches() +"\n" );
        compl.append("No. symbols: "+noSymbols() +"\n" );
        compl.append("No. total symbols: "+noTotalSymbols() +"\n" );



        return compl.toString();
    }
}
