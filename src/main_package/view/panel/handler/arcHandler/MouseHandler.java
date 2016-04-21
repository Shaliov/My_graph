package main_package.view.panel.handler.arcHandler;

import main_package.controller.GraphService;
import main_package.model.Arc;
import main_package.model.Node;
import main_package.view.panel.GraphPanel;

import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

/**
 * Created by Andrey on 4/17/2016.
 */
public class MouseHandler extends MouseAdapter {

    private Node nodeStart;
    private Node nodeEnd;
    private GraphPanel graphPanel;
    private GraphService graphService;

    public MouseHandler(GraphPanel graphPanel, GraphService graphService) {
        this.graphPanel = graphPanel;
        this.graphService = graphService;
    }

    public void mousePressed(MouseEvent event) {
        Node node = graphPanel.findNode(event.getPoint());
        if (node != null)
            if (((event.getModifiersEx() & InputEvent.BUTTON1_DOWN_MASK) != 0)) {
                Node node1 = graphPanel.findNode(event.getPoint());
                nodeStart = node1;
            }

    }
    public void mouseReleased(MouseEvent event) {
        nodeEnd = graphPanel.findNode(event.getPoint());
        if (nodeStart != null && nodeEnd != null) {
            graphService.addArc(nodeStart, nodeEnd);
        }
            nodeStart = null;
            nodeEnd = null;
            graphPanel.deleteTemArc();

    }


    public void mouseClicked(MouseEvent event) {
        Arc currentLine = graphPanel.findArc(event.getPoint());
        if (currentLine != null && event.getClickCount() >= 2) {
            graphService.removeArc(currentLine);
            graphPanel.repaint();
        }
    }

    public Node getNodeEnd() {
        return nodeEnd;
    }
    public Node getNodeStart() {
        return nodeStart;
    }
}
