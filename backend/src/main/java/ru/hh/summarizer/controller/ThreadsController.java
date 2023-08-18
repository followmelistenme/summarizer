package ru.hh.summarizer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.hh.summarizer.dto.ChatDto;
import ru.hh.summarizer.dto.PromptDto;
import ru.hh.summarizer.dto.ThreadLinkDto;
import ru.hh.summarizer.service.ThreadsService;

@RestController
@RequestMapping(path = "/api/threads")
@RequiredArgsConstructor
public class ThreadsController {

    private final ThreadsService threadsService;

    @PostMapping("/thread")
    @ResponseStatus(HttpStatus.OK)
    public ChatDto createChat(ThreadLinkDto threadLinkDto) {
        return threadsService.createChat(threadLinkDto);
    }

    @PostMapping("{chatId}/prompt")
    @ResponseStatus(HttpStatus.OK)
    public ChatDto addPrompt(@PathVariable Long chatId, PromptDto promptDto) {
        return threadsService.addPrompt(chatId, promptDto);
    }

}
