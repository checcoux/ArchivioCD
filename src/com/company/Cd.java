package com.company;

import java.util.ArrayList;

public class Cd {
    private String author;
    private Integer year;
    private String title;
    private String gen;
    private ArrayList<Track> tracks;
    private Integer id;

    public Cd(String author, Integer year, String title, String gen, ArrayList<Track> tracks, Integer id){
        this.author = author;
        this.year = year;
        this.title = title;
        this.gen = gen;
        this.tracks = tracks;
        this.id = id;
    }

    public void addTrack(Track tmpTrack){
        tracks.add(tmpTrack);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<Track> tracks) {
        this.tracks = tracks;
    }
}
