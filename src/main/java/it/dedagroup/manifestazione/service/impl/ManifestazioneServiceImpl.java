package it.dedagroup.manifestazione.service.impl;


import it.dedagroup.manifestazione.DTO.Request.FiltroManifestazioneDTORequest;
import it.dedagroup.manifestazione.DTO.Request.ManifestazioneRequestConId;
import it.dedagroup.manifestazione.mapper.ManifestazioneMapper;
import it.dedagroup.manifestazione.model.Manifestazione;
import it.dedagroup.manifestazione.repository.CriteriaManifestazioneRepository;
import it.dedagroup.manifestazione.repository.ManifestazioneRepository;
import it.dedagroup.manifestazione.service.def.ManifestazioneServiceDef;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ManifestazioneServiceImpl implements ManifestazioneServiceDef {

    private final ManifestazioneRepository repository;

    private final ManifestazioneMapper mapper;

    private final CriteriaManifestazioneRepository criteriaManifestazioneRepository;

    /**
     * Crea una nuova Manifestazione.
     *
     * @param request DTO con i campi da inserire.
     */

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void addManifestazione(ManifestazioneRequestConId request) {
        try{
            Manifestazione newManifestazione = mapper.fromRequestConId(request);
        repository.save(newManifestazione);
        }catch (ObjectOptimisticLockingFailureException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Questo oggetto è stato modificato");
        }
    }

    /**
     * Aggiorna i dati di una Manifestazione esistente nel database con l'ID fornito.
     *
     * @param request DTO contenente l'id della Manifestazione e i nuovi valori da utilizzare per l'aggiornamento.
     * @throws ResponseStatusException Se la Manifestazione con l'ID specificato non viene trovata nel database.
     *                                 Viene sollevata con uno stato HttpStatus.NOT_FOUND.
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public void updateManifestazioneById(ManifestazioneRequestConId request) {
        try {
            Manifestazione existingManifestazione = repository.findById(request.getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Manifestazione non trovata per l'ID: " + request.getId()));

            Manifestazione updatedManifestazione = mapper.fromRequestConId(request);
            updatedManifestazione.setId(existingManifestazione.getId());
            updatedManifestazione.setVersion(existingManifestazione.getVersion());

            repository.save(updatedManifestazione);
        } catch (ObjectOptimisticLockingFailureException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Questo oggetto è stato modificato");
        }
    }

    /**
     * Cancella (solo visivamente) una manifestazione ricercandola per ID.
     *
     * @param id Richiede l'inserimento dell'ID della manifestazione;
     * @implSpec Questo metodo inoltre setterà la variabile "cancellato" al valore true.
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public void deleteManifestazioneById(long id) {
        try {
            Manifestazione manifestazione = repository.findById(id).orElseThrow();
            manifestazione.setCancellato(true);
            repository.save(manifestazione);
        } catch (ObjectOptimisticLockingFailureException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Questo oggetto è stato modificato");
        }
    }

    /**
     * Stampa a schermo tutte le manifestazioni che esistono nel database.
     *
     * @return Ritorna una lista di tipo Manifestazione.
     * @throws ResponseStatusException Se la lista Manifestazioni è vuota.
     */
    @Override
    public List<Manifestazione> findAll() {
        List<Manifestazione> manifestazioni = repository.findAll();
        if (manifestazioni.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La lista è vuota.");
        }
        return manifestazioni;
    }

    /**
     * Stampa a schermo tutte le manifestazioni trovate nel database.
     *
     * @return Ritorna una lista di tipo Manifestazione.
     * @throws ResponseStatusException Se la Manifestazione non viene trovata nel database.
     */
    @Override
    public List<Manifestazione> findAllByIsCancellatoFalse() {
        List<Manifestazione> manifestazioni = repository.findAllByIsCancellatoFalse();
        if (manifestazioni.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La lista è vuota.");
        }
        return manifestazioni;
    }

    /**
     * Restituisce un oggetto Manifestazione cercato per ID.
     *
     * @param id L'ID della Manifestazione da cercare nel database.
     * @return Ritorna l'oggetto Manifestazione corrispondente all'ID fornito.
     * @throws ResponseStatusException Se la Manifestazione non viene trovata nel database.
     */
    @Override
    public Optional<Manifestazione> findById(long id) {
        Optional<Manifestazione> optionalManifestazione = repository.findById(id);
        if (optionalManifestazione.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Manifestazione non trovata per l'ID: " + id);
        }
        return optionalManifestazione;
    }

    /**
     * Stampa i record del database che presentano l'ID richiesto.
     *
     * @param id Richiede id da trovare nel database.
     * @return Ritorna una lista di tipo Manifestazione.
     * @throws ResponseStatusException Se la Manifestazione non viene trovata nel database.
     */
    @Override
    public Optional<Manifestazione> findByIdAndIsCancellatoFalse(long id) {
        Optional<Manifestazione> optionalManifestazione = repository.findByIdAndIsCancellatoFalse(id);
        if (optionalManifestazione.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Manifestazione non trovata per l'ID: " + id);
        }
        return optionalManifestazione;
    }

    /**
     * Esegue una ricerca per nome.
     *
     * @param nome Richiede il nome della Manifestazione da ricercare nel database.
     * @return Ritorna una lista di tipo Manifestazione.
     * @throws ResponseStatusException Se la Manifestazione non viene trovata nel database.
     */
    @Override
    public Optional<Manifestazione> findByNome(String nome) {
        Optional<Manifestazione> optionalManifestazione = repository.findByNome(nome);
        if (optionalManifestazione.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Manifestazione non trovata per il NOME: " + nome);
        }
        return optionalManifestazione;
    }

    /**
     * Esegue una ricerca per nome.
     *
     * @param nome Richiede il nome della Manifestazione da ricercare nel database.
     * @return Ritorna una lista di tipo Manifestazione.
     * @throws ResponseStatusException Se la Manifestazione non viene trovata nel database.
     */
    @Override
    public Optional<Manifestazione> findByNomeAndIsCancellatoFalse(String nome) {
        Optional<Manifestazione> optionalManifestazione = repository.findByNomeAndIsCancellatoFalse(nome);
        if (optionalManifestazione.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Manifestazione non trovata per il NOME: " + nome);
        }
        return optionalManifestazione;
    }

    /**
     * Questo metodo esegue una ricerca filtrando i risultati.
     *
     * @param request DTO in richiesta, per il nome.
     * @return una lista di tipo Manifestazione
     */
    @Override
    public List<Manifestazione> filtraManifestazioni(FiltroManifestazioneDTORequest request) {
        return criteriaManifestazioneRepository.filtraManifestazioni(request);
    }
}
