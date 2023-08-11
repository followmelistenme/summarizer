package com.followmelistenme.summarizer.integration.mattermost;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Service {

  private static final String MM_URL = "https://mattermost.pyn.ru";

  private final ObjectMapper objectMapper;
  public Service() {
    this.objectMapper = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
  }

  public String getMMSessionToken() {
    try {
      HttpRequest request = HttpRequest.newBuilder()
          .uri(new URI(MM_URL + "/api/v4/users/login"))
          .POST(HttpRequest.BodyPublishers.ofString(
              "{\"login_id\": \"login@kek.ru\", \"password\": \"kek,\"}")
          )
          .build();
      HttpResponse<String> response = HttpClient
          .newBuilder()
          .build()
          .send(request, HttpResponse.BodyHandlers.ofString());

      List<String> token = response.headers().map().getOrDefault("Token", List.of("token"));
      return token.get(0);

    } catch (RuntimeException | URISyntaxException | InterruptedException | IOException e) {
      return e.getMessage();
    }
  }

  public String getMessagesByThread(String threadId) {
    try {
      HttpRequest request = HttpRequest.newBuilder()
          .uri(new URI(MM_URL + "/api/v4/posts/%s/thread".formatted(threadId)))
          .setHeader("Authorization", "Bearer %s".formatted(getMMSessionToken()))
          .GET()
          .build();
      HttpResponse<String> response = HttpClient
          .newBuilder()
          .build()
          .send(request, HttpResponse.BodyHandlers.ofString());
      MMThread thread = objectMapper.readValue(response.body(), MMThread.class);
      return "ok!";
    } catch (RuntimeException | URISyntaxException | IOException | InterruptedException e) {
      return e.getMessage();
    }
  }

  private record MMSessionRequestDTO(String login_id, String password) {}
}
