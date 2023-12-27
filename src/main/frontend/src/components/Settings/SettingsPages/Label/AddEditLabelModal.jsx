import { Select, Input, Switch, Form, Row, Col, Button, Tooltip, ColorPicker } from "antd";
import { CaretDownOutlined, QuestionCircleOutlined } from "@ant-design/icons";
const AddEditLabelModal = (props) => {
  let initialValues = { ...props.initialValues };

  const [form] = Form.useForm();
  const onFinishHandler = (values) => {};
  return (
    <div>
      <Form onFinish={onFinishHandler} initialValues={initialValues} buttonText="Save" form={form} layout="vertical">
        <Row gutter={25}>
          <Col span={18}>
            <Form.Item name={"name"} label={"Name"} required>
              <Input size="large" placeholder="Label Name" />
            </Form.Item>
          </Col>
          <Col span={6}>
            <Form.Item name="color" label="Color">
              <ColorPicker size="middle" format="hex" className="custom-color-picker" />
            </Form.Item>
          </Col>
        </Row>
        <Row>
          <Col span={24}>
            <Form.Item name="autoAssign">
              <Switch size="large" /> Assign to every new record.{" "}
              <Tooltip placement="right" title={"This Label will be assigned to every new record."}>
                <QuestionCircleOutlined style={{ fontSize: "16px" }} />
              </Tooltip>
            </Form.Item>
          </Col>
        </Row>
        <Row gutter={[25, 5]} justify="center">
          <Col span={16}>
            <Form.Item style={{ marginBottom: 0 }}>
              <Button type="primary" block size="large" shape="round" htmlType="submit">
                {"Save"}
              </Button>
            </Form.Item>
          </Col>
        </Row>
      </Form>
    </div>
  );
};

export default AddEditLabelModal;
