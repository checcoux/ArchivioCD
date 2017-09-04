package com.company;

import java.sql.Time;

public class Track {
    private String trackTitle;
    private Time trackTotalTime;
    private String trackGen;
    private Integer rank;
    private String description;

    public Track(String trackTitle, Time trackTotalTime, String trackGen, Integer rank, String description){
        this.trackTitle = trackTitle;
        this.trackTotalTime = trackTotalTime;
        this.trackGen = trackGen;
        this.rank = rank;
        this.description = description;
    }


    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTrackTitle() {
        return trackTitle;
    }

    public void setTrackTitle(String trackTitle) {
        this.trackTitle = trackTitle;
    }

    public Time getTrackTotalTime() {
        return trackTotalTime;
    }

    public void setTrackTotalTime(Time trackTotalTime) {
        this.trackTotalTime = trackTotalTime;
    }

    public String getTrackGen() {
        return trackGen;
    }

    public void setTrackGen(String trackGen) {
        this.trackGen = trackGen;
    }
}
