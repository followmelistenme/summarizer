package ru.hh.summarizer.mattermost;

public class Message {
  private final String sender;

  private final String text;

  public Message(String sender, String text) {
    this.sender = sender;
    this.text = text;
  }

  public static Message of(MMPost post) {
    return new Message(post.userId(), post.message());
  }

  public String getSender() {
    return sender;
  }

  public String getText() {
    return text;
  }
}
