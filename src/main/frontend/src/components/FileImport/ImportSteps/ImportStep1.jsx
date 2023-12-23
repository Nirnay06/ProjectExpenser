import { Select, Switch } from "antd";
import styles from "./ImportStep.module.scss";
import { useContext } from "react";
import RecordImportContext from "../../../store/RecordImportContext";
const Option = Select.Option;
const ImportStep1 = () => {
  const RecordImportCtx = useContext(RecordImportContext);
  let fileImportData = RecordImportCtx.fileImportData;
  let fileMetaData = RecordImportCtx.fileMetaData;
  console.log(fileMetaData?.headerToggle);
  return (
    <div className={styles.importStep}>
      <h2>Select header row</h2>
      <p className="font14">Please select your header row. This will help you to select columns in the next steps.</p>
      
        <div className={`offsetT20 ${styles.importStep__switch}`}><Switch
        checked={fileMetaData?.headerToggle}
        onChange={(checked) => {
          RecordImportCtx.updateFileMetaData("headerToggle", checked);
        }}
      /><span className="offsetL10 font14">Has header row</span></div> 
      {fileMetaData?.headerToggle && (
        <div>
          <p>Header Row</p>
          <Select
            className="offsetT5 parentWidth"
            defaultValue={1}
            value={fileMetaData?.selectedHeaderRow}
            onChange={(value) => {
              RecordImportCtx.updateFileMetaData("selectedHeaderRow", value);
            }}>
            {Array.from({ length: fileMetaData?.rowCount }, (_, i) => i + 1).map((i) => {
              return (
                <Option key={i} value={i}>
                  {i}
                </Option>
              );
            })}
          </Select>
        </div>
      )}
      {!fileMetaData?.headerToggle && (
        <div>
          <p>First Row</p>
          <Select
            className="offsetT5 parentWidth"
            defaultValue={2}
            value={fileMetaData?.selectedFirstRow}
            onChange={(value) => {
              RecordImportCtx.updateFileMetaData("selectedFirstRow", value);
            }}>
            {Array.from({ length: fileMetaData?.rowCount }, (_, i) => i + 1).map((i) => {
              return (
                <Option key={i} value={i}>
                  {i}
                </Option>
              );
            })}
          </Select>
        </div>
      )}
      <div>
        <p>Last Row</p>
        <Select
          className="offsetT5 parentWidth"
          value={fileMetaData?.selectedLastRow}
          onChange={(value) => {
            RecordImportCtx.updateFileMetaData("selectedLastRow", value);
          }}>
          {Array.from({ length: fileMetaData?.rowCount }, (_, i) => i + 2).map((i) => {
            return (
              <Option key={i} value={i}>
                {i}
              </Option>
            );
          })}
        </Select>
      </div>
    </div>
  );
};

export default ImportStep1;
