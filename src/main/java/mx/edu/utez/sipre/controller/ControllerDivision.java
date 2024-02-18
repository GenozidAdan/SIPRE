package mx.edu.utez.sipre.controller;

import lombok.RequiredArgsConstructor;
import mx.edu.utez.sipre.model.bean.BeanDivision;
import mx.edu.utez.sipre.service.ServiceDivision;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apisiprev1/division")
@CrossOrigin(origins = {"*"})
@RequiredArgsConstructor
public class ControllerDivision{
    private final ServiceDivision serviceDivision;
    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(serviceDivision.getAllDivisions());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(serviceDivision.getDivisionById(id));
    }
    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody BeanDivision division){
        return ResponseEntity.ok(serviceDivision.save(division));
    }
    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody BeanDivision division){
        return ResponseEntity.ok(serviceDivision.update(division));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id")Long id){
        return serviceDivision.deleteDivision(id);
    }

}
