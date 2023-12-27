import { NavLink } from "react-router-dom/cjs/react-router-dom";
import styles from './Settings.module.scss';

const SettingSidebar = () => {
    const walletSettingOptions = [{ name: 'Accounts', link: '/accounts' },
    { name: 'Categories', link: '/category' },
    { name: 'Currencies', link: '/currency' },
    { name: 'Templates', link: '/template' },
    { name: 'Labels', link: '/labels' },
    { name: 'Automatic Rules', link: '/rules' }
    ];

    const generalSettingOptions = [{ name: 'Billing', link: '/billing' },
    { name: 'General', link: '/general' },
    { name: 'Personal data and privacy', link: '/privacy' },
    { name: 'Help', link: '/help' },
    ];


    return <>
        <h1 className="font20">Settings</h1>
        <div className="offsetL5">

            <h2 className="font15 offsetB15">Wallet Settings</h2>
            <div className={`${styles.Settings_tabs} offsetL5`}>
                {walletSettingOptions.map((option, index) => {
                    return <NavLink className={styles.Settings_tab} to={`/settings${option.link}`}>{option.name}</NavLink>
                })}
            </div>
            <h2 className="font15 offsetB15 offsetT20">General Settings</h2>

            <div className={`${styles.Settings_tabs} offsetL5`}>
                {generalSettingOptions.map((option, index) => {
                    return <NavLink className={styles.Settings_tab} to={`/settings${option.link}`}>{option.name}</NavLink>
                })}
            </div>
        </div>
    </>;
}

export default SettingSidebar;