import Card from "../components/UI/Card";
import logo from './../assets/expenser.png';
import { Menu, Dropdown, message } from 'antd';
import { DownOutlined } from '@ant-design/icons';

const LoginLayout = (props) => {

    const onClick = ({ key }) => {
        message.info(`The feature is not currently available.`);
    };

    const menu = (
        <Menu onClick={onClick}>
            <Menu.Item key="English">English (UK)</Menu.Item>
            <Menu.Item key="Dutch">Dutch</Menu.Item>
            <Menu.Item key="German">German</Menu.Item>
        </Menu>
    );
    return (
        <>

            <div className="loginLayout">
                <div className="login__header">
                    <Dropdown overlay={menu}>
                        <a className="ant-dropdown-link" onClick={e => e.preventDefault()}>
                            English (UK) <DownOutlined />
                        </a>
                    </Dropdown>,
                </div>
                <div className="loginLayout__logo">

                    <img src={logo} className="loginLayout__logo__img" alt="logo"></img>
                    <div className="loginLayout__logo__title">
                        <h2 className="loginLayout__logo__title-main">Expenser</h2>
                        <h4 className="loginLayout__logo__title-secondary">By @Myself</h4>
                    </div>
                </div>
                <Card className="Login-box">
                    {props.children}
                </Card>
            </div>
        </>
    )
}

export default LoginLayout;