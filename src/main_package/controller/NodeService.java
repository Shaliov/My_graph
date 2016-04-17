package main_package.controller;

import main_package.view.panel.GraphPanel;
import main_package.view.panel.NodePanel;
import main_package.view.panel.handler.nodeHandler.MouseMotionHandler;
import main_package.view.panel.handler.nodeHandler.MouseHandler;

import java.util.List;

/**
 * Created by Andrey on 4/8/2016.
 */
public class NodeService {
    private GraphService graphService;
    private ArcService arcService;
    private GraphPanel graphPanel;
    private List<NodePanel> nodePanelList;
    private MouseHandler mouseHandler;
    private MouseMotionHandler mouseMotionHandler;


    public NodeService(GraphPanel graphPanel) {
        this.graphPanel = graphPanel;
        nodePanelList = graphPanel.getNodePanelList();
    }

    public void addNode() {
        mouseHandler = new MouseHandler(graphPanel, graphService);
        graphPanel.addMouseListener(mouseHandler);
        mouseMotionHandler = new MouseMotionHandler(graphPanel, graphService);
        graphPanel.addMouseMotionListener(mouseMotionHandler);
    }



    public void setArcService(ArcService arcService) {
        this.arcService = arcService;
    }

    public void setGraphService(GraphService graphService) {
        this.graphService = graphService;
    }
}

