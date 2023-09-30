
import {Button, Dropdown} from 'antd';
import styles from './../../styles/components/chartbox.module.scss';
import {useState} from 'react';
import {useRef} from 'react';
import LastRecords from '../DashboardCharts/LastRecords';
import CashFlow from '../DashboardCharts/CashFlow';
import CurrencyBalance from '../DashboardCharts/CurrencyBalance';
import AccountList from '../DashboardCharts/AccountList';
import SpendByCategory from '../DashboardCharts/SpendByCategory';
import ExpenseStructure from '../DashboardCharts/ExpenseStructure';
import DashBoardGauge from '../DashboardCharts/DashBoardGauge';
import BalanceTrend from '../DashboardCharts/BalanceTrend';
const ChartBox = (props) => {
    const buttonRef = useRef();
    const [draggable, setDraggable] = useState(false);
    const onMouseLeave = () => {
        setDraggable(false);
    };
    const onMouseEnter = () => {
     buttonRef.current.style.cursor= 'grab';
      setDraggable(true);
    };
    const items = [
  {
    key: '1',
    label:"Remove From Dashboard"
        },];
 
    return (
      <div
        id={props.id}
        className={`${props.className} ${styles.chartbox}`}
        style={props.style}
        onDragOver={props.handleDragOver}
        onDragStart={props.handleDrag}
        draggable={draggable}
        onDrop={props.handleDrop}
        >
            
            <div className={styles.chartbox__drag}>
                <div></div>
                <div
                className={`icon icon-move ${styles.chartbox__drag__1}`}
                ref={buttonRef}
                onMouseDown={() => { buttonRef.current.style.cursor = "grabbing"}}
                onMouseUp={() => { buttonRef.current.style.cursor = "grab" }}
                onMouseLeave={onMouseLeave}
                onMouseEnter={onMouseEnter}>
                </div>
                <Dropdown menu={{items}}>
                    <div className={`icon icon-menu ${styles.chartbox__drag__2}`}></div>
                </Dropdown>
            </div>
        {/* <div className={styles.chartbox__header}>
            Chart header {props.id}
        </div>
        <div className={styles.chartbox__body}>
            Chart Body {props.id}
        </div> */}
        
        {props.id==='LastRecords' && <LastRecords  {...props}/>}
        {props.id==='CashFlow' && <CashFlow {...props}/>}
        {props.id==='CurrencyBalance' && <CurrencyBalance {...props}/>}
        {props.id==='AccountList' && <AccountList {...props}/>}
        {props.id==='SpendByCategory' && <SpendByCategory {...props}/>}
        {props.id==='ExpenseStructure' && <ExpenseStructure {...props}/>}
        {props.id==='DashBoardGauge' && <DashBoardGauge {...props}/>}
        {props.id==='BalanceTrend' && <BalanceTrend {...props}/>}
      </div>
    
    );
}

export default ChartBox;