import useAuthenticationService from "../Service/useAuthenticationService";
import useCategoryService from "../Service/useCategoryService";
import useLabelService from "../Service/useLabelService";
import useRecordService from "../Service/useRecordService";

const useServices = () => {
    const { AuthenticationService } = useAuthenticationService();
    const { RecordService } = useRecordService();
    const {CategoryService} = useCategoryService();
    const {LabelService} = useLabelService();
    return { AuthenticationService, RecordService,CategoryService, LabelService };
}


export default useServices;