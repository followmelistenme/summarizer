package ru.hh.summarizer.chatgptimplementation;

import java.util.List;

public class ChatCompletionRequest {
  public String model;
  public List<MessageDTO> messages;
}
