package it.dedagroup.manifestazione.service.def;

import it.dedagroup.manifestazione.DTO.Request.FiltroManifestazioneDTORequest;
import it.dedagroup.manifestazione.model.Manifestazione;

import java.util.List;

public interface ManifestazioneServiceDef {
    void addManifestazione(String nome);
    void updateManifestazioneById(long id);
    void deleteManifestazioneById(long id);
    List<Manifestazione> findAll();
    List<Manifestazione> findAllByIsCancellatoFalse();
    List<Manifestazione> findAllById(long id);
    List<Manifestazione> findAllByIdAndIsCancellatoFalse(long id);
    List<Manifestazione> findAllByNome(String nome);
    List<Manifestazione> findAllByNomeAndIsCancellatoFalse(String nome);
    List<Manifestazione> filtraManifestazioni(FiltroManifestazioneDTORequest request);


}
