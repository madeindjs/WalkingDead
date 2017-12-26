package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import population.Human;
import population.Population;
import population.Zombie;

public class Land extends JPanel implements MouseListener {

    final int PAD = 20;

    final Color humanColor = Color.BLUE;
    final Color zombieColor = Color.RED;

    private Population population;

    public Land(Population _population) {
        population = _population;
        addMouseListener(this);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // draw humans
        g2.setPaint(humanColor);
        for (Human human : Human.getInstances()) {
            g2.fillOval(human.x - 2, human.y - 2, 4, 4);
        }
        // draw zombies
        g2.setPaint(zombieColor);
        for (Zombie zombie : Zombie.getInstances()) {
            g2.fillOval(zombie.x - 2, zombie.y - 2, 4, 4);
        }

        g2.setPaint(Color.black);
        g2.drawString(String.format("Year: %s", population.getYear()), 0, 10);
        g2.drawString(String.format("Number of humans: %s", Human.count()), 0, 20);
        g2.drawString(String.format("Number of zombies: %s", Zombie.count()), 0, 30);
    }

    /**
     * Insert a zombie on cursor
     *
     * @param me
     */
    @Override
    public void mouseClicked(MouseEvent me) {
        System.out.println("view.Land.mouseReleased()");
        int x = me.getX();
        int y = me.getY();

        new Zombie(x, y);
    }

    @Override
    public void mousePressed(MouseEvent me) {
        // do nothing
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        // do nothing
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        // do nothing

    }

    @Override
    public void mouseExited(MouseEvent me) {
        // do nothing

    }
}
