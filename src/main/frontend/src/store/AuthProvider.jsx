import React, { useState } from "react";
import AuthContext from "./AuthContext";

const AuthProvider = (props) => {
  const [isLoggedIn, setLoggedIn] = useState(false);

  return (
    <AuthContext.Provider value={{ isLoggedIn, setLoggedIn }}>
      {props.children}
    </AuthContext.Provider>
  );
};

export default AuthProvider;
