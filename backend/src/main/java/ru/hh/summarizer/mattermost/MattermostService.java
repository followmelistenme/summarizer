package ru.hh.summarizer.mattermost;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MattermostService {
  private static final String MM_URL = "https://mattermost.pyn.ru";

  private final ObjectMapper objectMapper;
  public MattermostService() {
    this.objectMapper = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
  }
  public List<Message> getMessagesByThread(String threadId, String token) {
    try {
      HttpRequest request = HttpRequest.newBuilder()
          .uri(new URI(MM_URL + "/api/v4/posts/%s/thread".formatted(threadId)))
          .setHeader("Authorization", "Bearer %s".formatted(token))
          .GET()
          .build();
      HttpResponse<String> response = HttpClient
          .newBuilder()
          .build()
          .send(request, HttpResponse.BodyHandlers.ofString());
      MMThread thread = objectMapper.readValue(response.body(), MMThread.class);
      return mapToMessages(thread);
    } catch (RuntimeException | URISyntaxException | IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  private List<Message> mapToMessages(MMThread thread) {
    List<Message> messages = new ArrayList<>();
    for (String id: thread.order()) {
      messages.add(Message.of(thread.posts().get(id)));
    }

    return messages;
  }
}
