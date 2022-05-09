import Header from "../components/Header/Header";
import styles from './../styles/Layouts/dashboard.module.scss';
const DashboardLayout = (props) => {
    return (
        <>
            <Header />
            <div className={styles.dashboard}>
                {props.children}
            </div>
        </>
    )
}
export default DashboardLayout;

export const ButtonHeader = (props) => {
    return (
        <div className={styles.dashboard__header}>
            {props.children}
        </div>
    )
}

export const DropdownHeader = (props) => {
    return (
        <div className={styles.dashboard__datePicker}>
            {props.children}
        </div>
    )
}
export const DropdownHeaderItem = (props) => {
    return (<div className={styles.dropdownHeader__item}>{props.children}</div>)
}

export const GridContent = (props) => {
    return (
        <div className={styles.dashboard__content}>
            {props.children}
        </div>
    )
}

export const SidebarContent = (props) => {
    return (
        <div className={styles.sidebar__content}>
            {props.children}
        </div>
    )
}

export const SidebarContainer = (props) => {
    return (
        <div className={styles.sidebar__container}>
            {props.children}
        </div>
    )
}

export const MainContent = (props) => {
    return (
        <div className={`${styles.main__content} ${props.className} ${props.gridRow ? styles.gridRow : ''}`}>
            {props.children}
        </div>
    )
}

export const ContentRow = (props) => {
    return (
        <div className={`${styles.content__row} ${props.className}`}>{props.children}</div>
    )
}