package FeedbackTypes;

public class Atom {
    String stringRep;
    String position;
    String color;
    Boolean isNegated;
    Boolean isResolved;


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
        System.out.println("Atom: " +color+" pos "+position+ " stringRep: " + s);
    }

    public Boolean equals(Atom other) {
        return (color.equals(other.color) && position.equals(other.position));
    }

    public Atom getComplement(){
        if(isNegated){
            return new Atom(color + " "+position);
        } else {
            return new Atom("!"+color + " "+position);
        }
    }
}
