package FeedbackTypes;


//Class representing an atom or a literal in a Boolean translation
public class Atom {
    public String stringRep;
    String position;
    String color;
    Boolean isNegated;
    public Boolean isResolved = false;


    public Atom(String s){
        stringRep = s;
        if(s.contains("!")) {
            isNegated = true;
            s = s.replace("!","");
        } else{
            isNegated = false;
        }

        String[] getPosAndCol = s.split("_");
        color = getPosAndCol[0];
        position = getPosAndCol[1];
    }

    public boolean equals(Atom other) {
        return (color.equals(other.color) && position.equals(other.position)) &&(isNegated == other.isNegated);
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
