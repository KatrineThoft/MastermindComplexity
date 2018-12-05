

import FeedbackTypes.*;
import ProofMethods.*;

import java.util.*;


//Class used to run the proof methods from
public class Runner {

    public static void main(String[] args){
        /* 685
        {'colours':['ROYGBP', '444000'], 'pins':4, 'tried':[['OYRR', 'GGGR'],['RYRR', 'GGOR']], 'triesLeft':1}

         correct: ORRR */

        String item685Guess1 = "R_x,Y_y,R_z,R_w";
        Feedback item685Feedback1 = new GGOR(item685Guess1);
        String item685Guess2 = "O_x,Y_y,R_z,R_w";
        Feedback item685Feedback2 = new GGGR(item685Guess2);
        String item685Conclusion1 = "O_x,Y_y,R_z,R_w";
        String item685Conclusion2 = "O_x,R_y,R_z,R_w";

        System.out.println("---------> Boolean translation <------------  ");
        System.out.println("Guess: " + item685Guess1 +"\n Feedback: "+ item685Feedback1.getType() +"\n" +
                "Complexity: \n"+ item685Feedback1.getComplexity() );
        System.out.println("Guess: " + item685Guess2 +"\n Feedback: "+ item685Feedback2.getType() +"\n" +
                "Complexity: \n"+ item685Feedback2.getComplexity()  );

        System.out.println("---------> Resolution <------------  ");
        resolutionFourPinDMM(item685Feedback1,false);
        resolutionFourPinDMM(item685Feedback2,false);

         System.out.println("---------> Natural Deduction <------------ ");
        naturalDedFourPinDMM(item685Feedback1,item685Conclusion1);
        naturalDedFourPinDMM(item685Feedback2,item685Conclusion2);

       /* 686:
        rating -1.62
        {'colours':['ROYGBP', '444000'], 'pins':4, 'tried':[['ROOO', 'RRRR']], 'triesLeft':1}
        Correct: YYYY*/

       /*String item686Guess1 = "R_x,O_y,O_z,O_w";
        Feedback item686Feedback1 = new RRRR(item686Guess1);
        String item686Conclusion1 = "Y_x,Y_y,Y_z,Y_w";


        System.out.println("---------> Boolean translation <------------  ");
        System.out.println("Guess: " + item686Guess1 +"\n Feedback: "+ item686Feedback1.getType() +"\n" +
                "Complexity: \n"+ item686Feedback1.getComplexity() );

        System.out.println("---------> Resolution <------------  ");
        resolutionFourPinDMM(item686Feedback1,false);

        System.out.println("---------> Natural Deduction <------------ ");
        naturalDedFourPinDMM(item686Feedback1,item686Conclusion1);

*/

        /*698
        rating: 5.63
        {'colours':['ROYGBP', '444000'], 'pins':4, 'tried':[['OYYR', 'GOOR'],['RYRY', 'GORR'],['YOYY', 'GORR']], 'triesLeft':1}
        correct: OROY*/

        /*String item698Guess1 = "Y_x,O_y,Y_z,Y_w";
        Feedback item698Feedback1 = new GORR(item698Guess1);
        String item698Guess2 = "R_x,Y_y,R_z,Y_w";
        Feedback item698Feedback2 = new GORR(item698Guess2);
        String item698Guess3 = "O_x,Y_y,Y_z,R_w";
        Feedback item698Feedback3 = new GOOR(item698Guess3);
        String item698Conclusion1 = "R_x,Y_y,R_z,Y_w";
        String item698Conclusion2 = "O_x,Y_y,Y_z,R_w";
        String item698Conclusion3 = "O_x,R_y,O_z,Y_w";

        System.out.println("---------> Boolean translation <------------  ");
        System.out.println("Guess: " + item698Guess1 +"\n Feedback: "+ item698Feedback1.getType() +"\n" +
                "Complexity: \n"+ item698Feedback1.getComplexity() );
        System.out.println("Guess: " + item698Guess2 +"\n Feedback: "+ item698Feedback2.getType() +"\n" +
                "Complexity: \n"+ item698Feedback2.getComplexity() );
        System.out.println("Guess: " + item698Guess3 +"\n Feedback: "+ item698Feedback3.getType() +"\n" +
                "Complexity: \n"+ item698Feedback3.getComplexity() );

        System.out.println("---------> Resolution <------------  ");
        resolutionFourPinDMM(item698Feedback1,false);
        resolutionFourPinDMM(item698Feedback2,false);
        resolutionFourPinDMM(item698Feedback3,false);

        System.out.println("---------> Natural Deduction <------------ ");
        naturalDedFourPinDMM(item698Feedback1,item698Conclusion1);
        naturalDedFourPinDMM(item698Feedback2,item698Conclusion2);
        naturalDedFourPinDMM(item698Feedback3,item698Conclusion3);*/



    }

