import HeaderButton from "./HeaderButton";
import CustomModal from "../UI/Modal";
import { useState } from "react";
import RecordModalContainer from "../Record/RecordModal";

const HeaderButtons = (props) => {
    const [showRecordModal, toggleRecordModal] = useState(false);
    const DUMMY_BUTTONS = [{
        title: 'Record',
        icon: 'add-outline',
        onClick: () => { toggleRecordModal(true) }
    }];

    return (
        <>
            <CustomModal
                destroyOnClose={true}
                visible={showRecordModal}
                onCancel={() => {
                    toggleRecordModal(false);
                }}
                title="Add Record"
                bodyStyle={{ padding: 0, paddingBottom: "1rem" }}
            >
                <RecordModalContainer />
            </CustomModal>
            <div className={props.className}>
                {DUMMY_BUTTONS.map((btn => {
                    return <HeaderButton title={btn.title} icon={btn.icon} key={btn.title} onClick={btn.onClick} />
                }))}
            </div>
        </>);
}
export default HeaderButtons;