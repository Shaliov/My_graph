package main_package.view.panel;

import main_package.model.Arc;
import main_package.model.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/**
 * Created by Andrey on 4/4/2016.
 */
public class ArcPanel extends JComponent {
    private Arc arc;
    private Line2D line;

    public ArcPanel(Arc arc) {
        this.arc = arc;
        this.line = new Line2D.Double(arc.getArcStartNode().getNodeX(), arc.getArcStartNode().getNodeY(), arc.getArcEndNode().getNodeX(), arc.getArcEndNode().getNodeY());
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4.f));
        g2.draw(this.line);
    }

    public Arc getArc(){
        return arc;
    }
    public Line2D getLine() {
        return line;
    }
}
