package Zappy.Client.Game;

import Zappy.Client.Player.Player;

public class Algorithm
{
    Algorithm()
    {

    }
    public String run(Player player)
    {
        //Before the algorithm decides anything, there will be a random chance that a completely random command gets executed, apart from incantation
        //The Algorithm will decide which command to run with the information it has.
        //If we know no information, we will check what we can see.
        //If there are items to pick up, we'll attempt to pick them all up
        //If there are no items to pick up, we'll move forward or rotate left or right. The odds of rotating should be more or less 25% and each should have about a 50% of occurring. After a rotation, the algorithm is reset, and we start from the start
        //If we see a player, we'll add a high chance that a rotation in the direction not facing the player happens. If the rotation doesn't happen, we'll continue as normal. If the rotate does happen, we start from the start.
        //If everything was picked up, we'll check our inventory to validate whether elevation is possible
        //If yes, elevate. If no, see again.
        //If health drops below half, we'll lay an egg

        //Once command is decided, we'll return the String with the command's name
        System.out.println(player.getCurrentLevel());
        return null;
    }
}
