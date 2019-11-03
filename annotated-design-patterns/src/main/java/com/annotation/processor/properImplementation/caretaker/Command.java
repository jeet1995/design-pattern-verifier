package com.annotation.processor.properImplementation.caretaker;

import com.annotation.processor.VerifyCaretaker;
import com.annotation.processor.properImplementation.memento.Snapshot;
import com.annotation.processor.properImplementation.originator.Editor;


/**
 * A Command object can act as a caretaker. In that case, the command gets a memento just before it changes the
 * originator's state. When undo is requested, it restores the originator's state from a memento.
 */
@VerifyCaretaker
public class Command {

    @VerifyCaretaker.VerifyMementoField
    private Snapshot backup;

    @VerifyCaretaker.VerifyAddMementoMethod
    public void makeBackup(Editor editor) {
        backup = editor.createSnapshot();
    }

    @VerifyCaretaker.VerifyRestoreMementoMethod
    public void undo() {
        backup.restore();
    }
}
