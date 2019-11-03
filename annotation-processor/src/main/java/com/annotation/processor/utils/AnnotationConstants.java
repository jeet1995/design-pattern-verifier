package com.annotation.processor.utils;


/**
 * This class represents a utility class which encapsulates annotations used across the project.
 * */
public class AnnotationConstants {

    private AnnotationConstants() {
    }

    public static final String VERIFY_MEMENTO_FIELD = "VerifyMementoField";
    public static final String VERIFY_ADD_MEMENTO_METHOD = "VerifyAddMementoMethod";
    public static final String VERIFY_RESTORE_MEMENTO_METHOD = "VerifyRestoreMementoMethod";
    public static final String VERIFY_STATE = "VerifyState";
    public static final String VERIFY_MEMENTO_CONSTRUCTOR = "VerifyMementoConstructor";
    public static final String VERIFY_STATE_SETTER = "VerifyStateSetter";

    public static final String VERIFY_STATE_GETTER = "VerifyStateGetter";
    public static final String VERIFY_STATE_FIELD = "VerifyStateField";
    public static final String VERIFY_SET_STATE_METHOD = "VerifySetStateMethod";
    public static final String VERIFY_SAVE_MEMENTO_STATE_METHOD = "VerifySaveMementoStateMethod";
    public static final String VERIFY_RESTORE_MEMENTO_STATE_METHOD = "VerifyRestoreMementoStateMethod";

    public static final String VERIFY_MEMENTO = "VerifyMemento";
    public static final String VERIFY_ORIGINATOR = "VerifyOriginator";
    public static final String VERIFY_CARETAKER = "VerifyCaretaker";


}
