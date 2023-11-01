package hr.fer.tel.rassus.server.services;

import hr.fer.tel.rassus.server.beans.Sensor;
import hr.fer.tel.rassus.server.dto.SensorCreate;
import hr.fer.tel.rassus.server.dto.SensorResp;
import hr.fer.tel.rassus.server.mapper.Mapper;
import hr.fer.tel.rassus.server.repository.SensorRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@Service
public class SensorService {

    private final SensorRepository sensorRepository;
    private final Mapper mapper;

    public SensorService(SensorRepository sensorRepository,
                         Mapper mapper) {
        this.sensorRepository = sensorRepository;
        this.mapper = mapper;
    }

    public ResponseEntity<Void> register(SensorCreate sensorCreate) {
        final Sensor sensor = sensorRepository.save(mapper.toSensor(sensorCreate));
        final URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/sensors/{sensorId}")
                .buildAndExpand(sensor.getSensorId())
                .toUri();
        return ResponseEntity.status(CREATED).header(HttpHeaders.LOCATION, location.toString()).build();
    }

    public SensorResp getNearestNeighbour(Integer sensorId) {
        return mapper.toSensorResp(sensorRepository.findById(sensorId)
                .orElseThrow(() -> new RuntimeException("Sensor doesn't exist!"))
                .getNearest(sensorRepository.findAllExcept(sensorId)));

    }

    public SensorResp getSensor(Integer sensorId) {
        return mapper.toSensorResp(sensorRepository.findById(sensorId)
                .orElseThrow(() -> new RuntimeException("Sensor doesn't exist!")));
    }

    public List<SensorResp> getSensors() {
        return sensorRepository.findAll().stream().map(mapper::toSensorResp).toList();
    }
}
