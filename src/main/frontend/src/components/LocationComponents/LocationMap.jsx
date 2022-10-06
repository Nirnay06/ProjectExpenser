import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";
import "leaflet/dist/leaflet.css";
import L from "leaflet";
import icon from "leaflet/dist/images/marker-icon.png";
import iconShadow from "leaflet/dist/images/marker-shadow.png";
import { useState } from "react";
import { useEffect } from "react";
const LocationMapContainer = ({ lat, lon, name = "" }) => {
  let DefaultIcon = L.icon({
    iconUrl: icon,
    shadowUrl: iconShadow,
  });

  L.Marker.prototype.options.icon = DefaultIcon;
  const [map, setMap] = useState(null);
  useEffect(() => {
    if (map) {
      map.setView([lat, lon], "8");
    }
  }, [lat, lon, map]);
  return (
    <div>
      <MapContainer
        center={[lat, lon]}
        zoom={8}
        maxZoom={14}
        attributionControl={true}
        zoomControl={true}
        doubleClickZoom={true}
        scrollWheelZoom={false}
        dragging={true}
        animate={true}
        ref={setMap}
        easeLinearity={0.35}
      >
        <TileLayer
          attribution=""
          url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        />
        <Marker position={[lat, lon]}>
          <Popup closeButton={false}>{name}</Popup>
        </Marker>
      </MapContainer>
    </div>
  );
};
export default LocationMapContainer;
