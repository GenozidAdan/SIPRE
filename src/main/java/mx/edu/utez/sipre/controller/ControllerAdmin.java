package mx.edu.utez.sipre.controller;

import lombok.RequiredArgsConstructor;
import mx.edu.utez.sipre.model.bean.BeanAdmin;
import mx.edu.utez.sipre.service.ServiceAdmin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apisiprev1/admin")
@CrossOrigin(origins = {"*"})
@RequiredArgsConstructor
public class ControllerAdmin {
    private final ServiceAdmin serviceAdmin;

    @GetMapping("/")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(serviceAdmin.getAllAdmins());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        return ResponseEntity.ok(serviceAdmin.getAdminById(id));
    }

    @PostMapping("/")
    public ResponseEntity<?> save(@RequestBody BeanAdmin admin){
        return ResponseEntity.ok(serviceAdmin.save(admin));
    }

    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody BeanAdmin admin){
        return ResponseEntity.ok(serviceAdmin.update(admin));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")Long id){
        return serviceAdmin.deleteAdmin(id);
    }


}
