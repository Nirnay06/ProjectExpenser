import useHttp from "../hooks/useHttp";

const useLabelService = () => {
  const { sendRequest } = useHttp();

  const fetchAllLabelsByUserForRecord = (setData) => {
    sendRequest({ url: "/api/label/getLabelsForRecord" }, (data) => {
      console.log(data);
      setData(data);
    });
  };
  return { LabelService: { fetchAllLabelsByUserForRecord } };
};

export default useLabelService;
