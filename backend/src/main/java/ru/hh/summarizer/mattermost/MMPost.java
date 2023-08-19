package ru.hh.summarizer.mattermost;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MMPost(String id, @JsonProperty(value = "user_id") String userId, String message) {
}
