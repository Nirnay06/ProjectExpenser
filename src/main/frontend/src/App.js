import './styles/main.scss';
import 'antd/dist/antd.css';
import RoutesComponent from "./utils/RoutesComponent";
import { useLocation } from "react-router-dom";

function App() {
  const location = useLocation();

  return (
    <>
      <RoutesComponent />
    </>
  );
}

export default App;
