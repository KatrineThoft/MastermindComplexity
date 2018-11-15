

import FeedbackTypes.*;
import ProofMethods.*;

import java.util.*;


//Class used to run the proof methods from
public class Runner {

    public static void main(String[] args){

        testCases4pin(false);
       // testCases2pin(true);


    }

    private static void testCases2pin(boolean isResolution) {
    }

    private static void testCases4pin(boolean isResolution) {
        // Map w. a list of guesses from an item, the corresponding feedback and the conclusion of each guess
        Map<List<String>, Map.Entry<List<Feedback>,List<String>>> allItems = new HashMap<>();
/*
        String item1Guess1 = "O_x,R_y,O_z,R_w";
        Feedback item1Feedback1 = new GGOO(item1Guess1);
        String item1Guess2 = "R_x,O_y,O_z,R_w";
        Feedback item1Feedback2 = new OOOO(item1Guess2);
        List<String> item1Guesses = new ArrayList<>();
        item1Guesses.add(item1Guess1);
        item1Guesses.add(item1Guess2);

        List<Feedback> item1FeedBack = new ArrayList<>();
        item1FeedBack.add(item1Feedback1);
        item1FeedBack.add(item1Feedback2);

        List<String> item1Conclusion = new ArrayList<>();
        item1Conclusion.add("R_x,O_y,O_z,R_w");
        item1Conclusion.add("R_x,O_y,R_z,O_w");

        allItems.put(item1Guesses,new AbstractMap.SimpleEntry(item1FeedBack, item1Conclusion));

        String item2Guess1 = "O_x,R_y,R_z,O_w";
        Feedback item2Feedback1 = new GGOO(item2Guess1);
        String item2Guess2 = "O_x,R_y,O_z,R_w";
        Feedback item2Feedback2 = new GGOO(item2Guess2);
        String item2Guess3 = "R_x,O_y,R_z,R_w";
        Feedback item2Feedback3 = new GOOR(item2Guess3);
        List<String> item2Guesses = new ArrayList<>();
        item2Guesses.add(item2Guess1);
        item2Guesses.add(item2Guess2);
        item2Guesses.add(item2Guess3);


        List<Feedback> item2FeedBack = new ArrayList<>();
        item2FeedBack.add(item2Feedback1);
        item2FeedBack.add(item2Feedback2);
        item2FeedBack.add(item2Feedback3);

        List<String> item2Conclusion = new ArrayList<>();
        item2Conclusion.add("O_x,R_y,O_z,R_w");
        item2Conclusion.add("R_x,O_y,R_z,R_w");
        item2Conclusion.add("R_x,R_y,O_z,O_w");
        allItems.put(item2Guesses,new AbstractMap.SimpleEntry(item2FeedBack, item2Conclusion));


        String item3Guess1 = "O_x,R_y,R_z,R_w";
        Feedback item3Feedback1 = new OORR(item3Guess1);

        List<String> item3Guesses = new ArrayList<>();
        item3Guesses.add(item3Guess1);

        List<Feedback> item3FeedBack = new ArrayList<>();
        item3FeedBack.add(item3Feedback1);
        List<String> item3Conclusion = new ArrayList<>();
        item3Conclusion.add("R_x,O_y,O_z,O_w");


        allItems.put(item3Guesses,new AbstractMap.SimpleEntry(item3FeedBack, item3Conclusion));

        String item4Guess1 = "R_x,R_y,R_z,O_w";
        Feedback item4Feedback1 = new GGOO(item4Guess1);
        String item4Guess2 = "R_x,O_y,O_z,O_w";
        Feedback item4Feedback2 = new GGRR(item4Guess2);
        String item4Guess3 = "R_x,O_y,R_z,O_w";
        Feedback item4Feedback3 = new GGGR(item4Guess3);
        List<String> item4Guesses = new ArrayList<>();
        item4Guesses.add(item4Guess1);
        item4Guesses.add(item4Guess2);
        item4Guesses.add(item4Guess3);


        List<Feedback> item4FeedBack = new ArrayList<>();
        item4FeedBack.add(item4Feedback1);
        item4FeedBack.add(item4Feedback2);
        item4FeedBack.add(item4Feedback3);

        List<String> item4Conclusion = new ArrayList<>();
        item4Conclusion.add("R_x,O_y,O_z,O_w");
        item4Conclusion.add("R_x,O_y,R_z,O_w");
        item4Conclusion.add("R_x,O_y,R_z,R_w");
        allItems.put(item4Guesses,new AbstractMap.SimpleEntry(item4FeedBack, item4Conclusion));
*/
        String item5Guess1 = "Y_x,O_y,Y_z,Y_w";
        Feedback item5Feedback1 = new GGOO(item5Guess1);
        String item5Guess2 = "O_x,Y_y,Y_z,Y_w";
        Feedback item5Feedback2 = new GGOO(item5Guess2);
        String item5Guess3 = "O_x,O_y,Y_z,O_w";
        Feedback item5Feedback3 = new GGRR(item5Guess3);
        String item5Guess4 = "R_x,R_y,R_z,R_w";
        Feedback item5Feedback4 = new RRRR(item5Guess3);

        List<String> item5Guesses = new ArrayList<>();
        item5Guesses.add(item5Guess1);
        item5Guesses.add(item5Guess2);
        item5Guesses.add(item5Guess3);
        item5Guesses.add(item5Guess4);

        List<Feedback> item5FeedBack = new ArrayList<>();
        item5FeedBack.add(item5Feedback1);
        item5FeedBack.add(item5Feedback2);
        item5FeedBack.add(item5Feedback3);
        item5FeedBack.add(item5Feedback4);

        List<String> item5Conclusion = new ArrayList<>();
        item5Conclusion.add("O_x,Y_y,Y_z,Y_w");
        item5Conclusion.add("O_x,O_y,Y_z,O_w");
        item5Conclusion.add("R_x,R_y,R_z,R_w");
        item5Conclusion.add("Y_x,Y_y,Y_z,O_w");
        allItems.put(item5Guesses,new AbstractMap.SimpleEntry(item5FeedBack, item5Conclusion));

       // if (isResolution){
        for (List<String> item: allItems.keySet()) {
            for (int i = 0; i < item.size(); i++) {
              //  System.out.println(allItems.get(item).getKey().get(i).getGuess() + "is getting resolved");
                System.out.println(allItems.get(item).getKey().get(i).getComplexity());

            }
           // System.out.println("------------->Item done!<------------------");
        }
        System.out.println("------------->Bool. trans. done!<------------------");

            for (List<String> item: allItems.keySet()) {
                for (int i = 0; i < item.size(); i++) {
                   // System.out.println(allItems.get(item).getKey().get(i).getGuess() + "is getting resolved");
                    resolutionFourPinDMM(allItems.get(item).getKey().get(i),false);

                }
             //   System.out.println("------------->Item done!<------------------");
            }
            System.out.println("------------->Resolution done!<------------------");

       // } else {
            for (List<String> item : allItems.keySet()) {
                for (int i = 0; i < item.size(); i++) {
                    naturalDedFourPinDMM(allItems.get(item).getKey().get(i), allItems.get(item).getValue().get(i));
                }
              //  System.out.println("------------->Item done!<------------------");
            }
        System.out.println("------------->Nat. ded. done!<------------------");

        // }
    }

