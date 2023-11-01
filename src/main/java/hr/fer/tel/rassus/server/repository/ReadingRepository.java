package hr.fer.tel.rassus.server.repository;

import hr.fer.tel.rassus.server.beans.Reading;

import java.util.List;
import java.util.Optional;

public interface ReadingRepository {

    Reading save(Reading reading);

    Optional<Reading> findById(Integer readingId);

    List<Reading> findAllSensorReadings(Integer sensorId);
}
