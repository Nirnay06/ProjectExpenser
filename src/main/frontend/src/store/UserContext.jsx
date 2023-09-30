import React from "react";
const UserContext = React.createContext({
  defaultLabels: [],
  defaultCurrency : null,
  addDefaultLabels: () => {},
  loadInitialData: () => {},
  refresh: false,
  triggerRefresh: () => {},
});

export default UserContext;
