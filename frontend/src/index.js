import { jsx as _jsx } from "react/jsx-runtime";
import { createRoot } from 'react-dom/client';
import './styles.less';
import App from './App';
var domNode = document.getElementById('app');
var root = createRoot(domNode);
root.render(_jsx(App, {}));
