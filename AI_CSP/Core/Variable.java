package Core;

import java.util.HashSet;

public class Variable implements Comparable{
    protected String name;
    protected int numOfConstraints = 0;
    protected HashSet<Comparable> domain;

    public Variable(String name, HashSet<Comparable> domain){
        this.name = name;
        this.domain = domain;
    }

    public HashSet<Comparable> getDomain() {
        return domain;
    }

    public String getName() {
        return name;
    }

    public void incNumOfConstraints(){
        numOfConstraints++;
    }
    public int getNumOfConstraints(){
        return numOfConstraints;
    }

    @Override
    public int compareTo(Object o) {
        Variable that = (Variable) o;
        return Integer.compare(this.numOfConstraints, that.numOfConstraints);
    }

    @Override
    public String toString() {
        return "var name: [" + this.name + "] its domain list: " + this.domain.toString();
    }

}
