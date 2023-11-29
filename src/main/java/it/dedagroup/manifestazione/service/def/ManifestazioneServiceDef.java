package it.dedagroup.manifestazione.service.def;


import it.dedagroup.manifestazione.DTO.Request.FiltroManifestazioneDTORequest;
import it.dedagroup.manifestazione.DTO.Request.ManifestazioneRequest;
import it.dedagroup.manifestazione.DTO.Request.ManifestazioneRequestConId;
import it.dedagroup.manifestazione.model.Manifestazione;

import java.util.List;
import java.util.Optional;

public interface ManifestazioneServiceDef {
    void addManifestazione(ManifestazioneRequestConId request);

    void updateManifestazioneById(ManifestazioneRequestConId request);

    void deleteManifestazioneById(long id);

    List<Manifestazione> findAll();

    List<Manifestazione> findAllByIsCancellatoFalse();

    Manifestazione findById(long id);

    Manifestazione findByIdAndIsCancellatoFalse(long id);

    Manifestazione findByNome(String nome);

    Manifestazione findByNomeAndIsCancellatoFalse(String nome);

    List<Manifestazione> filtraManifestazioni(FiltroManifestazioneDTORequest request);
}
