package org.example.examenmediamanager.service;


import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.examenmediamanager.entity.CD;
import org.example.examenmediamanager.entity.Emprunt;
import org.example.examenmediamanager.entity.Utilisateur;

import java.time.LocalDate;
import java.util.List;

@Stateless
public class PretService {

    @PersistenceContext
    private EntityManager em;

    public void emprunterCD(int userId, int cdId) {
        Utilisateur utilisateur = em.find(Utilisateur.class, userId);
        CD cd = em.find(CD.class, cdId);
        if (cd != null && cd.isDisponible()) {
            Emprunt emprunt = new Emprunt();
            emprunt.setUtilisateur(utilisateur);
            emprunt.setCd(cd);
            emprunt.setDateEmprunt(LocalDate.now());
            cd.setDisponible(false);
            em.persist(emprunt);
            em.merge(cd);
        }
    }

    public void retournerCD(int cdId) {
        CD cd = em.find(CD.class, cdId);
        if (cd != null) {
            cd.setDisponible(true);
            em.merge(cd);
        }
    }

    public List<CD> listerCDDispo() {
        return em.createQuery("SELECT c FROM CD c WHERE c.disponible = true", CD.class).getResultList();
    }
}