    private static void testAll2PpinBoolTrans() {
        String guess = "G_x,R_y";
        String conclusion1 = "P_x,Y_y";
        String conclusion2 = "R_x,P_y";
        String conclusion3 = "R_x,G_y";
        String conclusion4 = "G_x,O_y";
        List<String> conclusions = new ArrayList<>();
        conclusions.addAll(Arrays.asList(conclusion1,conclusion2,conclusion3,conclusion4));

        Feedback rr = new RR(guess);
        Feedback or = new OR(guess);
        Feedback oo = new OO(guess);
        Feedback gr = new GR(guess);
        List<Feedback> feedbacks = new ArrayList<>();
        feedbacks.addAll(Arrays.asList(rr,or,oo,gr));

        for (int i = 0; i < feedbacks.size(); i++) {
            System.out.println(feedbacks.get(i).getType());
           System.out.println(feedbacks.get(i).getBoolTrans());
            System.out.println("------------->Bool. trans. done!<------------------");
            resolutionTwoPinDMM(feedbacks.get(i), false);
            System.out.println("------------->Resolution done!<------------------");
            naturalDedTwoPinDMM(feedbacks.get(i), conclusions.get(i));
            System.out.println("------------->Nat. Ded. done!<------------------");
        }
        System.out.println("------------->2 pin done! done!<------------------");


    }

    private static void testAll4PpinBoolTrans() {
      String guess = "G_x,R_y,O_z,B_w";
      String conclusion1 = "P_x,P_y,P_z,Y_w";
      String conclusion2 = "R_x,P_y,P_z,Y_w";
      String conclusion3 = "R_x,P_y,P_z,O_z";
      String conclusion4 = "R_x,G_y,P_z,O_w";
      String conclusion5 = "R_x,G_y,B_z,O_w";
      String conclusion6 = "G_x,B_y,R_z,O_w";
      String conclusion7 = "G_x,R_y,B_z,O_w";
      String conclusion8 = "G_x,R_y,Y_z,O_w";
      String conclusion9 = "G_x,R_y,O_z,Y_w";
      String conclusion10 = "G_x,R_y,Y_z,Y_w";
      String conclusion11 = "G_x,Y_y,Y_z,Y_w";
      String conclusion12 = "G_x,Y_y,R_z,Y_w";
      String conclusion13 = "G_x,B_y,R_z,Y_w";

      List<String> conclusions = new ArrayList<>();
      conclusions.addAll(Arrays.asList(conclusion1,conclusion2,conclusion3,conclusion4,conclusion5,conclusion6,
              conclusion7,conclusion8,conclusion9,conclusion10,conclusion11,conclusion12,conclusion13));


        Feedback rrrr = new RRRR(guess);
        Feedback orrr = new ORRR(guess);
        Feedback oorr = new OORR(guess);
        Feedback ooor = new OOOR(guess);
        Feedback oooo = new OOOO(guess);
        Feedback gooo = new GOOO(guess);
        Feedback ggoo = new GGOO(guess);
        Feedback ggor = new GGOR(guess);
        Feedback gggr = new GGGR(guess);
        Feedback ggrr = new GGRR(guess);
        Feedback grrr = new GRRR(guess);
        Feedback gorr = new GORR(guess);
        Feedback goor = new GOOR(guess);
        List<Feedback> feedbacks = new ArrayList<>();
        feedbacks.addAll(Arrays.asList(rrrr,orrr,oorr,ooor,oooo,gooo,
                ggoo,ggor,gggr,ggrr,grrr,gorr,goor));

        for (int i = 0; i < feedbacks.size(); i++) {

            System.out.println(feedbacks.get(i).getType());
            System.out.println(feedbacks.get(i).getComplexity());
            System.out.println("------------->Bool trans done!<------------------");
            resolutionFourPinDMM(feedbacks.get(i), false);
            System.out.println("------------->Resolution done!<------------------");
            naturalDedFourPinDMM(feedbacks.get(i), conclusions.get(i));
            System.out.println("------------->feedback done!<------------------");

        }
        System.out.println("------------->4 pin done! done!<------------------");


    }

