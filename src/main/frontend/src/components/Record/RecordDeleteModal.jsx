import { Button, Col, Row } from "antd";
import useServices from "../../hooks/useSevices";

const RecordDeleteModal = ({closePopup = ()=>{},  ...props}) => {
    const {RecordService} = useServices();

    const onConfirm = ()=>{
        RecordService.deleteRecord(props.recordIdentifier);
        closePopup();
    }
  return (
    <div style={{textAlign : 'center'}}>
      <p style={{width : '100%', fontSize : '2rem'}}>Are you sure you want to delete the Record?</p>
      <Row gutter={25} >
        <Col span={12}>
          <Button type="default" block size="large" shape="round" onClick={() => {closePopup()}}>
            Cancel
          </Button>
        </Col>
        <Col span={12}>
          <Button type="primary" block size="large" shape="round" onClick={onConfirm}>
            Confirm
          </Button>
        </Col>
      </Row>
    </div>
  );
};

export default RecordDeleteModal;
