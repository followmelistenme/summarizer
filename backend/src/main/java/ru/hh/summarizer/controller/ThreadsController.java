package ru.hh.summarizer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Mattermost threads", description = "API для работы с тредами ММ")
public class ThreadsController {

    private final ThreadsService threadsService;

    @Operation(summary = "Добавление нового треда")
    @ApiResponse(responseCode = "200", description = "Возврашается спуленный тред и его саммари",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ChatDto.class))})
    @PostMapping("/thread")
    @ResponseStatus(HttpStatus.OK)
    public ChatDto createChat(@RequestBody ThreadLinkDto threadLinkDto) {
        return threadsService.createChat(threadLinkDto);
    }

    @Operation(summary = "Добавление промпта в чат по треду")
    @ApiResponse(responseCode = "200", description = "Возврашается спуленный тред, все промпты и саммари",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ChatDto.class))})
    @PostMapping("{chatId}/prompt")
    @ResponseStatus(HttpStatus.OK)
    public ChatDto addPrompt(@PathVariable Long chatId, @RequestBody PromptDto promptDto) {
        return threadsService.addPrompt(chatId, promptDto);
    }

}
