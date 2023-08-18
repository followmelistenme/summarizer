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
    borderRadius: '20px',
  },
  left: {
    alignSelf: 'start',
    background: '#e0e0e0',
    color: '#000'
  },
  right: {
    alignSelf: 'end',
    color: '#fff',
    background: 'var(--accent)',
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