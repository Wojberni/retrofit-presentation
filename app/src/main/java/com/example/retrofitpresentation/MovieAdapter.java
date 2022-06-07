package com.example.retrofitpresentation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitpresentation.retrofit.MovieData;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<MovieData> movies;
    private static MovieAdapterClickListener movieAdapterClickListener;


    public MovieAdapter(List<MovieData> movies, MovieAdapterClickListener movieAdapterClickListener) {
        this.movies = movies;
        MovieAdapter.movieAdapterClickListener = movieAdapterClickListener;
    }

    public interface MovieAdapterClickListener {
        void onClick(View view, int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView movieTitle;
        public TextView movieGenre;
        public TextView movieDescription;
        public TextView movieDirector;
        public Button editButton;
        public Button deleteButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.movieTitle);
            movieGenre = itemView.findViewById(R.id.movieGenre);
            movieDescription = itemView.findViewById(R.id.movieDescription);
            movieDirector = itemView.findViewById(R.id.movieDirector);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            editButton.setOnClickListener(this);
            deleteButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            movieAdapterClickListener.onClick(v, getAbsoluteAdapterPosition());
        }
    }



    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.ViewHolder holder, int position) {
        holder.movieTitle.setText(movies.get(position).getTitle());
        holder.movieGenre.setText(movies.get(position).getGenre());
        holder.movieDescription.setText(movies.get(position).getDescription());
        holder.movieDirector.setText(movies.get(position).getDirector());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
