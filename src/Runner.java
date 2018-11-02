

import FeedbackTypes.Feedback;
import FeedbackTypes.*;
import Resolution.Resolution;


public class Runner {

    public static void main(String[] args){
      // String guess = "R_x,O_y,Y_z,G_w";
        String guess = "a_x,b_y,c_z,d_w";
        Feedback test1 = new OOOR(guess);

        System.out.println("Boolean Translation of " +test1.getType() +":\n"+test1.getBoolTrans() +"\n");
        Resolution resolution = new Resolution(test1);
        System.out.println("Result after resolution: \n" +resolution.resultString+"\n");


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
