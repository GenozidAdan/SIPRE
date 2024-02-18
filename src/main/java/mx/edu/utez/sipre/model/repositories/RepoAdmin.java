package mx.edu.utez.sipre.model.repositories;

import mx.edu.utez.sipre.model.bean.BeanAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepoAdmin extends JpaRepository<BeanAdmin, Long> {
    Optional<Object> findByUserAdmin(String userAdmin);

    Optional<Object> findByEmail(String email);

    //Optional<Object> findByName(String );
}
