package ru.hh.summarizer.service;

import org.springframework.stereotype.Service;
import ru.hh.summarizer.SummarizerService;
import ru.hh.summarizer.dao.ChatMessageRepository;
import ru.hh.summarizer.dao.ChatRepository;
import ru.hh.summarizer.dto.ChatDto;
import ru.hh.summarizer.dto.MessageDto;
import ru.hh.summarizer.dto.PromptDto;
import ru.hh.summarizer.dto.ThreadLinkDto;

import java.time.LocalDateTime;
import java.util.List;
import ru.hh.summarizer.entity.Chat;
import ru.hh.summarizer.entity.ChatMessage;

@Service
public class ThreadsService {
    private final SummarizerService summarizerService;
    private final ChatRepository chatRepository;
    private final ChatMessageRepository chatMessageRepository;

    public ThreadsService(SummarizerService summarizerService, ChatRepository chatRepository, ChatMessageRepository chatMessageRepository) {
        this.summarizerService = summarizerService;
        this.chatRepository = chatRepository;
        this.chatMessageRepository = chatMessageRepository;
    }

    public ChatDto createChat(ThreadLinkDto threadLinkDto) {
        String summary = summarizerService.getSummary(threadLinkDto.threadLink(), threadLinkDto.userToken());
        Chat chat = new Chat();
        chatRepository.save(chat);
        ChatMessage chatMessageFromUser = new ChatMessage(threadLinkDto.threadLink(), true);
        chatMessageFromUser.setChat(chat);
        ChatMessage chatMessageFromGPT = new ChatMessage(summary, false);
        chatMessageFromGPT.setChat(chat);
        List<ChatMessage> messages = List.of(chatMessageFromUser, chatMessageFromGPT);
        chatMessageRepository.saveAll(messages);
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
