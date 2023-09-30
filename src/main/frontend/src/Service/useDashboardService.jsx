import useHttp from "../hooks/useHttp";
import {useContext} from "react";
import UserContext from "../store/UserContext";

const useDashboardService = () => {
  const { sendRequest } = useHttp();
  const userCtx = useContext(UserContext);
  const getStatsForLastRecordCard = (accountIdentifier, startDate, endDate, setData)=>{
    sendRequest({url : `/api/dashboard/statsForLastRecordCard/${accountIdentifier}/${startDate}/${endDate}`},
    (data)=>{setData(data)}, ()=>{}, true)
  }

  const getStatsForCashFlowCard = (accountIdentifier, startDate, endDate, setData)=>{
    sendRequest({url : `/api/dashboard/statsForCashFlowCard/${accountIdentifier}/${startDate}/${endDate}`},
    (data)=>{setData(data)}, ()=>{}, true)
  }
  const getStatsForCurrencyBalanceCard = (accountIdentifier, startDate, endDate, setData)=>{
    sendRequest({url : `/api/dashboard/statsForCurrencyBalanceCard/${accountIdentifier}/${startDate}/${endDate}`},
    (data)=>{console.log(data); setData(data)}, ()=>{}, true)
  }
  const getStatsForDashboardGauge = (accountIdentifier, startDate, endDate, setData)=>{
    sendRequest({url : `/api/dashboard/statsForDashboardGauge/${accountIdentifier}/${startDate}/${endDate}`},
    (data)=>{console.log(data); setData(data)}, ()=>{}, true)
  }
  const getStatsForExpenseStructure = (accountIdentifier, startDate, endDate, setData)=>{
    sendRequest({url : `/api/dashboard/statsForExpenseStructureCard/${accountIdentifier}/${startDate}/${endDate}`},
    (data)=>{console.log(data); setData(data)}, ()=>{}, true)
  }
  return {
    DashBoardService: {
      getStatsForLastRecordCard,
      getStatsForCashFlowCard,
      getStatsForCurrencyBalanceCard,
      getStatsForDashboardGauge,
      getStatsForExpenseStructure
    },
  };
};
export default useDashboardService;
