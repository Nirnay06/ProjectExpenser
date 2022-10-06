import { AutoComplete, Select, Space } from "antd";
import { useState } from "react";
import LocationMapContainer from "./LocationMap";
const { Option } = AutoComplete;

let timeout;
let currentValue;

const fetchLocations = (value, callback) => {
  if (timeout) {
    clearTimeout(timeout);
    timeout = null;
  }

  currentValue = value;

  const sendLocationAPI = () => {
    fetch(
      `https://api.geoapify.com/v1/geocode/autocomplete?text=${value}&apiKey=3b4517dcf1b547cbaba0643e1615c1c2`
    )
      .then((response) => response.json())
      .then((d) => {
        if (currentValue === value) {
          const { features } = d;
          const data = features.map((feature) => ({
            name: feature.properties.formatted,
            lon: feature.properties.lon,
            lat: feature.properties.lat,
            place_id: feature.properties.place_id,
          }));
          callback(data);
        }
      });
  };

  timeout = setTimeout(sendLocationAPI, 300);
};

const LocationSearch = (props) => {
  const [data, setData] = useState([]);
  const [updatedValue, setValue] = useState(props.value);
  const handleSearch = (newValue) => {
    if (newValue) {
      fetchLocations(newValue, setData);
    } else {
      setData([]);
    }
  };

  const handleChange = (newValue) => {
    setValue(data.filter((p) => p.place_id === newValue));
    props.onChange(data.filter((p) => p.place_id === newValue));
  };

  const options = data.map((d) => (
    <Option key={d.place_id} lat={d.lat} lon={d.lon}>
      {d.name}
    </Option>
  ));
  return (
    <Space
      direction="vertical"
      size="middle"
      style={{
        display: "flex",
      }}
    >
      <Select
        showSearch
        value={props.value && props.value.name}
        placeholder={props.placeholder}
        style={props.style}
        defaultActiveFirstOption={false}
        filterOption={false}
        onSearch={handleSearch}
        onChange={handleChange}
        notFoundContent={<Option key={null}>{"No data found"}</Option>}
      >
        {options}
      </Select>

      <LocationMapContainer
        lat={
          updatedValue && updatedValue[0] && updatedValue[0].lat
            ? updatedValue[0].lat
            : 0
        }
        lon={
          updatedValue && updatedValue[0] && updatedValue[0].lon
            ? updatedValue[0].lon
            : 0
        }
        name={
          updatedValue && updatedValue[0] && updatedValue[0].name
            ? updatedValue[0].name
            : ""
        }
      ></LocationMapContainer>
    </Space>
  );
};

export default LocationSearch;
