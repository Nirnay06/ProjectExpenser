import { MapContainer, TileLayer, Marker, Popup } from "react-leaflet";
import "leaflet/dist/leaflet.css";
import L from "leaflet";
import icon from "leaflet/dist/images/marker-icon.png";
import iconShadow from "leaflet/dist/images/marker-shadow.png";
import { useState } from "react";
import { useEffect } from "react";
import { useRef } from "react";
import { useMemo } from "react";
const LocationMapContainer = ({ lat, lon, name = "", changeMarkerCallback = () => {} }) => {
  const [position, setPosition] = useState([lat, lon]);
  const markerRef = useRef(null);
  const [map, setMap] = useState(null);
  useEffect(() => {
    if (map) {
      map.setView([lat, lon], map.getZoom() ? map.getZoom() : "8");
      setPosition([lat, lon]);
    }
  }, [lat, lon, map]);

  const eventHandlers = useMemo(
    () => ({
      dragend() {
        const marker = markerRef.current;
        if (marker != null) {
          setPosition(marker.getLatLng());
          changeMarkerCallback({ latitude: marker.getLatLng().lat, longitude: marker.getLatLng().lng });
        }
      },
    }),
    []
  );
  let DefaultIcon = L.icon({
    iconUrl: icon,
    shadowUrl: iconShadow,
  });
  L.Marker.prototype.options.icon = DefaultIcon;

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
        easeLinearity={0.35}>
        <TileLayer attribution="" url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png" />
        <Marker draggable={true} eventHandlers={eventHandlers} position={position} ref={markerRef}>
          <Popup closeButton={false} maxWidth={100}>
            {name}
          </Popup>
        </Marker>
      </MapContainer>
    </div>
  );
};
export default LocationMapContainer;
