## Project info:
Implementation of CSP Algorithm and applying the algorithm to solve three well-know problems: Australia map coloring, Job scheduling, and NQueen problem. 
The implementation is generic enough such that any problem that can be formalized as CSP can be solved using this implementation (if the constraints were already implemented. Otherwise, it's easy to implement a new type of constraints). 

## Included Packages and Classes:

```
Core:
	- Constraint
	- CSP 
	- Variable
Solver: 
	- Assignment
	- Solver 
AusturaliaCSP: 
	- AustraliaCSP
	- AustraliaDomain
	- NotEqualConstraint
JobCSP
	- JobCSP
	- JobDomain
	- PrecedenceConstraint
	- DisjunctiveConstraint

NQueenCSP 
	- NQueenCSP
	- NQueenDomain
	- NotAttackingConstraint
	- Pair
```



## Building the project 

Use the command below in your terminal to build the project:

`javac AustraliaCSP/*.java Core/*.java JobCSP/*.java NQueenCSP/*.java Solver/*.java`


## Running the project

To run **AustraliaCSP**:

`java AustraliaCSP/AustraliaCSP`

*Expected runtime: t <= 1s*


To run **JobCSP**:

`java JobCSP/JobCSP`

*Expected runtime: 5<= t <= 10s* 


To run **NQueenCSP**:

`java NQueenCSP/NQueenCSP`

*Expected runtime: ∀n, n<=10 -> t <= 1s*

