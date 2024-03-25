import React, { useContext, useEffect, useState } from "react";
import useServices from "../hooks/useSevices";
import { isFutureDate } from "../utils/DateUtil";
import { parseJwt } from "../utils/JWTparser";
import AuthContext from "./AuthContext";
import Cookies from "js-cookie";

const AuthProvider = (props) => {
  const [isLoggedIn, setLoggedIn] = useState(false);
  const { AuthenticationService } = useServices();
  const [displaySpinner, setDisplaySpinner] = useState(false);
  const [JWTUser, setSessionUser] = useState({});

  useEffect(() => {
    setJWTUser();
  }, []);

  const setJWTUser = () => {
    const jwt = window.sessionStorage.getItem("Authorization");
    if (jwt) {
      const details = parseJwt(jwt);
      if (isFutureDate(details.expiredAt)) {
        setLoggedIn(true);
        setSessionUser(details.user);
      }
    } else {
      AuthenticationService.logoutHandler();
    }
    AuthenticationService.logoutHandler();
  };
  const logoutHandler = () => {
    setLoggedIn(false);
    window.sessionStorage.removeItem("Authorization");
    Cookies.remove("Authorization");
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
