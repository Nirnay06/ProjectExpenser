import { useCookies } from "react-cookie";
import styles from "./../styles/components/accountButton.module.scss";
import { useContext, useState } from "react";
import AuthContext from "../store/AuthContext";
import {
  GridContent,
  DropdownHeader,
  ButtonHeader,
  DropdownHeaderItem,
} from "../layout/DashboardLayout";
import DateRangePicker from "../components/UI/DatePicker/DateRangePicker";
import ChartBox from "../components/Dashboard/Chartbox";

const DashboardPage = (props) => {
  const [dateRange, setDateRange] = useState({
    startDate: new Date(),
    endDate: new Date(),
  });
  //const [cookies, setCookie] = useCookies();
  // const authCtx = useContext(AuthContext);
  // const onClickHandler = async (event) => {
  //   event.preventDefault();
  //   // console.log(cookies);
  //   let response = await fetch("http://testdomain.com:8080/broker/dashboard");
  //   console.log(response);
  //   let data = await response.json();

  //   console.log(data);
  // };
  /* <h2>This is dashboard
        <Button type="primary" onClick={authCtx.logoutHandler} style={{position:"relative", top:100}}>Logout</Button>
        <Button type="primary" onClick={onClickHandler} style={{position:"relative", top:100}}>Fetch</Button>
        </h2> */

  return (
    <>
      <ButtonHeader>
        <div className={styles.account__button}>This is a account</div>
        <div className={styles.account__button}>This is a account</div>
        <div className={styles.account__button}>This is a account</div>
        <div className={styles.account__button}>This is a account</div>
        <div className={styles.account__button}>This is a account</div>
        <div className={styles.account__button}>This is a account</div>
        <div className={styles.account__button}>This is a account</div>
        <div className={styles.account__button}>
          {dateRange &&
            dateRange.startDate &&
            dateRange.startDate.toISOString()}
        </div>
        <div className={styles.account__button}>
          {dateRange && dateRange.endDate && dateRange.endDate.toISOString()}
        </div>
      </ButtonHeader>
      <DropdownHeader>
        <DropdownHeaderItem>
          <DateRangePicker dateRange={dateRange} setDateRange={setDateRange} />
        </DropdownHeaderItem>
      </DropdownHeader>
      <GridContent>
        <ChartBox />
        <ChartBox />
        <ChartBox />
        <ChartBox />
        <ChartBox />
        <ChartBox />
        <ChartBox />
        <ChartBox />
        <ChartBox />
        <ChartBox />
        <ChartBox />
      </GridContent>
    </>
  );
};
export default DashboardPage;
