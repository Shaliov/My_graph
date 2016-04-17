package main_package.view.panel.handler.nodeHandler;

import main_package.controller.GraphService;
import main_package.model.Graph;
import main_package.view.panel.GraphPanel;
import main_package.view.panel.NodePanel;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;

/**
 * Created by Andrey on 4/17/2016.
 */
public class MouseHandler extends MouseAdapter {
    private GraphPanel graphPanel;
    private GraphService graphService;
    public MouseHandler(GraphPanel graphPanel, GraphService graphService){
        this.graphPanel = graphPanel;
        this.graphService = graphService;
    }

    public void mousePressed(MouseEvent event) {
        Ellipse2D currentCircle = graphPanel.findCircle(event.getPoint());
        if (currentCircle == null)
            if (((event.getModifiersEx() & InputEvent.BUTTON1_DOWN_MASK) != 0)) {
                graphService.addNode(event.getPoint());
            }
    }

    public void mouseClicked(MouseEvent event) {
        if (graphPanel.findCircle(event.getPoint()) != null && event.getClickCount() >= 2) {
            graphService.removeNode(graphPanel.findCircle(event.getPoint()));
            graphPanel.repaint();
        }
    }
}


