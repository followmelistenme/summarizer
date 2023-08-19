import { MessageType, ThreadResponseType, PromtResponseType } from './types'

interface ThreadData {
    userToken: string,
    link: string,
}

interface PromtData {
  prompt: string
  chatId: number
  userToken: string
}

const mockedMessages: MessageType[] = []
let mockedId = 2

const postData = async <T>(url: string, data: ThreadData | PromtData): Promise<T> => {
  let responseData: T;

  try {
    const response = await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data)
    });

    if (!response.ok)
      {
        throw new Error('Something went wrong.');  
      }

    responseData = await response.json()
  } catch (e) {
    throw e.toString();
  }

  return responseData;
    
}

export const summarizeThread = async (url: string, data: ThreadData) => {
  let response: ThreadResponseType;

  try {
    response = await postData<ThreadResponseType>(url, data);
  } catch (e) {
    throw e;
  }

  return {
      chatId: response.chatId,
      messages: response.messages,
    }
}

export const promtThread = async (url: string, data: PromtData) => {
  let response: PromtResponseType;

  try {
    response = await postData<PromtResponseType>(url, data);
  } catch (e) {
    throw e;
  }

  return {
    messages: response.messages,
  }
}
