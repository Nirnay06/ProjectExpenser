import styles from "./../../styles/components/AccountRecords.module.scss";
import { getMonthAndDate } from "../../utils/DateUtil";
import { Checkbox, Dropdown, Menu, Tag } from "antd";
import { EllipsisOutlined } from "@ant-design/icons";
import icons from "./../../assets/sprite.svg";
import { useState } from "react";
import moment from "moment";

import RecordModalContainer from "../Record/RecordModal";
import CustomModal from "../UI/Modal";
import RecordDeleteModal from "../Record/RecordDeleteModal";
import {getCurrencyFormatted} from "../../utils/StringUtils";

const AccountRecord = (props) => {
  const [showRecordModal, toggleRecordModal] = useState(false);
  const [showDeleteModal, toggleDeleteModal] = useState(false);
  const { recordCategory, account, amount, labels,  location } = props.record;
  const onRecordModal = (e) => {
    toggleRecordModal(true);
  };
  const menu = (
    <Menu>
      <Menu.Item key="edit" onClick={onRecordModal}>
        Edit
      </Menu.Item>

      <Menu.Item key="delete" onClick={()=>{toggleDeleteModal(true)}}>Delete</Menu.Item>
    </Menu>
  );
  return (
    <>
      <CustomModal
        visible={showRecordModal}
        onCancel={() => {
          toggleRecordModal(false);
        }}
        title="Edit Record"
        bodyStyle={{ padding: 0, paddingBottom: "1rem" }}>
        <RecordModalContainer
          initialValues={{ ...props.record }}
          onSaveCloseModal={() => {
            toggleRecordModal(false);
          }}
        />
      </CustomModal>
      <CustomModal
        visible={showDeleteModal}
        onCancel={() => {
          toggleDeleteModal(false);
        }}
        title="Wait"
        width={500}>
        <RecordDeleteModal
          recordIdentifier={props.record.recordIdentifier}
          closePopup={() => {
            toggleDeleteModal(false);
          }}
        />
      </CustomModal>
      <tr className={styles.table__row}>
        <td className={styles.table__row__item}>
          <Checkbox
            className="checkbox"
            name={props.record.title}
            value={props.record.recordIdentifier}
            checked={props.checkedList.has(props.record.recordIdentifier)}
            onChange={props.onRecordChecked}
          />
        </td>
        <div className={styles.table__row__data} onClick={onRecordModal}>
          <td className={`${styles.table__row__item}  ${styles["table__row__item-left"]}`}>
            <div className="dot dot-icon" style={{ backgroundColor: recordCategory?.color }}>
              <svg className={`icon icon-${recordCategory?.icon}`}>
                <use href={`${icons}#icon-${recordCategory?.icon}`}></use>
              </svg>
            </div>
            <span>{recordCategory?.title}</span>
          </td>

          <td className={`${styles.table__row__item}  ${styles["table__row__item-center"]}`}>
            <div className="dot yellow-bacground"></div>
            <span>{account.accountName}</span>
            <span style={{marginLeft : '5rem'}}>{labels.slice(0,3).map((label) => {
             
              return (
                <Tag color={label.userLabel.color} key={label.identifier} style={{fontSize : '1rem'}}>
                  {label.userLabel.title}
                </Tag>
          );
              })} 
                {labels.length > 3 && <Tag key={'+'}  style={{fontSize : '1rem'}}>+{labels.length - 3 } more</Tag>}
              </span>
          </td>
          <td className={`${styles.table__row__item} ${styles["table__row__item-right"]}`}>
            <span>{`${getCurrencyFormatted(amount)}`}</span>
          </td>
        </div>
        <td className={`${styles.table__row__item} ${styles["table__row__item-right"]}` }>
       {location && location.identifier && <svg className={`icon icon-location`}> 
                <use href={`${icons}#icon-location-on`}></use>
              </svg>}
        </td>
        <td className={`${styles.table__row__item} ${styles["table__row__item-right"]}`} >
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
        return <>{<AccountRecord key={index} record={record} onRecordChecked={onRecordChecked} checkedList={checkedList}></AccountRecord>}</>;
      })}
    </>
  );
};

export default AccountRecordGroup;
