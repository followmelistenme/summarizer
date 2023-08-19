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
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __generator = (this && this.__generator) || function (thisArg, body) {
    var _ = { label: 0, sent: function() { if (t[0] & 1) throw t[1]; return t[1]; }, trys: [], ops: [] }, f, y, t, g;
    return g = { next: verb(0), "throw": verb(1), "return": verb(2) }, typeof Symbol === "function" && (g[Symbol.iterator] = function() { return this; }), g;
    function verb(n) { return function (v) { return step([n, v]); }; }
    function step(op) {
        if (f) throw new TypeError("Generator is already executing.");
        while (g && (g = 0, op[0] && (_ = 0)), _) try {
            if (f = 1, y && (t = op[0] & 2 ? y["return"] : op[0] ? y["throw"] || ((t = y["return"]) && t.call(y), 0) : y.next) && !(t = t.call(y, op[1])).done) return t;
            if (y = 0, t) op = [op[0] & 2, t.value];
            switch (op[0]) {
                case 0: case 1: t = op; break;
                case 4: _.label++; return { value: op[1], done: false };
                case 5: _.label++; y = op[1]; op = [0]; continue;
                case 7: op = _.ops.pop(); _.trys.pop(); continue;
                default:
                    if (!(t = _.trys, t = t.length > 0 && t[t.length - 1]) && (op[0] === 6 || op[0] === 2)) { _ = 0; continue; }
                    if (op[0] === 3 && (!t || (op[1] > t[0] && op[1] < t[3]))) { _.label = op[1]; break; }
                    if (op[0] === 6 && _.label < t[1]) { _.label = t[1]; t = op; break; }
                    if (t && _.label < t[2]) { _.label = t[2]; _.ops.push(op); break; }
                    if (t[2]) _.ops.pop();
                    _.trys.pop(); continue;
            }
            op = body.call(thisArg, _);
        } catch (e) { op = [6, e]; y = 0; } finally { f = t = 0; }
        if (op[0] & 5) throw op[1]; return { value: op[0] ? op[1] : void 0, done: true };
    }
};
var __spreadArray = (this && this.__spreadArray) || function (to, from, pack) {
    if (pack || arguments.length === 2) for (var i = 0, l = from.length, ar; i < l; i++) {
        if (ar || !(i in from)) {
            if (!ar) ar = Array.prototype.slice.call(from, 0, i);
            ar[i] = from[i];
        }
    }
    return to.concat(ar || Array.prototype.slice.call(from));
};
import { jsx as _jsx, jsxs as _jsxs, Fragment as _Fragment } from "react/jsx-runtime";
import { useForm } from "react-hook-form";
import { useState } from 'react';
import { createUseStyles } from 'react-jss';
import { CircularProgress } from '@mui/material';
import { summarizeThread, promtThread } from './api';
import { Chat } from './components/Chat';
import { Button } from './components/Button';
import { TextField } from './components/TextField';
import { AutoHeight } from './components/Autoheight';
import Error from './components/Error';
var useStyles = createUseStyles({
    app: {
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        gap: '15px',
        margin: '40px',
        height: 'calc(100vh - 80px)',
        overflow: 'visible',
    },
});
var App = function () {
    var classes = useStyles();
    var _a = useForm(), register = _a.register, handleSubmit = _a.handleSubmit, formState = _a.formState, resetField = _a.resetField;
    var _b = useState(null), chatId = _b[0], setChatId = _b[1];
    var _c = useState([]), chatMessages = _c[0], setChatMessages = _c[1];
    var _d = useState(''), responseError = _d[0], setError = _d[1];
    var onSubmit = function (data) { return __awaiter(void 0, void 0, void 0, function () {
        var threadResponse, e_1, chatId_1, messages_1, promtResponse, e_2, messages;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0:
                    if (responseError) {
                        setError('');
                    }
                    if (!(chatId == null)) return [3 /*break*/, 5];
                    threadResponse = void 0;
                    _a.label = 1;
                case 1:
                    _a.trys.push([1, 3, , 4]);
                    return [4 /*yield*/, summarizeThread(data)];
                case 2:
                    threadResponse = _a.sent();
                    return [3 /*break*/, 4];
                case 3:
                    e_1 = _a.sent();
                    setError(e_1);
                    return [2 /*return*/];
                case 4:
                    chatId_1 = threadResponse.chatId, messages_1 = threadResponse.messages;
                    setChatId(chatId_1);
                    setChatMessages(messages_1);
                    return [2 /*return*/];
                case 5:
                    setChatMessages(function (messages) { return (__spreadArray(__spreadArray([], messages, true), [{ id: 999, text: data.promt, isUser: true }], false)); });
                    resetField('promt');
                    _a.label = 6;
                case 6:
                    _a.trys.push([6, 8, , 9]);
                    return [4 /*yield*/, promtThread({
                            chatId: chatId,
                            promt: data.promt,
                        })];
                case 7:
                    promtResponse = _a.sent();
                    return [3 /*break*/, 9];
                case 8:
                    e_2 = _a.sent();
                    setError(e_2);
                    return [2 /*return*/];
                case 9:
                    messages = promtResponse.messages;
                    setChatMessages(messages);
                    return [2 /*return*/];
            }
        });
    }); };
    return (_jsxs("form", { className: classes.app, onSubmit: handleSubmit(onSubmit), children: [_jsx("div", { className: "logo" }), _jsx(TextField, __assign({ label: "MM Token", variant: "outlined", fullWidth: true }, register("userToken"))), _jsx(TextField, __assign({ label: "Thread Link", variant: "outlined", fullWidth: true }, register("link"))), _jsxs(AutoHeight, { hidden: chatId == null, children: [_jsx(Chat, { messages: chatMessages }), _jsx(TextField, __assign({ label: "Promt", variant: "outlined", fullWidth: true }, register("promt")))] }), responseError && _jsx(Error, { children: responseError }), _jsx(Button, { type: "submit", size: "large", variant: "contained", fullWidth: true, disabled: formState.isSubmitting, children: formState.isSubmitting ?
                    _jsx(CircularProgress, { size: 26, color: "inherit" }) : _jsx(_Fragment, { children: "Summarize" }) })] }));
};
export default App;
