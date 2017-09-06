package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GUIview extends JFrame {
    private Manager gestore;
    private Integer idSetter;

    public GUIview(Manager gestore, Integer idSetter){
        this.gestore = gestore;
        this.idSetter = idSetter;

        setTitle("Tracce di "+gestore.getCdArray().get(idSetter-1).getTitle());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel2 = new JPanel(); //FLOW LAYOUT
        setPreferredSize(new Dimension(500,500));
        setLocation(200,200);

        //TABELLA
        String[] nomeColonne = {"Traccia","Durata","Genere","Classifica","Commento"};
        DefaultTableModel tableModel = new DefaultTableModel(nomeColonne, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        for (int i = 0; i < gestore.getCdArray().get(idSetter-1).getTracks().size(); i++){
            String nomeTraccia = gestore.getCdArray().get(idSetter-1).getTracks().get(i).getTrackTitle();
            String durata = gestore.getCdArray().get(idSetter-1).getTracks().get(i).getTrackTotalTime();
            Integer classifica = gestore.getCdArray().get(idSetter-1).getTracks().get(i).getRank();
            String descrizione = gestore.getCdArray().get(idSetter-1).getTracks().get(i).getDescription();
            String genereTraccia = gestore.getCdArray().get(idSetter-1).getTracks().get(i).getTrackGen();


            Object [] data = {nomeTraccia,durata,genereTraccia,classifica,descrizione};

            tableModel.addRow(data);

        }

        //AGGIUNTA COMPONENTI ALLA FINESTRA
        getContentPane().add(BorderLayout.CENTER ,scrollPane);
        pack();
        setVisible(true);

    }

}
