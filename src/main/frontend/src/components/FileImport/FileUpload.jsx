import React from "react";
import { InboxOutlined } from "@ant-design/icons";
import { Button, message, notification, Upload } from "antd";
import useHttp from "../../hooks/useHttp";
import RecordImportContext from "../../store/RecordImportContext";
import { useContext } from "react";
import useServices from "../../hooks/useSevices";
const { Dragger } = Upload;

const FileUpload = ({ toggleFlow }) => {
  const { sendRequest } = useHttp();
  const { FileService } = useServices();
  const RecordImportCtx = useContext(RecordImportContext);
  const uploadFile = async (options) => {
    FileService.uploadFileToServer(options);
  };

  const props = {
    name: "file",
    multiple: false,
    listType: "picture",
    customRequest: uploadFile,
    onChange(info) {
      const { status } = info.file;
      if (status !== "uploading") {
      }
      if (status === "done") {
        RecordImportCtx.setFileImportData(info.file?.response?.fileData);
        RecordImportCtx.setFileMetaData(info.file?.response?.fileMetaData); //{'headerToggle' : true, 'selectedHeaderRow' : 1, selectedFirstRow : 2 , 'selectedLastRow' : info.file?.response?.rowCount,isIncomeExpenseDiff : false, amountColumn : 2, isFeeApplicable: false, dateColumn : 4, currencyColumn : null, payeeColumn : null, noteColumn : null });
        toggleFlow("import");
        notification.success({ message: `${info.file.name} file uploaded successfully.`, duartion: 3 });
      } else if (status === "error") {
        notification.error({ message: `${info.file.name} file upload failed.`, duration: 0, description: info.file?.error?.message });
      }
    },
    onDrop(e) {
      console.log("Dropped files", e.dataTransfer.files);
    },
  };
  return (
    <div>
      <Dragger {...props}>
        <Button type="primary" size="large">
          Choose File
        </Button>
        <span>
          <p className="offsetB0">Or</p>
          <p>drag file to this area to upload</p>
        </span>
      </Dragger>
    </div>
  );
};
export default FileUpload;
