import { ChangeEvent, useState } from 'react'
import { Button, TextField } from '@mui/material';
import { createUseStyles } from 'react-jss';

const useStyles = createUseStyles({
    app: {
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        gap: '15px',
    },
})

const App = () => {
    const classes = useStyles()
    const [userToken, setToken] = useState<string>()
    const [threadLink, setThreadLink] = useState<string>()

    const handleChangeToken = (event: ChangeEvent<HTMLInputElement>) => {
        setToken(event.target.value)
    }

    const handleChangeThreadLink = (event: ChangeEvent<HTMLInputElement>) => {
        setThreadLink(event.target.value)
    }

    const handleSubmit = () => {
        console.log('22222')
    }

    return (
        <div className={classes.app}>
            <TextField label="MM Token" variant="filled" value={userToken} onChange={handleChangeToken} />
            <TextField label="Thread Link" variant="filled" value={threadLink} onChange={handleChangeThreadLink} />
            <TextField label="Promt" variant="filled" />
            <Button onClick={handleSubmit} variant="contained">
                Summarize
            </Button>
        </div>
    )
}

export default App;
