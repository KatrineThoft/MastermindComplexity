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

    public  int noOperators(){
        long count = boolTrans.chars().filter(ch -> ch == '&').count();
        count += boolTrans.chars().filter(ch -> ch == '|').count();
        return (int) count/2;
    }

    public int noSymbols() {
        String res, temp1,temp2, temp3;
        temp1 = boolTrans.replace(" ","");
        temp2 = temp1.replace("!","");
        temp1 = temp2.replace("&&","&");
        temp2= temp1.replace("||","|");
        temp1 = temp2.replace("x","");
        temp2= temp1.replace("y","");
        temp1 = temp2.replace("z","");
        temp2= temp1.replace("w","");
        res = temp2.replace("_","");
              return res.length();
    }


    public String getBoolTrans(){
        return boolTrans;
    }

    public Set<Clause> getClauses(){ return clauses; }

    public abstract int getBoolTransDepth();

    public abstract int noXOR();





}
