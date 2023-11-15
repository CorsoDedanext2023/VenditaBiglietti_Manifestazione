package it.dedagroup.manifestazione.repository;

import it.dedagroup.manifestazione.model.Manifestazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManifestazioneRepository extends JpaRepository<Manifestazione,Long> {
    List<Manifestazione> findAll();
    List<Manifestazione> findAllByIsCancellatoFalse();
    List<Manifestazione> findAllById(long id);
    List<Manifestazione> findAllByNome(String nome);
    List<Manifestazione> findAllByIdAndIsCancellatoFalse(long id);
    List<Manifestazione> findAllByNomeAndIsCancellatoFalse(String Nome);
}
