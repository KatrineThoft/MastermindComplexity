package ProofMethods;

import FeedbackTypes.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

//Class containing the resolution method
public class Resolution {
    private Set<Clause> currentClauses = new HashSet<>();
    private Set<Clause> resolvedClauses = new HashSet<>();
    private Set<Clause> allClauses = new HashSet<>();
    public  String resultString;
    private int noSteps;
    private int noResolvedAtoms;
    private boolean isMinimal;
    private int noTotalClauses;


    //Constructor for the first guess, without premises
    public Resolution(Feedback feedback, boolean isMinimal){
        this.currentClauses = feedback.getClauses();
        noTotalClauses = currentClauses.size();
        this.allClauses.addAll(currentClauses);
        this.isMinimal = isMinimal;
        noSteps =0;
        noResolvedAtoms =0;
        //printClauses();
        resultString = resolve();
    }

    //Constructor for the following guesses, with premises
    public Resolution(Feedback feedback, Set<Clause> premises, boolean isMinimal){
        this.isMinimal = isMinimal;
        currentClauses.addAll(premises);
        currentClauses.addAll(feedback.getClauses());
        noTotalClauses = currentClauses.size();
        this.allClauses.addAll(currentClauses);
        noSteps =0;
        noResolvedAtoms =0;
       // printClauses();
        resultString =  resolve();
    }

    //Used to print the current clauses in the proof
    private void printClauses() { for (Clause c: currentClauses) {System.out.println(c.toString()); } }


    //Methods executing resolution
    private String resolve() {
        Set<Clause> childClauses = searchForCompliments(currentClauses);

        //Resolves until no complementing pairs of Atoms are found
        while (containsComplimentingPairs()){
            currentClauses.addAll(childClauses);
            noTotalClauses += childClauses.size();
            allClauses.addAll(childClauses);
            removeClauses();
            childClauses=searchForCompliments(currentClauses);
        }

        //Building a String such that the results can be printed
        StringBuilder res = new StringBuilder();
        for (Clause c: currentClauses) {
            res.append(c.toString() +"\n");
        }
        return res.toString();
    }


    //Removing all resolved clauses from the current set of clauses
    private void removeClauses() {
        resolvedClauses.addAll( currentClauses.stream().filter(s -> s.isResolved()).collect(Collectors.toSet()));
        currentClauses = currentClauses.stream().filter(s -> !s.isResolved()).collect(Collectors.toSet());
         Set<Clause> newClauseSet =new  HashSet<>();
         newClauseSet.addAll(currentClauses);
        for (Clause c1: currentClauses) {
            for (Clause c2: currentClauses) {
                if (c2.equalAtoms(c1) && c1.hashCode() != c2.hashCode() ){
                    newClauseSet.remove(c2);
                }
            }
        }
        currentClauses = newClauseSet;

    }


