package com.example.basketballstats11;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.basketballstats11.databinding.FragmentTempMenuBinding;

public class TempMenuFragment extends Fragment {

    private FragmentTempMenuBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentTempMenuBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonR1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(TempMenuFragment.this)
                        .navigate(R.id.action_tempMenuFragment_to_r1Fragment);
            }
        });

        binding.buttonR2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(TempMenuFragment.this)
                        .navigate(R.id.action_tempMenuFragment_to_r2Fragment);
            }
        });

        binding.buttonR3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(TempMenuFragment.this)
                        .navigate(R.id.action_tempMenuFragment_to_r3Fragment);
            }
        });

        binding.buttonR4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(TempMenuFragment.this)
                        .navigate(R.id.action_tempMenuFragment_to_r4Fragment);
            }
        });

        binding.buttonR5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(TempMenuFragment.this)
                        .navigate(R.id.action_tempMenuFragment_to_r5Fragment);
            }
        });

        binding.buttonR6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(TempMenuFragment.this)
                        .navigate(R.id.action_tempMenuFragment_to_r6Fragment);
            }
        });

        binding.buttonR7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(TempMenuFragment.this)
                        .navigate(R.id.action_tempMenuFragment_to_r7Fragment);
            }
        });

        binding.buttonR8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(TempMenuFragment.this)
                        .navigate(R.id.action_tempMenuFragment_to_r8Fragment);
            }
        });

        binding.buttonR9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(TempMenuFragment.this)
                        .navigate(R.id.action_tempMenuFragment_to_r9Fragment);
            }
        });

        binding.buttonR10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(TempMenuFragment.this)
                        .navigate(R.id.action_tempMenuFragment_to_r10Fragment);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}