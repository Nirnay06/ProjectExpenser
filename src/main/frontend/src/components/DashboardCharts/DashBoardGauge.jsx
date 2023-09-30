import {Divider} from 'antd';
import styles from './../../styles/components/chartbox.module.scss';
import GaugeChart from 'react-gauge-chart';
import {useContext, useEffect, useState} from 'react';
import useServices from '../../hooks/useSevices';
import UserContext from '../../store/UserContext';
import {getFormattedDate} from '../../utils/DateUtil';
import {getNumberShorthand} from '../../utils/StringUtils';
const DashBoardGauge = (props)=>{
  const [data, setData] = useState({});
  const {DashBoardService} = useServices();
  const userCtx = useContext(UserContext);
  useEffect(()=>{
    DashBoardService.getStatsForDashboardGauge(props.selectedAccount,getFormattedDate(props.dateRange.startDate,'DD-MM-YYYY'), getFormattedDate(props.dateRange.endDate,'DD-MM-YYYY'),  setData);
},[props.dateRange.endDate, props.dateRange.startDate, props.selectedAccount, userCtx.refresh])

    return (
      <>
        <div className={styles.chartbox__header}>
          <p className="font16 fontBold">Dashboard</p>
        </div>
        <Divider style={{ margin: "8px 0px" }} />
        <div className={`font16 ${styles.chartbox__body}`}>
            <div className={`offsetT40 ${styles['dashboardGauge']}`}>
                <GaugeChart arcsLength={[0.5, 0.5, 0.5]}
                    colors={['#5BE12C', '#F5CD19', '#EA4228']}
                    percent={1-data.BALANCE_PERCENTAGE}
                    arcWidth={0.3}
                    hideText={true}
                    arcPadding={0}
                    cornerRadius={0}
                    marginInPercent={0.02}
                    />
                  <GaugeChart arcsLength={[0.5, 0.5, 0.5]}
                        colors={['#5BE12C', '#F5CD19', '#EA4228']}
                        percent={1-data.CASH_PERCENTAGE}
                        arcWidth={0.3}
                        hideText={true}
                        arcPadding={0}
                        cornerRadius={0}
                        marginInPercent={0.02}
                    /> 
                     <GaugeChart arcsLength={[0.5, 0.5, 0.5]}
                        colors={['#5BE12C', '#F5CD19', '#EA4228']}
                        percent={1-data.SPEND_PERCENTAGE}
                        arcWidth={0.3}
                        hideText={true}
                        arcPadding={0}
                        cornerRadius={0}
                        marginInPercent={0.02}
                    />
                 <div className='center'>
                    <p className='font12 text--dark-3'>BALANCE</p>
                    <p className="font18 fontBold">{getNumberShorthand(data.CURRENT_BALANCE)}</p>
                </div>
                <div className='center'>
                    <p className='font12 text--dark-3'>CASH FLOW</p>
                    <p className="font18 fontBold">{getNumberShorthand(data.CURRENT_CASH_FLOW)}</p>
                </div>
                <div className='center'>
                    <p className='font12 text--dark-3'>SPENDING</p>
                    <p className="font18 fontBold">{getNumberShorthand(data.CURRENT_SPEND)}</p>
                </div>
            </div>
           
        </div>
      </>
    );
}

export default DashBoardGauge;