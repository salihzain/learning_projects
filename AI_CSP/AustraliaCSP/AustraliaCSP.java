package AustraliaCSP;

import Core.*;
import Solver.*;

import java.util.HashSet;

public class AustraliaCSP extends CSP {
    public AustraliaCSP() {
        this.variables = new HashSet<>();

        Variable NT = new Variable("NT", AustraliaDomain.rgbDomain());
        this.variables.add(NT);

        Variable WA = new Variable("WA", AustraliaDomain.rgbDomain());
        this.variables.add(WA);

        Variable Q = new Variable("Q", AustraliaDomain.rgbDomain());
        this.variables.add(Q);

        Variable NSW = new Variable("NSW", AustraliaDomain.rgbDomain());
        this.variables.add(NSW);

        Variable V = new Variable("V", AustraliaDomain.rgbDomain());
        this.variables.add(V);

        Variable SA = new Variable("SA", AustraliaDomain.rgbDomain());
        this.variables.add(SA);

        Variable T = new Variable("T", AustraliaDomain.rgbDomain());
        this.variables.add(T);

        this.constraints = new HashSet<>();
        this.constraints.add(new NotEqualConstraint(SA, WA));
        this.constraints.add(new NotEqualConstraint(SA, NT));
        this.constraints.add(new NotEqualConstraint(SA, Q));
        this.constraints.add(new NotEqualConstraint(SA, NSW));
        this.constraints.add(new NotEqualConstraint(SA, V));
        this.constraints.add(new NotEqualConstraint(WA, NT));
        this.constraints.add(new NotEqualConstraint(NT, Q));
        this.constraints.add(new NotEqualConstraint(Q, NSW));
        this.constraints.add(new NotEqualConstraint(NSW, V));
    }


    public static void main(String[]args){
        AustraliaCSP problem = new AustraliaCSP();
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
