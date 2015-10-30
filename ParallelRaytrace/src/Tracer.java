import jdk.nashorn.internal.codegen.CompilerConstants;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.RecursiveAction;

/**
 * Created by jreuling on 30-9-2015.
 */
//The actual ray-tracing is done here. It extends Runnable so that it is executable in threads.
public class Tracer implements Runnable{
    private ArrayList<Point> viewPoints = new ArrayList<>();
    private ArrayList<ViewPort> viewPorts = new ArrayList<>();
    private ArrayList<LightSource> lightSources = new ArrayList<>();
    private ArrayList<Shapes> shapes = new ArrayList<>();
    private Scene scene;
    private int traceStartX, traceEndX;

    //Tracing requires the scene being traced and a start and end point, which determines which part of the
    //viewport is handled by this tracer.
    public Tracer(Scene scene, int traceStartX, int traceEndX)
    {
        this.scene = scene;
        this.viewPoints = scene.getViewPoints();
        this.viewPorts = scene.getViewPorts();
        this.lightSources = scene.getLightSources();
        this.shapes = scene.getShapes();
        this.traceStartX = traceStartX;
        this.traceEndX = traceEndX;
    }

    public void run()
    {
        tracePortToObject();
    }

    //The first part of the tracer method. For all viewpoints and all elements of the viewport defined creates a trace ray and checks whether or not
    //it intersects with any shapes. If it does, choose the first one and move over to the second part of the trace method.
    public void tracePortToObject()
    {
        if (viewPorts.size()==0)
        {
            System.out.println("No viewports defined.");
            System.exit(-1);
        }
        for(Point viewPoint:viewPoints)
        {
            for (ViewPort viewPort : viewPorts)
            {
                for (int i = traceStartX; i < traceEndX; i++)
                {
                    for (int j = 0; j < scene.result[0].length; j++)
                    {
                        Ray traceRay = new Ray(viewPoint, viewPort.getPoint(i, j));
                        ArrayList<Point> tempPointList = new ArrayList<>();
                        ArrayList<Shapes> tempShapeList = new ArrayList<>();
                        for (Shapes shape : shapes)
                        {
                            //The try-catch block is here for if there is no intersection, in which case get(0) throws an exception we don't really care about.
                            try
                            {
                                tempPointList.add(shape.intersect(traceRay).get(0));  //The zeroth element is always the first element of intersection with a shape.
                                tempShapeList.add(shape);
                            } catch (IndexOutOfBoundsException nill)
                            {
                                continue;
                            }
                        }
                        if (tempPointList.size() == 0)
                        {
                            continue;
                        }
                        Point firstIntersection = tempPointList.get(0);
                        Shapes intersectedShape = tempShapeList.get(0);
                        if (tempPointList.size() > 1)
                        {
                            double distance = scene.size(); //Longest distance possible within the scene, so every intersection will be at a shorter distance from the viewpoint
                            for (int a = 0; a < tempPointList.size(); a++)
                            {
                                if (Utility.distance(tempPointList.get(a), viewPoint) < distance)
                                {
                                    distance = Utility.distance(tempPointList.get(a), viewPoint);
                                    firstIntersection = tempPointList.get(a);
                                    intersectedShape = tempShapeList.get(a);
                                }
                            }
                        }
                        traceObjectToLight(firstIntersection, intersectedShape, i, j);
                    }
                }
            }
        }
    }

    //Part 2 of the ray-trace algorithm. This part traces the light from an intersection with an object to all lightsources present in the scene.
    //If it intersects with an object along the way ignore it, if it doesn't adjust the result based on its angle towards the normal on the object
    //calculated by the dot product. If the dot product is lower than 0 the ray to the lightsource is going through the object, so ignore that.
    public void traceObjectToLight(Point intersectionWithObject, Shapes shape, int viewPortX, int viewPortY)
    {
        for (LightSource lightSource: lightSources)
        {
            Ray traceRay = new Ray(intersectionWithObject, lightSource);
            ArrayList<Shapes> intersectedShapes = new ArrayList<>();
            for (Shapes tempShape:shapes)
            {
                if (tempShape.intersect(traceRay).size()>0)
                {
                    intersectedShapes.add(tempShape);
                }
            }
            if (intersectedShapes.size()>0)
            {
                continue;
            }
            else
            {
                scene.result[viewPortX][viewPortY][0] += lightSource.getBrightness()*shape.getDiffuseCoefficient()*shape.getColour()[0]*Math.max(0,Utility.dotProduct(shape.normal(intersectionWithObject),traceRay))/lightSources.size();
                scene.result[viewPortX][viewPortY][1] += lightSource.getBrightness()*shape.getDiffuseCoefficient()*shape.getColour()[1]*Math.max(0,Utility.dotProduct(shape.normal(intersectionWithObject),traceRay))/lightSources.size();
                scene.result[viewPortX][viewPortY][2] += lightSource.getBrightness()*shape.getDiffuseCoefficient()*shape.getColour()[2]*Math.max(0,Utility.dotProduct(shape.normal(intersectionWithObject),traceRay))/lightSources.size();
            }
        }
    }
}
