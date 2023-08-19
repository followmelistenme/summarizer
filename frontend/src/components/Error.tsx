
import { FC } from 'react';
import Alert from '@mui/material/Alert';
import AlertTitle from '@mui/material/AlertTitle';

import { createUseStyles } from 'react-jss';

const useStyles = createUseStyles({
    root: {
        width: '100%',
        boxSizing: 'border-box',
        borderRadius: '16px',
    },
})

const Error: FC = ({ children }) => {
    const styles = useStyles();

    return (
        <Alert severity="error" className={styles.root}>
            <AlertTitle>Error</AlertTitle>
            {children}
        </Alert>
    )
}

export default Error;
