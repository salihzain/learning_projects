package Solver;

import Core.CSP;
import Core.Variable;

import java.util.HashMap;

public class Assignment {
    protected HashMap<Variable, Comparable> assignment;
    public Assignment(){
        this.assignment = new HashMap<>();
    }

    //An assignment is complete if all variables are assigned values
    protected boolean isComplete(CSP csp){
        return assignment.size() == csp.getNumOfVariables();
    }

    protected void assignValue(Variable v, Comparable d){
        this.assignment.put(v, d);
    }

    protected void removeAssign(Variable v){
        this.assignment.remove(v);
    }

    public HashMap<Variable, Comparable> getAssignment() {
        return assignment;
    }

    public boolean isAssigned(Variable v){
        return assignment.containsKey(v);
    }

    public void printAssignment(CSP csp){
        assignment.forEach((var, value)-> {
            System.out.println("var " + var.getName() + " assigned a value of " + value);
        });
    }

}
