package com.annotation.processor.ruleSet;

import com.annotation.processor.VerificationResult;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;

public interface RuleSet {

    VerificationResult verifyElement(TypeElement annotation, Element elementToVerify, Element enclosingElement);

    VerificationResult constructElementNotDefinedResult(String elementSimpleName, ElementKind elementKind, Element
            enclosingElement);

}
