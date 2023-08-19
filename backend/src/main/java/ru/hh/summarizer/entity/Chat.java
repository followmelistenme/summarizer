package ru.hh.summarizer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

import lombok.Builder;
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

  @Column(name = "thread_url")
  @Getter
  @Setter
  private String threadUrl;

  @Column(name = "token_url")
  @Getter
  @Setter
  private String userToken;

  @OneToMany(mappedBy="chat")
  @Getter
  @Setter
  private List<ChatMessage> messages;

  public Chat() {}
}
