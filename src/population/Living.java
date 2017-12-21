package population;

public interface Living {

    /**
     * Remove this entity from instances
     */
    public void die();

    /**
     * Check if this entity is alive
     *
     * @return true if alive
     */
    public boolean isAlive();

}
