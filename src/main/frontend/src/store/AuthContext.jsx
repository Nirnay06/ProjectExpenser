import React from "react";
const AuthContext = React.createContext({
  isLoggedIn: false,
  setLoggedIn: () => {},
  displaySpinner: false,
  setDisplaySpinner: () => {},
  JWTUser: {},
  setJWTUser: () => {},
});

export default AuthContext;
