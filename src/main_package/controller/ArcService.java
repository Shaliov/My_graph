package main_package.controller;

import main_package.view.panel.ArcPanel;
import main_package.view.panel.GraphPanel;
import main_package.view.panel.handler.arcHandler.MouseHandler;
import main_package.view.panel.handler.arcHandler.MouseMotionHandler;

import java.util.List;

/**
 * Created by Andrey on 4/8/2016.
 */
public class ArcService {
    private GraphService graphService;
    private GraphPanel graphPanel;
    private List<ArcPanel> arcPanelList;
    private MouseHandler mouseHandler;
    private MouseMotionHandler mouseMotionHandler;

    public ArcService(GraphPanel graphPanel) {
        this.graphPanel = graphPanel;
        arcPanelList = graphPanel.getArcPanelList();
    }

    public void addArc() {
        mouseHandler = new MouseHandler(graphPanel, graphService);
        graphPanel.addMouseListener(mouseHandler);
        mouseMotionHandler = new MouseMotionHandler(graphPanel);
        mouseMotionHandler.setMouseHandler(mouseHandler);
        graphPanel.addMouseMotionListener(mouseMotionHandler);
    }

    public MouseHandler getMouseHandler() {
        return mouseHandler;
    }

    public MouseMotionHandler getMouseMotionHandler() {
        return mouseMotionHandler;
    }

    public void setGraphService(GraphService graphService) {
        this.graphService = graphService;
    }
}


