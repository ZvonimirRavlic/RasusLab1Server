package hr.fer.tel.rassus.server.repository.impl;

import hr.fer.tel.rassus.server.beans.Sensor;
import hr.fer.tel.rassus.server.repository.SensorRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
@Repository
public class SensorRepositoryImpl implements SensorRepository {
    private static final HashMap<Integer, Sensor> map = new HashMap<>();
    private static Integer nextId = 1;

    @Override
    public Optional<Sensor> findById(int sensorId) {
        return Optional.ofNullable(map.get(sensorId));
    }

    @Override
    public List<Sensor> findAll() {
        return map.values().stream().toList();
    }

    @Override
    public List<Sensor> findAllExcept(int sensorId) {
        return map.values().stream()
                .filter(sensor -> !sensor.getSensorId().equals(sensorId))
                .toList();
    }

    @Override
    public Sensor save(Sensor sensor) {
        sensor.setSensorId(getId());
        map.put(sensor.getSensorId(), sensor);
        return sensor;
    }

    private synchronized Integer getId() {
        return nextId++;
    }
}
