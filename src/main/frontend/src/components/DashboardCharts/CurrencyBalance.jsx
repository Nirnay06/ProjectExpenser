import {Button, Divider, Progress, Tag} from 'antd';
import styles from './../../styles/components/chartbox.module.scss';
import {getCurrencyFormatted} from '../../utils/StringUtils';
import icons from "./../../assets/sprite.svg";
import {useEffect, useState} from 'react';
import CustomModal from '../UI/Modal';
import {useContext} from 'react';
import UserContext from '../../store/UserContext';
import useServices from '../../hooks/useSevices';
import {getFormattedDate} from '../../utils/DateUtil';
import {getRandomBlueShade} from '../../utils/ColorUtils';



const CurrencyBalance = (props)=>{
  const [curencyList, setData] = useState([]);
  const {DashBoardService} = useServices();
  const userCtx = useContext(UserContext);
  const [showCurrencyPopup, toggleShowCurrencyPopup]  =useState(false);
  useEffect(() => {
    DashBoardService.getStatsForCurrencyBalanceCard(
      props.selectedAccount,
      getFormattedDate(props.dateRange.startDate, "DD-MM-YYYY"),
      getFormattedDate(props.dateRange.endDate, "DD-MM-YYYY"),
      setData
    );
  }, [props.dateRange.endDate, props.dateRange.startDate, props.selectedAccount, userCtx.refresh]);

  console.log(curencyList);
  curencyList.sort((a,b)=>b.PERCENTAGE - a.PERCENTAGE);
  let displayedCurrency = curencyList.slice(0,4);
  console.log(curencyList,displayedCurrency);
  const CurrencyBalanceList = ({list})=>{
    return <>
           {list.map(currency =>{
            return <div key={currency.IDENTIFIER} className={`${styles["cashflow__income"]}`}>
            <span className={`${styles["cashflow__income--detail"]}`}>
              <p>{currency.CURRENCY_TITLE}</p>
              <p className="fontBold">{getCurrencyFormatted(currency.AMOUNT,{currency : currency.CURRENCY_TITLE})}</p>
            </span>
            <Progress className="offsetT5" percent={currency.PERCENTAGE} strokeColor={getRandomBlueShade()} showInfo={false} size={[386, 20]}></Progress>
           </div>
           })}
    </>
  }
  
  return (
      <>
      <CustomModal
      visible={showCurrencyPopup}
      onCancel={() => {
        toggleShowCurrencyPopup(null);
      }}
      title="Currency Balance"
      centered
      width={430}
      >
     <CurrencyBalanceList list={curencyList}/>
    </CustomModal>

        <div className={styles.chartbox__header}>
          <p className="font16 fontBold">Balance By Currencies</p>
          <Button className='font10' size='small' type='default' shape='round' onClick={()=>{toggleShowCurrencyPopup(true)}}>Show more</Button>
        </div>
        <Divider style={{ margin: "8px 0px" }} />
        <div className={`font16 ${styles.chartbox__body}`}>
          
        <CurrencyBalanceList list={curencyList}/>
        </div>
      </>
    );
}

export default CurrencyBalance;