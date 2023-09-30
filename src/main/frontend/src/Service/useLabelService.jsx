import useHttp from "../hooks/useHttp";

const useLabelService = () => {
  const { sendRequest } = useHttp();

  const fetchAllLabelsByUserForRecord = (setData) => {
    sendRequest({ url: "/api/label/getLabelsForRecord" }, (data) => {
      setData(data);
    });
  };

  const fetchDefaultLabelsByUserForRecord = (setData) => {
    sendRequest({ url: "/api/label/getDefaultLabels" }, (data) => {
      setData(data);
    });
  };
  return { LabelService: { fetchAllLabelsByUserForRecord, fetchDefaultLabelsByUserForRecord } };
};

export default useLabelService;
