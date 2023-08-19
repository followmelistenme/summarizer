import { jsx as _jsx } from "react/jsx-runtime";
import classNames from 'classnames';
import { createUseStyles } from "react-jss";
var useStyles = createUseStyles({
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
});
export var AutoHeight = function (_a) {
    var _b;
    var children = _a.children, _c = _a.hidden, hidden = _c === void 0 ? true : _c;
    var classes = useStyles();
    return (_jsx("div", { className: classNames(classes.autoHeight, (_b = {},
            _b[classes.hidden] = hidden,
            _b)), children: children }));
};
