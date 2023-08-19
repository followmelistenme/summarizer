import { jsx as _jsx, jsxs as _jsxs } from "react/jsx-runtime";
import { useRef, useEffect } from 'react';
import { createUseStyles } from 'react-jss';
import { Message } from './Message';
var useStyles = createUseStyles({
    root: {
        display: 'flex',
        flexDirection: 'column',
        gap: '15px',
        border: '1px solid #eee',
        borderRadius: '16px',
        height: '100%',
        width: '100%',
        padding: '30px',
        boxSizing: 'border-box',
        background: '#fff',
        overflow: 'auto',
    },
});
export var Chat = function (_a) {
    var _b = _a.messages, messages = _b === void 0 ? [] : _b;
    var classes = useStyles();
    var messagesEnd = useRef(null);
    useEffect(function () {
        messagesEnd.current.scrollIntoView({ behavior: "smooth" });
    }, [messages]);
    return (_jsxs("div", { className: classes.root, children: [messages.map(function (message) { return (_jsx(Message, { float: message.isUser ? 'right' : 'left', animated: message.id === 999 || !message.isUser, children: message.text }, message.id)); }), _jsx("div", { style: { float: "left", clear: "both" }, ref: messagesEnd })] }));
};
