package main_package.controller;

import main_package.model.Arc;
import main_package.model.Graph;
import main_package.model.Node;
import main_package.view.panel.ArcPanel;
import main_package.view.panel.GraphPanel;
import main_package.view.panel.NodePanel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 4/3/2016.
 */
public class GraphService {
    private final GraphPanel graphPanel;
    private Graph graph = Graph.getInstance();
    private List<Node> nodeList = graph.getNodeList();
    private List<Arc> arcList = graph.getArcList();
    private List<ArcPanel> arcPanelList;
    private Arc arc;

    public GraphService(GraphPanel graphPanel) {
        this.graphPanel = graphPanel;
    }
    public void addNode(Point2D p)
    {
        Node node = new Node();
        node.setNodeX(p.getX());
        node.setNodeY(p.getY());
        arcPanelList = graphPanel.getArcPanelList();

        nodeList.add(node);
        graphPanel.addNode();
    }

    public List<Arc> getArcList() {
        return arcList;
    }

    public void addArc(Node nodeStart, Node nodeEnd){
        arc = new Arc();

        arc.setArcStartNode(nodeStart);
        arc.setArcEndNode(nodeEnd);

        arcList.add(arc);
        graphPanel.addArc();
    }

    public void removeNode(Ellipse2D e){
        if(e == null) {
            return;
        }
        for(int i = graphPanel.getNodePanelList().size() - 1; i != -1 ; i--)
            if(e == graphPanel.getNodePanelList().get(i).getCircle())
            {
                for(int j = arcList.size() - 1; j != -1 ; j--){
                    if(graphPanel.getNodePanelList().get(i).getNode() == arcList.get(j).getArcStartNode() || graphPanel.getNodePanelList().get(i).getNode() == arcList.get(j).getArcEndNode()){
                        removeArc(arcList.get(j));
                    }
                }
                nodeList.remove(graphPanel.getNodePanelList().get(i).getNode());
                graphPanel.getNodePanelList().remove(i);
                graphPanel.repaint();
            }
    }

    public void removeArc(Arc arc)
    {
        if(arc == null) {
            return;
        }
        for(int i = 0; i < graphPanel.getArcPanelList().size(); i++)
            if(arc == graphPanel.getArcPanelList().get(i).getArc())
            {
                arcList.remove(graphPanel.getArcPanelList().get(i).getArc());
                graphPanel.getArcPanelList().remove(i);
                graphPanel.repaint();
            }
    }

    public void clean(){
        for(int i =  graphPanel.getNodePanelList().size() - 1; i != -1; i--)
        {
           removeNode(graphPanel.getNodePanelList().get(i).getCircle());
        }
    }

    public void updateNode(NodePanel nodePanel, MouseEvent event)
    {
        nodePanel.getNode().setNodeX(event.getX());
        nodePanel.getNode().setNodeY(event.getY());

        nodePanel.getCircle().setFrame(event.getX() - 25 / 2, event.getY() - 25 / 2, 25, 25);

    }

    public void updateArc(Node node)
    {
        List<Arc> arcList = new ArrayList<>();
        for(int i = arcList.size(); i != -1; i--){


        }

    }

}
