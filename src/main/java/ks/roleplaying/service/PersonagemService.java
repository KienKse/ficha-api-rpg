package ks.roleplaying.service;

import ks.roleplaying.model.Atributos;
import ks.roleplaying.model.Inventario;
import ks.roleplaying.model.Item;
import ks.roleplaying.model.Personagem;
import ks.roleplaying.repository.AtributosRepository;
import ks.roleplaying.repository.HabilidadeRepository;
import ks.roleplaying.repository.InventarioRepository;
import org.springframework.transaction.annotation.Transactional;
import ks.roleplaying.repository.PersonagemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ks.roleplaying.controller.ResourceNotFoundException;

import java.math.BigDecimal;
import java.sql.SQLIntegrityConstraintViolationException;
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

    public List<Personagem> getAll() {
        LOGGER.debug("Obtendo personagens");
        return personagemRepository.findAll();
    }

    public Personagem addNewPersonagem(Personagem request) {
        Personagem personagem = new Personagem(request);

        Inventario inventario = new Inventario();
        inventarioRepository.save(inventario);

        //TODO: ADICIONAR ITEM TESTE
        personagem.setInventario(inventario);
//        String nome, BigDecimal peso, BigDecimal preco, Integer quantidade
        Item gold = new Item("dinheiro", BigDecimal.ONE, BigDecimal.ONE, 100);


        //TODO: DEFINIR ATRIBUTOS
        Atributos atributos = new Atributos(10, 10, 10, 10, 10, 10);

        atributosRepository.save(atributos);

        personagem.setAtributos(atributos);

        //TODO: VERIFICAR INSERT EM HABILIDADE - ManyToMany Chave em relação a habilidades existentes ?? CONTINUAR ASSIM??
        //TODO: VERIFICAR INSERT EM ITEM NO INVENTARIO - ManyToMany Chave em relação a habilidades existentes


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

    // TODO: UPDATE
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