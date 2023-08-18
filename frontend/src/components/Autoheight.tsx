import React from 'react'
import classNames from 'classnames';
import { FC } from "react";
import { createUseStyles } from "react-jss";

interface Props {
  hidden: boolean
}

const useStyles = createUseStyles({
  autoHeight: {
    transition: 'height 0.5s, opacity 0.5s',
    opacity: 1,
    height: '100%',
    width: '100%',
    flexDirection: 'column',
    display: 'flex',
    gap: '15px',
    overflow: 'hidden',
  },
  hidden: {
    height: 0,
    opacity: 0,
  },
})

export const AutoHeight: FC<Props> = ({ children, hidden = true }) => {
  const classes = useStyles()
  return (
    <div className={classNames(classes.autoHeight, {
      [classes.hidden]: hidden,
    })}>
      {children}
    </div>
  )
}