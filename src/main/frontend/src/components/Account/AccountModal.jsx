import { useCallback, useContext, useEffect, useState } from "react";
import InputField from "../UI/InputField";
import useServices from "../../hooks/useSevices";
import { Col, Row, ColorPicker, Select, Form, Input, Button } from "antd";
import UserContext from "../../store/UserContext";

const AccountModalContainer = (props) => {
  const [currencyList, setCurrencyList] = useState([]);
  const { CurrencyService, AccountService } = useServices();
  const userCtx = useContext(UserContext);
  useEffect(() => {
    CurrencyService.fetchCurrencyListForRecord(setCurrencyList);
  }, []);
  const { setAccountDetail = () => {} } = props;
  const initialValues = {
    accountType: "General",
    currenyIdentifier: userCtx.defaultCurrency,
    accountColor: "#1677FF",
    initialBalance: 0,
    ...props.initialValues,
  };
  const onFinishHandler = (values) => {
    if (typeof values.accountColor === "object") {
      values.accountColor = values.accountColor.toHexString();
    }
    AccountService.addOrEditAccount({ identifier: "addNew", ...initialValues, ...values }, setAccountDetail);
    props.closePopup();
  };
  const [form] = Form.useForm();
  return (
    <>
      <div>
        <Form onFinish={onFinishHandler} initialValues={initialValues} buttonText="Save" form={form} layout="vertical">
          <Row gutter={25}>
            <Col span={18}>
              <Form.Item label="Account Name" name="accountName">
                <Input type="text" placeholder="Enter Account Name" required="required" />
              </Form.Item>
            </Col>
            <Col span={6}>
              <Form.Item name={"accountColor"} label={"Account Color"}>
                <ColorPicker size="middle" format="hex" className="custom-color-picker" />
              </Form.Item>
            </Col>
          </Row>
          <Row>
            <Col span={24}>
              <Form.Item name={"accountType"} label={"Account Type"}>
                <Select
                  required={true}
                  showArrow
                  style={{
                    width: "100%",
                  }}
                >
                  {AccountService.getAccountsType().map((value) => AccountService.getAccountOptionForDropdown(value))}
                </Select>
              </Form.Item>
            </Col>
          </Row>
          <Row gutter={25}>
            <Col span={18}>
              <Form.Item name="initialBalance" label="Starting Amount">
                <Input type="number" placeholder="Enter Starting Amount" />
              </Form.Item>
            </Col>
            <Col span={6}>
              <Form.Item label="Currency" name="currenyIdentifier">
                <Select
                  required={true}
                  showArrow
                  style={{
                    width: "100%",
                  }}
                >
                  {currencyList.map((value) => CurrencyService.getOptionForCurrencyDropdown(value))}
                </Select>
              </Form.Item>
            </Col>
          </Row>
          <Row gutter={[25, 5]} justify="center">
            <Col span={16}>
              <Form.Item style={{ marginBottom: 0 }}>
                <Button type="primary" block size="large" shape="round" htmlType="submit">
                  {props.initialValues ? "Update Account" : "Add Account"}
                </Button>
              </Form.Item>
            </Col>
          </Row>
        </Form>
      </div>
    </>
  );
};

export default AccountModalContainer;
