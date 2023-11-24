package it.dedagroup.manifestazione.DTO.Request;

import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManifestazioneRequestConId {
    private Long id;
    private String nome;
    private Long idCategoria;

    @Version
    private Long version;
}
