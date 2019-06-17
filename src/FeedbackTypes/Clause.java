package FeedbackTypes;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

//Class representing and OR clause in a Boolean translation
public class Clause {
   Set<Atom> atoms = new HashSet<>();
   Set<Atom> resolvedAtoms = new HashSet<>();
   Set<Clause> parents= new HashSet<>();
   private boolean isResolved;

    public Clause(Set<Atom> atoms){
        this.atoms = atoms;
        isResolved = false;
    }
    public Clause(){
        isResolved = false;
    }

    public int noOfAtoms(){
        return atoms.size();
    }

    public void addAtom(Atom a){
        this.atoms.add(a);
    }

    public void addAllAtoms(Set<Atom> atoms){
        this.atoms.addAll(atoms);
    }

    //Methods used in proofs to mark an atom as resolved
    public void resolveAtom(Atom a){
        a.isResolved = true;
        resolvedAtoms.add(a);
        isResolved = true;
    }

    public Set<Atom> getAtoms(){ return atoms; }

    public void setParent(Set<Clause> parent){
        this.parents = parent;
    }

    public boolean isChildOf(Clause c) {
        return !parents.isEmpty() && parents.contains(c);
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
        if (atoms.isEmpty()){
            return"{}";
        }
       StringBuilder res = new StringBuilder();
       res.append("{");
        for (Atom a: atoms) {
            res.append(a.stringRep + ", ");
        }
       return res.substring(0,res.lastIndexOf(",")) +"}";
    }

    public boolean isResolved(){
       return isResolved;
    }


    public boolean equals(Clause other) {
        return this.hashCode() == other.hashCode();
    }

    //Method used to check whether to Clause objects holds the same Atoms
    //Used to remove duplicate clauses.
    public boolean equalAtoms(Clause other) {
        for (Atom a : atoms) {
            if (!other.atoms.contains(a)) {
                return false;
            }
        }
        for (Atom a : other.atoms) {
            if (!atoms.contains(a)) {
                return false;
            }

        }
        return true;
    }

    public Atom getAtom(Atom a){
        for (Atom at: atoms) {
          if (at.equals(a)) {
              return a;
          }
        }
        return null;
    }

    public Atom getNegAtomByPos(String pos){
        for (Atom at: atoms) {
            if (at.getPosition().equals(pos) && at.isNegated) {
                return at;
            }
        }
        return null;
    }

    @Override
    public int hashCode() {
        int res=0;
        for (Atom a:atoms
             ) {
            res+= a.hashCode();
        }
        return res+new Random().nextInt(9);
    }
}
