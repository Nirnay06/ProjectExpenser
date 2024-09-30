import useHttp from "../hooks/useHttp";
import { useHistory } from "react-router-dom";
import { useContext } from "react";
import AuthContext from "../store/AuthContext";
const useAuthenticationService = () => {
    const { sendRequest } = useHttp();
    const history = useHistory();
    const authCtx = useContext(AuthContext);

    return { "AuthenticationService": {} };
}

export default useAuthenticationService;