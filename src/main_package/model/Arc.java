package main_package.model;

/**
 * Created by Andrey on 4/3/2016.
 */
public class Arc {
    Node arcStartNode;
    Node arcEndNode;
//    String name;
    int weight;

    public Arc(){}

    public Node getArcStartNode() {
        return arcStartNode;
    }

    public Node getArcEndNode() {
        return arcEndNode;
    }

    public int getWeight() {
        return weight;
    }

//    public String getAcrName() {
//        return name;
//    }

    public double getArcWeight() {
        return weight;
    }

    public void setArcStartNode(Node arcStartNode) {
        this.arcStartNode = arcStartNode;
    }

    public void setArcEndNode(Node arcEndNode) {
        this.arcEndNode = arcEndNode;
    }

//    public void setName(String name) {
//        this.name = name;
//    }

    public void setWeight(int weight) {
        this.weight = weight;
    }


}
