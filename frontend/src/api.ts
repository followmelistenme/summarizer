import { MessageType } from './types'

function sleep(ms: number) {
  return new Promise(resolve => setTimeout(resolve, ms));
}

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

export const summarizeThread = async (data: ThreadData) => {
  await sleep(500)
  // TODO: Send data here
  mockedMessages.push({
    id: 0,
    text: 'This is the initial thread',
    isUser: true,
 })
 mockedMessages.push({
  id: 1,
  text: 'The first summary of the initial thread',
  isUser: false,
})
  return {
      chatId: 0,
      messages: mockedMessages
    }
}

export const promtThread = async (data: PromtData) => {
  await sleep(500)
  // TODO: Send data here
  mockedMessages.push({
    id: mockedId++,
    text: data.promt,
    isUser: true,
  })
  mockedMessages.push({
    id: mockedId++,
    text: `This is the result of the previous promt: ${data.promt}`,
    isUser: false,
  })
  return {
    messages: [...mockedMessages],
  }
}