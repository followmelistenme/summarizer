package ru.hh.summarizer.dto;

import jakarta.validation.constraints.NotNull;

public record PromptDto(@NotNull String userToken, @NotNull String prompt) {
}
