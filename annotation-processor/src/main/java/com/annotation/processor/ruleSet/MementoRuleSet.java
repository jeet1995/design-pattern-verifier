package com.annotation.processor.ruleSet;

import com.annotation.processor.VerificationResult;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

import static com.annotation.processor.utils.AnnotationConstants.*;
import static com.annotation.processor.utils.MessageUtils.*;

/**
 * This class denotes a rule set for elements defined within a class which represents a memento.
 * */
public class MementoRuleSet implements RuleSet {

    /**
     * @param annotation The annotation to verify.
     * @param elementToVerify The annotated element to verify.
     * @param enclosingElement The enclosing element which encloses the annotated element.
     *
     * @return verificationResult The verification result.
     * */
    @Override
    public VerificationResult verifyElement(TypeElement annotation, Element elementToVerify, Element enclosingElement) {

        VerificationResult verificationResult;

        switch (annotation.getSimpleName().toString()) {

        case VERIFY_STATE_GETTER:
            verificationResult = checkGetStateMethod(annotation, elementToVerify, enclosingElement);
            break;
        case VERIFY_MEMENTO_CONSTRUCTOR:
            verificationResult = checkConstructor(annotation, elementToVerify, enclosingElement);
            break;
        case VERIFY_STATE:
            verificationResult = checkStateVariable(annotation, elementToVerify, enclosingElement);
            break;
        case VERIFY_STATE_SETTER:
            verificationResult = checkSetStateMethod(annotation, elementToVerify, enclosingElement);
            break;
        default:
            verificationResult = null;

        }
        return verificationResult;

    }

    /**
     * @param annotation The annotation to verify.
     * @param element The annotated element to verify.
     * @param enclosingElement The enclosing element of the annotated element.
     *
     * @return VerificationResult The result of the verification.
     * */
    private VerificationResult checkStateVariable(TypeElement annotation, Element element, Element enclosingElement) {

        String elementName = element.getSimpleName().toString();
        String enclosingElementName = enclosingElement.getSimpleName().toString();

        if (element.getModifiers().contains(Modifier.PUBLIC)) {
            return new VerificationResult(Diagnostic.Kind.WARNING, element.getKind(), element, "Variable declaration " +
                    "" + "" + "" + elementName + ENCLOSING_ELEMENT + enclosingElementName + ANNOTATED_WITH + annotation.getSimpleName().toString() + " should not have public " +
                    "" + "modifier.");
        } else {
            return new VerificationResult(Diagnostic.Kind.NOTE, element.getKind(), element, "Variable declaration " +
                    elementName + ENCLOSING_ELEMENT + enclosingElementName + ANNOTATED_WITH + annotation.getSimpleName().toString() + IS_CORRECT);
        }

    }

    /**
     * @param annotation The annotation to verify.
     * @param element The annotated element to verify.
     * @param enclosingElement The enclosing element of the annotated element.
     *
     * @return VerificationResult The result of the verification.
     * */
    private VerificationResult checkGetStateMethod(TypeElement annotation, Element element, Element enclosingElement) {

        String elementName = element.getSimpleName().toString();
        String enclosingElementName = enclosingElement.getSimpleName().toString();

        if (element.getModifiers().contains(Modifier.PRIVATE) || element.getModifiers().contains(Modifier.STATIC)) {
            return new VerificationResult(Diagnostic.Kind.WARNING, element.getKind(), element, "Method " +
                    elementName + ENCLOSING_ELEMENT + enclosingElementName + ANNOTATED_WITH + annotation.getSimpleName().toString() + SHOULD_NOT_BE_PRIVATE_NOR_STATIC);
        } else {
            return new VerificationResult(Diagnostic.Kind.NOTE, element.getKind(), element, "Method declaration " +
                    elementName + ENCLOSING_ELEMENT + enclosingElementName + ANNOTATED_WITH + annotation.getSimpleName().toString() + " is correct");
        }
    }

    /**
     * @param annotation The annotation to verify.
     * @param element The annotated element to verify.
     * @param enclosingElement The enclosing element of the annotated element.
     *
     * @return VerificationResult The result of the verification.
     * */
    private VerificationResult checkSetStateMethod(TypeElement annotation, Element element, Element enclosingElement) {

        String elementName = element.getSimpleName().toString();
        String enclosingElementName = enclosingElement.getSimpleName().toString();


        return new VerificationResult(Diagnostic.Kind.WARNING, element.getKind(), element, "Method " +
                    elementName + ENCLOSING_ELEMENT + enclosingElementName + ANNOTATED_WITH + annotation.getSimpleName().toString() + "should not be defined and field variables should be initialized only through a constructor.");

    }

    /**
     * @param annotation The annotation to verify.
     * @param element The annotated element to verify.
     * @param enclosingElement The enclosing element of the annotated element.
     *
     * @return VerificationResult The result of the verification.
     * */
    private VerificationResult checkConstructor(TypeElement annotation, Element element, Element enclosingElement) {

        String enclosingElementName = enclosingElement.getSimpleName().toString();

        if (element.getModifiers().contains(Modifier.PRIVATE)) {
            return new VerificationResult(Diagnostic.Kind.WARNING, element.getKind(), element, "Constructor " +
                    ENCLOSING_ELEMENT + enclosingElementName + ANNOTATED_WITH + annotation.getSimpleName().toString() + " should not be private");
        } else {
            return new VerificationResult(Diagnostic.Kind.NOTE, element.getKind(), element, "Constructor " +
                    ENCLOSING_ELEMENT + enclosingElementName + ANNOTATED_WITH + annotation.getSimpleName().toString() + " is correct");
        }
    }

    @Override
    public VerificationResult constructElementNotDefinedResult(String elementSimpleName, ElementKind elementKind,
            Element enclosingElement) {
        return new VerificationResult(Diagnostic.Kind.WARNING, elementKind, null, "No element annotated with " +
                elementSimpleName + " " + "is defined in enclosing element " + enclosingElement.getSimpleName()
                .toString());
    }
}
