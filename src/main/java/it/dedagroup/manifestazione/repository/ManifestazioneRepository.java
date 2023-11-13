package it.dedagroup.manifestazione.repository;

import it.dedagroup.manifestazione.model.Manifestazione;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManifestazioneRepository extends JpaRepository<Manifestazione,Long> {
}
