package FeedbackTypes;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class Feedback {
    private String type;
    String boolTrans;
    String guess;
    Set<String> positions = new HashSet<>();
    Set<String> colors = new HashSet<>();
    Set<Clause> clauses=new HashSet<>();

    public Feedback(String type, String guess){
        this.type = type;
        this.guess = guess;
        positions.addAll(Arrays.asList("x","y","z","w"));
        colors.addAll(Arrays.asList("R","O","Y","G","B","P"));
    }

    public String getType(){
        return type;
    }

    public String getGuess(){
        return guess;
    }

    public int noAtoms(){
        Set<Atom> allUniqueAtoms = new HashSet<>();
        for (Clause c: clauses) {
            allUniqueAtoms.addAll(c.atoms);
        }
        return allUniqueAtoms.size();
    }

    public int noClauses(){
        return clauses.size();
    }

    public String getBoolTrans(){
        return boolTrans;
    }

    public Set<Clause> getClauses(){ return clauses; }

    public abstract int getBoolTransDepth();

    public abstract int noSymbols();

    public abstract int noOperators();

    public abstract int noXOR();





}
