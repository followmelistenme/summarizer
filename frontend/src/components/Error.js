import { jsx as _jsx, jsxs as _jsxs } from "react/jsx-runtime";
import Alert from '@mui/material/Alert';
import AlertTitle from '@mui/material/AlertTitle';
import { createUseStyles } from 'react-jss';
var useStyles = createUseStyles({
    root: {
        width: '100%',
        boxSizing: 'border-box',
        borderRadius: '16px',
    },
});
var Error = function (_a) {
    var children = _a.children;
    var styles = useStyles();
    return (_jsxs(Alert, { severity: "error", className: styles.root, children: [_jsx(AlertTitle, { children: "Error" }), children] }));
};
export default Error;
