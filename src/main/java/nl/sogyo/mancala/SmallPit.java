package nl.sogyo.mancala;

import java.util.ArrayList;

/**
 * Created by jreuling on 7-10-2015.
 */
public class SmallPit extends Pit {

    //First pit constructor called, creates the next one and sets the neighbour of the final element to be this.
    public SmallPit(int[] initialState, ArrayList<Pit> board, Player player1)
    {
        board.set(0,this);
        this.owner = player1;
        this.stones = initialState[0];
        this.neighbour = new SmallPit(initialState,board,true,player1);
        board.get(13).setNeighbour(this);
    }

    //Subsequent pits. The boolean notFirst does not do anything other than make the constructor overloaded.
    //The pits at index 6 and 13 should be Kalahas, so for those indices the Kalaha constructor is called.
    //Otherwise it is a SmallPit.
    public SmallPit(int[] initialState, ArrayList<Pit> board, boolean notFirst,Player player1)
    {
        int index = board.indexOf(null);
        board.set(index,this);
        this.stones = initialState[index];
        if (index+1 == 6 || index + 1 == 13) {
            this.neighbour = new Kalaha(initialState, board,player1);
        }
        else
        {
            this.neighbour = new SmallPit(initialState, board, true ,player1);
        }
        if (index <6)
        {
            this.owner = player1;
        }
        else
        {
            this.owner = player1.getOpponent();
        }
    }

    //tempStones is used rather than inputting this.stones directly because setting stones to 0 after passing would
    // produce incorrect results if stones >= 14.
    public void play()
    {
        int tempStones = this.stones;
        this.stones=0;
        neighbour.pass(tempStones);
    }

    //Takes a stone from the passed stack. If it was the last stone, the pit was empty and the owner is playing the opposing pit is captured.
    //Otherwise it calls upon its owner to pass the turn.
    public void pass(int stones)
    {
        this.stones++;
        stones--;
        if (stones > 0)
        {
            neighbour.pass(stones);
        }
        else
        {
            lastStone();
        }
    }

    private void lastStone()
    {
        if (owner.isOnPlay()) {
            if (this.stones==1)
            {
                ((SmallPit)getOpposite()).capture();
            }
        }
        owner.passTurn();
    }

    //Finds the Kalaha belonging to the same player as the opposing and puts all its stones in there.
    public void capture()
    {
        ((Kalaha)((getOpposite().getNeighbour()).getKalaha())).score(this.stones);
        this.stones = 0;
    }

    public Pit getKalaha() {
        return neighbour.getKalaha();
    }

    public Pit getOpposite()
    {
        Pit tempPit = neighbour.getOpposite();
        tempPit = tempPit.getNeighbour();
        return tempPit;
    }

    public void Empty()
    {
        ((Kalaha)getKalaha()).score(stones);
    }
}
