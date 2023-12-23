import React from "react";
const RecordImportContext = React.createContext({
  fileImportData : {},
  setFileImportData : ()=>{},
  updateFileImportData : (key, value)=>{},
  fileMetaData : {},
  setFileMetaData : ()=>{},
  updateFileMetaData : (key, value)=>{},
  isCellHighlighted : (rowIndex, colIndex) =>{}
});

export default RecordImportContext;
