package ru.hh.summarizer;

import java.util.List;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import ru.hh.summarizer.chatgptimplementation.ChatGptServiceImpl;
import ru.hh.summarizer.mattermost.MattermostService;
import ru.hh.summarizer.mattermost.Message;
import ru.hh.summarizer.prompt.PromptService;

@Service
public class SummarizerService {
  private final PromptService promptService;
  private final ChatGptServiceImpl chatGptService;
  private final MattermostService mattermostService;

  public SummarizerService(
      PromptService promptService,
      ChatGptServiceImpl chatGptService,
      MattermostService mattermostService
  ) {
    this.promptService = promptService;
    this.chatGptService = chatGptService;
    this.mattermostService = mattermostService;
  }

  public String getSummary(String threadId, String token) {
    return getSummary(threadId, token, promptService.getCommonPrompt());
  }

  public String getSummary(String threadId, String token, String prompt) {
    List<Message> mmThreadMessages = mattermostService.getMessagesByThread(threadId, token);
    String summarizedText = "";
    List<String> wrappedMessageBatches = promptService.getWrappedMessageBatches(mmThreadMessages);
    for (String batch: wrappedMessageBatches) {
      summarizedText = "%s %s".formatted(
          summarizedText,
          summarizeSingleBatch(batch, prompt)
      );
    }

    return summarizedText;
  }

  private String summarizeSingleBatch(String batch, String prompt) {
    String fullPrompt = "%s %s".formatted(prompt, batch);

    return chatGptService.chatCompletion(fullPrompt)
        .choices.get(0)
        .message
        .content;
  }

  public String getSummaryByPrompt(String lastSummary, String prompt) {
    return chatGptService.chatCompletion("%s %s".formatted(prompt, lastSummary))
            .choices.get(0)
            .message
            .content;
  }

}
