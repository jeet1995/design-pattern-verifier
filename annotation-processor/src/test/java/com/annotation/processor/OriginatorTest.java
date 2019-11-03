package com.annotation.processor;

import com.google.testing.compile.JavaFileObjects;
import com.google.testing.compile.JavaSourceSubjectFactory;
import org.junit.Test;

import static com.google.common.truth.Truth.assertAbout;

public class OriginatorTest {


    @Test
    public void testProperOriginator() {

        final MementoDesignPatternVerifierProcessor mementoDesignPatternVerifierProcessor = new
                MementoDesignPatternVerifierProcessor();
        assertAbout(JavaSourceSubjectFactory.javaSource()).that(JavaFileObjects.forSourceLines("com.annotation" + ""
                + ".processor.properImplementation.originator.Originator", "package com.annotation.processor" + "" +
                ".properImplementation.originator;", "import com.annotation.processor.VerifyOriginator;\n" + "\n" +
                "@VerifyOriginator\n" + "public class Originator {\n" + "\n" + "    @VerifyOriginator" + "" + "" + "" +
                ".VerifyStateField\n" + "    private " + "String state;\n" + "\n" + "    public Originator() {}\n" +
                "\n" + "    @VerifyOriginator" + ".VerifySetStateMethod\n" + "    public void setState(String state) " +
                "" + "" + "" + "{\n" + "        this.state = " + "state;\n" + "    }\n" + "\n" + "    " +
                "@VerifyOriginator" + "" + ".VerifySaveMementoStateMethod\n" + "    public " + "String save() {\n" +
                "        return " + "state;" + "\n" + "    }\n" + "\n" + "    " + "@VerifyOriginator" + "" + "" +
                ".VerifyRestoreMementoStateMethod\n" + "    " + "public void restore(String memento) " + "{\n" + "   " +
                "" + "    " + " state = memento;\n" + "    }\n" + "}")).processedWith
                (mementoDesignPatternVerifierProcessor).compilesWithoutWarnings();

    }

    @Test
    public void testImproperOriginator() {

        final MementoDesignPatternVerifierProcessor mementoDesignPatternVerifierProcessor = new
                MementoDesignPatternVerifierProcessor();
        assertAbout(JavaSourceSubjectFactory.javaSource()).that(JavaFileObjects.forSourceLines("com.annotation" + ""
                + ".processor.improperImplementation.ImproperOriginator", "package com.annotation.processor" + "" +
                ".properImplementation.originator;", "import com.annotation.processor.VerifyOriginator;\n" + "\n" +
                "@VerifyOriginator\n" + "public class ImproperOriginator {\n" + "\n" + "    @VerifyOriginator" + "" + "" + "" +
                ".VerifyStateField\n" + "    private " + "String state;\n" + "\n" + "    public ImproperOriginator() {}\n" +
                "\n" + "    @VerifyOriginator" + ".VerifySetStateMethod\n" + "    public void setState(String state) " +
                "" + "" + "" + "{\n" + "        this.state = " + "state;\n" + "    }\n" + "\n" + "    " +
                "@VerifyOriginator" + "" + ".VerifySaveMementoStateMethod\n" + "    public " + "String save() {\n" +
                "        return " + "state;" + "\n" + "    }\n" + "\n" + "    "  + "}")).processedWith
                (mementoDesignPatternVerifierProcessor).compilesWithoutError();

    }



}
