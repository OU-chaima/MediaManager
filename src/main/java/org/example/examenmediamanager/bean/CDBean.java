package org.example.examenmediamanager.bean;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import org.example.examenmediamanager.entity.CD;
import org.example.examenmediamanager.service.PretService;

import java.util.List;

@ManagedBean
@SessionScoped
public class CDBean {

    @EJB
    private PretService pretService;

    private Long cdId;
    private List<CD> listeCD;

    @PostConstruct
    public void init() {
        listeCD = pretService.listerCDDispo();
    }

    public void emprunterCD() {
        pretService.emprunterCD(cdId);
        init(); // Recharger la liste des CD
    }

    public void retournerCD() {
        pretService.retournerCD(cdId);
        init(); // Recharger la liste des CD
    }

    // Getters et Setters
}

