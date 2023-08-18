package ru.hh.summarizer.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.hh.summarizer.entity.TestEntity;

@Repository
public interface TestRepository extends CrudRepository<TestEntity, Integer> {
}
