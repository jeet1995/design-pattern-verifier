package com.annotation.processor.properImplementation.memento;

import com.annotation.processor.VerifyMemento;
import com.annotation.processor.properImplementation.originator.Editor;

/**
 * The snapshot class is a type of memento where our state variables are text, curX, curY and selectionWidth.
 * We save these 4 variables into an object of type @{@link Editor} and restore it when necessary.
 * */
@VerifyMemento
public class Snapshot {

    @VerifyMemento.VerifyState
    private String text;

    @VerifyMemento.VerifyState
    private String curX;

    @VerifyMemento.VerifyState
    private String curY;

    @VerifyMemento.VerifyState
    private String selectionWidth;

    private Editor editor;

    @VerifyMemento.VerifyMementoConstructor
    public Snapshot(Editor editor, String text, String curX, String curY, String selectionWidth) {
        this.editor = editor;
        this.text = text;
        this.curX = curX;
        this.curY = curY;
        this.selectionWidth = selectionWidth;
    }

    @VerifyMemento.VerifyStateGetter
    public Editor restore() {
        editor.setText(text);
        editor.setCursor(curX, curY);
        editor.setSelectionWidth(selectionWidth);
        return editor;
    }
}
