import {Divider, Progress, Tag} from 'antd';
import styles from './../../styles/components/chartbox.module.scss';
import {getCurrencyFormatted} from '../../utils/StringUtils';
import icons from "./../../assets/sprite.svg";
import PieChart from '../ChartComponent/PieChart';
import {useContext, useState} from 'react';
import useServices from '../../hooks/useSevices';
import UserContext from '../../store/UserContext';
import {useEffect} from 'react';
import {getFormattedDate} from '../../utils/DateUtil';
const ExpenseStructure = (props)=>{
  const [data, setData] = useState([]);
  const {DashBoardService} = useServices();
  const userCtx = useContext(UserContext);
  useEffect(()=>{
    DashBoardService.getStatsForExpenseStructure(props.selectedAccount,getFormattedDate(props.dateRange.startDate,'DD-MM-YYYY'), getFormattedDate(props.dateRange.endDate,'DD-MM-YYYY'),  setData);
},[props.dateRange.endDate, props.dateRange.startDate, props.selectedAccount, userCtx.refresh])

    return (
      <>
        <div className={styles.chartbox__header}>
          <p className="font16 fontBold">Expense Structure</p>
        </div>
        <Divider style={{ margin: "8px 0px" }} />
        <div className={`font16 ${styles.chartbox__body}`}>
          <div className={styles.cashflow__detail}>
            <span className={styles["cashflow__detail--current"]}>
              <p>{props.periodLabel}</p>
              <p className="font22">{getCurrencyFormatted(data.TOTAL_EXPENSE)}</p>
            </span>
            <span className={styles["cashflow__detail--previous"]}>
              <p className="font12">vs previous period</p>
              <p>
                <Tag
                  className={`font20 ${styles["cashflow__detail--previous-2"]}`}
                  icon={
                    <svg className={`icon icon-arrow-up-green offsetR5`}>
                      <use href={`${icons}#icon-arrow-up-green`}></use>
                    </svg>
                  }>
                  {data.PERCENTAGE_CHANGE}%
                </Tag>
              </p>
            </span>
          </div>
        </div>
        {data.data && <PieChart
            data={data.data}
            config={{ angleField: "value", colorField: "category",  className: "piechart offsetB10"}}
            formatter={(v) => getCurrencyFormatted(v)}
          />}
       </>
    );
}

export default ExpenseStructure;