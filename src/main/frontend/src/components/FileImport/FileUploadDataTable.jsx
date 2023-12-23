import { useContext } from "react";
import styles from "./FileUpload.module.scss";
import RecordImportContext from "../../store/RecordImportContext";

const FileUploadDataTable = () => {
  const RecordImportCtx = useContext(RecordImportContext);
  let fileImportData = RecordImportCtx.fileImportData;
  let fileMetaData = RecordImportCtx.fileMetaData;
  console.log(fileMetaData);
  return (
    <>
      <div
        className={styles.fileUpload__table}
        style={{
          gridTemplateColumns: `1fr repeat(${fileMetaData.columnCount}, 6fr)`,
          gridTemplateRows: `1fr repeat(${fileMetaData?.rowCount},  6fr)`,
        }}>
        {fileImportData?.map((row, rowIndex) => (
          <>
            {row.map((col, colIndex) => (
              <>
                {rowIndex == 0 && colIndex == 0 && (
                  <>
                    <div className={`${styles.fileUpload__alphaHeader} ${styles.fileUpload__table_cell}`} key={"header" + colIndex}>
                      <span className={`font16 ${styles.fileUpload__table_cell_content}`}>{""}</span>
                    </div>
                    {fileMetaData?.alphabetList?.map((alpha, alphaIndex) => {
                      return (
                        <div className={`${styles.fileUpload__alphaHeader} ${styles.fileUpload__table_cell}`} key={"alpha" + alphaIndex}>
                          <span className={`font16 ${styles.fileUpload__table_cell_content}`}>{alpha}</span>
                        </div>
                      );
                    })}
                  </>
                )}
                {console.log(RecordImportCtx.isCellHighlighted(rowIndex+1, colIndex+1))}
                {colIndex == 0 && <div className={`${styles.fileUpload__numRow} ${styles.fileUpload__table_cell}`} key={"num" + rowIndex}> <span className={`font16 ${styles.fileUpload__table_cell_content}`}>{rowIndex}</span></div>}
                {fileMetaData?.headerToggle && fileMetaData?.selectedHeaderRow == rowIndex + 1 ? (
                  <div className={`${styles.fileUpload__table_header} ${styles.fileUpload__table_cell}`} key={rowIndex * colIndex}>
                    <span className={`font16 ${styles.fileUpload__table_cell_content}`}>{col}</span>
                  </div>
                ) : (
                  <div className={`${RecordImportCtx.isCellHighlighted(rowIndex+1, colIndex+1) ? styles.fileUpload__table_highlighted: ''} ${styles.fileUpload__table_cell}`} key={rowIndex * colIndex}>
                    <span className={`font16 ${styles.fileUpload__table_cell_content}`}>{col}</span>
                  </div>
                )}
              </>
            ))}
          </>
        ))}
      </div>
    </>
  );
};

export default FileUploadDataTable;
