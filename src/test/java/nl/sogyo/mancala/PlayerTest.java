package nl.sogyo.mancala;

import junit.framework.Assert;
import junit.framework.TestCase;

public class PlayerTest extends TestCase {

    public void testIsOnPlayTrue()
    {
        Game game = new Game();
        Board board = game.getBoard();
        Player player = board.getPlayer1();
        boolean result = player.isOnPlay();
        Assert.assertEquals(true,result);
    }


    public void testOpponents()
    {
        Player player = new Player();
        Assert.assertNotNull(player.getOpponent());
    }


}