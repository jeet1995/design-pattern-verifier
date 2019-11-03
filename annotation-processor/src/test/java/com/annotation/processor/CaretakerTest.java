package com.annotation.processor;

import com.google.testing.compile.JavaFileObjects;
import com.google.testing.compile.JavaSourceSubjectFactory;
import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertAbout;

public class CaretakerTest {

    private MementoDesignPatternVerifierProcessor mementoDesignPatternVerifierProcessor;

    @Before
    public void setup() {
        mementoDesignPatternVerifierProcessor = new MementoDesignPatternVerifierProcessor();

    }

    @Test
    public void testProperCaretaker() {

        assertAbout(JavaSourceSubjectFactory.javaSource()).that(JavaFileObjects.forSourceLines("com.annotation" + ""
                + ".processor.properImplementation.caretaker.Caretaker", "" + "package com.annotation.processor" + ""
                + ".properImplementation.caretaker;\n" + "\n" + "import com.annotation.processor.VerifyCaretaker;\n"
                + "import com.annotation.processor.VerifyCaretaker.VerifyAddMementoMethod;\n" + "import com" + "" +
                "" + ".annotation" + ".processor.VerifyCaretaker.VerifyMementoField;\n" + "import com.annotation" +
                "" + "" + ".processor" + "" + ".VerifyCaretaker.VerifyRestoreMementoMethod;\n" + "import java.util" +
                ".ArrayList;" + "\n" + "import java" + ".util.List;\n" + "\n" + "@VerifyCaretaker\n" + "public class " +
                "" + "Caretaker {\n" + "    " + "@VerifyMementoField\n" + "    private List<String> mementos = new "
                + "ArrayList<String>();\n" + "\n" + "    " + "public Caretaker() {\n" + "    }\n" + "\n" + "    " +
                "@VerifyAddMementoMethod\n" + "  " + "" + "  " + "" + "public void " + "addMemento(String memento) "
                + "{\n" + "        this.mementos.add" + "(memento);" + "\n" + " " + "   " + "}\n" + "\n" + "    " +
                "@VerifyRestoreMementoMethod\n" + "    " + "public String " + "restore() {\n" + "      " + "  return " +
                "" + "this" + ".mementos.get(this.mementos.size()" + " - 1);\n" + "    " + "}\n" + "}"))
                .processedWith(mementoDesignPatternVerifierProcessor).compilesWithoutWarnings();
    }

    @Test
    public void testImproperCaretaker() {
        assertAbout(JavaSourceSubjectFactory.javaSource()).that(JavaFileObjects.forSourceLines("com.annotation" + ""
                + ".processor.improperImplementation.ImproperCaretaker", "" + "package com.annotation.processor" + ""
                + ".improperImplementation;\n" + "\n" + "import com.annotation.processor.VerifyCaretaker;\n"
                + "import com.annotation.processor.VerifyCaretaker.VerifyAddMementoMethod;\n" + "import com" + "" +
                "" + ".annotation" + ".processor.VerifyCaretaker.VerifyMementoField;\n" + "import com.annotation" +
                "" + "" + ".processor" + "" + ".VerifyCaretaker.VerifyRestoreMementoMethod;\n" + "import java.util" +
                ".ArrayList;" + "\n" + "import java" + ".util.List;\n" + "\n" + "@VerifyCaretaker\n" + "public class " +
                "" + "ImproperCaretaker {\n" + "    " + "@VerifyMementoField\n" + "    private List<String> mementos = new "
                + "ArrayList<String>();\n" + "\n" + "    " + "public ImproperCaretaker() {\n" + "    }\n" + "\n" + "    " +
                "}")).processedWith(mementoDesignPatternVerifierProcessor).compilesWithoutError();
    }
}

