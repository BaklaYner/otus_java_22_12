package com.bogdanovstudio.handler;

import com.bogdanovstudio.listener.Listener;
import com.bogdanovstudio.model.Message;

public interface Handler {
    Message handle(Message msg);

    void addListener(Listener listener);

    void removeListener(Listener listener);
}