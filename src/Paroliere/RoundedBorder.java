package Paroliere;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.border.AbstractBorder;

public class RoundedBorder extends AbstractBorder {

    private Color color;
    private int thickness = 2;
    private int radius = 10;
    private Insets insets = null;
    private boolean drawLine = true;

    public RoundedBorder(Color color, int radius) {
        this.color = color;
        this.radius = radius;
    }

    public RoundedBorder(Color color, int radius, boolean drawLine) {
        this.color = color;
        this.radius = radius;
        this.drawLine = drawLine;
    }

    @Override
    public Insets getBorderInsets(Component c) {
        if (insets == null) {
            insets = new Insets(thickness, thickness, thickness, thickness);
        }
        return insets;
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = insets.right = insets.bottom = insets.top = thickness;
        return insets;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.setColor(color);
        Shape border = new RoundRectangle2D.Double(thickness, thickness,
                width - thickness * 2, height - thickness * 2, radius, radius);
        Graphics g2 = g.create();
        ((java.awt.Graphics2D) g2).setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING,
                java.awt.RenderingHints.VALUE_ANTIALIAS_ON);
        ((Graphics2D) g2).setStroke(new java.awt.BasicStroke(thickness));
        ((Graphics2D) g2).draw(border);
        g2.dispose();
        if (drawLine) {
            g.setColor(color);
            g.drawLine(thickness, height - thickness, width - thickness, height - thickness);
        }
    }

}
