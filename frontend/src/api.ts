import { MessageType, ThreadResponseType, PromtResponseType } from './types'

interface ThreadData {
    userToken: string,
    link: string,
}

interface PromtData {
  promt: string
  chatId: number
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
        "Access-Control-Allow-Origin": "*",
        "Accept": "application/json",
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

const THREADS_URL = 'http://localhost:5099/api/threads/thread';

export const summarizeThread = async (data: ThreadData) => {
  let response: ThreadResponseType;

    try {
      response = await postData<ThreadResponseType>(THREADS_URL, data);
    } catch (e) {
      throw e;
    }

    // mock
    // 
    // mockedMessages.push(...response.messages)
    // 
    // mockedMessages.push({
    //   id: 1,
    //   text: 'This is the initial thread',
    //   isUser: true,
    // })
  
    // mockedMessages.push({
    //   id: 2,
    //   text: 'The first summary of the initial thread',
    //   isUser: false,
    // })
  
  return {
      chatId: response.chatId,
      messages: response.messages,
    }
}

export const promtThread = async (data: PromtData) => {
  const PROMT_URL = `http://localhost:5099/api/threads/${data.chatId}/prompt`;
  let response: PromtResponseType;

    try {
      response = await postData<PromtResponseType>(PROMT_URL, data);
    } catch (e) {
      throw e;
    }

    // mock
  // mockedMessages.push({
  //   id: mockedId++,
  //   text: data.promt,
  //   isUser: true,
  // })
  // mockedMessages.push({
  //   id: mockedId++,
  //   text: `This is the result of the previous promt: ${data.promt}`,
  //   isUser: false,
  // })

  return {
    messages: response.messages,
  }
}
