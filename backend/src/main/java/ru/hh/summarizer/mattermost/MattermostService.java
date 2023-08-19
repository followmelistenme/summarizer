package ru.hh.summarizer.mattermost;

import com.fasterxml.jackson.core.type.TypeReference;
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
import java.util.Map;
import java.util.stream.Collectors;
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

      List<String> userIds = thread.posts().entrySet().stream().map(entry -> entry.getValue().userId()).toList();
      Map<String, MMUser> usersByIds = getUsersByIds(userIds, token);
      return mapToMessages(thread, usersByIds);
    } catch (RuntimeException | URISyntaxException | IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  public Map<String, MMUser> getUsersByIds(List<String> ids, String token) {

    try {
      HttpRequest request = HttpRequest.newBuilder()
          .uri(new URI(MM_URL + "/api/v4/users/ids"))
          .setHeader("Authorization", "Bearer %s".formatted(token))
          .POST(HttpRequest.BodyPublishers.ofString(
              objectMapper.writeValueAsString(ids)
          ))
          .setHeader("Content-Type", "application/json")
          .build();

      HttpResponse<String> response = HttpClient
          .newBuilder()
          .build()
          .send(request, HttpResponse.BodyHandlers.ofString());

      List<MMUser> mmUsers = objectMapper.readValue(response.body(), new TypeReference<>() {});
      return mmUsers.stream().collect(Collectors.toMap(MMUser::id, user -> user, (v1, v2) -> v1));
    } catch (URISyntaxException | IOException e) {
      throw new RuntimeException(e);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new RuntimeException(e);
    }
  }


  private List<Message> mapToMessages(MMThread thread, Map<String, MMUser> userIdToMmUser) {
    List<Message> messages = new ArrayList<>();
    for (String id: thread.order()) {
      MMPost mmPost = thread.posts().get(id);
      MMUser mmUser = userIdToMmUser.get(mmPost.userId());
      messages.add(Message.of(mmPost, mmUser));
    }

    return messages;
  }
}
