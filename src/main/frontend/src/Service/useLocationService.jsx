const useLocationService = () => {
  let timeout;
  let currentValue;
  const formatLocationData = (data) => {
    return {
      name: data.formatted,
      lon: data.lon,
      lat: data.lat,
      place_id: data.place_id,
      addressLine: data.address_line1 + " " + data.address_line2,
      city: data.county,
      state: data.state,
      country: data.country,
    };
  };

  const fetchLocations = (value, callback) => {
    if (timeout) {
      clearTimeout(timeout);
      timeout = null;
    }

    currentValue = value;

    const sendLocationAPI = () => {
      fetch(`https://api.geoapify.com/v1/geocode/autocomplete?text=${value}&apiKey=3b4517dcf1b547cbaba0643e1615c1c2`)
        .then((response) => response.json())
        .then((d) => {
          if (currentValue === value) {
            const { features } = d;
            const data = features.map((feature) => {
              return formatLocationData(feature.properties);
            });
            callback(data);
          }
        });
    };

    timeout = setTimeout(sendLocationAPI, 300);
  };

  const reverseGeocoding = (coords, setValue, setData) => {
    fetch(
      `https://api.geoapify.com/v1/geocode/reverse?lat=${coords.latitude}&lon=${coords.longitude}&format=json&&apiKey=3b4517dcf1b547cbaba0643e1615c1c2`
    )
      .then((response) => response.json())
      .then((d) => {
        setValue([formatLocationData(d?.results[0])]);
        setData(
          d?.results.map((data) => {
            return formatLocationData(data);
          })
        );
      });
  };

  return { LocationService: { formatLocationData, fetchLocations, reverseGeocoding } };
};

export default useLocationService;
