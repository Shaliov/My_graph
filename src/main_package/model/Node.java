package main_package.model;

/**
 * Created by Andrey on 4/3/2016.
 */
public class Node {
    double x;
    double y;
    String name;

    public Node() {
        name = "";
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        if (Double.compare(node.x, x) != 0) return false;
        return Double.compare(node.y, y) == 0;

    }
}
