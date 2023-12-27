import { Select, message } from "antd";
import useHttp from "../hooks/useHttp";
import { useContext } from "react";
import UserContext from "../store/UserContext";

const useCurrencyService = () => {
  const { sendRequest } = useHttp();
  const userCtx = useContext(UserContext);
  const getOptionForCurrencyDropdown = (option) => {
    return <Select.Option value={option.identifier}>{option.icon}</Select.Option>;
  };

  const fetchCurrencyListForRecord = (setData) => {
    sendRequest({ url: "/api/currency/currencyListForRecord" }, (data) => {
      setData(data);
    });
  };

  const fetchDefaultCurrencyByUser = (setData) => {
    fetchCurrencyListForRecord((data) => {
      let id = data.find((c) => c.baseCurrency).identifier;
      setData(id);
    });
  };

  const fetchAllUnassignedCurrenciesForUser = (setData) => {
    sendRequest({ url: "/api/currency/listMasterCurrencyNotSelected" }, (data) => {
      setData(data);
    });
  };

  const assignCurrencyToUser = (data) => {
    sendRequest(
      {
        url: "/api/currency/assignCurrencyToUser",
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: data,
      },
      (res) => {
        message.success(res.message);
        userCtx.triggerRefresh();
      }
    );
  };

  const editUserCurrency = (data) => {
    sendRequest(
      {
        url: "/api/currency/editUserCurrency",
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: data,
      },
      (res) => {
        message.success(res.message);
        userCtx.triggerRefresh();
      }
    );
  };

  const removeUserCurrency = (identifier) => {
    sendRequest({ url: "/api/currency/removeUserCurrency/" + identifier, method: "PUT" }, (res) => {
      message.success(res.message);
      userCtx.triggerRefresh();
    });
  };
  return {
    CurrencyService: {
      getOptionForCurrencyDropdown,
      fetchCurrencyListForRecord,
      fetchDefaultCurrencyByUser,
      fetchAllUnassignedCurrenciesForUser,
      assignCurrencyToUser,
      editUserCurrency,
      removeUserCurrency,
    },
  };
};
export default useCurrencyService;
