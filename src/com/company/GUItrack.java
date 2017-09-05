package com.company;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
    private Manager gestore;
    private JButton ok;
    private Integer idSetter;
    final JFormattedTextField comp;

    public GUItrack(Manager gestore, Integer idSetter){

        this.gestore = gestore;

        this.idSetter = idSetter;

        AscoltaPulsanti as = new AscoltaPulsanti();

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
        comp = new JFormattedTextField();
        comp.setFormatterFactory(new DefaultFormatterFactory(new DateFormatter(new SimpleDateFormat(
                "H'h' mm'm'"))));
        comp.setValue(Calendar.getInstance().getTime());

        comp.setPreferredSize(new Dimension(200,30));
        titoloTraccia.setPreferredSize(new Dimension(200,30));
        genereTraccia.setPreferredSize(new Dimension(200,30));
        classifica.setPreferredSize(new Dimension(200,30));
        descrizione.setPreferredSize(new Dimension(200,30));

        //AGGIUNTA PULSANTE OK
        ok = new JButton("OK");
        ok.setPreferredSize(new Dimension(200,30));
        ok.addActionListener(as);

        panel.add(comp);
        panel.add(titoloTraccia);
        panel.add(genereTraccia);
        panel.add(classifica);
        panel.add(descrizione);
        panel.add(ok);

        getContentPane().add(BorderLayout.CENTER,panel);
        pack();
        setVisible(true);
    }
    private class AscoltaPulsanti implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            if (source.equals(ok)){
                //gestore.newTrack(titoloTraccia.getText(),new java.sql.Time());

            }

        }
    }

}
