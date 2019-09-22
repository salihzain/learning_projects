package Core.ExtraConstraints;

import Core.Constraint;
import Core.Variable;

import java.util.HashMap;
import java.util.LinkedList;

public class EqualConstraint extends Constraint {
    private Variable s, t;

    public EqualConstraint(Variable s, Variable t){
        this.s = s;
        this.t = t;

        this.variables = new LinkedList<>();
        this.variables.add(s);
        this.variables.add(t);

        this.s.incNumOfConstraints();
        this.t.incNumOfConstraints();
    }

    @Override
    public boolean isSatsfied(HashMap<Variable, Comparable> result) {
        // if we haven't assigned a value to one of the variables yet
        if (result.get(s) == null || result.get(t) == null){
            return true;
        }

        //negate if they are equal
        return (result.get(s).equals(result.get(t)));
    }

    @Override
    public boolean containsVariable(Variable var) {
        return this.variables.contains(var);
    }

    @Override
    public String toString() {
        return "[" + s.getName() + "] == [" + t.getName() + "]";
    }
}
