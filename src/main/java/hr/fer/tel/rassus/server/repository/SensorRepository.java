package hr.fer.tel.rassus.server.repository;

import hr.fer.tel.rassus.server.beans.Sensor;

import java.util.List;
import java.util.Optional;

public interface SensorRepository {
    Optional<Sensor> findById(int sensorId);

    List<Sensor> findAll();

    List<Sensor> findAllExcept(int sensorId);

    Sensor save(Sensor sensor);
}
