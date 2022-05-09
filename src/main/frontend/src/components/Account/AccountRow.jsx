import { Link } from "react-router-dom";
import { ContentRow } from "../../layout/DashboardLayout";
import styles from "./../../styles/components/AccountRow.module.scss";

import { MoneyCollectOutlined, WalletOutlined } from "@ant-design/icons";

{
  /* <WalletTwoTone />
  <MoneyCollectOutlined /> */
}
const AccountRow = (props) => {
  const { accountIdentifier } = props;
  return (
    <ContentRow>
      <Link
        className={styles.Account}
        to={`/accounts/account/${accountIdentifier}`}
      >
        <div className={styles.Account__info}>
          <WalletOutlined
            className={styles.Account__icon}
            style={{ "background-color": "rgb(247, 150, 4)" }}
          />
          <span>uhgyu</span>
        </div>
        <span>General</span>
        <span>$87484</span>
      </Link>
    </ContentRow>
  );
};
export default AccountRow;
