import Form from "../components/UI/Form";
import { useCallback, useState } from "react";
import { Link, useParams } from "react-router-dom";
import InputField from "../components/UI/InputField";
import useServices from "../hooks/useSevices";
import { validEmail, validPassword } from "../utils/RegexUtil";
import { useEffect } from "react";

const ResetPasswordPage = () => {
  const { AuthenticationService } = useServices();
  const [data, setData] = useState();
  const params = useParams();
  useEffect(() => {
    AuthenticationService.fetchUserDetailsForToken(
      params.tokenIdentifier,
      setData
    );
  }, []);
  console.log(data);
  const initialValues = {
    username: data ? data.username : "",
    password: "",
    firstname: data ? data.firstname : "",
    lastname: data ? data.lastname : "",
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
        <h2 className="heading--2">Reset Your Password</h2>
        {data && (
          <Form
            submit={(form) => {
              AuthenticationService.resetUserPassword(form);
            }}
            initialValues={initialValues}
            validationFunction={validationHandler}
            buttonText="Reset Password"
          >
            <InputField
              label="First Name"
              type="text"
              name="firstname"
              placeholder="Enter your First Name"
              disabled="disabled"
            />
            <InputField
              label="Last Name"
              type="text"
              name="lastname"
              placeholder="Enter your Last Name"
              disabled="disabled"
            />
            <InputField
              label="Username"
              type="email"
              name="username"
              placeholder="Enter your email address"
              disabled="disabled"
            />
            <InputField
              label="Password"
              type="password"
              name="password"
              placeholder="Enter your password"
              required="required"
              info="Pasword should have minimum 8 characters, at least 1 uppercase letter, 1 lowercase letter, 1 number and 1 special character"
            />
            {console.log(data)}
          </Form>
        )}
        <div className="Forget__password">
          <Link to="/login">Sign In</Link>
          <Link to="/signup">Sign Up</Link>
        </div>
        <div className="Forget__password"></div>
      </div>
    </>
  );
};

export default ResetPasswordPage;
