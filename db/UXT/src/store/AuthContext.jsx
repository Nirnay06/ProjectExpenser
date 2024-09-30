import React from "react";
const AuthContext = React.createContext({
  isLoggedIn: false,
  setLoggedIn: () => {},
  userDetails: {},
  setUserDetails: () => {},
  displaySpinner: false,
  setDisplaySpinner: () => {},
});

export default AuthContext;
