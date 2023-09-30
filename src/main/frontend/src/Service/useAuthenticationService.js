import useHttp from "../hooks/useHttp";
import { message } from 'antd';
import { validEmail, validName, validPassword } from "../utils/RegexUtil";
import { useHistory } from "react-router-dom";
import { useContext } from "react";
import AuthContext from "../store/AuthContext";
import UserContext from "../store/UserContext";
const useAuthenticationService = () => {
    const { sendRequest } = useHttp();
    const history = useHistory();
    const authCtx = useContext(AuthContext);
    const userCtx = useContext(UserContext);
    const createUser = (data) => {
        try {
            formattingSignupData(data);
            userSignupDataValidation(data);
            sendRequest({
                url: '/api/auth/signup',
                method: 'POST',
                headers: {
                    "Content-Type": "application/json",
                }, body: data
            }, (data) => {
                message.success(data.message);
                redirectToLoginPage();
            })
        }
        catch (e) {
            message.error(e.message);
        }
    }

    const userSignupDataValidation = (userData) => {
        if (!userData.username) {
            throw new Error('First Name cannot be blank');
        }
        if (!validName.test(userData.firstname || !validName.test(userData.lastname))) {
            throw new Error('Only alphanumeric characters are allowed in the name')
        }
        if (!validEmail.test(userData.username)) {
            throw new Error('Invalid username entered');
        }
        if (!validPassword.test(userData.password)) {
            throw new Error('Pasword should have minimum 8 characters, at least 1 uppercase letter, 1 lowercase letter, 1 number and 1 special character');
        }
    }

    const formattingSignupData = (userData) => {
        userData.firstname = userData.firstname.trim();
        userData.lastname = userData.lastname.trim();
        userData.username = userData.username.trim();
    }
    const redirectToLoginPage = () => {
        setTimeout(() => {
            message.loading('Redirecting to login page.')
        }, 1000);
        setTimeout(() => {
            history.push('/login');
        }, 4000);
    }
    const sendConfirmationMail = (data) => {
        sendRequest({
            url: '/api/auth/sendConfirmationLink',
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
            }, body: { 'username': data.username }
        }, (response) => {
            message.info(response.message);
            redirectToLoginPage();
        })
    }
    const sendResetPasswordMail = (data) => {
        sendRequest({
            url: '/api/auth/sendResetLink',
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
            }, body: { 'username': data.username }
        }, (response) => {
            message.info(response.message);
            redirectToLoginPage();
        })
    }

    const fetchUserDetailsForToken = (token, setData) => {
        sendRequest({
            url: '/api/auth/fetchUserDetialsForToken?token=' + token
        }, (response) => {

            setData(response);
        });
    }

    const resetUserPassword = (data) => {
        sendRequest({
            url: '/api/auth/resetPassword',
            method: 'POST',
            headers: {
                'Content-type': 'application/json'
            }, body: data
        }, (response) => {
            message.success(response.message);
            redirectToLoginPage();
        })
    }

    const loginHandler = async (username, password) => {
        sendRequest(
            {
                url: "/api/auth/login",
                method: "POST",
                body: { username: username, password: password },
                headers: {
                    "Content-Type": "application/json",
                },
            },
            (data) => {
                authCtx.setLoggedIn(true);
                authCtx.setJWTUser();
                userCtx.loadInitialData();
            }
        );
    };

    const logoutHandler = () => {
        authCtx.logoutHandler();
    };
    return { "AuthenticationService": { createUser, sendConfirmationMail, sendResetPasswordMail, fetchUserDetailsForToken, resetUserPassword, loginHandler, logoutHandler } };
}

export default useAuthenticationService;