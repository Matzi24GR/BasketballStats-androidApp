package com.example.basketballstats11;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import com.example.basketballstats11.databinding.FragmentR2Binding;

import java.util.Objects;

public class R2Fragment extends Fragment {

    private FragmentR2Binding binding;

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
        int gameID = R2FragmentArgs.fromBundle(getArguments()).getGameID();
        String teamA = R2FragmentArgs.fromBundle(getArguments()).getTeamA();
        String teamB = R2FragmentArgs.fromBundle(getArguments()).getTeamB();

        ((MainActivity) requireActivity()).setAppBarTitle(teamA+" - "+teamB);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}