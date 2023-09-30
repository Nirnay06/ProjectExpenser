import './styles/main.scss';
// import 'antd/dist/antd.css';
import RoutesComponent from "./utils/RoutesComponent";
import { useLocation } from "react-router-dom";
import Spinner from './components/UI/Spinner';
import { useContext } from 'react';
import AuthContext from './store/AuthContext';

function App() {
  const location = useLocation();
  const authCtx = useContext(AuthContext)

  return (
    <>
      <RoutesComponent />
      {authCtx.displaySpinner && <Spinner />}
    </>
  );
}

export default App;
