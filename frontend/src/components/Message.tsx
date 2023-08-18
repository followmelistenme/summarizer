import React from 'react'
import { FC } from "react"
import { createUseStyles } from 'react-jss'
import classNames from 'classnames'

type Props = {
  float?: 'left' | 'right'
}

const useStyles = createUseStyles({
  root: {
    padding: '8px 12px',
    background: '#4a86ff',
    borderRadius: '20px',
    color: '#fff',
  },
  left: {
    alignSelf: 'start',
    background: '#d9e5fd',
    color: '#000'
  },
  right: {
    alignSelf: 'end',
  },
})

export const Message: FC<Props> = ({ children, float = 'left' }) => {
  const classes = useStyles()
  return (
    <div className={classNames(classes.root, classes[float])}>
      {children}
    </div>
  )
}