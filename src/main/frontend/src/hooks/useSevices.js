import useAuthenticationService from "../Service/useAuthenticationService";
import useRecordService from "../Service/useRecordService";

const useServices = () => {
    const { AuthenticationService } = useAuthenticationService();
    const { RecordService } = useRecordService();
    return { AuthenticationService, RecordService };
}


export default useServices;