package com.mycompany.threshold_plugin;

import ij.IJ;
import ij.plugin.PlugIn;
import com.leapmotion.leap.*;
import ij.ImagePlus;
import java.io.IOException;
import java.util.Observable;

public class Threshold_Plugin extends Observable implements PlugIn {

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

        // Spinner Section
        JSpinnerTest mySpinner = new JSpinnerTest();
        mySpinner.showFrame();

        // ImageJ Section
        // Opens Image
        ImagePlus imp = IJ.openImage("http://wsr.imagej.net/images/blobs.gif");
        imp.show();

        // Opens Threshold
        IJ.setAutoThreshold(imp, "Default");

        // Sets Threshold values
        // IJ.setRawThreshold(imp, IJ.getNumber("prompt", 6), IJ.getNumber("prompt", 7), null);
        IJ.setRawThreshold(imp, 6, 7, null);


        // Sleeps to view changes
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Closes Threshold window
        IJ.run("Close");

        // Closes image
        imp.close();

        controller.removeListener(listener);

    }

}
