import java.util.ArrayList;

/**
 * Created by jreuling on 29-9-2015.
 */
//It's a sphere, and also the only shape we currently have. The sphere has a not-yet used colour, a radius, a center and a diffuseCoefficient.
public class Sphere implements Shapes{
    private double radius;
    private Point centerPoint;
    private int[] colour;
    private double diffuseCoefficient;

    //A bunch of constructors, allowing us to only input what we consider necessary at the time.
    public Sphere(double radius)
    {
        this(radius,new Point(0,0,0),new int[]{255,255,255});
    }

    public Sphere(double radius, Point centerPoint)
    {
        this(radius,centerPoint,new int[]{255,255,255});
    }

    public Sphere(double radius, Point centerPoint, int[] colour)
    {
        this(radius,centerPoint,colour,1);
    }

    public Sphere(double radius, Point centerPoint, int[] colour, double diffuseCoefficient)
    {
        this.radius = radius;
        this.centerPoint = centerPoint;
        this.colour = colour;
        this.diffuseCoefficient = diffuseCoefficient;
    }

    public int[] getColour()
    {
        return colour;
    }


    public double getDiffuseCoefficient() {
        return diffuseCoefficient;
    }

    public void setDiffuseCoefficient(double diffuseCoefficient) {
        this.diffuseCoefficient = diffuseCoefficient;
    }

    //Calculates if a given ray intersects with a sphere. Calculation is done by taking the difference between the line from the center of the sphere
    //to the origin of the ray and the ray itself given by the origin + t*(slope_x,slope_y,slope_z). The length of this difference should be equal to the radius for some t>0.
    public ArrayList<Point> intersect(Ray ray)
    {
        ArrayList<Point> intersections = new ArrayList<>();
        int offset = 5; //offset from which to calculate an intersection as an actual intersection (ignores collisions with the same element)
        double b = 2*(ray.getSlope_x()*(ray.getOffset_x()-this.centerPoint.getX()) + ray.getSlope_y()*(ray.getSlope_y()-this.centerPoint.getY()) + ray
                .getSlope_z()*(ray.getOffset_z()-this.centerPoint.getZ()));
        double c = Math.pow(this.centerPoint.getX(),2) + Math.pow(this.centerPoint.getY(),2) + Math.pow(this.centerPoint.getZ(),2) +
                Math.pow(ray.getOffset_x(),2) + Math.pow(ray.getOffset_y(),2) + Math.pow(ray.getOffset_z(),2) -
                2*ray.getOffset_x()*this.centerPoint.getX() - 2*ray.getOffset_y()*this.centerPoint.getY() - 2*ray.getOffset_z()*this.centerPoint.getZ() - radius*radius;
        double D = b*b - 4*c;
        if (D<0)
        {
            return intersections;
        }
        else
        {
            double t1 = (-b + Math.sqrt(D))/2;
            double t2 = (-b - Math.sqrt(D))/2;
            if (t2 > 0) //Since t2 is smaller it is the first intersection. We could add it at index 0 and preserve the order t1, t2 but it seems to be slower that way. Albeit only marginally.
            {
                Point tempPoint =new Point(Math.round(ray.getOffset_x()+(t2)*ray.getSlope_x()),Math.round(ray.getOffset_y() + (t2)*ray.getSlope_y())
                        ,Math.round(ray.getOffset_z() + (t2)*ray.getSlope_z()));
                if (t2>offset) {
                    intersections.add(tempPoint);
                }
            }
            if (t1 > 0)
            {
                Point tempPoint = new Point(Math.round(ray.getOffset_x()+(t1)*ray.getSlope_x()),Math.round(ray.getOffset_y() + (t1)*ray.getSlope_y())
                        ,Math.round(ray.getOffset_z() + (t1)*ray.getSlope_z()));
                if (t1>offset) {
                    intersections.add(tempPoint);
                }
            }
            return intersections;
        }

    }

    //For the sphere the normal is given by a vector from the center towards the given point.
    public Ray normal(Point point) {
        return  new Ray(centerPoint,point);
    }
}
