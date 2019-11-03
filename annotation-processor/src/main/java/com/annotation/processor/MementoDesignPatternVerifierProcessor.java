package com.annotation.processor;

import com.annotation.processor.ruleSet.RuleSet;
import com.annotation.processor.verifier.Verifier;
import com.annotation.processor.verifier.VerifierAndRuleSetLookup;
import com.google.auto.service.AutoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import java.util.List;
import java.util.Set;


/**
 * This class denotes an annotation processor which processes all annotated elements pertaining to the memento
 * design pattern and prints a set of compile time messages encapsulated in an object of type @{@link VerificationResult}.
 * These annotations are processed as a part of two for loops. The first one processes all elements enclosed within a class
 * and the second loop processes the class itself to check for elements which are not defined but required by the
 * memento design pattern.
 * */
@SupportedAnnotationTypes("com.annotation.processor.*")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class MementoDesignPatternVerifierProcessor extends AbstractProcessor {

    private Messager messager;
    private static final Logger LOGGER = LoggerFactory.getLogger(MementoDesignPatternVerifierProcessor.class);

    public MementoDesignPatternVerifierProcessor() {
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        messager = processingEnv.getMessager();
    }

    /**
     * This method processes all annotated elements at two levels - class level and at enclosed within a class level.
     *
     * @param annotations All the annotations which are defined in the package com.annotation.processor.*
     * @param roundEnv The compilation round when it comes to processing annotations.
     *
     * @return boolean
     * */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        // Verify all elements within a class
        annotations.forEach(annotation -> {

            Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(annotation);
            elements.forEach(element -> {
                if (element.getKind() != ElementKind.CLASS) {

                    LOGGER.info("Verifying elements within a class annotated with {}", annotation.getSimpleName());

                    RuleSet ruleSet = VerifierAndRuleSetLookup.nameToRuleSetMap.get(annotation.getEnclosingElement()
                            .getSimpleName().toString());

                    Verifier verifier = VerifierAndRuleSetLookup.nameToVerifierMap.get(annotation.getEnclosingElement
                            ().getSimpleName().toString());

                    VerificationResult verificationResult = ruleSet.verifyElement(annotation, element, element
                            .getEnclosingElement());
                    verifier.addVerifiedElement(annotation, element.getEnclosingElement());

                    messager.printMessage(verificationResult.getKind(), verificationResult.getMessage());
                }
            });
        });

        // Verify all annotated classes
        annotations.forEach(annotation -> {


            Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(annotation);
            elements.forEach(element -> {
                if (element.getKind() == ElementKind.CLASS) {

                    LOGGER.info("Verifying class annotated with {}", annotation.getSimpleName());

                    Verifier verifier = VerifierAndRuleSetLookup.nameToVerifierMap.get(annotation.getSimpleName()
                            .toString());
                    List<? extends VerificationResult> verificationResults = verifier.getVerificationResults(element);

                    if (!verificationResults.isEmpty()) {
                        verificationResults.forEach(verificationResult -> messager.printMessage(verificationResult
                                .getKind(), verificationResult.getMessage()));
                    }
                }
            });
        });
        return true;
    }
}
