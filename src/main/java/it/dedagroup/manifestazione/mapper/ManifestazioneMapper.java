package it.dedagroup.manifestazione.mapper;

import it.dedagroup.manifestazione.DTO.Request.ManifestazioneRequest;
import it.dedagroup.manifestazione.DTO.Request.ManifestazioneRequestConId;
import it.dedagroup.manifestazione.DTO.Response.ManifestazioneResponse;
import it.dedagroup.manifestazione.model.Manifestazione;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ManifestazioneMapper {
    public ManifestazioneResponse toResponse(Manifestazione manifestazione) {
        ManifestazioneResponse DTO = new ManifestazioneResponse();
        DTO.setId(manifestazione.getId());
        DTO.setNome(manifestazione.getNome());
        DTO.setIdUtente(manifestazione.getIdUtente());
        DTO.setIdCategoria(manifestazione.getIdCategoria());
        DTO.setCancellato(manifestazione.isCancellato());
        return DTO;
    }

    public List<ManifestazioneResponse> toListDTO(List<Manifestazione> manifestazioni) {
        return manifestazioni.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public Manifestazione fromRequest(ManifestazioneRequest request) {
        Manifestazione manifestazione = new Manifestazione();
        manifestazione.setNome(request.getNome());
        manifestazione.setIdCategoria(request.getIdCategoria());
        return manifestazione;
    }

    public Manifestazione fromRequestConId(ManifestazioneRequestConId request) {
        Manifestazione manifestazione = new Manifestazione();
        manifestazione.setNome(request.getNome());
        manifestazione.setIdUtente(request.getIdUtente());
        manifestazione.setIdCategoria(request.getIdCategoria());
        return manifestazione;
    }
}