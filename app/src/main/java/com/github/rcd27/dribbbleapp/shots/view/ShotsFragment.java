package com.github.rcd27.dribbbleapp.shots.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.rcd27.dribbbleapp.DribbbleApplication;
import com.github.rcd27.dribbbleapp.R;
import com.github.rcd27.dribbbleapp.di.ShotsModule;
import com.github.rcd27.dribbbleapp.shots.ShotsContract;
import com.github.rcd27.dribbbleapp.shots.data.ShotVisualObject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ShotsFragment extends android.support.v4.app.Fragment implements ShotsContract.View {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView shotsRecyclerView;
    //TODO FIXME https://habrahabr.ru/post/334710/
    private ShotsRecyclerViewAdapter shotsRecyclerViewAdapter;

    @Inject
    public ShotsContract.Presenter shotsPresenter;

    @Inject
    public Picasso picasso;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DribbbleApplication.getInstance()
                .getAppComponent()
                .plus(new ShotsModule(this))
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.swipe_fragment, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);

        shotsRecyclerView = (RecyclerView) view.findViewById(R.id.shots_recycler_view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        shotsRecyclerViewAdapter = new ShotsRecyclerViewAdapter(picasso,
                new ArrayList<>(), shotsPresenter);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        shotsRecyclerView.setLayoutManager(llm);
        shotsRecyclerView.setAdapter(shotsRecyclerViewAdapter);

        swipeRefreshLayout.setOnRefreshListener(
                () -> shotsPresenter.checkIfOnlineAndUpdateActual());

        shotsPresenter.updateActual();
    }

    @Override
    public void update(@NonNull List<ShotVisualObject> shots) {
        shotsRecyclerViewAdapter.setShots(shots);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showError(@NonNull String errorMessage) {
        Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
        swipeRefreshLayout.setRefreshing(false);
    }
}
