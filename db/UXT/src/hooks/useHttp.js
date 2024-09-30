import { useCallback, useContext } from "react";
import { useHistory } from "react-router-dom";
import AuthContext from "../store/AuthContext";
import { notificationTypes, openUZNotification } from "../utils/NotificationDefaultConfig";
import SystemConfigContext from "../store/SystemConfigContext";
import AlertContext from "../store/AlertContext";

const useHttp = () => {
    let history = useHistory();
    const authCtx = useContext(AuthContext);
    const configCtx = useContext(SystemConfigContext);
    const AlertCtx = useContext(AlertContext);

    const sendRequest = useCallback(async (requestConfig, successData = () => { }, errorData = () => { AlertCtx.showAlert("Something Went Wrong","error")
}) => {
        let auth = process.env.NODE_ENV === 'development' ? sessionStorage.getItem("X-CSRF-Token") : window.GlobalVars.CSRFToken;
        if (auth) {
            requestConfig.headers = { ...requestConfig.headers, "X-CSRF-Token": auth, "X-Requested-With": "XMLHttpRequest" }
        }
        try {
            authCtx.setDisplaySpinner(true);
            if (process.env.NODE_ENV === 'development') {
                requestConfig.url = '/api' + requestConfig.url;
            }
            requestConfig.url = requestConfig.params && Object.keys(requestConfig.params).length > 0 ? requestConfig.url + '?' + new URLSearchParams(requestConfig.params) : requestConfig.url;
            const response = await fetch(requestConfig.url, {
                method: requestConfig.method ? requestConfig.method : 'GET',
                headers: requestConfig.headers ? requestConfig.headers : {},
                body: requestConfig.body ? JSON.stringify(requestConfig.body) : null,
                credentials: "include"
            });
            let data = {};
            try {
                data = await response.json();
            } catch (err) {
                console.log('error parsing response');
            }

            if (!response.ok) {
                if ([401, 403].includes(response.status)) {
                    //Need to revisit this for logout once the user session is invalidate
                    window.sessionStorage.removeItem('Authorization');
                    authCtx.logoutHandler();
                    window.location.href = `${configCtx.systemConfig["EXCHANGE_BASE_URL"]}/${configCtx.systemConfig["portalContext"]}`
                }
                errorData(data);
                throw new Error(data);
            }
            auth = response.headers.get("X-CSRF-Token");
            if (process.env.NODE_ENV === 'development' && auth) {
                window.sessionStorage.setItem("X-CSRF-Token", auth);
            }
            if (data.redirect) {
                window.location.href = `${configCtx.systemConfig["EXCHANGE_BASE_URL"]}${data.redirect}`;
                return;
            }
            successData(data);
        }
        catch (err) {
            console.log(err);
        }
        authCtx.setDisplaySpinner(false);
    }, [configCtx.systemConfig]);

    return { sendRequest };
}

export default useHttp;