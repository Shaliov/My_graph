package main_package.view.panel;

import main_package.controller.GraphService;
import main_package.model.Graph;
import main_package.model.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Andrey on 4/3/2016.
 */
public class GraphPanel extends JPanel {

    private Graph graph = Graph.getInstance();
    private GraphService graphService = new GraphService(this);
    private List<NodePanel> nodePanelList = new ArrayList<>();
    private List<ArcPanel> arcPanelList = new ArrayList<>();

    public GraphPanel() {
        setBackground(Color.WHITE);
        JPopupMenu popup = new JPopupMenu();
        editMenu(popup);
    }

    public void editMenu(JPopupMenu popup) {
        JMenuItem cutAction = popup.add("Cut");
        cutAction.setIcon(new ImageIcon("cut.gif"));
        cutAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));

        JMenuItem copyAction = popup.add("Copy");
        copyAction.setIcon(new ImageIcon("copy.gif"));
        copyAction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));

        JMenuItem pasteAcction = popup.add("Paste");
        pasteAcction.setIcon(new ImageIcon("paste.gif"));
        pasteAcction.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));

        JMenuItem deleteAcction = popup.add("Delete");
        deleteAcction.setIcon(new ImageIcon("delete.gif"));
        deleteAcction.addActionListener(new ActionListener() {
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

    public List<NodePanel> getNodePanelList(){
        return nodePanelList;
    }
    public void addNNode() {
        nodePanelList.clear();
        for (Node node : graph.getNodeList()) {
            NodePanel nodePanel = new NodePanel(node);
            nodePanelList.add(nodePanel);
        }
        repaint();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for(int i = 0; i < nodePanelList.size(); i++){
            g2.setStroke(new BasicStroke(6.f));
            g2.draw(nodePanelList.get(i).getCircle());
        }
    }

    public Ellipse2D find(Point2D p){
        for(int i = 0; i < nodePanelList.size(); i++){
            Ellipse2D e = nodePanelList.get(i).getCircle();
            if(e.contains((p))){
                return e;
            }
        }
        return null;
    }

    public void remove(NodePanel e){
        if(e.getNode() == null) {
            return;
        }
        for(int i = 0; i < nodePanelList.size(); i++)
        if(e == nodePanelList.get(i))
        {

        }
    }

public void updateNode(Node node){
    for(int i = 0; i < nodePanelList.size(); i++)
        if(nodePanelList.get(i).getNode() == node)
  //  nodePanelList.get(i).setFrame(node.getNodeX() - 25 / 2, node.getNodeY() - 25 / 2, 25, 25);
    repaint();
}



}