    private static void testCases2pin() {
        // Map w. a list of guesses from an item, the corresponding feedback and the conclusion of each guess
        Map<List<String>, Map.Entry<List<Feedback>,List<String>>> allItems = new HashMap<>();

        //Item 1:
        //1,25769,"['OO', 'RR']","RR",2,1

        String item1Guess1 = "O_x,O_y";
        Feedback item1Feedback1 = new RR(item1Guess1);
        List<String> item1Guesses = new ArrayList<>();
        item1Guesses.add(item1Guess1);


        List<Feedback> item1FeedBack = new ArrayList<>();
        item1FeedBack.add(item1Feedback1);


        List<String> item1Conclusion = new ArrayList<>();
        item1Conclusion.add("R_x,R_y");
        allItems.put(item1Guesses,new AbstractMap.SimpleEntry(item1FeedBack, item1Conclusion));

        //Item 2:
        //62,125605,"['YY', 'GR'],['BY', 'OO']","YB",5,2,-20.577827889119,-4.6785575316,-15.899270357519,80034
       String item2Guess1 = "B_x,Y_y";
        Feedback item2Feedback1 = new OO(item2Guess1);
        String item2Guess2 = "Y_x,Y_y";
        Feedback item2Feedback2 = new GR(item2Guess2);

        List<String> item2Guesses = new ArrayList<>();
        item2Guesses.add(item2Guess1);
        item2Guesses.add(item2Guess2);

        List<Feedback> item2FeedBack = new ArrayList<>();
        item2FeedBack.add(item2Feedback1);
        item2FeedBack.add(item2Feedback2);

        List<String> item2Conclusion = new ArrayList<>();
        item2Conclusion.add("Y_x,Y_y");
        item2Conclusion.add("Y_x,B_y");

        allItems.put(item2Guesses,new AbstractMap.SimpleEntry(item2FeedBack, item2Conclusion));
        //Item 3:
        //95,125806,"['YY', 'RR'],['RR', 'GR'],['RO', 'OO']","OR",3,3,-17.234609854541,-4.3123002203,-12.922309634241,23086


        String item3Guess1 = "R_x,O_y";
        Feedback item3Feedback1 = new OO(item3Guess1);
        String item3Guess2 = "R_x,R_y";
        Feedback item3Feedback2 = new RR(item3Guess2);
        String item3Guess3 = "Y_x,Y_y";
        Feedback item3Feedback3 = new RR(item3Guess3);

        List<String> item3Guesses = new ArrayList<>();
        item3Guesses.add(item3Guess1);
        item3Guesses.add(item3Guess2);
        item3Guesses.add(item3Guess3);

        List<Feedback> item3FeedBack = new ArrayList<>();
        item3FeedBack.add(item3Feedback1);
        item3FeedBack.add(item3Feedback2);
        item3FeedBack.add(item3Feedback3);

        List<String> item3Conclusion = new ArrayList<>();
        item3Conclusion.add("R_x,R_y");
        item3Conclusion.add("Y_x,Y_y");
        item3Conclusion.add("O_x,R_y");

        allItems.put(item3Guesses,new AbstractMap.SimpleEntry(item3FeedBack, item3Conclusion));

        //Item 4:
        //18,25846,"['RG', 'OO']","GR",5,1,-30.113825547635,3.3,-33.413825547635,297633
       String item4Guess1 = "R_x,G_y";
       Feedback item4Feedback1 = new OO(item4Guess1);
        List<String> item4Guesses = new ArrayList<>();
        item4Guesses.add(item4Guess1);

        List<Feedback> item4FeedBack = new ArrayList<>();
        item4FeedBack.add(item4Feedback1);

        List<String> item4Conclusion = new ArrayList<>();
        item4Conclusion.add("G_x,R_y");

        allItems.put(item4Guesses,new AbstractMap.SimpleEntry(item4FeedBack, item4Conclusion));

        //Item 5:
      //89,25826,"['OG', 'GR'],['OR', 'GR'],['OY', 'GR']","OO",4,3,-18.216049549916,2.7,-20.916049549916,186656
        String item5Guess1 = "O_x,Y_y";
        Feedback item5Feedback1 = new GR(item5Guess1);
        String item5Guess2 = "O_x,R_y";
        Feedback item5Feedback2 = new GR(item5Guess2);
        String item5Guess3 = "O_x,G_y";
        Feedback item5Feedback3 = new GR(item5Guess3);

        List<String> item5Guesses = new ArrayList<>();
        item5Guesses.add(item5Guess1);
        item5Guesses.add(item5Guess2);
        item5Guesses.add(item5Guess3);

        List<Feedback> item5FeedBack = new ArrayList<>();
        item5FeedBack.add(item5Feedback1);
        item5FeedBack.add(item5Feedback2);
        item5FeedBack.add(item5Feedback3);

        List<String> item5Conclusion = new ArrayList<>();
        item5Conclusion.add("O_x,R_y");
        item5Conclusion.add("O_x,G_y");
        item5Conclusion.add("O_x,O_y");

        allItems.put(item5Guesses,new AbstractMap.SimpleEntry(item5FeedBack, item5Conclusion));

        for (List<String> item: allItems.keySet()) {
            for (int i = 0; i < item.size(); i++) {
                //  System.out.println(allItems.get(item).getKey().get(i).getGuess() + "is getting resolved");
                System.out.println(allItems.get(item).getKey().get(i).getComplexity());

            }
            System.out.println("------------->Item done!<------------------");
        }
        System.out.println("------------->Bool. trans. done!<------------------");

        for (List<String> item: allItems.keySet()) {
            for (int i = 0; i < item.size(); i++) {
                // System.out.println(allItems.get(item).getKey().get(i).getGuess() + "is getting resolved");
                resolutionFourPinDMM(allItems.get(item).getKey().get(i),false);

            }
               System.out.println("------------->Item done!<------------------");
        }
        System.out.println("------------->Resolution done!<------------------");

        for (List<String> item : allItems.keySet()) {
            for (int i = 0; i < item.size(); i++) {
                naturalDedFourPinDMM(allItems.get(item).getKey().get(i), allItems.get(item).getValue().get(i));
            }
             System.out.println("------------->Item done!<------------------");
        }
        System.out.println("------------->Nat. ded. done!<------------------");

    }

