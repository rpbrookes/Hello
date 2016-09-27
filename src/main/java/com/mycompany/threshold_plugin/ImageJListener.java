package com.mycompany.threshold_plugin;

import java.awt.Robot;
import com.leapmotion.leap.*;
import com.leapmotion.leap.Gesture.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class ImageJListener extends Listener {

    ObservableValue event = new ObservableValue();

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
        com.leapmotion.leap.Frame frame = controller.frame();

        //Get hands
        for (Hand hand : frame.hands()) {
            String handType = hand.isLeft() ? "Left hand" : "Right hand";

            // Get the hand's normal vector and direction
            Vector normal = hand.palmNormal();
            Vector direction = hand.direction();

            // Get arm bone
            Arm arm = hand.arm();

            // Get fingers
            for (Finger finger : hand.fingers()) {

                //Get Bones
                for (Bone.Type boneType : Bone.Type.values()) {
                    Bone bone = finger.bone(boneType);

                }
            }
        }

        for (Gesture gesture : frame.gestures()) {
            if (gesture.type() == Type.TYPE_CIRCLE) {
                CircleGesture circle = new CircleGesture(gesture);

                if (circle.pointable().direction().angleTo(circle.normal()) <= Math.PI / 4) {

                    System.out.println("clockwise");

                    int cw = event.getSpinValue();
                    cw++;
                    event.setSpinValue(cw);
                    JSpinnerTest.mySpinner.getModel().setValue(cw);

                } else {

                    System.out.println("anti-clockwise");

                    int acw = event.getSpinValue();
                    acw--;
                    event.setSpinValue(acw);
                    JSpinnerTest.mySpinner.getModel().setValue(acw);

                }

            } else if (gesture.type() == Type.TYPE_KEY_TAP) {
                System.out.println("Button Press");
                KeyTapGesture Tap;
                Tap = new KeyTapGesture(gesture);
                
                JSpinnerTest.myButton.doClick();

//                try {
//                    Robot robot = new Robot();
//                    robot.keyPress(KeyEvent.VK_ENTER);
//                    robot.keyRelease(KeyEvent.VK_ENTER);
//                } catch (AWTException awt) {
//                }
            }
        }
    }
}
