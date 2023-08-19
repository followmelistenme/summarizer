package ru.hh.summarizer.mattermost;

public class Message {
  private final String sender;

  private final String text;

  public Message(String sender, String text) {
    this.sender = sender;
    this.text = text;
  }

  public static Message of(MMPost post, MMUser mmUser) {
    return new Message("%s %s".formatted(mmUser.firstName(), mmUser.lastName()), post.message());
  }

  public String getSender() {
    return sender;
  }

  public String getText() {
    return text;
  }
}
