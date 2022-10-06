import { Button, Tooltip } from 'antd';
import React, { useState } from 'react';
import styles from './../../styles/UI/form.module.scss';

export const FormContext = React.createContext({
    form: {}
});

function Form(props) {
    const { children, submit = () => { },
        initialValues, validationFunction = () => { },
        buttonText = 'Submit', cancel, cancelText = 'Cancel',
        buttonContainerClass, } = props;
    const [form, setForm] = useState(initialValues);
    const [errors, setErrors] = useState({});
    const [initForm, setInitForm] = useState(true);
    const hasAnyError = Object.values(errors).filter(v => v).length > 0;
    const submitFormHandler = () => {
        submit(form);
        setForm(initialValues);
        setErrors({});
        setInitForm(true);
    }
    const onEnterPress = (e) => {
        if (e.key === 'Enter') {
            submitFormHandler();
        }
    }
    return (
        <form className={`${styles.Form} ${props.className}`} onKeyDown={onEnterPress} style={props.style}>
            <FormContext.Provider value={{
                form,
                setForm,
                errors,
                setErrors,
                initForm,
                setInitForm,
                validationFunction,
            }}>
                {children}
            </FormContext.Provider>
            <div className={[styles.Form__buttons, styles[`Form__buttons${cancel ? '__2' : '__1'}`], buttonContainerClass].join(' ')}>
                {
                    cancel && <Button block type="default" onClick={cancel} shape="round" size='large'>
                        {cancelText}
                    </Button>
                }
                {(hasAnyError || initForm) && <Tooltip placement="right"
                    defaultVisible={false}
                    title="Required fields missing"
                    arrowPointAtCenter
                    trigger="hover"
                    overlayInnerStyle={{ 'textAlign': 'Center' }}>
                    <Button type="primary" block onClick={submitFormHandler} shape="round" size='large'
                        disabled='disabled'>
                        {buttonText}
                    </Button>
                </Tooltip>}
                {
                    !(hasAnyError || initForm) && <Button block type="primary" onClick={submitFormHandler} shape="round" size='large'>
                        {buttonText}
                    </Button>
                }

            </div>
        </form >
    );
}

export default Form;