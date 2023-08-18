package ru.hh.summarizer.chatgptimplementation;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import org.springframework.stereotype.Service;

@Service
public class ChatGptServiceImpl {

  private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);
  private static final String API_KEY = "key_mock";
  private static final String PROXY_API_HOST = "https://llmgtw.hhdev.ru/openai/hack2";
  private static final String MODEL_NAME = "gpt-3.5-turbo";
  private final HttpClient httpClient;
  private final ObjectMapper objectMapper;

  public ChatGptServiceImpl() {
   this.httpClient = HttpClient.newBuilder().connectTimeout(DEFAULT_TIMEOUT).build();
   this.objectMapper = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
  }

  public String chatCompletion(String prompt) {
    try {
      HttpRequest request = HttpRequest.newBuilder()
          .uri(new URI(PROXY_API_HOST + "/chat/completions"))
          .setHeader("Authorization", "Bearer %s".formatted(API_KEY))
          .setHeader("Content-Type", "application/json")
          .POST(HttpRequest.BodyPublishers.ofString(
              "{\"model\": \"" + MODEL_NAME + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}")
          )
          .build();
      HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

      ChatCompletion chatCompletion = objectMapper.readValue(response.body(), ChatCompletion.class);
      return objectMapper.writeValueAsString(chatCompletion);
    } catch (JsonMappingException e) {
      throw new RuntimeException(e);
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
