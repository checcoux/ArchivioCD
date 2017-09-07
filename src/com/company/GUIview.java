package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUIview extends JFrame {
    private Manager gestore;
    private Integer idSetter;
    private Integer aid;

    public GUIview(Manager gestore, Integer idSetter){
        this.gestore = gestore;
        this.idSetter = idSetter;

        //IDSETTER IN QUESTO CASO E' L'ID DEL CD

        setTitle("Tracce di "+gestore.getCdArray().get(idSetter-1).getTitle());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(500,500));
        setLocation(200,200);

        //TABELLA
        String[] nomeColonne = {"Traccia","Durata","Genere","Classifica","Commento"};
        DefaultTableModel tableModel = new DefaultTableModel(nomeColonne, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(tableModel);
        table.setAutoCreateRowSorter(true);
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

        //ASCOLTATORI AGGIORNAMENTO DATI E MOUSE

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String titoloCanzone = table.getValueAt(table.getSelectedRow(),0).toString();
                for (int i = 0; i<gestore.getCdArray().get(idSetter-1).getTracks().size(); i++){
                    if (gestore.getCdArray().get(idSetter-1).getTracks().get(i).getTrackTitle() == titoloCanzone){
                        if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
                            new GUItrack(gestore, idSetter,false,i);
                        }
                    }
                }
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                tableModel.setRowCount(0);
                for (int i = 0; i < gestore.getCdArray().get(idSetter-1).getTracks().size(); i++){
                    String nomeTraccia = gestore.getCdArray().get(idSetter-1).getTracks().get(i).getTrackTitle();
                    String durata = gestore.getCdArray().get(idSetter-1).getTracks().get(i).getTrackTotalTime();
                    Integer classifica = gestore.getCdArray().get(idSetter-1).getTracks().get(i).getRank();
                    String descrizione = gestore.getCdArray().get(idSetter-1).getTracks().get(i).getDescription();
                    String genereTraccia = gestore.getCdArray().get(idSetter-1).getTracks().get(i).getTrackGen();


                    Object [] data = {nomeTraccia,durata,genereTraccia,classifica,descrizione};

                    tableModel.addRow(data);

                }
            }
        });

    }

}
