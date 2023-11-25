package it.dedagroup.manifestazione.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManifestazioneResponse {
    private Long id;
    private String nome;
    private Long idCategoria;
    private long idUtente;
    private boolean isCancellato;
}
