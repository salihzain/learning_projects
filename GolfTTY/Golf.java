import java.util.Scanner; 

public class Golf {

    public Golf () {
        //creating a 2D array to store the course array locally. will use it below
        int arr[][] = new int[18][3];
        //variables bank. I hope the names are clear 
        int club, power, shotDistance, distanceToHole, initDistance, withinPar, totalRoundPar, roundShots;
        int shots = 1; int score = 0;

        //an array for the hole names. where holeNumner[0] = First 
        String [] holeNumber  = {"First", "Second", "Third", "Fourth", "Fifth", "Sixth", "Seventh", "Eighth","Ninth",
                                "Tenth", "Eleventh", "Twelfth", "Thirteenth", "Fourteenth", "Fifteenth", "Sixteenth",
                                "Seventeenth", "Final"};

        //creating instances of the other four classes to be used here.                         
        Courses course = new Courses();
        Putting putt = new Putting();
        Methods method = new Methods();
        Shot shot = new Shot();
        //creating the lovely scanner! 
        Scanner scan = new Scanner(System.in);
        //an awesome banner
        System.out.println("######## ######## ##    ##     ######    #######  ##       ######## \n" +
                "   ##       ##     ##  ##     ##    ##  ##     ## ##       ##       \n" +
                "   ##       ##      ####      ##        ##     ## ##       ##       \n" +
                "   ##       ##       ##       ##   #### ##     ## ##       ######   \n" +
                "   ##       ##       ##       ##    ##  ##     ## ##       ##       \n" +
                "   ##       ##       ##       ##    ##  ##     ## ##       ##       \n" +
                "   ##       ##       ##        ######    #######  ######## ##  ");
        System.out.println("Welcome to TTY GOLF!!\nGeneral Instructions: to exit the game at any point, etner 0");
        System.out.println("To start the game, please select one of the following courses:");
        System.out.println("1.Genesee Valley Park North Course \n2. The Old Course at St. Andrews");
        int userin = scan.nextInt(); 
        //for the sake of validation. I want to make sure that the player only selects the right value. 
        while (userin != 1 && userin != 2){
            System.out.println("Try again!");
            userin = scan.nextInt();
        }
        //the statment that copies the selected course array from Courses.java to Golf.java using the local array arr
        if (userin == 1) {
            method.copyArray(course.getGVP(), arr);
        } else if (userin == 2) {
           method.copyArray(course.getSTA(), arr);
        }

        //getting the total par values to later be used as a determinant of the final score. 
        totalRoundPar = method.sumArray(arr);

        //outer loop that loops through each hole
        for (int i = 0; i<18; i++) {
            //storing the initial disance to inform the user of the remaining distance 
            initDistance = arr[i][1];
            //setting within par to zero for every new hole.
            withinPar = 0;
            roundShots = 1;
            System.out.println("You are at the " + holeNumber[i]+ " hole. You are "+ initDistance +
                    " yards away and your par is " + arr[i][2]);

            System.out.println("Please select a club: [1-10]");
            userin = scan.nextInt();
            //using validate method from the Methods class to validate the input and make sure that input makes sense and that the user doesn't want to quit. 
            club = method.validate(userin) - 1;

            System.out.println("Please select a power: [1-10]");
            userin = scan.nextInt();
            power = method.validate(userin);
            // using the nextDistance from the shot class to calculate the distance and store it in a variable. 
            shotDistance = shot.nextDistance(club,power);
            //Remaining distance = distanceToHole. 
            distanceToHole = initDistance - shotDistance;

            //the loop before the player gets to the greeny 
            while (distanceToHole>20){
                //informing the player of his shot. 
                System.out.println("You hit the ball " + shotDistance + " yards");
                System.out.println("Remaining Distane to hole is: " + distanceToHole + " yrads!");
                System.out.println("Take your shot number " + (roundShots+1));

                System.out.println("Please select a club: [1-10]");
                userin = scan.nextInt();
                club = method.validate(userin) - 1;

                System.out.println("Please select a power: [1-10]");
                userin = scan.nextInt();
                power = method.validate(userin);

                shotDistance = shot.nextDistance(club,power);
                distanceToHole-=shotDistance;
                //shots is used for the final score whereas roundShots is used to store the shots per hole. sorry for the confusing name. 
                shots++;
                roundShots++;
            }

            //sometimes, a player would make it into the hole without need to be on the green. This statement is to make sure that
            // the player still has some distance to the hole. 
            if (distanceToHole>1){
            System.out.println("You are on the green!");}

            //converting the distance from yard to feet. 
            distanceToHole *= 3;
            //on the green loop     
            while (distanceToHole>1){
                System.out.println("You are " + distanceToHole +" feet away from the hole!");
                System.out.println("Take your shot number " + (roundShots+1));
                System.out.println("Choose your power [1-10]");
                userin = scan.nextInt();
                power = method.validate(userin);
                shotDistance = putt.nextDistance(power);
                distanceToHole-=shotDistance;
                shots++;
                roundShots++;
            }
            //withinPar is a varibale that determines if the shots needed to make it to hole are more or less that par. 
            withinPar = roundShots - arr[i][2];
            if (withinPar > 0) {
                System.out.println("You made it! " + withinPar + " Shots over par");

            } else if (withinPar<0){
                System.out.println("You made it! " + Math.abs(withinPar) + " under par");

            } else {
                System.out.println("You made it! on par!");

            }


            

        }
    //determing the score based on the total round shots and the total round par 
    score = shots - totalRoundPar;

    if (score <0){
            System.out.println("Your score is " + Math.abs(score) + " under par");

    } else if (score>0){
            System.out.println("Your score is " + score + " over par");

    } else {
            System.out.println("Your score is at par");
    }

    //giving the player the chance to play again or quit.
    method.playAgain();

    }


}