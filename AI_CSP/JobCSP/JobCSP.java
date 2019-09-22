package JobCSP;

import Core.*;
import Solver.*;

import java.util.HashSet;

public class JobCSP extends CSP{
    public JobCSP(){
        this.variables = new HashSet<>();

        Variable axleF = new Variable("axleF", JobDomain.timeBlockDomain(27));
        this.variables.add(axleF);

        Variable axleB = new Variable("axleB", JobDomain.timeBlockDomain(27));
        this.variables.add(axleB);

        Variable wheelRF = new Variable("wheelRF", JobDomain.timeBlockDomain(27));
        this.variables.add(wheelRF);

        Variable wheelLF = new Variable("wheelLF", JobDomain.timeBlockDomain(27));
        this.variables.add(wheelLF);
        
        Variable wheelRB = new Variable("wheelRB", JobDomain.timeBlockDomain(27));
        this.variables.add(wheelRB);

        Variable wheelLB = new Variable("wheelLB", JobDomain.timeBlockDomain(27));
        this.variables.add(wheelLB);
        
        Variable nutsRF = new Variable("nutsRF", JobDomain.timeBlockDomain(27));
        this.variables.add(nutsRF);

        Variable nutsLF = new Variable("nutsLF", JobDomain.timeBlockDomain(27));
        this.variables.add(nutsLF);

        Variable nutsRB = new Variable("nutsRB", JobDomain.timeBlockDomain(27));
        this.variables.add(nutsRB);

        Variable nutsLB = new Variable("nutsLB", JobDomain.timeBlockDomain(27));
        this.variables.add(nutsLB);

        Variable capRF = new Variable("capRF", JobDomain.timeBlockDomain(27));
        this.variables.add(capRF);

        Variable capLF = new Variable("capLF", JobDomain.timeBlockDomain(27));
        this.variables.add(capLF);

        Variable capRB = new Variable("capRB", JobDomain.timeBlockDomain(27));
        this.variables.add(capRB);

        Variable capLB = new Variable("capLB", JobDomain.timeBlockDomain(27));
        this.variables.add(capLB);

        Variable inspect = new Variable("inspect", JobDomain.timeBlockDomain(27));
        this.variables.add(inspect);

        this.constraints = new HashSet<>();
        this.constraints.add(new PrecedenceConstraint(wheelRF, nutsRF, 1));
        this.constraints.add(new PrecedenceConstraint(wheelRF, inspect, 1));
        this.constraints.add(new PrecedenceConstraint(wheelLF, nutsLF, 1));
        this.constraints.add(new PrecedenceConstraint(wheelLF, inspect, 1));
        this.constraints.add(new PrecedenceConstraint(wheelRB, nutsRB, 1));
        this.constraints.add(new PrecedenceConstraint(wheelRB, inspect, 1));
        this.constraints.add(new PrecedenceConstraint(wheelLB, nutsLB, 1));
        this.constraints.add(new PrecedenceConstraint(wheelLB, inspect, 1));
        this.constraints.add(new PrecedenceConstraint(nutsRF, capRF, 2));
        this.constraints.add(new PrecedenceConstraint(nutsRF, inspect, 2));
        this.constraints.add(new PrecedenceConstraint(capRF, inspect, 1));
        this.constraints.add(new PrecedenceConstraint(nutsLF, capLF, 2));
        this.constraints.add(new PrecedenceConstraint(nutsLF, inspect, 2));
        this.constraints.add(new PrecedenceConstraint(capLF, inspect, 1));
        this.constraints.add(new PrecedenceConstraint(nutsRB, capRB, 2));
        this.constraints.add(new PrecedenceConstraint(nutsRB, inspect, 2));
        this.constraints.add(new PrecedenceConstraint(capRB, inspect, 1));
        this.constraints.add(new PrecedenceConstraint(nutsLB, capLB, 2));
        this.constraints.add(new PrecedenceConstraint(nutsLB, inspect, 2));
        this.constraints.add(new PrecedenceConstraint(capLB, inspect, 1));
        this.constraints.add(new PrecedenceConstraint(axleF, wheelRF, 10));
        this.constraints.add(new PrecedenceConstraint(axleF, wheelLF, 10));
        this.constraints.add(new PrecedenceConstraint(axleF, inspect, 10));
        this.constraints.add(new PrecedenceConstraint(axleB, wheelRB, 10));
        this.constraints.add(new PrecedenceConstraint(axleB, wheelLB, 10));
        this.constraints.add(new PrecedenceConstraint(axleB, inspect, 10));
        this.constraints.add(new DisjunctiveConstraint(axleF, axleB, 10));
    }

    public static void main(String[]args){
        JobCSP problem = new JobCSP();
        Solver solver = new Solver(problem);

        Assignment assignment = solver.solve(problem);
        if (assignment!=null){
            System.out.println("The solution is: ");
            assignment.printAssignment(problem);
        } else {
            System.out.println("failed to find a solution");
        }
    }
}
