package mx.edu.utez.sipre.service;

import lombok.RequiredArgsConstructor;
import mx.edu.utez.sipre.model.bean.BeanDivision;
import mx.edu.utez.sipre.model.bean.BeanWorker;
import mx.edu.utez.sipre.model.dto.DtoWorker;
import mx.edu.utez.sipre.model.repositories.RepoWorker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ServiceWorker {
private final RepoWorker repoWorker;

    @Transactional(readOnly = true)
    public List<BeanWorker> getAllWorkers() {
        return repoWorker.findAll();
    }

    @Transactional(readOnly = true)
    public BeanWorker getWorkerById(Long id) {
        if (repoWorker.findById(id).isEmpty()) {
            throw new RuntimeException("Trabajador con id " + id + " no encontrado");
        }
        return repoWorker.findById(id).get();
    }


    @Transactional(rollbackFor = {Exception.class})
    public BeanWorker save(DtoWorker dtoWorker) {
        BeanDivision division = BeanDivision.builder().id((long) dtoWorker.getIdDivision()).build();
        BeanWorker worker = BeanWorker.builder()
                .id((long) dtoWorker.getId())
                .name(dtoWorker.getName())
                .lastname(dtoWorker.getLastname())
                .email(dtoWorker.getEmail())
                .password(dtoWorker.getPassword())
                .status(dtoWorker.getStatus())
                .userWorker(dtoWorker.getUserWorker())
                .saldo(dtoWorker.getSaldo())
                .division(division)
                .build();
        return repoWorker.save(worker);
    }


    @Transactional(rollbackFor = {Exception.class})
    public BeanWorker update(BeanWorker worker) {
        if (repoWorker.findById((long) worker.getId()).isEmpty()) {
            throw new RuntimeException("Trabajador con id " + worker.getId() + " no encontrado. Actualización no permitida");
        }
        return repoWorker.save(worker);
    }

    @Transactional(rollbackFor = {Exception.class})
    public ResponseEntity<String> deleteWorker(Long id) {
        if (repoWorker.findById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Trabajador con id " + id + " no encontrado");
        }
        repoWorker.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Trabajador eliminado exitosamente");
    }
}
