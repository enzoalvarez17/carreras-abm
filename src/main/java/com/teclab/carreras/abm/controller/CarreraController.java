package com.teclab.carreras.abm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.teclab.carreras.abm.model.Carrera;
import com.teclab.carreras.abm.service.CarreraService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/carreras")
public class CarreraController {

    private final CarreraService carreraService;

    @Autowired
    public CarreraController(CarreraService carreraService) {
        this.carreraService = carreraService;
    }

    @GetMapping
    public ResponseEntity<List<Carrera>> getAllCarreras() {
        return ResponseEntity.ok(carreraService.getAllCarreras());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrera> getCarreraById(@PathVariable Long id) {
        Carrera carrera = carreraService.getCarreraById(id);
        return carrera != null ? ResponseEntity.ok(carrera) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Carrera> createCarrera(@RequestBody Carrera carrera) {
        return ResponseEntity.ok(carreraService.saveCarrera(carrera));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carrera> updateCarrera(@PathVariable Long id, @RequestBody Carrera carrera) {
        Carrera existingCarrera = carreraService.getCarreraById(id);
        if (existingCarrera != null) {
            existingCarrera.setNombre(carrera.getNombre());
            existingCarrera.setDescripcion(carrera.getDescripcion());
            existingCarrera.setDuracion(carrera.getDuracion());
            Carrera updatedCarrera = carreraService.saveCarrera(existingCarrera);
            return ResponseEntity.ok(updatedCarrera);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Carrera> patchCarrera(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Carrera updatedCarrera = carreraService.updatePartial(id, updates);
        return updatedCarrera != null ? ResponseEntity.ok(updatedCarrera) : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarrera(@PathVariable Long id) {
        Carrera existingCarrera = carreraService.getCarreraById(id);
        if (existingCarrera != null) {
            carreraService.deleteCarrera(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
