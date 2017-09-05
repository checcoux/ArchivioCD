package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.ArrayList;


public class GUIcd extends JFrame{

    private JButton aggiungiTraccia;
    private JTextField nomeCd;
    private JTextField nomeArtista;
    private JTextField annoCd;
    private JTextField genereCd;
    private Manager gestore;
    private JButton aggiungiCd;
    private boolean cdInserito;
    private Integer idSetter = new Integer(1);

    public GUIcd(Manager gestore){

        cdInserito = false;

        this.gestore = gestore;

        AscoltaPulsanti as = new AscoltaPulsanti();

        setTitle("Aggiungi nuovo CD");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel(); //FLOW LAYOUT
        //JPanel panel = new JPanel(new GridLayout(4,4,4,4)); //GRID LAYOUT
        setPreferredSize(new Dimension(500,500));
        setLocation(200,200);

        //INSERIMENTO CASELLE DI TESTO
        nomeCd = new JTextField("Nome CD");
        nomeArtista = new JTextField("Nome Artista");
        annoCd = new JTextField("Anno");
        genereCd = new JTextField("Genere");

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

        /*
        //BOTTONE AGGIUNGI CD
        aggiungiCd = new JButton("Aggiungi CD a catalogo");
        aggiungiCd.setPreferredSize(new Dimension(200,30));
        panel.add(aggiungiCd);
        aggiungiCd.addActionListener(as);
        */

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
                if (cdInserito == false){
                    gestore.newCd(nomeArtista.getText(),Integer.parseInt(annoCd.getText()),nomeCd.getText(),genereCd.getText(),new ArrayList<Track>() ,idSetter);
                    idSetter++;
                    cdInserito = true;
                }
                new GUItrack(gestore,idSetter);
            }
        }
    }

}
