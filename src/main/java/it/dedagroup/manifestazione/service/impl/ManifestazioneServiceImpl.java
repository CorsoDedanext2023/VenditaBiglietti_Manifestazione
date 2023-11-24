package it.dedagroup.manifestazione.service.impl;

import it.dedagroup.manifestazione.DTO.Request.FiltroManifestazioneDTORequest;
import it.dedagroup.manifestazione.mapper.ManifestazioneMapper;
import it.dedagroup.manifestazione.model.Manifestazione;
import it.dedagroup.manifestazione.repository.CriteriaManifestazioneRepository;
import it.dedagroup.manifestazione.repository.ManifestazioneRepository;
import it.dedagroup.manifestazione.service.def.ManifestazioneServiceDef;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class ManifestazioneServiceImpl implements ManifestazioneServiceDef {

    private final ManifestazioneRepository repository;

    private final ManifestazioneMapper mapper;

    private final CriteriaManifestazioneRepository criteriaManifestazioneRepository;
    @Override
    @Transactional(rollbackOn = Exception.class)
    public void addManifestazione(String nome) {
        try{
            Manifestazione newManifestazione = new Manifestazione();
            newManifestazione.setNome(nome);
            repository.save(newManifestazione);
        }catch (OptimisticLockingFailureException e){
            throw new OptimisticLockingFailureException("Questo oggetto è stato modificato");
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void updateManifestazioneById(long id) {
        try {
            Manifestazione manifestazione = repository.findById(id).orElseThrow();
            repository.save(manifestazione);
        }catch (OptimisticLockingFailureException e){
            throw new OptimisticLockingFailureException("Questo oggetto è stato modificato");
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void deleteManifestazioneById(long id) {
        try {
            Manifestazione manifestazione = repository.findById(id).orElseThrow();
            manifestazione.setCancellato(true);
            repository.save(manifestazione);
        }catch (OptimisticLockingFailureException e){
            throw new OptimisticLockingFailureException("Questo oggetto è stato modificato");
        }
    }

    @Override
    public List<Manifestazione> findAll() {
        List<Manifestazione> manifestazioni = repository.findAll();
        if (manifestazioni.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return manifestazioni;
    }

    @Override
    public List<Manifestazione> findAllByIsCancellatoFalse() {
        List<Manifestazione> manifestazioni = repository.findAllByIsCancellatoFalse();
        if (manifestazioni.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return manifestazioni;
    }

    @Override
    public List<Manifestazione> findAllById(long id) {
        List<Manifestazione> manifestazioni = repository.findAllById(id);
        if (manifestazioni.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return manifestazioni;
    }

    @Override
    public List<Manifestazione> findAllByIdAndIsCancellatoFalse(long id) {
        List<Manifestazione> manifestazioni = repository.findAllByIdAndIsCancellatoFalse(id);
        if (manifestazioni.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return manifestazioni;
    }

    @Override
    public List<Manifestazione> findAllByNome(String nome) {
        List<Manifestazione> manifestazioni = repository.findAllByNome(nome);
        if (manifestazioni.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return manifestazioni;
    }

    @Override
    public List<Manifestazione> findAllByNomeAndIsCancellatoFalse(String nome) {
        List<Manifestazione> manifestazioni = repository.findAllByNomeAndIsCancellatoFalse(nome);
        if (manifestazioni.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return manifestazioni;
    }

    @Override
    public List<Manifestazione> filtraManifestazioni(FiltroManifestazioneDTORequest request) {
        return criteriaManifestazioneRepository.filtraManifestazioni(request);
    }
}
