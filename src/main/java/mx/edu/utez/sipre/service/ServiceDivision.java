package mx.edu.utez.sipre.service;

import lombok.RequiredArgsConstructor;
import mx.edu.utez.sipre.model.bean.BeanDivision;
import mx.edu.utez.sipre.model.repositories.RepoDivision;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ServiceDivision {
    private final RepoDivision repoDivision;

    @Transactional(readOnly = true)
    public List<BeanDivision> getAllDivisions() {
        return repoDivision.findAll();
    }

    @Transactional(readOnly = true)
    public  BeanDivision getDivisionById(Long id) {
        if (repoDivision.findById(id).isEmpty()) {
            throw new RuntimeException("División con id " + id + " no encontrado");
        }
        return repoDivision.findById(id).get();
    }

    @Transactional(rollbackFor = {Exception.class})
    public ResponseEntity<String> save(BeanDivision division) {
        // Comprobar si ya existe una división con el mismo nombre
        if (repoDivision.findByName(division.getName()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe una división con este nombre");
        }

        if (repoDivision.findBySiglas(division.getSiglas()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe una división con estas siglas");
        }

        repoDivision.save(division);
        return ResponseEntity.status(HttpStatus.CREATED).body("División creada exitosamente");
    }


@Transactional(rollbackFor = {Exception.class})
    public BeanDivision update(BeanDivision division) {
        if (repoDivision.findById(division.getId()).isEmpty()) {
            throw new RuntimeException("División con id " + division.getId() + " no encontrado. Actualización no permitida");
        }
        return repoDivision.save(division);
    }

    @Transactional(rollbackFor = {Exception.class})
    public ResponseEntity<String> deleteDivision(Long id) {
        if (repoDivision.findById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("División con id " + id + " no encontrado");
        }
        repoDivision.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("División eliminada exitosamente");
    }

}
