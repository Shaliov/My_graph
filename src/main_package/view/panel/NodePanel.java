package main_package.view.panel;

import main_package.model.Graph;
import main_package.model.Node;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.font.GraphicAttribute;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;

/**
 * Created by Andrey on 4/3/2016.
 */
public class NodePanel extends JComponent {

    private static final double RADIUS = 25;
    private Node node;
    private Ellipse2D circle;

    public NodePanel(Node node) {
        this.node = node;
        this.circle = new Ellipse2D.Double(node.getNodeX() - RADIUS / 2, node.getNodeY() - RADIUS / 2, RADIUS, RADIUS);
       repaint();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(6.f));
        g2.draw(this.circle);
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public Ellipse2D getCircle() {
        return circle;
    }




}
