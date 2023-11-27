package it.dedagroup.manifestazione.controller;


import it.dedagroup.manifestazione.DTO.Request.FiltroManifestazioneDTORequest;
import it.dedagroup.manifestazione.DTO.Request.ManifestazioneRequest;
import it.dedagroup.manifestazione.DTO.Request.ManifestazioneRequestConId;
import it.dedagroup.manifestazione.model.Manifestazione;
import it.dedagroup.manifestazione.service.impl.ManifestazioneServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@AllArgsConstructor
@RequestMapping("/manifestazione")
public class ManifestazioneController {

    private final ManifestazioneServiceImpl manifestazioneService;

    @PostMapping("/new")
    public ResponseEntity<String> addManifestazione(@RequestBody ManifestazioneRequest request) {
        manifestazioneService.addManifestazione(request);
        return ResponseEntity.ok().body("Manifestazione creata.");
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateManifestazione(@RequestBody ManifestazioneRequestConId request) {
        manifestazioneService.updateManifestazioneById(request);
        return ResponseEntity.ok().body("Manifestazione aggiornata.");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id) {
        manifestazioneService.deleteManifestazioneById(id);
        return ResponseEntity.ok().body("Manifestazione eliminata.");
    }

    @GetMapping("/find-all")
    public ResponseEntity<List<Manifestazione>> findAll() {
        List<Manifestazione> manifestazioni = manifestazioneService.findAll();
        return ResponseEntity.ok().body(manifestazioni);
    }

    @GetMapping("/find-all/cancellato=false")
    public ResponseEntity<List<Manifestazione>> findAllByIsCancellatoFalse() {
        List<Manifestazione> manifestazioni = manifestazioneService.findAllByIsCancellatoFalse();
        return ResponseEntity.ok().body(manifestazioni);
    }

    @GetMapping("/find-name/{nome}")
    public ResponseEntity<Optional<Manifestazione>> findByNome(@PathVariable String nome) {
        Optional<Manifestazione> optionalManifestazione = manifestazioneService.findByNome(nome);
        return ResponseEntity.ok().body(optionalManifestazione);
    }

    @GetMapping("/find-name/{nome}/cancellato=false")
    public ResponseEntity<Optional<Manifestazione>> findByNomeAndIsCancellatoFalse(@PathVariable String nome) {
        Optional<Manifestazione> optionalManifestazione = manifestazioneService.findByNomeAndIsCancellatoFalse(nome);
        return ResponseEntity.ok().body(optionalManifestazione);
    }

    @GetMapping("/find-id/{id}")
    public ResponseEntity<Optional<Manifestazione>> findById(@PathVariable long id) {
        Optional<Manifestazione> optionalManifestazione = manifestazioneService.findById(id);
        return ResponseEntity.ok().body(optionalManifestazione);
    }

    @GetMapping("/find-id/{id}/cancellato=false")
    public ResponseEntity<Optional<Manifestazione>> findByIdAndIsCancellatoFalse(@PathVariable long id) {
        Optional<Manifestazione> optionalManifestazione = manifestazioneService.findByIdAndIsCancellatoFalse(id);
        return ResponseEntity.ok().body(optionalManifestazione);
    }

    @GetMapping("/filtraManifestazioni")
    public ResponseEntity<List<Manifestazione>> filtraManifestazioni(@RequestBody FiltroManifestazioneDTORequest request) {
        return ResponseEntity.ok().body(manifestazioneService.filtraManifestazioni(request));
    }
}

