package com.annotation.processor.improperImplementation;

import com.annotation.processor.VerifyOriginator;


/**
 * This class denotes an incorrect implementation of an Originator class as it
 * does not save or restore the Memento or set the state.
 * */
@VerifyOriginator
public class ImproperOriginator {

    @VerifyOriginator.VerifyStateField
    private String state;

}
