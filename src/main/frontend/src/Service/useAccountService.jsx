import { Col, Row, Select } from "antd";
import icons from "../assets/sprite.svg";
import useHttp from "../hooks/useHttp";
import { useContext } from "react";
import UserContext from "../store/UserContext";

const useAccountService = () => {
  const { sendRequest } = useHttp();
  const userCtx = useContext(UserContext);
  const getAccountDisplayRowForDrowdown = (value) => {
    return (
      <Row align="middle">
        <Col
          style={{
            marginRight: "5px",
          }}
        >
          {value.icon ? (
            <svg className={`accountIcon accountIcon-${value.icon}-tiny`}>
              <use href={`${icons}#icon-${value.icon}-tiny`}></use>
            </svg>
          ) : (
            <></>
          )}
        </Col>
        <Col>
          <span>{value.accountName}</span>
        </Col>
      </Row>
    );
  };
  const getAccountOptionForDropdown = (value) => {
    console.log(value);
    return (
      <Select.Option value={value.identifier} label={getAccountDisplayRowForDrowdown(value)} key={value.identifier}>
        {getAccountDisplayRowForDrowdown(value)}
      </Select.Option>
    );
  };

  const fetchAccountsForRecord = (setData) => {
    sendRequest({ url: "/api/account/getActiveClientAccount" }, (data) => {
      setData(data);
    });
  };

  const fetchAllUserBankaccounts = (setData) => {
    sendRequest({ url: "/api/account/allUserBankAccount" }, (data) => {
      setData(data);
    });
  };

  const getAccountDetailsByIdentifier = (accountIdentifier, setAccountDetail) => {
    sendRequest({ url: `/api/account/details/${accountIdentifier}` }, (data) => {
      setAccountDetail(data);
    });
  };

  const getAllRecordsByAccount = (accountIdentifier, setAccountRecords) => {
    sendRequest({ url: `/api/account/records/${accountIdentifier}` }, (data) => {
      setAccountRecords(data);
    });
  };

  const getAccountsType = () => {
    return [
      {
        accountName: "General",
        icon: "wallet",
        identifier: "General",
      },
      {
        accountName: "Cash",
        icon: "coins",
        identifier: "Cash",
      },
      {
        accountName: "Current Account",
        icon: "safe",
        identifier: "Current_Account",
      },
      {
        accountName: "Credit Card",
        icon: "credit-card1",
        identifier: "Credit_Card",
      },
      {
        accountName: "Account with Overdraft",
        icon: "banknote",
        identifier: "Account_With_Overdraft",
      },
      {
        accountName: "Saving Account",
        icon: "pig1",
        identifier: "Saving_Account",
      },
      {
        accountName: "Bonus",
        icon: "dollar1",
        identifier: "Bonus",
      },
      {
        accountName: "Insurance",
        icon: "coin-yen",
        identifier: "Insurance",
      },
      {
        accountName: "Investment",
        icon: "coin-pound",
        identifier: "Investment",
      },
      {
        accountName: "Loan",
        icon: "coin-euro",
        identifier: "Loan",
      },
      {
        accountName: "Mortgage",
        icon: "library",
        identifier: "Mortgage",
      },
    ];
  };

  const addOrEditAccount = (values, setAccountDetail) => {
    sendRequest({ url: "/api/account/addOrEditAccount", method: "POST", headers: { "Content-Type": "application/json" }, body: values }, (data) => {
      setAccountDetail(data);
      userCtx.triggerRefresh();
    });
  };

  const getAccountIconByType = (accounType) => {
    return getAccountsType().find((ac) => ac.accountName === accounType)?.icon;
  };
  return {
    AccountService: {
      getAccountOptionForDropdown,
      fetchAccountsForRecord,
      fetchAllUserBankaccounts,
      getAccountDetailsByIdentifier,
      getAllRecordsByAccount,
      getAccountsType,
      addOrEditAccount,
      getAccountIconByType,
    },
  };
};
export default useAccountService;
