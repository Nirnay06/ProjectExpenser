import { useCallback, useContext } from "react";
import { Link, useHistory, useLocation } from "react-router-dom";
import Form from "../components/UI/Form";
import InputField from "../components/UI/InputField";
import Spinner from "../components/UI/Spinner";
import useServices from "../hooks/useSevices";
import AuthContext from "../store/AuthContext";
import { GithubLoginButton, FacebookLoginButton, GoogleLoginButton } from "react-social-login-buttons";

const LoginPage = () => {
  const authCtx = useContext(AuthContext);
  const { AuthenticationService } = useServices();
  let history = useHistory();
  const location = useLocation();
  const onLoginHandler = (form) => {
    AuthenticationService.loginHandler(form["username"], form["password"]);
  };
  if (authCtx.isLoggedIn) {
    history.push(location && location.state && location.state.from ? location.state.from : "/");
  }

  const initialValues = {
    username: "",
    password: "",
  };
  const validationHandler = useCallback((form) => {
    let errors = {};
    return errors;
  }, []);
  return (
    <>
      <div className="Login-box__left">
        <h2 className="heading--2">Welcome Back !</h2>
        <Form
          submit={(form) => {
            onLoginHandler(form);
          }}
          initialValues={initialValues}
          validationFunction={validationHandler}
          buttonText="Login"
        >
          <InputField label="Username" type="email" name="username" placeholder="Enter your email address" required="required" />
          <InputField label="Password" type="password" name="password" placeholder="Enter your password" required="required" />
        </Form>
        <div className="Forget__password">
          <Link to="/reset-password">Reset Password</Link>
        </div>

        <div className="Forget__password">
          <p>
            Dont't have account? <Link to="/signup">Sign Up</Link>
          </p>
        </div>
      </div>
      <div className="Login-box__right Login-box__dark">
        <h3 className="heading--3 center uppercase">this is the social login</h3>
        <h3 className="heading--3 center uppercase">coming soon!!</h3>
        <a href="http://localhost:8080/oauth2/authorize/google">
          <GoogleLoginButton />
        </a>
        <a href="http://localhost:8080/oauth2/authorize/github">
          <GithubLoginButton />
        </a>
        <a href="http://localhost:8080/oauth2/authorize/facebook">
          <FacebookLoginButton />
        </a>
      </div>
    </>
  );
};

export default LoginPage;
