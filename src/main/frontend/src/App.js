import './styles/main.scss';
// import 'antd/dist/antd.css';
import RoutesComponent from "./utils/RoutesComponent";
import { useLocation } from "react-router-dom";
import Spinner from './components/UI/Spinner';
import { useContext, useEffect } from 'react';
import AuthContext from './store/AuthContext';
import Cookies from 'js-cookie';
import { useHistory } from 'react-router-dom/cjs/react-router-dom.min';

function App() {
  const location = useLocation();
  const history = useHistory();
  const authCtx = useContext(AuthContext);

  const removeAuthorizationQuery = () => {
    if (window.location.href.includes("Authorization=")) {
      window.location.href = window.location.origin;
    }
  }
  useEffect(() => {
    let timer1;
    if (!authCtx.isLoggedIn) {
      timer1 = setInterval(() => {
        let authCookie = Cookies.get('Authorization');
        if (authCookie) {
          window.sessionStorage.setItem("Authorization", authCookie);
          authCtx.setJWTUser();
          removeAuthorizationQuery();
          clearInterval(timer1);
        }
      }, 1000);
    } else {
      clearInterval(timer1);
    }
    return () => {
      clearInterval(timer1);
    };
  }, [authCtx.isLoggedIn]);

  return (
    <>
      <RoutesComponent />
      {authCtx.displaySpinner && <Spinner />}
    </>
  );
}

export default App;
