import { useCallback, useContext } from "react";
import { useHistory } from "react-router-dom";
import AuthContext from "../store/AuthContext";
import { message } from 'antd'

const useHttp = () => {
    let history = useHistory();
    const authCtx = useContext(AuthContext);
    const defaultErrorHandlingFunction = (data) => { message.error(data.message ? data.message : 'Something went wrong !!') }
    const sendRequest = useCallback(async (requestConfig, applyData = () => { }, handleError = defaultErrorHandlingFunction) => {
        let auth = sessionStorage.getItem("Authorization");
        if (auth) {
            requestConfig.headers = { ...requestConfig.headers, "Authorization": auth }
        }
        try {
            authCtx.setDisplaySpinner(true);
            const response = await fetch(requestConfig.url, {
                method: requestConfig.method ? requestConfig.method : 'GET',
                headers: requestConfig.headers ? requestConfig.headers : {},
                body: requestConfig.body ? JSON.stringify(requestConfig.body) : null,
            });
            let data = {};
            try {
                data = await response.json();
            } catch (err) {
                console.log('error parsing response');
            }
            if (!response.ok) {
                if ([401, 403].includes(response.status)) {
                    window.sessionStorage.removeItem('Authorization');
                    if (history) {
                        history.push('/login');
                    }
                    // authCtx.logoutHandler();
                }
                handleError(data);
                throw new Error(data?.message);
            }
            auth = response.headers.get('Authorization');
            if (auth) {
                window.sessionStorage.setItem("Authorization", auth);
            }
            applyData(data);
        }
        catch (err) {
            console.log(err);
        }
        authCtx.setDisplaySpinner(false);
    }, []);

    return { sendRequest };
}

export default useHttp;