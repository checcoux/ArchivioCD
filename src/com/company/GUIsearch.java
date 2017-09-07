package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUIsearch extends JFrame {
    private JTextField ricerca;
    private JButton avviaRicerca;
    private ButtonGroup selettoreRicerca;
    private JRadioButton traccePreferite;
    private JRadioButton perGenere;
    private JRadioButton perDurataMin;
    private Manager gestore;
    private DefaultTableModel tableModel;

    public GUIsearch(Manager gestore){

        this.gestore = gestore;

        AscoltaPulsanti as = new AscoltaPulsanti();

        setTitle("Ricerca Avanzata");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        setLocation(200, 200);
        JPanel panel = new JPanel(); //FLOW LAYOUT

        selettoreRicerca = new ButtonGroup();
        traccePreferite = new JRadioButton("Tracce preferite");
        perGenere = new JRadioButton("Genere");
        perDurataMin = new JRadioButton("Traccia più corta di");

        selettoreRicerca.add(traccePreferite);
        selettoreRicerca.add(perGenere);
        selettoreRicerca.add(perDurataMin);

        panel.add(traccePreferite);
        panel.add(perGenere);
        panel.add(perDurataMin);

        ricerca = new JTextField("genere/mm:ss");
        ricerca.setPreferredSize(new Dimension(300,30));
        panel.add(ricerca);

        avviaRicerca = new JButton("Cerca!");
        panel.add(avviaRicerca);
        avviaRicerca.addActionListener(as);


        //TABELLA
        String[] nomeColonne = {"Traccia","Durata","Genere","Classifica","Commento"};
        tableModel = new DefaultTableModel(nomeColonne, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);

        getContentPane().add(BorderLayout.CENTER,panel);
        pack();
        setVisible(true);

    }

    private class AscoltaPulsanti implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton sorgente = (JButton) e.getSource();
            ArrayList<Track> tmpArray = new ArrayList<Track>();
            if (sorgente.equals(avviaRicerca)){
                if (traccePreferite.isSelected()){
                    for (int i = 0;i<gestore.getCdArray().size();i++){
                        System.out.println("a");
                        for (int j = 0;i<gestore.getCdArray().get(i).getTracks().size();j++){
                            System.out.println("b");
                            if (gestore.getCdArray().get(i).getTracks().get(j).getRank() == 5){
                                System.out.println("c");
                                tmpArray.add(gestore.getCdArray().get(i).getTracks().get(j));
                            }
                        }

                    }
                }
                if (perGenere.isSelected()){
                    for (int i = 0;i<gestore.getCdArray().size();i++){
                            if (gestore.getCdArray().get(i).getGen() == ricerca.getText()){
                                for (int j=0;i<gestore.getCdArray().get(i).getTracks().size();j++){
                                    tmpArray.add(gestore.getCdArray().get(i).getTracks().get(j));
                            }
                        }

                    }
                }
                if (perDurataMin.isSelected()){
                    System.out.println("3");
                }

                tableModel.setRowCount(0);
                for (int i = 0; i < tmpArray.size(); i++) {
                    String nomeTraccia = tmpArray.get(i).getTrackTitle();
                    String durata = tmpArray.get(i).getTrackTotalTime();
                    Integer classifica = tmpArray.get(i).getRank();
                    String descrizione = tmpArray.get(i).getDescription();
                    String genereTraccia = tmpArray.get(i).getTrackGen();

                    Object [] data = {nomeTraccia,durata,genereTraccia,classifica,descrizione};

                    tableModel.addRow(data);

                }
            }
        }
    }
}
