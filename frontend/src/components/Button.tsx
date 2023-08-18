import React from 'react'
import { Button as MuiButton } from '@mui/material';
import { ComponentProps, FC } from 'react';
import { createUseStyles } from 'react-jss';

const useStyles = createUseStyles({
  root: {
    borderRadius: '16px',
    background: 'var(--accent)',
    '&:hover': {
      background: 'var(--accent-dark)',
    },
    '&, &:hover': {
      boxShadow: 'none',
    },
  },
})

export const Button: FC<ComponentProps<typeof MuiButton>> = (props) => {
  const classes = useStyles()
  return (
    <MuiButton {...props} className={classes.root} />
  )
}