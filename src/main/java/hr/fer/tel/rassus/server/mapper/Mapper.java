package hr.fer.tel.rassus.server.mapper;

import hr.fer.tel.rassus.server.beans.Reading;
import hr.fer.tel.rassus.server.beans.Sensor;
import hr.fer.tel.rassus.server.dto.ReadingCreate;
import hr.fer.tel.rassus.server.dto.ReadingResp;
import hr.fer.tel.rassus.server.dto.SensorCreate;
import hr.fer.tel.rassus.server.dto.SensorResp;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    public Sensor toSensor(SensorCreate sensorCreate) {
        final Sensor sensor = new Sensor();
        sensor.setLatitude(sensorCreate.getLatitude());
        sensor.setLongitude(sensorCreate.getLongitude());
        sensor.setIp(sensorCreate.getIp());
        sensor.setPort(sensorCreate.getPort());
        return sensor;
    }

    public SensorResp toSensorResp(Sensor sensor) {
        if (sensor == null) {
            return null;
        }
        final SensorResp sensorResp = new SensorResp();
        sensorResp.setLatitude(sensor.getLatitude());
        sensorResp.setLongitude(sensor.getLongitude());
        sensorResp.setIp(sensor.getIp());
        sensorResp.setPort(sensor.getPort());
        return sensorResp;
    }

    public Reading toReading(Integer sensorId, ReadingCreate readingCreate) {
        final Reading reading = new Reading();
        reading.setSensorId(sensorId);
        reading.setTemperature(readingCreate.getTemperature());
        reading.setPressure(readingCreate.getPressure());
        reading.setHumidity(readingCreate.getHumidity());
        reading.setCo(readingCreate.getCo());
        reading.setSo2(readingCreate.getSo2());
        reading.setNo2(readingCreate.getNo2());
        return reading;
    }

    public ReadingResp toReadingResp(Reading reading) {
        final ReadingResp readingResp = new ReadingResp();
        readingResp.setId(reading.getReadingId());
        readingResp.setTemperature(reading.getTemperature());
        readingResp.setHumidity(reading.getHumidity());
        readingResp.setPressure(reading.getPressure());
        readingResp.setCo(reading.getCo());
        readingResp.setNo2(reading.getNo2());
        readingResp.setSo2(reading.getSo2());
        return readingResp;
    }

}
