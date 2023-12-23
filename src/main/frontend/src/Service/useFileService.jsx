import {notification} from "antd";
import useHttp from "../hooks/useHttp";

const useFileService = ()=>{
    const { sendRequest } = useHttp();

    const uploadFileToServer = (options)=>{
        const { onSuccess, onError, file, onProgress } = options;
        const formData = new FormData();
        formData.append('file', file);
        formData.append('accountIdentifier', 'f34cdf77-3bc7-49ef-b023-a2ce7c6527df');
        sendRequest({
            url: '/api/files/uploadRecordFiles',
            body: formData ,
            method: 'POST',
            isJson : false
        }, onSuccess, onError);
    }

    const importRecordsFromUploadedFile = (fileMetaData)=>{
        sendRequest({
            url: '/api/files/importRecords/'+fileMetaData.fileIdentifier,
            body: fileMetaData ,
            method: 'POST',
            headers: { "Content-Type": "application/json" }
        }, (response)=>{notification.success(response.message)})
    }
    return {
        FileService: {
            uploadFileToServer, importRecordsFromUploadedFile
        },
      };
}
export default useFileService;