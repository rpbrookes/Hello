package com.mycompany.threshold_plugin;

import java.util.Observable;

class ObservableValue extends Observable {

    public int promptValue = 0;

    public int getSpinValue() {
        return promptValue;

    }

    public void setSpinValue(int newPromptValue) {
        promptValue = newPromptValue;
        this.setChanged();

    }

    public void addObserver() {
        ObservableValue event = new ObservableValue();
        JSpinnerTest value = new JSpinnerTest();
        event.addObserver(value);
        event.setSpinValue(promptValue);

    }

}