    //Searches for a pair of complimenting Atoms in the set of current clauses
    private Set<Clause> searchForCompliments(Set<Clause> clauses){
        Set<Clause> temp = new HashSet<>();
        for (Clause c1: clauses) {
            if (!c1.isResolved()) {
            for (Clause c2 : clauses) {
                if (!(c1.equals(c2)) && !c1.isResolved() && !c2.isResolved() && !c1.isChildOf(c2) && !c2.isChildOf(c1)) {
                    for (Atom a : c1.getAtoms()) {
                        if (c2.contains(a.getComplement()) && !a.isResolved && !a.getComplement().isResolved) {
                            //If one is found, the clauses containing the pair is resolved
                            //A new clause the child clause is created
                            //All Atoms except the complimenting pair is added to the child clause
                            noSteps++;
                            noResolvedAtoms+=2;
                            c1.resolveAtom(a);
                            Atom complement = c2.getAtom(a.getComplement());
                            c2.resolveAtom(complement);
                            c2.resolveAtom(a.getComplement());

                            if(isMinimal){ //If a Minimal proof is wanted
                            Clause child = new Clause();
                            child.setParent(new HashSet<>(Arrays.asList(c1, c2)));
                            Set<Atom> atomSet = c1.getAtoms().stream().filter(s -> !s.equals(a)).collect(Collectors.toSet());
                            atomSet.addAll(c2.getAtoms().stream().filter(s -> !s.equals(complement)).collect(Collectors.toSet()));
                                    child.addAllAtoms(atomSet);
                                    //All other complimenting pairs are removed from the child clause
                                    child = removeAllCompliments(child);
                                    if(child.getAtoms().size() >0) {
                                        temp.add(child);
                                    }


                            } else if(containsCompliments(c1,c2)){ //If default proof is wanted and
                                                                    // there exists another pair of complimenting literals
                                Set<Atom> atomSet1 = c1.getAtoms().stream().filter(s -> !s.equals(a)).collect(Collectors.toSet());
                                Set<Atom> atomSet2=c2.getAtoms().stream().filter(s -> !s.equals(complement)).collect(Collectors.toSet());
                                if(atomSet1.size()>0) { //Onl
                                    Clause child1 = new Clause(atomSet1);
                                    temp.add(child1);
                                }
                                if (atomSet2.size() >0) {
                                    Clause child2 = new Clause(atomSet2);
                                    temp.add(child2);
                                }

                            } else {//If default proof is wanted and no other pairs of complimenting literals
                                    Set<Atom> atomSet = c1.getAtoms();
                                    atomSet.addAll(c2.getAtoms());
                                    Clause child = new Clause(atomSet);
                                    child.setParent(new HashSet<>(Arrays.asList(c1, c2)));

                                }
                            }
                        }

                    }
                }
            }
        }


        return temp;
    }

    //Used in minimal proof, removes all complimenting literals from a clause
    private Clause removeAllCompliments(Clause c) {
        Clause res = new Clause();
        Set<Atom> atoms = new HashSet<>();
        Set<Atom> resolvedAtoms = new HashSet<>();

        for (Atom a: c.getAtoms()) {
            if(!c.contains(a.getComplement()) && !resolvedAtoms.contains(a)) {
                noResolvedAtoms = +2;
                atoms.add(a);
                resolvedAtoms.add(a);
                resolvedAtoms.add(a.getComplement());
            }
        }
        res.addAllAtoms(atoms);

        return res;
    }

    //Used to check two clauses for complimenting literals
    private boolean containsCompliments(Clause c1, Clause c2) {
        for (Atom a : c1.getAtoms()) {
            if (c2.contains(a.getComplement())) { return true; }
        }
        return false;
    }

    //Used to check if current clauses contains complimenting literals
    private boolean containsComplimentingPairs(){
        for (Clause c1:currentClauses) {
            for (Clause c2: currentClauses){
                if (containsCompliments(c1,c2)){
                  return true;
                }
            }
        }
        return false;
    }

    //Getter methods for different properties of the proof
    public Set<Clause> getCurrentClauses(){
        return currentClauses;
    }
    public int getNoSteps(){ return noSteps; }
    public int getNoResolvedAtoms(){ return noResolvedAtoms; }
    public int getNoTotalClauses() {
        return noTotalClauses;
    }
    public int noTotalSymbols(){
        int res=0;
        for (Clause c: allClauses) {
           res+=c.getAtoms().size();
        }
        return res;
    }
    public int noSymbols(){
        int res=0;
        for (Clause c: currentClauses) {
            res+=c.getAtoms().size();
        }
        return res;
    }
    public Set<Clause> getResolvedClauses() {
        return resolvedClauses;
    }
    public int noResolvedClauses() {
        return resolvedClauses.size();
    }

    public String getComplexity(){
        StringBuilder compl = new StringBuilder();
        compl.append("No. clauses total: "+getNoTotalClauses() +"\n" );
        compl.append("No. symbols total: "+noTotalSymbols() +"\n" );
        compl.append("No. symbols in result: "+noSymbols() +"\n" );
        compl.append("No. steps: "+getNoSteps() +"\n" );
        compl.append("No. resolved atoms total: "+getNoResolvedAtoms() +"\n" );
        compl.append("No. resolved clauses total: "+noResolvedClauses() +"\n" );
        return compl.toString();
    }
}

