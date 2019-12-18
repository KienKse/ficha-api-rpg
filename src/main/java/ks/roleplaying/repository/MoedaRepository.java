package ks.roleplaying.repository;

import ks.roleplaying.model.Moeda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoedaRepository extends JpaRepository<Moeda, Long> {
}
