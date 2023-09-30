import {Divider, Progress, Tag} from 'antd';
import styles from './../../styles/components/chartbox.module.scss';
import {getCurrencyFormatted} from '../../utils/StringUtils';
import icons from "./../../assets/sprite.svg";
import useServices from '../../hooks/useSevices';
import {useContext, useEffect, useState} from 'react';
import UserContext from '../../store/UserContext';
import {getFormattedDate} from '../../utils/DateUtil';
const CashFlow = (props)=>{
  const [data, setData] = useState({});
  const {DashBoardService} = useServices();
  const userCtx = useContext(UserContext);
  useEffect(()=>{
    DashBoardService.getStatsForCashFlowCard(props.selectedAccount,getFormattedDate(props.dateRange.startDate,'DD-MM-YYYY'), getFormattedDate(props.dateRange.endDate,'DD-MM-YYYY'),  setData);
},[props.dateRange.endDate, props.dateRange.startDate, props.selectedAccount, userCtx.refresh])

    return (
      <>
        <div className={styles.chartbox__header}>
          <p className="font16 fontBold">Cash Flow</p>
        </div>
        <Divider style={{ margin: "8px 0px" }} />
        <div className={`font16 ${styles.chartbox__body}`}>
          <div className={styles.cashflow__detail}>
            <span className={styles["cashflow__detail--current"]}>
              <p>{props.periodLabel}</p>
              <p className="font22">{getCurrencyFormatted(data.TOTAL)}</p>
            </span>
           {data.VS_PREVIOUS ? <span className={styles["cashflow__detail--previous"]}>
              <p className="font12">vs previous period</p>
              <p>
                <Tag
                  className={`font20 ${styles["cashflow__detail--previous-2"]}`}
                  icon={
                    <svg className={`icon icon-arrow-up-green offsetR5`}>
                      <use href={`${icons}#icon-arrow-up-green`}></use>
                    </svg>
                  }>
                  {data.VS_PREVIOUS}%
                </Tag>
              </p>
            </span>:<></>}
          </div>
          <div className={`offsetT20 ${styles["cashflow__income"]}`}>
            <span className={`${styles["cashflow__income--detail"]}`}>
              <p>Income</p>
              <p className="fontBold">{getCurrencyFormatted(data.CURRENT_INCOME)}</p>
            </span>
            <Progress className="offsetT5" percent={data.CURRENT_INCOME_PERCENT} status="success" showInfo={false} size={[386, 20]}></Progress>
          </div>
          <div className={`offsetT20 ${styles["cashflow__expense"]}`}>
            <span className={`${styles["cashflow__expense--detail"]}`}>
              <p>Expense</p>
              <p className="fontBold">{getCurrencyFormatted(data.CURRENT_EXPENSE)}</p>
            </span>
            <Progress className="offsetT5" percent={data.CURRENT_EXPENSE_PERCENT} status="exception" showInfo={false} size={[386, 20]}></Progress>
          </div>
        </div>
      </>
    );
}

export default CashFlow;