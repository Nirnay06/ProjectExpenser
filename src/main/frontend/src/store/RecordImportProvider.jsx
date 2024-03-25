import React, { useState } from "react";
import RecordImportContext from "./RecordImportContext";
import { useEffect } from "react";

const RecordImportProvider = (props) => {
  const [fileImportData, setFileImportData] = useState({
    // "data": [
    //     [
    //         "Transaction name ",
    //         "Amount ",
    //         "Label ",
    //         "Nirnay",
    //         "Category",
    //         "Transaction name ",
    //         "Label ",
    //         "Nirnay",
    //         "Category",
    //         "Transaction name ",
    //         "Amount ",
    //         "Label ",
    //         "Nirnay",
    //         "Category"
    //     ],
    //     [
    //         "Taxes",
    //         "9282.0",
    //         "Home",
    //         "01-Jan-2023",
    //         "taxes",
    //         "Taxes",
    //         "Home",
    //         "01-Jan-2023",
    //         "taxes",
    //         "Taxes",
    //         null,
    //         "Home",
    //         "01-Jan-2023",
    //         "taxes"
    //     ],
    //     [
    //         "Grocery",
    //         "283.0",
    //         null,
    //         "01-Jan-2023",
    //         null,
    //         "Grocery",
    //         null,
    //         "01-Jan-2023",
    //         null,
    //         "Grocery",
    //         null,
    //         null,
    //         "01-Jan-2023",
    //         null
    //     ],
    //     [
    //         "Taxes",
    //         "9282.0",
    //         "Home",
    //         "01-Jan-2023",
    //         "taxes",
    //         "Taxes",
    //         "Home",
    //         "01-Jan-2023",
    //         "taxes",
    //         "Taxes",
    //         null,
    //         "Home",
    //         "01-Jan-2023",
    //         "taxes"
    //     ],
    //     [
    //         "Taxes",
    //         "9282.0",
    //         "Home",
    //         "01-Jan-2023",
    //         "taxes",
    //         "Taxes",
    //         "Home",
    //         "01-Jan-2023",
    //         "taxes",
    //         "Taxes",
    //         null,
    //         "Home",
    //         "01-Jan-2023",
    //         "taxes"
    //     ],
    //     [
    //         "Taxes",
    //         "9282.0",
    //         "Home",
    //         "01-Jan-2023",
    //         "taxes",
    //         "Taxes",
    //         "Home",
    //         "01-Jan-2023",
    //         "taxes",
    //         "Taxes",
    //         null,
    //         "Home",
    //         "01-Jan-2023",
    //         "taxes"
    //     ]
    // ],
    // "rowCount": 6,
    // "columnCount": 14,
    // "alphabetList": [
    //     "A",
    //     "B",
    //     "C",
    //     "D",
    //     "E",
    //     "F",
    //     "G",
    //     "H",
    //     "I",
    //     "J",
    //     "K",
    //     "L",
    //     "M",
    //     "N"
    // ]
  });
  const [currentStep, setCurrentStep] = useState(0);
  const [fileMetaData, setFileMetaData] = useState({ headerToggle: false });

  const updateFileImportData = (key, value) => {
    setFileImportData((p) => {
      return { ...p, key: value };
    });
  };

  const updateFileMetaData = (field, value) => {
    setFileMetaData((p) => {
      return { ...p, [`${field}`]: value };
    });
  };

  const isCellHighlighted = (rowIndex, colIndex) => {
    if (fileMetaData.headerToggle == true && fileMetaData.selectedHeaderRow == rowIndex) {
      return false;
    }
    if (currentStep == 1) {
      let highlightCell = false;
      if (fileMetaData.isIncomeExpenseDiff) {
        highlightCell = colIndex == fileMetaData.incomeColumn || colIndex == fileMetaData.expenseColumn;
      } else {
        highlightCell = colIndex == fileMetaData.amountColumn;
      }
      if (highlightCell == false && fileMetaData.isFeeApplicable) {
        highlightCell = colIndex == fileMetaData.feeColumn;
      }
      return highlightCell;
    }

    if (currentStep == 2) {
      return colIndex == fileMetaData.dateColumn;
    }

    if (currentStep == 3) {
      return colIndex == fileMetaData.noteColumn || colIndex == fileMetaData.payeeColumn || colIndex == fileMetaData.currencyColumn;
    }
    return false;
  };
  return (
    <RecordImportContext.Provider
      value={{
        fileImportData,
        setFileImportData,
        updateFileImportData,
        fileMetaData,
        setFileMetaData,
        updateFileMetaData,
        isCellHighlighted,
        currentStep,
        setCurrentStep,
      }}
    >
      {props.children}
    </RecordImportContext.Provider>
  );
};

export default RecordImportProvider;
