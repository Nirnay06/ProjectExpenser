import { Link } from "react-router-dom";
import { ContentRow } from "../../layout/DashboardLayout";
import styles from "./../../styles/components/AccountRow.module.scss";
import icons from "./../../assets/AccountSprite.svg";
import useServices from "../../hooks/useSevices";
import AccountIcon from "./AccountIcon";
import {getCurrencyFormatted} from "../../utils/StringUtils";

const AccountRow = (props) => {
  const { account } = props;
  const {AccountService} = useServices();
  return (
    <ContentRow>
      <Link className={styles.Account} to={`/accounts/account/${account.identifier}`}>
        <div className={styles.Account__info}>
         <AccountIcon className="offsetR15" account={account} size="-small"/>
          <span className="font16">{account.accountName}</span>
        </div>
        <span className={styles.Account__type}>{account.accountType}</span>
        <span className={styles.Account__balance}>{getCurrencyFormatted(account.accountBalance)}</span>
      </Link>
    </ContentRow>
  );
};
export default AccountRow;