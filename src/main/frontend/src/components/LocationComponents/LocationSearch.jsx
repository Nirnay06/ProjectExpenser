import { AutoComplete, Select, Space } from "antd";
import { useEffect } from "react";
import { useState } from "react";
import { useGeolocated } from "react-geolocated";
import LocationMapContainer from "./LocationMap";
import useServices from "../../hooks/useSevices";
const { Option } = AutoComplete;

const LocationSearch = (props) => {
  const [data, setData] = useState([]);
  const [updatedValue, setUpdatedValue] = useState(
    props.value ? [{ name: props.value?.title, lat: props.value?.latitude, lon: props.value?.longitude }] : null
  );
  const { LocationService } = useServices();
  const setValue = (data) => {
    setUpdatedValue(data);
    let d = data[0];
    props.onChange({ ...d, title: d.name, latitude: d.lat, longitude: d.lon });
  };
  const { coords, isGeolocationAvailable, isGeolocationEnabled } = useGeolocated({
    positionOptions: {
      enableHighAccuracy: true,
    },
    userDecisionTimeout: 5000,
  });

  useEffect(() => {
    if (props.value == null && isGeolocationAvailable && isGeolocationEnabled && coords) {
      LocationService.reverseGeocoding(coords, setValue, setData);
    }
  }, [coords]);

  const handleSearch = (newValue) => {
    if (newValue) {
      LocationService.fetchLocations(newValue, setData);
    } else {
      setData([]);
    }
  };

  const handleChange = (newValue) => {
    setValue(data.filter((p) => p.place_id === newValue));
    let locationValue = data.filter((p) => p.place_id === newValue)[0];
    props.onChange({ latitude: locationValue.lat, longitude: locationValue.lon, title: locationValue.name, identifier: locationValue.placeId });
  };

  const changeMarkerCallback = (coords) => {
    LocationService.reverseGeocoding(coords, setValue, setData);
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
      }}>
      <Select
        showSearch
        value={updatedValue && updatedValue[0].name}
        placeholder={props.placeholder}
        style={props.style}
        defaultActiveFirstOption={false}
        filterOption={false}
        onSearch={handleSearch}
        onChange={handleChange}
        notFoundContent={<Option key={null}>{"No data found"}</Option>}>
        {options}
      </Select>

      {updatedValue && updatedValue[0] && updatedValue[0].lat && (
        <LocationMapContainer
          changeMarkerCallback={changeMarkerCallback}
          lat={updatedValue && updatedValue[0] && updatedValue[0].lat ? updatedValue[0].lat : 0}
          lon={updatedValue && updatedValue[0] && updatedValue[0].lon ? updatedValue[0].lon : 0}
          name={updatedValue && updatedValue[0] && updatedValue[0].name ? updatedValue[0].name : ""}></LocationMapContainer>
      )}
    </Space>
  );
};

export default LocationSearch;
