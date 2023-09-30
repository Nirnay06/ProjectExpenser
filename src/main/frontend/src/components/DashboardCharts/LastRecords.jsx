import {Button, Divider, Empty} from 'antd';
import styles from './../../styles/components/chartbox.module.scss';
import {NavLink} from 'react-router-dom/cjs/react-router-dom.min';
import {useContext, useEffect, useState} from 'react';
import useServices from '../../hooks/useSevices';
import icons from "./../../assets/sprite.svg";
import {getCurrencyFormatted} from '../../utils/StringUtils';
import {getFormattedDate} from '../../utils/DateUtil';
import UserContext from '../../store/UserContext';
const LastRecords = (props)=>{
    const [records, setRecords] = useState([]);
    const {DashBoardService } = useServices();
    const userCtx = useContext(UserContext);
    useEffect(()=>{
        DashBoardService.getStatsForLastRecordCard(props.selectedAccount,getFormattedDate(props.dateRange.startDate,'DD-MM-YYYY'), getFormattedDate(props.dateRange.endDate,'DD-MM-YYYY'),  setRecords);
    },[props.dateRange.endDate, props.dateRange.startDate, props.selectedAccount, userCtx.refresh])
    let displayedRecords = records.slice(0,4);
    return <>
    <div className={styles.chartbox__header}>
        <p className="font16 fontBold">Last Records</p>
        <Button className='font10' size='small' type='default' shape='round'><NavLink to={"/records"}>Records</NavLink></Button>
        </div>
        <Divider style={{margin : '8px 0px'}}/>
        <div className={styles.chartbox__body}>
            {records.length !=0 && <div>
            {displayedRecords.map(record => {
                return <>
                    <div key={record.recordIdentifier} className={`font16 ${styles.lastRecord__record}`}>
                        <div className={`offsetR15 ${styles.lastRecord__iconBox}`}>
                            <div className="dot dot-icon" style={{ backgroundColor: record.recordCategory?.color }}>
                                <svg className={`icon icon-${record.recordCategory?.icon}`}>
                                    <use href={`${icons}#icon-${record.recordCategory?.icon}`}></use>
                                </svg>
                            </div>
                        </div>
                        <div className={`offsetR10 ${styles.lastRecord__content}`}>
                            <p className='font18 fontBold'>{record.recordCategory?.title}</p>
                            <p className={`font12 offsetT5 ${styles.lastRecord__content_account}`}>
                                <span className="dot yellow-bacground offsetR5"></span>
                                <span>{record.account.accountName}</span>
                            </p>
                        </div>
                        <div className={`${styles.lastRecord__detail}`}>
                        <span  className='font18 fontBold' style={{color : record.amount <0 ? 'red' : 'green'}}>{`${getCurrencyFormatted(record.amount, {currency : record.currency.title})}`}</span>
                        <span className='font12'>{`${record.recordDate}, ${record.recordTime}`}</span>
                        </div>
                    </div>
                </>
            })}
            </div>}
           {records.length ==0 && <div className={styles.chartbox__empty}>
                <Empty image={Empty.PRESENTED_IMAGE_SIMPLE} description={"There are no data in the selected time interval."} />; 
            </div>}
        </div>
    </>
}

export default LastRecords;