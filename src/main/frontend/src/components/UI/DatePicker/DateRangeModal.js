import { Button, Divider, Radio, Space } from "antd";
import React, { useContext, useState } from "react";
import DatePicker, { CalendarContainer } from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { checkIfDatesAreInSameWeek, getDateWithoutTime, getFirstAndLastMonthDays, getFirstAndLastWeekDays, getFirstAndLastYearDays, getMonthAndDate, getMonthAndYear, getUTCDate, getYear, subtractDaysFromDate, subtractMonthsFromDate } from "../../../utils/DateUtil";
import { DateRangeContext } from "./DateRangePicker";


const CalenderContainer = ({ className, children }) => {
    return (<CalendarContainer>
        <div style={{ position: "relative" }}>{children}</div>
    </CalendarContainer>
    );
}
const RangeContainer = (props) => {
    const { activeTabRadio, activeTabBtn, setActiveTab, setDateRange, setDropDownVisible, setPeriodLabel } = props
    const [startDate, setStartDate] = useState(new Date());
    const [endDate, setEndDate] = useState(null);
    const onChange = (dates) => {
        const [start, end] = dates;
        setStartDate(start);
        setEndDate(end);
        if (start && end) {
            setDateRange({ startDate: getUTCDate(start), endDate: getUTCDate(end) });
            setPeriodLabel(getMonthAndDate(start) + ' - ' + getMonthAndDate(end));
            setDropDownVisible(false);
        }
    };

    const onRadioChangeHandler = (e) => {
        let value = e.target.value;
        if (value !== 'custom') {
            setDropDownVisible(false);
            if (value === '7days') {
                setPeriodLabel('Last 7 Days');
                setDateRange({ endDate: getUTCDate(), startDate: subtractDaysFromDate(getUTCDate(), 7) })
            }
            if (value === '30days') {
                setPeriodLabel('Last 30 Days');
                setDateRange({ endDate: getUTCDate(), startDate: subtractDaysFromDate(getUTCDate(), 30) })
            }
            if (value === '90days') {
                setPeriodLabel('Last 90 Days');
                setDateRange({ endDate: getUTCDate(), startDate: subtractDaysFromDate(getUTCDate(), 90) })
            }
            if (value === '12months') {
                setPeriodLabel('Last 12 Months');
                setDateRange({ endDate: getUTCDate(), startDate: subtractMonthsFromDate(getUTCDate(), 12) })
            }
        }
        setActiveTab({ activeTabBtn, activeTabRadio: value })
    }
    return (<>
        <Radio.Group onChange={onRadioChangeHandler} value={activeTabRadio}>
            <Space direction="vertical">
                <Radio value={'7days'}>7 days</Radio>
                <Radio value={'30days'}>30 days</Radio>
                <Radio value={'90days'}>90 days</Radio>
                <Radio value={'12months'}>12 Months</Radio>
                <Radio value={'custom'}>Custom Range</Radio>

            </Space>
        </Radio.Group>
       
        {activeTabRadio === 'custom' ? (
            <> <Divider style={{margin :'12px 0'}}/>
            <DatePicker
                calendarContainer={CalenderContainer}
                selected={startDate}
                onChange={onChange}
                startDate={startDate}
                endDate={endDate}
                selectsRange
                inline
            />
            </>
        ) : null}
    </>)
}


