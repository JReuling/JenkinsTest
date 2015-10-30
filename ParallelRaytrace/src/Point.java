/**
 * Created by jreuling on 28-9-2015.
 */
public class Point {
    protected long x,y,z;

    public Point()
    {
        this(0,0,0);
    }

    public Point(long x, long y, long z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public long getX() {
        return x;
    }

    public long getY()
    {
        return y;
    }

    public long getZ() {
        return z;
    }

    public boolean equals(Point otherPoint)
    {
        if (this.x == otherPoint.getX() && this.y == otherPoint.getY() && this.z == otherPoint.getZ())
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}