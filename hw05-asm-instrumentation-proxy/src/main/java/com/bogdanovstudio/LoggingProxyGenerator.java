package com.bogdanovstudio;

import com.bogdanovstudio.annotations.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoggingProxyGenerator {
    private LoggingProxyGenerator() {}

    public static <T> T generateLoggingProxy(Class<T> interfaceForProxying, T implementation) {
        var handler = new CustomInvocationHandler(implementation);
        return (T) Proxy.newProxyInstance(LoggingProxyGenerator.class.getClassLoader(), new Class<?>[] {interfaceForProxying}, handler);
    }

    private static class CustomInvocationHandler implements InvocationHandler {
        private final Object wrappedImplementation;
        private final List<Method> annotatedMethods;

        CustomInvocationHandler(Object implementation) {
            wrappedImplementation = implementation;
            annotatedMethods = Arrays.stream(implementation.getClass().getMethods())
                    .filter((it) -> it.isAnnotationPresent(Log.class))
                    .toList();
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (isAnnotatedMethod(method)) {
                var paramsStringed = new ArrayList<String>();
                var params = method.getParameters();
                for (int i = 0; i < params.length; i++) {
                    paramsStringed.add("(" + params[i].getType().getSimpleName() + ": " + args[i] + ")");
                }

                System.out.println("Executed method: " + method.getName() + ", params: " + paramsStringed);
            }

            return method.invoke(wrappedImplementation, args);
        }

        private boolean isAnnotatedMethod(Method method) {
            return annotatedMethods.stream()
                    .filter(annotatedMethod -> annotatedMethod.getName().equals(method.getName()))
                    .anyMatch(it -> isEqualMethodsByParams(it, method));
        }

        private boolean isEqualMethodsByParams(Method first, Method second) {
            var firstMethodParams = first.getParameters();
            var secondMethodParams = second.getParameters();
            if (firstMethodParams.length != secondMethodParams.length) return false;

            for (int i = 0; i < firstMethodParams.length; i++) {
                var firstMethodParamType = firstMethodParams[i].getType().getName();
                var secondMethodParamType = secondMethodParams[i].getType().getName();
                if (!firstMethodParamType.equals(secondMethodParamType)) return false;
            }
            return true;
        }
    }
}
