package com.annotation.processor;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface VerifyOriginator {

    @Retention(RetentionPolicy.CLASS)
    @Target(ElementType.FIELD)
    @interface VerifyStateField {
    }

    @Retention(RetentionPolicy.CLASS)
    @Target(ElementType.METHOD)
    @interface VerifySetStateMethod {
    }

    @Retention(RetentionPolicy.CLASS)
    @Target(ElementType.METHOD)
    @interface VerifySaveMementoStateMethod {
    }

    @Retention(RetentionPolicy.CLASS)
    @Target(ElementType.METHOD)
    @interface VerifyRestoreMementoStateMethod {
    }


}
