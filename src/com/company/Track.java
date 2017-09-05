package com.company;

public class Track {
    private String trackTitle;
    private String trackTotalTime;
    private String trackGen;
    private Integer rank;
    private String description;

    public Track(String trackTitle, String trackTotalTime, String trackGen, Integer rank, String description){
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

    public String getTrackTotalTime() {
        return trackTotalTime;
    }

    public void setTrackTotalTime(String trackTotalTime) {
        this.trackTotalTime = trackTotalTime;
    }

    public String getTrackGen() {
        return trackGen;
    }

    public void setTrackGen(String trackGen) {
        this.trackGen = trackGen;
    }
}
