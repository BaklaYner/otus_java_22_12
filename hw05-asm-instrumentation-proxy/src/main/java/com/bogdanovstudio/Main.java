package com.bogdanovstudio;

public class Main {
    public static void main(String[] args) {
        var proxy = LoggingProxyGenerator.generateLoggingProxy(AddLoggingInterface.class, new AddLoggingImpl());
        proxy.calculation(10);
        proxy.calculation(11, 12);
        proxy.calculation();
    }
}
