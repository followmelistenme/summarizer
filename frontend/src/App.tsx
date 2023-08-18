import { useForm } from "react-hook-form";
import { createUseStyles } from 'react-jss';
import { Chat } from './components/Chat'
import { Button } from './components/Button';
import { TextField } from './components/TextField'

const useStyles = createUseStyles({
    app: {
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        gap: '15px',
        margin: '40px',
        height: 'calc(100vh - 80px)',
    },
})

const App = () => {
    const classes = useStyles()
    const { register, handleSubmit } = useForm();

    const onSubmit = (data: any) => {
        console.log(data)
    }

    return (
        <form className={classes.app} onSubmit={handleSubmit(onSubmit)}>
            <TextField label="MM Token" variant="outlined" fullWidth {...register("token")} />
            <TextField label="Thread Link" variant="outlined" fullWidth {...register("link")} />
            <Chat />
            <TextField label="Promt" variant="outlined" fullWidth {...register("promt")} />

            <Button type="submit" size="large" variant="contained" fullWidth>
                Summarize
            </Button>
        </form>
    )
}

export default App;
