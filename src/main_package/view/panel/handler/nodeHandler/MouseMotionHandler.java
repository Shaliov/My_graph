package main_package.view.panel.handler.nodeHandler;

import main_package.controller.GraphService;
import main_package.view.panel.GraphPanel;
import main_package.view.panel.NodePanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;

/**
 * Created by Andrey on 4/17/2016.
 */
public class MouseMotionHandler implements MouseMotionListener {
    private GraphService graphService;
    private GraphPanel graphPanel;

    public MouseMotionHandler(GraphPanel graphPanel, GraphService graphService){
        this.graphPanel = graphPanel;
        this.graphService = graphService;
    }

    public void mouseMoved(MouseEvent event) {
        if (graphPanel.findCircle(event.getPoint()) == null) {
            graphPanel.setCursor(Cursor.getDefaultCursor());
        }
    }

    public void mouseDragged(MouseEvent event) {
        Point point = event.getPoint();
        Ellipse2D circle = graphPanel.findCircle(point);
        if (circle != null) {
            for (NodePanel nodePanel : graphPanel.getNodePanelList()) {
                Ellipse2D currentCircle = nodePanel.getCircle();
                if (circle.equals(currentCircle)) {
                    graphService.updateNode(nodePanel, event);
                    graphPanel.repaint();
                }
            }
        }
    }
}