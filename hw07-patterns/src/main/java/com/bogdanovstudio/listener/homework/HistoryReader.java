package com.bogdanovstudio.listener.homework;

import com.bogdanovstudio.model.Message;

import java.util.Optional;

public interface HistoryReader {
    Optional<Message> findMessageById(long id);
}