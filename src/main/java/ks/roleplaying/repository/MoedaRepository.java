package ks.roleplaying.repository;

import ks.roleplaying.enums.MoedaEnum;
import ks.roleplaying.model.Moeda;
import ks.roleplaying.model.Tendencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoedaRepository extends JpaRepository<Moeda, Long> {

    @Query("select m from Moeda m where m.moeda = :moeda")
    List<Tendencia> findByMoeda(@Param("moeda") MoedaEnum moeda);

}
