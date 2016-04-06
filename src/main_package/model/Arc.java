package main_package.model;

/**
 * Created by Andrey on 4/3/2016.
 */
public class Arc {
    Node arcStartNode;
    Node arcEndNode;
    String name;
    double weight;

    public Arc(){}

    public Node getArcStartNode() {
        return arcStartNode;
    }

    public Node getArcEndNode() {
        return arcEndNode;
    }

    public String getAcrName() {
        return name;
    }

    public double getArcWeight() {
        return weight;
    }
}
