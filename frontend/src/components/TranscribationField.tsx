import React, { forwardRef } from 'react'
import { TextareaAutosize } from '@mui/base/TextareaAutosize';
import InputLabel from '@mui/material/InputLabel';
import { ComponentProps, FC } from 'react';
import { createUseStyles } from 'react-jss';

const useStyles = createUseStyles({
  container: {
    width: '100%',
  },
  root: {
    background: '#fff',
    borderRadius: '8px',
    width: '100%',
    height: 'auto !important',
    minHeight: '20vh',
    overflow: 'scroll !important',
    boxSizing: 'border-box',

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

// @ts-ignore
const TranscribationField: FC<ComponentProps<typeof TextareaAutosize>> = forwardRef<HTMLInputElement, ComponentProps<typeof TextareaAutosize>>((props, ref) => {
  const classes = useStyles()
  return (
    <div className={classes.container}>
      <InputLabel>Введи транскрибированный текст</InputLabel>
      {/* @ts-ignore */}
      <TextareaAutosize {...props} ref={ref} className={classes.root} />
    </div>
  )
})

export default TranscribationField;
