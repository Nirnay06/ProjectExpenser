import useAuthenticationService from "../Service/useAuthenticationService";
import useCategoryService from "../Service/useCategoryService";
import useRecordService from "../Service/useRecordService";

const useServices = () => {
    const { AuthenticationService } = useAuthenticationService();
    const { RecordService } = useRecordService();
    const {CategoryService} = useCategoryService();
    return { AuthenticationService, RecordService,CategoryService };
}


export default useServices;