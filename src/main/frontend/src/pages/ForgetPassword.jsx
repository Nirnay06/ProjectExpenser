import { useCallback } from "react";
import Form from "../components/UI/Form";
import InputField from "../components/UI/InputField";
import { useHistory } from "react-router-dom";
import { validEmail } from "../utils/RegexUtil";
import useServices from "../hooks/useSevices";

const ForgetPasswordPage = () => {
  const history = useHistory();
  const { AuthenticationService } = useServices();
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
  const moveToLoginHandler = () => {
    history.push("/login");
  };
  return (
    <>
      <div className="Login-box__left">
        <h2 className="heading--2">Reset password</h2>
        <Form
          submit={(form) => {
            AuthenticationService.sendResetPasswordMail(form);
          }}
          initialValues={initialValues}
          validationFunction={validationHandler}
          buttonText="Send Reset Password Email"
          cancel={moveToLoginHandler}
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

export default ForgetPasswordPage;
