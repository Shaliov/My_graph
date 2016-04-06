package main_package.model;

/**
 * Created by Andrey on 4/3/2016.
 */
public class Node {
    double x;
    double y;
    String name;

    public Node() {
    }

    public double getNodeX() {
        return x;
    }

    public double setNodeX(double setX) {
        return x = setX;
    }

    public double getNodeY() {
        return y;
    }

    public double setNodeY(double setY) {
        return y = setY;
    }

    public String getNodeName() {
        return name;
    }

    public void setNodeName(String name) {
        this.name = name;
    }
}
