import React, { forwardRef, useState, FC } from 'react';
import { CircularProgress } from '@mui/material';

import { UseFormSetValue, FieldValues } from 'react-hook-form'
import { Button } from './Button';

import { createUseStyles } from 'react-jss';

const useStyles = createUseStyles({
    video: {
        width: '100%',
        boxSizing: 'border-box',
        borderRadius: '16px',
    },
    container: {
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'baseline',
        width: '100%',
        maxWidth: '500px',
        gap: '12px',
        marginRight: 'auto'
    }
});

interface Props {
    setValue: UseFormSetValue<FieldValues>;
    disabled: boolean;
};

// @ts-ignore
const FileUploader: FC<Props> = forwardRef(({ setValue, disabled, ref, ...props }) => {
    const [dataUrl, setDataUrl] = useState<string>('');
    const [isLoading, setIsLoading] = useState<boolean>(false);
    const styles = useStyles();

    const handleFileChange = async (e: React.ChangeEvent<HTMLInputElement>) => {
        if (e.target.files) {
            const file = e.target.files[0];

            // @ts-ignore
            props.onChange(e);
            setValue('video', file);
            setIsLoading(true);

            const fileReader = new FileReader();

            fileReader.readAsDataURL(file);
            fileReader.onload = e => {
                setDataUrl(e.target.result as string);
                setIsLoading(false);
            };
        }
    };

    return (
        <div className={styles.container}>
            {/* @ts-ignore */}
            <Button variant="contained" component="label" disabled={disabled} >
                {isLoading ? <CircularProgress size={26} color="inherit" /> : <>Upload Video</>}
                <input ref={ref} hidden accept='video/*' type="file" {...props} onChange={handleFileChange} />
            </Button>
            {dataUrl && !isLoading && <video className={styles.video} controls src={dataUrl}></video>}
        </div>
    )
})

export default FileUploader;
