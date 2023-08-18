package ru.hh.summarizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.hh.summarizer.chatgptimplementation.ChatGptServiceImpl;
import ru.hh.summarizer.entity.TestEntity;
import ru.hh.summarizer.service.TestService;

@RestController
@RequestMapping("/test")
public class TestController {

  private final TestService testService;
  private final ChatGptServiceImpl serviceImpl;

  @Autowired
  public TestController(TestService testService, ChatGptServiceImpl serviceImpl) {
    this.testService = testService;
    this.serviceImpl = serviceImpl;
  }

  @RequestMapping("/hello")
  public Iterable<TestEntity> hello() {
    return testService.findAllTest();
  }

  @RequestMapping(method = RequestMethod.GET, path = "/gpt_hello")
  public String gptHello() {
    return serviceImpl.chatCompletion("скажи привет на 10 самых популярных языках через запятую");
  }
}
