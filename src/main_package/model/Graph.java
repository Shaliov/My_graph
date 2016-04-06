package main_package.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 4/3/2016.
 */
public class Graph {
    private static Graph instance = null;
    private List<Node> nodeList = new ArrayList<>();
    private List<Arc> arcList = new ArrayList<>();

    private Graph() {
    }

    public static Graph getInstance() {
        if (instance == null) {
            instance = new Graph();
        }
        return instance;
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<Node> nodeList) {
        this.nodeList = nodeList;
    }

    public List<Arc> getArcList() {
        return arcList;
    }

    public void setArcList(List<Arc> arcList) {
        this.arcList = arcList;
    }
}
