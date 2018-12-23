package com.ago.movieapp.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ago.movieapp.R;
import com.ago.movieapp.data.cache.storage.ImageStorageRepo;
import com.ago.movieapp.data.model.Movie;
import com.ago.movieapp.ui.activity.MovieActivity;

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
        private CardView cardView;

        public MovieItemHolder(@NonNull View view) {
            super(view);

            imageView = view.findViewById(R.id.imageView);
            textView_name = view.findViewById(R.id.textView_name);
            cardView = view.findViewById(R.id.cardView);
        }

        public void bindData(final Movie movie){

            textView_name.setText(movie.getTitle());

            new ImageStorageRepo(context).getImage(movie.getPosterPath(),imageView);


            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data",movie);

                    Intent intent = new Intent(context,MovieActivity.class);
                    intent.putExtras(bundle);

                    //Shared Element transaction animation only supported for SDK >= 21
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

                        ActivityOptionsCompat options = ActivityOptionsCompat.
                                makeSceneTransitionAnimation((Activity)context, imageView, "image_transition");

                        context.startActivity(intent, options.toBundle());
                    }
                    else {
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}
