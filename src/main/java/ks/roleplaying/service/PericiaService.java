package ks.roleplaying.service;

import ks.roleplaying.controller.ResourceNotFoundException;
import ks.roleplaying.model.Pericia;
import ks.roleplaying.repository.PericiaRepository;
import ks.roleplaying.repository.PericiaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PericiaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PericiaService.class);

    @Autowired
    private PericiaRepository periciaRepository;

    public List<Pericia> getAll() {
        LOGGER.debug("Obtendo itens");
        return periciaRepository.findAll();
    }

    public Pericia addNewPericia(Pericia request) {
        Pericia pericia = new Pericia(request);

        return periciaRepository.save(pericia);
    }

    public Pericia addNewPericiaCarga(String nome, String habilidade_chave, String treinada, Integer cd, String descricao) {
        return periciaRepository.save(new Pericia(nome, habilidade_chave, treinada, cd, descricao));
    }

    public Pericia getPericiaById(Long periciaId) {
        return periciaRepository.findById(periciaId)
                .orElseThrow(() -> new ResourceNotFoundException("PERICIA", "id", periciaId));
    }

    public void deletePericia(Long periciaId) {
        Pericia pericia = getPericiaById(periciaId);

        periciaRepository.delete(pericia);
    }

    public ResponseEntity<Object> updatePericia(Pericia periciaDetails, Long periciaId) {
        Optional<Pericia> periciaOptional = periciaRepository.findById(periciaId);

        if(!periciaOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        periciaDetails.setId(periciaId);

        periciaRepository.save(periciaDetails);

        return ResponseEntity.noContent().build();
    }



}