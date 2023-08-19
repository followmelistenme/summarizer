package ru.hh.summarizer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "chat_message")
public class ChatMessage {

  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  @Column(name = "chat_message_id")
  @Getter
  private Long id;

  @Column(name = "creation_time")
  @Getter
  private LocalDateTime creationTime;

  @Column(name = "text", length = 100000)
  @Getter
  private String text;

  @Column(name = "is_user")
  @Getter
  private boolean isUser;

  @ManyToOne
  @JoinColumn(name="chat_id", nullable=false)
  @Getter
  @Setter
  private Chat chat;

  public ChatMessage() {
  }

  public ChatMessage(String text, boolean isUser) {
    this.text = text;
    this.isUser = isUser;
    this.creationTime = LocalDateTime.now();
  }
}
