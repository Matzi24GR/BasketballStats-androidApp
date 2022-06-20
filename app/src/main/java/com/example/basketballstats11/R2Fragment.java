package com.example.basketballstats11;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.basketballstats11.databinding.FragmentR2Binding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class R2Fragment extends Fragment {

    private FragmentR2Binding binding;

    OkHttpClient client = new OkHttpClient();

    int gameID;

    Player[] teamAPlayers = new Player[5];
    Player[] teamBPlayers = new Player[5];

    TextView[] teamAtextViews = new TextView[5];
    TextView[] teamBtextViews = new TextView[5];

    Player selectedPlayer;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentR2Binding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupTextViews();

        gameID = R2FragmentArgs.fromBundle(getArguments()).getGameID();
        String teamA = R2FragmentArgs.fromBundle(getArguments()).getTeamA();
        String teamB = R2FragmentArgs.fromBundle(getArguments()).getTeamB();

        // Set Title
        ((MainActivity) requireActivity()).setAppBarTitle("R2: "+teamA+" - "+teamB);

        // Setup Players
        Request request = new Request.Builder().url(Host.getInstance().getUrl()+"/games/"+gameID+"/players").get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String myresponse = response.body().string();
                Log.i("R2", myresponse);
                try {
                    JSONObject json = new JSONObject(myresponse);
                    JSONArray array = json.getJSONArray("teamAPlayers");
                    for (int i=0; i<array.length()&&i<5; i++) {
                        teamAPlayers[i] = new Player(array.getJSONObject(i));
                    }
                    array = json.getJSONArray("teamBPlayers");
                    for (int i=0; i<array.length()&&i<5; i++) {
                        teamBPlayers[i] = new Player(array.getJSONObject(i));
                    }
                    setPlayerNames();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

        //Button Handling

        // Shots

        binding.onePointButton.setOnClickListener( v -> {
            if (selectedPlayer != null) {
                selectedPlayer.sendShot(client, gameID,1, true);
            }
        });
        binding.onePointButton.setOnLongClickListener( v -> {
            if (selectedPlayer != null) {
                selectedPlayer.sendShot(client, gameID,1, false);
            }
            return true;
        });
        binding.twoPointButton.setOnClickListener( v -> {
            if (selectedPlayer != null) {
                selectedPlayer.sendShot(client, gameID,2, true);
            }
        });
        binding.twoPointButton.setOnLongClickListener( v -> {
            if (selectedPlayer != null) {
                selectedPlayer.sendShot(client, gameID,2, false);
            }
            return true;
        });
        binding.threePointButton.setOnClickListener( v -> {
            if (selectedPlayer != null) {
                selectedPlayer.sendShot(client, gameID,3, true);
            }
        });
        binding.threePointButton.setOnLongClickListener( v -> {
            if (selectedPlayer != null) {
                selectedPlayer.sendShot(client, gameID,3, false);
            }
            return true;
        });

        // Other

        binding.reboundButton.setOnClickListener( v -> {
            if (selectedPlayer != null) {
                selectedPlayer.sendRebound(client, gameID);
            }
        });

        binding.assistButton.setOnClickListener( v -> {
            if (selectedPlayer != null) {
                selectedPlayer.sendAssist(client, gameID);
            }
        });

        binding.cutButton.setOnClickListener( v -> {
            if (selectedPlayer != null) {
                selectedPlayer.sendCut(client, gameID);
            }
        });

        binding.stealButton.setOnClickListener( v -> {
            if (selectedPlayer != null) {
                selectedPlayer.sendSteal(client, gameID);
            }
        });

        binding.mistakeButton.setOnClickListener( v -> {
            if (selectedPlayer != null) {
                selectedPlayer.sendMistake(client, gameID);
            }
        });

        binding.foulButton.setOnClickListener( v -> {
            if (selectedPlayer != null) {
                selectedPlayer.sendFoul(client, gameID);
            }
        });

    }

    private void setupTextViews() {
        teamAtextViews[0] = binding.teamAP1;
        teamAtextViews[1] = binding.teamAP2;
        teamAtextViews[2] = binding.teamAP3;
        teamAtextViews[3] = binding.teamAP4;
        teamAtextViews[4] = binding.teamAP5;
        teamBtextViews[0] = binding.teamBP1;
        teamBtextViews[1] = binding.teamBP2;
        teamBtextViews[2] = binding.teamBP3;
        teamBtextViews[3] = binding.teamBP4;
        teamBtextViews[4] = binding.teamBP5;

        for (int i=0; i<teamAtextViews.length; i++) {
            int finalI = i;
            teamAtextViews[i].setOnClickListener(v -> {
                selectedPlayer = teamAPlayers[finalI];
            });
            teamBtextViews[i].setOnClickListener(v -> {
                selectedPlayer = teamBPlayers[finalI];
            });
        }
    }

    private void setPlayerNames() {
        for (int i=0; i<teamAtextViews.length; i++) {
            int finalI = i;

            getActivity().runOnUiThread(() -> {
                if (teamAPlayers[finalI] != null) teamAtextViews[finalI].setText(teamAPlayers[finalI].getName());
                if (teamBPlayers[finalI] != null) teamBtextViews[finalI].setText(teamBPlayers[finalI].getName());
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}