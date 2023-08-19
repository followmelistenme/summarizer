package ru.hh.summarizer.dao;

import org.springframework.data.repository.CrudRepository;
import ru.hh.summarizer.entity.ChatMessage;

public interface ChatMessageRepository extends CrudRepository<ChatMessage, Integer> {
}