    private static void naturalDedTwoPinDMM(Feedback feedback, String conclusion) {
        NaturalDeduction natDed1 = new NaturalDeduction(feedback,conclusion);
        System.out.println("Complexity: \n"+natDed1.getComplexity());


    }

    private static void naturalDedFourPinDMM(Feedback feedback, String conclusion) {
        NaturalDeduction natDed1 = new NaturalDeduction(feedback,conclusion);
        //System.out.println("Result after deduction: \n" +natDed1.getResultString()+"\n");
        System.out.println("Complexity: \n"+natDed1.getComplexity());

    }

    //Executing resolution on 2-pin DMM examples
    private static void resolutionTwoPinDMM(Feedback feedback, boolean isMinimal) {
        Resolution resolution = new Resolution(feedback,isMinimal);
        System.out.println("Complexity: \n"+resolution.getComplexity());
    }

    //Executing resolution on 4-pin DMM examples
    private static void resolutionFourPinDMM(Feedback feedback, boolean isMinimal) {

        Resolution resolution = new Resolution(feedback,isMinimal);
        System.out.println("Resolving guess " + feedback.getGuess() +" on feedback bool Trans: " +feedback.getType());
        System.out.println("Result after resolving guess: \n" +resolution.resultString+"\n");
        System.out.println("Complexity: \n"+resolution.getComplexity());


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
