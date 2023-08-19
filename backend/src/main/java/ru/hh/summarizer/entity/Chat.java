package ru.hh.summarizer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "chat")
public class Chat {
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  @Column(name = "chat_id")
  @Getter
  private Long chatId;

  @OneToMany(mappedBy="chat")
  @Getter
  @Setter
  private List<ChatMessage> messages;

  public Chat() {}
}
