package com.ago.movieapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.ago.movieapp.R;
import com.ago.movieapp.data.model.Movie;
import com.ago.movieapp.data.model.enums.MovieType;
import com.ago.movieapp.mvpContract.MovieListContract;
import com.ago.movieapp.presenter.MoviePresenter;
import com.ago.movieapp.ui.adapter.MovieListAdapter;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment implements MovieListContract.IMovieListView {

    private View view;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private List<Movie> movieList;
    private MovieListAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_movielist,container,false);

        movieList = new ArrayList<>();
        adapter = new MovieListAdapter(getActivity(),movieList);

        initUI();


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        // call onResume to get updated favorites on runtime
        getFavoriteMovies();
    }

    private void initUI(){

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.setAdapter(adapter);

        progressBar = view.findViewById(R.id.progressBar);
    }

    private void getFavoriteMovies(){
        movieList.clear();
        new MoviePresenter(this,MovieType.FAVORITE).getFavoriteMovies();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMsg(String msg) {

    }

    @Override
    public void onReceiveMovies(List<Movie> list) {
        if (list == null || list.isEmpty()){

            return;
        }

        movieList.addAll(list);
        adapter.notifyDataSetChanged();
    }
}

