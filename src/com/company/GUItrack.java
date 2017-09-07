package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUItrack extends JFrame {
    private JTextField titoloTraccia;
    private JTextField genereTraccia;
    private JTextField classifica;
    private JTextField descrizione;
    private Manager gestore;
    private Integer idSetter;
    private JTextField durataTraccia;

    public GUItrack(Manager gestore, Integer idSetter, boolean nuovo, Integer aid){

        this.gestore = gestore;

        this.idSetter = idSetter;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel(); //FLOW LAYOUT
        setPreferredSize(new Dimension(450,160));
        setLocation(700,200);

        //INSERIMENTO CASELLE DI TESTO

        if (nuovo){
            setTitle("Aggiungi nuova traccia");
            titoloTraccia = new JTextField("Titolo traccia");
            genereTraccia = new JTextField("Genere traccia");
            classifica = new JTextField("Classifica");
            descrizione = new JTextField("Descrizione");
            durataTraccia = new JTextField("mm:ss");
        }
        else {
            setTitle("Modifica tracce di "+gestore.getCdArray().get(idSetter-1).getTitle());
            titoloTraccia = new JTextField(gestore.getCdArray().get(idSetter-1).getTracks().get(aid).getTrackTitle());
            genereTraccia = new JTextField(gestore.getCdArray().get(idSetter-1).getTracks().get(aid).getTrackGen());
            classifica = new JTextField(gestore.getCdArray().get(idSetter-1).getTracks().get(aid).getRank().toString());
            descrizione = new JTextField(gestore.getCdArray().get(idSetter-1).getTracks().get(aid).getDescription());
            durataTraccia = new JTextField(gestore.getCdArray().get(idSetter-1).getTracks().get(aid).getTrackTotalTime());
        }


        durataTraccia.setPreferredSize(new Dimension(200,30));
        titoloTraccia.setPreferredSize(new Dimension(200,30));
        genereTraccia.setPreferredSize(new Dimension(200,30));
        classifica.setPreferredSize(new Dimension(200,30));
        descrizione.setPreferredSize(new Dimension(350,55));

        panel.add(durataTraccia);
        panel.add(titoloTraccia);
        panel.add(genereTraccia);
        panel.add(classifica);
        panel.add(descrizione);

        getContentPane().add(BorderLayout.CENTER,panel);
        pack();
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (nuovo)
                    gestore.newTrack(titoloTraccia.getText(),durataTraccia.getText(),genereTraccia.getText(),Integer.parseInt(classifica.getText()),descrizione.getText(),idSetter);
                else {
                    gestore.getCdArray().get(idSetter-1).getTracks().get(aid).setTrackTitle(titoloTraccia.getText());
                    gestore.getCdArray().get(idSetter-1).getTracks().get(aid).setTrackGen(genereTraccia.getText());
                    gestore.getCdArray().get(idSetter-1).getTracks().get(aid).setRank(Integer.parseInt(classifica.getText()));
                    gestore.getCdArray().get(idSetter-1).getTracks().get(aid).setDescription(descrizione.getText());
                    gestore.getCdArray().get(idSetter-1).getTracks().get(aid).setTrackTotalTime(durataTraccia.getText());
                }
                e.getWindow().dispose();
            }
        });
    }

}
