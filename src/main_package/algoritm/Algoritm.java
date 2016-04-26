package main_package.algoritm;

import main_package.model.Arc;
import main_package.model.Graph;
import main_package.model.Node;
import main_package.view.panel.ArcPanel;
import main_package.view.panel.GraphPanel;
import main_package.view.panel.NodePanel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 4/20/2016.
 */
public class Algoritm {
    private Graph graph = Graph.getInstance();
    private List<Node> nodeList;
    private List<Node> visitedNode = new ArrayList();
    private List<Node> unvisitedNode = new ArrayList();
    private List<Arc> arcList;
    private Node startNode;
    private Node endNode;
    private int[][] adjacencyMatrix = null;
    private int indexStartNode;
    private int indexEndNode;
    private int wieght = 0;
    private int tempWieght;

    public void setWieght(int wieght) {
        this.wieght = wieght;
    }
    public void setTempWieght(int tempWieght) {
        this.tempWieght = tempWieght;
    }
    public int getWieght() {

        return wieght;
    }
    public int getTempWieght() {
        return tempWieght;
    }
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
        adjacencyMatrix = new int[nodeList.size()][arcList.size()];
        matrixFilling();
        find();
        findIndexNode(getStartNode().getNodeName(),getEndNode().getNodeName());
        fillUnvisitedNode();
        findingTheShortestPath();
    }
    private void find() {
        while(getStartNode() == null) {
            setStartNode(findNode(JOptionPane.showInputDialog("Введите имя начального узла: ").trim()));
            if(getStartNode() == null) {
                JOptionPane.showMessageDialog
                        (null, "такого узла нету", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        while(getEndNode() == null) {
            setEndNode(findNode(JOptionPane.showInputDialog("Введите имя конечного узла: ").trim()));
            if(getEndNode() == null) {
                JOptionPane.showMessageDialog
                        (null, "такого узла нету", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }

    }
    private Node findNode(String name) {
        for(Node node : nodeList) {
            if(node.getNodeName().equals(name)){
                return node;
            }
        }
        return null;
    }
    private void fillUnvisitedNode() {
        for(Node node : nodeList) {
            if(node.equals(getStartNode()) != true) {
                unvisitedNode.add(node);
            }
        }
        visitedNode.add(getStartNode());
    }
    private void matrixFilling() {
        for (int i = 0; i < nodeList.size(); i++) {
            for(int j = 0; j < arcList.size(); j++) {
                adjacencyMatrix[i][j] = weightSearch(nodeList.get(i), nodeList.get(j)) ;
            }
        }
    }
    private int weightSearch(Node startNode, Node endNode) {
        for(Arc arc : arcList) {
            Node arcStartNode = arc.getArcStartNode();
            Node arcEndNode = arc.getArcEndNode();
            if(arcStartNode.equals(startNode) && arcEndNode.equals(endNode) || arcStartNode.equals(endNode) && arcEndNode.equals(startNode)  ) {
                return arc.getWeight();
            }
        }
        return 0;
    }
    private void findIndexNode(String startNode, String  endNode) {
        for(int i = 0; i < nodeList.size(); i ++) {
            if(nodeList.get(i).getNodeName().equals(startNode)) {
                setIndexStartNode(i);
            }
            if(nodeList.get(i).getNodeName().equals(endNode)) {
                setIndexEndNode(i);
            }
        }

    }
//    private Arc findArc(Node startNode, Node endNode) {
//        for(Arc arc : arcList) {
//            Node arcStartNode = arc.getArcStartNode();
//            Node arcEndNode = arc.getArcEndNode();
//            if(arcStartNode.equals(startNode) && arcEndNode.equals(endNode) || arcStartNode.equals(endNode) && arcEndNode.equals(startNode)  ) {
//                return arc;
//            }
//        }
//        return null;
//    }
    private void findingTheShortestPath() {
        if(indexEndNode == indexStartNode) {
            setWieght(weightSearch(nodeList.get(indexStartNode), nodeList.get(indexEndNode)));
            return;
        }
        if(adjacencyMatrix[indexStartNode][indexEndNode] != 0){
                wieght = adjacencyMatrix[indexStartNode][indexEndNode];
            }
        for (int i = 0; i < nodeList.size(); i++) {
            if(i != indexStartNode) {
                
              }
            }


    }




}
