import {useEffect, useState} from "react";
import styles from "./../../styles/components/BalanceChart.module.scss";
import { Line } from "@ant-design/plots";
import {Segmented} from "antd";
import useHttp from "../../hooks/useHttp";

const AccountBalanceChart = (props) => {
  const [data, setData] = useState([]);
  const [balance, setBalance] = useState('');
  const [timeInterval, setTimeInterval] = useState('Weekly');
  const {sendRequest} = useHttp();
  useEffect(() => {
    sendRequest({url : `/api/account/balanceData/${props.accountIdentifier}/${timeInterval}`}, (data)=>{
      setData(data);
    })
    sendRequest({url : `/api/account/getCurrentBalance/${props.accountIdentifier}`}, (data)=>{
      setBalance(data);
    })
  }, [timeInterval]);

  const config = {
    data,
    xField: 'interval',
    yField: 'amount',
    seriesField: 'parameterName',
    legend: {
      position: 'top',
      valueStyle: {
        fontSize : 24,
        fontWeight: 'bold' 
      },
      marker: {
        symbol: 'circle',
        radius: 5
      }
    },
    smooth: true,
    animation: {
      appear: {
        animation: 'path-in',
        duration: 5000,
      },
    },
  };
  return (
    <div className={styles["BalanceChart"]}>
      <div className={styles["BalanceChart__header"]}>
        <div className={styles["header__item"]}>
          <span className={styles["header__item--title"]}>Today</span>
          <span className={styles["header__item--value"]}>{balance}</span>
        </div>
        <div className={styles["header__item"]}>
        <Segmented options={['Weekly', 'Monthly', 'Quarterly', 'Yearly', '5 Yearly']} onChange={setTimeInterval} />
        </div>
      </div>
      <div className={styles["BalanceChart__graphContainer"]}>
        {data && data.length && <Line {...config} />}
      </div>
    </div>
  );
};
export default AccountBalanceChart;
