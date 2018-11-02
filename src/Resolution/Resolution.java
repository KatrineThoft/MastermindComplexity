package Resolution;

import FeedbackTypes.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Resolution {
    Set<Clause> allClauses;
    Set<Atom> resolvedAtoms;
    public  String resultString;


    public Resolution(Feedback feedback){
        this.allClauses = feedback.getClauses();
        resultString = resolve();
    }

    public Resolution(Feedback feedback, Set<Clause> premises){
        this.allClauses = feedback.getClauses();
        allClauses.addAll(premises);
        resolve();
    }

    private String resolve() {
        Set<Clause> childClauses = searchForCompliments(allClauses);

        while (!childClauses.isEmpty() && !allClauses.containsAll(childClauses)){
            allClauses.addAll(childClauses);
            childClauses=searchForCompliments(allClauses);
        }


        StringBuilder res = new StringBuilder();
        for (Clause c: allClauses) {
            res.append(c.toString() +"\n");
        }
        return res.toString();
    }

    private Set<Clause> searchForCompliments(Set<Clause> clauses){
        Set<Clause> temp = new HashSet<>();
        int i =0, j=0;
        for (Clause c1: clauses) {
            i++;
            System.out.println("Loop 1 looking at clause: "+c1.toString() +"\n i=" +i);
            for (Clause c2: clauses) {
                j++;
                System.out.println("Loop 2 looking at clause: "+c2.toString()+"\n j=" +j);
                if(!(c1.equals(c2)) && !c2.isResolved() && !c1.isResolved() && !c1.isChildOf(c2) && !c2.isChildOf(c1)){
                    for (Atom a : c1.getAtoms()) {
                        System.out.println("Searching for compliment for: "+a.stringRep);
                        if (c2.contains(a.getComplement()) && !a.isResolved &&!a.getComplement().isResolved){
                            System.out.println("Found compliment! ");
                            Clause child = new Clause();
                            c1.resolveAtom(a);
                            c2.resolveAtom(a.getComplement());
                            child.addAllAtoms(c1.getAtoms());
                            child.addAllAtoms(c2.getAtoms());
                            child.resolveAtom(a);
                            child.resolveAtom(a.getComplement());
                            child.setParent(new HashSet<>(Arrays.asList(c1, c2)));
                            temp.add(child);
                            System.out.println("Child clause: " + child.toString());
                        }

                    }
                }
            }
        }
        return temp;
    }
}
