
import {useState} from "react";
import {GridContent,DropdownHeader,ButtonHeader,DropdownHeaderItem} from "../layout/DashboardLayout";
import DateRangePicker from "../components/UI/DatePicker/DateRangePicker";
import DashboardAccountList from "../components/Dashboard/DashboardAccountList";
import DashBoardCharts from "../components/Dashboard/DashboardCharts";
import {getUTCDate, subtractDaysFromDate} from "../utils/DateUtil";
let dateRangeCopy;
const DashboardPage = (props) => {
  const [dateRange, setDateRange] = useState({ endDate: getUTCDate(), startDate: subtractDaysFromDate(getUTCDate(), 30) });
  const [periodLabel , setPeriodLabel] = useState('Last 30 Days');
    if(dateRange.startDate && dateRange.endDate){
      dateRangeCopy = dateRange;
    }
  const [selectedAccount, setSelectedAccount] = useState('ALL');
  console.log(dateRangeCopy);
  return (
    <>
      <ButtonHeader><DashboardAccountList setSelectedAccount={setSelectedAccount}  selectedAccount ={selectedAccount}/>
      </ButtonHeader>
      <DropdownHeader>
        <DropdownHeaderItem>
          <DateRangePicker dateRange={dateRange} setDateRange={setDateRange} periodLabel={periodLabel} setPeriodLabel={setPeriodLabel} />
        </DropdownHeaderItem>
      </DropdownHeader>
      <GridContent>
        <DashBoardCharts selectedAccount={selectedAccount} dateRange={dateRangeCopy} periodLabel={periodLabel}/>
      </GridContent>
    </>
  );
};
export default DashboardPage;
