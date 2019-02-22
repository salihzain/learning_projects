import java.util.LinkedList;
import java.util.List;

public class Minimax {

    public Minimax(){}

    public static int miniMax(State state, int depth) {
        return maxValue(state, depth);
    }
    private  static int maxValue(State state, int depth){
        if (state.isTerminalState())
            return state.getUtility();
        List<Integer> actions = state.getApplicableActions();
        LinkedList<Action> actionSelector = new LinkedList<>();
        int value = Integer.MIN_VALUE;
        for (int i = 0; i<actions.size(); i++){
            int action = actions.get(i);
            Board temp = new Board(state.board, state.row, state.col, state.userChoice, state.connect);
            temp.takeAction(action, state.COMPUTER);
            value = Math.max(value, minValue(temp, depth +1));
            actionSelector.add(new Action(action, value));
        }
        if (depth == 0){
            int bestMove = 0;
            for (Action a : actionSelector){
                if (a.value >= value){
                    bestMove = a.move;
                    break;
                }
            }
            return bestMove;
        }
        return value;
    }
    private static int minValue(State state, int depth){
        if (state.isTerminalState())
            return state.getUtility();
        List<Integer> actions = state.getApplicableActions();
        int value = Integer.MAX_VALUE;
        for (int i = 0; i<actions.size(); i++){
            int action = actions.get(i);
            Board temp = new Board(state.board, state.row, state.col, state.userChoice, state.connect);
            temp.takeAction(action, state.USER);
            value = Math.min(value, maxValue(temp, depth +1));
        }
        return value;
    }

    public static int miniMaxWithAlphaBeta(State state, int depth, int alpha, int beta) {
        return alpaBetamaxValue(state, depth, alpha, beta);
    }
    private  static int alpaBetamaxValue(State state, int depth, int alpha, int beta){
        if (state.isTerminalState())
            return state.getUtility();
        List<Integer> actions = state.getApplicableActions();
        LinkedList<Action> actionSelector = new LinkedList<>();
        int value = Integer.MIN_VALUE;
        for (int i = 0; i<actions.size(); i++){
            int action = actions.get(i);
            Board temp = new Board(state.board, state.row, state.col, state.userChoice, state.connect);
            temp.takeAction(action, state.COMPUTER);
            value = Math.max(value, alpaBetaminValue(temp, depth +1 , alpha, beta));
            if (value >= beta)
                return value;
            alpha = Math.max(alpha, value);
            actionSelector.add(new Action(action, value));
        }
        if (depth == 0){
            int bestMove = 0;
            for (Action a : actionSelector){
                if (a.value >= value){
                    bestMove = a.move;
                    break;
                }
            }
            return bestMove;
        }
        return value;
    }
    private static int alpaBetaminValue(State state, int depth, int alpha, int beta){
        if (state.isTerminalState())
            return state.getUtility();
        List<Integer> actions = state.getApplicableActions();
        int value = Integer.MAX_VALUE;
        for (int i = 0; i<actions.size(); i++){
            int action = actions.get(i);
            Board temp = new Board(state.board, state.row, state.col, state.userChoice, state.connect);
            temp.takeAction(action, state.USER);
            value = Math.min(value, alpaBetamaxValue(temp, depth +1 , alpha, beta));
            if (value <= alpha)
                return value;
            beta = Math.min(beta, value);
        }
        return value;
    }

    public static int hMinimax(State state, int depth, int cutoff) {
        return hMaxValue(state, depth,  cutoff);
    }
    private  static int hMaxValue(State state, int depth, int cutoff){
        if (state.isTerminalState())
            return state.getUtility();
        if (depth == cutoff)
            return state.hFunction(state.COMPUTER);

        List<Integer> actions = state.getApplicableActions();
        LinkedList<Action> actionSelector = new LinkedList<>();
        int value = Integer.MIN_VALUE;
        for (int i = 0; i<actions.size(); i++){
            int action = actions.get(i);
            Board temp = new Board(state.board, state.row, state.col, state.userChoice, state.connect);
            temp.takeAction(action, state.COMPUTER);
            value = Math.max(value, hMinValue(temp, depth +1 , cutoff));
            actionSelector.add(new Action(action, value));
        }
        if (depth == 0){
            int bestMove = 0;
            for (Action a : actionSelector){
                if (a.value >= value){
                    bestMove = a.move;
                    break;
                }
            }
            return bestMove;
        }
        return value;
    }
    private static int hMinValue(State state, int depth, int cutoff){
        if (state.isTerminalState())
            return state.getUtility();
        if (depth == cutoff)
            return state.hFunction(state.USER);
        List<Integer> actions = state.getApplicableActions();
        int value = Integer.MAX_VALUE;
        for (int i = 0; i<actions.size(); i++){
            int action = actions.get(i);
            Board temp = new Board(state.board, state.row, state.col, state.userChoice, state.connect);
            temp.takeAction(action, state.USER);
            value = Math.min(value, hMaxValue(temp, depth +1, cutoff));
        }
        return value;
    }

    public static int hMiniMaxWithAlphaBeta(State state, int depth, int alpha, int beta, int cutoff) {
        return hAlpaBetamaxValue(state, depth, alpha, beta, cutoff);
    }
    private  static int hAlpaBetamaxValue(State state, int depth, int alpha, int beta,  int cutoff){
        if (state.isTerminalState())
            return state.getUtility();
        if (depth == cutoff)
            return state.hFunction(state.COMPUTER);
        List<Integer> actions = state.getApplicableActions();
        LinkedList<Action> actionSelector = new LinkedList<>();
        int value = Integer.MIN_VALUE;
        for (int i = 0; i<actions.size(); i++){
            int action = actions.get(i);
            Board temp = new Board(state.board, state.row, state.col, state.userChoice, state.connect);
            temp.takeAction(action, state.COMPUTER);
            value = Math.max(value, hAlpaBetaminValue(temp, depth +1 , alpha, beta, cutoff));
            if (value >= beta)
                return value;
            alpha = Math.max(alpha, value);
            actionSelector.add(new Action(action, value));
        }
        if (depth == 0){
            int bestMove = 0;
            for (Action a : actionSelector){
                if (a.value >= value){
                    bestMove = a.move;
                    break;
                }
            }
            return bestMove;
        }
        return value;
    }
    private static int hAlpaBetaminValue(State state, int depth, int alpha, int beta,  int cutoff){
        if (state.isTerminalState())
            return state.getUtility();
        if (depth == cutoff)
            return state.hFunction(state.USER);
        List<Integer> actions = state.getApplicableActions();
        int value = Integer.MAX_VALUE;
        for (int i = 0; i<actions.size(); i++){
            int action = actions.get(i);
            Board temp = new Board(state.board, state.row, state.col, state.userChoice, state.connect);
            temp.takeAction(action, state.USER);
            value = Math.min(value, hAlpaBetamaxValue(temp, depth +1 , alpha, beta, cutoff));
            if (value <= alpha)
                return value;
            beta = Math.min(beta, value);
        }
        return value;
    }
}
