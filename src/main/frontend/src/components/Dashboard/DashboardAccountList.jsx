import {useContext} from "react";
import useServices from "../../hooks/useSevices";
import styles from "./../../styles/components/accountButton.module.scss";
import UserContext from "../../store/UserContext";
import {useEffect} from "react";
import {useState} from "react";
import AccountIcon from "../Account/AccountIcon";
import {getFontColorBasedOnBrightness} from "../../utils/ColorUtils";
import {getCurrencyFormatted} from "../../utils/StringUtils";
import CustomModal from "../UI/Modal";
import AccountModalContainer from "../Account/AccountModal";
import {Button} from "antd";
const DashboardAccountList = ({selectedAccount , setSelectedAccount})=>{
  const [accountModalDetails, setAccountModalDetails] = useState();
    const [accountsList, setAccountsList] = useState([]);
    const { AccountService } = useServices();
    const userCtx = useContext(UserContext);
    useEffect(() => {
      AccountService.fetchAllUserBankaccounts(setAccountsList);
    }, [userCtx.refresh])
    const onAccountSelect = (identifier)=>{
          setSelectedAccount(identifier);
    }
    return <>
     <CustomModal
        visible={accountModalDetails}
        onCancel={() => {
          setAccountModalDetails(null);
        }}
        title="Edit Account"
        width={600}
        bodyStyle={{ padding: '2rem', paddingBottom: "3rem" }}>
        <AccountModalContainer 
          initialValues={accountModalDetails} 
          closePopup={() => { setAccountModalDetails(null); }} 
          />
      </CustomModal>
        {accountsList.map(account=> {
          let fontColor = getFontColorBasedOnBrightness(account.accountColor);
          let selected = selectedAccount==='ALL'  || selectedAccount===account.identifier;
            return <div key={account.identifier} onClick={()=>{onAccountSelect(account.identifier)}} className={`font16 ${styles[`account__button`]} ${selected ? '': styles[`account__button--selected`]}`} 
                      style={{backgroundColor : account.accountColor ? account.accountColor : '#cbcbcb' }}>
              <div className={styles['account__button-icon']}>
                <AccountIcon account={account} size="-tiny" style={{backgroundColor : 'transparent'}}/>
              </div>
              <div className={styles['account__button-detail']}>
                <p style={{color : fontColor, opacity : '0.5'}} className="font14">{account.accountName}</p>
                <p style={{color : fontColor}} className="font16" >{getCurrencyFormatted(account.accountBalance)}</p>
                </div>
              <div className={styles['account__button-edit']} onClick={(e)=>{e.stopPropagation();setAccountModalDetails(account)}}>
              <div style={{opacity  : '0.5'}} className={`accountIcon${ fontColor==='#ffffff' ? '-white' : ''} accountIcon-pencil-tiny`} >
              </div>
              </div>
            </div>
        })}
        <div className={`font16 ${styles['account__button']} ${styles['account__button--add']}`} 
            onClick={()=>{setAccountModalDetails({})}}>
          + Add Account
        </div>
        {selectedAccount!=='ALL' && <div className={styles.selectAllButton}><Button type="primary" onClick={()=>{setSelectedAccount('ALL')}}>Select All</Button></div>}
      
       </>
}

export default DashboardAccountList;