package main_package.algoritm;

import main_package.model.Arc;
import main_package.model.Graph;
import main_package.model.Node;
import main_package.view.panel.ArcPanel;
import main_package.view.panel.GraphPanel;
import main_package.view.panel.NodePanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 4/20/2016.
 */
public class Algoritm {
    private Graph graph = Graph.getInstance();
    private List<Node> nodeList;
    private GraphPanel graphPanel;

    public void setGraphPanel(GraphPanel graphPanel) {
        this.graphPanel = graphPanel;
    }

    private List<Node> visitedNode = new ArrayList();
    private List<Node> unvisitedNode = new ArrayList();
    private List<Arc> arcList;
    private Node startNode;
    private Node endNode;
    private int[][] adjacencyMatrix = null;
    private int indexStartNode;
    private int indexEndNode;

    public int getIndexStartNode() {
        return indexStartNode;
    }

    public int getIndexEndNode() {
        return indexEndNode;
    }

    public void setIndexStartNode(int indexStartNode) {

        this.indexStartNode = indexStartNode;
    }

    public void setIndexEndNode(int indexEndNode) {
        this.indexEndNode = indexEndNode;
    }

    public Graph getGraph() {
        return graph;
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    public List<Arc> getArcList() {
        return arcList;
    }

    public Node getStartNode() {
        return startNode;
    }

    public Node getEndNode() {
        return endNode;
    }

    public int[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    public void setGraph(Graph graph) {

        this.graph = graph;
    }

    public void setNodeList(List<Node> nodeList) {
        this.nodeList = nodeList;
    }

    public void setArcList(List<Arc> arcList) {
        this.arcList = arcList;
    }

    public void setStartNode(Node startNode) {
        this.startNode = startNode;
    }

    public void setEndNode(Node endNode) {
        this.endNode = endNode;
    }

    public void setAdjacencyMatrix(int[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
    }

    public Algoritm(GraphPanel graphPanel) {
        nodeList = graph.getNodeList();
        arcList = graph.getArcList();
        setGraphPanel(graphPanel);
        adjacencyMatrix = new int[nodeList.size()][nodeList.size()];
        matrixFilling();
        find();
        findIndexNode(getStartNode().getNodeName(), getEndNode().getNodeName());
        fillUnvisitedNode();
        findingTheShortestPath();
    }

    private void find() {
        while (getStartNode() == null) {
            setStartNode(findNode(JOptionPane.showInputDialog("Введите имя начального узла: ").trim()));
            if (getStartNode() == null) {
                JOptionPane.showMessageDialog(null, "такого узла нету", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        while (getEndNode() == null) {
            setEndNode(findNode(JOptionPane.showInputDialog("Введите имя конечного узла: ").trim()));
            if (getEndNode() == null) {
                JOptionPane.showMessageDialog(null, "такого узла нету", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private Node findNode(String name) {
        for (Node node : nodeList) {
            if (node.getNodeName().equals(name)) {
                return node;
            }
        }
        return null;
    }

    private void fillUnvisitedNode() {
        for (Node node : nodeList) {
            if (node.equals(getStartNode()) != true) {
                unvisitedNode.add(node);
            }
        }
        visitedNode.add(getStartNode());
    }

    private void matrixFilling() {
        for (int i = 0; i < nodeList.size(); i++) {
            for (int j = 0; j < nodeList.size(); j++) {
                if (i == j) {
                    adjacencyMatrix[i][j] = 0;
                } else {
                    adjacencyMatrix[i][j] = weightSearch(nodeList.get(i), nodeList.get(j));
                }
            }
        }
    }

    private int weightSearch(Node startNode, Node endNode) {
        for (Arc arc : arcList) {
            Node arcStartNode = arc.getArcStartNode();
            Node arcEndNode = arc.getArcEndNode();
            if (arcStartNode.equals(startNode) && arcEndNode.equals(endNode)) {
                return arc.getWeight();
            }
        }
        return 10000;
    }

    private void findIndexNode(String startNode, String endNode) {
        for (int i = 0; i < nodeList.size(); i++) {
            if (nodeList.get(i).getNodeName().equals(startNode)) {
                setIndexStartNode(i);
            }
            if (nodeList.get(i).getNodeName().equals(endNode)) {
                setIndexEndNode(i);
            }
        }

    }


    private void findingTheShortestPath() {
        int size = nodeList.size();
        final int INF = 1000000000;
        int[] dist = new int[size];
        dist[indexStartNode] = 0;
        for (int i = 0; i < size; i++) {
            if (i != indexStartNode) {
                dist[i] = INF;
            }
        }
        int[] used = new int[size];
        for (int i = 0; i < size; i++) {
            used[i] = 0;
        }
        int min_dist = 0;
        int min_vertex = indexStartNode;
        while (min_dist < INF) {
            int i = min_vertex;
            used[i] = 1;
            nodeList.get(i).setColor(Color.RED);
            graphPanel.repaint();
            int choose = JOptionPane.showOptionDialog(null, "дродолжить алгоритм?",
                    null, JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
            if (choose == JOptionPane.NO_OPTION) {
                while (min_dist < INF) {
                    int l = min_vertex;
                    used[l] = 1;
                    for (int j = 0; j < size; ++j)
                        if (dist[i] + adjacencyMatrix[l][j] < dist[j])
                            dist[j] = dist[i] + adjacencyMatrix[l][j];
                    min_dist = INF;
                    for (int j = 0; j < size; ++j)
                        if (used[j] == 0 && dist[j] < min_dist) {
                            min_dist = dist[j];
                            min_vertex = j;
                        }
                }
                JOptionPane.showMessageDialog(null, "длина кратчайшего пути от " + startNode.getNodeName() + " до "
                        + endNode.getNodeName() + " = " + dist[indexEndNode], null, JOptionPane.WARNING_MESSAGE | JOptionPane.OK_OPTION);
                return;
            }
            for (int j = 0; j < size; ++j)
                if (dist[i] + adjacencyMatrix[i][j] < dist[j])
                    dist[j] = dist[i] + adjacencyMatrix[i][j];
            min_dist = INF;
            for (int j = 0; j < size; ++j)
                if (used[j] == 0 && dist[j] < min_dist) {
                    min_dist = dist[j];
                    min_vertex = j;
                }
        }

        JOptionPane.showMessageDialog(null, "длина кратчайшего пути от " + startNode.getNodeName() + " до "
                + endNode.getNodeName() + " = " + dist[indexEndNode], null, JOptionPane.WARNING_MESSAGE | JOptionPane.OK_OPTION);


    }
}
