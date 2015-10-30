import java.util.ArrayList;

/**
 * Created by jreuling on 29-9-2015.
 */
//We assume the viewport is a rectangle (or at least spannable by two base vectors).
public class ViewPort {
    Point[] corners = new Point[4];
    Ray[] baseVectors = new Ray[2];
    long dimX, dimY;

    //Here we assume that the corner points are defined as: bottom-left(p1), top-left(p2), top-right(p3) and bottom-right(p4)
    //Note that this definition may not have to be true, but if it isn't the coordinates within the viewport will be different, depending on the
    //relative position of p1 with respect to the other corners.
    public ViewPort(Point p1, Point p2, Point p3, Point p4)
    {
        corners[0] = p1;
        corners[1] = p2;
        corners[2] = p3;
        corners[3] = p4;
        createBaseVectors();
    }

    //Point getting is calculated from corners[0].
    public Point getPoint(int x, int y)
    {
        if (x>dimX || y>dimY)
        {
            System.out.println("The requested viewport coordinate is out of bounds");
            System.exit(-1);
        }
        long tempX = Math.round(corners[0].getX()+x*baseVectors[0].getSlope_x()+y*baseVectors[1].getSlope_x());
        long tempY = Math.round(corners[0].getY()+x*baseVectors[0].getSlope_y()+y*baseVectors[1].getSlope_y());
        long tempZ = Math.round(corners[0].getZ()+x*baseVectors[0].getSlope_z()+y*baseVectors[1].getSlope_z());
        return new Point(tempX,tempY,tempZ);
    }

    //Creates the basevectors (given by Rays, except we just ignore the non-vector properties). To ensure we have an orthogonal set
    //we take the inner (dot) product between both vectors. This should be 0, if it is not move on to the next corner.
    private void createBaseVectors()
    {
        Ray ray1 = new Ray(corners[0],corners[1]);
        Ray ray2 = new Ray(corners[0],corners[2]);
        Ray ray3 = new Ray(corners[0],corners[3]);
        if (Utility.dotProduct(ray1,ray2)==0)
        {
            baseVectors[0] = ray1;
            baseVectors[1] = ray2;
            dimX = Math.round(Math.sqrt(Math.pow((corners[2].getX() - corners[0].getX()),2) + Math.pow((corners[2].getY() - corners[0].getY()),2) + Math.pow((corners[2].getZ() - corners[0].getZ()),2)));
            dimY = Math.round(Math.sqrt(Math.pow((corners[1].getX() - corners[0].getX()),2) + Math.pow((corners[1].getY() - corners[0].getY()),2) + Math.pow((corners[1].getZ() - corners[0].getZ()),2)));
        }
        else
        {
            baseVectors[0] = ray1;
            baseVectors[1] = ray3;
            dimX = Math.round(Math.sqrt(Math.pow((corners[1].getX() - corners[0].getX()),2) + Math.pow((corners[1].getY() - corners[0].getY()),2) + Math.pow((corners[1].getZ() - corners[0].getZ()),2)));
            dimY = Math.round(Math.sqrt(Math.pow((corners[3].getX() - corners[0].getX()),2) + Math.pow((corners[3].getY() - corners[0].getY()),2) + Math.pow((corners[3].getZ() - corners[0].getZ()),2)));
        }
    }

    public long getDimY() {
        return dimY;
    }

    public long getDimX() {
        return dimX;
    }
}
