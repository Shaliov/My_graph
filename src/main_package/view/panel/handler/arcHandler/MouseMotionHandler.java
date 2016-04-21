package main_package.view.panel.handler.arcHandler;

import main_package.model.Node;
import main_package.view.panel.GraphPanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Created by Andrey on 4/17/2016.
 */
public class MouseMotionHandler implements MouseMotionListener {
    private GraphPanel graphPanel;
    private MouseHandler mouseHandler;

    public void setMouseHandler(MouseHandler mouseHandler) {
        this.mouseHandler = mouseHandler;
    }

    public MouseMotionHandler(GraphPanel graphPanel) {
        this.graphPanel = graphPanel;
    }

    public void mouseMoved(MouseEvent event) {
        if (graphPanel.findCircle(event.getPoint()) == null) {
            graphPanel.setCursor(Cursor.getDefaultCursor());
        }
    }

    public void mouseDragged(MouseEvent event) {
        Node nodeStart = mouseHandler.getNodeStart();
        if(nodeStart != null)
        graphPanel.tempArc(nodeStart, event.getPoint());
    }
}