import React, { forwardRef } from 'react'
import Select from '@mui/material/Select';
import MenuItem from '@mui/material/MenuItem';
import InputLabel from '@mui/material/InputLabel';
import { ComponentProps, FC } from 'react';
import { createUseStyles } from 'react-jss';

import { PROPMPT_DICK } from '../constants/promptDick';

const useStyles = createUseStyles({
  root: {
    background: '#fff',
    borderRadius: '16px',
    border: 'none',

    '& .MuiOutlinedInput-notchedOutline ': {
      border: 'none',
    },

    '& .Mui-focused .MuiOutlinedInput-notchedOutline ': {
      border: '2px solid red',
    },
    
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
const PromptSelect: FC<ComponentProps<typeof Select>> = forwardRef<HTMLInputElement, ComponentProps<typeof Select>>((props, ref) => {
  const classes = useStyles()
  return (
    <>
      <InputLabel>Выберите уточняющий вопрос</InputLabel>
      <Select {...props} ref={ref} className={classes.root} label="Выберите уточняющий вопрос">
        {PROPMPT_DICK.map(item => (
          <MenuItem key={item} value={item}>{item}</MenuItem>
        ))}
      </Select>
    </>
  )
})

export default PromptSelect;
