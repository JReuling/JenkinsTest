import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by jreuling on 2-10-2015.
 */
public class DrawingPanel extends JPanel {

    Scene scene;

    public DrawingPanel()                       // set up graphics window
    {
        super();
    }

    public DrawingPanel(Scene scene)
    {
        this();
        this.scene = scene;
    }


    public void paintComponent(Graphics g)  // draw graphics in the panel
    {
        int width = getWidth();             // width of window in pixels
        int height = getHeight();           // height of window in pixels

        super.paintComponent(g);            // call superclass to make panel display correctly

        int[][][] result = scene.getResult();
        Image image = imageOfResult(result);
        g.drawImage(image,0,0,null);
    }

    public Image imageOfResult(int[][][] imageArray)
    {
        BufferedImage image;
        try {
            image = new BufferedImage(imageArray.length, imageArray[0].length, BufferedImage.TYPE_INT_RGB);
        }
        catch (NullPointerException nill)
        {
            return null;
        }
        for (int i = 0; i < imageArray.length ; i++)
        {
            for (int j = 0; j < imageArray[0].length ; j++)
            {
                int temp = new Color(imageArray[i][j][0]/255,imageArray[i][j][1]/255,imageArray[i][j][2]/255).getRGB();
                image.setRGB(i, j, temp);
            }
        }
        return image;
    }
}
