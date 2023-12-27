import ReactDOM from 'react-dom';
import styles from './../../styles/UI/modal.module.scss';
import icons from './../../assets/sprite.svg';
import { Modal } from 'antd';
const Backdrop = (props) => {
    const onBackDropClickHandler = (e) => {
        if (e.target.className === styles.backdrop) {
            props.onCloseModal();
        }
    }

    return (<div className={styles["backdrop"]} onClick={onBackDropClickHandler} >
        {props.children}
    </div>)
}
const ModalBox = (props) => {
    const { headerTitle = 'title', showCloseButton = true, headerButtons } = props;
    return (
        <div className={styles.modal} style={props.style}>
            <div className={styles.modal__header}>
                {headerTitle ? <div className={styles.modal__header__title}>{headerTitle}</div> : <div></div>}
                {headerButtons && headerButtons}
                {showCloseButton ?
                    <svg className={styles.modal__header__close} onClick={props.onCloseModal}>
                        <use href={`${icons}#icon-close`}></use>
                    </svg> : <div></div>}
            </div>
            <div className={styles.modal__content}>
                {props.children}
            </div>
        </div>
    )
}
const CustomModal = (props) => {
    const { visible, onCancel, title, style, children, ...others } = props;
    return <>
        <Modal
            visible={visible}
            width={1000}
            keyboard={true}
            onCancel={onCancel}
            destroyOnClose={true}
            title={title}
            footer={null}
            zIndex={1000}
            style={{ borderRadius: "5px", overflow: "hidden", top: '20px', ...style }}
            {...others}
        >
            {children}
        </Modal></>
}

export default CustomModal;