package mx.edu.utez.sipre.controller;

import lombok.RequiredArgsConstructor;
import mx.edu.utez.sipre.model.bean.BeanDivision;
import mx.edu.utez.sipre.model.bean.BeanWorker;
import mx.edu.utez.sipre.model.dto.DtoWorker;
import mx.edu.utez.sipre.model.repositories.RepoWorker;
import mx.edu.utez.sipre.service.ServiceWorker;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apisiprev1/worker")
@CrossOrigin(origins = {"*"})
@RequiredArgsConstructor
public class ControllerWorker {
    private final ServiceWorker serviceWorker;
    private final RepoWorker repoWorker;

    @GetMapping("/")
    public List<BeanWorker> getAllWorkers(){
        return serviceWorker.getAllWorkers();
    }

    @GetMapping("/{id}")
    public BeanWorker getWorkerById(@PathVariable("id") Long id){
        return serviceWorker.getWorkerById(id);
    }

    @PostMapping("/")
    public DtoWorker save(@RequestBody DtoWorker dtoWorker) {
        if (repoWorker.findByEmail(dtoWorker.getEmail()).isPresent()) {
            throw new RuntimeException("Ya existe un trabajador con el mismo email: " + dtoWorker.getEmail());
        }

        if (repoWorker.findByUserWorker(dtoWorker.getUserWorker()).isPresent()) {
            throw new RuntimeException("Ya existe un trabajador con el mismo username: " + dtoWorker.getUserWorker());
        }

        BeanWorker workerSave = serviceWorker.save(dtoWorker);

        return DtoWorker.builder()
                .name(workerSave.getName())
                .lastname(workerSave.getLastname())
                .email(workerSave.getEmail())
                .password(workerSave.getPassword())
                .status(workerSave.getStatus())
                .userWorker(workerSave.getUserWorker())
                .saldo(workerSave.getSaldo())
                .idDivision(Math.toIntExact(workerSave.getDivision().getId()))
                .build();
    }


    @PutMapping("/")
    public DtoWorker update(@RequestBody DtoWorker dtoWorker){
        BeanWorker workerUpdate = serviceWorker.save(dtoWorker);
        return DtoWorker.builder()
                .name(workerUpdate.getName())
                .lastname(workerUpdate.getLastname())
                .email(workerUpdate.getEmail())
                .password(workerUpdate.getPassword())
                .status(workerUpdate.getStatus())
                .userWorker(workerUpdate.getUserWorker())
                .saldo(workerUpdate.getSaldo())
                .idDivision(Math.toIntExact(workerUpdate.getDivision().getId()))
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        return serviceWorker.deleteWorker(id);
    }

}
