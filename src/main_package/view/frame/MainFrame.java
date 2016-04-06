package main_package.view.frame;

import main_package.controller.GraphService;
import main_package.view.panel.GraphPanel;
import main_package.view.panel.NodePanel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;


import static main_package.view.action.Action.*;
import static main_package.view.action.FileAction.*;

/**
 * Created by Andrey on 4/2/2016.
 */
public class MainFrame {
    private GraphService graphService;
    private GraphPanel graphPanel;
    private List<NodePanel> nodePanelList;

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
        graphPanel.setVisible(true);

        actioN(mainFrame);

        cleanAction.putValue(Action.SHORT_DESCRIPTION, "Clean");

        JToolBar barAction = new JToolBar(SwingConstants.VERTICAL);
        barAction.add(new AbstractAction("Node", new ImageIcon("node.gif")) {
            public void actionPerformed(ActionEvent event) {
                graphPanel.addMouseListener(new MouseHandler());
                graphPanel.addMouseMotionListener(new MouseMotionHandler());
            }
        });
        Action arcAction = new AbstractAction("arc", new ImageIcon("arc.gif")) {
            public void actionPerformed(ActionEvent event) {

            }
        };
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

    private class MouseHandler extends MouseAdapter {
        public void mousePressed(MouseEvent event) {
            if(((event.getModifiersEx() & InputEvent.BUTTON1_DOWN_MASK) != 0))
            graphService.addNode(event.getPoint());

        }
    }
    private class MouseMotionHandler implements MouseMotionListener {
        public void mouseMoved(MouseEvent event) {
            if ((event.getPoint()) != nodePanelList) {
                graphPanel.setCursor(Cursor.getDefaultCursor());
            }
        }

        public void mouseDragged(MouseEvent event) {
                for(int i=0; i < nodePanelList.size(); i++){
                    if(nodePanelList.get(i).getCircle() == graphPanel.find(event.getPoint())){
                        graphService.updateNode(nodePanelList.get(i).getNode(), event);

                    }
                }
            }
        }


    }
