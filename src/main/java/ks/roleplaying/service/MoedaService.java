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

    public void atualizarMoeda(Moeda moeda) {
        if(moeda != null) {
            moedaTibarPrata(moeda);
            moedaPrataOuro(moeda);
            moedaOuroPlatina(moeda);
        }
    }

    private void moedaTibarPrata(Moeda moeda) {
        if(BigDecimal.valueOf(moeda.getTibar()).divide(BigDecimal.TEN).compareTo(BigDecimal.ONE) == 1) {
            moeda.setTibarPrata(moeda.getTibarPrata()+moeda.getTibar()/10);
            moeda.setTibar(moeda.getTibar()%10);
        }
    }

    private void moedaPrataOuro(Moeda moeda) {
        if(BigDecimal.valueOf(moeda.getTibarPrata()).divide(BigDecimal.TEN).compareTo(BigDecimal.ONE) == 1) {
            moeda.setTibarOuro(moeda.getTibarOuro()+moeda.getTibarPrata()/10);
            moeda.setTibarPrata(moeda.getTibarPrata()%10);
        }
    }

    private void moedaOuroPlatina(Moeda moeda) {
        if(BigDecimal.valueOf(moeda.getTibarOuro()).divide(BigDecimal.TEN).compareTo(BigDecimal.ONE) == 1) {
            moeda.setTibarPlatina(moeda.getTibarPlatina()+moeda.getTibarOuro()/10);
            moeda.setTibarOuro(moeda.getTibarOuro()%10);
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

        atualizarMoeda(moedaDetails);

        moedaRepository.save(moedaDetails);

        return ResponseEntity.noContent().build();
    }

}