import { jsx as _jsx } from "react/jsx-runtime";
import { createUseStyles } from 'react-jss';
import classNames from 'classnames';
var useStyles = createUseStyles({
    root: {
        padding: '8px 12px',
        borderRadius: '20px',
    },
    animated: {
        animation: '$message 0.5s'
    },
    left: {
        alignSelf: 'start',
        background: '#e0e0e0',
        color: '#000'
    },
    right: {
        alignSelf: 'end',
        color: '#fff',
        background: 'var(--accent)',
    },
    '@keyframes message': {
        from: {
            opacity: 0,
            transform: 'translateY(10px)',
        },
        to: {
            opacity: 1,
            transform: 'translateY(0px)',
        }
    },
});
export var Message = function (_a) {
    var _b;
    var children = _a.children, _c = _a.float, float = _c === void 0 ? 'left' : _c, _d = _a.animated, animated = _d === void 0 ? false : _d;
    var classes = useStyles();
    return (_jsx("div", { className: classNames(classes.root, classes[float], (_b = {},
            _b[classes.animated] = animated,
            _b)), children: children }));
};
