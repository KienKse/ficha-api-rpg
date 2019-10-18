package ks.roleplaying.service;

import ks.roleplaying.model.*;
import ks.roleplaying.repository.AtributosRepository;
import ks.roleplaying.repository.InventarioRepository;
import ks.roleplaying.repository.PersonagemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ks.roleplaying.controller.ResourceNotFoundException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PersonagemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonagemService.class);

    @Autowired
    private PersonagemRepository personagemRepository;

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private AtributosRepository atributosRepository;

    @Autowired
    private HabilidadeService habilidadeService;

    @Autowired
    private ItemService itemService;

    public List<Personagem> getAll() {
        LOGGER.debug("Obtendo personagens");
        return personagemRepository.findAll();
    }

    public Personagem addNewPersonagem(Personagem request) {
        Personagem personagem = new Personagem(request);

//        Item padrao = itemService.getItemById(1L);
//        Item padrao2 = itemService.getItemById(2L);
        Item padrao = itemService.addNewItemCarga("Poção de HP", BigDecimal.ONE, BigDecimal.TEN);
        Item padrao2 = itemService.addNewItemCarga("Poção de MP", BigDecimal.valueOf(2), BigDecimal.TEN);

        InventarioItem inventarioItem = new InventarioItem(padrao, 2);
        InventarioItem inventarioItem2 = new InventarioItem(padrao2, 1);

        inventarioRepository.save(inventarioItem);
        inventarioRepository.save(inventarioItem2);

        personagem.getInventarioItens().add(inventarioItem);
        personagem.getInventarioItens().add(inventarioItem2);

        Atributos atributos = new Atributos(10, 10, 10, 10, 10, 10);

        atributosRepository.save(atributos);

        personagem.setAtributos(atributos);

        Habilidade furia = habilidadeService.addNewHabilidadeCarga("Furia", 10, "Dobra o dano do próximo ataque");

        personagem.getHabilidades().add(furia);

        return personagemRepository.save(personagem);
    }

    public Personagem getPersonagemById(Long personagemId) {
        return personagemRepository.findById(personagemId)
                .orElseThrow(() -> new ResourceNotFoundException("PERSONAGEM", "id", personagemId));
    }

    public void deletePersonagem(Long personagemId) {
        Personagem personagem = getPersonagemById(personagemId);

        personagemRepository.delete(personagem);
    }

    public ResponseEntity<Object> updatePersonagem(Personagem personagemDetails, Long personagemId) {
        Optional<Personagem> personagemOptional = personagemRepository.findById(personagemId);

        if(!personagemOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        personagemDetails.setId(personagemId);

        personagemRepository.save(personagemDetails);

        return ResponseEntity.noContent().build();
    }



}