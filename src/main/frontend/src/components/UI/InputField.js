import { useContext, useEffect, useState } from "react";
import { FormContext } from './Form';
import styles from './../../styles/UI/form.module.scss';
import { Tooltip } from "antd";
import { InfoCircleTwoTone } from '@ant-design/icons';

const InputField = (props) => {
    const [isClicked, setClicked] = useState(false);
    const formContext = useContext(FormContext);
    const { form, setForm, errors, setErrors, initForm, setInitForm, validationFunction } = formContext;
    const {
        label,
        type = 'text',
        name,
        required = '',
        placeholder,
        info,
        disabled
    } = props;

    const handleFormChange = (event) => {
        onClickHandler();
        setForm({ ...form, [name]: event.target.value });
    };
    const onClickHandler = () => {
        setClicked(true)
        setInitForm(false);
    }

    useEffect(() => {
        if (initForm) {
            setClicked(false);
            setErrors({});
        }
        let timer = setTimeout(() => {
            let newErrors;
            if (required && (!form[name] || form[name].length < 0)) {
                newErrors = { [name]: `${name} is required` };
            }
            if (isClicked) {
                newErrors = { ...newErrors, ...validationFunction(form) };
            }
            if (name && newErrors) {
                setErrors((prev) => {
                    return { ...prev, [name]: newErrors[name] }
                });
            }
        }, 300);
        return () => { clearTimeout(timer) }
    }, [form, validationFunction, setErrors, isClicked, name, initForm, required]);

    return (
        <div className={`${styles.Form__Input} ${props.className} ${errors[name] && isClicked ? styles['Form__Input-invalid'] : null} ${disabled ? styles['Form__Input-disabled'] : null} `}>
            <label>
                {label}
                {required && <p>*</p>}
                {info && <Tooltip placement="right"
                    defaultVisible={false}
                    title={info}
                    arrowPointAtCenter
                    trigger="hover"
                    overlayInnerStyle={{ 'textAlign': 'Center' }}>
                    <InfoCircleTwoTone className={styles.info} />

                </Tooltip>}

            </label>

            <Tooltip placement="right"
                defaultVisible={false}
                title={errors[name]}
                arrowPointAtCenter
                trigger="hover"
                overlayInnerStyle={{ 'textAlign': 'Center' }}>

                {!props.children && <input
                    value={form[name]}
                    required={required}
                    name={name}
                    type={type}
                    placeholder={placeholder}
                    onChange={handleFormChange}
                    onBlur={onClickHandler}
                    disabled={disabled}
                ></input>}
            </Tooltip>
        </div>
    )
}

export default InputField;
