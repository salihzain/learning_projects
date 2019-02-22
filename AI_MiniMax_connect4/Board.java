import java.util.ArrayList;
import java.util.List;

public class Board extends State{


    public Board(int row, int col, int userChoice, int connect){
        board = new int[row][col];
        this.row = row;
        this.col = col;
        this.userChoice = userChoice;
        if (userChoice == RED){
            this.USER = RED;
            this.COMPUTER = YELLOW;
        } else {
            this.USER = YELLOW;
            this.COMPUTER = RED;
        }
        this.connect = connect;
    }

    public Board(int[][] toCopy, int row, int col, int userChoice, int connect){
        board = new int[row][col];
        this.row = row;
        this.col = col;
        this.userChoice = userChoice;
        if (userChoice == RED){
            this.USER = RED;
            this.COMPUTER = YELLOW;
        } else {
            this.USER = YELLOW;
            this.COMPUTER = RED;
        }
        this.connect = connect;
        for (int i = 0; i<row; i++)
            for (int j = 0; j<col; j++)
                this.board[i][j] = toCopy[i][j];
    }

    public boolean isTerminalState() {
        return hasPlayerWon(RED) || hasPlayerWon(YELLOW) || getApplicableActions().isEmpty();
    }

    public boolean hasPlayerWon(int player){
        //if the game is to connect 3
        if (connect == 3)
            return evaluateConnect3(player);
        //otherwise, it's a connect four game
        return evaluateConnect4(player);
    }


    private boolean evaluateConnect3(int player){
        //check diagonally
        if (board[0][0]==board[1][1] && board[0][0]==board[2][2] && board[0][0]==player)
            return true;
        if (board[2][0]==board[1][1] && board[2][0]==board[0][2] && board[2][0]==player)
            return true;

        //check horizontally
        for (int i = 0; i<3; i++){
            if (board[i][0]== board[i][1] && board[i][0]== board[i][2] && board[i][0] == player)
                return true;
        }

        //check vertically
        for (int i = 0; i<3; i++){
            if (board[0][i]== board[1][i] && board[0][i]== board[2][i] && board[0][i] == player)
                return true;
        }

        //otherwise,
        return false;
    }

    private boolean evaluateConnect4(int player){
        //check diagonally
        for (int i = 3; i<row; i++)
            for (int j = 3; j<col; j++)
                if (board[i][j-3]==board[i-1][j-2] && board[i][j-3]==board[i-2][j-1] && board[i][j-3]==board[i-3][j] && board[i][j-3]==player)
                    return true;
        for (int i = 3; i<row; i++)
            for (int j = 3; j<col; j++)
                if (board[i-3][j-3]==board[i-2][j-2]&&board[i-3][j-3]==board[i-1][j-1]&&board[i-3][j-3]==board[i][j]&&board[i-3][j-3]==player)
                    return true;

        //check horizontally
        for (int i = 0; i<row; i++)
            for (int j = 3; j<col;j++)
                if (board[i][j-3]==board[i][j-2]&&board[i][j-3]==board[i][j-1]&&board[i][j-3]==board[i][j]&&board[i][j-3]==player)
                    return true;

        //check vertically
        for (int j = 0; j<col; j++)
            for (int i = 3; i<row; i++)
                if (board[i-3][j]==board[i-2][j]&&board[i-3][j]==board[i-1][j]&&board[i-3][j]==board[i][j]&&board[i-3][j]==player)
                    return true;

        //otherwise, no winner
        return false;
    }


