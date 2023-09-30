import { Button, Dropdown } from "antd";
import { DownOutlined } from "@ant-design/icons";
import React, { useState } from "react";
import "react-datepicker/dist/react-datepicker.css";
import DateRangeModal from "./DateRangeModal";

export const DateRangeContext = React.createContext({
    activeTab: '',
    setActiveTab: () => { },
    dateRange: {},
    setDateRange: () => { },
    setDropDownVisible: false
})

const DateRangePicker = (props) => {
    const [dropdownVisible, setDropDownVisible] = useState(false);
    
    const [activeTab, setActiveTab] = useState({ activeTabBtn: 'Range', activeTabRadio: '30days' });
    const { dateRange, setDateRange, setPeriodLabel } = props
    return (
        <div className="datepicker" style={props.style}>
            <DateRangeContext.Provider value={{ activeTab, setActiveTab, dateRange, setDateRange, setDropDownVisible, setPeriodLabel }}>
                <Dropdown
                    overlay={<DateRangeModal />}
                    trigger="click"
                    className="datepicker"
                    placement="bottom"
                    open={dropdownVisible}
                    arrow={{ pointAtCenter: true }}
                >
                    <Button style={{textAlign : 'center'}} onClick={() => { setDropDownVisible(p=>!p) }}>
                        {props.periodLabel} <DownOutlined />
                    </Button>
                </Dropdown>
            </DateRangeContext.Provider>
        </div>)
}

export default DateRangePicker;