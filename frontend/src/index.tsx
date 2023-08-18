import { createRoot } from 'react-dom/client'
import './styles.less'

import App from './App';

const domNode = document.getElementById('app');
const root = createRoot(domNode);
root.render(<App />);
