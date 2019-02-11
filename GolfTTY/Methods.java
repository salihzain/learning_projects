import java.util.Scanner;
public class Methods {
    Scanner scan = new Scanner(System.in);

    int userinput;

    //this method takes a 2D array and copies it to another 2D array 
    public int [][] copyArray(int [][] arr1, int [][] arr2){
        for (int i = 0; i< arr1.length; i++){
            for (int j = 0; j<arr1[i].length; j++){
                arr2[i][j] = arr1[i][j]; }}
        return arr2;
    }

    //This method sums the value of a column in a 2D area. Used to calculate the total round par. 
    public int sumArray(int[][] array){
        int sum = 0;
        for (int i = 0; i<array.length; i++){
            sum += array[i][2];
        }
        return sum;
    }
    //this method validates every input the player makes to check if he wants to quit or just made a mistake. 
    public int validate(int input){
        if (input==0){
            onExit();
        }

        while (input > 10 || input <0){
            System.out.println("INVALID!!! try again!");
            input = scan.nextInt();
            if (input ==0){
                onExit();
            }
        }

        return input;
    }

    //This method makes sure that user wants to quit then stops the program or allow the user to playAgain. 
    public void onExit(){
        System.out.println("Sorry to see you give up!");
        System.out.println("Do you want to play again? if so, enter 1. if not, 0");
        userinput = scan.nextInt();
        while (userinput != 0 && userinput != 1){
            System.out.println("Try again!!");
            userinput = scan.nextInt();
        }
        if (userinput==0){
            System.out.println(" ######  ######## ########    ##    ##  #######  ##     ## #### \n" +
                    "##    ## ##       ##           ##  ##  ##     ## ##     ## #### \n" +
                    "##       ##       ##            ####   ##     ## ##     ## #### \n" +
                    " ######  ######   ######         ##    ##     ## ##     ##  ##  \n" +
                    "      ## ##       ##             ##    ##     ## ##     ##      \n" +
                    "##    ## ##       ##             ##    ##     ## ##     ## #### \n" +
                    " ######  ######## ########       ##     #######   #######  ####");
            System.exit(0);
        } else if(userinput ==1 ){
            new Golf();
        }


    }


    //once the player finished a round, this method is called to allow the player to play again. 
    public void  playAgain(){
        System.out.println("Do you want to play again? If so, enter 1. Otherwise, 0 to exit");
        userinput = scan.nextInt();

        while (userinput!=0 && userinput != 1){
            System.out.println("Invalid Input! Tray again!");
            userinput = scan.nextInt();
        }

        if (userinput == 0) {
            System.out.println(" ######  ######## ########    ##    ##  #######  ##     ## #### \n" +
                    "##    ## ##       ##           ##  ##  ##     ## ##     ## #### \n" +
                    "##       ##       ##            ####   ##     ## ##     ## #### \n" +
                    " ######  ######   ######         ##    ##     ## ##     ##  ##  \n" +
                    "      ## ##       ##             ##    ##     ## ##     ##      \n" +
                    "##    ## ##       ##             ##    ##     ## ##     ## #### \n" +
                    " ######  ######## ########       ##     #######   #######  ####");
            System.exit(0);

        } else if (userinput == 1) {
            new Golf();
        }
    }


}
