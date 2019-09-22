package Core.ExtraConstraints;

import Core.Constraint;
import Core.Variable;

import java.util.HashMap;
import java.util.LinkedList;

public class UnaryConstraint extends Constraint {
    private Variable var;
    private Comparable value;

    public UnaryConstraint(Variable var, Comparable value){
        this.var = var;
        this.value = value;

        this.variables = new LinkedList<>();
        this.variables.add(var);

        this.var.incNumOfConstraints();
    }

    @Override
    public boolean isSatsfied(HashMap<Variable, Comparable> result) {
        if (result.get(var) == null)
            return true;

        return result.get(var).equals(value);
    }

    @Override
    public boolean containsVariable(Variable var) {
        return this.variables.contains(var);
    }

    @Override
    public String toString() {
        return "[" + var.getName() + "] == [" + value + "]";
    }
}
