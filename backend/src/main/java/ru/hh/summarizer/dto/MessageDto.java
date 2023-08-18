package ru.hh.summarizer.dto;

import java.time.LocalDateTime;

public record MessageDto(Long id,
                         LocalDateTime dateTime,
                         String text,
                         boolean isUser) {
}
