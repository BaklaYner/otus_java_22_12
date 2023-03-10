package com.bogdanovstudio;

public class TestResultConsolePrinter implements TestResultHandler {
    @Override
    public void handleResult(TestData testData) {
        if (testData.getFatalError() != null) {
            System.out.println("Fatal error was occurred, tests wasn't run. " + testData.getFatalError());
            return;
        }

        var result = String.format("Test result: %d/%d success test", testData.getSuccessTestQuantity(), testData.getTotalTestQuantity());
        System.out.println(result);
        if (testData.getFailedTestQuantity() != 0) {
            System.out.println("Failed tests: " + testData.getFailedTestNames());
        }
    }
}
