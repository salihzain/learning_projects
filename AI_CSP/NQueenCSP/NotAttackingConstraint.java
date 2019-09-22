package NQueenCSP;

import Core.Variable;

import java.util.HashMap;
import java.util.LinkedList;

public class NotAttackingConstraint extends Core.Constraint{
    private Variable q1, q2;

    public NotAttackingConstraint(Variable q1, Variable q2){
        this.q1 = q1;
        this.q2 = q2;

        this.variables = new LinkedList<>();
        this.variables.add(q1);
        this.variables.add(q2);

        q1.incNumOfConstraints();
        q2.incNumOfConstraints();
    }

    @Override
    public boolean isSatsfied(HashMap<Variable, Comparable> result) {
        // case1: we haven't set the values for either of them yet
        if (result.get(q1) == null || result.get(q2) == null){
            return true;
        }

        Pair loc1 = (Pair) result.get(q1);
        Pair loc2 = (Pair) result.get(q2);

        //check row
        if (loc1.i == loc2.i){
            return false;
        }

        //check column
        if (loc1.j == loc2.j){
            return false;
        }

        //check first diagonal
        if ((loc1.i - loc1.j)==(loc2.i - loc2.j)){
            return false;
        }

        //check second diagonal
        if ((loc1.i + loc1.j)==(loc2.i + loc2.j)){
            return false;
        }

        return true;
    }

    @Override
    public boolean containsVariable(Variable var) {
        return this.variables.contains(var);
    }

    public String toString() {
        return "[" + q1.getName() + "] CAN'T ATTACK [" + q2.getName() + "]";
    }
}
