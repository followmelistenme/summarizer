import React, { useRef, useEffect } from 'react'
import { FC } from "react"
import { createUseStyles } from 'react-jss'
import {Message} from './Message'
import { MessageType } from '../types'

type Props = {
  messages: MessageType[]
}

const useStyles = createUseStyles({
  root: {
    display: 'flex',
    flexDirection: 'column',
    gap: '15px',
    border: '1px solid #eee',
    borderRadius: '16px',
    height: '100%',
    width: '100%',
    padding: '30px',
    boxSizing: 'border-box',
    background: '#fff',
    overflow: 'auto',
  },
})

export const Chat: FC<Props> = ({ messages = [] }) => {
  const classes = useStyles()
  const messagesEnd = useRef<HTMLDivElement>(null)

  useEffect(() => {
    messagesEnd.current.scrollIntoView({ behavior: "smooth" });
  }, [messages])

  return (
    <div className={classes.root}>
      {
        messages.map((message) => (
          <Message key={message.id} float={message.isUser ? 'right' : 'left'} animated={message.id === 999 || !message.isUser}>
            {message.text}
          </Message>
        ))
      }
      <div
        style={{ float:"left", clear: "both" }}
        ref={messagesEnd}
      />
    </div>
  )
}