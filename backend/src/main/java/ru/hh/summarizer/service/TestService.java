package ru.hh.summarizer.service;

import org.springframework.stereotype.Service;
import ru.hh.summarizer.entity.TestEntity;
import ru.hh.summarizer.dao.TestRepository;

@Service
public class TestService {

  private final TestRepository testRepository;

  public TestService(TestRepository testRepository) {
    this.testRepository = testRepository;
  }

  public Iterable<TestEntity> findAllTest() {
    return testRepository.findAll();
  }
}
