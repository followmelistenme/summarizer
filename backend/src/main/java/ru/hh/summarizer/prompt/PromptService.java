package ru.hh.summarizer.prompt;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import ru.hh.summarizer.mattermost.Message;

@Service
public class PromptService {
  private static final int THRESHOLD = 2800;

  public String getCommonPrompt() {
    return "к какому выводу пришли в обсуждении?";
  }

  public List<String> getWrappedMessageBatches(List<Message> threadMessages) {
    List<String> batches = new ArrayList<>();
    StringBuilder currentPrompt = new StringBuilder();
    List<String> wrappedMessages = threadMessages
        .stream()
        .map(m -> "%s написал: %s. ".formatted(m.getSender(), m.getText()))
        .toList();

    for (String currentMessage : wrappedMessages) {
      currentPrompt.append(currentMessage);
      if (currentPrompt.length() > THRESHOLD) {
        batches.add(currentPrompt.toString());
        currentPrompt.setLength(0);
      }
    }

    return batches;
  }
}
