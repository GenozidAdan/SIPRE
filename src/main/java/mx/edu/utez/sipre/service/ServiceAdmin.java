package mx.edu.utez.sipre.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import mx.edu.utez.sipre.model.bean.BeanAdmin;
import mx.edu.utez.sipre.model.repositories.RepoAdmin;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@RequiredArgsConstructor

public class ServiceAdmin {
    private final RepoAdmin repoAdmin;

    @Transactional(readOnly = true)
    public List<BeanAdmin> getAllAdmins() {
        return repoAdmin.findAll();
    }


    @Transactional(readOnly = true)
    public BeanAdmin getAdminById(Long id) {
        if (repoAdmin.findById(id).isEmpty()) {
            throw new EntityNotFoundException("Admin con id " + id + " no encontrado");
        }
      if (repoAdmin.findById(id).isPresent())
        return repoAdmin.findById(id).get();
      return null;
    }


    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<String> save(BeanAdmin admin) {
        if(repoAdmin.findByUserAdmin(admin.getUserAdmin()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El administrador ya existe con el nombre de usuario: " + admin.getUserAdmin());
        }
        if(repoAdmin.findByEmail(admin.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Hay un administrador que ya existe con el correo: " + admin.getEmail());
        }
        repoAdmin.save(admin);
        return ResponseEntity.status(HttpStatus.CREATED).body("Administrador creado exitosamente");
    }

    @Transactional(rollbackFor = {SQLException.class})
    public BeanAdmin update(BeanAdmin admin) {
        Optional<BeanAdmin> existingAdminOptional = repoAdmin.findById(admin.getId());

        if (existingAdminOptional.isEmpty()) {
            throw new EntityNotFoundException("Admin con id " + admin.getId() + " no encontrado. Actualización no permitida");
        }
        return repoAdmin.save(admin);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<String> deleteAdmin(Long id) {
        try {
            if (repoAdmin.findById(id).isEmpty())
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrador con ID " + id + " no encontrado. La eliminación ha sido abortada."
                        + " No se pudo eliminar");
            repoAdmin.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Administrador con ID " + id + " eliminado correctamente");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    //Métodos extra que no son parte del CRUD principal
    /*
    @Transactional(readOnly = true)
    public ResponseEntity<String> getByUserAdmin(String userAdmin) {
        Optional<Object> adminOptional = repoAdmin.findByUserAdmin(userAdmin);
        if (adminOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrador con nombre de usuario " + userAdmin + " no encontrado.");
        }
        return ResponseEntity.ok(adminOptional.get().toString());
    }



    @Transactional(readOnly = true)
    public ResponseEntity<String> getByName(String name) {
        Optional<Object> adminOptional = repoAdmin.findByName(name);
        if (adminOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrador con nombre " + name + " no encontrado.");
        }
        return ResponseEntity.ok(adminOptional.get().toString());
    }

    @Transactional(readOnly = true)
    public ResponseEntity<String> getByStatus(boolean status) {
        List<BeanAdmin> adminList = repoAdmin.findAll();
        if (adminList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay administradores registrados.");
        }
        StringBuilder response = new StringBuilder();
        for (BeanAdmin admin : adminList) {
            if (admin.isStatus() == status) {
                response.append(admin.toString()).append("\n");
            }
        }
        return ResponseEntity.ok(response.toString());
    }
    */
}
