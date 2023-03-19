import { Button, Checkbox } from "antd";
import { useState } from "react";
import styles from "./../../styles/components/AccountRecords.module.scss";
import AccountRecordGroup from "./AccountRecordGroup";
const AccountRecords = (props) => {
  const [checkedList, setCheckedList] = useState(new Set());
  const [indeterminate, setIndeterminate] = useState(false);
  const [checkAll, setCheckAll] = useState(false);
  const records = [
    { title: "A", account: "B", amount: 123, key: "A1", date: "11-03-2022" },
    { title: "A", account: "B", amount: 123, key: "B1", date: "11-03-2022" },

    {
      title: "A2",
      account: "B2",
      amount: 123,
      key: "A3",
      date: "12-03-2022",
    },
    {
      title: "A3",
      account: "B3",
      amount: 123,
      key: "A4",
      date: "12-03-2022",
    },
    {
      title: "A4",
      account: "B4",
      amount: 123,
      key: "A5",
      date: "12-03-2022",
    },

    {
      title: "A9",
      account: "B9",
      amount: 123,
      key: "10",
      date: "09-03-2022",
    },
    {
      title: "A12",
      account: "B12",
      amount: 123,
      key: "11",
      date: "09-03-2022",
    },
    {
      title: "A9",
      account: "B9",
      amount: 123,
      key: "12",
      date: "09-03-2022",
    },
    {
      title: "A12",
      account: "B12",
      amount: 123,
      key: "13",
      date: "09-03-2022",
    },
    {
      title: "A9",
      account: "B9",
      amount: 123,
      key: "14",
      date: "09-03-2022",
    },
    {
      title: "A12",
      account: "B12",
      amount: 123,
      key: "15",
      date: "09-03-2022",
    },
    {
      title: "A9",
      account: "B9",
      amount: 123,
      key: "16",
      date: "09-03-2022",
    },
    {
      title: "A12",
      account: "B12",
      amount: 123,
      key: "17",
      date: "09-03-2022",
    },
  ];

  const onCheckAllChange = (e) => {
    setCheckedList(e.target.checked ? new Set(records.map((r) => r.key)) : new Set());
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
        if (prev.size === records.length) {
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
  const recordsByDate = records.reduce(
    (dates, item) => ({
      ...dates,
      [item.date]: [...(dates[item.date] || []), item],
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
            ${records.filter((r) => checkedList.size === 0 || checkedList.has(r.key)).reduce((sum, r) => (sum = sum + r.amount), +0)}
          </span>
        </td>
      </thead>
      {recordGroups}
    </table>
  );
};

export default AccountRecords;
