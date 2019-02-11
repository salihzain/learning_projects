import java.util.Random;

public class prob {
    //for the sake of simplicity, I'm going to place the winning choice in the third place.
    //so imagine we have [0,0,1] : the third place, or index 2, is the winning choice.
    // my goal is to create an experiment to see how the Monty opening a door concept, and changing
    //the choice that was already made, might increase the probability of winning.
    //Since I don't really need to create a list, we're just going to imagine I have a list of [0,0,1]
    // where 0 denotes the losing choices. Keep in mind that the array above is index as follow (0,1,2)

    /*
    after the player, in this case the computer, makes a choice, Monty is going to pick one of the losing choices
    and let the computer know about it. the computer will then going to change its initial choice to the other remaining choice.
    for example, if the computer picks index 0, Monty will be index 1 and the computer will then change its choice to index
    2 that helps it win or it could be the other way around. The experiment aims to see how changing the initial choice
    might result in greater chances of winning.

    every time the method is called, two values must be passed, count and changeChoice.
    Where count stands for how many times do we want to run the experiment.
    and changeChoice stands for whether we want to change our opinion after Monty opens a door or not.
     */

    static int countProp(int count, boolean changeChoice){

        int wins = 0; // to count the successful wins
        if (changeChoice){
            for (int i = 0; i<count; i++){
                Random r = new Random();
                //picking a random number between 0 and 2
                int choice = r.nextInt(3);
                // we start by deriving a simple way to make a choice that we want to eliminate
                // in this case, we either want to eliminate hte first or the second zero. Namely, index 0 or index 1.
                int ele = r.nextInt(2);

                //making sure that the choice is not equal to the eliminated choice.
                if (choice == ele){
                    ele = ele + 1 % 2; // the module is to keep it bounded
                }

                // now we need to make a new choice based on the eliminated choice
                int newChoice;
                if (choice == 2){
                    newChoice = ele + 1 %2;
                } else {
                    newChoice = 2;
                    wins++;
                }
            }
            return wins;
        }

        //here is the code in which we don't make a new choice, very simple case
        for (int i=0; i<count;i++){
            Random r = new Random();
            //picking a random number between 0 and 2
            int choice = r.nextInt(3);

            if (choice == 2){
                wins++;
            }
        }
        return wins;
    }

    public static void main(String[]args){

        System.out.println(countProp(1000000,true));
        System.out.println(countProp(1000000,false));

    }

}
