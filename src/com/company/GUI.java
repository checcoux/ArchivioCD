package com.company;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

public class GUI extends JFrame {
    private JMenuItem nuovaLibreria;
    private JMenuItem apriLibreria;
    private JMenuItem salvaLibreria;
    private JMenuItem nuovoCd;
    private JMenuItem nuovaTraccia;
    private Manager gestore;

    public GUI(){

        gestore = new Manager();
        AscoltaPulsanti as = new AscoltaPulsanti();

        //SETTING INIZIALE DELLA SCHERMATA
        setTitle("GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel2 = new JPanel(); //FLOW LAYOUT
        //JPanel panel = new JPanel(new GridLayout(8,8,0,0)); //GRID LAYOUT
        setPreferredSize(new Dimension(500,500));
        setLocation(200,200);

        //SETTING BARRA MENU
        JMenuBar menuBar = new JMenuBar();
        JMenu menuFile = new JMenu("File");
        JMenu menuLibreria = new JMenu("Libreria");
        JMenu menuCd = new JMenu("CD");
        menuBar.add(menuFile);
        menuBar.add(menuLibreria);
        menuBar.add(menuCd);

        //Creazione elementi barra menu
        nuovaLibreria = new JMenuItem("Nuova Libreria", KeyEvent.VK_T);
        apriLibreria = new JMenuItem("Apri Libreria", KeyEvent.VK_T);
        salvaLibreria = new JMenuItem("Salva Libreria", KeyEvent.VK_T);
        nuovoCd = new JMenuItem("Nuovo CD", KeyEvent.VK_T);
        nuovaTraccia = new JMenuItem("Nuova traccia in...", KeyEvent.VK_T);

        //aggiunta elementi a menu
        menuLibreria.add(nuovaLibreria);
        menuLibreria.add(apriLibreria);
        menuLibreria.add(salvaLibreria);
        menuCd.add(nuovoCd);
        menuCd.add(nuovaTraccia);

        //aggiunta ascoltatori
        nuovaLibreria.addActionListener(as);
        apriLibreria.addActionListener(as);
        salvaLibreria.addActionListener(as);
        nuovoCd.addActionListener(as);
        nuovaTraccia.addActionListener(as);

        //DATI TEST
        /*gestore.newCd("Mario",2002,"Bello","house",new ArrayList<Track>() ,1);
        gestore.newCd("Jovanotti",1999,"A te","pop",new ArrayList<Track>() ,2);
        gestore.newCd("Fedex",3333,"Aggg","cacca",new ArrayList<Track>() ,3);
        gestore.newCd("Leonardo",43,"non","non",new ArrayList<Track>() ,4);
        */

        //AGGIUNTA TABELLA TRACCE
        String[] nomeColonne = {"Autore","Anno","Titolo","Genere"};
        DefaultTableModel tableModel = new DefaultTableModel(nomeColonne, 0);
        JTable table = new JTable(tableModel);
        for (int i = 0; i < gestore.getCdArray().size(); i++){
            String autore = gestore.getCdArray().get(i).getAuthor();
            String anno = gestore.getCdArray().get(i).getYear().toString();
            String titolo = gestore.getCdArray().get(i).getTitle();
            String genere = gestore.getCdArray().get(i).getGen();

            Object [] data = {autore,anno,titolo,genere};

            tableModel.addRow(data);

        }
        JScrollPane scrollPane = new JScrollPane(table);

        //AGGIUNTA COMPONENTI ALLA FINESTRA
        setJMenuBar(menuBar);
        getContentPane().add(BorderLayout.NORTH ,scrollPane);
        pack();
        setVisible(true);

        //REFRESH DELLA TABELLA ALL'OTTENIMENTO DEL FOCUS
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                System.out.println("Aggiorno tabella");
                tableModel.setRowCount(0);
                for (int i = 0; i < gestore.getCdArray().size(); i++){
                    String autore = gestore.getCdArray().get(i).getAuthor();
                    String anno = gestore.getCdArray().get(i).getYear().toString();
                    String titolo = gestore.getCdArray().get(i).getTitle();
                    String genere = gestore.getCdArray().get(i).getGen();

                    Object [] data = {autore,anno,titolo,genere};

                    tableModel.addRow(data);

                }
            }
        });
    }


    private class AscoltaPulsanti implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JMenuItem source = (JMenuItem)e.getSource();
            if (source.equals(nuovoCd)){
                new GUIcd(gestore);
            }
        }
    }
}
