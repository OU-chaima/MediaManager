package org.example.examenmediamanager;


import org.example.examenmediamanager.entity.CD;
import org.example.examenmediamanager.service.AdminService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CDAdminApp extends JFrame {

    private AdminService adminService;

    public CDAdminApp(AdminService adminService) {
        this.adminService = adminService;

        setTitle("Gestion des CD");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton btnAjouter = new JButton("Ajouter CD");
        JButton btnModifier = new JButton("Modifier CD");
        JButton btnSupprimer = new JButton("Supprimer CD");
        JButton btnConsulter = new JButton("Consulter CD");

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(btnAjouter);
        panel.add(btnModifier);
        panel.add(btnSupprimer);
        panel.add(btnConsulter);

        add(panel, BorderLayout.SOUTH);

        btnConsulter.addActionListener(e -> afficherCD());
    }

    private void afficherCD() {
        List<CD> cds = adminService.consulterCD();
        StringBuilder sb = new StringBuilder();
        for (CD cd : cds) {
            sb.append(cd.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString(), "Liste des CD", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {

        AdminService adminService = new AdminService();
        new CDAdminApp(adminService).setVisible(true);
    }
}

