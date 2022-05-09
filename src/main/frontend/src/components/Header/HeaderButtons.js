import HeaderButton from "./HeaderButton";

const HeaderButtons = (props) => {
    const DUMMY_BUTTONS = [{
        title: 'Record',
        icon: 'add-outline'
    }];
    return (<div className={props.className}>
        {DUMMY_BUTTONS.map((btn => {
            return <HeaderButton title={btn.title} icon={btn.icon} key={btn.title} />
        }))}
    </div>);
}
export default HeaderButtons;