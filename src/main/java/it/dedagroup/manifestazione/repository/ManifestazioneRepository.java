package it.dedagroup.manifestazione.repository;

import it.dedagroup.manifestazione.model.Manifestazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("SpringDataMethodInconsistencyInspection")
public interface ManifestazioneRepository extends JpaRepository<Manifestazione, Long> {
    List<Manifestazione> findAllByIsCancellatoFalse();

    Optional<Manifestazione> findById(long id);

    Optional<Manifestazione> findByIdAndIsCancellatoFalse(long id);

    Optional<Manifestazione> findByNome(String nome);

    Optional<Manifestazione> findByNomeAndIsCancellatoFalse(String Nome);
}
