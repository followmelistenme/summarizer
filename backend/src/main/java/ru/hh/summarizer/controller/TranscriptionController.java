package ru.hh.summarizer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.hh.summarizer.dto.ChatDto;
import ru.hh.summarizer.dto.PromptDto;
import ru.hh.summarizer.dto.TranscriptionDto;
import ru.hh.summarizer.service.TranscriptionService;

@RestController
@RequestMapping(path = "/api/transcription")
@RequiredArgsConstructor
@Tag(name = "Transcription video", description = "API для работы с траскрибацией видео")
public class TranscriptionController {

    private final TranscriptionService transcriptionService;

    @Operation(summary = "Добавление транскрибации видео")
    @ApiResponse(responseCode = "200", description = "Возврашается транскрибация видео и её саммари",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ChatDto.class))})
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    public ChatDto createChat(@RequestBody TranscriptionDto transcriptionDto) {
        return transcriptionService.createChat(transcriptionDto);
    }

    @Operation(summary = "Добавление промпта в чат по видео")
    @ApiResponse(responseCode = "200", description = "Возврашается транскрибация видео, все промпты и саммари",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ChatDto.class))})
    @PostMapping("/{chatId}/prompt")
    @ResponseStatus(HttpStatus.OK)
    public ChatDto addPrompt(@PathVariable Long chatId, @RequestBody PromptDto promptDto) {
        return transcriptionService.addPrompt(chatId, promptDto);
    }

}
