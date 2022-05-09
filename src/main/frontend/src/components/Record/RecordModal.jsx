import { useCallback } from "react";
import Form from "../UI/Form";
import InputField from "../UI/InputField";

const RecordModalContainer = (props) => {
  const {
    initialValues = {
      accountName: "",
      amount: "",
      CurrencyType: "INR",
      category: 0,
      type: "expense",
      label: "",
      date: new Date(),
    },
  } = props;
  const validationHandler = useCallback((form) => {
    let errors = {};
    return errors;
  }, []);
  return (
    <>
      <div className="Login-box__left">
        <Form
          submit={(form) => {}}
          initialValues={initialValues}
          validationFunction={validationHandler}
          buttonText="Save"
        >
          <InputField
            label="Account Name"
            type="text"
            name="accountName"
            placeholder="Enter Account Name"
            required="required"
          />
          <InputField
            label="Amount"
            type="number"
            name="amount"
            required="required"
          />{" "}
          CurrencyType: "INR", category: 0, type: "expense", label: "", date:
          new Date()
          <InputField
            label="Currency"
            type="text"
            name="CurrencyType"
            required="required"
          />
          <InputField
            label="Category"
            type="text"
            name="category"
            required="required"
          />
          <InputField label="Type" type="text" name="type" />
          <InputField label="Label" type="text" name="label" />
          <InputField
            label="Date"
            type="text"
            name="Date"
            required="required"
            placeholder="Enter the date (dd/mm/yyy)"
          />
        </Form>
      </div>
    </>
  );
};

export default RecordModalContainer;
