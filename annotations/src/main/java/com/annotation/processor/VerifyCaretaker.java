package com.annotation.processor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotations to verify the Caretaker class and its elements.
 * */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface VerifyCaretaker {

    @Retention(RetentionPolicy.CLASS)
    @Target(ElementType.FIELD)
    @interface VerifyMementoField {
    }

    @Retention(RetentionPolicy.CLASS)
    @Target({ElementType.METHOD})
    @interface VerifyAddMementoMethod {
    }

    @Retention(RetentionPolicy.CLASS)
    @Target(ElementType.METHOD)
    @interface VerifyRestoreMementoMethod {
    }

}
