import styles from "./../../styles/components/AccountRecords.module.scss";
import { getMonthAndDate } from "../../utils/DateUtil";
import { Checkbox, Dropdown, Menu } from "antd";
import { EllipsisOutlined } from "@ant-design/icons";
import icons from "./../../assets/sprite.svg";
import { useState } from "react";
import Modal from "../UI/Modal";
import RecordModalContainer from "../Record/RecordModal";

const AccountRecord = (props) => {
  const [showRecordModal, toggleRecordModal] = useState(false);
  const { title, account, amount } = props.record;
  const onRecordModal = (e) => {
    toggleRecordModal(true);
  };
  const menu = (
    <Menu>
      <Menu.Item key="edit" onClick={onRecordModal}>
        Edit
      </Menu.Item>

      <Menu.Item key="delete">Delete</Menu.Item>
    </Menu>
  );
  return (
    <>
      {showRecordModal && (
        <Modal
          headerTitle="Edit Account"
          onCloseModal={() => {
            toggleRecordModal(false);
          }}
        >
          <RecordModalContainer />
        </Modal>
      )}
      <tr className={styles.table__row}>
        <td className={styles.table__row__item}>
          <Checkbox
            className="checkbox"
            name={props.record.title}
            value={props.record.key}
            checked={props.checkedList.has(props.record.key)}
            onChange={props.onRecordChecked}
          />
        </td>
        <div className={styles.table__row__data} onClick={onRecordModal}>
          <td className={styles.table__row__item}>
            <div className={styles.table__row__icon}>
              <svg>
                <use href={`${icons}#icon-knife-fork`}></use>
              </svg>
            </div>
            <span>{title}</span>
          </td>

          <td className={styles.table__row__item}>
            <div className="dot yellow-bacground"></div>
            <span>{account}</span>
          </td>
          <td
            className={`${styles.table__row__item} ${styles["table__row__item-right"]}`}
          >
            <span>{`$${amount}`}</span>
          </td>
        </div>
        <td
          className={`${styles.table__row__item} ${styles["table__row__item-right"]}`}
        >
          <Dropdown overlay={menu}>
            <EllipsisOutlined style={{ fontSize: "2rem" }} />
          </Dropdown>
        </td>
      </tr>
    </>
  );
};
const AccountRecordGroup = (props) => {
  const { date, records, onRecordChecked, checkedList } = props;
  const monthYear = getMonthAndDate(date);
  return (
    <>
      <tr className={`${styles.table__group__header}`}>
        <td>{monthYear}</td>
      </tr>

      {records.map((record, index) => {
        return (
          <AccountRecord
            key={index}
            record={record}
            onRecordChecked={onRecordChecked}
            checkedList={checkedList}
          ></AccountRecord>
        );
      })}
    </>
  );
};

export default AccountRecordGroup;
