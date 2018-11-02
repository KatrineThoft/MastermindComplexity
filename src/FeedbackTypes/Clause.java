package FeedbackTypes;

import java.util.HashSet;
import java.util.Set;

//or clause
public class Clause {
   Set<Atom> atoms = new HashSet<>();
   Set<Atom> resolvedAtoms = new HashSet<>();
   Clause parent;
   boolean isResolved;

    public Clause(Set<Atom> atoms){
        this.atoms = atoms;
        isResolved = false;
    }
    public Clause(){}

    public int noOfAtoms(){
        return atoms.size();
    }

    public void addAtom(Atom a){
        this.atoms.add(a);
    }

    public void addAllAtoms(Set<Atom> atoms){
        this.atoms.addAll(atoms);
    }

    public void resolveAtom(Atom a){
        a.isResolved = true;
        resolvedAtoms.add(a);
    }

    public Set<Atom> getAtoms(){ return atoms; }

    public void setParent(Clause parent){
        this.parent = parent;
    }

    public boolean contains(Atom a){
        for (Atom a1:atoms) {
            if(a1.equals(a)){
                return true;
            }
        }
        return false;
    }

    public String toString(){
       StringBuilder res = new StringBuilder();
       res.append("{");
        for (Atom a: atoms) {
            res.append(a.stringRep + ", ");
        }
       return res.substring(0,res.lastIndexOf(",")) +"}";
    }


    public boolean isResolved(){
       return atoms.size() == resolvedAtoms.size();
    }

    public Boolean equals(Clause other) {
        for (Atom a: atoms) {
            if (!other.atoms.contains(a)){
                return false;
            }
        }
        for (Atom a: other.atoms) {
            if (!atoms.contains(a)){
                return false;
            }

        }
        return true;
    }


}
