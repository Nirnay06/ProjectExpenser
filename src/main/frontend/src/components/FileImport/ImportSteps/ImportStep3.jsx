import styles from "./ImportStep.module.scss";
import { useContext } from "react";
import RecordImportContext from "../../../store/RecordImportContext";
import { Select } from "antd";
const Option = Select.Option;
const ImportStep3 = () => {
  const RecordImportCtx = useContext(RecordImportContext);
  let fileImportData = RecordImportCtx.fileImportData;
  let fileMetaData = RecordImportCtx.fileMetaData;
  return (
    <div className={styles.importStep}>
      {" "}
      <h2>Select date columns</h2>
      <p className="font14">Please select your transaction date column.</p>
      <div>
        <p>Date Column</p>
        <Select
          className="offsetT5 parentWidth"
          value={fileMetaData?.dateColumn}
          onChange={(value) => {
            RecordImportCtx.updateFileMetaData("dateColumn", value);
          }}>
          {fileMetaData?.alphabetList.map((i, index) => {
            return (
              <Option key={i} value={index+1}>
                {i}
              </Option>
            );
          })}
        </Select>
      </div>
    </div>
  );
};

export default ImportStep3;
