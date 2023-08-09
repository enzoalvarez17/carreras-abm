package com.teclab.carreras.abm.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teclab.carreras.abm.model.Carrera;
import com.teclab.carreras.abm.repo.CarreraRepository;

@Service
public class CarreraService {

    private final CarreraRepository carreraRepository;

    @Autowired
    public CarreraService(CarreraRepository carreraRepository) {
        this.carreraRepository = carreraRepository;
    }

    public List<Carrera> getAllCarreras() {
        return carreraRepository.findAll();
    }

    public Carrera getCarreraById(Long id) {
        return carreraRepository.findById(id).orElse(null);
    }

    public Carrera saveCarrera(Carrera carrera) {
        return carreraRepository.save(carrera);
    }

    public void deleteCarrera(Long id) {
        carreraRepository.deleteById(id);
    }

    public Carrera updatePartial(Long id, Map<String, Object> updates) {
        Carrera existingCarrera = carreraRepository.findById(id).orElseThrow(() -> new CarreraNotFoundException("Carrera no encontrada"));
    
        for (String key : updates.keySet()) {
            switch (key) {
                case "nombre":
                    existingCarrera.setNombre((String) updates.get(key));
                    break;
                case "descripcion":
                    existingCarrera.setDescripcion((String) updates.get(key));
                    break;
                case "duracion":
                    existingCarrera.setDuracion((Integer) updates.get(key));
                    break;
                default:
                    throw new InvalidFieldException("Campo " + key + " no es reconocido o no es actualizable");
            }
        }
    
        return carreraRepository.save(existingCarrera);
    }
    
    // Excepciones personalizadas
    public static class CarreraNotFoundException extends RuntimeException {
        public CarreraNotFoundException(String message) {
            super(message);
        }
    }

    public static class InvalidFieldException extends RuntimeException {
        public InvalidFieldException(String message) {
            super(message);
        }
    }
}
