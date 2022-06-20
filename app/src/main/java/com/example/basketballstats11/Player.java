package com.example.basketballstats11;

import android.util.Log;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Player {

    private int id;
    private String name;
    private int role;
    private String photoUrl;

    public Player(int id, String name, int role, String photoUrl) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.photoUrl = photoUrl;
    }

    public Player(JSONObject json) throws JSONException {
        this.id = Integer.parseInt(json.getString("id"));
        this.name = json.getString("name");
        this.role = Integer.parseInt(json.getString("role"));
        this.photoUrl = json.getString("photoUrl");
    }

    private void sendEvent(OkHttpClient client, int gameID, String type) throws IOException {

        RequestBody formBody = new FormBody.Builder()
                .add("playerId", String.valueOf(id))
                .add("type", type)
                .add("time", String.valueOf(System.currentTimeMillis()/1000))
                .build();

        Request request = new Request.Builder().url(Host.getInstance().getUrl()+"/games/"+gameID+"/events").post(formBody).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.i("Player", response.body().toString());
            }
        });
    }

    public void sendShot(OkHttpClient client, int gameID, int points, boolean successful) {
        String type= "";
        if (!successful) type += "Failed";
        if (points >=1 && points <= 3) {
            type += points + "Shot";
            try {
                sendEvent(client,gameID,type);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendRebound(OkHttpClient client, int gameID) {
        try {
            sendEvent(client,gameID,"Rebound");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendAssist(OkHttpClient client, int gameID) {
        try {
            sendEvent(client,gameID,"Assist");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendCut(OkHttpClient client, int gameID) {
        try {
            sendEvent(client,gameID,"Cut");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendSteal(OkHttpClient client, int gameID) {
        try {
            sendEvent(client,gameID,"Steal");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendMistake(OkHttpClient client, int gameID) {
        try {
            sendEvent(client,gameID,"Mistake");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void sendFoul(OkHttpClient client, int gameID) {
        try {
            sendEvent(client,gameID,"Foul");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getName() {
        return name;
    }

    public int getRole() {
        return role;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }
}
