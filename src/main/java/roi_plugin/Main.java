package roi_plugin;

import ij.IJ;
import ij.ImageJ;

public class Main {

    public static void main(final String... args) {

        // set the plugins.dir property to make the plugin appear in the Plugins menu
        Class<?> clazz = RoI_Plugin.class;
        String url = clazz.getResource("/" + clazz.getName().replace('.', '/') + ".class").toString();
        String pluginsDir = url.substring("file:".length(), url.length() - clazz.getName().length() - ".class".length());
        System.setProperty("plugins.dir", pluginsDir);

        // start ImageJ
        new ImageJ();


        // open the Clown sample
//        ImagePlus image = IJ.openImage("http://imagej.net/images/clown.jpg");
//        image.show();

        // run the plugin
        IJ.runPlugIn(clazz.getName(), "");

        // Run plugin straight away
        // ij.command().run(Threshold_Plugin.class, true);
    }

}
