import { Button, Dropdown, Menu } from "antd";
import { DownOutlined } from "@ant-design/icons";

import {
  DropdownHeader,
  DropdownHeaderItem,
  MainContent,
  SidebarContainer,
  SidebarContent,
} from "../layout/DashboardLayout";
import SearchField from "../components/UI/SearchField";
import AccountRow from "../components/Account/AccountRow";
import { RouteWithSubRoutes } from "../utils/RoutesComponent";
import { Switch } from "react-router";

const AccountsPage = (props) => {
  function handleMenuClick(e) {
  }

  const menu = (
    <Menu onClick={handleMenuClick}>
      <Menu.Item key="1">1st menu item</Menu.Item>
      <Menu.Item key="1">1st menu item</Menu.Item>
      <Menu.Item key="1">1st menu item</Menu.Item>
      <Menu.Item key="1">1st menu item</Menu.Item>
    </Menu>
  );
  return (
    <>
      <DropdownHeader>
        <DropdownHeaderItem>
          <Dropdown overlay={menu}>
            <Button>
              Button <DownOutlined />
            </Button>
          </Dropdown>
        </DropdownHeaderItem>
      </DropdownHeader>
      <SidebarContent>
        <SidebarContainer>
          <h2 className="heading--2 text--dark-2">Accounts</h2>
          <Button type="primary" shape="round" onClick={() => {}} size="large">
            + Add
          </Button>
          <SearchField />
        </SidebarContainer>
        <MainContent className="transparent" gridRow>
          <AccountRow accountIdentifier={"1"} />
          <AccountRow accountIdentifier={"2"} />
          <AccountRow accountIdentifier={"3"} />
          <AccountRow />
          <AccountRow />
        </MainContent>
      </SidebarContent>
    </>
  );
};

export default AccountsPage;
