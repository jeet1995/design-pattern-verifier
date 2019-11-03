package com.annotation.processor.properImplementation.memento;

import com.annotation.processor.VerifyMemento;


/**
 * The Memento class can be likened to a class whose instances we would like to save or restore.
 * */
@VerifyMemento
public class Memento {

    @VerifyMemento.VerifyState
    private String state;

    @VerifyMemento.VerifyMementoConstructor
    public Memento(String state) {
        this.state = state;
    }

    @VerifyMemento.VerifyStateGetter
    public String getState() {
        return state;
    }
}
