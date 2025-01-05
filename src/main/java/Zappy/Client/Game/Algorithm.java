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
        //If there are no items to pick up, we'll move forward
        //If we see a player, we'll add a high chance that a rotate in the direction not facing the player happens. If the rotate doesn't happen, we'll continue as normal. If the rotate does happen, we start from the start.
        //If everything was picked up, we'll check our inventory to validate whether elevation is possible
        //If yes, elevate. If no, see again.
        //If health drops below half, we'll lay an egg

        //Once command is decided, we'll return the String with the command's name
        return null;
    }
}
