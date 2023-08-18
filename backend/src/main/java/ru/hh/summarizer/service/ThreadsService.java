package ru.hh.summarizer.service;

import org.springframework.stereotype.Service;
import ru.hh.summarizer.SummarizerService;
import ru.hh.summarizer.dto.ChatDto;
import ru.hh.summarizer.dto.MessageDto;
import ru.hh.summarizer.dto.PromptDto;
import ru.hh.summarizer.dto.ThreadLinkDto;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ThreadsService {
    private final SummarizerService summarizerService;

    public ThreadsService(SummarizerService summarizerService) {
        this.summarizerService = summarizerService;
    }

    public ChatDto createChat(ThreadLinkDto threadLinkDto) {
        String summary = summarizerService.getSummary(threadLinkDto.threadLink(), threadLinkDto.userToken());
        MessageDto exampleMessageDto = new MessageDto(1L, LocalDateTime.now(), summary, false);
        ChatDto exampleChatDto = new ChatDto(69L, List.of(exampleMessageDto));
        return exampleChatDto;
    }

    public ChatDto addPrompt(Long chatId, PromptDto promptDto) {
        MessageDto exampleMessageDto = new MessageDto(1L, LocalDateTime.now().minusMinutes(10), "text", true);
        MessageDto examplePrompt = new MessageDto(2L, LocalDateTime.now(), promptDto.prompt(), true);
        ChatDto exampleChatDto = new ChatDto(chatId, List.of(exampleMessageDto, examplePrompt));
        return exampleChatDto;
    }

}
