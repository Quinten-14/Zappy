package Zappy.Client.Player;

import java.util.concurrent.ConcurrentHashMap;

public class Player
{
    private int health = 1260;
    private int delay = 0;
    private int currentLevel = 1;
    private int[] location;
    ConcurrentHashMap<String, String> tiles;
    private Items items = new Items();
    private boolean incantationPossible = false;
    private boolean enoughPlayerLock = true;
    private char direction = 'N';

    public Player()
    {

    }


    private boolean checkLevelUp() {
        return switch (currentLevel) {
            case 1 -> items.getLinemate() >= 1;
            case 2 -> items.getLinemate() >= 1 && items.getDeraumere() >= 1 && items.getSibur() >= 1;
            case 3 -> items.getLinemate() >= 2 && items.getSibur() >= 1 && items.getPhiras() >= 2;
            case 4 -> items.getLinemate() >= 1 && items.getDeraumere() >= 1 && items.getSibur() >= 2 && items.getPhiras() >= 1;
            case 5 -> items.getLinemate() >= 1 && items.getDeraumere() >= 2 && items.getSibur() >= 1 && items.getMendiane() >= 3;
            case 6 -> items.getLinemate() >= 1 && items.getDeraumere() >= 2 && items.getSibur() >= 3 && items.getPhiras() >= 1;
            case 7 -> items.getLinemate() >= 2 && items.getDeraumere() >= 2 && items.getSibur() >= 2 && items.getMendiane() >= 2 && items.getPhiras() >= 2 && items.getThystame() >= 1;
            default -> false;
        };
    }

    public void setTiles(ConcurrentHashMap<String, String> tiles)
    {
        this.tiles = tiles;
    }

    public void setLocation(int[] location)
    {
        this.location = location;
    }

    public void setCurrentLevel(int newLevel)
    {
        if (newLevel > currentLevel)
            currentLevel = newLevel;
    }

    public void setDelay(int delay)
    {
        this.delay = delay;
    }

    public void setHealth(int change)
    {
        health += change;
    }

    public void setItems(ConcurrentHashMap<String, Integer> inventory)
    {
        if (inventory != null)
        {
            if (inventory.get("deraumere") != 0)
                items.setDeraumere(inventory.get("deraumere"));
            if (inventory.get("linemate") != 0)
                items.setLinemate(inventory.get("linemate"));
            if (inventory.get("mendiane") != 0)
                items.setMendiane(inventory.get("mendiane"));
            if (inventory.get("thystame") != 0)
                items.setThystame(inventory.get("thystame"));
            if (inventory.get("phiras") != 0)
                items.setPhiras(inventory.get("phiras"));
            if (inventory.get("sibur") != 0)
                items.setSibur(inventory.get("sibur"));
        }
        incantationPossible = checkLevelUp() && enoughPlayerLock;
    }


    public ConcurrentHashMap<String, String> getTiles()
    {
        return tiles;
    }

    public int[] getLocation()
    {
        return location;
    }

    public int getCurrentLevel()
    {
        return currentLevel;
    }

    public int getDelay()
    {
        return delay;
    }

    public int getHealth()
    {
        return health;
    }


    public boolean isIncantationPossible() {
        return incantationPossible;
    }

    public void setIncantationPossible(boolean incantationPossible) {
        this.incantationPossible = incantationPossible;
    }

    public boolean isEnoughPlayerLock() {
        return enoughPlayerLock;
    }

    public void setEnoughPlayerLock(boolean enoughPlayerLock) {
        this.enoughPlayerLock = enoughPlayerLock;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }
}

