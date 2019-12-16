package ks.roleplaying.repository;

import ks.roleplaying.model.Habilidade;
import ks.roleplaying.model.Talento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TalentoRepository extends JpaRepository<Talento, Long> {


    @Query("select t from Talento t where t.nome = :nome")
    List<Talento> findByNome(@Param("nome") String nome);

}
