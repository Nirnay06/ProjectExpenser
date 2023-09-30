import useHttp from "../hooks/useHttp";
import { message } from 'antd'
import UserContext from "../store/UserContext";
import {useContext} from "react";
const useRecordService = () => {
  const { sendRequest } = useHttp();
  const userCtx = useContext(UserContext);
  const deleteRecord = (recordIdentifier)=>{
    sendRequest({url : `/api/record/delete/${recordIdentifier}`}, ()=>{
        message.success('Record Deleted Successfully.');
        userCtx.triggerRefresh();
      })
  }
  return { RecordService: {deleteRecord} };
};

export default useRecordService;
