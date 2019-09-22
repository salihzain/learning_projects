package Solver;

import Core.*;
import Core.Constraint;

import java.util.*;

public class Solver {
    private CSP csp;
    public HashMap<Variable, LinkedList<Constraint>> constraintSelector;
    public PriorityQueue<Variable> nextVariable;

    public Solver(CSP csp){
        this.csp = csp;
        this.constraintSelector = new HashMap<>();
        this.nextVariable = new PriorityQueue<>(Collections.reverseOrder());

        // for every variable, add to the map a linkedlist of constraints
        for (Variable var : this.csp.getVariables()){
            LinkedList<Constraint> varConstraints = new LinkedList<>();

            for (Constraint c : csp.getConstraints()){
                if (c.containsVariable(var)){
                    varConstraints.add(c);
                }
            }

            this.constraintSelector.put(var, varConstraints);
            this.nextVariable.add(var);
        }
    }

    public Assignment solve(CSP csp){
        System.out.println("Problem to solve: " + csp.getClass().getSimpleName());
        System.out.println("Problem details");
        System.out.println();

        System.out.println("Variables and their domains");
        csp.getVariables().forEach(variable -> System.out.println(variable));
        System.out.println();

        System.out.println("Constraints");
        csp.getConstraints().forEach(constraint -> System.out.println(constraint));
        System.out.println();

        System.out.println("Working on solving the problem... Please wait...");
        System.out.println();
        return backtrack(new Assignment(), csp);
    }

    private Assignment backtrack(Assignment assignment, CSP csp){
        // if (assignment is complete) return assignment
        if (assignment.isComplete(csp)){
            return assignment;
        }

        //  var = SelectUnassignedVar(csp)
        Variable var = this.selectUnassignedVar(assignment,csp);
        if (var == null)
            return assignment;

        // for every value in OrderDomainValues(var, assignment, csp)
        for (Comparable d : var.getDomain()){
            // if value is consistent with assignment
            if (isConsistent(assignment.getAssignment(), csp, var, d)){
                // add <var, value> to assignment
                assignment.assignValue(var, d);

                //result = backgrack(assignment, csp)
                Assignment result = backtrack(assignment, csp);
                if (result != null){
                    return result;
                } else {
                    assignment.removeAssign(var);
                }
            }
        }
        if (!assignment.assignment.containsKey(var) && !nextVariable.contains(var)){
            nextVariable.add(var);
        }
        return null;
    }

    private boolean isConsistent(HashMap<Variable, Comparable> currAssignment, CSP csp, Variable toAssign, Comparable d){
        if (constraintSelector.get(toAssign).isEmpty()){
            return true;
        }

        currAssignment.put(toAssign, d);
        for (Constraint c : constraintSelector.get(toAssign)) {
            if (!c.isSatsfied(currAssignment)){
                currAssignment.remove(toAssign);
                return false;
            }
        }
        currAssignment.remove(toAssign);

        return true;
    }

    private Variable selectUnassignedVar(Assignment assignment, CSP csp){
        if (!this.nextVariable.isEmpty()){
            return nextVariable.poll();
        }

        return null;
    }
}
