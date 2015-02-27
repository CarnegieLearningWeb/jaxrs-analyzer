package com.sebastian_daschner.jaxrs_analyzer.analysis.project.methods;

import com.sebastian_daschner.jaxrs_analyzer.analysis.utils.TestClassUtils;
import com.sebastian_daschner.jaxrs_analyzer.model.elements.JsonValue;
import com.sebastian_daschner.jaxrs_analyzer.model.results.MethodResult;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Set;

@RunWith(Parameterized.class)
public class JsonResponseMethodAnalyzerTest {

    private final JsonResponseMethodAnalyzer classUnderTest;
    private final String testClassName;
    private final Set<JsonValue> expectedResult;
    private final CtMethod method;

    public JsonResponseMethodAnalyzerTest(final String testClassName, final CtMethod method, final Set<JsonValue> expectedResult) {
        this.testClassName = testClassName;
        this.expectedResult = expectedResult;
        this.classUnderTest = new JsonResponseMethodAnalyzer();
        this.method = method;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() throws NotFoundException, IOException, ReflectiveOperationException {
        Collection<Object[]> data = new LinkedList<>();

        final Set<Class<?>> testClasses = TestClassUtils.getClasses("com.sebastian_daschner.jaxrs_analyzer.analysis.project.methods.testclasses.json");

        for (final Class<?> testClass : testClasses) {
            if (!testClass.getSimpleName().startsWith("TestClass"))
                continue;

            final Object[] testData = new Object[3];

            testData[0] = testClass.getSimpleName();

            // load test class
            ClassPool pool = ClassPool.getDefault();
            final CtClass ctClass = pool.get(testClass.getName());

            // "method"-method
            testData[1] = ctClass.getDeclaredMethod("method");

            // evaluate static "getResult"-method
            testData[2] = testClass.getDeclaredMethod("getResult").invoke(null);

            data.add(testData);
        }

        return data;
    }

    @Test
    public void test() {
        final MethodResult result = new MethodResult();
        try {
            classUnderTest.analyze(method, result);
        } catch (Exception e) {
            System.err.println("failed for " + testClassName);
            throw e;
        }
        final Set<JsonValue> actualResult = result.getResponses().iterator().next().getInlineEntities();

        Assert.assertEquals("failed for " + testClassName, expectedResult, actualResult);
    }

}
