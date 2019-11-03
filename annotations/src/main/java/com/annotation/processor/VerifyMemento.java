package com.annotation.processor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotations to verify the Memento class and its elements.
 * */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface VerifyMemento {

    @Retention(RetentionPolicy.CLASS)
    @Target(ElementType.FIELD)
    @interface VerifyState {
    }

    @Retention(RetentionPolicy.CLASS)
    @Target(ElementType.CONSTRUCTOR)
    @interface VerifyMementoConstructor {
    }

    @Retention(RetentionPolicy.CLASS)
    @Target(ElementType.METHOD)
    @interface VerifyStateSetter {

    }


    @Retention(RetentionPolicy.CLASS)
    @Target(ElementType.METHOD)
    @interface VerifyStateGetter {
    }

}
