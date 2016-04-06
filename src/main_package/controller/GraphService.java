package main_package.controller;

import main_package.model.Graph;
import main_package.model.Node;
import main_package.view.panel.GraphPanel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.List;

/**
 * Created by Andrey on 4/3/2016.
 */
public class GraphService {
    private final GraphPanel graphPanel;
    private Graph graph = Graph.getInstance();

    public GraphService(GraphPanel graphPanel) {
        this.graphPanel = graphPanel;
    }
    public void addNode(Point2D p)
    {
        Node node = new Node();
        node.setNodeX(p.getX());
        node.setNodeY(p.getY());

        List<Node> nodeList = graph.getNodeList();
        nodeList.add(node);
        graphPanel.addNNode();

    }

    public void updateNode(Node node, MouseEvent event)
    {
        node.setNodeX(event.getX());
        node.setNodeY(event.getY());
       // graphPanel.updateNode();


    }


}
