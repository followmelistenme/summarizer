package ru.hh.summarizer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "test")
public class TestEntity {

  @Id
  @Column(name = "test_id")
  @Getter
  @Setter
  private Integer id;


}
