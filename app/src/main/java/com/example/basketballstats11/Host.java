package com.example.basketballstats11;

public class Host {
    private String url = "https://www.esake.tk/api";

    private static Host instance;

    public static Host getInstance() {
        if (instance == null) {
            instance = new Host();
        }
        return instance;
    }

    public void setUrl(String url) {
        this.url = url+"/api";
    }

    public String getUrl() {
        return url;
    }

    private Host() {}
}
