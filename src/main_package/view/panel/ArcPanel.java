package main_package.view.panel;

import main_package.model.Arc;
import main_package.model.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.List;

/**
 * Created by Andrey on 4/4/2016.
 */
public class ArcPanel extends JComponent {
    private Arc arc;
    private Line2D line;
    private GraphPanel graphPanel;
    private List<ArcPanel> arcPanelList;
    private Ellipse2D loop;

    public void setGraphPanel(GraphPanel graphPanel) {
        this.graphPanel = graphPanel;
    }

    public ArcPanel(Arc arc) {
        this.arc = arc;

        Node arcStartNode = arc.getArcStartNode();
        Node arcEndNode = arc.getArcEndNode();
        if (arcStartNode.equals(arcEndNode)) {
            this.loop = new Ellipse2D.Double(arcStartNode.getNodeX() - 35 / 2, arcStartNode.getNodeY() - 25, 20, 20);
        } else {
            this.line = new Line2D.Double(arcStartNode.getNodeX(), arcStartNode.getNodeY(), arcEndNode.getNodeX(), arcEndNode.getNodeY());
        }
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (loop != null) {
            g2.draw(this.loop);
        }
        g2.setStroke(new BasicStroke(4.f));
        g2.draw(this.line);
    }

    public void setLine(Line2D line) {
        this.line = line;
    }

    public void repaintLine() {
        arcPanelList = graphPanel.getArcPanelList();
        for (ArcPanel arcPanel : arcPanelList) {
            if (arcPanel.getLine() != null) {
                Arc arc = arcPanel.getArc();
                Node arcStartNode = arc.getArcStartNode();
                Node arcEndNode = arc.getArcEndNode();
                arcPanel.setLine(new Line2D.Double(arcStartNode.getNodeX(), arcStartNode.getNodeY(), arcEndNode.getNodeX(), arcEndNode.getNodeY()));
            }
            if (arcPanel.getLoop() != null) {
                Arc arc = arcPanel.getArc();
                Node arcStartNode = arc.getArcStartNode();
                arcPanel.setLoop(new Ellipse2D.Double(arcStartNode.getNodeX() - 35 / 2, arcStartNode.getNodeY() - 25, 25, 25));
            }
        }
    }

    public void setLoop(Ellipse2D loop) {
        this.loop = loop;
    }

    public Arc getArc() {
        return arc;
    }

    public Line2D getLine() {
        return line;
    }

    public Ellipse2D getLoop() {
        return loop;
    }
}
