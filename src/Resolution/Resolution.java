package Resolution;

import FeedbackTypes.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Resolution {
    private Set<Clause> allClauses = new HashSet<>();
    public  String resultString;
    private int noSteps;
    private int noResolvedAtoms;
    private boolean isMinimal;


    public Resolution(Feedback feedback, boolean isMinimal){
        this.allClauses = feedback.getClauses();
        this.isMinimal = isMinimal;
        printClauses();
        resultString = resolve();
    }

    private void printClauses() { for (Clause c: allClauses) {System.out.println(c.toString()); } }

    public Resolution(Feedback feedback, Set<Clause> premises, boolean isMinimal){
       // mergeRedundantClauses(feedback.getClauses(),premises);
        this.isMinimal = isMinimal;
        allClauses.addAll(premises);
        allClauses.addAll(feedback.getClauses());
        printClauses();
        resultString =  resolve();
    }

    private void mergeRedundantClauses(Set<Clause> feedback, Set<Clause> premises) {

        Set<Clause> temp = new HashSet<>();
        Set<Atom> atoms = new HashSet<>();
        temp.addAll(feedback);
        temp.addAll(premises);
        for (Clause c1: feedback) {
            atoms.addAll(c1.getAtoms());
        }
        for (Clause c: premises) {
            Set<Atom> temp2 = c.getAtoms();
            allClauses.add(replaceAtoms(atoms,temp2));
        }

        allClauses.addAll(feedback);


    }

    private Clause replaceAtoms(Set<Atom> atoms,Set<Atom> clauseAtoms) {
        String str ="";
        Set<Atom> temp= new HashSet<>();
        for (Atom a1 : clauseAtoms) {
            for (Atom a2 : atoms) {
                if (a1.equals(a2) && !clauseAtoms.contains(a2)) {
                    temp.add(a2);
                    str = a2.stringRep;
                }
            }
        }
        Clause c =new Clause();
        if (temp.isEmpty()){
            c.addAllAtoms(clauseAtoms);
           return c;
        }
         c.addAllAtoms(temp);
        return c;
    }

    private String resolve() {
        Set<Clause> childClauses = searchForCompliments(allClauses);
        while (!childClauses.isEmpty() && !allClauses.containsAll(childClauses)){
            allClauses.addAll(childClauses);
            removeClauses();
            childClauses=searchForCompliments(allClauses);
            System.out.println("Size of child Clauses: "+ childClauses.size());
        }

        StringBuilder res = new StringBuilder();
        for (Clause c: allClauses) {
            res.append(c.toString() +"\n");
        }

        System.out.println("Size of allClauses: "+ allClauses.size());
        return res.toString();
    }


    private void removeClauses() {
         allClauses = allClauses.stream().filter(s -> !s.isResolved()).collect(Collectors.toSet());
         Set<Clause> newClauseSet =new  HashSet<>();
         newClauseSet.addAll(allClauses);
        for (Clause c1:allClauses) {
            for (Clause c2: allClauses) {
                if (c2.equalAtoms(c1) && c1.hashCode() != c2.hashCode() ){
                    newClauseSet.remove(c2);
                }
            }
        }
        allClauses = newClauseSet;

    }


    private Set<Clause> searchForCompliments(Set<Clause> clauses){
        Set<Clause> temp = new HashSet<>();

        for (Clause c1: clauses) {
            if (!c1.isResolved()) {
            for (Clause c2 : clauses) {
                if (!(c1.equals(c2)) && !c1.isResolved() && !c2.isResolved() && !c1.isChildOf(c2) && !c2.isChildOf(c1)) {
                    for (Atom a : c1.getAtoms()) {
                        if (c2.contains(a.getComplement()) && !a.isResolved && !a.getComplement().isResolved) {
                            noSteps++;
                            noResolvedAtoms+=2;
                            c1.resolveAtom(a);
                            Atom complement = c2.getAtom(a.getComplement());
                            c2.resolveAtom(complement);
                            c2.resolveAtom(a.getComplement());

                            if(isMinimal){
                            Clause child = new Clause();
                            child.setParent(new HashSet<>(Arrays.asList(c1, c2)));
                            Set<Atom> atomSet = c1.getAtoms().stream().filter(s -> !s.equals(a)).collect(Collectors.toSet());
                            atomSet.addAll(c2.getAtoms().stream().filter(s -> !s.equals(complement)).collect(Collectors.toSet()));
                            child.addAllAtoms(atomSet);
                            child = removeAllCompliments(child);
                            temp.add(child);

                            } else if(containsCompliments(c1,c2)){
                                Set<Atom> atomSet1 = c1.getAtoms().stream().filter(s -> !s.equals(a)).collect(Collectors.toSet());
                                Set<Atom> atomSet2=c2.getAtoms().stream().filter(s -> !s.equals(complement)).collect(Collectors.toSet());
                                if(atomSet1.size()>0) {
                                    Clause child1 = new Clause(atomSet1);
                                    temp.add(child1);
                                }
                                if (atomSet2.size() >0) {
                                    Clause child2 = new Clause(atomSet2);
                                    temp.add(child2);
                                }

                            } else {
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

    private boolean containsCompliments(Clause c1, Clause c2) {
        for (Atom a : c1.getAtoms()) {
            if (c2.contains(a.getComplement())) { return true; }
        }
        return false;
    }


    public Set<Clause> getAllClauses(){
        return allClauses;
    }
    public int getNoSteps(){ return noSteps; }
    public int getNoResolvedAtoms(){ return noResolvedAtoms; }
}

