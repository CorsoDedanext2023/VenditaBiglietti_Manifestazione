package it.dedagroup.manifestazione.service.def;


import it.dedagroup.manifestazione.DTO.Request.ManifestazioneRequest;
import it.dedagroup.manifestazione.DTO.Request.ManifestazioneRequestConId;

import it.dedagroup.manifestazione.DTO.Request.FiltroManifestazioneDTORequest;

import it.dedagroup.manifestazione.model.Manifestazione;

import java.util.List;
import java.util.Optional;

public interface ManifestazioneServiceDef {
    void addManifestazione(ManifestazioneRequest request);
    void updateManifestazioneById(ManifestazioneRequestConId request);
    void deleteManifestazioneById(long id);
  
    List<Manifestazione> findAll();
    List<Manifestazione> findAllByIsCancellatoFalse();
  
    Optional<Manifestazione> findById(long id);
    Optional<Manifestazione> findByIdAndIsCancellatoFalse(long id);
    Optional<Manifestazione> findByNome(String nome);
    Optional<Manifestazione> findByNomeAndIsCancellatoFalse(String nome);
    List<Manifestazione> filtraManifestazioni(FiltroManifestazioneDTORequest request);
}
