package com.example.basketballstats11;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.basketballstats11.databinding.FragmentLoginBinding;
import com.example.basketballstats11.databinding.FragmentRegisterBinding;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.registerButton.setOnClickListener(v -> {
            String username = binding.registerUsernameField.getText().toString();
            String password = binding.registerPasswordField.getText().toString();

            OkHttpClient client = new OkHttpClient();

            RequestBody formBody = new FormBody.Builder()
                    .add("username", username)
                    .add("password", password)
                    .build();

            Request request = new Request.Builder().url("http://192.168.1.2/api/register").post(formBody).build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.i("RegisterFragment", e.toString(), null);
                    requireActivity().runOnUiThread( () -> { Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show(); } );
                    call.cancel();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final String myResponse = response.body().string();
                    Log.i("RegisterFragment", myResponse, null);
                    requireActivity().runOnUiThread( () -> {
                        Toast.makeText(getContext(), myResponse, Toast.LENGTH_SHORT).show();
                        if (myResponse.equals("SUCCESS")) {
                            NavHostFragment.findNavController(RegisterFragment.this).navigateUp();
                        }
                    });
                }
            });
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}