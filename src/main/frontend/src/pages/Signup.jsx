import Form from "./../components/UI/Form";
import { useCallback } from "react";
import { Link } from "react-router-dom";
import InputField from "../components/UI/InputField";
import useServices from "../hooks/useSevices";
import { validEmail, validPassword } from "../utils/RegexUtil";

const SignupPage = () => {
  const { AuthenticationService } = useServices();
  const initialValues = {
    username: "",
    password: "",
    firstname: "",
    lastname: "",
  };
  const validationHandler = useCallback((form) => {
    let errors = {};
    if (!validEmail.test(form.username)) {
      errors = { ...errors, username: "Enter a valid username" };
    }
    if (!validPassword.test(form.password)) {
      errors = { ...errors, password: "Enter a valid password" };
    }
    return errors;
  }, []);
  return (
    <>
      <div className="Login-box__left">
        <h2 className="heading--2">Create account</h2>
        <h3 className="heading--3">Sign up below to create a secure account</h3>
        <Form
          submit={(form) => {
            AuthenticationService.createUser(form);
          }}
          initialValues={initialValues}
          validationFunction={validationHandler}
          buttonText="Sign Up"
        >
          <InputField
            label="First Name"
            type="text"
            name="firstname"
            placeholder="Enter your First Name"
            required="required"
          />
          <InputField
            label="Last Name"
            type="text"
            name="lastname"
            placeholder="Enter your Last Name"
          />
          <InputField
            label="Username"
            type="email"
            name="username"
            placeholder="Enter your email address"
            required="required"
          />
          <InputField
            label="Password"
            type="password"
            name="password"
            placeholder="Enter your password"
            required="required"
            info="Pasword should have minimum 8 characters, at least 1 uppercase letter, 1 lowercase letter, 1 number and 1 special character"
          />
        </Form>
        <div className="Forget__password">
          <p>
            Already have account? <Link to="/login">Sign In</Link>
          </p>
        </div>
        <div className="Forget__password">
          <Link to="/sendConfirmationMail">Resend Confirmation Mail</Link>
        </div>
      </div>
    </>
  );
};

export default SignupPage;
