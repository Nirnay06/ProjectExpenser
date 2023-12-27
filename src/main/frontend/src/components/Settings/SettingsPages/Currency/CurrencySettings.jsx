import { Select, Button, Space } from "antd";
import styles from "./../../Settings.module.scss";
import useServices from "../../../../hooks/useSevices";
import React, { useContext, useEffect, useState } from "react";
import { CaretDownOutlined } from "@ant-design/icons";

import CustomModal from "../../../UI/Modal";
import AddCurrencyModal from "./AddCurrencyModal";
import UserContext from "../../../../store/UserContext";
import { render } from "@testing-library/react";
import { getCurrencyFormatted } from "../../../../utils/StringUtils";
import SortedTable from "../../../UI/SortedTable";

const CurrencySettings = () => {
  const userCtx = useContext(UserContext);
  const [unassignedCurrencies, setUnassignedCurrencies] = useState([]);
  const [assignedCurrencies, setAssignedCurrencies] = useState([]);
  const [selectedCurrency, setSelectedCurrency] = useState(null);
  const [editCurrencyObj, setEditCurrencyObj] = useState(null);
  const [showAddCurrencyModal, setShowAddCurrencyModal] = useState(false);
  const { CurrencyService } = useServices();
  useEffect(() => {
    CurrencyService.fetchAllUnassignedCurrenciesForUser(setUnassignedCurrencies);
    CurrencyService.fetchCurrencyListForRecord(setAssignedCurrencies);
  }, [userCtx.refresh]);
  const sorted = assignedCurrencies.sort((a, b) => a.displayOrder - b.displayOrder);
  const items = unassignedCurrencies.map((cur) => {
    return { label: cur.currencyAbbreviation + " - " + cur.currencyTitle, value: cur.identifier };
  });
  const baseCurrency = assignedCurrencies.find((c) => c.baseCurrency);
  const columns = [
    {
      key: "sort",
    },
    {
      title: "",
      dataIndex: "currencyAbbreviation",
      width: "10%",
    },
    {
      title: "Name",
      dataIndex: "currencyTitle",
      width: "30%",
    },
    {
      title: "Ratio",
      dataIndex: "conversionRate",
      width: "50%",
      render: (text, record) => {
        if (record.baseCurrency) {
          return <Space>This is your base currency</Space>;
        }
        const base1 = getCurrencyFormatted(1, { currency: baseCurrency.currencyAbbreviation });
        const currRatio = getCurrencyFormatted(record.currencyRate, { currency: record.currencyAbbreviation });
        const baseInverse = getCurrencyFormatted(1 / record.currencyRate, { currency: baseCurrency.currencyAbbreviation });
        const curr1 = getCurrencyFormatted(1, { currency: record.currencyAbbreviation });
        return (
          <>
            <Space className="offsetR40">{`${base1} = ${currRatio}`}</Space>
            <Space>{`${curr1} = ${baseInverse}`}</Space>
          </>
        );
      },
    },
    {
      title: "Action",
      key: "action",
      render: (_, record) => (
        <Space size="middle">
          <Button
            type="link"
            onClick={() => {
              handleEditCurrency(record);
            }}
          >
            Edit
          </Button>
          <Button
            type="link"
            danger
            onClick={() => {
              handleDeletCurrency(record.identifier);
            }}
          >
            Delete
          </Button>
        </Space>
      ),
    },
  ];

  const handleEditCurrency = (currency) => {
    setShowAddCurrencyModal(true);
    setEditCurrencyObj(currency);
  };
  const handleDeletCurrency = (identifier) => {
    //delete currency
    CurrencyService.removeUserCurrency(identifier);
  };
  const handleOnCurrencySelect = (value) => {
    setSelectedCurrency(value);
  };

  const handleAddCurrency = () => {
    setEditCurrencyObj(false);

    setShowAddCurrencyModal(true);
  };
  return (
    <>
      <CustomModal
        visible={showAddCurrencyModal}
        onCancel={() => {
          setShowAddCurrencyModal(false);
        }}
        title={editCurrencyObj ? "Edit Currency" : "Add Currency"}
        width={600}
        centered
        bodyStyle={{ padding: "2rem", paddingBottom: "3rem" }}
      >
        <AddCurrencyModal
          setSelectedCurrency={setSelectedCurrency}
          selectedCurrency={selectedCurrency}
          assignedCurrencies={assignedCurrencies}
          items={items}
          editCurrencyObj={editCurrencyObj}
          unassignedCurrencies={unassignedCurrencies}
          closePopup={() => {
            setShowAddCurrencyModal(false);
          }}
        />
      </CustomModal>
      <div>
        <div className={styles["Settings_main__header"]}>
          <h2 className="font18">Currencies</h2>
        </div>
        <div className={`${styles.Settings_main__subheader} gutter30`}>
          <h4 className="font16 offsetB10">Add a new Currency</h4>
          <Select
            showSearch
            style={{
              width: 300,
              borderRadius: 0,
            }}
            size="large"
            placeholder="Search to Select"
            suffixIcon={<CaretDownOutlined />}
            optionFilterProp="children"
            value={selectedCurrency}
            filterOption={(input, option) => (option?.label.toLowerCase() ?? "").includes(input.toLowerCase())}
            filterSort={(optionA, optionB) => (optionA?.label ?? "").toLowerCase().localeCompare((optionB?.label ?? "").toLowerCase())}
            options={items}
            onSelect={handleOnCurrencySelect}
          />
          <Button type="primary" size={"large"} className="offsetL10" onClick={handleAddCurrency}>
            + Add
          </Button>
        </div>
        <div className="gutterT30 gutterB30">
          <h4 className="font16 offsetB10 gutterL30">Your Currencies</h4>
        </div>
        <SortedTable pagination={false} list={sorted} setUpdatedData={setAssignedCurrencies} columns={columns} sortProperty={"displayOrder"} />
      </div>
    </>
  );
};

export default CurrencySettings;
