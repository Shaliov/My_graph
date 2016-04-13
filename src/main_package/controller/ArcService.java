package main_package.controller;

import main_package.model.Arc;
import main_package.model.Node;
import main_package.view.panel.ArcPanel;
import main_package.view.panel.GraphPanel;
import main_package.view.panel.NodePanel;

import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.List;

/**
 * Created by Andrey on 4/8/2016.
 */
public class ArcService {
    private GraphService graphService;
    private GraphPanel graphPanel;
    private List<ArcPanel> arcPanelList;


    public ArcService(GraphPanel graphPanel) {
        this.graphPanel = graphPanel;
        graphService = new GraphService(graphPanel);
        arcPanelList = graphPanel.getArcPanelList();
    }

    public void addArc(GraphPanel graphPanel) {
        graphPanel.addMouseListener(new MouseHandler());
        graphPanel.addMouseMotionListener(new MouseMotionHandler());
    }

    private class MouseHandler extends MouseAdapter {
        private Node nodeStart;
        public void mousePressed(MouseEvent event) {
            if (graphPanel.findNode(event.getPoint()) != null)
                if (((event.getModifiersEx() & InputEvent.BUTTON1_DOWN_MASK) != 0)) {
                    nodeStart = graphPanel.findNode(event.getPoint());
                }

        }
        public void mouseReleased(MouseEvent event) {
            if (nodeStart != null && graphPanel.findNode(event.getPoint()) != null)
                graphService.addArc(nodeStart, graphPanel.findNode(event.getPoint()));
        }

        public void mouseClicked(MouseEvent event) {
            Arc currentLine = graphPanel.findArc(event.getPoint());
            if (currentLine != null && event.getClickCount() >= 2) {
                graphService.removeArc(currentLine);
                graphPanel.repaint();
            }
        }



    }

    private class MouseMotionHandler implements MouseMotionListener {
        Node nodeStart;
        public void mouseMoved(MouseEvent event) {
            if (graphPanel.findCircle(event.getPoint()) == null) {
                graphPanel.setCursor(Cursor.getDefaultCursor());
            }
        }

        public void mouseDragged(MouseEvent event) {
            nodeStart = graphPanel.findNode(event.getPoint());
            if (nodeStart != null) {

            }
        }


    }
}


