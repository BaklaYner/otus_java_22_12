package com.bogdanovstudio;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.IntStream;

public class HelloOtus {
    public static void main(String... args) {
        List<Integer> range = IntStream.rangeClosed(0, 99).boxed().toList();
        System.out.println(Lists.reverse(range));
    }
}
