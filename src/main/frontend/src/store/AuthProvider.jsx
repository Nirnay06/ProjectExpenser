import React, { useState } from "react";
import useServices from "../hooks/useSevices";
import { isFutureDate } from "../utils/DateUtil";
import { parseJwt } from "../utils/JWTparser";
import AuthContext from "./AuthContext";

const AuthProvider = (props) => {
  const [isLoggedIn, setLoggedIn] = useState(false);
  const { AuthenticationService } = useServices();
  const [displaySpinner, setDisplaySpinner] = useState(false);
  const [JWTUser, setSessionUser] = useState({});

  const setJWTUser = () => {
    const jwt = window.sessionStorage.getItem("Authorization");
    const details = parseJwt(jwt);
    console.log(details);
    if (!isFutureDate(details.expiredAt)) {
      AuthenticationService.logoutHandler();
    } else {
      setSessionUser(details.user);
    }
  };
  const logoutHandler = () => {
    setLoggedIn(false);
    window.sessionStorage.removeItem("Authorization");
  };
  return (
    <AuthContext.Provider
      value={{
        isLoggedIn,
        setLoggedIn,
        displaySpinner,
        setDisplaySpinner,
        JWTUser,
        setJWTUser,
        logoutHandler,
      }}
    >
      {props.children}
    </AuthContext.Provider>
  );
};

export default AuthProvider;
