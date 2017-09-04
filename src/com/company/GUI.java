package com.company;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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

        nuovaLibreria = new JMenuItem("Nuova Libreria", KeyEvent.VK_T);
        apriLibreria = new JMenuItem("Apri Libreria", KeyEvent.VK_T);
        salvaLibreria = new JMenuItem("Salva Libreria", KeyEvent.VK_T);
        nuovoCd = new JMenuItem("Nuovo CD", KeyEvent.VK_T);
        nuovaTraccia = new JMenuItem("Nuova traccia in...", KeyEvent.VK_T);

        menuLibreria.add(nuovaLibreria);
        menuLibreria.add(apriLibreria);
        menuLibreria.add(salvaLibreria);
        menuCd.add(nuovoCd);
        menuCd.add(nuovaTraccia);

        nuovaLibreria.addActionListener(as);
        apriLibreria.addActionListener(as);
        salvaLibreria.addActionListener(as);
        nuovoCd.addActionListener(as);
        nuovaTraccia.addActionListener(as);

        //DATI TEST
        gestore.newCd("Mario",2002,"Bello","house",new ArrayList<Track>() ,1);
        gestore.newCd("Jovanotti",1999,"A te","pop",new ArrayList<Track>() ,2);
        gestore.newCd("Fedex",3333,"Aggg","cacca",new ArrayList<Track>() ,3);
        gestore.newCd("Leonardo",43,"non","non",new ArrayList<Track>() ,4);


        //AGGIUNTA TABELLA TRACCE
        //Jtable(nomeColonne, dati)
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
        //table.setFillsViewportHeight(true);
        getContentPane().add(BorderLayout.NORTH ,scrollPane);


        //AGGIUNTA COMPONENTI ALLA FINESTRA
        setJMenuBar(menuBar);
        //getContentPane().add(BorderLayout.CENTER, panel);
        //getContentPane().add(BorderLayout.CENTER,scrollPane);
        pack();
        setVisible(true);
    }
    private class AscoltaPulsanti implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JMenuItem source = (JMenuItem)e.getSource();
            if (source.equals(nuovoCd)){
                new GUIcd();
            }
            System.out.println("Selected: " + e.getActionCommand());


        }
    }
}