    public int hFunction(int player){
        //counting the number of possible winning rows/columns/diagonals
        int possibleWins = 0;
        int countLabel, countEmpty;
        //check diagonally
        for (int i = 3; i<row; i++)
            for (int j = 3; j<col; j++){
                countLabel = 0;
                countEmpty = 0;
                if (board[i][j-3] == player)
                    countLabel++;
                else if (board[i][j-3] == 0)
                    countEmpty++;

                if (board[i-1][j-2] == player)
                    countLabel++;
                else if (board[i-1][j-2] == 0)
                    countEmpty++;

                if (board[i-2][j-1] == player)
                    countLabel++;
                else if (board[i-2][j-1] == 0)
                    countEmpty++;

                if (board[i-3][j] == player)
                    countLabel++;
                else if (board[i-3][j] == 0)
                    countEmpty++;

                if (countLabel == 3 && (countLabel+countEmpty)==4)
                    possibleWins += countLabel;
            }

        for (int i = 3; i<row; i++)
            for (int j = 3; j<col; j++){
                countLabel = 0;
                countEmpty = 0;
                if (board[i-3][j-3] == player)
                    countLabel++;
                else if (board[i-3][j-3] == 0)
                    countEmpty++;

                if (board[i-2][j-2] == player)
                    countLabel++;
                else if (board[i-2][j-2] == 0)
                    countEmpty++;

                if (board[i-1][j-1] == player)
                    countLabel++;
                else if (board[i-1][j-1] == 0)
                    countEmpty++;

                if (board[i][j] == player)
                    countLabel++;
                else if (board[i][j] == 0)
                    countEmpty++;

                if (countLabel == 3 && (countLabel+countEmpty)==4)
                    possibleWins += countLabel;
            }

        //check horizontally
        for (int i = 0; i<row; i++)
            for (int j = 3; j<col;j++){
                countLabel = 0;
                countEmpty = 0;
                if (board[i][j-3] == player)
                    countLabel++;
                else if (board[i][j-3] == 0)
                    countEmpty++;

                if (board[i][j-2] == player)
                    countLabel++;
                else if (board[i][j-2] == 0)
                    countEmpty++;

                if (board[i][j-1] == player)
                    countLabel++;
                else if (board[i][j-1] == 0)
                    countEmpty++;

                if (board[i][j] == player)
                    countLabel++;
                else if (board[i][j] == 0)
                    countEmpty++;

                if (countLabel == 3 && (countLabel+countEmpty)==4)
                    possibleWins += countLabel;
            }

        //check vertically
        for (int j = 0; j<col; j++)
            for (int i = 3; i<row; i++){
                countLabel = 0;
                countEmpty = 0;
                if (board[i-3][j] == player)
                    countLabel++;
                else if (board[i-3][j] == 0)
                    countEmpty++;

                if (board[i-2][j] == player)
                    countLabel++;
                else if (board[i-2][j] == 0)
                    countEmpty++;

                if (board[i-1][j] == player)
                    countLabel++;
                else if (board[i-1][j] == 0)
                    countEmpty++;

                if (board[i][j] == player)
                    countLabel++;
                else if (board[i][j] == 0)
                    countEmpty++;

                if (countLabel == 3 && (countLabel+countEmpty)==4)
                    possibleWins += countLabel;
            }

        if (player == COMPUTER)
            return possibleWins;
        // if user
        return (-1 * possibleWins);
    }

    public List<Integer> getApplicableActions(){
        List<Integer> applicableActions = new ArrayList<>();
        //check every column
        for (int j = 0; j< col; j++){
            if (board[0][j] == NO_PLAYER|| board[1][j] == NO_PLAYER || board[2][j]==NO_PLAYER)
                applicableActions.add(j);
        }
        return applicableActions;
    }

    public boolean takeAction(int col, int player){
        if (!checkApplicableAction(col))
            return false;
        //otherwise, start from the bottom, until you find an empty spot, place a player label
        for (int i = row-1; i>=0; i--) {
            if (board[i][col]==NO_PLAYER){
                board[i][col] = player;
                break;
            }
        }
        return true;
    }

    public boolean checkApplicableAction(int col){
        //check the col, see if it has an empty place or not
        for (int i = 0; i<row; i++){
            if (board[i][col] == NO_PLAYER)
                return true;
        }
        return false;
    }

    public int getUtility(){
        if (hasPlayerWon(COMPUTER))
            return 100;
        if (hasPlayerWon(USER))
            return -100;
        return 0;
    }

    public void printBoard(){
        System.out.print("   ");
        for (int i = 0; i<col; i++){
            System.out.print("   " + i + "   ");
        }
        System.out.println();
        for (int i =0; i<row; i++){
            System.out.print(" " + i + " ");
            for (int j=0; j<col; j++){
                String value = "_";
                if (board[i][j]==RED)
                    value = "R";
                if (board[i][j]==YELLOW)
                    value = "Y";
                System.out.print(" | " + value + " | ");
            }
            System.out.println();
        }
    }
}
