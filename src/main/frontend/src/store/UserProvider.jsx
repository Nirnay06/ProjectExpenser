import React, { useState } from "react";
import useServices from "../hooks/useSevices";
import UserContext from "./UserContext";

const UserProvider = (props) => {
  const [defaultLabels, setDefaultLabels] = useState([]);
  const [defaultCurrency, setDefaultCurrency] = useState(null);
  const { LabelService,CurrencyService } = useServices();
  const [refresh, setRefresh] = useState(false);

  const triggerRefresh = () => {
    setRefresh((p) => !p);
  };

  const addDefaultLabels = (newList) => {
    setDefaultLabels((prev) => [...prev, ...newList]);
  };
  const loadInitialData = () => {
    LabelService.fetchDefaultLabelsByUserForRecord(addDefaultLabels);
    CurrencyService.fetchDefaultCurrencyByUser(setDefaultCurrency);
  };
  return (
    <UserContext.Provider
      value={{
        defaultLabels,
        addDefaultLabels,
        defaultCurrency,
        loadInitialData,
        refresh,
        triggerRefresh,
      }}>
      {props.children}
    </UserContext.Provider>
  );
};

export default UserProvider;
