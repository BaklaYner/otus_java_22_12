package com.bogdanovstudio;

import com.bogdanovstudio.annotations.After;
import com.bogdanovstudio.annotations.Before;
import com.bogdanovstudio.annotations.Test;

public class TestClass {
    @Before
    public void beforeOne() {
        System.out.println("beforeOne " + this.hashCode());
    }

    @Before
    public void beforeTwo() {
        System.out.println("beforeTwo " + this.hashCode());
    }

    @Test
    public void testOne() {
        System.out.println("testOne " + this.hashCode());
    }

    @Test
    public void testTwo() {
        System.out.println("testTwo " + this.hashCode());
    }

    @Test
    public void testThree() {
        System.out.println("testThree " + this.hashCode());
    }

    @After
    public void afterOne() {
        System.out.println("afterOne " + this.hashCode());
    }

    @After
    public void afterTwo() {
        System.out.println("afterTwo " + this.hashCode());
    }
}