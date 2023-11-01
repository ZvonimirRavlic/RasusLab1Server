package hr.fer.tel.rassus.server.services;

import hr.fer.tel.rassus.server.beans.Reading;
import hr.fer.tel.rassus.server.dto.ReadingCreate;
import hr.fer.tel.rassus.server.dto.ReadingResp;
import hr.fer.tel.rassus.server.mapper.Mapper;
import hr.fer.tel.rassus.server.repository.ReadingRepository;
import hr.fer.tel.rassus.server.repository.SensorRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Service
public class ReadingService {
    private final SensorRepository sensorRepository;

    private final ReadingRepository readingRepository;
    private final Mapper mapper;

    public ReadingService(SensorRepository sensorRepository,
                          ReadingRepository readingRepository,
                          Mapper mapper) {
        this.sensorRepository = sensorRepository;
        this.readingRepository = readingRepository;
        this.mapper = mapper;
    }

    public ResponseEntity<Void> storeReading(Integer sensorId, ReadingCreate readingCreate) {
        if (sensorRepository.findById(sensorId).isEmpty()) {
            return ResponseEntity.status(NO_CONTENT).build();
        }
        final Reading reading = readingRepository.save(mapper.toReading(sensorId, readingCreate));
        final URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/reading/{readingId}")
                .buildAndExpand(reading.getReadingId())
                .toUri();
        return ResponseEntity.status(CREATED).header(HttpHeaders.LOCATION, location.toString()).build();
    }

    public ReadingResp getReading(Integer readingId) {
        return mapper.toReadingResp(readingRepository.findById(readingId).orElseThrow(() -> new RuntimeException("Reading doesn't exists!")));
    }

    public ResponseEntity<List<ReadingResp>> getSensorReadings(Integer sensorId) {
        if (sensorRepository.findById(sensorId).isEmpty()) {
            return ResponseEntity.status(NO_CONTENT).build();
        }
        return ResponseEntity.status(OK).body(readingRepository.findAllSensorReadings(sensorId).stream().map(mapper::toReadingResp).toList());
    }
}
