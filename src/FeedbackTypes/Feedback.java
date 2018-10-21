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
        //colors.addAll(Arrays.asList("a","b","c","d","e","f"));
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

    public  int noAndOperators(){
        String temp = boolTrans.replace("&&","&");
        long count = temp.chars().filter(ch -> ch == '&').count();
        return (int)count;
    }

    public  int noOrOperators(){
        String temp = boolTrans.replace("||","|");
        long count = temp.chars().filter(ch -> ch == '|').count();
        return (int)count;
    }

    public int noSymbols() {
        String res;
        res=removeAllUnesserayInfo(true);
              return res.length();
    }

    //Not necessary all have depth one in CNF form
    public int getBoolTransDepth(){
        int depth=0;
        String temp= removeAllUnesserayInfo(false);
        for (String s:colors) {
            temp = temp.replace(s,"");
        }

        for (int i = 0; i < temp.length()-1;i++) {
            if (temp.charAt(i) == '(' && temp.charAt(i+1) !=')'){
                depth++;
            }
        }

        return depth;
    }

    public String getBoolTrans(){
        return boolTrans;
    }

    public Set<Clause> getClauses(){ return clauses; }

    public abstract int noXOR();

    private String removeAllUnesserayInfo(Boolean countSymbols){
        String temp1,temp2;
        temp1 = boolTrans.replace(" ","");
        temp2 = temp1.replace("!","");
        if(countSymbols) {
            temp1 = temp2.replace("&&", "&");
            temp2 = temp1.replace("||", "|");
        } else{
            temp1 = temp2.replace("&&", "");
            temp2 = temp1.replace("||", "");
        }
        temp1= temp2.replace("\n","");
        for (String s:positions) {
            temp1 = temp1.replace(s,"");
        }
       return temp1.replace("_","");
    }





}
