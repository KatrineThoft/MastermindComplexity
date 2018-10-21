package FeedbackTypes;

public class Runner {

    public static void main(String[] args){
        String guess = "R_x,O_y,Y_z,G_w";
      /* Feedback test1 = new RRRR(guess);

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
        System.out.println(test4.noAtoms());

        Feedback test5 = new GOOO(guess);

        System.out.println(test5.getBoolTrans());
        System.out.println(test5.noAtoms());

       Feedback test6 = new GORR(guess);

        System.out.println(test6.getBoolTrans());
        System.out.println(test6.noClauses());

       Feedback test7 = new GRRR(guess);

        System.out.println(test7.getBoolTrans());
        System.out.println(test7.noClauses());*/

       Feedback test8 = new OORR(guess);

        System.out.println(test8.getBoolTrans());
        System.out.println(test8.noOrOperators());

        /*Feedback test9 = new OOOO(guess);

        System.out.println(test9.getBoolTrans());
        System.out.println(test9.noClauses());

        Feedback test10 = new GGGR(guess);

        System.out.println(test10.getBoolTrans());
        System.out.println(test10.getBoolTransDepth());*/


    }
}
