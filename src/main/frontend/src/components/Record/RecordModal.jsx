import { Button, Col, DatePicker, Form, Input, Radio, Row, Select, Tag, TimePicker, TreeSelect } from "antd";
import React, { useEffect } from "react";
import styles from "./RecordModal.module.scss";
import TagRender from "../UI/TagRender";
import TextArea from "antd/lib/input/TextArea";
import LocationSearch from "../LocationComponents/LocationSearch";
import useServices from "../../hooks/useSevices";
import { useState } from "react";
import useHttp from "../../hooks/useHttp";
import { getFieldName } from "../../utils/StringUtils";
import dayjs from "dayjs";
import CustonParseFormat from "dayjs/plugin/customParseFormat";
import { getFormattedDate, getFormattedTime, getDateFromDateAndTimeString, getDateString, parseDate, parseTime } from "../../utils/DateUtil";
import { useContext } from "react";
import UserContext from "../../store/UserContext";
const Option = Select.Option;
dayjs.extend(CustonParseFormat);

const RecordModalContainer = (props) => {
  const [divColor, setDivColor] = useState("brown");
  const [categoryList, setCategoryList] = useState([]);
  const userCtx = useContext(UserContext);
  const [labelList, setLabelList] = useState([]);
  const [accountList, setAccountList] = useState([]);
  const [currencyList, setCurrencyList] = useState([]);
  const [isEdited, toggleEdited] = useState(props.addRecord ? true : false);
  const [preventModal, setPreventModal] = useState(false);
  const { RecordService, CategoryService, LabelService, AccountService, CurrencyService } = useServices();
  const { sendRequest } = useHttp();
  // const [amountPrefix, setAmounPrefix] = useState('');
  useEffect(() => {
    CategoryService.fetchUserCategory(setCategoryList);
    LabelService.fetchAllLabelsByUserForRecord(setLabelList);
    AccountService.fetchAccountsForRecord(setAccountList);
    CurrencyService.fetchCurrencyListForRecord(setCurrencyList);
  }, []);
  const RecordTypePrefix = {
    'Expense' : '-',
    'Income' : '+',
    'Trnsfer' : ''
  }
  const {
    initialValues = {
      currencyIdentifier: "d8e7c565-802c-41a8-808b-5c60b7ce7da9",
      accountIdentifier: "f34cdf77-3bc7-49ef-b023-a2ce7c6527df",
      recordDate: getFormattedDate(new Date()),
      recordTime: getFormattedTime(new Date()),
      recordType: "Expense",
      userLabelIdentifiers: props.addRecord ? userCtx.defaultLabels : [],
    },
    onSaveCloseModal = () => {},
  } = props;

  const [form] = Form.useForm();
  return (
    <>
      <Form
        style={{ padding: 0 }}
        onFieldsChange={(changedFields) => {
          if(changedFields && changedFields[0].name[0]==='recordType'){
            if (changedFields[0].value === "Transfer") {
                  setDivColor("#01aa71");
                  
                } else if(changedFields[0].value === 'Expense'){
                  setDivColor("brown");
                  // setAmounPrefix('-');
                }else{
                  // setAmounPrefix('+');
                }
          }
          if (isEdited === false) {
            toggleEdited(true);
          }
        }}
        onFinish={(values) => {
          values.recordDate = getFormattedDate(values.recordDate);
          values.recordTime = getFormattedTime(values.recordTime);
          let labelIdentifiers = values.userLabelIdentifiers;
          values.labels = [];
          labelIdentifiers.forEach((identifier, index) => {
            values.labels[index] = { userLabelIdentifier: identifier };
          });
          values.clientIdentifier = "a4e9f3d9-ca54-4b3e-9bd5-cbd014f8a088";
          if(values.recordType==='Expense'){
            values.amount *=-1;
          }
          if (props.addRecord) {
            sendRequest(
              { url: "/api/record/add", method: "POST", headers: { "Content-Type": "application/json" }, body: values },
              () => {
                if (!preventModal) {
                  onSaveCloseModal();
                  userCtx.triggerRefresh();
                  setPreventModal(false);
                } else {
                  form.resetFields();
                }
              },
              (data) => {}
            );
          } else {
            sendRequest(
              { url: "/api/record/update", method: "POST", headers: { "Content-Type": "application/json" }, body: values },
              () => {
                onSaveCloseModal();
                userCtx.triggerRefresh();
              },
              (data) => {}
            );
          }
        }}
        initialValues={{
          ...initialValues,
          recordDate: parseDate(initialValues.recordDate),
          recordTime: parseTime(initialValues.recordTime),
          amount : Math.abs(initialValues.amount)
        }}
        buttonText="Save"
        form={form}
        className={styles.recordModalForm}
        layout="vertical">
        <Form.Item hidden name="recordIdentifier">
          <Input type="hidden" />
        </Form.Item>
        <Row style={{ height: "73%", overflow: "hidden" }}>
          <Col span={15}>
            <Col
              className="col padding-25"
              flex="auto"
              style={{
                backgroundColor: divColor,
              }}>
              <Row justify="space-around">
                <Form.Item name="recordType">
                  <Radio.Group
                    style={{
                      border: "1px solid white",
                      borderRadius: "2px",
                      overflow: "hidden",
                      display: "flex",
                      alignItems: "center",
                    }}
                    >
                    <Radio.Button value="Expense" className="RadioTransparent">
                      Expense
                    </Radio.Button>
                    <Radio.Button value="Income" className="RadioTransparent">
                      Income
                    </Radio.Button>
                    <Radio.Button value="Transfer" className="RadioTransparent">
                      Transfer
                    </Radio.Button>
                  </Radio.Group>
                </Form.Item>
              </Row>
              <Row justify="space-around" gutter={[25, 25]}>
                <Col span={12}>
                  <Form.Item
                    name={getFieldName("accountIdentifier")}
                    style={{
                      width: "100%",
                    }}
                    label={<span style={{ color: "white" }}>Account</span>}>
                    <Select
                      required={true}
                      showArrow
                      style={{
                        width: "100%",
                      }}
                      optionLabelProp="label">
                      {accountList.map((value) => AccountService.getAccountOptionForDropdown(value))}
                    </Select>
                  </Form.Item>
                </Col>
                <Col span={12}>
                  <Form.Item name="amount" label={<span style={{ color: "white" }}>Amount</span>}>
                    <Input
                      addonBefore={
                        <Form.Item name="currencyIdentifier" noStyle>
                          <Select showArrow={false} required={true} style={{ width: "60px" }}>
                            {currencyList.map((value) => CurrencyService.getOptionForCurrencyDropdown(value))}
                          </Select>
                        </Form.Item>
                      }
                      className="input"
                      type="number"
                      prefix={RecordTypePrefix[form.getFieldValue('recordType')]}
                      name="amount"
                      required="required"
                      placeholder="0"
                    />
                  </Form.Item>
                </Col>
              </Row>
            </Col>
            <Col className="col" flex="auto" style={{ backgroundColor: "white" }}>
              <Row gutter={[25, 25]} className="padding-25">
                <Col span={12}>
                  <Form.Item name="categoryIdentifier" label="Category" rules={[{ required: true, message: "Please select a category" }]}>
                    <TreeSelect
                      style={{ width: "100%" }}
                      dropdownStyle={{ maxHeight: 400, overflow: "auto" }}
                      placeholder="Please select"
                      treeDefaultExpandAll={false}
                      treeExpandAction={"click"}>
                      {CategoryService.getCategoryTreeNodes(categoryList)}
                    </TreeSelect>
                  </Form.Item>
                  <Form.Item name="recordDate" label="Date">
                    <DatePicker style={{ width: "100%" }} format="MM/DD/YYYY" />
                  </Form.Item>
                </Col>
                <Col span={12}>
                  <Form.Item name={getFieldName("userLabelIdentifiers")} label="Label">
                    <Select
                      mode="multiple"
                      showArrow
                      tagRender={(props) => {
                        return <TagRender label={props.label} options={labelList} fieldKey={"title"} />;
                      }}
                      style={{
                        width: "100%",
                      }}
                      optionLabelProp="label"
                      maxTagCount={3}>
                      {labelList.map((value) => {
                        return (
                          <Option value={value.identifier} label={value.title}>
                            <Row align="middle">
                              <Col
                                className="dot"
                                style={{
                                  backgroundColor: value.color,
                                  marginRight: "5px",
                                }}></Col>
                              <Col>
                                <span>{value.title}</span>
                              </Col>
                            </Row>
                          </Option>
                        );
                      })}
                    </Select>
                  </Form.Item>

                  <Form.Item name="recordTime" label="Time">
                    <TimePicker format={"HH:mm"} style={{ width: "100%" }} />
                  </Form.Item>
                </Col>
              </Row>
            </Col>
            <Col className="col" flex="auto" style={{ backgroundColor: "white" }}>
              {isEdited && props.addRecord === undefined && (
                <Row gutter={[25, 25]} justify="center">
                  <Col span={6}>
                    <Form.Item>
                      <Button
                        type="default"
                        block
                        size="large"
                        shape="round"
                        onClick={() => {
                          form.resetFields();
                          toggleEdited(false);
                        }}>
                        Revert
                      </Button>
                    </Form.Item>
                  </Col>
                  <Col span={6}>
                    <Form.Item>
                      <Button type="primary" block size="large" shape="round" htmlType="submit">
                        Update
                      </Button>
                    </Form.Item>
                  </Col>
                </Row>
              )}

              {props.addRecord !== undefined && (
                <Row gutter={[25, 5]} justify="center">
                  <Col span={16}>
                    <Form.Item style={{ marginBottom: 0 }}>
                      <Button type="primary" block size="large" shape="round" htmlType="submit">
                        Add Record
                      </Button>
                    </Form.Item>
                  </Col>

                  <Col span={12}>
                    <Form.Item>
                      <Button
                        type="link"
                        block
                        size="large"
                        shape="round"
                        htmlType="submit"
                        onClick={() => {
                          setPreventModal(true);
                        }}>
                        Add and Create Another
                      </Button>
                    </Form.Item>
                  </Col>
                </Row>
              )}
            </Col>
          </Col>
          <Col className="col bg-color-grey-light-2 padding-25" span={9} style={{ height: "70vh", overflowY: "scroll" }}>
            <Form.Item name="payee" label="Payee">
              <Input type="text" />
            </Form.Item>
            <Form.Item name="comments" label="note">
              <TextArea rows={4} />
            </Form.Item>
            <Form.Item name="paymentType" label="Payment Type">
              <Select
                style={{
                  width: "100%",
                }}>
                <Option value="cash">Cash</Option>
                <Option value="debit_card">Debit Card</Option>
                <Option value="credit_card">Credit Card</Option>
                <Option value="transfer">Transfer</Option>
                <Option value="voucher">Voucher</Option>
                <Option value="mobile_payment">Mobile Payment</Option>
              </Select>
            </Form.Item>
            <Form.Item name="paymentStatus" label="Payment status">
              <Select
                style={{
                  width: "100%",
                }}>
                <Option value="reconciled">Reconciled</Option>
                <Option value="cleared">Cleared</Option>
                <Option value="uncleared">Uncleared</Option>
              </Select>
            </Form.Item>
            <Form.Item name="location" label="Place">
              <LocationSearch
                style={{
                  width: "100%",
                }}
                placeholder="Enter place here"></LocationSearch>
            </Form.Item>
          </Col>
        </Row>
      </Form>
    </>
  );
};

export default RecordModalContainer;