    private static void testCases4pin() {
        // Map w. a list of guesses from an item, the corresponding feedback and the conclusion of each guess
        Map<List<String>, Map.Entry<List<Feedback>,List<String>>> allItems = new HashMap<>();

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

        //ITEM: 657
        //{'colours':['ROYGBP', '440000'], 'pins':4, 'tried':[['ROOR', 'GGGR'],['OOOR', 'GGOO']], 'triesLeft':1}
        //correct: ROOO
        String item6Guess1 = "O_x,O_y,O_z,R_w";
        Feedback item6Feedback1 = new GGOO(item6Guess1);
        String item6Guess2 = "R_x,O_y,O_z,R_w";
        Feedback item6Feedback2 = new GGGR(item6Guess2);

        List<String> item6Guesses = new ArrayList<>();
        item6Guesses.add(item6Guess1);
        item6Guesses.add(item6Guess2);

        List<Feedback> item6FeedBack = new ArrayList<>();
        item6FeedBack.add(item6Feedback1);
        item6FeedBack.add(item6Feedback2);
        List<String> item6Conclusion = new ArrayList<>();
        item6Conclusion.add("R_x,O_y,O_z,R_w");
        item6Conclusion.add("R_x,O_y,O_z,O_w");
        allItems.put(item6Guesses,new AbstractMap.SimpleEntry(item6FeedBack, item6Conclusion));

      for (List<String> item: allItems.keySet()) {
            for (int i = 0; i < item.size(); i++) {
                System.out.println(allItems.get(item).getKey().get(i).getType());
                System.out.println(allItems.get(item).getKey().get(i).getComplexity());

            }
           System.out.println("------------->Item done!<------------------");
        }
        System.out.println("------------->Bool. trans. done!<------------------");

            for (List<String> item: allItems.keySet()) {
                for (int i = 0; i < item.size(); i++) {
                    resolutionFourPinDMM(allItems.get(item).getKey().get(i),false);

                }
              System.out.println("------------->Item done!<------------------");
            }
            System.out.println("------------->Resolution done!<------------------");

            for (List<String> item : allItems.keySet()) {
                for (int i = 0; i < item.size(); i++) {

                    System.out.println(allItems.get(item).getKey().get(i).getType());
                    naturalDedFourPinDMM(allItems.get(item).getKey().get(i), allItems.get(item).getValue().get(i));
                }
                System.out.println("------------->Item done!<------------------");
            }
        System.out.println("------------->Nat. ded. done!<------------------");
    }

