var __assign = (this && this.__assign) || function () {
    __assign = Object.assign || function(t) {
        for (var s, i = 1, n = arguments.length; i < n; i++) {
            s = arguments[i];
            for (var p in s) if (Object.prototype.hasOwnProperty.call(s, p))
                t[p] = s[p];
        }
        return t;
    };
    return __assign.apply(this, arguments);
};
import { jsx as _jsx } from "react/jsx-runtime";
import { forwardRef } from 'react';
import { TextField as MuiTextField } from '@mui/material';
import { createUseStyles } from 'react-jss';
var useStyles = createUseStyles({
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
});
export var TextField = forwardRef(function (props, ref) {
    var classes = useStyles();
    return (_jsx(MuiTextField, __assign({}, props, { ref: ref, className: classes.root })));
});
