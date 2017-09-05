package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Time;
import java.util.ArrayList;


public class GUIcd extends JFrame{

    private JButton aggiungiTraccia;
    private JTextField nomeCd;
    private JTextField nomeArtista;
    private JTextField annoCd;
    private JTextField genereCd;
    private Manager gestore;
    private JButton updateui;
    private boolean cdInserito;
    private Integer idSetter = new Integer(1);

    DefaultTableModel tableModel;

    public GUIcd(Manager gestore){

        cdInserito = false;

        this.gestore = gestore;

        AscoltaPulsanti as = new AscoltaPulsanti();

        System.out.println("id appena lanciata gui:"+idSetter.toString());

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

        updateui = new JButton("update");
        updateui.setPreferredSize(new Dimension(200,30));
        panel.add(updateui);
        updateui.addActionListener(as);

        //DATI TEST
        //gestore.newTrack("oriva","32:15","soul",4,"coap",idSetter);

        //TABELLA TRACCE PRESENTI GIÀ NEL DISCOO
        //Jtable(nomeColonne, dati)

        String[] nomeColonne = {"Traccia","Durata","Genere","Classifica","Commento"};
        tableModel = new DefaultTableModel(nomeColonne, 0);

        JTable table = new JTable(tableModel);


        if (!(gestore.getCdArray().isEmpty())){
            for (int i = 0; i < gestore.getCdArray().size(); i++){
                String nomeTraccia = gestore.getCdArray().get(idSetter).getTracks().get(i).getTrackTitle();
                String durata = gestore.getCdArray().get(idSetter).getTracks().get(i).getTrackTotalTime();
                Integer classifica = gestore.getCdArray().get(idSetter).getTracks().get(i).getRank();
                String descrizione = gestore.getCdArray().get(idSetter).getTracks().get(i).getDescription();
                String genereTraccia = gestore.getCdArray().get(idSetter).getTracks().get(i).getTrackGen();


                Object [] data = {nomeTraccia,durata,genereTraccia,classifica,descrizione};

                tableModel.addRow(data);

            }
        }


        JScrollPane scrollPane = new JScrollPane(table);

        panel.add(scrollPane);

        getContentPane().add(BorderLayout.CENTER,panel);
        pack();
        setVisible(true);

        //REFRESH DELLA TABELLA ALL'OTTENIMENTO DEL FOCUS

        /*addWindowListener(new WindowAdapter() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                System.out.println("Aggiorno tabella");
                tableModel.setRowCount(0);
                for (int i = 0; i < gestore.getCdArray().size(); i++){
                    String nomeTraccia = gestore.getCdArray().get(idSetter).getTracks().get(i).getTrackTitle();
                    String durata = gestore.getCdArray().get(idSetter).getTracks().get(i).getTrackTotalTime();
                    Integer classifica = gestore.getCdArray().get(idSetter).getTracks().get(i).getRank();
                    String descrizione = gestore.getCdArray().get(idSetter).getTracks().get(i).getDescription();
                    String genereTraccia = gestore.getCdArray().get(idSetter).getTracks().get(i).getTrackGen();


                    Object [] data = {nomeTraccia,durata,genereTraccia,classifica,descrizione};

                    tableModel.addRow(data);

                }

            }
        });*/

        //incremento id SOLO A CHIUSURA

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                idSetter++;
                e.getWindow().dispose();
            }
        });

    }

    private class AscoltaPulsanti implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            if (source.equals(aggiungiTraccia)){
                if (cdInserito == false){
                    gestore.newCd(nomeArtista.getText(),Integer.parseInt(annoCd.getText()),nomeCd.getText(),genereCd.getText(),new ArrayList<Track>() ,idSetter);
                    cdInserito = true;

                    System.out.println("id appena aggiunto nuovo cd:"+idSetter.toString());
                }
                new GUItrack(gestore,idSetter);
            }
            if (source.equals(updateui)){
                System.out.println("UPDATING UI");

                for (int i = 0; i < gestore.getCdArray().get(idSetter-1).getTracks().size(); i++){
                    String nomeTraccia = gestore.getCdArray().get(idSetter).getTracks().get(i).getTrackTitle();
                    String durata = gestore.getCdArray().get(idSetter).getTracks().get(i).getTrackTotalTime();
                    Integer classifica = gestore.getCdArray().get(idSetter).getTracks().get(i).getRank();
                    String descrizione = gestore.getCdArray().get(idSetter).getTracks().get(i).getDescription();
                    String genereTraccia = gestore.getCdArray().get(idSetter).getTracks().get(i).getTrackGen();


                    Object [] data = {nomeTraccia,durata,genereTraccia,classifica,descrizione};

                    tableModel.addRow(data);

                }
            }
        }
    }

}
