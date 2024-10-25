package org.example.examenmediamanager.service;

import jakarta.ejb.Stateful;
import jakarta.persistence.PersistenceContext;
import org.example.examenmediamanager.entity.CD;

import java.util.List;

@Stateful
public class AdminService {

    @PersistenceContext
    private EntityManager em;

    public void ajouterCD(CD cd) {
        em.persist(cd);
    }

    public void modifierCD(CD cd) {
        em.merge(cd);
    }

    public void supprimerCD(Long cdId) {
        CD cd = em.find(CD.class, cdId);
        if (cd != null) {
            em.remove(cd);
        }
    }

    public List<CD> consulterCD() {
        return em.createQuery("SELECT c FROM CD c", CD.class).getResultList();
    }
}

