import { useState } from "react";
import DateRangePicker from "../components/UI/DatePicker/DateRangePicker";
import {
  DropdownHeader,
  DropdownHeaderItem,
  MainContent,
  SidebarContainer,
  SidebarContent,
} from "../layout/DashboardLayout";

const AnalyticsPage = (props) => {
  const [dateRange, setDateRange] = useState({
    startDate: new Date(),
    endDate: new Date(),
  });
  return (
    <>
      <DropdownHeader>
        <DropdownHeaderItem>
          <DateRangePicker dateRange={dateRange} setDateRange={setDateRange} />
        </DropdownHeaderItem>
      </DropdownHeader>
      <SidebarContent>
        <SidebarContainer />
        <MainContent />
      </SidebarContent>
    </>
  );
};
export default AnalyticsPage;
