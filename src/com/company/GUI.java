package com.company;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    private JMenuItem salvaComeLibreria;
    private JMenuItem apriLibreria;
    private JMenuItem salvaLibreria;
    private JMenuItem nuovoCd;
    private JMenuItem nuovaTraccia;
    private JMenuItem modificaSelezionato;
    private JMenuItem rimuoviSelezionato;
    private JMenuItem ricercaAvanzata;
    private JMenuItem braniPreferiti;
    private Manager gestore;
    private String path;
    private Integer aid;

    public GUI() {

        gestore = new Manager();
        AscoltaPulsanti as = new AscoltaPulsanti();

        //TEMPORANEO
        path = "/Users/ux/Documents/ArchivioCD/ArchivioCD/libreriacd.pgl";
        gestore.load(path);

        //SETTING INIZIALE DELLA SCHERMATA
        setTitle("GIOSTRA");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 500));
        setLocation(200, 200);

        //SETTING BARRA MENU
        JMenuBar menuBar = new JMenuBar();
        JMenu menuModifica = new JMenu("Modifica");
        JMenu menuLibreria = new JMenu("Libreria");
        JMenu menuArchivio = new JMenu("Archivio");
        JMenu menuRicerca = new JMenu("Ricerca");
        menuBar.add(menuModifica);
        menuBar.add(menuLibreria);
        menuBar.add(menuArchivio);
        menuBar.add(menuRicerca);

        //Creazione elementi barra menu
        apriLibreria = new JMenuItem("Apri Libreria", KeyEvent.VK_T);
        salvaLibreria = new JMenuItem("Salva Libreria", KeyEvent.VK_T);
        salvaComeLibreria = new JMenuItem("Salva come...", KeyEvent.VK_T);
        nuovoCd = new JMenuItem("Nuovo CD", KeyEvent.VK_T);
        nuovaTraccia = new JMenuItem("Nuova traccia in...", KeyEvent.VK_T);
        modificaSelezionato = new JMenuItem("Modifica CD selezionato", KeyEvent.VK_T);
        rimuoviSelezionato = new JMenuItem("Rimuovi CD selezionato", KeyEvent.VK_T);
        ricercaAvanzata = new JMenuItem("Ricerca avanzata", KeyEvent.VK_T);

        //aggiunta elementi a menu
        menuLibreria.add(apriLibreria);
        menuLibreria.add(salvaLibreria);
        menuLibreria.add(salvaComeLibreria);
        menuArchivio.add(nuovoCd);
        menuArchivio.add(nuovaTraccia);
        menuModifica.add(modificaSelezionato);
        menuModifica.add(rimuoviSelezionato);
        menuRicerca.add(ricercaAvanzata);

        //aggiunta ascoltatori
        salvaComeLibreria.addActionListener(as);
        apriLibreria.addActionListener(as);
        salvaLibreria.addActionListener(as);
        nuovoCd.addActionListener(as);
        nuovaTraccia.addActionListener(as);
        modificaSelezionato.addActionListener(as);
        rimuoviSelezionato.addActionListener(as);
        ricercaAvanzata.addActionListener(as);

        //AGGIUNTA TABELLA TRACCE
        String[] nomeColonne = {"Autore", "Anno", "Titolo Album", "Genere"};
        DefaultTableModel tableModel = new DefaultTableModel(nomeColonne, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        }; //CREAZIONE MODELLO PER TABELLA (non editabile dall'utente)
        JTable table = new JTable(tableModel);
        table.setAutoCreateRowSorter(true);
        for (int i = 0; i < gestore.getCdArray().size(); i++) {
            String autore = gestore.getCdArray().get(i).getAuthor();
            String anno = gestore.getCdArray().get(i).getYear().toString();
            String titolo = gestore.getCdArray().get(i).getTitle();
            String genere = gestore.getCdArray().get(i).getGen();

            Object[] data = {autore, anno, titolo, genere};

            tableModel.addRow(data);

        }
        JScrollPane scrollPane = new JScrollPane(table);

        //AGGIUNTA COMPONENTI ALLA FINESTRA
        setJMenuBar(menuBar);
        getContentPane().add(BorderLayout.CENTER, scrollPane);
        pack();
        setVisible(true);

        //ASCOLTATORI VARI EVENTI

        //CATTURA PRESSIONE MOUSE SU TABELLA
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String titoloCd = table.getValueAt(table.getSelectedRow(),2).toString();
                for (int i = 0; i<gestore.getCdArray().size(); i++){
                    if (gestore.getCdArray().get(i).getTitle() == titoloCd){
                        aid = gestore.getCdArray().get(i).getId();
                        if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
                            new GUIview(gestore, aid);
                        }
                    }
                }
            }
        });


        //REFRESH DELLA TABELLA ALL'OTTENIMENTO DEL FOCUS
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                tableModel.setRowCount(0);
                for (int i = 0; i < gestore.getCdArray().size(); i++) {
                    String autore = gestore.getCdArray().get(i).getAuthor();
                    String anno = gestore.getCdArray().get(i).getYear().toString();
                    String titolo = gestore.getCdArray().get(i).getTitle();
                    String genere = gestore.getCdArray().get(i).getGen();

                    Object[] data = {autore, anno, titolo, genere};

                    tableModel.addRow(data);

                }
            }
        });
    }

    private class AscoltaPulsanti implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JMenuItem source = (JMenuItem) e.getSource();
            if (source.equals(nuovoCd)) {
                new GUIcd(gestore);
            }
            if (source.equals(apriLibreria)) {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "Portable GIOSTRA Library", "pgl");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    path = chooser.getSelectedFile().getAbsolutePath();
                    gestore.load(path);
                }
            }
            if (source.equals(salvaComeLibreria)) {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "Portable GIOSTRA Library", "pgl");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showSaveDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    path = chooser.getSelectedFile().getAbsolutePath();
                    gestore.save(path);
                }
            }
            if (source.equals(salvaLibreria)) {
                gestore.save(path);
            }
            if (source.equals(rimuoviSelezionato)){
                gestore.removeCd(aid);
            }
            if (source.equals(modificaSelezionato)){
                new GUIeditcd(gestore,aid);
            }
            if (source.equals(ricercaAvanzata)){
                new GUIsearch(gestore);
            }
        }
    }
}
