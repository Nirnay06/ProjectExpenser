import { NavLink } from 'react-router-dom';
import icons from './../../assets/sprite.svg';
const HeaderLink = (props) => {
    return (
        <NavLink activeClassName='link__active' className="link" to={props.link.path} key={props.title}>
            {props.link.icon && <svg className='link__icon'>
                <use href={`${icons}#icon-${props.link.icon}`}></use>
            </svg>}
            <div className="link__title">
                <h3 className="link__text">{props.link.title}</h3>
            </div>
        </NavLink >
    )
}

export default HeaderLink;