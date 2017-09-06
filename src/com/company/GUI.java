package com.company;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.AbstractTableModel;
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

        //AGGIUNTA TABELLA TRACCE
        String[] nomeColonne = {"Autore","Anno","Titolo","Genere"};
        DefaultTableModel tableModel = new DefaultTableModel(nomeColonne, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        }; //CREAZIONE MODELLO PER TABELLA (non editabile dall'utente)
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

        //MENU TASTO DESTRO SU TRACCE
        table.addMouseListener( new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    System.out.println("rigadoppio:"+table.getSelectedRow());
                    new GUIview(gestore, table.getSelectedRow()+1);
                }
                if (e.getClickCount() == 1) {
                    System.out.println("riga:"+table.getSelectedRow());
                }
            }
        });

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
