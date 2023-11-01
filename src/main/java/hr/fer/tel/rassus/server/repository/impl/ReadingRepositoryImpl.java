package hr.fer.tel.rassus.server.repository.impl;

import hr.fer.tel.rassus.server.beans.Reading;
import hr.fer.tel.rassus.server.repository.ReadingRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class ReadingRepositoryImpl implements ReadingRepository {

    private static final HashMap<Integer, Reading> map = new HashMap<>();
    private static Integer nextId = 1;

    @Override
    public List<Reading> findAllSensorReadings(Integer sensorId) {
        return map.values().stream()
                .filter(reading -> reading.getSensorId().equals(sensorId)).
                toList();
    }


    @Override
    public Reading save(Reading reading) {
        reading.setReadingId(getId());
        map.put(reading.getReadingId(), reading);
        return reading;
    }

    @Override
    public Optional<Reading> findById(Integer readingId) {
        return Optional.ofNullable(map.get(readingId));
    }

    private synchronized Integer getId() {
        return nextId++;
    }
}
