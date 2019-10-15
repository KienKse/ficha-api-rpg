package ks.roleplaying.repository;

import ks.roleplaying.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("select id from Item i where i.nome = :nome")
    List<Item> findByNome(@Param("nome") String nome);

}
