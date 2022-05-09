import { useCallback } from "react";
import Form from "../components/UI/Form";
import InputField from "../components/UI/InputField";
import { useHistory } from "react-router-dom";
import useServices from "../hooks/useSevices";
import { validEmail } from "../utils/RegexUtil";

const SendConfirmationPage = () => {
  const { AuthenticationService } = useServices();
  const history = useHistory();
  const initialValues = {
    username: "",
  };
  const validationHandler = useCallback((form) => {
    let errors = {};
    if (!validEmail.test(form.username)) {
      errors = { ...errors, username: "Enter a valid username" };
    }
    return errors;
  }, []);
  const moveToSignupHandler = () => {
    history.push("/signup");
  };
  return (
    <>
      <div className="Login-box__left">
        <h2 className="heading--2">Re-send account confirmation</h2>
        <Form
          submit={(form) => {
            AuthenticationService.sendConfirmationMail(form);
          }}
          initialValues={initialValues}
          validationFunction={validationHandler}
          buttonText="Send Email"
          cancel={moveToSignupHandler}
        >
          <InputField
            label="Username"
            type="email"
            name="username"
            placeholder="Enter your email address"
            required="required"
          />
        </Form>
      </div>
    </>
  );
};

export default SendConfirmationPage;
