/**
 * Created by jreuling on 28-9-2015.
 */
public class LightSource extends Point implements Coloured {
    private int[] colour;
    private short brightness;

    public LightSource()
    {
        this(new int[]{255,255,255});
    }

    public LightSource(long x1, long y1, long z1)
    {
        this(x1,y1,z1,(short)255);
    }

    public LightSource(int[] colour)
    {
        this((short)255, colour);
    }

    public LightSource(short brightness, int[] colour)
    {
        super();
        this.brightness = brightness;
        this.colour = colour;
    }

    public LightSource(long x1, long y1, long z1, short brightness)
    {
        this(x1,y1,z1,brightness,new int[]{255,255,255});
    }

    public LightSource(long x1, long y1, long z1, short brightness, int[] colour)
    {
        this.x = x1;
        this.y = y1;
        this.z = z1;
        this.brightness = brightness;
        this.colour = colour;
    }

    public int[] getColour()
    {
        return colour;
    }

    public short getBrightness() {
        return brightness;
    }
}
