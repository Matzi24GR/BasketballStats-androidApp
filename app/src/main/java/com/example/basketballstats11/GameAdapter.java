package com.example.basketballstats11;


import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * From https://developer.android.com/guide/topics/ui/layout/recyclerview
 */
public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {

    private ArrayList<Game> localDataSet;
    private NavController navController;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ConstraintLayout layout;
        private final TextView dateText;
        private final TextView teamAText;
        private final TextView teamBText;
        private final ImageView teamAImage;
        private final ImageView teamBImage;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            layout = view.findViewById(R.id.gameItemLayout);
            dateText = view.findViewById(R.id.dateText);
            teamAText = view.findViewById(R.id.teamAname);
            teamBText = view.findViewById(R.id.teamBname);
            teamAImage = view.findViewById(R.id.teamAicon);
            teamBImage = view.findViewById(R.id.teamBicon);
        }

        public ConstraintLayout getLayout() {
            return layout;
        }

        public TextView getDateText() {
            return dateText;
        }

        public TextView getTeamAText() {
            return teamAText;
        }

        public TextView getTeamBText() {
            return teamBText;
        }

        public ImageView getTeamAImage() {
            return teamAImage;
        }

        public ImageView getTeamBImage() {
            return teamBImage;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public GameAdapter(ArrayList<Game> dataSet, NavController navController) {
        localDataSet = dataSet;
        this.navController = navController;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_game, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Game game = localDataSet.get(position);

        viewHolder.getLayout().setOnClickListener(v -> {
            AdminFragmentDirections.ActionAdminFragmentToR2Fragment action = AdminFragmentDirections.actionAdminFragmentToR2Fragment(game.getId(), game.getTeamA(), game.getTeamB());
            navController.navigate(action);
        });

        DateFormat formatter = SimpleDateFormat.getDateTimeInstance();
        String start = formatter.format(new Date(game.getTimeStart()*1000));
        String end = formatter.format(new Date(game.getTimeEnd()*1000));
        viewHolder.getDateText().setText(start +"\n"+end);

        viewHolder.getTeamAText().setText(game.getTeamA());
        viewHolder.getTeamBText().setText(game.getTeamB());
        Context context = viewHolder.dateText.getContext();

        Picasso.with(context).load(Uri.parse(Host.getInstance().getBaseUrl())+game.getTeamAurl()).into(viewHolder.getTeamAImage());
        Picasso.with(context).load(Uri.parse(Host.getInstance().getBaseUrl())+game.getTeamBurl()).into(viewHolder.getTeamBImage());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}


