package com.bogdanovstudio;

import java.lang.reflect.Method;

public class TestAnnotationHandler {
    public static void executeTests(Class<?> classForTest) {
        var testData = new TestData(classForTest);
        runTests(testData);
        testData.printTestResult();
    }

    private static void runTests(TestData testData) {
        testData.getTestMethods().forEach(testMethod -> runTest(testMethod, testData));
    }

    private static void runTest(Method testMethod, TestData testData) {
        Object testObject;
        try {
            testObject = testData.createTestClassObject();
        } catch (Exception e) {
            testData.addFatalError("Can't create object of test class");
            return;
        }

        var beforeMethodFailed = runBeforeMethods(testData, testObject);
        if (!beforeMethodFailed) {
            try {
                testMethod.invoke(testObject);
                testData.addSuccess(testMethod.getName());
            } catch (Exception e) {
                testData.addFail(testMethod.getName());
            }
        }
        runAfterMethods(testData, testObject);
        System.out.println("------------------");
    }

    private static boolean runBeforeMethods(TestData testData, Object testObject) {
        var beforeMethods = testData.getBeforeMethods();
        var beforeMethodFailed = false;
        for (Method beforeMethod : beforeMethods) {
            try {
                beforeMethod.invoke(testObject);
            } catch (Exception e) {
                testData.addFatalError("Exception is occurred on @Before method: '" + beforeMethod.getName() + "' invoke");
                beforeMethodFailed = true;
            }
        }

        return beforeMethodFailed;
    }

    private static void runAfterMethods(TestData testData, Object testObject) {
        var afterMethods = testData.getAfterMethods();
        afterMethods.forEach(afterMethod -> {
            try {
                afterMethod.invoke(testObject);
            } catch (Exception e) {
                System.out.println("Exception is occurred on @After method: '" + afterMethod.getName() + "' invoke");
            }
        });
    }
}
