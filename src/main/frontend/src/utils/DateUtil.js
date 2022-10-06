export function getFirstAndLastWeekDays(datecopy = new Date()) {
    let date = new Date(datecopy);
    var firstDay = new Date(Date.UTC(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay()));
    //  new Date(date.setDate(date.getDate() - date.getDay()));
    var lastDay = new Date(Date.UTC(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay() + 6));
    // new Date(date.setDate(date.getDate() - date.getDay() + 6));
    return { firstDay, lastDay }
}

export function getDateWithoutTime(date) {
    return new Date(new Date(date).setHours(0, 0, 0, 0));
}
export const checkIfDatesAreInSameWeek = (targetDate, referenceDate) => {
    const { firstDay, lastDay } = getFirstAndLastWeekDays(getDateWithoutTime(referenceDate));
    if (targetDate.getTime() >= firstDay.getTime() && targetDate.getTime() <= lastDay.getTime()) {
        return true;
    }
    return false
}

export function getFirstAndLastMonthDays(datecopy = new Date()) {
    var date = new Date(datecopy), y = date.getFullYear(), m = date.getMonth();
    var firstDay = new Date(Date.UTC(y, m, 1));
    var lastDay = new Date(Date.UTC(y, m + 1, 0));
    return { firstDay, lastDay }
}

export function getFirstAndLastYearDays(datecopy = new Date()) {
    var date = new Date(datecopy), year = date.getFullYear();
    var firstDay = new Date(Date.UTC(year, 0, 1));
    var lastDay = new Date(Date.UTC(year, 11, 31));
    return { firstDay, lastDay }
}

export function getUTCDate(date = new Date()) {
    return new Date(Date.UTC(date.getFullYear(), date.getMonth(), date.getDate()))
}
export function subtractDaysFromDate(date = new Date(), days = 0) {
    let dateCopy = new Date(date);
    dateCopy.setDate(dateCopy.getDate() - days)
    return getUTCDate(dateCopy);
}

export function subtractMonthsFromDate(date = new Date(), months = 0) {
    let newDate = new Date(Date.UTC(date.getFullYear() - (months / 12), date.getMonth() - (months % 12), date.getDate()))
    return getUTCDate(newDate);
}

export function getMonthAndYear(odate = new Date()) {
    const date = new Date(odate);
    return date.toLocaleDateString('en-us', { month: 'short', year: 'numeric' });
}

export function getMonthAndDate(odate = new Date()) {
    const date = new Date(odate);
    return date.toLocaleDateString('en-us', { month: 'short', day: 'numeric' });
}

export function isFutureDate(targetDate) {
    return targetDate > new Date();
}