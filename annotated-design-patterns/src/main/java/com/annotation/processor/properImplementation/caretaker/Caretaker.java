package com.annotation.processor.properImplementation.caretaker;

import com.annotation.processor.VerifyCaretaker;
import com.annotation.processor.properImplementation.memento.Memento;

import java.util.ArrayList;
import java.util.List;


/**
 * This class denotes a proper implementation of a caretaker which
 * checkpoints as a list of @{@link Memento} and restores by returning
 * the last memento on the list.
 * */
@VerifyCaretaker
public class Caretaker {

    @VerifyCaretaker.VerifyMementoField
    private List<Memento> mementos = new ArrayList<>();

    @VerifyCaretaker.VerifyAddMementoMethod
    public void addMemento(Memento memento) {
        mementos.add(memento);
    }

    @VerifyCaretaker.VerifyRestoreMementoMethod
    public Memento restore() {
        return mementos.get(mementos.size() - 1);
    }

}
