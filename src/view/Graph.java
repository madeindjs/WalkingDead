package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Vector;
import javax.swing.JPanel;

public class Graph extends JPanel {

    private Vector<Integer> humansCount;
    private Vector<Integer> zombiesCount;
    final int PAD = 20;

    public Graph() {
        zombiesCount = new Vector();
        humansCount = new Vector();

    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();
        g2.drawLine(PAD, PAD, PAD, h - PAD);
        g2.drawLine(PAD, h - PAD, w - PAD, h - PAD);
        double xScale = (w - 2 * PAD) / (humansCount.size() + 1);
        double maxValue = 100.0;
        double yScale = (h - 2 * PAD) / maxValue;
        // The origin location.
        int x0 = PAD;
        int y0 = h - PAD;
        g2.setPaint(Color.blue);

        for (int j = 0; j < humansCount.size(); j++) {
            int x = x0 + (int) (xScale * (j + 1));
            int y = y0 - (int) (yScale * humansCount.get(j));
            g2.fillOval(x - 2, y - 2, 4, 4);
        }

        g2.setPaint(Color.red);

        for (int j = 0; j < zombiesCount.size(); j++) {
            int x = x0 + (int) (xScale * (j + 1));
            int y = y0 - (int) (yScale * zombiesCount.get(j));
            g2.fillOval(x - 2, y - 2, 4, 4);
        }
    }

    public void addCount(int humans, int zombies) {
        humansCount.add(humans);
        zombiesCount.add(zombies);
        this.repaint();
    }

}
