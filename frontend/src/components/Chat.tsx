import React from 'react'
import { FC } from "react"
import { createUseStyles } from 'react-jss'
import {Message} from './Message'

type Props = {}

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
  },
})

export const Chat: FC<Props> = ({ children }) => {
  const classes = useStyles()
  return (
    <div className={classes.root}>
      {children}
      <Message>
         Summary
      </Message>
      <Message float="right">
        Promt
      </Message>
    </div>
  )
}