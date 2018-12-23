package com.ago.movieapp.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ago.movieapp.R;
import com.ago.movieapp.data.model.Movie;
import com.ago.movieapp.network.EndPoints;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieItemHolder>{

    private Context context;
    private List<Movie> list;

    public MovieListAdapter(Context context, List<Movie> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MovieItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_movie,viewGroup,false);

        return new MovieItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieItemHolder movieItemHolder, int i) {

        Movie movie = list.get(i);

        movieItemHolder.bindData(movie);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MovieItemHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView_name;

        public MovieItemHolder(@NonNull View view) {
            super(view);

            imageView = view.findViewById(R.id.imageView);
            textView_name = view.findViewById(R.id.textView_name);
        }

        public void bindData(Movie movie){

            textView_name.setText(movie.getTitle());

            Picasso.get().load(EndPoints.IMAGE_BASE_URL + movie.getPosterPath()).into(imageView);
        }
    }
}
