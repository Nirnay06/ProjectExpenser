import CategoryList from '../Pages/CategoryList';
import ExpenseHistory from '../Pages/ExpenseHistory';
import PendingExpenses from '../Pages/PendingExpense';
import DashboardLayout from '../layout/DashboardLayout';

export const anonymousRoutes = [
    { path: "/", exact: true, redirect: "/pending-expenses", layout: DashboardLayout },
    { path: "/pending-expenses", exact: true, component: PendingExpenses, layout: DashboardLayout },
    { path: "/history-expenses", exact: true, component: ExpenseHistory, layout: DashboardLayout },
    { path: "/categories", component: CategoryList, layout: DashboardLayout, exact: true },
];

export const routes = [
    // { path: "/:portalUser/privacy", component: PrivacyPage, layout: PolicyLayout, footer: 'false', pageId: "privacy" },
    // { path: "/:portalUser/termsofuse", component: TermsOfUsePage, layout: PolicyLayout, footer: 'false', pageId: "termsofuse" },
    // { path: "/", redirect: window.location.pathname + window.location.search, layout: AnonymousLayout },
];
