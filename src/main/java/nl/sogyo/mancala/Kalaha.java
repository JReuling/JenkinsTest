package nl.sogyo.mancala;

import java.util.ArrayList;

/**
 * Created by jreuling on 7-10-2015.
 */
public class Kalaha extends Pit{


    //Creates a Kalaha, if it does so at board index 6 it belongs to player1 and more small pits need to be made, otherwise
    //
    public Kalaha(int[] initialState, ArrayList<Pit> board, Player player1)
    {
        int index = board.indexOf(null);
        board.set(index,this);
        this.stones = initialState[index];
        if (index == 6)
        {
            this.owner = player1;
            this.neighbour = new SmallPit(initialState, board, true,player1);
        }
        else
        {
            this.owner = player1.getOpponent();
        }
    }

    //Passes the stones after taking one if the owner is on play. Otherwise it just passes the stones. If the last stone
    //is put in the kalaha no passTurn method is called, meaning the owner can play again.
    public void pass(int stones)
    {
        if (owner.isOnPlay()) {
            this.stones++;
            stones--;
            if (stones>0) {
                neighbour.pass(stones);
            }
        }
        else
        {
            neighbour.pass(stones);
        }
    }

    public Pit getKalaha() {
        return this;
    }

    public Pit getOpposite() {
        return this;
    }

    public void score(int stones)
    {
        this.stones = this.stones + stones;
    }
}
