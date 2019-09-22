package Core;

import java.util.HashMap;
import java.util.LinkedList;

public abstract class Constraint {
    public abstract boolean isSatsfied(HashMap<Variable, Comparable> result);
    protected LinkedList<Variable> variables;
    public abstract boolean containsVariable(Variable var);
}
