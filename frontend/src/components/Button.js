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
import { Button as MuiButton } from '@mui/material';
import { createUseStyles } from 'react-jss';
var useStyles = createUseStyles({
    root: {
        borderRadius: '16px',
        background: 'var(--accent)',
        textTransform: 'none',
        '&:hover': {
            background: 'var(--accent-dark)',
        },
        '&, &:hover': {
            boxShadow: 'none',
        },
    },
});
export var Button = function (props) {
    var classes = useStyles();
    return (_jsx(MuiButton, __assign({}, props, { className: classes.root })));
};
