import { Form, Row, Col, Input, Select, Button } from "antd";
import { CaretDownOutlined } from "@ant-design/icons";
import useServices from "../../../../hooks/useSevices";
const AddCurrencyModal = (props) => {
  const { CurrencyService } = useServices();
  const defaultCurrency = props.assignedCurrencies?.find((c) => c.baseCurrency);
  const selectedCurrencyObj = props.unassignedCurrencies?.find((c) => c.identifier === props.selectedCurrency);
  const onFinishHandler = (values) => {
    if (typeof values.accountColor === "object") {
      values.accountColor = values.accountColor.toHexString();
    }
    if (editCurrencyObj) {
      CurrencyService.editUserCurrency({ masterCurrencyIdentifier: editCurrencyObj.masterCurrencyIdentifier, currencyRate: values.ratio });
    } else {
      CurrencyService.assignCurrencyToUser({ masterCurrencyIdentifier: values.name, currencyRate: values.ratio });
    }
    props.closePopup();
  };
  const [form] = Form.useForm();
  let initialValues = {
    name: props.selectedCurrency,
    ratio: selectedCurrencyObj?.conversionRate.toFixed(4),
    inverseRatio: (1 / selectedCurrencyObj?.conversionRate).toFixed(4),
  };
  let editCurrencyObj = props.editCurrencyObj;
  if (editCurrencyObj) {
    initialValues = {
      name: editCurrencyObj.currencyTitle,
      ratio: editCurrencyObj?.currencyRate.toFixed(4),
      inverseRatio: (1 / editCurrencyObj?.currencyRate).toFixed(4),
    };
  }

  const handleValueChange = (changedFields, allFields) => {
    let field = changedFields[0].name;
    let value = changedFields[0].value;
    if (field.includes("ratio")) {
      form.setFieldValue("inverseRatio", (1 / +value).toFixed(4));
    } else if (field.includes("inverseRatio")) {
      form.setFieldValue("ratio", (1 / +value).toFixed(4));
    } else if (field.includes("name")) {
      let selectedCurr = props.unassignedCurrencies?.find((c) => c.identifier === props.selectedCurrency);
      form.setFieldValue("ratio", selectedCurr?.conversionRate.toFixed(4));
      form.setFieldValue("inverseRatio", (1 / selectedCurr?.conversionRate).toFixed(4));
    }
  };
  return (
    <div>
      <Form
        onFinish={onFinishHandler}
        onFieldsChange={handleValueChange}
        initialValues={initialValues}
        buttonText="Save"
        form={form}
        layout="vertical"
      >
        <Row>
          <Col span={24}>
            <Form.Item name={"name"} label={"Name"} required>
              <Select
                showSearch
                disabled={editCurrencyObj}
                style={{
                  width: "100%",
                  borderRadius: 0,
                }}
                size="large"
                placeholder="Search to Select"
                value={props.selectedCurrency}
                // defaultValue={}
                suffixIcon={<CaretDownOutlined />}
                optionFilterProp="children"
                filterOption={(input, option) => (option?.label.toLowerCase() ?? "").includes(input.toLowerCase())}
                filterSort={(optionA, optionB) => (optionA?.label ?? "").toLowerCase().localeCompare((optionB?.label ?? "").toLowerCase())}
                options={props.items}
                onSelect={(value) => props.setSelectedCurrency(value)}
              />
            </Form.Item>
          </Col>
        </Row>
        <Row gutter={25}>
          <Col span={12}>
            <Form.Item name="ratio" label={props.selectedCurrency ? `1 ${defaultCurrency?.currencyAbbreviation} = ` : "Ratio"}>
              <Input type="number" placeholder="Enter Starting Amount" />
            </Form.Item>
          </Col>
          <Col span={12}>
            <Form.Item name="inverseRatio" label={props.selectedCurrency ? `1 ${selectedCurrencyObj?.currencyAbbreviation} = ` : "Inverse ratio"}>
              <Input type="number" placeholder="Enter Starting Amount" />
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

export default AddCurrencyModal;
