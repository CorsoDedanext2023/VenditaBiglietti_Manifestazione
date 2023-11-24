package it.dedagroup.manifestazione.repository;

import it.dedagroup.manifestazione.DTO.Request.FiltroManifestazioneDTORequest;
import it.dedagroup.manifestazione.model.Manifestazione;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CriteriaManifestazioneRepository {
    @Autowired
    private EntityManager manager;

    public List<Manifestazione> filtraManifestazioni(FiltroManifestazioneDTORequest request) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Tuple> query = builder.createQuery(Tuple.class);
        Root<Manifestazione> root = query.from(Manifestazione.class);
        List<Predicate> predicate = new ArrayList<>();
        if(request.getNome() != null) predicate.add(builder.like(builder.lower(root.get("nome")), "%"+ request.getNome().toLowerCase()+"%"));
        Predicate[] predicateArray = predicate.toArray(new Predicate[predicate.size()]);
        query.where(predicateArray);
        List<Tuple> list = manager.createQuery(query).getResultList();
        return list.stream().map(t -> t.get(0, Manifestazione.class)).toList();
    }
}
