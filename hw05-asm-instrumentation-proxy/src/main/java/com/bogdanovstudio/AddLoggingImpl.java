package com.bogdanovstudio;

import com.bogdanovstudio.annotations.Log;

public class AddLoggingImpl implements AddLoggingInterface {
    @Override
    public void calculation(int param) {
        System.out.println("#calculation(int param): " + param);
    }

    @Log
    @Override
    public void calculation(int param1, int param2) {
        System.out.println("#calculation(int param1, int param2): " + param1 + ", " + param2);
    }

    @Log
    @Override
    public void calculation() {
        System.out.println("#calculation(): without params");
    }
}
