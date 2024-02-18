package mx.edu.utez.sipre.model.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DtoBuys {
    private int id;
    private String descripcion;
    private String fecha;
    private double monto;
    private int idWorker;
}
