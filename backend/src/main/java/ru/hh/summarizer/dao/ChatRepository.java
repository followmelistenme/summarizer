package ru.hh.summarizer.dao;

import org.springframework.data.repository.CrudRepository;
import ru.hh.summarizer.entity.Chat;

public interface ChatRepository extends CrudRepository<Chat, Long> {
}
