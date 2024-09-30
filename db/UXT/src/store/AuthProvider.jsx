import React, { useState } from "react";
import AuthContext from "./AuthContext";

const AuthProvider = (props) => {
  const [isLoggedIn, setLoggedIn] = useState(true);
  const [displaySpinnerValue, setDisplaySpinnerValue] = useState(0);

  const [userDetails, setUserDetailsObject] = useState({});

  const setDisplaySpinner = (value) => {
    if (value) {
      setDisplaySpinnerValue((p) => p + 1);
    } else {
      setDisplaySpinnerValue((p) => p - 1);
    }
  };

  const setUserDetails = (key, value) => {
    setUserDetailsObject((prev) => {
      return { ...prev, [key]: value };
    });
  };
  const getDisplaySpinner = () => {
    return displaySpinnerValue !== 0;
  };

  return (
    <AuthContext.Provider
      value={{
        isLoggedIn,
        setLoggedIn,
        userDetails,
        setUserDetails,
        displaySpinner: getDisplaySpinner(),
        setDisplaySpinner,
      }}
    >
      {props.children}
    </AuthContext.Provider>
  );
};

export default AuthProvider;
