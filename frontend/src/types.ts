
export interface MessageType {
  id: number,
  text: string,
  isUser: boolean,
}

export interface ThreadResponseType {
  chatId: number;
  messages: MessageType[]
}

export interface PromtResponseType {
  messages: MessageType[]
}
