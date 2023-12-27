import SettingSidebar from "../components/Settings/SettingSidebar";
import { DropdownHeader, MainContent, SidebarContainer, SidebarContent } from "../layout/DashboardLayout";
import AccountSettings from "../components/Settings/SettingsPages/AccountSettings";
import CategorySettings from "../components/Settings/SettingsPages/CategorySettings";
import CurrencySettings from "../components/Settings/SettingsPages/Currency/CurrencySettings";
import GeneralSettings from "../components/Settings/SettingsPages/GeneralSettings";
import TemplateSettings from "../components/Settings/SettingsPages/TemplateSettings";
import RulesSettings from "../components/Settings/SettingsPages/RulesSettings";
import LabelSettings from "../components/Settings/SettingsPages/Label/LabelSettings";
import BillingSettings from "../components/Settings/SettingsPages/BillingSettings";
import PrivacySettings from "../components/Settings/SettingsPages/PrivacySettings";
import HelpSettings from "../components/Settings/SettingsPages/HelpSettings";
import { useParams } from "react-router-dom/cjs/react-router-dom";

const SettingsPage = (props) => {
  let { pageId } = useParams();
  const getCurrentSettingPage = (page) => {
    switch (page) {
      case "accounts":
        return <AccountSettings />;
      case "category":
        return <CategorySettings />;
      case "currency":
        return <CurrencySettings />;
      case "template":
        return <TemplateSettings />;
      case "labels":
        return <LabelSettings />;
      case "rules":
        return <RulesSettings />;
      case "billing":
        return <BillingSettings />;
      case "general":
        return <GeneralSettings />;
      case "privacy":
        return <PrivacySettings />;
      case "help":
        return <HelpSettings />;
      default:
        return <AccountSettings />;
    }
  };
  return (
    <>
      <DropdownHeader></DropdownHeader>
      <SidebarContent>
        <SidebarContainer>
          <SettingSidebar />
        </SidebarContainer>
        <MainContent>{getCurrentSettingPage(pageId)}</MainContent>
      </SidebarContent>
    </>
  );
};

export default SettingsPage;
