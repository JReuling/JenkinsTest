/**
 * Created by jreuling on 28-9-2015.
 */

//Rays are defined as vectors t(slope_x,slope_y,slope_z), where t is a real number, with the origin at (offset_x,offset_y,offset_z)
//All rays are normalized, meaning the length of the vector is 1.

public class Ray {
    private Point pointOfOrigin;
    private double slope_x, slope_y, slope_z;
    private double offset_x, offset_y, offset_z;

    public Ray()
    {
        this(1.0,1.0,1.0);
    }

    public Ray(Point pointOfOrigin, Point targetPoint)
    {
        this.pointOfOrigin = pointOfOrigin;
        double norm = Utility.normalizationFactor((targetPoint.getX() - pointOfOrigin.getX()),(targetPoint.getY() - pointOfOrigin.getY()),(targetPoint.getZ() - pointOfOrigin.getZ()));
        this.slope_x = (targetPoint.getX() - pointOfOrigin.getX())/norm;
        this.slope_y = (targetPoint.getY() - pointOfOrigin.getY())/norm;
        this.slope_z = (targetPoint.getZ() - pointOfOrigin.getZ())/norm;
        this.offset_x = pointOfOrigin.getX();
        this.offset_y = pointOfOrigin.getY();
        this.offset_z = pointOfOrigin.getZ();
    }

    public Ray(double slope_x, double slope_y, double slope_z)
    {
        this(slope_x,slope_y,slope_z,0.0,0.0,0.0);
    }

    public Ray(double slope_x, double slope_y, double slope_z, Point pointOfOrigin)
    {
        double norm = Utility.normalizationFactor(slope_x,slope_y,slope_z);
        this.slope_x = slope_x/norm;
        this.slope_y = slope_y/norm;
        this.slope_z = slope_z/norm;
        this.pointOfOrigin = pointOfOrigin;
        this.offset_x = pointOfOrigin.getX();
        this.offset_y = pointOfOrigin.getY();
        this.offset_z = pointOfOrigin.getZ();
        if (slope_x ==  0 & slope_y == 0 & slope_z == 0)
        {
            System.out.println("Not a ray (but a point).");
            System.exit(-2);
        }
    }

    public Ray(double slope_x, double slope_y,double slope_z, double offset_x, double offset_y, double offset_z)
    {
        if (slope_x ==  0 & slope_y == 0 & slope_z == 0)
        {
            System.out.println("Not a ray (but a point).");
            System.exit(-2);
        }
        double norm = Utility.normalizationFactor(slope_x,slope_y,slope_z);
        this.slope_x = slope_x/norm;
        this.slope_y = slope_y/norm;
        this.slope_z = slope_z/norm;
        this.offset_x = offset_x;
        this.offset_y = offset_y;
        this.offset_z = offset_z;
        pointOfOrigin = new Point(Math.round(offset_x),Math.round(offset_y),Math.round(offset_z));
    }

    //The normal vector is defined as pointing from the point to the line. Values obtained by substracting the point's coordinates from the Ray and then
    //stating that the dot product of the result with the original ray's vector (origin at 0,0,0) should be 0.
    public Ray normal(Point point)
    {
        double alpha = slope_x*(offset_x - point.getX()) + slope_y*(offset_y - point.getY()) - slope_z*(offset_z - point.getZ());
        double beta = slope_x*slope_x + slope_y*slope_y + slope_z*slope_z;
        return new Ray(offset_x-point.getX()-(alpha/beta)*slope_x,offset_y-point.getY()-(alpha/beta)*slope_y,offset_z-point.getZ()-(alpha/beta)*slope_z,point.getX(),point.getY(),point.getZ());
    }

