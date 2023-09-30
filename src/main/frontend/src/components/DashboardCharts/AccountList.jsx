import {Button,Divider} from 'antd';
import {Link} from "react-router-dom";
import icons from "./../../assets/sprite.svg";
import styles from './../../styles/components/chartbox.module.scss';
import {useContext,useState} from 'react';
import {NavLink} from 'react-router-dom/cjs/react-router-dom.min';
import useServices from '../../hooks/useSevices';
import UserContext from '../../store/UserContext';
import {useEffect} from 'react';
import AccountIcon from '../Account/AccountIcon';
import {getCurrencyFormatted} from '../../utils/StringUtils';
const AccountList = (props)=>{
    const [accountsList, setAccountsList] = useState([]);
    const { AccountService } = useServices();
    const userCtx = useContext(UserContext);
    useEffect(() => {
      AccountService.fetchAllUserBankaccounts(setAccountsList);
    }, [userCtx.refresh]);
  
  let displayedAccounts = accountsList.sort((a,b)=> b.accountBalance - a.accountBalance).slice(0,4);

  return (
      <>

        <div className={styles.chartbox__header}>
          <p className="font16 fontBold">Accounts</p>
          <Button className='font10' size='small' type='default' shape='round'><NavLink to={"/accounts"}>Accounts</NavLink></Button>
          </div>
        <Divider style={{ margin: "8px 0px" }} />
        <div className={`font16 ${styles.chartbox__body}`}>
            {displayedAccounts.map(account =>{
                return <div key={account.identifier} className={styles.accountlist__account}>
                        <div className={`offsetR10 ${styles['accountlist__account-icon']}`}>
                            <AccountIcon  account={account} size='-small'/>
                        </div>
                        <div className={`offsetR10 ${styles['accountlist__account-detail']}`}> 
                            <p className='font18 fontBold'>{account.accountName}</p>
                            <p className='text--dark-3 font12'>{account.accountType}</p>
                        </div>
                        <div className={`offsetR10 ${styles['accountlist__account-amount']}`}>
                            <p className={styles.Account__balance}>{getCurrencyFormatted(account.accountBalance)}</p>
                        </div>
                        <div className={`${styles['accountlist__account-nav']}`}>
                            <Link  to={`/accounts/account/${account.identifier}`}>
                                    <svg className={`icon icon-arrow-circle-right`}>
                                        <use href={`${icons}#icon-arrow-circle-right`}></use>
                                    </svg>
                            </Link>
                        </div>
                    </div>
            })}
        </div>
      </>
    );
}

export default AccountList;