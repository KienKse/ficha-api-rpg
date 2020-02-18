package ks.roleplaying.service;

import ks.roleplaying.model.Atributos;
import ks.roleplaying.repository.AtributosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AtributoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AtributoService.class);
    private static final Integer NUMERO_BASE = 10;

    @Autowired
    private AtributosRepository atributosRepository;

    public void atualizarModificadores(Atributos atributos) {
        atributos.setModificadorForca(calcularModificador(atributos.getForca()));
        atributos.setModificadorCarisma(calcularModificador(atributos.getCarisma()));
        atributos.setModificadorConstituicao(calcularModificador(atributos.getConstituicao()));
        atributos.setModificadorDestreza(calcularModificador(atributos.getDestreza()));
        atributos.setModificadorInteligencia(calcularModificador(atributos.getInteligencia()));
        atributos.setModificadorSabedoria(calcularModificador(atributos.getSabedoria()));
    }

    public Integer calcularModificador(Integer atributo) {
        if(atributo < NUMERO_BASE) {
            return (int) Math.ceil((atributo-NUMERO_BASE)/2);
        }
        return (int) Math.floor((atributo-NUMERO_BASE)/2);
    }

    public Atributos save(Atributos atributos) {
        return atributosRepository.save(atributos);
    }

    public ResponseEntity<Object> updateAtributo(Atributos atributoDetails, Long atributoId) {
        Optional<Atributos> atributoOptional = atributosRepository.findById(atributoId);

        if(!atributoOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        atributoDetails.setId(atributoId);

        atualizarModificadores(atributoDetails);

        atributosRepository.save(atributoDetails);

        return ResponseEntity.noContent().build();
    }

}