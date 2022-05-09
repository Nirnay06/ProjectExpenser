import ReactDOM from 'react-dom';
import styles from './../../styles/UI/modal.module.scss';
import icons from './../../assets/sprite.svg';
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
        <div className={styles.modal}>
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
const Modal = (props) => {
    const overlayDiv = document.getElementById('modal');
    return (
        <>
            {ReactDOM.createPortal(<Backdrop onCloseModal={props.onCloseModal}><ModalBox {...props}>
                {/* {props.children} */}
            </ModalBox></Backdrop>, overlayDiv)
            }
        </>);
}

export default Modal;