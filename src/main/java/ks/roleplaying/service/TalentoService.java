package ks.roleplaying.service;

import ks.roleplaying.controller.ResourceNotFoundException;
import ks.roleplaying.model.Talento;
import ks.roleplaying.repository.TalentoRepository;
import ks.roleplaying.repository.TalentoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TalentoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TalentoService.class);

    @Autowired
    private TalentoRepository talentoRepository;

    public List<Talento> getAll() {
        LOGGER.debug("Obtendo itens");
        return talentoRepository.findAll();
    }

    public Talento addNewTalento(Talento request) {
        Talento talento = new Talento(request);

        return talentoRepository.save(talento);
    }

    public Talento addNewTalentoCarga(String nome, String preRequisito, String beneficio, String observacao) {
        return talentoRepository.save(new Talento(nome, preRequisito, beneficio, observacao));
    }

    public Talento getTalentoById(Long talentoId) {
        return talentoRepository.findById(talentoId)
                .orElseThrow(() -> new ResourceNotFoundException("TALENTO", "id", talentoId));
    }

    public void deleteTalento(Long talentoId) {
        Talento talento = getTalentoById(talentoId);

        talentoRepository.delete(talento);
    }

    public ResponseEntity<Object> updateTalento(Talento talentoDetails, Long talentoId) {
        Optional<Talento> talentoOptional = talentoRepository.findById(talentoId);

        if(!talentoOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        talentoDetails.setId(talentoId);

        talentoRepository.save(talentoDetails);

        return ResponseEntity.noContent().build();
    }



}