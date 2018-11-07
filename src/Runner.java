

import FeedbackTypes.Feedback;
import FeedbackTypes.*;
import Resolution.Resolution;



public class Runner {

    public static void main(String[] args){

        twoPinDMM();
       // fourPinDMM();

    }

    private static void twoPinDMM() {
        String guess1 = "a_x,b_y";
        //Colors:("R","O","Y","G")
        Feedback test1 = new GR(guess1);
        System.out.println("Bool. Trans. of: "+ test1.getType()+ "\n" + test1.getBoolTrans());
        System.out.println("No. clauses: "+ test1.noClauses()+ "\n" + test1.noSymbols());
    }

    private static void fourPinDMM() {
        String guess1 = "R_x,G_y,R_z,G_w";

        //Colors:("R","O","Y","G","B","P")



        Feedback test1 = new GGOO(guess1);
        Resolution resolution1 = new Resolution(test1,false);
        System.out.println("Result after resolution: \n" +resolution1.resultString+"\n");
        System.out.println("Done w. resolving guess 1!");
        String guess2 = "G_x,R_y,R_z,G_w";
        Feedback test2 = new GGOO(guess2);
        //System.out.println("Boolean Translation of " +test1.getType() +":\n"+test1.getBoolTrans() +"\n");

        Resolution resolution2 = new Resolution(test2,resolution1.getAllClauses(),false);
        //   System.out.println("Boolean Translation of " +test2.getType() +":\n"+test2.getBoolTrans() +"\n");

        System.out.println("Result after resolution: \n" +resolution2.resultString+"\n");
        System.out.println("Done w. resolving guess 2!");


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
