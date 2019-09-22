package Core;

import java.util.HashSet;

public abstract class CSP {
    protected HashSet<Variable> variables;
    protected HashSet<Constraint> constraints;

    public HashSet<Constraint> getConstraints() {
        return constraints;
    }

    public HashSet<Variable> getVariables() {
        return variables;
    }

    public int getNumOfVariables(){
        return variables.size();
    }

    //Using my own printing function instead of toString() to better format it
    protected void printCSP(){
        System.out.println("Problem details:");
        System.out.println();
        System.out.println("list of variables and their domains");

        for (Variable variable : variables) {
            System.out.println(variable.toString());
        }
        System.out.println();

        System.out.println("list of constraints");
        for (Constraint constraint : constraints) {
            System.out.println(constraint.toString());
        }
        System.out.println();
        System.out.println("End of printing");
    }
}
