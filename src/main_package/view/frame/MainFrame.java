package main_package.view.frame;

import main_package.XML.FileXML;
import main_package.controller.ArcService;
import main_package.controller.GraphService;
import main_package.controller.NodeService;
import main_package.model.Graph;
import main_package.view.panel.GraphPanel;
import main_package.view.panel.NodePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * Created by Andrey on 4/21/2016.
 */
public class MainFrame {
    private GraphService graphService;
    private GraphPanel graphPanel;
    private List<NodePanel> nodePanelList;
    private NodeService nodeService;
    private ArcService arcService;
    private JFrame mainFrame;
    private FileXML fileXML;
    private JToolBar barAction;

    public MainFrame() {
        JFrame mainFrame = new JFrame();
        this.mainFrame = mainFrame;
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        fileXML = new FileXML();
        mainFrame.setTitle("DrawPanel");
        mainFrame.setSize(screenWidth / 2, screenHeight / 2);
        mainFrame.setLocation(screenWidth / 4, screenHeight / 4);
        JToolBar bar = new JToolBar();
        mainToolBar(bar);
        JMenu fileMenu = new JMenu("File");
        fileMenu(fileMenu);
        JMenuBar menuBar = new JMenuBar();
        mainFrame.setJMenuBar(menuBar);
        JMenu helpMenu = new JMenu("Help");
        helpMenu(helpMenu);
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(3);
    }

    private void createNewFile() {
        graphPanel = new GraphPanel();
        graphService = new GraphService(graphPanel);
        nodePanelList = graphPanel.getNodePanelList();
        mainFrame.add(graphPanel, "Center");
        nodeService = new NodeService(graphPanel);
        arcService = new ArcService(graphPanel);
        graphPanel.setVisible(true);
        graphPanel.setGraphService(graphService);
        nodeService.setGraphService(graphService);
        arcService.setGraphService(graphService);
        barAction = new JToolBar(1);
        toolBar(barAction);
        barAction.setVisible(true);
        mainFrame.setVisible(true);
    }
    private void openFile() {
        if(this.fileXML.openFile()) {
            createNewFile();
            new Graph(fileXML.getGraph());
            graphPanel.repaint();
        }
    }
    private void saveFile() {
        if (fileXML.getMessage() == null) fileXML.saveAsFile();
        else{
            fileXML.saveFile();
        }
    }
    private void closeFile() {
        mainFrame.remove(graphPanel);
        mainFrame.remove(barAction);
        mainFrame.repaint();
        mainFrame.validate();
    }



    public void fileMenu(JMenu fileMenu) {
        fileMenu.setMnemonic('F');
        JMenuItem newItem = new JMenuItem("New file", new ImageIcon("new.gif"));
        newItem.setAccelerator(KeyStroke.getKeyStroke(78, 2));
        newItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createNewFile();
            }
        });
        fileMenu.add(newItem);
        JMenuItem openAction = new JMenuItem("Open file", new ImageIcon("open.gif"));
        openAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        openAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });
        fileMenu.add(openAction);
        fileMenu.addSeparator();

        JMenuItem saveAction = new JMenuItem("Save", new ImageIcon("save.gif"));
        saveAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        saveAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveFile();
            }
        });
        fileMenu.add(saveAction);
        JMenuItem saveAsAction = new JMenuItem("Save As", new ImageIcon("saveAs.gif"));
        saveAsAction.setAccelerator(KeyStroke.getKeyStroke(83, 3));
        saveAsAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileXML.saveAsFile();
            }
        });
        fileMenu.add(saveAsAction);
        fileMenu.addSeparator();





        fileMenu.addSeparator();
        fileMenu.add(new AbstractAction("Exit") {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
    }

    private void toolBar(JToolBar barAction) {
        barAction.add(new AbstractAction("Node", new ImageIcon("node.gif")) {
            public void actionPerformed(ActionEvent event) {
                MainFrame.this.graphPanel.eraseListeners();
                MainFrame.this.graphPanel.addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent e) {
                        super.mousePressed(e);
                        graphPanel.setPointNode(e.getPoint());
                    }
                });
                nodeService.addNode();
            }
        });
        AbstractAction arcAction = new AbstractAction("arc", new ImageIcon("arc.gif")) {
            public void actionPerformed(ActionEvent event) {
                graphPanel.eraseListeners();
                graphPanel.addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent e) {
                        super.mousePressed(e);
                        graphPanel.setPointNode(e.getPoint());
                    }
                });
                arcService.addArc();
            }
        };
        AbstractAction cleanAction = new AbstractAction("clean", new ImageIcon("clean.gif")) {
            public void actionPerformed(ActionEvent event) {
                graphPanel.eraseListeners();
                graphService.removeAll();
            }
        };
        cleanAction.putValue("ShortDescription", "Clean");
        barAction.add(arcAction);
        barAction.add(cleanAction);
        this.mainFrame.add(barAction, "West");
    }

    private void mainToolBar(JToolBar bar) {
        AbstractAction newAction = new AbstractAction("New file", new ImageIcon("new.gif")) {
            public void actionPerformed(ActionEvent event) {
                createNewFile();
            }
        };
        AbstractAction openAction = new AbstractAction("Open File", new ImageIcon("open.gif")) {
            public void actionPerformed(ActionEvent event) {
                openFile();
            }
        };
        AbstractAction saveActionBar = new AbstractAction("Save File", new ImageIcon("save.gif")) {
            public void actionPerformed(ActionEvent event) {
                saveFile();
            }
        };
        AbstractAction saveAsActionBar = new AbstractAction("Save As", new ImageIcon("saveAs.gif")) {
            public void actionPerformed(ActionEvent event) {
                fileXML.saveAsFile();
            }
        };
        AbstractAction exitAction = new AbstractAction("Close", new ImageIcon("exit.gif")) {
            public void actionPerformed(ActionEvent event) {
                closeFile();
            }
        };
        bar.add(newAction);
        bar.add(openAction);
        bar.add(saveActionBar);
        bar.add(saveAsActionBar);
        bar.add(exitAction);
        mainFrame.add(bar, "North");
    }

    public static void helpMenu(JMenu helpMenu) {
        helpMenu.setMnemonic('H');
        JMenuItem aboutAction = helpMenu.add("About");
        aboutAction.setMnemonic(112);
    }
}