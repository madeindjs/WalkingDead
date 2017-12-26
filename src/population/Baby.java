/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package population;

import java.util.Vector;

/**
 * Baby is a futur human & will be added in the end of the year. This system
 * allow to avoid the famous `java.util.ConcurrentModificationException`.
 */
public class Baby {

    private static Vector<Baby> instances = new Vector();

    /**
     * One of parent used to copy some informations when baby is bith
     */
    private Human parent;

    public static void births() {
        for (Baby baby : instances) {
            baby.birth();
        }

        instances.removeAllElements();
    }

    public Baby(Human _parent) {
        parent = _parent;
        instances.add(this);
    }

    public Human birth() {
        Human human = new Human();
        human.setCoordinates(parent.getX(), parent.getY());
        return human;
    }
}
