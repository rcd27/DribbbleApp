package com.github.rcd27.dribbbleapp.shots.view.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.github.rcd27.dribbbleapp.R;
import com.github.rcd27.dribbbleapp.shots.model.objects.ShotVisualObject;
import com.github.rcd27.dribbbleapp.shots.presenter.ShotsPresenter;
import com.github.rcd27.dribbbleapp.shots.presenter.ShotsFragmentPresenter;
import com.github.rcd27.dribbbleapp.shots.view.adapters.ShotsFragmentListAdapter;

import java.util.List;

public class ShotsFragment extends android.support.v4.app.Fragment implements ShotsFragmentView {

    private SwipeRefreshLayout swipeRefreshLayout;

    private ListView shotsListView;

    //TODO FIXME https://habrahabr.ru/post/334710/
    private ShotsFragmentListAdapter listAdapter;

    private ShotsPresenter shotsPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        shotsPresenter = new ShotsFragmentPresenter(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.swipe_fragment, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);

        shotsListView = (ListView) view.findViewById(R.id.shot_list);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listAdapter = new ShotsFragmentListAdapter(getContext());
        shotsListView.setAdapter(listAdapter);
        swipeRefreshLayout.setOnRefreshListener(
                () -> shotsPresenter.checkIfOnlineAndUpdateActual());

        shotsPresenter.updateActual();
    }

    @Override
    public void update(@NonNull List<ShotVisualObject> shots) {
        listAdapter.clear();
        for (ShotVisualObject shot : shots) {
            listAdapter.add(shot);
            listAdapter.notifyDataSetChanged();
        }
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showError(@NonNull String errorMessage) {
        Toast.makeText(getContext(),errorMessage, Toast.LENGTH_LONG).show();
        swipeRefreshLayout.setRefreshing(false);
    }
}