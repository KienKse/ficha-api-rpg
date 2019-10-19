package ks.roleplaying.repository;

import ks.roleplaying.model.Tendencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TendenciaRepository extends JpaRepository<Tendencia, Long> {

    @Query("select t.titulo from Tendencia t where t.tendencia = :sigla")
    List<Tendencia> findBySigla(@Param("sigla") String sigla);

}
