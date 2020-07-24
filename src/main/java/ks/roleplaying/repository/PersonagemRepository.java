package ks.roleplaying.repository;

import ks.roleplaying.model.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonagemRepository extends JpaRepository<Personagem, Long> {

    @Query("SELECT p FROM Personagem p " +
            "JOIN FETCH p.inventarioItens i " +
            "WHERE i.id = :personagemId AND p.id=:personagemId")
    Optional<Personagem> findPersonagem(@Param("personagemId") Long personagemId);

    @Query("SELECT p FROM Personagem p " +
            "JOIN FETCH p.inventarioItens i " +
            "WHERE i.id = p.id")
    Optional<List<Personagem>> findAllPersonagem();

/*
    @Query("SELECT p FROM Personagem p " +
            "JOIN FETCH p.inventarioItens i ON p.id = i.idpersonagemfk " +
            "JOIN FETCH p.habilidades h ON p.id = h.idpersonagemfk " +
            "WHERE 1=1")
    List<Personagem> findAllPersonagem();
*/
}
