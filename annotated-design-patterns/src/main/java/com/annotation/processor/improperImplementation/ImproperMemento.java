package com.annotation.processor.improperImplementation;

import com.annotation.processor.VerifyMemento;


/**
 * This class denotes an incorrect implementation of a Memento class as it
 * does not set the state through a constructor or get the state. Also, the state field has a public modifier.
 * These defects will be printed as warnings at compile time.
 * */
@VerifyMemento
public class ImproperMemento {

    @VerifyMemento.VerifyState
    public String state;

    @VerifyMemento.VerifyStateSetter
    public void setState(String state) {
        this.state = state;
    }
}
