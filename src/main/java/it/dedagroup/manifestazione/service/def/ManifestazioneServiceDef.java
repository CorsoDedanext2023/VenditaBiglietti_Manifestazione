package it.dedagroup.manifestazione.service.def;

import it.dedagroup.manifestazione.DTO.Response.ManifestazioneResponse;
import it.dedagroup.manifestazione.model.Manifestazione;

import java.util.List;
import java.util.Optional;

public interface ManifestazioneServiceDef {
    void addManifestazione(Manifestazione manifestazione);
    void updateManifestazioneById(long id);
    void deleteManifestazioneById(long id);
    List<ManifestazioneResponse> findAll();
    List<Manifestazione> findAllByIsCancellatoFalse();
    List<Manifestazione> findAllById(long id);
    List<Manifestazione> findAllByIdAndIsCancellatoFalse(long id);
    List<Manifestazione> findAllByNome(String nome);
    List<Manifestazione> findAllByNomeAndIsCancellatoFalse(String nome);


}
