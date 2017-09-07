package com.company;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class Manager {
    private ArrayList<Cd> cdArray = new ArrayList<Cd>();

    public void newCd(String author, Integer year, String title, String gen, ArrayList<Track> tracks, Integer id){
        Cd disc = new Cd(author,year,title,gen,tracks, id);
        cdArray.add(disc);
    }

    //per aggiungere una traccia bisogna passare i metadati alla funzione newTrack e l'id del cd
    //ovviamente prima è necessario creare un cd con nessuna traccia al suo interno, esse verranno aggiunte dopo

    public void newTrack(String trackTitle, String trackTotalTime, String trackGen, Integer rank, String description, Integer id){
        Track tmpTrack = new Track(trackTitle,trackTotalTime,trackGen, rank, description);
        cdArray.get(id-1).addTrack(tmpTrack);
    }

    //per rimuovere un cd basta passare l'id del cd

    public void removeCd(Integer id){
        /*cdArray.get(id-1).getTracks().clear();
        for (int i = id-1; i<cdArray.size();i++){
            cdArray.get(id-1).setId(id);
        }
        cdArray.remove(cdArray.get(id));*/
        Iterator<Cd> it = cdArray.iterator();
        while (it.hasNext()) {
            if (it.next().getId() == id) {
                it.remove();
                break;
            }
        }
    }

    //salvataggio su file degli oggetti
    public void save(String filePath) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(cdArray);
            oos.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void load(String filePath) {
        try {
            System.out.println(filePath);
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            cdArray = (ArrayList<Cd>) ois.readObject();
            ois.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Cd> getCdArray() {
        return cdArray;
    }

    public void setCdArray(ArrayList<Cd> cdArray) {
        this.cdArray = cdArray;
    }

}
