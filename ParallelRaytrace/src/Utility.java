import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by jreuling on 28-9-2015.
 */
public class Utility {
    //Calculates the dot (inner) product between two rays
    public static double dotProduct(Ray ray1, Ray ray2)
    {
        return ray1.getSlope_x()*ray2.getSlope_x() + ray1.getSlope_y()*ray2.getSlope_y() + ray1.getSlope_z()*ray2.getSlope_z();
    }

    //Calculates the cross (outer) product between two rays, currently not in use because it was used in the method to calculate the normal on an object
    //but sphere has an easier way to do so.
    public static Ray crossProduct(Ray ray1, Ray ray2, Point pointOfOrigin)
    {
        double newSlopeX = ray1.getSlope_y()*ray2.getSlope_z() - ray1.getSlope_z()*ray2.getSlope_y();
        double newSlopeY = ray1.getSlope_z()*ray2.getSlope_x() - ray1.getSlope_x()*ray2.getSlope_z();
        double newSlopeZ = ray1.getSlope_x()*ray2.getSlope_y() - ray1.getSlope_y()*ray2.getSlope_x();
        return new Ray(newSlopeX,newSlopeY,newSlopeZ,pointOfOrigin.getX(),pointOfOrigin.getY(),pointOfOrigin.getZ());
    }

    //Calculates the normalization factor of a ray.
    public static double normalizationFactor(Ray ray)
    {
        return Math.sqrt(Math.pow(ray.getSlope_x(),2) + Math.pow(ray.getSlope_y(),2) + Math.pow(ray.getSlope_z(),2));
    }

    public static double normalizationFactor(double x, double y, double z)
    {
        return Math.sqrt(Math.pow(x,2) + Math.pow(y,2) + Math.pow(z,2));
    }

    //Calculates the distance between two points.
    public static double distance(Point p1, Point p2)
    {
        return Math.sqrt(Math.pow((p1.getX()-p2.getX()),2) + Math.pow((p1.getY()-p2.getY()),2) + Math.pow((p1.getZ()-p2.getZ()),2));
    }

    //Create an image object and set all its RGB values according to the result of the trace given by imageArray.
    public static void saveImage(int[][][] imageArray)
    {
        BufferedImage image;
        try {
            image = new BufferedImage(imageArray.length, imageArray[0].length, BufferedImage.TYPE_INT_RGB);
        }
        catch (NullPointerException nill)
        {
            return;
        }
        for (int i = 0; i < imageArray.length ; i++)
        {
            for (int j = 0; j < imageArray[0].length ; j++)
            {
                int temp = new Color(imageArray[i][j][0]/255,imageArray[i][j][1]/255,imageArray[i][j][2]/255).getRGB();
                image.setRGB(i, j, temp);
            }
        }

        try {
            ImageIO.write(image, "png", new File("saved2.png"));
        }
        catch (IOException io)
        {
            System.out.println("IO Exception in Image 2");
            System.exit(-1);
        }
    }

    public static void saveImage(int[][][] imageArray, File file)
    {
        BufferedImage image;
        try {
            image = new BufferedImage(imageArray.length, imageArray[0].length, BufferedImage.TYPE_BYTE_GRAY);
        }
        catch (NullPointerException nill)
        {
            return;
        }
        for (int i = 0; i < imageArray.length ; i++)
        {
            for (int j = 0; j < imageArray[0].length ; j++)
            {
                int temp = new Color(imageArray[i][j][0]/255,imageArray[i][j][1]/255,imageArray[i][j][2]/255).getRGB();
                image.setRGB(i, j, temp);
            }
        }


        try {
            ImageIO.write(image, "png", file);
        }
        catch (IOException io)
        {
            System.out.println("IO Exception in Image 2");
            System.exit(-1);
        }
    }
}
