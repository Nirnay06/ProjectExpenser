import { useCallback } from "react";
import Form from "../UI/Form";
import InputField from "../UI/InputField";

const AccountModalContainer = (props) => {
  const {
    initialValues = {
      accountName: "",
      AccountType: "",
      CurrencyType: "INR",
      accountStartingAmount: 0,
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
            label="Account Type"
            type="text"
            name="accountType"
            required="required"
          />
          <InputField
            label="Starting Amount"
            type="number"
            name="accountStartingAmount"
            placeholder="Enter Starting Amount"
          />
          <InputField label="Currency" type="text" name="CurrencyType" />
        </Form>
      </div>
    </>
  );
};

export default AccountModalContainer;
