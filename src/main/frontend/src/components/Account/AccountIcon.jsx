import React, { useEffect, useState } from 'react';
import styles from "./../../styles/components/AccountRow.module.scss";
import useServices from '../../hooks/useSevices';
import {getFontColorBasedOnBrightness} from '../../utils/ColorUtils';

const AccountIcon = ({ account , size='', style={}}) => {
  const [whiteImage, setWhiteImage] = useState(false);
  const {AccountService} = useServices();
  useEffect(() => {
     
    setWhiteImage( getFontColorBasedOnBrightness(account.accountColor)==='#000000' ? false : true)
    
  }, [account.accountColor]);

  return (
    <div className={`${styles[`Account__icon`]} ${size?styles[`Account__icon${size}`] : ''}`} style={{ backgroundColor: account.accountColor , ...style}}>
    <div className={`accountIcon${whiteImage ? '-white' : ''} accountIcon-${AccountService.getAccountIconByType(account.accountType)}${size}`} >
    </div>
    </div>
  );
};

export default AccountIcon;