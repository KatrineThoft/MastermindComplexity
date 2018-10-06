package FeedbackTypes;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class Feedback {
    private String type;
    String guess;
    Set<String> positions = new HashSet<>();

    public Feedback(String type, String guess){
        this.type = type;
        this.guess = guess;
        positions.addAll(Arrays.asList( "x","y","z","w"));
    }

    public String getType(){
        return type;
    }

    public String getGuess(){
        return guess;
    }

    public abstract String getBoolTrans();

    public abstract Set<String> splitBoolTrans();

    public abstract int getBoolTransDepth();

    public abstract String prettyPrint();

    public abstract int noSymbols();

    public abstract int noOperators();

    public abstract int noXOR();




}
