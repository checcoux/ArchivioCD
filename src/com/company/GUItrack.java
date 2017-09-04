package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUItrack extends JFrame {

    /*
    this.trackTitle = trackTitle;
        this.trackTotalTime = trackTotalTime;
        this.trackGen = trackGen;
        this.rank = rank;
        this.description = description;

    */
    private JTextField titoloTraccia;
    private JTextField genereTraccia;
    private JTextField classifica;
    private JTextField descrizione;

    public GUItrack(){

        setTitle("Aggiungi nuova traccia");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel(); //FLOW LAYOUT
        //JPanel panel = new JPanel(new GridLayout(4,4,4,4)); //GRID LAYOUT
        setPreferredSize(new Dimension(450,110));
        setLocation(700,200);

        //INSERIMENTO CASELLE DI TESTO

        titoloTraccia = new JTextField("Titolo traccia");
        genereTraccia = new JTextField("Genere traccia");
        classifica = new JTextField("Classifica");
        descrizione = new JTextField("Descrizione");

        titoloTraccia.setPreferredSize(new Dimension(200,30));
        genereTraccia.setPreferredSize(new Dimension(200,30));
        classifica.setPreferredSize(new Dimension(200,30));
        descrizione.setPreferredSize(new Dimension(200,30));

        panel.add(titoloTraccia);
        panel.add(genereTraccia);
        panel.add(classifica);
        panel.add(descrizione);

        getContentPane().add(BorderLayout.CENTER,panel);
        pack();
        setVisible(true);
    }

}
