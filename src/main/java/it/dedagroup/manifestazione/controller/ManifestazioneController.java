package it.dedagroup.manifestazione.controller;


import it.dedagroup.manifestazione.DTO.Request.FiltroManifestazioneDTORequest;
import it.dedagroup.manifestazione.DTO.Request.ManifestazioneRequest;
import it.dedagroup.manifestazione.DTO.Request.ManifestazioneRequestConId;
import it.dedagroup.manifestazione.model.Manifestazione;
import it.dedagroup.manifestazione.service.impl.ManifestazioneServiceImpl;
import jakarta.validation.Valid;
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
    public ResponseEntity<String> addManifestazione( @Valid @RequestBody ManifestazioneRequestConId request) {
        manifestazioneService.addManifestazione(request);
        return ResponseEntity.ok().body("Manifestazione creata.");
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateManifestazione(@Valid @RequestBody ManifestazioneRequestConId request) {
        manifestazioneService.updateManifestazioneById(request);
        return ResponseEntity.ok().body("Manifestazione aggiornata.");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@Valid @PathVariable long id) {
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
    public ResponseEntity<Manifestazione> findByNome(@Valid @PathVariable String nome) {
        Manifestazione m = manifestazioneService.findByNome(nome);
        return ResponseEntity.ok().body(m);
    }

    @GetMapping("/find-name/{nome}/cancellato=false")
    public ResponseEntity<Manifestazione> findByNomeAndIsCancellatoFalse(@Valid @PathVariable String nome) {
        Manifestazione m = manifestazioneService.findByNomeAndIsCancellatoFalse(nome);
        return ResponseEntity.ok().body(m);
    }

    @GetMapping("/find-id/{id}")
    public ResponseEntity<Manifestazione> findById(@Valid @PathVariable long id) {
        Manifestazione m = manifestazioneService.findById(id);
        return ResponseEntity.ok().body(m);
    }

    @GetMapping("/find-id/{id}/cancellato=false")
    public ResponseEntity<Manifestazione> findByIdAndIsCancellatoFalse(@Valid @PathVariable long id) {
        Manifestazione m = manifestazioneService.findByIdAndIsCancellatoFalse(id);
        return ResponseEntity.ok().body(m);
    }

    @PostMapping("/filtraManifestazioni")
    public ResponseEntity<List<Manifestazione>> filtraManifestazioni(@Valid @RequestBody FiltroManifestazioneDTORequest request) {
        return ResponseEntity.ok().body(manifestazioneService.filtraManifestazioni(request));
    }
}

