import { Link, useParams } from "react-router-dom";
import styles from "./../styles/Layouts/accountdetail.module.scss";
import icons from "./../assets/sprite.svg";
import { Button } from "antd";
import { WalletOutlined } from "@ant-design/icons";
import React, { useState } from "react";
import AccountBalanceChart from "../components/Account/AccountBalanceChart";
import AccountRecords from "../components/Account/AccountRecords";
import CustomModal from "../components/UI/Modal";
import AccountModalContainer from "../components/Account/AccountModal";

const AccountDetailPage = (props) => {
  const [activeContent, setActiveContent] = useState("record");
  const [showAccountModal, toggleAccountModal] = useState(false);
  const { accountIdentifier } = useParams();
  return (
    <>
      <CustomModal
        visible={showAccountModal}
        onCancel={() => {
          toggleAccountModal(false);
        }}
        title="Edit Account"
        bodyStyle={{ padding: 0, paddingBottom: "1rem" }}
      >
        <AccountModalContainer />
      </CustomModal>

      <div className={styles.accountdetail}>
        <div className={styles.accountdetail__header}>
          <div className={styles["accountdetail__header--button"]}>
            <Link to="/accounts" className={styles.header__back}>
              <svg
                className={`${styles.header__back_button} icon icon-circle-left`}
              >
                <use xlinkHref={`${icons}#icon-circle-left`}></use>
              </svg>
            </Link>
            <div className={styles.header__title}>
              <h2 className="heading--2 text--dark-2"> Account Details</h2>
            </div>
            <Button
              type="dashed"
              className={styles.header__buttons}
              shape="round"
              onClick={() => {
                toggleAccountModal(true);
              }}
            >
              Edit
            </Button>
            <Button
              type="dashed"
              danger
              className={styles.header__buttons}
              shape="round"
            >
              Delete
            </Button>
          </div>
          <div className={styles["accountdetail__header--detail"]}>
            <WalletOutlined
              className={styles.account__icon}
              style={{ backgroundColor: "rgb(247, 150, 4)" }}
            />
            <div className={styles.account__details}>
              <div className={styles.account__detail}>
                <span className={styles["account__detail--title"]}>Name</span>
                <span className={styles["account__detail--value"]}>
                  Test Account
                </span>
              </div>
              <div className={styles.account__detail}>
                <span className={styles["account__detail--title"]}>
                  Category
                </span>
                <span className={styles["account__detail--value"]}>
                  General
                </span>
              </div>
              <div className={styles.account__info}></div>
            </div>
          </div>
          <div className={styles["accountdetail__header--tabs"]}>
            <div
              className={`${styles["navigation__tab"]} ${
                activeContent === "balance" && styles[`navigation__tab-active`]
              }`}
              onClick={() => {
                setActiveContent("balance");
              }}
            >
              <span className={styles["navigation__tab--text"]}>Balance</span>
            </div>
            <div
              className={`${styles["navigation__tab"]} ${
                activeContent === "record" && styles[`navigation__tab-active`]
              }`}
              onClick={() => {
                setActiveContent("record");
              }}
            >
              <span className={styles["navigation__tab--text"]}>Records</span>
            </div>
          </div>
        </div>
        <div className={styles.accountdetail__content}>
          {activeContent === "balance" && (
            <AccountBalanceChart></AccountBalanceChart>
          )}
          {activeContent === "record" && (
            <>
              <AccountRecords></AccountRecords>
            </>
          )}
        </div>
      </div>
    </>
  );
};

//https://codepen.io/rpsthecoder/pen/mjoMMm
export default AccountDetailPage;