    //The point is calculated by stating that the x,y and z components of the rays should be equal to each other.
    public Point intersect(Ray otherRay) //uhhh, what does this method do... (interference calculations or something?)
    {
        System.out.println(otherRay.offset_x);
        System.out.println(this.offset_x);
        double new_x, new_y, new_z;

        //If the difference of the offsets is 0 and the lines cross each other (slope difference is not 0) the division becomes 1/(slopes).
        if ((otherRay.offset_x - this.offset_x == 0) && (this.slope_x - otherRay.slope_x != 0 ))
        {
            new_x = 1/(this.slope_x - otherRay.slope_x);
        }
        //If the difference of the offsets is not 0 and the lines cross each other this is used.
        else if (this.slope_x - otherRay.slope_x != 0) {
            new_x = (otherRay.offset_x - this.offset_x) / (this.slope_x - otherRay.slope_x);
        }
        //If the slope difference is 0 but they are in the same plane there's no problem.
        else if (this.offset_x == otherRay.offset_x)
        {
            new_x = offset_x;
        }
        //At this point we can conclude that the lines do not cross each other, so an exception is thrown (should probably make a new one or find a more suitable one though).
        else
        {
            System.out.println("X is null.");
            return null;
        }

        if ((otherRay.offset_y - this.offset_y == 0) && (this.slope_y != 0 || otherRay.slope_y != 0) )
        {
            new_y = 1/(this.slope_y - otherRay.slope_y);
        }
        else if (this.slope_y != 0 || otherRay.slope_y != 0) {
            new_y = (otherRay.offset_y - this.offset_y) / (this.slope_y - otherRay.slope_y);
        }
        else if (this.offset_y == otherRay.offset_y)
        {
            new_y = offset_y;
        }
        else
        {
            System.out.println("Y is null");
            return null;
        }

        if ((otherRay.offset_z - this.offset_z == 0) && (this.slope_z != 0 || otherRay.slope_z != 0) )
        {
            new_z = 1/(this.slope_z - otherRay.slope_z);
        }
        else if (this.slope_z != 0 || otherRay.slope_z != 0) {
            new_z = (otherRay.offset_z - this.offset_z) / (this.slope_z - otherRay.slope_z);
        }
        else if (this.offset_z == otherRay.offset_z)
        {
            new_z = offset_z;
        }
        else
        {
            System.out.println("Z is null");
            return null;
        }

        return new Point(Math.round(new_x),Math.round(new_y),Math.round(new_z));
    }

    //First we normalize the vectors (making new Ray objects in the process) at their intersection point and then
    //we calculate the dot product from which we can calculate the angle of intersection.
    public double angleOfIntersection(Ray otherRay)
    {
       double normalizationFactor_this = Utility.normalizationFactor(this);
       double normalizationFactor_other = Utility.normalizationFactor(otherRay);
       double temp_x_slope_this = (this.slope_x)/normalizationFactor_this;
       double temp_y_slope_this = (this.slope_y)/normalizationFactor_this;
       double temp_z_slope_this = (this.slope_z)/normalizationFactor_this;
       double temp_x_slope_other = (otherRay.slope_x)/normalizationFactor_other;
       double temp_y_slope_other = (otherRay.slope_y)/normalizationFactor_other;
       double temp_z_slope_other = (otherRay.slope_z)/normalizationFactor_other;
       Ray thisNormalized = new Ray(temp_x_slope_this,temp_y_slope_this,temp_z_slope_this);
       Ray otherNormalized = new Ray(temp_x_slope_other,temp_y_slope_other,temp_z_slope_other);
       return Math.acos(Utility.dotProduct(thisNormalized,otherNormalized));
    }

    public double getOffset_x() {
        return offset_x;
    }

    public double getOffset_y() {
        return offset_y;
    }

    public double getOffset_z() {
        return offset_z;
    }

    public double getSlope_x() {
        return slope_x;
    }

    public double getSlope_y() {
        return slope_y;
    }

    public double getSlope_z() {
        return slope_z;
    }

    public Point getPointOfOrigin() {
        return pointOfOrigin;
    }

    public void setOffset_x(double offset_x) {
        this.offset_x = offset_x;
    }

    public void setOffset_y(double offset_y) {
        this.offset_y = offset_y;
    }

    public void setOffset_z(double offset_z) {
        this.offset_z = offset_z;
    }

    public void setPointOfOrigin(Point pointOfOrigin) {
        this.pointOfOrigin = pointOfOrigin;
    }

    public void setSlope_x(double slope_x) {
        this.slope_x = slope_x;
    }

    public void setSlope_y(double slope_y) {
        this.slope_y = slope_y;
    }

    public void setSlope_z(double slope_z) {
        this.slope_z = slope_z;
    }
}
