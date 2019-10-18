package ks.roleplaying.service;

import ks.roleplaying.controller.ResourceNotFoundException;
import ks.roleplaying.model.Habilidade;
import ks.roleplaying.repository.HabilidadeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabilidadeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HabilidadeService.class);

    @Autowired
    private HabilidadeRepository habilidadeRepository;

    public List<Habilidade> getAll() {
        LOGGER.debug("Obtendo itens");
        return habilidadeRepository.findAll();
    }

    public Habilidade addNewHabilidade(Habilidade request) {
        Habilidade habilidade = new Habilidade(request);

        return habilidadeRepository.save(habilidade);
    }

    public Habilidade addNewHabilidadeCarga(String nome, Integer custo, String descricao) {
        return habilidadeRepository.save(new Habilidade(nome, custo, descricao));
    }

    public Habilidade getHabilidadeById(Long habilidadeId) {
        return habilidadeRepository.findById(habilidadeId)
                .orElseThrow(() -> new ResourceNotFoundException("HABILIDADE", "id", habilidadeId));
    }

    public void deleteHabilidade(Long habilidadeId) {
        Habilidade habilidade = getHabilidadeById(habilidadeId);

        habilidadeRepository.delete(habilidade);
    }

    public ResponseEntity<Object> updateHabilidade(Habilidade habilidadeDetails, Long habilidadeId) {
        Optional<Habilidade> habilidadeOptional = habilidadeRepository.findById(habilidadeId);

        if(!habilidadeOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        habilidadeDetails.setId(habilidadeId);

        habilidadeRepository.save(habilidadeDetails);

        return ResponseEntity.noContent().build();
    }



}