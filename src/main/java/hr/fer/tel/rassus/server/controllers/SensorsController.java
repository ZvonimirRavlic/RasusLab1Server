package hr.fer.tel.rassus.server.controllers;

import hr.fer.tel.rassus.server.dto.SensorCreate;
import hr.fer.tel.rassus.server.dto.SensorResp;
import hr.fer.tel.rassus.server.services.SensorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/sensors")
@RestController
public class SensorsController {

    private final SensorService sensorService;

    public SensorsController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @PostMapping
    ResponseEntity<Void> register(@RequestBody SensorCreate sensorCreate) {
        return sensorService.register(sensorCreate);
    }

    @GetMapping("/{sensorId}/nearestNeighbour")
    SensorResp getNearestNeighbour(@PathVariable Integer sensorId) {
        return sensorService.getNearestNeighbour(sensorId);
    }

    @GetMapping("/{sensorId}")
    SensorResp getSensor(@PathVariable Integer sensorId) {
        return sensorService.getSensor(sensorId);
    }

    @GetMapping
    List<SensorResp> getSensors() {
        return sensorService.getSensors();
    }


}
