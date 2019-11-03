package com.annotation.processor.properImplementation.originator;


import com.annotation.processor.VerifyOriginator;
import com.annotation.processor.properImplementation.memento.Snapshot;

/**
 * The Editor holds some important data that may change over
 * time. It also defines a method for saving its state inside a
 * memento and another method for restoring the state from it.
 */
@VerifyOriginator
public class Editor {

    @VerifyOriginator.VerifyStateField
    private String text;

    @VerifyOriginator.VerifyStateField
    private String curX;

    @VerifyOriginator.VerifyStateField
    private String curY;

    @VerifyOriginator.VerifyStateField
    private String selectionWidth;

    private Snapshot snapshot;

    @VerifyOriginator.VerifySetStateMethod
    public void setText(String text) {
        this.text = text;
    }

    @VerifyOriginator.VerifySetStateMethod
    public void setCursor(String x, String y) {
        this.curX = x;
        this.curY = y;
    }

    @VerifyOriginator.VerifySetStateMethod
    public void setSelectionWidth(String width) {
        this.selectionWidth = width;

    }

    @VerifyOriginator.VerifySaveMementoStateMethod
    public void saveSnapshot() {
        snapshot = new Snapshot(this, text, curX, curY, selectionWidth);
    }

    @VerifyOriginator.VerifyRestoreMementoStateMethod
    public Snapshot createSnapshot() {
        return snapshot;
    }

}
