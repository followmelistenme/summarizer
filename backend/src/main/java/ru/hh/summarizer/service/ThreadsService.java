package ru.hh.summarizer.service;

import org.springframework.stereotype.Service;
import ru.hh.summarizer.SummarizerService;
import ru.hh.summarizer.dao.ChatMessageRepository;
import ru.hh.summarizer.dao.ChatRepository;
import ru.hh.summarizer.dto.ChatDto;
import ru.hh.summarizer.dto.MessageDto;
import ru.hh.summarizer.dto.PromptDto;
import ru.hh.summarizer.dto.ThreadLinkDto;

import java.util.Comparator;
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
        chat.setThreadUrl(threadLinkDto.threadLink());
        chat.setUserToken(threadLinkDto.userToken());
        chatRepository.save(chat);
        ChatMessage chatMessageFromUser = new ChatMessage(threadLinkDto.threadLink(), true);
        chatMessageFromUser.setChat(chat);
        ChatMessage chatMessageFromGPT = new ChatMessage(summary, false);
        chatMessageFromGPT.setChat(chat);
        List<ChatMessage> messages = List.of(chatMessageFromUser, chatMessageFromGPT);
        chatMessageRepository.saveAll(messages);
        List<MessageDto> messagesDto = messages.stream().map(this::messageToMessageDto).toList();
        return new ChatDto(chat.getChatId(), messagesDto);
    }

    public ChatDto addPrompt(Long chatId, PromptDto promptDto) {
        Chat chat = chatRepository.findById(chatId)
                .orElseThrow(() -> new RuntimeException("id не правильный"));
        ChatMessage chatMessageFromUser = new ChatMessage(promptDto.prompt(), true);
        chatMessageFromUser.setChat(chat);
        chatMessageRepository.save(chatMessageFromUser);
        String lastSummary = chat.getMessages().stream()
                .max(Comparator.comparing(ChatMessage::getCreationTime))
                .get() // to do сделать нормально
                .getText();
        String summary = summarizerService.getSummaryByPrompt(lastSummary, chatMessageFromUser.getText());
        ChatMessage chatMessageFromGPT = new ChatMessage(summary, false);
        chatMessageFromGPT.setChat(chat);
        chatMessageRepository.save(chatMessageFromGPT);
        List<MessageDto> messages = chat.getMessages().stream()
                .sorted(Comparator.comparing(ChatMessage::getCreationTime))
                .map(this::messageToMessageDto)
                .toList();
        return new ChatDto(chatId, messages);
    }

    private MessageDto messageToMessageDto(ChatMessage message) {
        return new MessageDto(message.getId(), message.getCreationTime(), message.getText(), message.isUser());
    }

}
