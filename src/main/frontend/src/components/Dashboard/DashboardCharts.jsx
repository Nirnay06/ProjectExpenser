import { useState } from "react";
import ChartBox from "./Chartbox";
import styles from "./../../styles/components/chartbox.module.scss";

const DashBoardCharts = ({ selectedAccount, dateRange, periodLabel }) => {
  const [dragId, setDragId] = useState();
  const [boxes, setBoxes] = useState([
    {
      id: "LastRecords",
      color: "red",
      order: 1,
    },
    {
      id: "CashFlow",
      color: "green",
      order: 2,
    },
    {
      id: "CurrencyBalance",
      color: "blue",
      order: 3,
    },
    {
      id: "AccountList",
      color: "blue",
      order: 4,
    },
    {
      id: "SpendByCategory",
      color: "blue",
      order: 5,
    },
    {
      id: "ExpenseStructure",
      color: "blue",
      order: 6,
    },
    {
      id: "DashBoardGauge",
      color: "blue",
      order: 7,
    },
    {
      id: "BalanceTrend",
      color: "blue",
      order: 7,
    },
  ]);

  const handleDrag = (ev) => {
    setDragId(ev.currentTarget.id);
  };

  const handleDrop = (ev) => {
    // const dragBox = boxes.find((box) => box.id === dragId);
    // const dropBox = boxes.find((box) => box.id === ev.currentTarget.id);
    // const dragBoxOrder = dragBox.order;
    // const dropBoxOrder = dropBox.order;
    // const newBoxState = boxes.map((box) => {
    //   if (box.id === dragId) {
    //     box.order = dropBoxOrder;
    //   }
    //   if (box.id === ev.currentTarget.id) {
    //     box.order = dragBoxOrder;
    //   }
    //   return box;
    // });
    // setBoxes(newBoxState);
  };
  const handleDragOver = (ev) => {
    if (ev.currentTarget.id === dragId) {
      ev.preventDefault();
      return;
    }
    const dragBox = boxes.find((box) => box.id === dragId);
    const currentBox = boxes.find((box) => box.id === ev.currentTarget.id);
    const dragBoxOrder = dragBox.order;
    const currentBoxOrder = currentBox.order;

    const newBoxState = boxes.map((box) => {
      if (box.id === dragId) {
        box.order = currentBoxOrder;
      } else {
        if (currentBoxOrder < dragBoxOrder) {
          if (box.order >= currentBoxOrder && box.order < dragBoxOrder) {
            box.order += 1;
          }
        }
        if (currentBoxOrder > dragBoxOrder) {
          if (box.order <= currentBoxOrder && box.order > dragBoxOrder) {
            box.order -= 1;
          }
        }
      }
      return box;
    });

    setBoxes(newBoxState);
  };
  return (
    <>
      {boxes
        .sort((a, b) => a.order - b.order)
        .map((box) => (
          <ChartBox
            key={box.id}
            id={box.id}
            handleDrag={handleDrag}
            handleDrop={handleDrop}
            handleDragOver={handleDragOver}
            dateRange={dateRange}
            selectedAccount={selectedAccount}
            periodLabel={periodLabel}
          />
        ))}
      <div className={`${styles["chartbox__addMore"]}`}>
        <div className={`${styles["chartbox__addMore-circle"]}`}>+</div>
        <p className="font18 offsetT20 text--dark-3">Add Card</p>
      </div>
    </>
  );
};

export default DashBoardCharts;
