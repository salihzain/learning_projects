import java.util.Random;

public class PropWithCoin {

/*
This is case two of the probability experiment.

Imagine 3 doors, 0,1,2 ... There's a car behind one of these doors. We pick one randomly.
Let's say we picked door 1, Monty then comes and picks the losing door out of 0 or 2 and assures us that we did a good job
by not choosing that door. However, he gives us the choice to stick to our choice which is one, or change it since now we only
have to choices. In order to make that choice, we toss a coin, if H or 0, we change, otherwise, we stick to our initial code.
this experiment is to show the probability of getting a heads and changing and the probability of getting a tales and sticking to the
same choice.

 */
        static Random r = new Random();

        //counting the number of wins if we change and if we dont.
        static int changeWins, noChangeWins;


        static void changeOnCoin(int count){
            for (int i=0; i<count;i++){
                //generating a random choice.
                int choice = r.nextInt(3);

                //tossing a coin
                int toss = r.nextInt(2);

                //if heads, change the choice
                if (toss ==0){
                    countPropChange(choice);
                } else {
                    //if tails, keep it.
                    countPropNoChange(choice);
                }
            }

        }



        static void countPropChange(int choice){
                // we start by deriving a simple way to make a choice that we want to eliminate
                // in this case, we either want to eliminate hte first or the second zero. Namely, index 0 or index 1.
                int ele = r.nextInt(2);

                //making sure that the choice is not equal to the eliminated choice.
                if (choice == ele){
                    ele = ele + 1 % 2; // the module is to keep it bounded
                }

                //the winning choice is predetermined to be 2.
                // now we need to make a new choice based on the eliminated choice
                int newChoice;
                if (choice == 2){
                    newChoice = ele + 1 %2;
                } else {
                    newChoice = 2;
                    changeWins++;
                }

        }

        static void countPropNoChange(int choice){

                if (choice == 2){
                    noChangeWins++;
                }

        }

        public static void main(String[]args){


            changeOnCoin(10000);
            System.out.println(changeWins);
            System.out.println(noChangeWins);

        }



}
