package main_package.view.panel;

import main_package.controller.GraphService;
import main_package.controller.NodeService;
import main_package.model.Arc;
import main_package.model.Graph;
import main_package.model.Node;
import main_package.view.frame.MainFrame;
import main_package.view.panel.handler.nodeHandler.MouseHandler;

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
    private ArcPanel arcPanel;
    private Point2D pointNode;

    public GraphPanel() {
        setBackground(Color.WHITE);
        JPopupMenu popup = new JPopupMenu();
        editMenu(popup);
    }

    public GraphPanel(Graph graph)
    {
        setBackground(Color.WHITE);
        JPopupMenu popup = new JPopupMenu();
        if(graph.getNodeList() != null){
            addNode();
            repaint();
        }
        if(graph.getArcList() != null) {
            addArc();
            repaint();
        }

        editMenu(popup);

    }
    public void editMenu(JPopupMenu popup) {
        JMenuItem setNodeName = popup.add("NodeName");
        final JComponent graphPanel = this;
        setNodeName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Point2D p = getPointNode();
                Node node = findNode(p);
                if(node != null) {
                    node.setNodeName(JOptionPane.showInputDialog("Введите имя узла: ").trim());
                    repaint();
                }
                else {
                    JOptionPane.showMessageDialog(graphPanel, "вы не выбрали узел");
                }
            }
        });
//        JMenuItem setArcName = popup.add("ArcName");
//        setArcName.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                Point2D p = getPointNode();
//                Arc arc = findArc(p);
//                arc.setName(JOptionPane.showInputDialog("Введите имя дуги: "));
//            }
//        });
        JMenuItem setweight = popup.add("Weight");
        setweight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Point2D p = getPointNode();
                Arc arc = findArc(p);
                if(arc != null) {
                    arc.setWeight(Integer.valueOf(JOptionPane.showInputDialog("Введите вес дуги: ").trim()));
                    repaint();
                }
                else {
                    JOptionPane.showMessageDialog(graphPanel,"вы не выбрали дугу!");
                }
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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
        if (nodePanelList != null) {
            for (NodePanel nodePanel : nodePanelList) {
                Node node = nodePanel.getNode();
                if(node.getNodeName() != null) {
                    g2.setFont(new Font("TimenewsNewRoman", 0, 20));
                    g2.drawString(node.getNodeName(), (int)node.getNodeX() - 20, (int)node.getNodeY() - 20);
                }
                g2.fill(nodePanel.getCircle());
                g2.setStroke(new BasicStroke(6.f));
                g2.draw(nodePanel.getCircle());

            }
        }
        if (arcPanelList != null) {
            for (ArcPanel arcPanel : arcPanelList) {
                Arc arc = arcPanel.getArc();
                Node arcEndNode = arc.getArcEndNode();
                Node arcStartNode = arc.getArcStartNode();
//                if(arc.getAcrName() != null) {
//                    double delX = arcStartNode.getNodeX() - arcEndNode.getNodeX();
//                    double delY = arcStartNode.getNodeY() - arcEndNode.getNodeY();
//                    g2.setFont(new Font("TimenewsNewRoman", 0, 20));
//                    g2.drawString(arc.getAcrName(), (float)delX, (float)delY);
//                }
                if(arcPanel.getLoop() != null) {
//                    if(arc.getAcrName() != null) {
//                        g2.setFont(new Font("TimenewsNewRoman", 0, 20));
//                        g2.drawString(arc.getAcrName(), (int) (arcStartNode.getNodeX() - 30 / 2), (int) (arcStartNode.getNodeY() - 27));
//                    }
                    if(String.valueOf(arc.getWeight()) != null && arc.getWeight() != 0){
                        g2.setFont(new Font("TimenewsNewRoman", 0, 20));
                        g2.drawString(String.valueOf(arc.getWeight()), (int) (arcStartNode.getNodeX() - 30 / 2), (int) (arcStartNode.getNodeY() + 15));
                    }
                    g2.setStroke(new BasicStroke(4.f));
                    g2.draw(arcPanel.getLoop());
                }
                if(arcPanel.getLine() != null) {
//                    if(arc.getAcrName() != null) {
//                        g2.setFont(new Font("TimenewsNewRoman", 0, 20));
//                        g2.drawString(arc.getAcrName(), (int)((arcStartNode.getNodeX() + arcEndNode.getNodeX())/2),(int)((arcStartNode.getNodeY() + arcEndNode.getNodeY())/2) );
//                    }
                    if(String.valueOf(arc.getWeight()) != null && arc.getWeight() != 0){
                        g2.setFont(new Font("TimenewsNewRoman", 0, 20));
                        g2.drawString(String.valueOf(arc.getWeight()), (int) ((arcStartNode.getNodeX() + arcEndNode.getNodeX())/2),(int)(((arcStartNode.getNodeY() + arcEndNode.getNodeY())/2) + 20));
                    }
                    g2.setStroke(new BasicStroke(4.f));
                    g2.draw(arcPanel.getLine());
                }
            }
        }
        if(tempArc != null) {
            g2.setColor(Color.black);
            g2.setStroke(new BasicStroke(4.f));
            g2.draw(tempArc);
        }
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
    public Ellipse2D findCircle(Point2D p) {
        for(NodePanel nodePanel : nodePanelList) {
            Ellipse2D e = nodePanel.getCircle();
            if (e.contains((p))) {
                return e;
            }
        }
        return null;
    }
    public Arc findArc(Point2D p) {
        for(ArcPanel arcPanel : arcPanelList ) {
            if (arcPanel.getLine() != null ) {
                if (arcPanel.getLine().getBounds2D().contains(p)) {
                    return arcPanel.getArc();
                }
            }
            else if(arcPanel.getLoop() != null){
                if(arcPanel.getLoop().getBounds2D().contains(p)) {
                    return arcPanel.getArc();
                }
            }
        }
        return null;
    }
    public Node findNode(Point2D p) {
        for(NodePanel nodePanel: nodePanelList){
            if (nodePanel.getCircle().contains((p))) {
                return nodePanel.getNode();
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

    public void eraseListeners () {
        for (MouseListener mList : getMouseListeners()) {
            removeMouseListener(mList);
        }
        for (MouseMotionListener mMList : getMouseMotionListeners()) {
            removeMouseMotionListener(mMList);
        }
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
    public List<NodePanel> getNodePanelList() {
        return nodePanelList;
    }
    public ArcPanel getArcPanel() {
        return arcPanel;
    }
    public Point2D getPointNode() {
        return pointNode;
    }
    public void setPointNode(Point2D p) {
        this.pointNode = p;
    }
}



    