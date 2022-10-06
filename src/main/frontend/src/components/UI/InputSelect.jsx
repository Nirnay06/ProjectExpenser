import { useContext, useState } from "react";
import { FormContext } from "./Form";

const InputSelect = (props) => {
  const [isClicked, setClicked] = useState(false);
  const formContext = useContext(FormContext);
  const {
    form,
    setForm,
    errors,
    setErrors,
    initForm,
    setInitForm,
    validationFunction,
  } = formContext;

  <select>
    <option value="volvo">Volvo</option>
    <option value="saab">Saab</option>
    <option value="opel">Opel</option>
    <option value="audi">Audi</option>
  </select>;
};

export default InputSelect;
