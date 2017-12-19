/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package population;

/**
 *
 * @author arousseau
 */
public interface Fighter {

    /**
     * Represent life quantity
     */
    public int life = 100;

    /**
     * Represent strenght of fighter. When to Fighters fights, strenght is
     * substract to life
     */
    public int strength = 30;

    /**
     * Fight against an human
     *
     * @param human
     */
    public void fight(Human human);

    /**
     * Figth against a zombie
     *
     * @param zombie
     */
    public void fight(Zombie zombie);

    /**
     * Check if Fighter is alive
     *
     * @return
     */
    public boolean isAlive();

}
