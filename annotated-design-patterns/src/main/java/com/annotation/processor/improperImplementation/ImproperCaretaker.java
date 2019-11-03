package com.annotation.processor.improperImplementation;


import com.annotation.processor.VerifyCaretaker;
import com.annotation.processor.properImplementation.memento.Memento;

import java.util.ArrayList;
import java.util.List;

/**
 * This class denotes an incorrect implementation of a Caretaker class as it
 * does not add a memento or restore a memento. This will be a warning at compile time.
 * */

@VerifyCaretaker
public class ImproperCaretaker {
    @VerifyCaretaker.VerifyMementoField
    private List<Memento> mementos = new ArrayList<>();
}
