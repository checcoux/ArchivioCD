package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUIeditcd extends JFrame {
    private Manager gestore;
    private Integer idSetter;
    private JTextField nomeCd;
    private JTextField nomeArtista;
    private JTextField annoCd;
    private JTextField genereCd;

    public GUIeditcd(Manager gestore, Integer idSetter){
        this.gestore = gestore;
        this.idSetter = idSetter;

        setTitle("Modifica di "+gestore.getCdArray().get(idSetter-1).getTitle());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel(); //FLOW LAYOUT
        setPreferredSize(new Dimension(450,100));
        setLocation(700,200);

        nomeCd = new JTextField(gestore.getCdArray().get(idSetter-1).getTitle());
        nomeArtista = new JTextField(gestore.getCdArray().get(idSetter-1).getAuthor());
        annoCd = new JTextField(gestore.getCdArray().get(idSetter-1).getYear().toString());
        genereCd = new JTextField(gestore.getCdArray().get(idSetter-1).getGen());

        nomeCd.setPreferredSize(new Dimension(200,30));
        nomeArtista.setPreferredSize(new Dimension(200,30));
        annoCd.setPreferredSize(new Dimension(200,30));
        genereCd.setPreferredSize(new Dimension(200,30));

        panel.add(nomeCd);
        panel.add(nomeArtista);
        panel.add(annoCd);
        panel.add(genereCd);

        //AGGIUNTA COMPONENTI ALLA FINESTRA
        getContentPane().add(BorderLayout.CENTER,panel);
        pack();
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                gestore.getCdArray().get(idSetter-1).setTitle(nomeCd.getText());
                gestore.getCdArray().get(idSetter-1).setAuthor(nomeArtista.getText());
                gestore.getCdArray().get(idSetter-1).setYear(Integer.parseInt(annoCd.getText()));
                gestore.getCdArray().get(idSetter-1).setGen(genereCd.getText());
                e.getWindow().dispose();
            }
        });

    }

}

