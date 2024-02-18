package mx.edu.utez.sipre.model.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "worker")
public class BeanWorker {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", length = 100, nullable = false)
    private String name;
    @Column(name = "lastname", length = 100, nullable = false)
    private String lastname;
    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;
    @Column(name = "password", length = 100, nullable = false)
    private String password;
    @Column(name = "status", length = 1, nullable = false)
    private int status;
    @Column(name = "userWorker", length = 100, nullable = false, unique = true)
    private String userWorker;
    @Column(name = "saldo", length = 9, nullable = false)
    private double saldo;

    @ManyToOne
    @JoinColumn(name = "idDivision", nullable = false)
    private BeanDivision division;

    @OneToMany(mappedBy = "beanWorker")
    @JsonIgnore
    private List<BeanBuys> buys;


}
