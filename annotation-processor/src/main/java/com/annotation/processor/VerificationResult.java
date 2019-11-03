package com.annotation.processor;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.tools.Diagnostic;


/**
 * This class denotes the result of annotation based verification for a given annotated element (CLASS, METHOD,
 * CONSTRUCTOR).
 */
public class VerificationResult {

    private Diagnostic.Kind kind;
    private ElementKind elementKind;
    private Element element;
    private String message;

    public VerificationResult(Diagnostic.Kind kind, ElementKind elementKind, Element element, String message) {
        this.kind = kind;
        this.elementKind = elementKind;
        this.element = element;
        this.message = message;
    }

    public Diagnostic.Kind getKind() {
        return kind;
    }

    public ElementKind getElementKind() {
        return elementKind;
    }

    public Element getElement() {
        return element;
    }

    public String getMessage() {
        return message;
    }
}
