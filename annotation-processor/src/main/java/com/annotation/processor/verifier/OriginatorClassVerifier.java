package com.annotation.processor.verifier;

import com.annotation.processor.VerificationResult;
import com.annotation.processor.ruleSet.OriginatorRuleSet;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import java.util.*;

import static com.annotation.processor.utils.AnnotationConstants.*;

/**
 * This class denotes a class level verifier which determines if all annotated elements within a class are present.
 * */
public class OriginatorClassVerifier implements Verifier {

    private static final List<String> enclosingAnnotatedElements = new ArrayList<>(Arrays.asList(VERIFY_STATE_FIELD,
            VERIFY_SET_STATE_METHOD, VERIFY_SAVE_MEMENTO_STATE_METHOD, VERIFY_RESTORE_MEMENTO_STATE_METHOD));
    private static Map<String, Map<String, Boolean>> originatorByImplementationVerifiedMap = new HashMap<>();
    private OriginatorRuleSet originatorRuleSet;

    public OriginatorClassVerifier() {
        originatorRuleSet = new OriginatorRuleSet();
    }

    /**
     * This method adds an annotated element which has been verified by the annotation processor and adds it
     * to a map consisting of verified elements enclosed within the class.
     *
     * @param annotationElement The annotated element which is defined within a caretaker class.
     * @param classElement The class element which encloses the annotated element.
     * */
    @Override
    public void addVerifiedElement(TypeElement annotationElement, Element classElement) {
        Map<String, Boolean> verifiedMap = originatorByImplementationVerifiedMap.get(classElement.getSimpleName()
                .toString());

        if (verifiedMap == null)
            verifiedMap = new HashMap<>();

        verifiedMap.put(annotationElement.getSimpleName().toString(), true);
        originatorByImplementationVerifiedMap.put(classElement.getSimpleName().toString(), verifiedMap);
    }

    /**
     * This method determines at the class level which enclosing element have not been defined and returns a
     * list of @{@link VerificationResult} containing warning messages for all those elements that are not defined.
     *
     * @param classElement The class which is to be verified.
     * @return list of @{@link VerificationResult}
     * */
    @Override
    public List<? extends VerificationResult> getVerificationResults(Element classElement) {
        Map<String, Boolean> verifiedMap = originatorByImplementationVerifiedMap.get(classElement.getSimpleName()
                .toString());
        List<VerificationResult> verificationResults = new ArrayList<>();

        for (String enclosingAnnotatedElement : enclosingAnnotatedElements) {

            if (!verifiedMap.containsKey(enclosingAnnotatedElement)) {
                verificationResults.add(originatorRuleSet.constructElementNotDefinedResult(enclosingAnnotatedElement,
                        ElementKind.ANNOTATION_TYPE, classElement));
            }
        }
        return verificationResults;
    }
}
