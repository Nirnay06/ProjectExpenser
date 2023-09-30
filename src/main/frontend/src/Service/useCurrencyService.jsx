import { Select } from "antd";
import useHttp from "../hooks/useHttp";

const useCurrencyService = () => {
  const { sendRequest } = useHttp();
  const getOptionForCurrencyDropdown = (option) => {
    return <Select.Option value={option.identifier}>{option.icon}</Select.Option>;
  };

  const fetchCurrencyListForRecord = (setData) => {
    sendRequest({ url: "/api/currency/currencyListForRecord" }, (data) => {
      setData(data);
    });
  };

  const fetchDefaultCurrencyByUser = (setData) => {
    fetchCurrencyListForRecord((data)=>{
      let id = data.find(c => c.baseCurrency).identifier;
      setData(id);
    })
  }
  return { CurrencyService: { getOptionForCurrencyDropdown, fetchCurrencyListForRecord,fetchDefaultCurrencyByUser } };
};
export default useCurrencyService;
