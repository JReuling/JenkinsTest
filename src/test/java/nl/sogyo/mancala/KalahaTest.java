package nl.sogyo.mancala;

import junit.framework.Assert;
import junit.framework.TestCase;

public class KalahaTest extends TestCase {

    public void testKalahaCreate()
    {
        Game game = new Game();
        Board board = game.getBoard();
        Assert.assertEquals(0, board.getPit1().getKalaha().getStones());
    }

    public void testPassOnTurn()
    {
        Game game = new Game();
        Board board = game.getBoard();
        board.getPit(6).pass(3);
        int result = board.getPit(7).getStones(); //Which is the kalaha of the starting player.
        Assert.assertEquals(1,result);
    }

    public void testPassNotOnTurn()
    {
        Game game = new Game();
        Board board = game.getBoard();
        board.getPit(13).pass(3);
        int result = board.getPit(14).getStones(); //Which is the kalaha of the not-starting player.
        Assert.assertEquals(0,result);
    }

    public void testPassNotOnTurnLastPit()
    {
        Game game = new Game();
        Board board = game.getBoard();
        board.getPit(13).pass(3);
        int result = board.getPit(2).getStones();
        Assert.assertEquals(5,result);
    }

}