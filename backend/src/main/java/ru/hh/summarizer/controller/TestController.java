package ru.hh.summarizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.hh.summarizer.SummarizerService;
import ru.hh.summarizer.chatgptimplementation.ChatCompletion;
import ru.hh.summarizer.chatgptimplementation.ChatGptServiceImpl;
import ru.hh.summarizer.entity.TestEntity;
import ru.hh.summarizer.service.TestService;

@RestController
@RequestMapping("/test")
public class TestController {

  private final TestService testService;
  private final SummarizerService summarizerService;

  @Autowired
  public TestController(TestService testService, SummarizerService summarizerService) {
    this.testService = testService;
    this.summarizerService = summarizerService;
  }

  @RequestMapping("/hello")
  public Iterable<TestEntity> hello() {
    return testService.findAllTest();
  }

  @RequestMapping(method = RequestMethod.GET, path = "/summarize", params = {"threadId"})
  public String summarize(String threadId) {
    return summarizerService.getSummary(threadId);
  }
}
