package mx.edu.utez.sipre.controller;

import lombok.RequiredArgsConstructor;
import mx.edu.utez.sipre.model.bean.BeanBuys;
import mx.edu.utez.sipre.model.dto.DtoBuys;
import mx.edu.utez.sipre.service.ServiceBuys;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apisiprev1/buys")
@CrossOrigin(origins = {"*"})
@RequiredArgsConstructor
public class ControllerBuys {
    private final ServiceBuys serviceBuys;

    @GetMapping("/")
    public List<BeanBuys> getAllBuys(){
        return serviceBuys.getAllBuys();
    }

    @GetMapping("/{id}")
    public BeanBuys getBuysById(@PathVariable("id") Long id){
        return serviceBuys.getBuysById(id);
    }

    @PostMapping("/")
    public DtoBuys save(@RequestBody DtoBuys dtoBuys) {
        BeanBuys buysSave = serviceBuys.save(dtoBuys);
        return DtoBuys.builder()
                .descripcion(buysSave.getDescripcion())
                .fecha(buysSave.getFecha())
                .monto(buysSave.getMonto())
                .idWorker(Math.toIntExact(buysSave.getId()))
                .build();
    }

    @PutMapping("/")
    public DtoBuys update(@RequestBody DtoBuys dtoBuys){
        BeanBuys buysUpdate = serviceBuys.save(dtoBuys);
        return DtoBuys.builder()
                .descripcion(buysUpdate.getDescripcion())
                .fecha(buysUpdate.getFecha())
                .monto(buysUpdate.getMonto())
                .idWorker(Math.toIntExact(buysUpdate.getId()))
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        serviceBuys.delete(id);
        return ResponseEntity.ok("Compra con id " + id + " eliminada");
    }

}
