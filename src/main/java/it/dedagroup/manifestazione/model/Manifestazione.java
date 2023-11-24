package it.dedagroup.manifestazione.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Manifestazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Long idCategoria;
    private long idUtente;
    private boolean isCancellato;
    private Long idCategoria;
    @Version
    private long version;
}
