package ru.hh.summarizer.service;

import org.springframework.stereotype.Service;
import ru.hh.summarizer.dto.ChatDto;
import ru.hh.summarizer.dto.MessageDto;
import ru.hh.summarizer.dto.PromptDto;
import ru.hh.summarizer.dto.TranscriptionDto;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TranscriptionService {

    public ChatDto createChat(TranscriptionDto transcriptionDto) {
        return new ChatDto(88L, List.of(new MessageDto(1L, LocalDateTime.now(), transcriptionDto.text(), true)));
    }

    public ChatDto addPrompt(Long chatId, PromptDto promptDto) {
        return new ChatDto(chatId, List.of(new MessageDto(1L, LocalDateTime.now(), promptDto.prompt(), true)));
    }

}
