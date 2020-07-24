package ks.roleplaying.service;

import ks.roleplaying.controller.ResourceNotFoundException;
import ks.roleplaying.enums.MoedaEnum;
import ks.roleplaying.enums.TendenciaEnum;
import ks.roleplaying.model.*;
import ks.roleplaying.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class PersonagemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonagemService.class);

    @Autowired
    private PersonagemRepository personagemRepository;

    @Autowired
    private InventarioRepository inventarioRepository;

    @Autowired
    private AtributoService atributoService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private HabilidadeService habilidadeService;

    private static Random random = new Random();

    @Transactional
    public Personagem addNewPersonagem(Personagem request) {
        Personagem personagem = new Personagem(request);

        //TODO: REMOVER TESTE
        if(itemService.getAll().size() < 1) {
            itemService.addNewItemCarga("Poção de HP", BigDecimal.TEN, BigDecimal.TEN);
            itemService.addNewItemCarga("Poção de MP", BigDecimal.TEN.add(BigDecimal.ONE), BigDecimal.TEN.add(BigDecimal.ONE));
        }
        if(habilidadeService.getAll().size() < 1) {
            habilidadeService.addNewHabilidade(new Habilidade("Teste", "Instantaneo", "Um alvo",
                    "testar as coisas", "2 turnos", 2, "testar"));

            habilidadeService.addNewHabilidade(new Habilidade("Teste Supremo", "1 turno", "Em area",
                    "testar as coisas supremamente", "1 turnos", 5, "testar no modo hard"));
        }

        personagem.setInventarioItens(request.getInventarioItens());

        verificarHabilidades(personagem, request.getHabilidades());

        Atributos atributos = null;
        atributos = request.getAtributos();
//      GERAR ATRIBUTOS ALEATORIOS > DESCOMENTAR LINHA ABAIXO
//        atributos = atributosBasicos();

        personagem.getInventarioItens().forEach(this::adicionarInventarioItem);

        atributoService.save(atributos);

        personagem.setAtributos(atributos);

        return personagemRepository.save(personagem);
    }

    private void verificarHabilidades(Personagem personagem, List<Habilidade> habilidades) {
        personagem.setHabilidades(new ArrayList<>());
        Habilidade habilidadeBanco;
        for (Habilidade habilidade: habilidades) {
            habilidadeBanco = habilidadeService.getHabilidadeById(habilidade.getId());
            if(habilidadeBanco != null) {
                personagem.getHabilidades().add(habilidadeBanco);
            } else {
                throw new ResourceNotFoundException(habilidade.getNome());
            }
        }
    }

    private void adicionarInventarioItem(InventarioItem inventarioItem) {
        Item item = itemService.getItemById(inventarioItem.getItem().getId());
        if(item != null) {
            inventarioItem.setItem(item);
            inventarioRepository.save(inventarioItem);
        }
    }

    private Atributos atributosBasicos() {
        return new Atributos(diceRoll(), diceRoll(), diceRoll(), diceRoll(), diceRoll(), diceRoll());
    }

    public List<Personagem> getAll() {
        LOGGER.debug("Obtendo personagens");
        return this.personagemRepository.findAllPersonagem()
                .orElseThrow(() -> new ResourceNotFoundException("ALL PERSONAGEM"));
    }

    public Personagem getPersonagemById(Long personagemId) {
        // Resolução de parte do "problema" do LAZY considerando a entidade Inventario Item
//        return personagemRepository.findPersonagem(personagemId)
//                .orElseThrow(() -> new ResourceNotFoundException("PERSONAGEM", "id", personagemId));
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

    public static int diceRoll() {
        int number = random.nextInt(20);
        return number < 3 ? 3 : number;
    }

}