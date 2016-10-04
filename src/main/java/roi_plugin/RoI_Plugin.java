package roi_plugin;

import ij.IJ;
import ij.plugin.PlugIn;
import com.leapmotion.leap.*;
import ij.ImagePlus;
import java.io.IOException;
import java.util.Observable;

public class RoI_Plugin extends Observable implements PlugIn {

    public void run(String arg) {

        // Leap Section
        ImageJListener listener = new ImageJListener();
        Controller controller = new Controller();
        controller.addListener(listener);

        System.out.println("Press Enter to quit...");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // ImageJ Section
        // Opens Image
        ImagePlus imp = IJ.openImage("http://wsr.imagej.net/images/blobs.gif");
        imp.show();



        // Closes Threshold window
        IJ.run("Close");

        // Closes image
        imp.close();

        controller.removeListener(listener);

    }

}
