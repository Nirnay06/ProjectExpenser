import HeaderLink from "./HeaderLink";

const HeaderLinks = (props) => {
    const DUMMY_LINKS = [
        { title: 'Dashboard', path: '/dashboard', icon: 'home' },
        { title: 'Accounts', path: '/accounts', icon: 'calculator' },
        { title: 'Records', path: '/records', icon: 'database' },
        { title: 'Analytics', path: '/analytics', icon: 'pie-chart' },
        { title: 'Imports', path: '/imports', icon: 'download' },
        { title: 'Wallet Life', path: '/wallet-life', icon: 'lifebuoy' }
    ];

    return (
        <div className={props.className}> {
            DUMMY_LINKS.map((link, index) => <HeaderLink link={link} key={index} />)
        }
        </div>
    )
}
export default HeaderLinks;