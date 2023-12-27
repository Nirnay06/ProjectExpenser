import AccountsPage from "../pages/Accounts";
import AnalyticsPage from "../pages/Analytics";
import DashboardPage from "../pages/Dashboard";
import ForgetPasswordPage from "../pages/ForgetPassword";
import ImportsPage from "../pages/Imports";
import LoginPage from "../pages/Login";
import SignupPage from "../pages/Signup";
import RecordsPage from "../pages/Records";
import SettingsPage from "../pages/Settings";
import UpgradePage from "../pages/Upgrade";
import WalletLifePage from "../pages/WalletLife";
import LoginLayout from "../layout/Login";
import DashboardLayout from "../layout/DashboardLayout";
import AccountDetailPage from "../pages/AccountDetail";
import SendConfirmationPage from "../pages/SendConfirmation";
import UserAccountConfirmationPage from "../pages/UserAccountConfirmation";
import ResetPasswordPage from "../pages/ResetPassowd";

export const routes = [
    { path: "/", exact: true, redirect: "/dashboard", layout: DashboardLayout },
    { path: "/dashboard", component: DashboardPage, layout: DashboardLayout, exact: true },
    { path: "/accounts", component: AccountsPage, layout: DashboardLayout, exact: true },
    { path: "/accounts/account/:accountIdentifier", component: AccountDetailPage, layout: DashboardLayout, exact: true },
    { path: "/records", component: RecordsPage, layout: DashboardLayout, exact: true },
    { path: "/analytics", component: AnalyticsPage, layout: DashboardLayout, exact: true },
    { path: "/imports", component: ImportsPage, layout: DashboardLayout, exact: true },
    { path: "/wallet-life", component: WalletLifePage, layout: DashboardLayout, exact: true },
    { path: "/settings", redirect : "/settings/accounts", layout: DashboardLayout, exact : true},
    { path: "/settings/:pageId", component: SettingsPage, layout: DashboardLayout},
    { path: "/upgrade", component: UpgradePage, layout: DashboardLayout, exact: true },];

export const anonymousRoutes = [
    { path: "/login", component: LoginPage, exact: true, layout: LoginLayout },
    { path: "/signup", component: SignupPage, exact: true, layout: LoginLayout },
    { path: "/reset-password", component: ForgetPasswordPage, exact: true, layout: LoginLayout },
    { path: "/sendConfirmationMail", component: SendConfirmationPage, exact: true, layout: LoginLayout },
    { path: "/confirmAccount/tokenIdentifier/:tokenIdentifier", component: UserAccountConfirmationPage, layout: LoginLayout },
    { path: "/resetPassword/tokenIdentifier/:tokenIdentifier", component: ResetPasswordPage, layout: LoginLayout },
];
