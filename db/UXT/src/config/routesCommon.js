export const CommonRoutes = [
    { path: "/", exact: true, redirect: "/expenseList", layout: DashboardLayout },
    { path: "/expenseList", exact: true, component: ExpenseList, layout: DashboardLayout },
    { path: "/category", component: DashboardPage, layout: DashboardLayout, exact: true },
];

export const CommonAnonymousRoutes = [
    // { path: "/:portalUser/privacy", component: PrivacyPage, layout: PolicyLayout, footer: 'false', pageId: "privacy" },
    // { path: "/:portalUser/termsofuse", component: TermsOfUsePage, layout: PolicyLayout, footer: 'false', pageId: "termsofuse" },
    // { path: "/", redirect: window.location.pathname + window.location.search, layout: AnonymousLayout },
];
