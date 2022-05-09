import { useCallback, useContext, useState } from "react";
import { useHistory } from "react-router-dom";
import AuthContext from "../store/AuthContext";
import { message } from 'antd';

const useHttp = () => {
    let history = useHistory();
    const authCtx = useContext(AuthContext);
    const [displaySpinner, setDisplaySpinner] = useState(false);

    const sendRequest = useCallback(async (requestConfig, applyData) => {
        let auth = sessionStorage.getItem("Authorization");
        if (auth) {
            requestConfig.headers = { ...requestConfig.headers, "Authorization": auth }
        }
        try {
            setDisplaySpinner(true);
            // setMessage((prev) => { return { ...prev, isVisible: false } });
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
                    authCtx.logoutHandler();
                }
                message.error(data.message ? data.message : 'Something went wrong !!')
                throw new Error(data.message);
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
        setDisplaySpinner(false);
    }, []);

    return { displaySpinner, sendRequest };
}

export default useHttp;