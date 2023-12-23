import styles from "./ImportStep.module.scss";
import { useContext } from "react";
import RecordImportContext from "../../../store/RecordImportContext";
import { Select } from "antd";
const Option = Select.Option;

const ImportStep4 = () => {
  const RecordImportCtx = useContext(RecordImportContext);
  let fileImportData = RecordImportCtx.fileImportData;
  let fileMetaData = RecordImportCtx.fileMetaData;
  return (
    <div className={styles.importStep}>
      {" "}
      <h2>Select additional columns</h2>
      <p className="font14">Please select your additional columns. These columns are optional.</p>
      <div>
        <p>Note</p>
        <Select
          className="offsetT5 parentWidth"
          value={fileMetaData?.noteColumn}
          onChange={(value) => {
            RecordImportCtx.updateFileMetaData("noteColumn", value);
          }}>
          <Option value={null}>Unset</Option>
          {fileMetaData?.alphabetList.map((i, index) => {
            return (
              <Option key={i} value={index+1}>
                {i}
              </Option>
            );
          })}
        </Select>
      </div>
      <div>
        <p>Payee</p>
        <Select
          className="offsetT5 parentWidth"
          value={fileMetaData?.payeeColumn}
          onChange={(value) => {
            RecordImportCtx.updateFileMetaData("payeeColumn", value);
          }}>
          <Option value={null}>Unset</Option>
          {fileMetaData?.alphabetList.map((i, index) => {
            return (
              <Option key={i} value={index+1}>
                {i}
              </Option>
            );
          })}
        </Select>
      </div>
      <div>
        <p>Currency</p>
        <Select
          className="offsetT5 parentWidth"
          value={fileMetaData?.currencyColumn}
          onChange={(value) => {
            RecordImportCtx.updateFileMetaData("currencyColumn", value);
          }}>
          <Option value={null}>Unset</Option>
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

export default ImportStep4;
