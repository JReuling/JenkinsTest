import java.util.ArrayList;

/**
 * Created by jreuling on 28-9-2015.
 */

public interface Shapes extends Coloured {
    ArrayList<Point> intersect(Ray ray);

    double getDiffuseCoefficient();

    Ray normal(Point point);
}