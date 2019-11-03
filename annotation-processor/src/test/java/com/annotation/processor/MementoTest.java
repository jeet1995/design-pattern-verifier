package com.annotation.processor;

import com.google.common.base.Joiner;
import com.google.common.truth.Truth;
import com.google.testing.compile.JavaFileObjects;
import com.google.testing.compile.JavaSourcesSubjectFactory;
import org.junit.Test;

import javax.tools.JavaFileObject;
import java.util.Arrays;

public class MementoTest {

    @Test
    public void testProperMemento() {

        final JavaFileObject javaFileObjectInput = JavaFileObjects.forSourceString("com.annotation.processor" + "" +
                        ".properImplementation.memento.Memento",

                Joiner.on("\n").join("package com.annotation.processor.properImplementation.memento;\n", "\n",
                        "import com" + ".annotation.processor.VerifyMemento;\n", "import com.annotation.processor" +
                                ".VerifyMemento" + "" + ".VerifyState;\n", "import com.annotation.processor" + "" +
                                ".VerifyMemento.VerifyStateGetter;\n", "import com.annotation.processor" +
                                ".VerifyMemento" + ".VerifyMementoConstructor;\n", "\n", "@VerifyMemento\n", "public class " +
                                "Memento {\n", "    " + "@VerifyState\n", "    private String " + "state;\n", "\n", "" +
                                "    " + "@VerifyMementoConstructor\n", "    public Memento(String state) {\n", "        " +
                                "this.state =" + " state;\n", "    }\n", "\n", "    @VerifyStateGetter\n", "    " +
                                "public " + "String " + "getState() {\n", "        return this.state;\n", "    }\n",
                        "}")

        );

        Truth.assert_().about(JavaSourcesSubjectFactory.javaSources()).that(Arrays.asList(javaFileObjectInput))
                .processedWith(new MementoDesignPatternVerifierProcessor()).compilesWithoutWarnings();

    }

    @Test
    public void testImproperMemento() {

        final JavaFileObject javaFileObjectInput = JavaFileObjects.forSourceString("com.annotation.processor" + "" +
                        ".improperImplementation.ImproperMemento",

                Joiner.on("\n").join("package com.annotation.processor.improperImplementation;\n", "\n" + "import " +
                        "com" + ".annotation.processor.VerifyMemento;\n", "import com.annotation.processor" + "" + ""
                        + ".VerifyMemento" + ".VerifyState;\n", "\n", "@VerifyMemento\n", "public class " +
                        "ImproperMemento" + " {\n", "    " + "@VerifyState\n", "    public String state;\n", "\n", " " +
                        "" + "" + "   public " + "ImproperMemento() {\n", "  " + "  }\n", "}")

        );

        Truth.assert_().about(JavaSourcesSubjectFactory.javaSources()).that(Arrays.asList(javaFileObjectInput))
                .processedWith(new MementoDesignPatternVerifierProcessor()).compilesWithoutError();

    }
}
