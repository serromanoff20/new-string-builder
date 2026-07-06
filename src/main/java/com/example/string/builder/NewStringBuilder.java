package com.example.string.builder;

import java.io.IOException;
import java.util.Objects;
import java.util.Stack;

public class NewStringBuilder extends AbstractNewStringBuilder{

    /**
     * История состояний
     */
    protected Stack<String> stateHistory;

    public NewStringBuilder() {
        super(16);
        firstSaveState();
    }
    public NewStringBuilder(int initialCapacity) {
        super(initialCapacity);
        firstSaveState();
    }
    public NewStringBuilder(String initialStr) {
        super(initialStr.length());
        firstSaveState();
    }

    private void firstSaveState() {
        stateHistory = new Stack<>();

        stateHistory.push(super.toString());
    }

    protected void saveState() {
        if (!Objects.equals(stateHistory.getLast(), super.toString())) {

            stateHistory.push(super.toString());
        }
    }

    @Override
    public NewStringBuilder append(String str) {
        try {
            super.append(str);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        saveState();

        return this;
    }

    public NewStringBuilder append(int i) {
        String str  = String.valueOf(i);

        try {
            super.append(str);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        saveState();

        return this;
    }

    public void undo() {
        if (stateHistory.size() > 1) {
            stateHistory.pop();

            super.delete(stateHistory.getLast().length(), super.length());
        }
    }
}
