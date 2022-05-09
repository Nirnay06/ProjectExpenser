import { Menu, Dropdown, Avatar } from 'antd';
import { UserOutlined } from '@ant-design/icons';
import icons from '../../assets/sprite.svg';
import { useContext, useState } from 'react';
import { Link } from 'react-router-dom';
import AuthContext from '../../store/AuthContext';
import useServices from '../../hooks/useSevices';

const HeaderUser = (props) => {
    const USERNAME = 'nirnay Mittal';
    const { AuthenticationService } = useServices();
    const [isVisible, setVisible] = useState({ visible: false });
    const authCtx = useContext(AuthContext);
    // handleMenuClick = (e) => {
    //     if (e.key === "3") {
    //         this.setState({ visible: false });
    //     }
    // };

    const handleVisibleChange = (flag) => {
        setVisible({ visible: flag })
    };
    const menu = (
        <Menu selectable className='dropdown'>
            <Menu.Item>
                <a href='#' className='dropdown__link'>
                    <svg className='dropdown__icon'>
                        <use href={`${icons}#icon-ticket`}></use>
                    </svg>
                    <p className='dropdown__text'>Add Voucher</p>
                </a>
            </Menu.Item>
            <Menu.Item>
                <Link to="/upgrade" className='dropdown__link'>
                    <svg className='dropdown__icon'>
                        <use href={`${icons}#icon-star-full`}></use>
                    </svg>
                    <p className='dropdown__text'>Upgrade</p>
                </Link>
            </Menu.Item>
            <Menu.Item >
                <Link to="/settings" className='dropdown__link'>
                    <svg className='dropdown__icon'>
                        <use href={`${icons}#icon-cogs`}></use>
                    </svg>
                    <p className='dropdown__text'>Settings</p>
                </Link>
            </Menu.Item>
            <Menu.Item>
                <Link to="/login" className='dropdown__link' onClick={AuthenticationService.logoutHandler}>
                    <svg className='dropdown__icon'>
                        <use href={`${icons}#icon-exit`}></use>
                    </svg>
                    <p className='dropdown__text'>Logout</p>
                </Link>
            </Menu.Item>
        </Menu>
    );
    return (
        <div className={props.className}>
            <Dropdown overlay={menu} trigger='click' placement='top'>
                <div onClick={(e) => { e.preventDefault() }}>
                    <Avatar style={{ width: 'none', height: 'none', lineHeight: 'none' }} size={64}
                        icon={<UserOutlined />} shape='circle' />
                    <p className='user__name'>{USERNAME}</p>
                    <svg className='user__icon'>
                        <use href={`${icons}#icon-caret-down`}></use>
                    </svg>
                </div>
            </Dropdown>


        </div>
    )
}

export default HeaderUser;