const WeekContainer = (props) => {
    const { dateRange, setDateRange, setDropDownVisible,  setPeriodLabel } = props;

    const setWeekDates = (date) => {
        setDropDownVisible(false);
        const { firstDay: startDate, lastDay: endDate } = getFirstAndLastWeekDays(date)
        setDateRange({ startDate, endDate });
        setPeriodLabel(getMonthAndDate(startDate) + ' - ' +getMonthAndDate(endDate));
    }
    let { startDate } = dateRange;
    return (
        <DatePicker
            calendarContainer={CalenderContainer}
            selected={startDate}
            onChange={(date) => setWeekDates(date)}
            dayClassName={(date) => checkIfDatesAreInSameWeek(getDateWithoutTime(date), getDateWithoutTime(startDate)) ? "react-datepicker__day--keyboard-selected" : ""
            }
            inline

        />
    )
}
const MonthContainer = (props) => {
    const { dateRange, setDateRange, setDropDownVisible, setPeriodLabel } = props;
    const setMonthDates = (date) => {
        setDropDownVisible(false);
        const { firstDay: startDate, lastDay: endDate } = getFirstAndLastMonthDays(date);
        setDateRange({ startDate, endDate });
        setPeriodLabel(getMonthAndYear(date));
    }
    let { startDate } = dateRange;
    return (
        <DatePicker
            calendarContainer={CalenderContainer}
            selected={startDate}
            onChange={(date) => setMonthDates(date)}
            dateFormat="MMMM yyyy"
            showMonthYearPicker
            inline
        />
    )
}
const YearContainer = (props) => {
    const { dateRange, setDateRange, setDropDownVisible, setPeriodLabel } = props;
    const setYearDates = (date) => {
        setDropDownVisible(false);
        const { firstDay: startDate, lastDay: endDate } = getFirstAndLastYearDays(date);
        setDateRange({ startDate, endDate });
        setPeriodLabel(getYear(date));
    }
    let { startDate } = dateRange;
    return (
        <DatePicker
            calendarContainer={CalenderContainer}
            selected={startDate}
            onChange={(date) => setYearDates(date)}
            showYearPicker
            inline
        />
    );
}


const DateRangeModal = (props) => {
    const dateCtx = useContext(DateRangeContext);
    const { activeTab, setActiveTab, dateRange, setDateRange, setDropDownVisible, setPeriodLabel } = dateCtx;
    const { activeTabBtn, activeTabRadio = '' } = activeTab;
    return (
        <div className="modal" >
            <div className="tabs">
                <Button type={activeTabBtn === 'Range' ? 'primary' : 'secondary'}
                    size="small"
                    onClick={() => { setActiveTab({ activeTabBtn: 'Range', activeTabRadio: '7day' }); setDateRange({}); }}>
                    Range
                </Button>
                <Button type={activeTabBtn === 'Week' ? 'primary' : 'secondary'}
                    size="small"
                    onClick={() => { setActiveTab({ activeTabBtn: 'Week' }); setDateRange({}); }}>
                    Week
                </Button>
                <Button type={activeTabBtn === 'Month' ? 'primary' : 'secondary'}
                    size="small"
                    onClick={() => { setActiveTab({ activeTabBtn: 'Month' }); setDateRange({}); }} >
                    Month
                </Button>
                <Button type={activeTabBtn === 'Year' ? 'primary' : 'secondary'}
                    size="small"
                    onClick={() => { setActiveTab({ activeTabBtn: 'Year' }); setDateRange({}); }} >
                    Year
                </Button>
            </div>
            <div className={`calender__conatiner${activeTabBtn === 'Week' ? ' calender__conatiner__week' : ''}`}>
                {activeTabBtn === 'Range' && <RangeContainer
                    activeTabRadio={activeTabRadio}
                    activeTabBtn={activeTabBtn}
                    setActiveTab={setActiveTab}
                    dateRange={dateRange}
                    setDateRange={setDateRange}
                    setPeriodLabel={setPeriodLabel}
                    setDropDownVisible={setDropDownVisible}
                >
                </RangeContainer>}


                {activeTabBtn === 'Week' && <WeekContainer
                    dateRange={dateRange}
                    setDateRange={setDateRange}
                    setPeriodLabel={setPeriodLabel}
                    setDropDownVisible={setDropDownVisible}
                />}


                {activeTabBtn === 'Month' && <MonthContainer
                    dateRange={dateRange}
                    setDateRange={setDateRange}
                    setPeriodLabel={setPeriodLabel}
                    setDropDownVisible={setDropDownVisible}
                >
                </MonthContainer>}


                {activeTabBtn === 'Year' && <YearContainer
                    dateRange={dateRange}
                    setDateRange={setDateRange}
                    setPeriodLabel={setPeriodLabel}
                    setDropDownVisible={setDropDownVisible}
                >
                </YearContainer>}
            </div>

        </div>
    )
}

export default DateRangeModal;