import styles from "./../../styles/components/BalanceChart.module.scss";
import { Line } from "@ant-design/plots";

const AccountBalanceChart = () => {
  const data = [
    {
      year: "1991",
      value: 3,
    },
    {
      year: "1992",
      value: 4,
    },
    {
      year: "1993",
      value: 3.5,
    },
    {
      year: "1994",
      value: 5,
    },
    {
      year: "1995",
      value: 4.9,
    },
    {
      year: "1996",
      value: 6,
    },
    {
      year: "1997",
      value: 7,
    },
    {
      year: "1998",
      value: 9,
    },
    {
      year: "1999",
      value: 13,
    },
    {
      year: "1999",
      value: 8,
    },
  ];
  const config = {
    data,
    xField: "year",
    yField: "value",
    stepType: "vh",
  };
  return (
    <div className={styles["BalanceChart"]}>
      <div className={styles["BalanceChart__header"]}>
        <div className={styles["header__item"]}>
          <span className={styles["header__item--title"]}>Today</span>
          <span className={styles["header__item--value"]}>$7182.97</span>
        </div>
        <div className={styles["header__item"]}>
          <span className={styles["header__item--title"]}>
            Vs Previous Period
          </span>
          <span className={`${styles["header__item--value"]} danger-text`}>
            -709%
          </span>
        </div>
      </div>
      <div className={styles["BalanceChart__graphContainer"]}>
        <Line {...config} />
      </div>
    </div>
  );
};
export default AccountBalanceChart;
