package com.bogdanovstudio;

import com.bogdanovstudio.annotations.After;
import com.bogdanovstudio.annotations.Before;
import com.bogdanovstudio.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class TestData {
    private final List<Class<? extends Annotation>> annotations = List.of(Before.class, Test.class, After.class);
    private final Map<Class<? extends Annotation>, List<Method>> methodsByAnnotation = new HashMap<>();
    private final Map<String, String> resultsByMethodName = new HashMap<>();
    private final Class<?> classForTest;
    private String fatalError = null;

    public TestData(Class<?> classForTest) {
        annotations.forEach(anno -> methodsByAnnotation.put(anno, new ArrayList<>()));
        this.classForTest = classForTest;
        parseClass();
    }

    public Object createTestClassObject() throws Exception {
        return classForTest.getConstructor().newInstance();
    }

    public List<Method> getBeforeMethods() {
        return methodsByAnnotation.get(Before.class);
    }

    public List<Method> getTestMethods() {
        return methodsByAnnotation.get(Test.class);
    }

    public List<Method> getAfterMethods() {
        return methodsByAnnotation.get(After.class);
    }

    public void addSuccess(String methodName) {
        resultsByMethodName.put(methodName, "success");
    }

    public void addFail(String methodName) {
        resultsByMethodName.put(methodName, "failed");
    }

    public long getSuccessTestQuantity() {
        return resultsByMethodName.values().stream().filter("success"::equals).count();
    }

    public long getFailedTestQuantity() {
        return resultsByMethodName.values().stream().filter("failed"::equals).count();
    }

    public long getTotalTestQuantity() {
        return getTestMethods().size();
    }

    public void addFatalError(String errorDescription) {
        fatalError = errorDescription;
    }

    public void printTestResult() {
        if (fatalError == null) {
            var result = String.format("Test result: %d/%d success test", getSuccessTestQuantity(), getTotalTestQuantity());
            System.out.println(result);
            if (getSuccessTestQuantity() != getTotalTestQuantity()) {
                System.out.println("Failed tests: " + getFailedTestNames());
            }
            return;
        }

        System.out.println("Fatal error was occurred, tests wasn't run. " + fatalError);
    }

    private void parseClass() {
        Stream.of(classForTest.getDeclaredMethods()).forEach(method -> annotations.forEach(anno -> {
            if (method.isAnnotationPresent(anno)) methodsByAnnotation.get(anno).add(method);
        }));
    }

    private String getFailedTestNames() {
        return resultsByMethodName.entrySet()
                .stream()
                .filter(entry -> "failed".equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.joining(", "));
    }
}