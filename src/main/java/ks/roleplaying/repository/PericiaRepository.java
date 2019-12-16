package ks.roleplaying.repository;

import ks.roleplaying.model.Pericia;
import ks.roleplaying.model.Talento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PericiaRepository extends JpaRepository<Pericia, Long> {


    @Query("select p from Pericia p where p.nome = :nome")
    List<Pericia> findByNome(@Param("nome") String nome);

}