    //Executing Natural Deduction  on 2-pin DMM examples
    private static void naturalDedTwoPinDMM(Feedback feedback, String conclusion) {
        NaturalDeduction natDed1 = new NaturalDeduction(feedback,conclusion);
        System.out.println("Guees: "+ feedback.getGuess() +"\n Feedback: "+feedback.getType());
        System.out.println("Complexity: \n"+natDed1.getComplexity());
       // System.out.println("Result: \n"+natDed1.getResultString());


    }


    //Executing Natural Deduction  on 4-pin DMM examples
    private static void naturalDedFourPinDMM(Feedback feedback, String conclusion) {
        NaturalDeduction natDed1 = new NaturalDeduction(feedback,conclusion);
        //System.out.println("Result after deduction: \n" +natDed1.getResultString()+"\n");
        System.out.println("Guees: "+ feedback.getGuess() +"\n Feedback: "+feedback.getType());
        System.out.println("Complexity: \n"+natDed1.getComplexity());
     //   System.out.println("Result: \n"+natDed1.getResultString());

    }

    //Executing resolution on 2-pin DMM examples
    private static void resolutionTwoPinDMM(Feedback feedback, boolean isMinimal) {
        Resolution resolution = new Resolution(feedback,isMinimal);
        System.out.println("Guees: "+ feedback.getGuess() +"\n Feedback: "+feedback.getType());
        System.out.println("Complexity: \n"+resolution.getComplexity());
        //System.out.println("Result: \n"+resolution.resultString);
    }

    //Executing resolution on 4-pin DMM examples
    private static void resolutionFourPinDMM(Feedback feedback, boolean isMinimal) {
        Resolution resolution = new Resolution(feedback,isMinimal);
        System.out.println("Resolving guess " + feedback.getGuess() +" on feedback bool Trans: " +feedback.getType());
        System.out.println("Result after resolving guess: \n" +resolution.resultString+"\n");
        System.out.println("Complexity: \n"+resolution.getComplexity());
        //System.out.println("Result: \n"+resolution.resultString);
    }
}
