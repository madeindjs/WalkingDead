/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.JFrame;

/**
 *
 * @author arousseau
 */
public class Frame extends JFrame {

    private Graph graph = new Graph();

    public Frame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(graph);
        setSize(400, 400);
        setLocation(200, 200);
        setVisible(true);
    }

    public void addCount(int humans, int zombies) {
        graph.addCount(humans, zombies);
    }

}
