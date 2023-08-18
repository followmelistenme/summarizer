import React, { forwardRef } from 'react'
import { TextField as MuiTextField } from '@mui/material';
import { ComponentProps, FC } from 'react';
import { createUseStyles } from 'react-jss';

const useStyles = createUseStyles({
  root: {
    background: '#fff',
    borderRadius: '16px',

    '& .MuiFormLabel-root.Mui-focused ': {
        color: 'var(--accent)',
    },

    '& .Mui-focused .MuiOutlinedInput-notchedOutline': {
      borderRadius: '16px',
      borderColor: 'var(--accent)',
    },
    '& :not(.Mui-focused) .MuiOutlinedInput-notchedOutline': {
      border: 0,
    },

    '& .MuiInputBase-input': {
      '&:-webkit-autofill, &:-webkit-autofill:hover, &:-webkit-autofill:focus, &:-webkit-autofill:active': {
          transition: 'background-color 0s 600000s, color 0s 600000s',
        },
    },
  },
})

export const TextField: FC<ComponentProps<typeof MuiTextField>> = forwardRef<HTMLInputElement, ComponentProps<typeof MuiTextField>>((props, ref) => {
  const classes = useStyles()
  return (
    <MuiTextField {...props} ref={ref} className={classes.root} />
  )
})