package main_package.view.frame;

import main_package.controller.ArcService;
import main_package.controller.GraphService;
import main_package.controller.NodeService;
import main_package.view.panel.GraphPanel;
import main_package.view.panel.NodePanel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;


import static main_package.view.action.FileAction.*;

/**
 * Created by Andrey on 4/2/2016.
 */
public class MainFrame {
    private GraphService graphService;
    private GraphPanel graphPanel;
    private List<NodePanel> nodePanelList;
    private NodeService nodeService;
    private ArcService arcService;

    public MainFrame() {
        JFrame mainFrame = new JFrame();
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        mainFrame.setTitle("DrawPanel");
        mainFrame.setSize(screenWidth / 2, screenHeight / 2);
        mainFrame.setLocation(screenWidth / 4, screenHeight / 4);

        JMenu fileMenu = new JMenu("File");
        fileMenu(fileMenu);

        JMenuBar menuBar = new JMenuBar();
        mainFrame.setJMenuBar(menuBar);

        JMenu helpMenu = new JMenu("Help");
        helpMenu(helpMenu);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);


        graphPanel = new GraphPanel();
        mainFrame.add(graphPanel, BorderLayout.CENTER);
        mainFrame.setVisible(true);
        graphService = new GraphService(graphPanel);
        nodePanelList = graphPanel.getNodePanelList();
        nodeService = new NodeService(graphPanel);
        arcService = new ArcService(graphPanel);
        graphPanel.setVisible(true);

        actioN(mainFrame);

        graphPanel.setGraphService(graphService);
        nodeService.setGraphService(graphService);
        arcService.setGraphService(graphService);


        JToolBar barAction = new JToolBar(SwingConstants.VERTICAL);
        barAction.add(new AbstractAction("Node", new ImageIcon("node.gif")) {
            public void actionPerformed(ActionEvent event) {
                eraseListeners();
                nodeService.addNode();
            }
        });
        Action arcAction = new AbstractAction("arc", new ImageIcon("arc.gif")) {
            public void actionPerformed(ActionEvent event) {
                eraseListeners();
                arcService.addArc();
            }
        };

        Action cleanAction = new AbstractAction("clean", new ImageIcon("clean.gif")) {
            public void actionPerformed(ActionEvent event) {
                eraseListeners();
                graphService.clean();
            }
        };

        cleanAction.putValue(Action.SHORT_DESCRIPTION, "Clean");
        barAction.add(arcAction);
        barAction.add(cleanAction);
        mainFrame.add(barAction, BorderLayout.WEST);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    public static void fileMenu(JMenu fileMenu) {
        fileMenu.setMnemonic('F');
        JMenuItem newItem = fileMenu.add("New");
        newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));

        JMenuItem openItem = fileMenu.add("Open");
        openItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));

        fileMenu.addSeparator();
        JMenuItem saveAction = fileMenu.add("Save");
        saveAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));

        JMenuItem saveAsAction = fileMenu.add("SaveAs");
        saveAsAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.SHIFT_MASK + InputEvent.CTRL_MASK));
        fileMenu.addSeparator();

        fileMenu.add(new AbstractAction("Exit") {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
    }
    public static void helpMenu(JMenu helpMenu) {
        helpMenu.setMnemonic('H');

        JMenuItem aboutAction = helpMenu.add("About");
        aboutAction.setMnemonic(KeyEvent.VK_F1);
    }




    public void eraseListeners () {
        for (MouseListener mList : graphPanel.getMouseListeners())
        {
            graphPanel.removeMouseListener(mList);
        }
        for (MouseMotionListener mMList : graphPanel.getMouseMotionListeners())
        {
            graphPanel.removeMouseMotionListener(mMList);
        }
    }

}
