import { Input, Button, Space } from "antd";
import React, { useContext, useEffect, useState } from "react";
import icons from "./../../../../assets/sprite.svg";
import styles from "./../../Settings.module.scss";
import UserContext from "../../../../store/UserContext";
import useServices from "../../../../hooks/useSevices";
import { getCurrencyFormatted } from "../../../../utils/StringUtils";
import CustomModal from "../../../UI/Modal";
import SortedTable from "../../../UI/SortedTable";
import AddEditLabelModal from "./AddEditLabelModal";

const LabelSettings = () => {
  const userCtx = useContext(UserContext);
  const [labelList, setLabelList] = useState([]);
  const [labelNameInput, setLabelName] = useState(null);
  const [editLabelObj, setEditLabelObj] = useState(null);
  const [showAddEditLabelModal, setShowAddEditLabelModal] = useState(false);
  const { LabelService } = useServices();
  useEffect(() => {
    LabelService.fetchAllLabelsByUserForRecord(setLabelList);
  }, [userCtx.refresh]);

  const columns = [
    {
      title: "Type",
      dataIndex: "currencyAbbreviation",
      width: "10%",
      render: (_, record) => {
        return "a";
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
              handleEditLabel(record);
            }}
          >
            Edit
          </Button>
          <Button
            type="link"
            danger
            onClick={() => {
              handleDeleteLabel(record.identifier);
            }}
          >
            Delete
          </Button>
        </Space>
      ),
    },
  ];

  const handleEditLabel = (account) => {
    setShowAddEditLabelModal(true);
    setEditLabelObj(account);
  };
  const handleDeleteLabel = (identifier) => {
    // AccountService.removeUserAccount(identifier);
  };
  const handleOnLabelNameChange = (e) => {
    setLabelName(e.target.value);
  };

  const handleAddLabel = () => {
    setEditLabelObj(null);
    setShowAddEditLabelModal(true);
  };
  return (
    <>
      <CustomModal
        visible={showAddEditLabelModal}
        onCancel={() => {
          setShowAddEditLabelModal(false);
        }}
        centered
        title={editLabelObj ? "Edit Label" : "Add Label"}
        width={500}
        bodyStyle={{ padding: "2rem", paddingBottom: "3rem" }}
      >
        <AddEditLabelModal
          closePopup={() => {
            setShowAddEditLabelModal(false);
          }}
          initialValues={editLabelObj ? editLabelObj : { accountName: labelNameInput }}
        />
      </CustomModal>
      <div>
        <div className={styles["Settings_main__header"]}>
          <h2 className="font18">Labels</h2>
        </div>
        <div className={`${styles.Settings_main__subheader} gutter30`}>
          <h4 className="font16 offsetB10">Add a new Label</h4>
          <Input
            style={{
              width: 300,
              borderRadius: 0,
            }}
            size="large"
            placeholder="Label Name"
            value={labelNameInput}
            onChange={handleOnLabelNameChange}
          />
          <Button type="primary" size={"large"} className="offsetL10" onClick={handleAddLabel}>
            + Add
          </Button>
        </div>
        <div className="gutterT30 gutterB30">
          <h4 className="font16 offsetB10 gutterL30">Your Labels</h4>
        </div>
        <SortedTable pagination={false} list={labelList} setUpdatedData={setLabelList} columns={columns} className={"offsetL10 offsetR10"} />
      </div>
    </>
  );
};

export default LabelSettings;
