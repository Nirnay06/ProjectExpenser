import {Button, Divider, Progress, Tag} from 'antd';
import styles from './../../styles/components/chartbox.module.scss';
import {getCurrencyFormatted} from '../../utils/StringUtils';
import icons from "./../../assets/sprite.svg";
import {useState} from 'react';
import CustomModal from '../UI/Modal';
import useServices from '../../hooks/useSevices';
import {useContext} from 'react';
import UserContext from '../../store/UserContext';
import {useEffect} from 'react';
import {getFormattedDate} from '../../utils/DateUtil';
import {getRandomBlueShade} from '../../utils/ColorUtils';
const SpendByCategory = (props)=>{
  const [showCurrencyPopup, toggleShowCurrencyPopup]  =useState(false);
  const [data, setData] = useState([]);
  const {DashBoardService} = useServices();
  const userCtx = useContext(UserContext);
  useEffect(()=>{
    DashBoardService.getStatsForExpenseStructure(props.selectedAccount,getFormattedDate(props.dateRange.startDate,'DD-MM-YYYY'), getFormattedDate(props.dateRange.endDate,'DD-MM-YYYY'),  setData);
},[props.dateRange.endDate, props.dateRange.startDate, props.selectedAccount, userCtx.refresh])

let categoryList = data?.data?.sort((a,b)=>b.PERCENTAGE - a.PERCENTAGE);
let displayedCategory = categoryList?.slice(0,3);
const CurrencyBalanceList = ({list})=>{
  return <>
         {list && list.map(category =>{
          return <div key={category.IDENTIFIER} className={`${styles["cashflow__income"]}`}>
          <span className={`${styles["cashflow__income--detail"]}`}>
            <p>{category.category}</p>
            <p className="fontBold">{getCurrencyFormatted(category.value)}</p>
          </span>
          <Progress className="offsetT5" percent={category.PERCENTAGE} strokeColor={getRandomBlueShade()} showInfo={false} size={[386, 20]}></Progress>
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
      title="Spending By Categories"
      centered
      width={430}
      >
     <CurrencyBalanceList list={categoryList}/>
    </CustomModal>

        <div className={styles.chartbox__header}>
          <p className="font16 fontBold">Spending By Categories</p>
          <Button className='font10' size='small' type='default' shape='round' onClick={()=>{toggleShowCurrencyPopup(true)}}>Show more</Button>
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
          <div className='offsetT20'>
            <CurrencyBalanceList list={displayedCategory}/>
          </div>
        </div>
      </>
    );
}

export default SpendByCategory;