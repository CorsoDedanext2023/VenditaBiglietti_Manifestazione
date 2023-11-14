package it.dedagroup.manifestazione.controller;

import it.dedagroup.manifestazione.DTO.Response.ManifestazioneResponse;
import it.dedagroup.manifestazione.model.Manifestazione;
import it.dedagroup.manifestazione.service.impl.ManifestazioneServiceImpl;
import lombok.AllArgsConstructor;
import static org.springframework.http.HttpStatus.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/manifestazione")
public class ManifestazioneController {

    private final ManifestazioneServiceImpl manifestazioneService;
    @GetMapping("/all")
    public ResponseEntity<List<ManifestazioneResponse>> findAll(){
        return ResponseEntity.status(FOUND).body(manifestazioneService.findAll());
    }
    @GetMapping("/{nome}")
    public ResponseEntity<List<Manifestazione>> findAllByNome(@PathVariable String nome){
        return ResponseEntity.status(FOUND).body(manifestazioneService.findAllByNome(nome));
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<Manifestazione>> findAllById(@PathVariable long id){
        return ResponseEntity.status(FOUND).body(manifestazioneService.findAllById(id));
    }
    @PostMapping("/add")
    public ResponseEntity<String> addManifestazione(@RequestBody Manifestazione manifestazione){
        manifestazioneService.addManifestazione(manifestazione);
        return ResponseEntity.status(CREATED).body("Manifestazione creata.");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id){
        manifestazioneService.deleteManifestazioneById(id);
        return ResponseEntity.status(OK).body("Manifestazione eliminata.");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateManifestazione(@PathVariable long id ){
        manifestazioneService.updateManifestazioneById(id);
        return ResponseEntity.status(OK).body("Manifestazione aggiornata.");
    }

}
