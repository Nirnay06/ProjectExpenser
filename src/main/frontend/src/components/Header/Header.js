import { Link } from 'react-router-dom';
import HeaderButtons from './HeaderButtons';
import HeaderLinks from './HeaderLinks';
import HeaderUser from './HeaderUser';
import logo from './../../assets/expenser.png';
const Header = () => {
    return (
        <div className="page__header">
            <Link to="/"><img src={logo} alt="expenser" className='page__header--logo' /></Link>
            <HeaderLinks className='page__header--links'></HeaderLinks>
            <HeaderButtons className='page__header--buttons'></HeaderButtons>
            <HeaderUser className='page__header--user'></HeaderUser>
        </div>
    )
}

export default Header;