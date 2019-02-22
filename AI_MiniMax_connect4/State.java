import java.util.List;

public abstract class State {
    static final int NO_PLAYER = 0;
    static final int RED = 1;
    static final int YELLOW = 2;
    int COMPUTER;
    int USER;
    int computerMove;
    int row;
    int col;
    int userChoice;
    int connect;
    int[][]board;

    abstract boolean isTerminalState();
    abstract boolean hasPlayerWon(int player);
    abstract List<Integer> getApplicableActions();
    abstract boolean takeAction(int col, int player);
    abstract boolean checkApplicableAction(int col);
    abstract int getUtility();
    abstract int hFunction(int player);
}
