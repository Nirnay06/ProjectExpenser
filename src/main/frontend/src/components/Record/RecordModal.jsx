import {
  Button,
  Col,
  DatePicker,
  Form,
  Input,
  Radio,
  Row,
  Select,
  TimePicker,
  TreeSelect,
} from "antd";
import React from "react";
import styles from "./RecordModal.module.scss";
import moment from "moment";
import TagRender from "../UI/TagRender";
import TextArea from "antd/lib/input/TextArea";
import LocationSearch from "../LocationComponents/LocationSearch";
import useServices from "../../hooks/useSevices";
import { CategoryList, getCategoryTreeNodes } from "../../utils/CategoryList";
import { useState } from "react";
import useHttp from "../../hooks/useHttp";
const Option = Select.Option;

const RecordModalContainer = (props) => {
  const [divColor, setDivColor] = useState("brown");
  const { RecordService } = useServices();
  const { sendRequest } = useHttp();
  const labelList = RecordService.fetchAllLabelsByUser();
  // const accountList = RecordService.fetchAllAccountByUser();
  const {
    initialValues = {
      currency: {
        title: "INR",
        identifier: "d8e7c565-802c-41a8-808b-5c60b7ce7da9",
      },
      account :{
        accountName :'Federal Bank',
        accountIdentifier : 'f34cdf77-3bc7-49ef-b023-a2ce7c6527df'
      },
      category
      date: moment(new Date(), "yyyy-MM-dd"),
      time: moment(new Date(), "HH:MM"),
      recordType: "Expense",
    },
  } = props;
  const [form] = Form.useForm();
  return (
    <>
      <Form
        style={{ padding: 0 }}
        onFinish={(values) => {
          sendRequest(
            {
              url: "/api/record/add",
              method: "POST",
              headers: {
                "Content-Type": "application/json",
              },
              body: values,
            },
            (data) => {}
          );
        }}
        initialValues={initialValues}
        buttonText="Save"
        form={form}
        className={styles.recordModalForm}
        layout="vertical"
      >
        <Row style={{ height: "73%", overflow: "hidden" }}>
          <Col span={15}>
            <Col
              className="col padding-25"
              flex="auto"
              style={{
                backgroundColor: divColor,
              }}
            >
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
                    name="accountName"
                    style={{
                      width: "100%",
                    }}
                    label={<span style={{ color: "white" }}>Account</span>}
                  >
                    <Select
                      required={true}
                      showArrow
                      style={{
                        width: "100%",
                      }}
                      optionLabelProp="label"
                      maxTagCount={3}
                    >
                      {labelList.map((value) => {
                        return (
                          <Option value={value.value} label={value.value}>
                            <Row align="middle">
                              <Col
                                className="dot"
                                style={{
                                  backgroundColor: value.color,
                                  marginRight: "5px",
                                }}
                              ></Col>
                              <Col>
                                <span>{value.value}</span>
                              </Col>
                            </Row>
                          </Option>
                        );
                      })}
                    </Select>
                  </Form.Item>
                </Col>
                <Col span={12}>
                  <Form.Item
                    name="amount"
                    label={<span style={{ color: "white" }}>Amount</span>}
                  >
                    <Input
                      addonBefore={
                        <Select
                          defaultValue="&#8377;"
                          className="select-after"
                          showArrow={false}
                        >
                          <Option value="inr">&#8377;</Option>
                          <Option value="dollar">$</Option>
                          <Option value="euro">&euro;</Option>
                        </Select>
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
            <Col
              className="col"
              flex="auto"
              style={{ backgroundColor: "white" }}
            >
              <Row gutter={[25, 25]} className="padding-25">
                <Col span={12}>
                  <Form.Item name="category" label="Category">
                    <TreeSelect
                      style={{ width: "100%" }}
                      dropdownStyle={{ maxHeight: 400, overflow: "auto" }}
                      // treeData={CategoryList}
                      placeholder="Please select"
                      treeDefaultExpandAll
                      treeDefaultExpandedKeys={["0-0"]}
                      onTreeExpand={(expandedKey) => {
                        console.log(expandedKey);
                      }}
                    >
                      {getCategoryTreeNodes(CategoryList)}
                    </TreeSelect>
                  </Form.Item>
                  <Form.Item name="recordDate" label="Date">
                    <DatePicker style={{ width: "100%" }} />
                  </Form.Item>
                </Col>
                <Col span={12}>
                  <Form.Item name="label" label="Label">
                    <Select
                      mode="multiple"
                      showArrow
                      tagRender={(props) => {
                        return <TagRender {...props} options={labelList} />;
                      }}
                      style={{
                        width: "100%",
                      }}
                      optionLabelProp="label"
                      maxTagCount={3}
                    >
                      {labelList.map((value) => {
                        return (
                          <Option value={value.value} label={value.value}>
                            <Row align="middle">
                              <Col
                                className="dot"
                                style={{
                                  backgroundColor: value.color,
                                  marginRight: "5px",
                                }}
                              ></Col>
                              <Col>
                                <span>{value.value}</span>
                              </Col>
                            </Row>
                          </Option>
                        );
                      })}
                    </Select>
                  </Form.Item>

                  <Form.Item name="recordTime" label="Time">
                    <TimePicker
                      format={"HH:mm"}
                      style={{ width: "100%" }}
                      minuteStep={15}
                    />
                  </Form.Item>
                </Col>
              </Row>
            </Col>
            <Col
              className="col"
              flex="auto"
              style={{ backgroundColor: "white" }}
            >
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
                      }}
                    >
                      Revert
                    </Button>
                  </Form.Item>
                </Col>
                <Col span={6}>
                  <Form.Item>
                    <Button
                      type="primary"
                      block
                      size="large"
                      shape="round"
                      htmlType="submit"
                    >
                      Save
                    </Button>
                  </Form.Item>
                </Col>
              </Row>
            </Col>
          </Col>
          <Col
            className="col bg-color-grey-light-2 padding-25"
            span={9}
            style={{ height: "75vh", overflowY: "scroll" }}
          >
            <Form.Item name="payee" label="Payee">
              <Input type="text" />
            </Form.Item>
            <Form.Item name="record_notes" label="note">
              <TextArea rows={4} />
            </Form.Item>
            <Form.Item name="payment_type" label="Payment type">
              <Select
                style={{
                  width: "100%",
                }}
              >
                <Option value="cash">Cash</Option>
                <Option value="debit_card">Debit Card</Option>
                <Option value="credit_card">Credit Card</Option>
                <Option value="transfer">Transfer</Option>
                <Option value="voucher">Voucher</Option>
                <Option value="mobile_payment">Mobile Payment</Option>
              </Select>
            </Form.Item>
            <Form.Item name="payment_status" label="Payment status">
              <Select
                style={{
                  width: "100%",
                }}
              >
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
                placeholder="Enter place here"
              ></LocationSearch>
            </Form.Item>
          </Col>
        </Row>
      </Form>
    </>
  );
};

export default RecordModalContainer;
