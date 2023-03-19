import { Button, Col, DatePicker, Form, Input, Radio, Row, Select, Tag, TimePicker, TreeSelect } from "antd";
import React, { useEffect } from "react";
import styles from "./RecordModal.module.scss";
import moment from "moment";
import TagRender from "../UI/TagRender";
import TextArea from "antd/lib/input/TextArea";
import LocationSearch from "../LocationComponents/LocationSearch";
import useServices from "../../hooks/useSevices";
import { useState } from "react";
import useHttp from "../../hooks/useHttp";
import { getFieldName } from "../../utils/StringUtils";
import dayjs from "dayjs";
import CustonParseFormat from "dayjs/plugin/customParseFormat";
import { getFormattedDate, getFormattedTime } from "../../utils/DateUtil";
const Option = Select.Option;
dayjs.extend(CustonParseFormat);
const RecordModalContainer = (props) => {
  const [divColor, setDivColor] = useState("brown");
  const [categoryList, setCategoryList] = useState([]);
  const [labelList, setLabelList] = useState([]);
  const { RecordService, CategoryService, LabelService } = useServices();
  const { sendRequest } = useHttp();

  const accountList = RecordService.fetchAllAccountsByUser();
  useEffect(() => {
    CategoryService.fetchUserCategory(setCategoryList);
    LabelService.fetchAllLabelsByUserForRecord(setLabelList);
  }, []);
  const {
    initialValues = {
      currencyIdentifier: "d8e7c565-802c-41a8-808b-5c60b7ce7da9",
      accountIdentifier: "f34cdf77-3bc7-49ef-b023-a2ce7c6527df",
      recordDate: moment(new Date(), "yyyy-MM-dd"),
      recordTime: moment(new Date(), "HH:MM"),
      recordType: "Expense",
    },
  } = props;
  const [form] = Form.useForm();
  const accountTagRender = (props) => {
    const { label, value, closable, onClose } = props;

    const onPreventMouseDown = (event) => {
      event.preventDefault();
      event.stopPropagation();
    };
    return (
      <Tag
        onMouseDown={onPreventMouseDown}
        closable={closable}
        onClose={onClose}
        style={{
          marginRight: 3,
        }}>
        {label}
      </Tag>
    );
  };
  return (
    <>
      <Form
        style={{ padding: 0 }}
        onFinish={(values) => {
          values.recordDate = getFormattedDate(values.recordDate);
          values.recordTime = getFormattedTime(values.recordTime);
          let labelIdentifiers = values.labels;
          values.labels = [];
          labelIdentifiers.forEach((identifier, index) => {
            values.labels[index] = { userLabelIdentifier: identifier };
          });
          values.clientIdentifier = "a4e9f3d9-ca54-4b3e-9bd5-cbd014f8a088";
          sendRequest({ url: "/api/record/add", method: "POST", headers: { "Content-Type": "application/json" }, body: values }, null, (data) => {
            console.log(data);
          });
          console.log(values);
        }}
        initialValues={initialValues}
        buttonText="Save"
        form={form}
        className={styles.recordModalForm}
        layout="vertical">
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
                    onChange={(e) => {
                      if (e.target.value === "transfer") {
                        setDivColor("#01aa71");
                      } else {
                        setDivColor("brown");
                      }
                    }}>
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
                      optionLabelProp="label"
                      maxTagCount={3}>
                      {accountList.map((value) => {
                        return (
                          <Option value={value.identifier} label={value.accountName} key={value.identifier}>
                            <Row align="middle">
                              <Col
                                className="dot"
                                style={{
                                  backgroundColor: value.accountColor,
                                  marginRight: "5px",
                                }}></Col>
                              <Col>
                                <span>{value.accountName}</span>
                              </Col>
                            </Row>
                          </Option>
                        );
                      })}
                    </Select>
                  </Form.Item>
                </Col>
                <Col span={12}>
                  <Form.Item name="amount" label={<span style={{ color: "white" }}>Amount</span>}>
                    <Input
                      addonBefore={
                        <Form.Item name="currencyIdentifier" noStyle>
                          <Select showArrow={false}>
                            <Option value="d8e7c565-802c-41a8-808b-5c60b7ce7da9">&#8377;</Option>
                            <Option value="dollar">$</Option>
                            <Option value="euro">&euro;</Option>
                          </Select>
                        </Form.Item>
                      }
                      className="input"
                      type="number"
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
                  <Form.Item name="categoryIdentifier" label="Category">
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
                    <DatePicker style={{ width: "100%" }} format="DD/MM/YYYY" />
                  </Form.Item>
                </Col>
                <Col span={12}>
                  <Form.Item name={getFieldName("labels")} label="Label">
                    <Select
                      mode="multiple"
                      showArrow
                      tagRender={(props) => {
                        return <TagRender label={props.label} options={labelList} />;
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
                    <TimePicker format={"HH:mm"} style={{ width: "100%" }} minuteStep={15} />
                  </Form.Item>
                </Col>
              </Row>
            </Col>
            <Col className="col" flex="auto" style={{ backgroundColor: "white" }}>
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
                      }}>
                      Revert
                    </Button>
                  </Form.Item>
                </Col>
                <Col span={6}>
                  <Form.Item>
                    <Button type="primary" block size="large" shape="round" htmlType="submit">
                      Save
                    </Button>
                  </Form.Item>
                </Col>
              </Row>
            </Col>
          </Col>
          <Col className="col bg-color-grey-light-2 padding-25" span={9} style={{ height: "75vh", overflowY: "scroll" }}>
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
            <Form.Item name="location" label="Place" shouldUpdate>
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
