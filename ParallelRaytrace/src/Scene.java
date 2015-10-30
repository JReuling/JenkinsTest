import java.util.ArrayList;

/**
 * Created by jreuling on 29-9-2015.
 */
public class Scene {
    private long dimX,dimY,dimZ;
    private ArrayList<Point> viewPoints = new ArrayList<>();
    private ArrayList<ViewPort> viewPorts = new ArrayList<>();
    private ArrayList<LightSource> lightSources = new ArrayList<>();
    private ArrayList<Shapes> shapes = new ArrayList<>();
    public int[][][] result;

    //Scene is assumed to be cubic and dimX, dimY and dimZ are the distances of the center to the sides along their respective axes.
    public Scene(long dimX, long dimY, long dimZ)
    {
        this.dimX = dimX;
        this.dimY = dimY;
        this.dimZ = dimZ;
    }

    public void initializeResultSet(ViewPort viewPort)
    {
        result = new int[(int)viewPort.dimX][(int)viewPort.dimY][3];
    }

    public long size()
    {
        return 2*Math.round(Math.sqrt(dimX*dimX + dimY*dimY + dimZ*dimZ));
    }

    public void addViewPort(ViewPort viewPort)
    {
        viewPorts.add(viewPort);
    }

    public void addViewPoint(Point viewPoint)
    {
        viewPoints.add(viewPoint);
    }

    public void addLightSource(LightSource lightSource)
    {
        lightSources.add(lightSource);
    }

    public void addShape(Shapes shape)
    {
        shapes.add(shape);
    }

    public ArrayList<LightSource> getLightSources() {
        return lightSources;
    }

    public ArrayList<Point> getViewPoints() {
        return viewPoints;
    }

    public ArrayList<Shapes> getShapes() {
        return shapes;
    }

    public ArrayList<ViewPort> getViewPorts() {
        return viewPorts;
    }

    public long getDimX() {
        return dimX;
    }

    public long getDimY() {
        return dimY;
    }

    public long getDimZ() {
        return dimZ;
    }

    public int[][][] getResult() {
        return result;
    }

    public void setResult(int[][][] result) {
        this.result = result;
    }

    public void setDimX(long dimX) {
        this.dimX = dimX;
    }

    public void setDimY(long dimY) {
        this.dimY = dimY;
    }

    public void setDimZ(long dimZ) {
        this.dimZ = dimZ;
    }

    public void setLightSources(ArrayList<LightSource> lightSources) {
        this.lightSources = lightSources;
    }

    public void setShapes(ArrayList<Shapes> shapes) {
        this.shapes = shapes;
    }

    public void setViewPoints(ArrayList<Point> viewPoints) {
        this.viewPoints = viewPoints;
    }

    public void setViewPorts(ArrayList<ViewPort> viewPorts) {
        this.viewPorts = viewPorts;
    }
}
