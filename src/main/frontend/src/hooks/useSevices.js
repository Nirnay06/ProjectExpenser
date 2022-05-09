import useAuthenticationService from "../Service/useAuthenticationService";

const useServices = () => {
    const { AuthenticationService } = useAuthenticationService();
    return { AuthenticationService };
}


export default useServices;