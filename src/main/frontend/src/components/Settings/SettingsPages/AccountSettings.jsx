import { Input, Button, Space } from "antd";
import React, { useContext, useEffect, useState } from "react";
import icons from "./../../../assets/sprite.svg";
import styles from "./../Settings.module.scss";
import UserContext from "../../../store/UserContext";
import useServices from "../../../hooks/useSevices";
import { getCurrencyFormatted } from "../../../utils/StringUtils";
import CustomModal from "../../UI/Modal";
import SortedTable from "../../UI/SortedTable";
import AccountModalContainer from "../../Account/AccountModal";

const AccountSettings = () => {
  const userCtx = useContext(UserContext);
  const [accountList, setAccountList] = useState([]);
  const [accountNameInput, setAccountName] = useState(null);
  const [editAccountObj, setEditAccountObj] = useState(null);
  const [showAddEditAccountModal, setShowAddEditAccountModal] = useState(false);
  const { AccountService } = useServices();
  useEffect(() => {
    AccountService.fetchAllUserBankaccounts(setAccountList);
  }, [userCtx.refresh]);

  const columns = [
    {
      title: "Type",
      dataIndex: "currencyAbbreviation",
      width: "10%",
      render: (_, record) => {
        const value = AccountService.getAccountsType().find((t) => t.identifier === record.accountType);
        return (
          <svg className={`accountIcon accountIcon-${value?.icon}-tiny`}>
            <use href={`${icons}#icon-${value?.icon}-tiny`}></use>
          </svg>
        );
      },
    },
    {
      title: "Name",
      dataIndex: "accountName",
      width: "50%",
    },
    {
      title: "Balance",
      dataIndex: "conversionRate",
      width: "20%",
      render: (text, record) => {
        return getCurrencyFormatted(record.accountBalance);
      },
    },
    {
      title: "Last Update",
      dataIndex: "currencyTitle",
      width: "30%",
    },
    {
      title: "Action",
      key: "action",
      width: "30%",
      render: (_, record) => (
        <Space size="middle">
          <Button
            type="link"
            onClick={() => {
              handleEditAccount(record);
            }}
          >
            Edit
          </Button>
          <Button
            type="link"
            danger
            onClick={() => {
              handleDeleteAccount(record.identifier);
            }}
          >
            Delete
          </Button>
        </Space>
      ),
    },
  ];

  const handleEditAccount = (account) => {
    setShowAddEditAccountModal(true);
    setEditAccountObj(account);
  };
  const handleDeleteAccount = (identifier) => {
    AccountService.removeUserAccount(identifier);
  };
  const handleOnAccountNameChange = (e) => {
    setAccountName(e.target.value);
  };

  const handleAddCurrency = () => {
    setEditAccountObj(null);
    setShowAddEditAccountModal(true);
  };
  return (
    <>
      <CustomModal
        visible={showAddEditAccountModal}
        onCancel={() => {
          setShowAddEditAccountModal(false);
        }}
        title={editAccountObj ? "Edit Account" : "Add Account"}
        width={600}
        centered
        bodyStyle={{ padding: "2rem", paddingBottom: "3rem" }}
      >
        <AccountModalContainer
          closePopup={() => {
            setShowAddEditAccountModal(false);
          }}
          initialValues={editAccountObj ? editAccountObj : { accountName: accountNameInput }}
        />
      </CustomModal>
      <div>
        <div className={styles["Settings_main__header"]}>
          <h2 className="font18">Accounts</h2>
        </div>
        <div className={`${styles.Settings_main__subheader} gutter30`}>
          <h4 className="font16 offsetB10">Add a new Account</h4>
          <Input
            style={{
              width: 300,
              borderRadius: 0,
            }}
            size="large"
            placeholder="Account Name"
            value={accountNameInput}
            onChange={handleOnAccountNameChange}
          />
          <Button type="primary" size={"large"} className="offsetL10" onClick={handleAddCurrency}>
            + Add
          </Button>
        </div>
        <div className="gutterT30 gutterB30">
          <h4 className="font16 offsetB10 gutterL30">Your Accounts</h4>
        </div>
        <SortedTable pagination={false} list={accountList} setUpdatedData={setAccountList} columns={columns} className={"offsetL10 offsetR10"} />
      </div>
    </>
  );
};

export default AccountSettings;
