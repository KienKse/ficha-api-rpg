package ks.roleplaying.repository;

import ks.roleplaying.model.InventarioItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository extends JpaRepository<InventarioItem, Long> {

}
