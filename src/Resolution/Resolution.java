package Resolution;

import FeedbackTypes.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Resolution {
     Set<Clause> allClauses = new HashSet<>();
    public  String resultString;


    public Resolution(Feedback feedback){
        this.allClauses = feedback.getClauses();
        printClauses();
        resultString = resolve();
    }

    private void printClauses() {
        for (Clause c: allClauses
             ) {
             System.out.println(c.toString());
        }
    }

    public Resolution(Feedback feedback, Set<Clause> premises){
        mergeRedundantClauses(feedback.getClauses(),premises);
        printClauses();

        resultString =  resolve();
    }

    private void mergeRedundantClauses(Set<Clause> feedback, Set<Clause> premises) {
        //Merge all atoms to same object
        //Find overlapping atoms
        //replace them in premises
        Set<Clause> totalClauseSet = new HashSet<>();

        for (Clause c1: feedback) {
            totalClauseSet.add(c1);
            for (Clause c2:premises) {
                if (!c1.equals(c2)){
                    totalClauseSet.add(c2);
                }
            }
        }

        allClauses.addAll(totalClauseSet);

    }

    private String resolve() {
        Set<Clause> childClauses = searchForCompliments(allClauses);
        while (!childClauses.isEmpty() && !allClauses.containsAll(childClauses)){
            removeClauses();
            allClauses.addAll(childClauses);
            childClauses=searchForCompliments(allClauses);
        }


        StringBuilder res = new StringBuilder();
        for (Clause c: allClauses) {
            res.append(c.toString() +"\n");
        }
        return res.toString();
    }

    private void removeClauses() {
         allClauses = allClauses.stream().filter(s -> !s.isResolved()).collect(Collectors.toSet());
    }


    private Set<Clause> searchForCompliments(Set<Clause> clauses){
        Set<Clause> temp = new HashSet<>();

        for (Clause c1: clauses) {
            if (!c1.isResolved()) {
            for (Clause c2 : clauses) {
                if (!(c1.equals(c2)) && !c1.isResolved() && !c2.isResolved() && !c1.isChildOf(c2) && !c2.isChildOf(c1)) {
                    for (Atom a : c1.getAtoms()) {
                        if (c2.contains(a.getComplement()) && !a.isResolved && !a.getComplement().isResolved) {
                            Clause child = new Clause();
                            c1.resolveAtom(a);
                            Atom complement = c2.getAtom(a.getComplement());
                            c2.resolveAtom(complement);
                            c2.resolveAtom(a.getComplement());

                            child.setParent(new HashSet<>(Arrays.asList(c1, c2)));
                            Set<Atom> atomSet = c1.getAtoms().stream().filter(s -> !s.equals(a)).collect(Collectors.toSet());
                            atomSet.addAll(c2.getAtoms().stream().filter(s -> !s.equals(complement)).collect(Collectors.toSet()));
                            child.addAllAtoms(atomSet);

                           /* System.out.println("Resolved: " + a.stringRep + " and " + complement.stringRep);
                            System.out.println("Parents: " + c1.toString() + " and " + c2.toString());*/
                            child = removeAllCompliments(child);
                            temp.add(child);
                          //  System.out.println("No. children: " + temp.size());
                        }

                    }
                }
            }
        }
        }
        return temp;
    }

    private Clause removeAllCompliments(Clause c) {
        Clause res = new Clause();
        Set<Atom> atoms = new HashSet<>();
        Set<Atom> resolvedAtoms = new HashSet<>();

        for (Atom a: c.getAtoms()) {
            if(!c.contains(a.getComplement()) && !resolvedAtoms.contains(a)){
                atoms.add(a);
                resolvedAtoms.add(a);
                resolvedAtoms.add(a.getComplement());
            //} else {
             //   System.out.println("Resolved: " +a.stringRep+ " and it's compliment!");
            }
        }
        res.addAllAtoms(atoms);
       // System.out.println("Child clause after elimination compliments: " +res.toString());

        return res;
    }


    public Set<Clause> getAllClauses(){
        return allClauses;
    }
}

