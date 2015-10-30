import sun.java2d.loops.GraphicsPrimitive;

import javax.swing.*;
import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Created by jreuling on 29-9-2015.
 */

//Runner class to create the scenes, shapes etc and trace. Ironically does not extend Runnable.
public class Runner {
    public void start()
    {
        int dim = 500; //Half of the dimensions of the one viewport we are defining here, primarily a convenience variable.
        int numOfThreads = Math.min(1000,dim*dim); //We're making a number of threads for the tracing. It is possible to have more (several thousands even) but we'll keep it on the safe side.
        ArrayList<Tracer> tracerList = new ArrayList<>(); //The Tracer instances for threaded execution go in here.

        //Set up the attributes of the to-be-raytraced scene. Here a large scene is first created and then a sphere, lightsource, viewport and viewpoint are added.
        Scene scene = new Scene(1000,1000,1000);
        scene.addLightSource(new LightSource(0,0,1000));
        Shapes sphere = new Sphere(500,new Point(0,0,0),new int[]{0,255,255},0.8);
        scene.addShape(sphere);
        scene.addViewPort(new ViewPort(new Point(-dim,-dim,500),new Point(-dim,dim,500),new Point(dim,-dim,500),new Point(dim,dim,500)));
        scene.addViewPoint(new Point(0,0,1000));
        scene.initializeResultSet(scene.getViewPorts().get(0));

        //Determine how long it takes to run this.
        long timeParallelStart = System.currentTimeMillis();
        //Create the trace instances that we will be executing in threads.
        for (int i = 0; i < numOfThreads ; i++)
        {
            Tracer trace = new Tracer(scene,(int)Math.floor(i*2*dim/numOfThreads),(int)Math.floor((i+1)*2*dim/numOfThreads));
            tracerList.add(trace);
        }

        //Initialize threadPool and execute each tracer instance. Shutdown tells the threads to wrap up (or at least, that is my understanding of it).
        //The shutdown is then waited for for 10 minutes, though you will probably not reach 10 minutes unless you make a lot of shapes.
        ExecutorService threadPool = Executors.newFixedThreadPool(numOfThreads);

        for (int i = 0; i < numOfThreads ; i++)
        {
            threadPool.execute(tracerList.get(i));
        }

        threadPool.shutdown();

        try {
            threadPool.awaitTermination(10,TimeUnit.MINUTES);
        }
        catch (InterruptedException disturbance)
        {
            System.out.println("Process was disturbed.");
        }

        //Print how long it took and save the result.
        long timeParallelEnd = System.currentTimeMillis();
        double timeParallel = ((double)(timeParallelEnd -timeParallelStart))/1000.0;
        Utility.saveImage(scene.getResult());
        System.out.println("Parallel ray-tracing took: " + timeParallel + " seconds.");

        //This part below makes a window and prints the image of the result in it.

        DrawingPanel panel = new DrawingPanel(scene);                            // window for drawing
        JFrame application = new JFrame();                            // the program itself
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // set frame to exit when it is closed
        application.add(panel);
        application.setSize((int)scene.getViewPorts().get(0).dimX,(int)scene.getViewPorts().get(0).dimY );
        application.setVisible(true);
    }
}
