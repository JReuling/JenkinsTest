package nl.sogyo.mancala;

import junit.framework.Assert;
import junit.framework.TestCase;

public class SmallPitTest extends TestCase {


    public void testPassToNeighbour()
    {
        Game game = new Game();
        Board board = game.getBoard();
        int originalStones = board.getPit(2).getStones();
        board.getPit(1).pass(2);
        int result = board.getPit(1).getStones();
        Assert.assertEquals(originalStones+1,result);
    }

    public void testLastStone_InPlayingPlayerPit_PitNonEmpty()
    {
        Game game = new Game();
        Board board = game.getBoard();
        board.getPit(1).pass(1);
        boolean result = board.getPlayer1().getOpponent().isOnPlay();
        Assert.assertEquals(true, result);
    }

    public void testLastStone_InPlayingPlayerPit_PitEmpty()
    {
        Game game = new Game();
        Board board = game.getBoard();

        board.getPit(2).setStones(0);
        board.getPit(1).pass(2);
        Assert.assertSame(board.getPit(2).getOpposite(),board.getPit(12));
        Assert.assertEquals(0,board.getPit(12).getStones());
    }

    public void testGetKalaha()
    {
        Game game = new Game();
        Board board = game.getBoard();
        Pit tempKalaha = board.getPit(1).getKalaha();
        Assert.assertNotNull(tempKalaha);
    }

    public void testGetOpposite()
    {
        Game game = new Game();
        Board board = game.getBoard();

        Pit tempPit = board.getPit(1).getOpposite();
        Assert.assertSame(board.getPit(13),tempPit);
    }

    public void testCaptureByPlayer1()
    {
        Game game = new Game();
        Board board = game.getBoard();

        int expected = board.getPit(7).getStones() + board.getPit(2).getOpposite().getStones();
        board.getPit(2).setStones(0);
        board.getPit(1).pass(2);
        int result = board.getPit(7).getStones();
        Assert.assertEquals(expected,result);
        Assert.assertEquals(4,result);
    }

    public void testPlay()
    {
        Game game = new Game();
        Board board = game.getBoard();
        ((SmallPit)board.getPit(1)).play();
        int result = board.getPit(1).getStones();
        Assert.assertEquals(0,result);
    }

    public void testPlayV2()
    {
        Game game = new Game();
        Board board = game.getBoard();
        ((SmallPit)board.getPit(1)).play();
        int result = board.getPit(2).getStones();
        Assert.assertEquals(5,result);
    }

    public void testCaptureByPlayer2()
    {
        Game game = new Game();
        Board board = game.getBoard();
        board.getPlayer1().passTurn();
        board.getPit(13).setStones(0);
        ((SmallPit)board.getPit(9)).play();
        int result = board.getPit(14).getStones();
        Assert.assertEquals(4,result);
    }

    public void testEmptyPitPlayer2PlayByPlayer1()
    {
        Game game = new Game();
        Board board = game.getBoard();
        board.getPit(10).setStones(0);
        ((SmallPit)board.getPit(6)).play();
        int result = board.getPit(4).getStones();
        int expected = 4;
        Assert.assertEquals(expected,result);
    }

}