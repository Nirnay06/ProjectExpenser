import { Select, Switch } from "antd";
import styles from "./ImportStep.module.scss";
import { useContext } from "react";
import RecordImportContext from "../../../store/RecordImportContext";
const Option = Select.Option;

const ImportStep2 = () => {
  const RecordImportCtx = useContext(RecordImportContext);
  let fileImportData = RecordImportCtx.fileImportData;
  let fileMetaData = RecordImportCtx.fileMetaData;
  return (
    <div className={styles.importStep}>
      <h2>Select amount columns</h2>
      <p className="font14">
        Please select your amount column. You can select Expense or Fee columns if your amount values are split into individual columns.
      </p>
      
       <div className={`offsetT20 ${styles.importStep__switch}`}><Switch
        checked={fileMetaData?.isIncomeExpenseDiff}
        onChange={(checked) => {
          RecordImportCtx.updateFileMetaData("isIncomeExpenseDiff", checked);
        }}
      /><span className="offsetL10 font14">Incomes and Expenses are in two columns</span></div> 
      {!fileMetaData?.isIncomeExpenseDiff && (
        <div>
          <p>Amount Column</p>
          <Select
            className="offsetT5 parentWidth"
            value={fileMetaData?.amountColumn}
            onChange={(value) => {
              RecordImportCtx.updateFileMetaData("amountColumn", value);
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
      )}
      {fileMetaData?.isIncomeExpenseDiff && (
        <>
          <div>
            <p>Income Column</p>
            <Select
              className="offsetT5 parentWidth"
              value={fileMetaData?.incomeColumn}
              onChange={(value) => {
                RecordImportCtx.updateFileMetaData("incomeColumn", value);
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
          <div>
            <p>Expense Column</p>
            <Select
              className="offsetT5 parentWidth"
              value={fileMetaData?.expenseColumn}
              onChange={(value) => {
                RecordImportCtx.updateFileMetaData("expenseColumn", value);
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
        </>
      )}
     <div className={`offsetT20 ${styles.importStep__switch}`}><Switch
        checked={fileMetaData?.isFeeApplicable}
        onChange={(checked) => {
          RecordImportCtx.updateFileMetaData("isFeeApplicable", checked);
        }}
      /><span className="offsetL10 font14">I have a separate fees/charges column</span></div> 
      {fileMetaData?.isFeeApplicable && (
        <div>
          <p>Amount Column</p>
          <Select
            className="offsetT5 parentWidth"
            value={fileMetaData?.feeColumn}
            onChange={(value) => {
              RecordImportCtx.updateFileMetaData("feeColumn", value);
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
      )}
    </div>
  );
};

export default ImportStep2;
