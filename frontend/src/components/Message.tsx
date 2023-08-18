import React from 'react'
import { FC } from "react"
import { createUseStyles } from 'react-jss'
import classNames from 'classnames'

type Props = {
  float?: 'left' | 'right',
  animated?: boolean
}

const useStyles = createUseStyles({
  root: {
    padding: '8px 12px',
    borderRadius: '20px',
  },
  animated: {
    animation: '$message 0.5s'
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
  '@keyframes message': {
    from: {
      opacity: 0,
      transform: 'translateY(10px)',
    },
    to: {
      opacity: 1,
      transform: 'translateY(0px)',
    }
},
})

export const Message: FC<Props> = ({ children, float = 'left', animated = false }) => {
  const classes = useStyles()
  return (
    <div className={classNames(classes.root, classes[float], {
      [classes.animated]: animated,
    })}>
      {children}
    </div>
  )
}