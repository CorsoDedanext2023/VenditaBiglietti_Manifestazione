package it.dedagroup.manifestazione.service.impl;

import it.dedagroup.manifestazione.DTO.Response.ManifestazioneResponse;
import it.dedagroup.manifestazione.mapper.ManifestazioneMapper;
import it.dedagroup.manifestazione.model.Manifestazione;
import it.dedagroup.manifestazione.repository.ManifestazioneRepository;
import it.dedagroup.manifestazione.service.def.ManifestazioneServiceDef;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ManifestazioneServiceImpl implements ManifestazioneServiceDef {

    private final ManifestazioneRepository repository;

    private final ManifestazioneMapper mapper;
    @Override
    public void addManifestazione(Manifestazione manifestazione) {
        repository.save(manifestazione);
    }

    @Override
    public void updateManifestazioneById(long id) {
    Manifestazione manifestazione = repository.findById(id).orElseThrow();
        repository.save(manifestazione);
    }

    @Override
    public void deleteManifestazioneById(long id) {
        Manifestazione manifestazione = repository.findById(id).orElseThrow();
        manifestazione.setCancellato(true);
        repository.save(manifestazione);
    }

    @Override
    public List<Manifestazione> findAll() {
        List<Manifestazione> manifestazioni = repository.findAll();
        return manifestazioni;
    }

    @Override
    public List<Manifestazione> findAllByIsCancellatoFalse() {
        return repository.findAllByIsCancellatoFalse();
    }

    @Override
    public List<Manifestazione> findAllById(long id) {
        return repository.findAllById(id);
    }

    @Override
    public List<Manifestazione> findAllByIdAndIsCancellatoFalse(long id) {
        return repository.findAllByIdAndIsCancellatoFalse(id);
    }

    @Override
    public List<Manifestazione> findAllByNome(String nome) {
        return repository.findAllByNome(nome);
    }

    @Override
    public List<Manifestazione> findAllByNomeAndIsCancellatoFalse(String nome) {
        return repository.findAllByNomeAndIsCancellatoFalse(nome);
    }
}
