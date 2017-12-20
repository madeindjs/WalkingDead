package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import population.Human;
import population.Population;
import population.Zombie;

public class Land extends JPanel {

    final int PAD = 20;

    final Color humanColor = Color.BLUE;
    final Color zombieColor = Color.RED;

    private Population population;

    public Land(Population _population) {
        population = _population;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // draw humans
        g2.setPaint(humanColor);
        for (Human human : population.getHumans()) {
            g2.fillOval(human.x - 2, human.y - 2, 4, 4);
        }
        // draw zombies
        g2.setPaint(zombieColor);
        for (Zombie zombie : population.getZombie()) {
            g2.fillOval(zombie.x - 2, zombie.y - 2, 4, 4);
        }
    }
}
