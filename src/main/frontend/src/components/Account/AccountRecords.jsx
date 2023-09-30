import { Button, Checkbox } from "antd";
import { useEffect, useState } from "react";
import styles from "./../../styles/components/AccountRecords.module.scss";
import AccountRecordGroup from "./AccountRecordGroup";
import useServices from "../../hooks/useSevices";
import { useContext } from "react";
import UserContext from "../../store/UserContext";
import {getCurrencyFormatted} from "../../utils/StringUtils";
const AccountRecords = (props) => {
  const [checkedList, setCheckedList] = useState(new Set());
  const [indeterminate, setIndeterminate] = useState(false);
  const [checkAll, setCheckAll] = useState(false);
  const [accountRecords, setAccountrecords] = useState([]);
  const { AccountService } = useServices();
  const userCtx = useContext(UserContext);
  useEffect(() => {
    if (props.accountIdentifier) {
      AccountService.getAllRecordsByAccount(props.accountIdentifier, setAccountrecords);
    }
  }, [userCtx.refresh]);

  const onCheckAllChange = (e) => {
    setCheckedList(e.target.checked ? new Set(accountRecords.map((r) => r.recordIdentifier)) : new Set());
    setIndeterminate(false);
    setCheckAll(e.target.checked);
  };
  const onRecordChecked = (e) => {
    setCheckedList((prev) => {
      e.target.checked ? prev.add(e.target.value) : prev.delete(e.target.value);
      if (prev.size === 0) {
        setIndeterminate(false);
        setCheckAll(false);
      } else {
        if (prev.size === accountRecords.length) {
          setCheckAll(true);
          setIndeterminate(false);
        } else {
          setCheckAll(false);
          setIndeterminate(true);
        }
      }
      return new Set(prev);
    });
  };
  const recordsByDate = accountRecords.reduce(
    (dates, item) => ({
      ...dates,
      [item.recordDate]: [...(dates[item.recordDate] || []), item],
    }),
    {}
  );
  let recordGroups = [];

  for (let date in recordsByDate) {
    recordGroups.push(
      <AccountRecordGroup
        key={date.toString()}
        date={date}
        records={recordsByDate[date]}
        onRecordChecked={onRecordChecked}
        checkedList={checkedList}
        onRecordClicked={props.onRecordClicked}></AccountRecordGroup>
    );
  }

  return (
    <table className={styles.table}>
      <thead className={styles.table__header}>
        <td className={styles.table__header__item}>
          <Checkbox indeterminate={indeterminate} onChange={onCheckAllChange} checked={checkAll}>
            <span>{`${checkAll ? "De-Select All" : "Select All"}`}</span>
          </Checkbox>
        </td>
        <td className={styles.table__header__item}>
          {checkedList.size > 0 && (
            <>
              <span>{`${checkedList.size} Rows selected`}</span>
              <Button className={styles.table__header__button} size="small" type="primary">
                Edit
              </Button>
              <Button className={styles.table__header__button} size="small" type="primary" danger>
                Delete
              </Button>
            </>
          )}
        </td>
        <td className={styles.table__header__item}>
          <span className={`text--dark-2`}>
            {getCurrencyFormatted(accountRecords
              .filter((r) => checkedList.size === 0 || checkedList.has(r.recordIdentifier))
              .reduce((sum, r) => (sum = sum + r.amount), +0))}
          </span>
        </td>
      </thead>
      {recordGroups}
    </table>
  );
};

export default AccountRecords;
