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
    const [activeTab, setActiveTab] = useState({ activeTabBtn: 'Range', activeTabRadio: '7days' });
    const { dateRange, setDateRange } = props
    return (
        <div className="datepicker" style={props.style}>
            <DateRangeContext.Provider value={{ activeTab, setActiveTab, dateRange, setDateRange, setDropDownVisible }}>
                <Dropdown
                    overlay={<DateRangeModal dateRange={dateRange} setDateRange={setDateRange} />}
                    trigger="click"
                    className="datepicker"
                    placement="bottom"
                    visible={dropdownVisible}
                    arrow={{ pointAtCenter: true }}
                >
                    <Button onClick={() => { setDropDownVisible(true) }}>
                        Button <DownOutlined />
                    </Button>
                </Dropdown>
            </DateRangeContext.Provider>
        </div>)
}

export default DateRangePicker;