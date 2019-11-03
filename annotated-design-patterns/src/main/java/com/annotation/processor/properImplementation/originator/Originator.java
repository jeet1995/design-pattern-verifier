package com.annotation.processor.properImplementation.originator;


import com.annotation.processor.VerifyOriginator;
import com.annotation.processor.properImplementation.memento.Memento;


/**
 * This class helps in saving the state variable into an instance of the class @{@link Memento}
 * */
@VerifyOriginator
public class Originator {

    @VerifyOriginator.VerifyStateField
    private String state;

    public Originator() {
    }

    @VerifyOriginator.VerifySetStateMethod
    public void setState(String state) {
        this.state = state;
    }

    @VerifyOriginator.VerifySaveMementoStateMethod
    public Memento save() {
        return new Memento(state);
    }

    @VerifyOriginator.VerifyRestoreMementoStateMethod
    public void restore(Memento memento) {
        state = memento.getState();
    }
}
