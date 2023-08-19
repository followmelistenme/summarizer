package ru.hh.summarizer;

import java.util.List;
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
    List<Message> mmThreadMessages = mattermostService.getMessagesByThread(threadId, token);
    String summarizedText = "";
    List<String> wrappedMessageBatches = promptService.getWrappedMessageBatches(mmThreadMessages);
    for (String batch: wrappedMessageBatches) {
      summarizedText = "%s %s".formatted(
          summarizedText,
          chatGptService.chatCompletion(promptService.getCommonPrompt(batch))
              .choices.get(0)
              .message
              .content
      );
    }

    return summarizedText;
  }

  public String getSummaryByPrompt(String lastSummary, String prompt) {
    return chatGptService.chatCompletion("%s %s".formatted(prompt, lastSummary))
            .choices.get(0)
            .message
            .content;
  }

}
