package com.annotation.processor.verifier;

import com.annotation.processor.VerificationResult;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.List;

public interface Verifier {

    void addVerifiedElement(TypeElement annotation, Element element);

    List<? extends VerificationResult> getVerificationResults(Element element);

}
