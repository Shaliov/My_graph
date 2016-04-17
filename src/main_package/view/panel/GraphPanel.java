package main_package.view.panel;

import main_package.controller.GraphService;
import main_package.controller.NodeService;
import main_package.model.Arc;
import main_package.model.Graph;
import main_package.model.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 4/3/2016.
 */
public class GraphPanel extends JPanel {

    private Graph graph = Graph.getInstance();
    private NodePanel nodePanel;
    private GraphService graphService;
    private List<NodePanel> nodePanelList = new ArrayList<>();
    private List<ArcPanel> arcPanelList = new ArrayList<>();
    private NodeService nodeService;
    private Line2D tempArc;
    private  ArcPanel arcPanel;

    public ArcPanel getArcPanel() {
        return arcPanel;
    }

    public GraphPanel() {
        setBackground(Color.WHITE);
        JPopupMenu popup = new JPopupMenu();
        editMenu(popup);
    }

    public void editMenu(JPopupMenu popup) {
        JMenuItem cutAction = popup.add("Cut");
        cutAction.setIcon(new ImageIcon("cut.gif"));
        cutAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
        cutAction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        JMenuItem copyAction = popup.add("Copy");
        copyAction.setIcon(new ImageIcon("copy.gif"));
        copyAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));

        JMenuItem pasteAcction = popup.add("Paste");
        pasteAcction.setIcon(new ImageIcon("paste.gif"));
        pasteAcction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));

        JMenuItem setNodeName = popup.add("NodeName");
        setNodeName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });


        setComponentPopupMenu(popup);
        addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent event) {
                if (event.isPopupTrigger()) {
                    popup.show(event.getComponent(), event.getX(), event.getY());
                }
            }
        });

    }

    public List<NodePanel> getNodePanelList() {
        return nodePanelList;
    }

    public void addNode() {
        nodePanelList.clear();
        for (Node node : graph.getNodeList()) {
            NodePanel nodePanel = new NodePanel(node);
            nodePanelList.add(nodePanel);
        }
        repaint();
    }


    public void addArc() {
        arcPanelList.clear();
        for (Arc arc : graph.getArcList()) {
            arcPanel = new ArcPanel(arc);
            arcPanelList.add(arcPanel);
        }
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (nodePanelList != null) {
            for (NodePanel node : nodePanelList) {
                g2.setStroke(new BasicStroke(6.f));
                g2.draw(node.getCircle());
            }
        }
        if (arcPanelList != null) {
            for (ArcPanel arc : arcPanelList) {
                g2.setStroke(new BasicStroke(4.f));
                g2.draw(arc.getLine());
            }
        }
        if(tempArc != null) {
                g2.setStroke(new BasicStroke(4.f));
                g2.draw(tempArc);
        }

    }


    public Ellipse2D findCircle(Point2D p) {
        for (int i = 0; i < nodePanelList.size(); i++) {
            Ellipse2D e = nodePanelList.get(i).getCircle();
            if (e.contains((p))) {
                return e;
            }
        }
        return null;
    }

    public Arc findArc(Point2D p) {
        for (int i = 0; i < arcPanelList.size(); i++) {
            if (arcPanelList.get(i).getLine().getBounds2D().contains(p)) {
                return arcPanelList.get(i).getArc();
            }
        }
        return null;
    }


    public Node findNode(Point2D p) {
        for (int i = 0; i < nodePanelList.size(); i++) {
            if (nodePanelList.get(i).getCircle().contains((p))) {
                return nodePanelList.get(i).getNode();
            }
        }
        return null;
    }

    public void tempArc(Node node, Point2D p) {
        tempArc = new Line2D.Double(node.getNodeX(), node.getNodeY(), p.getX(), p.getY());
        repaint();
    }
    public void deleteTemArc(){
        tempArc = null;
        repaint();
    }
    public void setGraphService(GraphService graphService) {
        this.graphService = graphService;
    }

    public Line2D getTempArc() {
        return tempArc;
    }

    public List<ArcPanel> getArcPanelList() {
        return arcPanelList;
    }
}
