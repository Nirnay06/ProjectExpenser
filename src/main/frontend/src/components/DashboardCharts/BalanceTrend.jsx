import {Divider, Tag} from 'antd';
import styles from './../../styles/components/chartbox.module.scss';
import {getCurrencyFormatted} from '../../utils/StringUtils';
import icons from "./../../assets/sprite.svg";
import {Area} from '@ant-design/charts';
import {useEffect} from 'react';
import {useState} from 'react';
const BalanceTrend = (props)=>{
  const [data, setData] = useState([]);

  useEffect(() => {
    asyncFetch();
  }, []);

  const asyncFetch = () => {
    fetch('https://gw.alipayobjects.com/os/bmw-prod/360c3eae-0c73-46f0-a982-4746a6095010.json')
      .then((response) => response.json())
      .then((json) => setData(json))
      .catch((error) => {
        console.log('fetch data failed', error);
      });
  };
  const config = {
    data,
    xField: 'timePeriod',
    yField: 'value',
    xAxis: {
      range: [0, 1],
    },
  };

    return (
      <>
        <div className={styles.chartbox__header}>
          <p className="font16 fontBold">Balance Trend</p>
        </div>
        <Divider style={{ margin: "8px 0px" }} />
        <div className={`font16 ${styles.chartbox__body}`}>
        <div className={styles.cashflow__detail}>
            <span className={styles["cashflow__detail--current"]}>
              <p>{props.periodLabel}</p>
              <p className="font22">{getCurrencyFormatted(-5000)}</p>
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
                  99%
                </Tag>
              </p>
            </span>
          </div>
        </div>
        <Area {...config} />
       </>
    );
}

export default BalanceTrend;