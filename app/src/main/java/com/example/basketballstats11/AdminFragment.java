package com.example.basketballstats11;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.basketballstats11.databinding.FragmentAdminBinding;
import com.example.basketballstats11.databinding.FragmentR3Binding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AdminFragment extends Fragment {

    private FragmentAdminBinding binding;
    ArrayList<Game> games = new ArrayList<>();

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentAdminBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        GameAdapter adapter = new GameAdapter(games);
        binding.gameRecycler.setAdapter(adapter);
        binding.gameRecycler.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.gameRecycler.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL));

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(Host.getInstance().getUrl()+"/games").get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String myresponse = response.body().string();
                Log.i("admin", myresponse);
                try {
                    JSONObject json = new JSONObject(myresponse);
                    JSONArray array = json.getJSONArray("games");
                    for (int i=0; i<array.length(); i++) {
                        games.add(new Game(array.getJSONObject(i)));
                    }
                    getActivity().runOnUiThread(() -> adapter.notifyDataSetChanged());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}