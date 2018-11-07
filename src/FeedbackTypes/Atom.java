package FeedbackTypes;

import java.util.Random;

public class Atom {
    public String stringRep;
    String position;
    String color;
    Boolean isNegated;
    public Boolean isResolved = false;


    public Atom(String s){
        stringRep = s;
        String neg="";
        if(s.contains("!")) {
            isNegated = true;
            neg = "!";
            s = s.replace("!","");
        } else{
            isNegated = false;
        }


        String[] getPosAndCol = s.split("_");
        color = getPosAndCol[0];
        position = getPosAndCol[1];
       // System.out.println("Atom: " +color+" pos: "+position+ " stringRep: " +neg +s);
    }

    public boolean equals(Atom other) {
        return (color.equals(other.color) && position.equals(other.position)) &&(isNegated == other.isNegated);
       // return this.hashCode() == other.hashCode();
    }

    public String getColor() {
        return color;
    }

    public String getPosition() {
        return position;
    }

    public Boolean getNegated() {
        return isNegated;
    }

    @Override
    public int hashCode() {
       return stringRep.hashCode();
    }
    public Atom getComplement(){
        if(isNegated){
            return new Atom(color + "_"+position);
        } else {
            return new Atom("!"+color + "_"+position);
        }
    }
}
