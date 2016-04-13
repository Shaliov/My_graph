package main_package.controller;

import main_package.model.Node;
import main_package.view.frame.MainFrame;
import main_package.view.panel.GraphPanel;
import main_package.view.panel.NodePanel;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.util.*;
import java.util.List;

/**
 * Created by Andrey on 4/8/2016.
 */
public class NodeService {
    private GraphService graphService;
    private GraphPanel graphPanel;
    private List<NodePanel> nodePanelList;

    public NodeService(GraphPanel graphPanel){
        this.graphPanel = graphPanel;
        graphService = new GraphService(graphPanel);
        nodePanelList = graphPanel.getNodePanelList();
    }

    public void addNode(GraphPanel graphPanel){
        graphPanel.addMouseListener(new MouseHandler());
        graphPanel.addMouseMotionListener(new MouseMotionHandler());
    }

    private class MouseHandler extends MouseAdapter {
        public void mousePressed(MouseEvent event) {
            Ellipse2D currentCircle = graphPanel.findCircle(event.getPoint());
            if( currentCircle == null)
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


    private class MouseMotionHandler implements MouseMotionListener {
        public void mouseMoved(MouseEvent event) {
            if (graphPanel.findCircle(event.getPoint()) == null) {
                graphPanel.setCursor(Cursor.getDefaultCursor());
            }
        }

        public void mouseDragged(MouseEvent event) {
            if(graphPanel.findCircle(event.getPoint()) != null)
            {
                for(int i = 0; i < nodePanelList.size(); i++)
                {
                    if(graphPanel.findCircle(event.getPoint()) == nodePanelList.get(i).getCircle())
                    {
                        graphService.updateNode(nodePanelList.get(i), event);
                        graphPanel.repaint();
                    }
                }
            }
        }
    }


}
