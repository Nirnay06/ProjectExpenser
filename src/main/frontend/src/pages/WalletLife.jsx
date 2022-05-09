import { useState } from "react";
import ChartBox from "../components/Dashboard/Chartbox";
import {
  DropdownHeader,
  DropdownHeaderItem,
  GridContent,
} from "../layout/DashboardLayout";

const WalletLifePage = (props) => {
  return (
    <>
      <DropdownHeader>
        <h2 className="heading--2 center uppercase">Wallet Life</h2>
      </DropdownHeader>
      <GridContent>
        <ChartBox />
        <ChartBox />
        <ChartBox />
        <ChartBox />
      </GridContent>
    </>
  );
};

export default WalletLifePage;
