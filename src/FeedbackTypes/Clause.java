package FeedbackTypes;

import java.util.HashSet;
import java.util.Set;

//or clause
public class Clause {
   Set<Atom> atoms = new HashSet<>();

    public Clause(Set<Atom> atoms){
        this.atoms = atoms;

    }
    public Clause(){
    }

    public int noOfAtoms(){
        return atoms.size();
    }

    public void addAtom(Atom a){
        this.atoms.add(a);
    }
}
