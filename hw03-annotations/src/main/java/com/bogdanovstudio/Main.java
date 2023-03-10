package com.bogdanovstudio;

public class Main {
    public static void main(String[] args) {
        try {
            new TestAnnotationHandler(new TestResultConsolePrinter()).executeTests("com.bogdanovstudio.TestClass");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
