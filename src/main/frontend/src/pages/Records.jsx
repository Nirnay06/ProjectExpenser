import { useState } from "react";
import DateRangePicker from "../components/UI/DatePicker/DateRangePicker";
import {
  DropdownHeader,
  MainContent,
  SidebarContainer,
  SidebarContent,
  DropdownHeaderItem,
} from "../layout/DashboardLayout";

const RecordsPage = (props) => {
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
        <DropdownHeaderItem>
          <h3 className="heading--3">Sort By</h3>
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
export default RecordsPage;
