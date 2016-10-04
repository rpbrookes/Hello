package roi_plugin;

import java.awt.Robot;
import com.leapmotion.leap.*;
import com.leapmotion.leap.Gesture.*;
import java.awt.*;
import java.awt.event.InputEvent;

class SampleListener extends Listener {

    public Robot robot;

    public void onInit(Controller controller) {
        System.out.println("Initialized");
    }

    public void onConnect(Controller controller) {
        System.out.println("Connected");
        controller.enableGesture(Gesture.Type.TYPE_CIRCLE);
        controller.enableGesture(Gesture.Type.TYPE_KEY_TAP);
    }

    public void onDisconnect(Controller controller) {
        System.out.println("Disconnected");
    }

    public void onExit(Controller controller) {
        System.out.println("Exited");
    }

    public void onFrame(Controller controller) {
        try {
            robot = new Robot();
        } catch (Exception e) {
        }
        com.leapmotion.leap.Frame frame = controller.frame();
        InteractionBox box = frame.interactionBox();
        for (Finger f : frame.fingers()) {
            if (f.type() == Finger.Type.TYPE_INDEX) {
                Vector fingerPos = f.stabilizedTipPosition();
                Vector boxFingerPos = box.normalizePoint(fingerPos);
                Dimension screen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
                robot.mouseMove((int) (screen.width * boxFingerPos.getX()), (int) (screen.height - boxFingerPos.getY() * screen.height));
            }

        }

        for (Gesture gesture : frame.gestures()) {
            if (gesture.type() == Type.TYPE_CIRCLE) {
                CircleGesture circle = new CircleGesture(gesture);

                if (circle.pointable().direction().angleTo(circle.normal()) <= Math.PI / 4) {

                    System.out.println("clockwise");
                    robot.mousePress(InputEvent.BUTTON1_MASK);

                } else {

                    System.out.println("anti-clockwise");
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);

                }
            }
//            else if (gesture.type() == Type.TYPE_KEY_TAP) {
//                
//                System.out.println("Button Press");
//                
//                KeyTapGesture Tap;
//                Tap = new KeyTapGesture(gesture);
//
//            }
        }
    }

}
