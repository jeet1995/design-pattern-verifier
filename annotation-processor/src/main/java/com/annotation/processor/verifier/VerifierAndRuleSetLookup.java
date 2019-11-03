package com.annotation.processor.verifier;

import com.annotation.processor.ruleSet.CaretakerRuleSet;
import com.annotation.processor.ruleSet.MementoRuleSet;
import com.annotation.processor.ruleSet.OriginatorRuleSet;
import com.annotation.processor.ruleSet.RuleSet;

import java.util.HashMap;
import java.util.Map;

import static com.annotation.processor.utils.AnnotationConstants.*;

/**
 * This class denotes a look up for an annotated element when it comes to determining the right rule set and class
 * level verifier.
 */
public class VerifierAndRuleSetLookup {

    private VerifierAndRuleSetLookup() {
    }

    private static final MementoRuleSet mementoRuleSet = new MementoRuleSet();
    private static final OriginatorRuleSet originatorRuleSet = new OriginatorRuleSet();
    private static final CaretakerRuleSet caretakerRuleSet = new CaretakerRuleSet();

    public static final Map<String, RuleSet> nameToRuleSetMap = new HashMap<String, RuleSet>() {{
        put(VERIFY_MEMENTO, mementoRuleSet);
        put(VERIFY_ORIGINATOR, originatorRuleSet);
        put(VERIFY_CARETAKER, caretakerRuleSet);
    }};

    private static final MementoClassVerifier mementoClassVerifier = new MementoClassVerifier();
    private static final OriginatorClassVerifier originatorClassVerifier = new OriginatorClassVerifier();
    private static final CaretakerClassVerifier caretakerClassVerifier = new CaretakerClassVerifier();

    public static final Map<String, Verifier> nameToVerifierMap = new HashMap<String, Verifier>() {{
        put(VERIFY_MEMENTO, mementoClassVerifier);
        put(VERIFY_ORIGINATOR, originatorClassVerifier);
        put(VERIFY_CARETAKER, caretakerClassVerifier);
    }};


}
