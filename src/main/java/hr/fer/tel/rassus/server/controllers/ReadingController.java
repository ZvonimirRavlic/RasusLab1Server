package hr.fer.tel.rassus.server.controllers;

import hr.fer.tel.rassus.server.dto.ReadingCreate;
import hr.fer.tel.rassus.server.dto.ReadingResp;
import hr.fer.tel.rassus.server.services.ReadingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/reading")
@RestController
public class ReadingController {
    private final ReadingService readingService;

    public ReadingController(ReadingService sensorService) {
        this.readingService = sensorService;
    }

    @PostMapping("/sensor/{sensorId}")
    ResponseEntity<Void> storeReading(@PathVariable Integer sensorId, @RequestBody ReadingCreate readingCreate) {
        return readingService.storeReading(sensorId, readingCreate);
    }

    @GetMapping("/sensor/{sensorId}")
    ResponseEntity<List<ReadingResp>> getSensorReadings(@PathVariable Integer sensorId) {
        return readingService.getSensorReadings(sensorId);
    }

    @GetMapping("/{readingId}")
    ReadingResp getReading(@PathVariable Integer readingId) {
        return readingService.getReading(readingId);
    }

}
