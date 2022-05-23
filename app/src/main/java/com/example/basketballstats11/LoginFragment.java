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

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.loginButton.setOnClickListener(v -> {
            String username = binding.loginUsernameField.getText().toString();
            String password = binding.loginPasswordField.getText().toString();

            OkHttpClient client = new OkHttpClient();

            RequestBody formBody = new FormBody.Builder()
                    .add("username", username)
                    .add("password", password)
                    .build();

            Request request = new Request.Builder().url("http://192.168.1.2/api/login").post(formBody).build();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.i("LoginFragment", e.toString(), null);
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                    call.cancel();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final String myResponse = response.body().string();
                    requireActivity().runOnUiThread( () -> {
                        if (myResponse.equals("Admin")) {
                            NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.action_loginFragment_to_adminFragment);
                        } else if (myResponse.equals("User")) {
                            NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.action_loginFragment_to_userFragment);
                        } else {
                            Toast.makeText(getContext(), myResponse, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        });

        binding.navigateRegisterButton.setOnClickListener(v -> {
            NavHostFragment.findNavController(LoginFragment.this).navigate(R.id.action_loginFragment_to_registerFragment);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}