package hr.fer.tel.rassus.server.beans;

import java.util.List;

public class Sensor {

    private Integer sensorId;
    private Double longitude;
    private Double latitude;
    private String ip;
    private Integer port;

    public Integer getSensorId() {
        return sensorId;
    }

    public void setSensorId(Integer sensorId) {
        this.sensorId = sensorId;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Sensor getNearest(List<Sensor> sensors) {
        if (sensors.isEmpty()) {
            return null;
        }
        return sensors.stream().min((sensor1, sensor2) ->
                        Double.compare(this.haversineDistance(sensor1), this.haversineDistance(sensor2)))
                .get();
    }

    private double haversineDistance(Sensor sensor) {

        double dlon = Math.toRadians(sensor.getLongitude() - this.getLongitude());
        double dlat = Math.toRadians(sensor.getLatitude() - this.getLatitude());

        double a = Math.sin(dlat / 2) * Math.sin(dlat / 2) +
                Math.cos(Math.toRadians(this.getLatitude())) *
                        Math.cos(Math.toRadians(sensor.getLatitude())) *
                        Math.sin(dlon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return 6371 * c;
    }

}
