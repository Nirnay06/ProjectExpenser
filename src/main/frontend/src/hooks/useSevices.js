import useAccountService from "../Service/useAccountService";
import useAuthenticationService from "../Service/useAuthenticationService";
import useCategoryService from "../Service/useCategoryService";
import useCurrencyService from "../Service/useCurrencyService";
import useDashboardService from "../Service/useDashboardService";
import useLabelService from "../Service/useLabelService";
import useLocationService from "../Service/useLocationService";
import useRecordService from "../Service/useRecordService";

const useServices = () => {
    const { AuthenticationService } = useAuthenticationService();
    const { RecordService } = useRecordService();
    const {CategoryService} = useCategoryService();
    const {LabelService} = useLabelService();
    const {AccountService}= useAccountService();
    const {CurrencyService}=useCurrencyService();
    const {LocationService} = useLocationService();
    const {DashBoardService} = useDashboardService();
    return { AuthenticationService, RecordService,CategoryService, LabelService, AccountService, CurrencyService,LocationService, DashBoardService };
}


export default useServices;