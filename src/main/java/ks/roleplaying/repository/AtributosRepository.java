package ks.roleplaying.repository;

import ks.roleplaying.model.Atributos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtributosRepository extends JpaRepository<Atributos, Long> {

}
