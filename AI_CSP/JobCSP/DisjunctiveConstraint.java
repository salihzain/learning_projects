package JobCSP;

import Core.Constraint;
import Core.Variable;

import java.util.HashMap;
import java.util.LinkedList;

public class DisjunctiveConstraint extends Constraint {
    private Variable v1, v2;
    private int timeGap;

    public DisjunctiveConstraint(Variable v1, Variable v2, int timeGap){
        this.v1 = v1;
        this.v2 = v2;

        this.timeGap = timeGap;

        this.variables = new LinkedList<>();
        this.variables.add(v1);
        this.variables.add(v2);

        this.v1.incNumOfConstraints();
        this.v2.incNumOfConstraints();
    }

    @Override
    public boolean isSatsfied(HashMap<Variable, Comparable> result) {
        // case1: we haven't set the values for either of them yet
        if (result.get(v1) == null || result.get(v2) == null){
            return true;
        }

        int t1 = (int) result.get(v1);
        int t2 = (int) result.get(v2);

        return (t1 + this.timeGap <= t2) || (t2 + this.timeGap <= t1);
    }

    @Override
    public boolean containsVariable(Variable var) {
        return this.variables.contains(var);
    }

    public String toString() {
        return "[" + v1.getName() + "] + " + this.timeGap + " <= [" + v2.getName() + "] or " + "[" + v2.getName() + "] + " + this.timeGap + " <= [" + v1.getName() + "]";
    }
}
