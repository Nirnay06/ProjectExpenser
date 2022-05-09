import { Button, message as antdMessage } from "antd";
import { useState } from "react";
import { useEffect } from "react";
import { useHistory, useParams } from "react-router-dom";
import styles from "../styles/UI/form.module.scss";

const UserAccountConfirmationPage = () => {
  const params = useParams();
  const history = useHistory();
  const [message, setMessage] = useState();
  const [responseType, setReponseType] = useState();
  useEffect(() => {
    async function sendReq() {
      const res = await fetch(
        "/api/auth/registrationConfirm?token=" + params.tokenIdentifier
      );
      const data = await res.json();
      setMessage(data.message);
      setReponseType(res.ok ? true : false);
      if (res.ok) {
        setTimeout(() => {
          antdMessage.loading("Redirecting to login page.");
        }, 1000);
        setTimeout(() => {
          history.replace("/login");
        }, 3000);
      }
    }
    sendReq();
  }, [history, params.tokenIdentifier]);
  return (
    <div className="Login-box__left" style={{ width: "100%" }}>
      {message && <h2 className="heading--3 center">{message}</h2>}
      {message && !responseType && (
        <>
          <h3>
            You can create a new account or re-send account confirmation mail
          </h3>
          <div
            className={[styles.Form__buttons, styles[`Form__buttons_2`]].join(
              " "
            )}
          >
            <Button
              type="primary"
              onClick={() => {
                history.push("/signup");
              }}
              shape="round"
              size="large"
            >
              Create New Account
            </Button>
            <Button
              type="secondary"
              onClick={() => {
                history.push("/sendConfirmationMail");
              }}
              shape="round"
              size="large"
            >
              Resend Confirmation Email
            </Button>
          </div>
        </>
      )}
    </div>
  );
};
export default UserAccountConfirmationPage;
