package NQueenCSP;

import Core.*;
import Solver.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class NQueenCSP extends CSP{
    private Variable[] varsArray;

    public NQueenCSP(int n){
        varsArray = new Variable[n];

        this.variables = new HashSet<>();
        initVariables(n);

        this.constraints = new HashSet<>();
        initConstraints();
    }

    private void initVariables(int n){
        for (int i = 0; i<n; i++){
            String queenName = "q" + (i+1);
            Variable temp = new Variable(queenName, NQueenDomain.boardPairsDomain(n));
            this.variables.add(temp);
            varsArray[i] = temp;
        }
    }

    private void initConstraints(){
        for (int i = 0; i<varsArray.length-1; i++){
            for (int j = i+1; j<varsArray.length; j++){
                this.constraints.add(new NotAttackingConstraint(varsArray[i], varsArray[j]));
            }
        }
    }

    private static void printBoard(HashMap<Variable, Comparable> assignment, int n){
        String[][] board = new String[n][n];

        assignment.forEach((key,value)->{
            String name = key.getName();
            Pair pair = (Pair) value;
            board[pair.i][pair.j] = name;
        });

        for (int i = 0; i<n; i++){
            for (int j = 0; j<n; j++){
                if (board[i][j] ==null)
                    System.out.print(" -  ");
                else
                    System.out.print(" " + board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the number of queens");
        int n = scanner.nextInt();
        while (n <= 0 ){
            System.out.println("Invalid! Please enter a positive number");
            n = scanner.nextInt();
        }

        NQueenCSP problem = new NQueenCSP(n);
        Solver solver = new Solver(problem);

        Assignment assignment = solver.solve(problem);
        if (assignment!=null){
            System.out.println("The solution is: ");
            assignment.printAssignment(problem);
            printBoard(assignment.getAssignment(), n);
        } else {
            System.out.println("failed to find a solution");
        }
    }
}
