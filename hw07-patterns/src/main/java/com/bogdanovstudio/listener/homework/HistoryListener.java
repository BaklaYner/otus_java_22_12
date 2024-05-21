package com.bogdanovstudio.listener.homework;

import com.bogdanovstudio.listener.Listener;
import com.bogdanovstudio.model.Message;

import java.util.Optional;

public class HistoryListener implements Listener, HistoryReader {
    @Override
    public void onUpdated(Message msg) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Message> findMessageById(long id) {
        throw new UnsupportedOperationException();
    }
}