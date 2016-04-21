package main_package.algoritm;

import main_package.model.Arc;
import main_package.model.Graph;
import main_package.model.Node;

import java.util.List;

/**
 * Created by Andrey on 4/20/2016.
 */
public class Algoritm {
    private List<Node> nodeList;
    private List<Arc> arcList;
    private Graph graph = Graph.getInstance();

    public Algoritm() {
        nodeList = graph.getNodeList();
        arcList = graph.getArcList();

    }




}
