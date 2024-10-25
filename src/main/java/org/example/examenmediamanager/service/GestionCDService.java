package org.example.examenmediamanager.service;


import jakarta.ejb.Stateful;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.examenmediamanager.entity.CD;
import org.example.examenmediamanager.entity.Emprunt;

import java.util.List;

@Stateful
public class GestionCDService {

    @PersistenceContext
    private EntityManager em;

    public void ajouterCD(CD cd) {
        em.persist(cd);
    }

    public void modifierCD(CD cd) {
        em.merge(cd);
    }

    public void supprimerCD(int cdId) {
        CD cd = em.find(CD.class, cdId);
        if (cd != null) {
            em.remove(cd);
        }
    }

    public List<Emprunt> listerEmprunts() {
        return em.createQuery("SELECT e FROM Emprunt e", Emprunt.class).getResultList();
    }
}

