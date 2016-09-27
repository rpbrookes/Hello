package com.mycompany.threshold_plugin;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class JSpinnerTest extends JPanel implements ActionListener, Observer {

    // Maybe introduce two buttons, one for each threshold?
    // Or, could work with an array...
    public static JSpinner mySpinner = new JSpinner();
    public static JButton myButton = new JButton("Set T1");
    public static JButton myButton2 = new JButton("Set T2");
    public static ObservableValue myObservable = new ObservableValue();
    int[] aryNums = new int[2];

    public JSpinnerTest() {
        this.setLayout(new BorderLayout());
        this.add(myButton, BorderLayout.SOUTH);
        this.add(myButton2, BorderLayout.SOUTH);
        this.add(mySpinner, BorderLayout.NORTH);
        myButton.addActionListener(this);
        myButton2.addActionListener(this);
        myObservable.addObserver(this);

    }

    // Observer implementation
    public void update(Observable o, Object arg) {
        mySpinner.getModel().setValue(new Integer(myObservable.getSpinValue()));

    }

    // ActionListener implementation
    public void actionPerformed(ActionEvent ae) {
        System.out.println("+++  Action Performed  +++");
        myObservable.setSpinValue(myObservable.getSpinValue());
        myObservable.notifyObservers();

    }

    public void showFrame() {
        JFrame myFrame = new JFrame("JSpinner Test");
        myFrame.getContentPane().setLayout(new BorderLayout());
        myFrame.getContentPane().add(this, BorderLayout.CENTER);
        myFrame.pack();
        myFrame.setVisible(true);

    }

}
