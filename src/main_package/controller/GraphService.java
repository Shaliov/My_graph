package main_package.controller;

import main_package.model.Arc;
import main_package.model.Graph;
import main_package.model.Node;
import main_package.view.panel.ArcPanel;
import main_package.view.panel.GraphPanel;
import main_package.view.panel.NodePanel;

import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.List;

/**
 * Created by Andrey on 4/3/2016.
 */
public class GraphService {
    private final GraphPanel graphPanel;
    private ArcPanel arcPanel;
    private Graph graph = Graph.getInstance();
    private List<Node> nodeList = graph.getNodeList();
    private List<Arc> arcList = graph.getArcList();
    private List<NodePanel> nodePanelList;
    private List<ArcPanel> arcPanelList;
    private Arc arc;

    public void setNodePanelList(List<NodePanel> nodePanelList) {
        this.nodePanelList = nodePanelList;
    }
    public void setArcPanelList(List<ArcPanel> arcPanelList) {
        this.arcPanelList = arcPanelList;
    }

    public GraphService(GraphPanel graphPanel) {
        this.graphPanel = graphPanel;
    }

    public void addNode(Point2D p) {
        Node node = new Node();
        node.setNodeX(p.getX());
        node.setNodeY(p.getY());

        nodeList.add(node);
        graphPanel.addNode();
        graphPanel.repaint();
    }

    public void addArc(Node nodeStart, Node nodeEnd) {
        for(Arc arc1 : arcList)
        {
            Node arcEndNode = arc1.getArcEndNode();
            Node arcStartNode = arc1.getArcStartNode();
            if(arcStartNode.equals(nodeStart)&& arcEndNode.equals(nodeEnd) || arcStartNode.equals(nodeEnd)&& arcEndNode.equals(nodeStart) )
                return;
        }
        arc = new Arc();

        arc.setArcStartNode(nodeStart);
        arc.setArcEndNode(nodeEnd);

        arcList.add(arc);
        graphPanel.addArc();
        graphPanel.repaint();
    }

    public void graphNull(Graph graph) {
        graph.setInstance(null);
    }
    public List<Arc> getArcList() {
        return arcList;
    }


    public void removeNode(Ellipse2D e) {
        if (e == null) {
            return;
        }
        List<NodePanel> nodePanelList = graphPanel.getNodePanelList();
        for (int i = graphPanel.getNodePanelList().size() - 1; i != -1; i--) {
            if (e.equals(nodePanelList.get(i).getCircle())) {
                for (int j = arcList.size() - 1; j != -1; j--) {
                    if (nodePanelList.get(i).getNode().equals(arcList.get(j).getArcStartNode()) || nodePanelList.get(i).getNode().equals(arcList.get(j).getArcEndNode())) {
                        removeArc(arcList.get(j));
                    }
                }
                nodeList.remove(nodePanelList.get(i).getNode());
                graphPanel.getNodePanelList().remove(i);
                graphPanel.repaint();
            }
        }
    }

    public void removeArc(Arc arc) {
        if (arc == null) {
            return;
        }
        for (int i = 0; i < graphPanel.getArcPanelList().size(); i++)
            if (arc.equals(graphPanel.getArcPanelList().get(i).getArc())) {
                arcList.remove(graphPanel.getArcPanelList().get(i).getArc());
                graphPanel.getArcPanelList().remove(i);
                graphPanel.repaint();
            }
    }

    public void removeAll() {
        clean();
        graphPanel.repaint();
    }

    private void clean() {
        graphPanel.getNodePanelList().clear();
        graphPanel.getArcPanelList().clear();
        graph.getArcList().clear();
        graph.getNodeList().clear();
    }

    public void updateNode(NodePanel nodePanel, MouseEvent event) {
        nodePanel.getNode().setNodeX(event.getX());
        nodePanel.getNode().setNodeY(event.getY());

        nodePanel.getCircle().setFrame(event.getX() - 25 / 2, event.getY() - 25 / 2, 25, 25);
        updateArc(nodePanel.getNode());

    }

    private Arc findArcOfStartNode(Node node) {
        for (Arc arc : arcList) {
            if (arc.getArcStartNode().equals(node)) {
                return arc;
            }
        }
        return null;
    }

    private Arc findArcOfEndNode(Node node) {
        for (Arc arc : arcList) {
            if (arc.getArcEndNode().equals(node)) {
                return arc;
            }
        }
        return null;
    }

    public void updateArc(Node node) {
        for (Arc arc : arcList) {
                graphPanel.getArcPanel().setGraphPanel(graphPanel);
            if (arc.equals(findArcOfStartNode(node))) {
                arc.setArcStartNode(node);
                graphPanel.getArcPanel().repaintLine();
            }
            if (arc.equals(findArcOfEndNode(node))) {
                arc.setArcEndNode(node);
                graphPanel.getArcPanel().repaintLine();
            }
        }


    }
}
