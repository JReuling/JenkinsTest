package nl.sogyo.mancala;

import java.util.ArrayList;

/**
 * Created by jreuling on 14-10-2015.
 */
public class Board {
    //Infrastructure/Repository class
    Pit pit1;
    Player player1;
    Player player2;

    public Pit getPit(int x)
    {
        //Assumes the input goes from 1-14.
        return pit1.getNeighbour(x - 1);
    }

    public void setPit1(Pit pit1) {
        this.pit1 = pit1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
        player2 = player1.getOpponent();
    }


    public Player getPlayer1() {
        return player1;
    }

    public Pit getPit1() {
        return pit1;
    }
}
