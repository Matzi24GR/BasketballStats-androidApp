package com.example.basketballstats11;

public class Host {
    private String url = "https://www.esake.tk";

    private static Host instance;

    public static Host getInstance() {
        if (instance == null) {
            instance = new Host();
        }
        return instance;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url+"/api";
    }

    public String getBaseUrl() {
        return url;
    }

    private Host() {}
}
