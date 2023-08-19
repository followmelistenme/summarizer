import { get, useForm } from "react-hook-form";
import { useState } from 'react'
import { createUseStyles } from 'react-jss';
import { CircularProgress } from '@mui/material';

import { summarizeThread, promtThread } from './api'

import { Chat } from './components/Chat'
import { Button } from './components/Button';
import { TextField } from './components/TextField'
import { AutoHeight } from './components/Autoheight'
import Error from './components/Error';
import FileUploader from './components/FileUploader';
import PromptSelect from './components/PromptSelect';

import { MessageType } from "./types";

const useStyles = createUseStyles({
    app: {
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        gap: '15px',
        margin: '40px',
        height: 'calc(100vh - 80px)',
        overflow: 'visible',
    },
})

const getThreadId = (link: string) => {
    return link.substring(link.lastIndexOf('/') + 1);
};

const App = () => {
    const classes = useStyles()
    const { register, handleSubmit, formState, resetField, setValue, getValues } = useForm();
    const [chatId, setChatId] = useState<number>(null)
    const [chatMessages, setChatMessages] = useState<MessageType[]>([])
    const [error, setError] = useState('');

    const onSubmit = async (data: any) => {
        if (error) {
            setError('');
        }

        if (data.threadLink) {
            data.threadLink = getThreadId(data.threadLink)
        }

        if (chatId == null) {
            let threadResponse;

            try {
                threadResponse = await summarizeThread(data);
            } catch (e) {
                setError(e);
                return;
            }

            const { chatId, messages } = threadResponse;

            setChatId(chatId)
            setChatMessages(messages)
            return
        }
        
        setChatMessages((messages) => ([...messages, { id: 999, text: data.promt, isUser: true }]))
        resetField('promt')

        let promtResponse;

        try {
            promtResponse = await promtThread({
                chatId,
                promt: data.promt,
            });
        } catch (e) {
            setError(e);
            return;
        }

        const { messages } = promtResponse;

        setChatMessages(messages);
    }

    return (
        <form className={classes.app} onSubmit={handleSubmit(onSubmit)}>
            <div className="logo" />
            <TextField
                label="MM Token"
                variant="outlined"
                fullWidth
                {...register("userToken")}
                disabled={!!chatId}
            />
            <TextField
                label="Thread Link"
                variant="outlined"
                fullWidth
                {...register("threadLink")}
                disabled={!!chatId || !!getValues().video}
            />
            <FileUploader
                setValue={setValue}
                disabled={!!chatId || !!formState.dirtyFields.threadLink}
                {...register("video")}
            /> 
            <AutoHeight hidden={chatId == null}>
                <Chat messages={chatMessages} />
                <PromptSelect label="Promt" variant="outlined" fullWidth {...register("prompt")} />
            </AutoHeight>
            {error && <Error>{error}</Error>}
            <Button type="submit" size="large" variant="contained" fullWidth disabled={formState.isSubmitting}>
                {formState.isSubmitting ? 
                    <CircularProgress size={26} color="inherit" /> : <>Summarize</>
                }
            </Button>
        </form>
    )
}

export default App;
