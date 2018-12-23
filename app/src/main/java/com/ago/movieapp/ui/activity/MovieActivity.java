package com.ago.movieapp.ui.activity;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ago.movieapp.R;
import com.ago.movieapp.data.cache.database.CachedMovie;
import com.ago.movieapp.data.cache.storage.ImageStorageRepo;
import com.ago.movieapp.data.model.Movie;


public class MovieActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView_poster,imageView_cover;
    private TextView textView_overView;
    private Toolbar toolbar;
    private RatingBar ratingBar;
    private FloatingActionButton fab_fav;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        initUI();

        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            finish();
            return;
        }

        movie = (Movie) bundle.getSerializable("data");

        setMoveData();
    }

    private void initUI(){

        toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fab_fav = findViewById(R.id.fab_fav);
        fab_fav.setOnClickListener(this);

        collapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout);
        imageView_poster = findViewById(R.id.imageView_poster);
        imageView_cover = findViewById(R.id.imageView_cover);
        textView_overView = findViewById(R.id.textView_overView);
        ratingBar = findViewById(R.id.ratingBar);
    }

    private void setMoveData(){

        getSupportActionBar().setTitle(movie.getTitle());

        textView_overView.setText(movie.getOverview());
        ratingBar.setRating((float) (movie.getVoteAverage()/2.0f));

        //collapsingToolbarLayout.setTitle(movie.getTitle());

        new ImageStorageRepo(this).getImage(movie.getPosterPath(),imageView_poster);
        new ImageStorageRepo(this).getImage(movie.getBackdropPath(),imageView_cover);
    }

    private void addFavorite(){

        new CachedMovie(null).setFavoriteMovie(movie.getId());

        Toast.makeText(this,"Movie Added To Favorites",Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){

            case android.R.id.home:
                onBackPressed();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.fab_fav:
                addFavorite();
                break;
        }
    }
}
