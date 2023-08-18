package ru.hh.summarizer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hh.summarizer.dto.ChatDto;
import ru.hh.summarizer.dto.MessageDto;
import ru.hh.summarizer.dto.PromptDto;
import ru.hh.summarizer.dto.ThreadLinkDto;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ThreadsService {

    public ChatDto createChat(ThreadLinkDto threadLinkDto) {
        MessageDto exampleMessageDto = new MessageDto(1L, LocalDateTime.now(), "text", true);
        ChatDto exampleChatDto = new ChatDto(69L, List.of(exampleMessageDto));
        return exampleChatDto;
    }

    public ChatDto addPrompt(Long chatId, PromptDto promptDto) {
        MessageDto exampleMessageDto = new MessageDto(1L, LocalDateTime.now().minusMinutes(10), "text", true);
        MessageDto examplePrompt = new MessageDto(2L, LocalDateTime.now(), promptDto.text(), true);
        ChatDto exampleChatDto = new ChatDto(chatId, List.of(exampleMessageDto, examplePrompt));
        return exampleChatDto;
    }

}
