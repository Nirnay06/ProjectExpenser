import { useState } from "react";
import DateRangePicker from "../components/UI/DatePicker/DateRangePicker";
import {
  DropdownHeader,
  DropdownHeaderItem,
  MainContent,
  SidebarContainer,
  SidebarContent,
} from "../layout/DashboardLayout";
import FileUpload from "../components/FileImport/FileUpload";
import FileUploadDataTable from "../components/FileImport/FileUploadDataTable";
import FileProcessSidebar from "../components/FileImport/FileProcessSidebar";
import styles from './../components/FileImport/FileUpload.module.scss';

import icons from "./../assets/sprite.svg";
import RecordImportProvider from "../store/RecordImportProvider";

const ImportsPage = (props) => {
  const [dateRange, setDateRange] = useState({
    startDate: new Date(),
    endDate: new Date(),
  });
  const [currentFlow, toggleFlow] = useState('review');
  return (
    <>
    <RecordImportProvider>
      <DropdownHeader>
        <DropdownHeaderItem>
          {currentFlow ==='import' && <span className={styles.fileName__header}>
              <svg className={`icon icon-file-excel offsetR10`}>
                  <use href={`${icons}#icon-file-excel`}></use>
              </svg>
              <span>Upload.xlsx</span>
              </span>}
          {currentFlow==='review' &&  <DateRangePicker dateRange={dateRange} setDateRange={setDateRange} />}
        </DropdownHeaderItem>
      </DropdownHeader>
      <SidebarContent>
        <SidebarContainer>
         {currentFlow==='review' && <FileUpload  toggleFlow={toggleFlow}/>}
         {currentFlow==='import' && <FileProcessSidebar  toggleFlow={toggleFlow}/>}
        </SidebarContainer>
        <MainContent>
          {currentFlow ==='import' && <FileUploadDataTable />}
        </MainContent>
      </SidebarContent>
      </RecordImportProvider>
    </>
  );
};
export default ImportsPage;
