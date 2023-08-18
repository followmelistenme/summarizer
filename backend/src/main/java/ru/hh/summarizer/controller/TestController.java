package ru.hh.summarizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hh.summarizer.entity.TestEntity;
import ru.hh.summarizer.service.TestService;

@RestController
@RequestMapping("/test")
public class TestController {

  private final TestService testService;

  @Autowired
  public TestController(TestService testService) {
    this.testService = testService;
  }

  @RequestMapping("/hello")
  public Iterable<TestEntity> hello() {
    return testService.findAllTest();
  }

}
