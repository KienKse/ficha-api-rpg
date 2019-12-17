package ks.roleplaying.service;

import ks.roleplaying.controller.ResourceNotFoundException;
import ks.roleplaying.model.Moeda;
import ks.roleplaying.repository.MoedaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class MoedaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MoedaService.class);

    @Autowired
    private MoedaRepository moedaRepository;

    public void adicionarMoeda(Moeda moeda, String tipoMoeda, int quantidade) {
        if(moeda != null) {
            Moeda moedaBase = getMoedaById(moeda.getId());
            if(moedaBase != null) {
                //TODO: CRIAR VALIDAÇÃO PARA ADICIONAR E REMOVER MOEDAS QUE ATUALIZE O ENUM DA MOEDA ATUAL
                moedaBase.setQuantidade(moedaBase.getQuantidade().add(BigDecimal.valueOf(quantidade)));
            }
        }
    }

    public Moeda getMoedaById(Long moedaId) {
        return moedaRepository.findById(moedaId)
                .orElseThrow(() -> new ResourceNotFoundException("MOEDA", "id", moedaId));
    }

    public void deleteMoeda(Long moedaId) {
        Moeda moeda = getMoedaById(moedaId);

        moedaRepository.delete(moeda);
    }

    public ResponseEntity<Object> updateMoeda(Moeda moedaDetails, Long moedaId) {
        Optional<Moeda> moedaOptional = moedaRepository.findById(moedaId);

        if(!moedaOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        moedaDetails.setId(moedaId);

        moedaRepository.save(moedaDetails);

        return ResponseEntity.noContent().build();
    }



}