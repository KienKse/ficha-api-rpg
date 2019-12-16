package ks.roleplaying.service;

import ks.roleplaying.controller.ResourceNotFoundException;
import ks.roleplaying.enums.TendenciaEnum;
import ks.roleplaying.model.*;
import ks.roleplaying.repository.AtributosRepository;
import ks.roleplaying.repository.InventarioRepository;
import ks.roleplaying.repository.PersonagemRepository;
import ks.roleplaying.repository.TendenciaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    private AtributosRepository atributosRepository;

    @Autowired
    private TendenciaRepository tendenciaRepository;

    @Autowired
    private HabilidadeService habilidadeService;

    @Autowired
    private TalentoService talentoService;

    @Autowired
    private PericiaService periciaService;

    @Autowired
    private ItemService itemService;

    private static Random random = new Random();

    public List<Personagem> getAll() {
        LOGGER.debug("Obtendo personagens");
        return personagemRepository.findAll();
    }

    @Transactional
    public Personagem addNewPersonagem(Personagem request) {
        Personagem personagem = new Personagem(request);

        Tendencia tendencia = personagem.isGerarAtributosETendencia() ? gerarTendencia() : obterTendenciaFront(request);
        tendenciaRepository.save(tendencia);

        personagem.setTendencia(tendencia);

        if(personagem.isGerarAtributosETendencia()) {
            Item padrao = itemService.addNewItemCarga("Poção de HP", BigDecimal.TEN, BigDecimal.TEN);
            Item padrao2 = itemService.addNewItemCarga("Poção de MP", BigDecimal.valueOf(4), BigDecimal.TEN);

            InventarioItem inventarioItem = new InventarioItem(padrao, 2);
            InventarioItem inventarioItem2 = new InventarioItem(padrao2, 1);

            inventarioRepository.save(inventarioItem);
            inventarioRepository.save(inventarioItem2);

            personagem.getInventarioItens().add(inventarioItem);
            personagem.getInventarioItens().add(inventarioItem2);

            Habilidade habilidade = new Habilidade("Abençoar Água", "Divina 1 (cura)","Esta magia imbui um frasco d’água com energia positiva, transformando-a em água benta (veja o Capítulo 7: Equipamento). Componente material: 2,5kg de prata em pó (no valor de 25 TO).");

            personagem.getHabilidades().add(habilidade);

            Talento t1 = new Talento("Canto Monástico", "treinado em Atuação (música), capacidade de lançar magias divinas.",
                    "quando você lança uma magia, pode gastar uma ação de movimento para entoar um canto litúrgico. Se fizer isso, a CD para resistir à magia aumenta em +1. Você pode usar este talento um número de vezes por dia igual ao seu bônus de Carisma +1. Obviamente, você não pode lançar magias desta forma se não puder fazer sons (por exemplo, sob efeito de Magia Silenciosa).",
                    "[Manual do Devoto pg. 44] Você foi treinado em um mosteiro, onde rezava através de belos cânticos.");

//            talentoService.addNewTalento(t1);

            personagem.getTalentos().add(t1);

            Pericia p1 = new Pericia("Domesticar um animal selvagem", "Adestrar Animais",
                    "Carisma", 25, "Você pode criar um animal selvagem desde filhote, " +
                    "domesticando-o. O tempo necessário varia de acordo com a criatura.");

//            periciaService.addNewPericia(p1);

            personagem.getPericias().add(p1);

        } else {
            personagem.getInventarioItens().forEach(this::adicionarInventarioItem);
        }

        Atributos atributos = personagem.isGerarAtributosETendencia() ? atributosBasicos() : obterAtributosFront(request);

        atributosRepository.save(atributos);

        personagem.setAtributos(atributos);

        return personagemRepository.save(personagem);
    }

    private void adicionarInventarioItem(InventarioItem inventarioItem) {
        Item item = itemService.getItemById(inventarioItem.getItem().getId());
        if(item != null) {
            inventarioRepository.save(inventarioItem);
        }
    }

    private Tendencia obterTendenciaFront(Personagem personagem) {
        return personagem.getTendencia();
    }

    private Tendencia gerarTendencia() {
        int number = random.nextInt(TendenciaEnum.values().length);
        return new Tendencia(TendenciaEnum.getByCodigo(number == 0 ? 1 : number));
    }

    private Atributos obterAtributosFront(Personagem personagem) {
        return personagem.getAtributos();
    }

    private Atributos atributosBasicos() {
        return new Atributos(diceRoll(), diceRoll(), diceRoll(), diceRoll(), diceRoll(), diceRoll());
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

    public static int diceRoll() {
        int number = random.nextInt(20);
        return number < 3 ? 3 : number;
    }

}