import { Button, Dropdown, Menu, Select } from "antd";
import { DownOutlined } from "@ant-design/icons";

import { DropdownHeader, DropdownHeaderItem, MainContent, SidebarContainer, SidebarContent } from "../layout/DashboardLayout";
import SearchField from "../components/UI/SearchField";
import AccountRow from "../components/Account/AccountRow";
import { useEffect, useState, useContext } from "react";
import useServices from "../hooks/useSevices";
import CustomModal from "../components/UI/Modal";
import AccountModalContainer from "../components/Account/AccountModal";
import UserContext from "../store/UserContext";

const AccountsPage = (props) => {
  const [accountsList, setAccountsList] = useState([]);
  const [showAccountModal, toggleAccountModal] = useState(false);
  const [searchValue, setSearchValue] = useState(null);
  const [sortValue, setSortValue] = useState(1);
  const { AccountService } = useServices();
  const userCtx = useContext(UserContext);
  useEffect(() => {
    AccountService.fetchAllUserBankaccounts(setAccountsList);
  }, [userCtx.refresh]);
  
  const AccountSortFunction = (a,b)=>{
    if(sortValue==1){
      return 0;
    }else if(sortValue==2){
       return a.accountName.localeCompare(b.accountName);
    }else if(sortValue==3){
      return b.accountName.localeCompare(a.accountName);
    }  else if(sortValue==4){
       return  a.accountBalance - b.accountBalance;
    } else if(sortValue==5){
      return  b.accountBalance - a.accountBalance;
    }
  }
  return (
    <>
     <CustomModal
        visible={showAccountModal}
        onCancel={() => {
          toggleAccountModal(false);
        }}
        title="Edit Account"
        width={600}
        bodyStyle={{ padding: '2rem', paddingBottom: "3rem" }}>
        <AccountModalContainer 
          closePopup={() => { toggleAccountModal(false); }} 
         />
      </CustomModal>
     <DropdownHeader>
        <DropdownHeaderItem>
        <h3>Sort By:</h3>
        <Select
            defaultValue="1"
            style={{
              width: 180,
            }}
            onChange={(e)=>{setSortValue(e)}}
            options={[{ value: '1',  label: 'Default', },
                      {  value: '2', label: 'A-Z',  },
                      { value: '3', label: 'Z-A', },
                      { value: '4', label: 'Balance (Lowest first)',  },
                      { value: '5', label: 'Balance (Highest first)', },  ]}
          />
        </DropdownHeaderItem>
      </DropdownHeader>
      
      <SidebarContent>
        <SidebarContainer>
          <h2 className="heading--2 text--dark-2">Accounts</h2>
          <Button type="primary" shape="round" onClick={() => {toggleAccountModal(true)}} size="large">
            + Add
          </Button>
          <SearchField searchValue={searchValue} setSearchValue={setSearchValue}/>
        </SidebarContainer>
        <MainContent className="transparent" gridRow>
          {accountsList &&
            accountsList.filter(ac =>
              !searchValue || ac.accountName.toLowerCase().includes(searchValue.toLowerCase())
           ).sort(AccountSortFunction).map((account, index) => {
              return <AccountRow account={account} key={index} />;
            })}
        </MainContent>
      </SidebarContent>
    </>
  );
};

export default AccountsPage;
