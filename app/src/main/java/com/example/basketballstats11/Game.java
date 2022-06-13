package com.example.basketballstats11;

import org.json.JSONException;
import org.json.JSONObject;

public class Game {
    private int id;
    private long timeStart;
    private long timeEnd;
    private String teamA;
    private String teamAurl;
    private String teamB;
    private String teamBurl;

    public Game(int id, long timeStart, long timeEnd, String teamA, String teamAurl, String teamB, String teamBurl) {
        this.id = id;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.teamA = teamA;
        this.teamAurl = teamAurl;
        this.teamB = teamB;
        this.teamBurl = teamBurl;
    }

    public Game(JSONObject json) throws JSONException {
        this.id = Integer.parseInt(json.getString("id"));
        this.timeStart = Long.parseLong(json.getString("timeStart"));
        this.timeEnd = Long.parseLong(json.getString("timeEnd"));
        this.teamA = json.getString("teamA");
        this.teamAurl = json.getString("teamAurl");
        this.teamB = json.getString("teamB");
        this.teamBurl = json.getString("teamBurl");
    }

    public int getId() {
        return id;
    }

    public long getTimeStart() {
        return timeStart;
    }

    public long getTimeEnd() {
        return timeEnd;
    }

    public String getTeamA() {
        return teamA;
    }

    public String getTeamAurl() {
        return teamAurl;
    }

    public String getTeamB() {
        return teamB;
    }

    public String getTeamBurl() {
        return teamBurl;
    }
}
