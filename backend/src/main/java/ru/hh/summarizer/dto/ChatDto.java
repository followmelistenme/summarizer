package ru.hh.summarizer.dto;

import java.util.List;

public record ChatDto(Long chatId, List<MessageDto> messages) {
}