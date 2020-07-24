package ks.roleplaying.repository;

import ks.roleplaying.model.Habilidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabilidadeRepository extends JpaRepository<Habilidade, Long> {

    @Query("select h.id from Habilidade h where h.nome = :nome")
    List<Habilidade> findByNome(@Param("nome") String nome);

}
