import java.util.Random;
import java.util.Scanner;

public class Game {
    private final Random random = new Random();
    private final Scanner scanner = new Scanner(System.in);
    private Board board;

    public Game(){
        init();
    }

    public void init(){
        System.out.println("Hello! Welcome to the game!");
        System.out.println("To play as Red, enter 1, Yellow, enter 2");
        int playerChoice = scanner.nextInt();
        while (playerChoice!=1 && playerChoice!=2){
            System.out.println("Invalid input. Enter 1 for Red, 2 for Yellow");
            playerChoice = scanner.nextInt();
        }

        int gameChoice;
        System.out.println("What game would you like to play?");
        System.out.println("1. Tiny 3x3x3 Connect-Three");
        System.out.println("2.  Standard 6x7x4 Connect-Four");
        gameChoice = scanner.nextInt();
        while (gameChoice!=1 && gameChoice!=2){
            System.out.println("Invalid input. 1 for 3x3x3 Connect-Three or 2 for 6x7x4 Connect-Four");
            gameChoice = scanner.nextInt();
        }
        if (gameChoice == 1){
            board = new Board(3,3, playerChoice, 3);
        }
        else {
            board = new Board(6,7,playerChoice, 4);
        }
        gameLoop(playerChoice, gameChoice);
    }

    private void gameLoop(int playerChoice, int gameChoice){
        int agentSelected = 1;
        int cutoff = 10; //default cutoff for h-minimax. Can be changed by user
        if (gameChoice == 2){
            selectAgent();
            agentSelected = scanner.nextInt();
            while (agentSelected > 4 || agentSelected <1){
                System.out.println("Invalid input");
                selectAgent();
            }
            if (agentSelected == 3 || agentSelected == 4){
                System.out.println("Please enter a cutoff depth");
                cutoff = scanner.nextInt();
                while (cutoff <1){
                    System.out.println("Please enter a valid cutoff depth");
                    cutoff = scanner.nextInt();
                }
            }
        }


        if (playerChoice == Board.YELLOW){
            board.takeAction(random.nextInt(board.col), board.COMPUTER);
        }
        board.printBoard();
        while (!board.isTerminalState()){
            boolean moveOk = true;
            do {
                if (!moveOk){
                    System.out.println("Not applicable move, try again");
                }
                if (board.USER == Board.RED)
                    System.out.println("Red turn, enter a value between 0 - " + (board.col-1));
                else
                    System.out.println("Yellow turn, enter a value between 0 - " + (board.col-1));

                int userMove = scanner.nextInt();
                while (userMove < 0 || userMove >= board.col){
                    System.out.println("Invalid, enter a value between 0 - " + (board.col-1));
                }
                moveOk = board.takeAction(userMove, board.USER);
            } while (!moveOk);
            board.printBoard();
            if (board.isTerminalState()) break;

            System.out.println("Agent is thinking...");
            if (agentSelected == 1){
                board.computerMove = Minimax.miniMax(board, 0);
            } else if (agentSelected == 2){
                board.computerMove = Minimax.miniMaxWithAlphaBeta(board, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
            } else if (agentSelected == 3){
                board.computerMove = Minimax.hMinimax(board, 0, cutoff);
            } else {
                board.computerMove = Minimax.hMiniMaxWithAlphaBeta(board, 0, Integer.MIN_VALUE, Integer.MAX_VALUE, cutoff);
            }
            board.takeAction(board.computerMove, board.COMPUTER);
            board.printBoard();
        }

        if ( board.hasPlayerWon(Board.RED))
            System.out.println("RED WON!!!");
        else if (board.hasPlayerWon(Board.YELLOW))
            System.out.println("YELLOW WON!!!");
        else
            System.out.println("Draw");
    }

    private void selectAgent(){
        System.out.println("What kind of agent would you like to play against?");
        System.out.println("1. An agent that uses standard MINIMAX");
        System.out.println("2. An agent that uses MINIMAX with alpha-beta pruning");
        System.out.println("3. An agent that uses  H-MINIMAX with fixed depth cutoff");
        System.out.println("4. An agent that uses  H-MINIMAX with alpha-beta pruning and fixed depth cutoff");
    }


    public static void main(String[] args){
        new Game();
    }
}
