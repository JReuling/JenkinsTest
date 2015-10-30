package nl.sogyo.mancala;

/**
 * Created by jreuling on 7-10-2015.
 */
public abstract class Pit {
    protected int stones;
    protected Pit neighbour;
    protected Player owner;

    public int getStones() {
        return stones;
    }

    //Passes the stones. Here a small pit and a Kalaha behave differently so the method is made abstract.
    abstract void pass(int stones);

    //The opposite of a smallPit is found by repeatedly calling getOpposite onto the neighbour until it finds a Kalaha,
    //which returns itself, and then calling getNeighbour on the Kalaha an equal amount of times as it took getOpposites to get to the Kalaha.
    public abstract Pit getOpposite();

    //getKalaha finds the Kalaha a SmallPit belongs to by repeatedly calling getKalaha() on its neighbour until it reaches a Kalaha, which returns itself.
    public abstract Pit getKalaha();

    public void setNeighbour(Pit neighbour) {
        this.neighbour = neighbour;
    }

    public Pit getNeighbour() {
        return neighbour;
    }

    public void setStones(int stones) {
        this.stones = stones;
    }

    public Player getOwner() {
        return owner;
    }

    public Pit getNeighbour(int x)
    {
        if (x==0)
        {
            return this;
        }
        else
        {
            return this.neighbour.getNeighbour(x-1);
        }
    }
}
