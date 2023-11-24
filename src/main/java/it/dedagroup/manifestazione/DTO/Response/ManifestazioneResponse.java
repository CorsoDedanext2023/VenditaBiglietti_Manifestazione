package it.dedagroup.manifestazione.DTO.Response;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private boolean isCancellato;
}
