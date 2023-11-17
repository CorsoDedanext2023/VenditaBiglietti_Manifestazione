package it.dedagroup.manifestazione.controller;

import it.dedagroup.manifestazione.DTO.Request.ManifestazioneRequest;
import it.dedagroup.manifestazione.mapper.ManifestazioneMapper;
import it.dedagroup.manifestazione.model.Manifestazione;
import it.dedagroup.manifestazione.service.impl.ManifestazioneServiceImpl;
import lombok.AllArgsConstructor;
import static org.springframework.http.HttpStatus.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Classe controller per la gestione delle manifestazioni.
 * Questo controller fornisce endpoint per eseguire operazioni CRUD sulle manifestazioni.
 * Interagisce con il servizio {@link ManifestazioneServiceImpl} per gestire la logica di business.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/manifestazione")
public class ManifestazioneController {

    private final ManifestazioneServiceImpl manifestazioneService;
    private final ManifestazioneMapper mapper;

    /**
     * Aggiunge una nuova manifestazione.
     *
     * @param nome Il nome della manifestazione da creare
     * @return ResponseEntity con un messaggio che indica il successo dell'operazione
     */
    @GetMapping("/new/{nome}")
    public ResponseEntity<String> addManifestazione(@PathVariable String nome) {
        manifestazioneService.addManifestazione(nome);
        return ResponseEntity.status(OK).body("Manifestazione creata.");
    }

    /**
     * Aggiorna una manifestazione per ID.
     *
     * @param id L'ID della manifestazione da aggiornare
     * @return ResponseEntity con un messaggio che indica il successo dell'operazione
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateManifestazione(@PathVariable long id) {
        manifestazioneService.updateManifestazioneById(id);
        return ResponseEntity.status(OK).body("Manifestazione aggiornata.");
    }

    /**
     * Elimina una manifestazione per ID.
     *
     * @param id L'ID della manifestazione da eliminare
     * @return ResponseEntity con un messaggio che indica il successo dell'operazione
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id) {
        manifestazioneService.deleteManifestazioneById(id);
        return ResponseEntity.status(OK).body("Manifestazione eliminata.");
    }

    /**
     * Recupera tutte le manifestazioni.
     *
     * @return ResponseEntity contenente una lista di manifestazioni
     */
    @GetMapping("/all")
    public ResponseEntity<List<Manifestazione>> findAll() {
        List<Manifestazione> manifestazioni = manifestazioneService.findAll();
        return ResponseEntity.status(FOUND).body(manifestazioni);
    }

    /**
     * Recupera le manifestazioni per nome.
     *
     * @param nome Il nome da cercare
     * @return ResponseEntity contenente una lista di manifestazioni con il nome specificato
     */
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Manifestazione>> findAllByNome(@PathVariable String nome) {
        List<Manifestazione> manifestazioni = manifestazioneService.findAllByNome(nome);
        return ResponseEntity.status(FOUND).body(manifestazioni);
    }

    /**
     * Recupera le manifestazioni per ID.
     *
     * @param id L'ID da cercare
     * @return ResponseEntity contenente una lista di manifestazioni con l'ID specificato
     */
    @GetMapping("/id/{id}")
    public ResponseEntity<List<Manifestazione>> findAllById(@PathVariable long id) {
        List<Manifestazione> manifestazioni = manifestazioneService.findAllById(id);
        return ResponseEntity.status(FOUND).body(manifestazioni);
    }
}

