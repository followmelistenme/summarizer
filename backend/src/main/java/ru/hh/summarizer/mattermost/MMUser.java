package ru.hh.summarizer.mattermost;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MMUser(String id, @JsonProperty(value = "first_name") String firstName, @JsonProperty(value = "last_name") String lastName) {

}
