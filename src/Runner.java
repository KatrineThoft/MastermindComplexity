

import FeedbackTypes.*;
import ProofMethods.*;


//Class used to run the proof methods from
public class Runner {

    public static void main(String[] args){

       // resolutionTwoPinDMM();
       //resolutionFourPinDMM();
        naturalDedTwoPinDMM();
       // naturalDedFourPinDMM();



    }

    private static void naturalDedTwoPinDMM() {
        String guess1 = "R_x,O_y";
        Feedback test1 = new OO(guess1);
        String conclusion1 = "O_x,R_y";
        NaturalDeduction natDed = new NaturalDeduction(test1,conclusion1);
        System.out.println(natDed.getResultString());

    }

    private static void naturalDedFourPinDMM() {
    }

    //Executing resolution on 2-pin DMM examples
    private static void resolutionTwoPinDMM() {
        String guess1 = "R_x,O_y";
        //Colors:("R","O","Y","G")
        Feedback test1 = new RR(guess1);
        System.out.println("Bool. Trans. of: "+ test1.getType()+ "\n" + test1.getBoolTrans());
        System.out.println("No. colors: "+ test1.noColors());

        Resolution resolution1 = new Resolution(test1,false);
        System.out.println("Result after resolution: \n" +resolution1.resultString+"\n");
        System.out.println("Done w. resolving guess 1!");

      /*  String guess2 = "G_x,R_y";
        Feedback test2 = new OO(guess2);
        Resolution resolution2 = new Resolution(test2,resolution1.getCurrentClauses(),false);
        System.out.println("Result after resolution: \n" +resolution2.resultString+"\n");
        System.out.println("Done w. resolving guess 2!");*/
    }

    //Executing resolution on 4-pin DMM examples
    private static void resolutionFourPinDMM() {
        //Colors:("R","O","Y","G","B","P")

        String guess1 = "O_x,O_y,O_z,R_w";
        Feedback test1 = new GGOO(guess1);
        Resolution resolution1 = new Resolution(test1,true);
        System.out.println("Resolving guess " + guess1 +" on feedback bool Trans: " +test1.getType() + "\n"+test1.getClauses());
        System.out.println("Result after resolving 1st guess: \n" +resolution1.resultString+"\n");


        String guess2 = "R_x,O_y,O_z,R_w";
        Feedback test2 = new GGGR(guess2);
        System.out.println("Resolving guess " + guess2 +" on feedback type: " +test2.getType()+"\n"+test2.getBoolTrans());

        Resolution resolution2 = new Resolution(test2,resolution1.getCurrentClauses(),true);

        System.out.println("Result after resolving 2nd guess: \n" +resolution2.resultString+"\n");

      /*  String guess3 = "R_x,R_y,B_z,R_w";
        Feedback test3 = new GORR(guess3);
        System.out.println("Resolving guess " + guess3 +" on feedback type: " +test3.getType());
        Resolution resolution3 = new Resolution(test3,resolution2.getCurrentClauses(),true);
        System.out.println("Result after resolving 3rd guess: \n" +resolution3.resultString+"\n");



        String guess4 = "Y_x,O_y,O_z,O_w";
        Feedback test4 = new RRRR(guess4);
        System.out.println("Resolving guess " + guess4 +" on feedback type: " +test4.getType());
        Resolution resolution4 = new Resolution(test4,resolution3.getCurrentClauses(),true);
        System.out.println("Result after resolving 4th guess: \n" +resolution4.resultString+"\n");


        /*System.out.println("No. symbols in Bool. Trans.: "+test1.noSymbols());
        System.out.println("No. unique atoms in Bool. Trans. (complimented atoms included): "+test1.noAtoms());
        System.out.println("No. clauses in Bool. Trans.: "+test1.noClauses());
        System.out.println("No. operators in total in Bool. Trans.: "+test1.noOperators());
        System.out.println("No. AND operators in Bool. Trans.: "+test1.noAndOperators());
        System.out.println("No. OR operators in Bool. Trans.: "+test1.noOrOperators());
        System.out.println("Longest OR clause  in Bool. Trans.: "+test1.longestOrClause());*/



      /*
      String guess = "a_x,b_y,c_z,d_w";
      //%OOOO,  OOOR, ORRR, GOOR, GGOR;

      Feedback test1 = new RRRR(guess);

       System.out.println(test1.getBoolTrans());
        System.out.println(test1.noOperators());

       Feedback test2 = new ORRR(guess);

        System.out.println(test2.getBoolTrans());
        System.out.println(test2.noClauses());*/



        /*Feedback test3 = new GGOO(guess);

        System.out.println(test3.getBoolTrans());
        System.out.println(test3.noAtoms());

        Feedback test4 = new GGRR(guess);

        System.out.println(test4.getBoolTrans());
        System.out.println(test4.noAtoms());*/

       /*Feedback test6 = new GORR(guess);

        System.out.println(test6.getBoolTrans());
        System.out.println(test6.noClauses());

       Feedback test7 = new GRRR(guess);

        System.out.println(test7.getBoolTrans());
        System.out.println(test7.noClauses());

       Feedback test8 = new OORR(guess);

        System.out.println(test8.getBoolTrans());
        System.out.println(test8.noOrOperators());

        /*Feedback test9 = new OOOO(guess);

        System.out.println(test9.getBoolTrans());
        System.out.println(test9.noClauses());

        Feedback test10 = new GGGR(guess);

        System.out.println(test10.getBoolTrans());
        System.out.println(test10.getBoolTransDepth());

        Feedback test11 = new OOOO(guess);

        System.out.println(test11.getBoolTrans());*/

    }
}
