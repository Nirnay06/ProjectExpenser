import useHttp from "../hooks/useHttp";

const useRecordService = () => {
  const { sendRequest } = useHttp();
  const fetchAllLabelsByUser = () => {
    return [
      { label: "business", color: "cyan", userLabelIdentifier: "a881ef56-eb9c-4e55-97e3-573c03bb2d84" },
      { label: "home", color: "gold", userLabelIdentifier: "c0cd4b5e-93a9-457f-bdb1-b0f9ef3e3e3b" },
      { label: "green", color: "orange", userLabelIdentifier: "as" },
      { label: "cyan", color: "green", userLabelIdentifier: "fds" },
      { label: "orange", color: "blue", userLabelIdentifier: "dsfs" },
    ];
  };

  const fetchAllAccountsByUser = () => {
    return [
      { identifier: "f34cdf77-3bc7-49ef-b023-a2ce7c6527df", accountName: "Fed Bank", accountColor: "yellow" },
      { identifier: "2", accountName: "PNB", accountColor: "yellow" },
      { identifier: "3", accountName: "SBI", accountColor: "yellow" },
      { identifier: "5", accountName: "UCO", accountColor: "yellow" },
      { identifier: "6", accountName: "ICICI", accountColor: "yellow" },
    ];
  };
  return { RecordService: { fetchAllLabelsByUser, fetchAllAccountsByUser } };
};

export default useRecordService;
