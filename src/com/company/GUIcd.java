package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;


public class GUIcd extends JFrame{

    private JButton aggiungiTraccia;

    public GUIcd(){

        AscoltaPulsanti as = new AscoltaPulsanti();

        setTitle("Aggiungi nuovo CD");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel(); //FLOW LAYOUT
        //JPanel panel = new JPanel(new GridLayout(4,4,4,4)); //GRID LAYOUT
        setPreferredSize(new Dimension(500,500));
        setLocation(200,200);

        //INSERIMENTO CASELLE DI TESTO
        JTextField nomeCd = new JTextField("Nome CD");
        JTextField nomeArtista = new JTextField("Nome Artista");
        JTextField annoCd = new JTextField("Anno");
        JTextField genereCd = new JTextField("Genere");

        nomeCd.setPreferredSize(new Dimension(200,30));
        nomeArtista.setPreferredSize(new Dimension(200,30));
        annoCd.setPreferredSize(new Dimension(200,30));
        genereCd.setPreferredSize(new Dimension(200,30));


        panel.add(nomeCd);
        panel.add(nomeArtista);
        panel.add(annoCd);
        panel.add(genereCd);

        //BOTTONE AGGIUNTA TRACCIA
        aggiungiTraccia = new JButton("Aggiungi nuova traccia");
        aggiungiTraccia.setPreferredSize(new Dimension(200,30));
        panel.add(aggiungiTraccia);
        aggiungiTraccia.addActionListener(as);


        //TABELLA TRACCE PRESENTI GIÀ NEL DISCOO
        //Jtable(nomeColonne, dati)CENTER
        String[] nomeColonne = {"Traccia","Durata","Genere","Classifica","Commento"};
        Object[][] data = new Object[][]{
                {"happy days",new Time(3,5,6),"Soul",5,"Bella"},
                {"no days",new Time(0,5,6),"Blues",5,"non tanto bella"},
        };
        JTable table = new JTable(data,nomeColonne);
        JScrollPane scrollPane = new JScrollPane(table);
        //table.setFillsViewportHeight(true);
        panel.add(scrollPane);


        getContentPane().add(BorderLayout.CENTER,panel);
        pack();
        setVisible(true);
    }

    private class AscoltaPulsanti implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            if (source.equals(aggiungiTraccia)){
                new GUItrack();
            }
        }
    }

}
