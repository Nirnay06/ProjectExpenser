import useHttp from "../hooks/useHttp";

const useRecordService = () => {
  const { sendRequest } = useHttp();
  const fetchAllLabelsByUser = () => {
    return [
      { value: "gold", color: "cyan" },
      { value: "lime", color: "gold" },
      { value: "green", color: "orange" },
      { value: "cyan", color: "green" },
      { value: "orange", color: "blue" },
    ];
  };
  return { RecordService: { fetchAllLabelsByUser } };
};

export default useRecordService;
