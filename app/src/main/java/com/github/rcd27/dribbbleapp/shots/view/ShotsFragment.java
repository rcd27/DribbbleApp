package com.github.rcd27.dribbbleapp.shots.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
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
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView shotsRecyclerView;
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

        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);

        LayoutAnimationController animationController =
                AnimationUtils.loadLayoutAnimation(getContext(), R.anim.layout_animation_fall_down);

        shotsRecyclerView.setLayoutManager(linearLayoutManager);
        shotsRecyclerView.setAdapter(shotsRecyclerViewAdapter);
        shotsRecyclerView.setLayoutAnimation(animationController);

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

    @Override
    public void scrollToBottom() {
        linearLayoutManager.scrollToPositionWithOffset(0, 0);
        shotsRecyclerView.scheduleLayoutAnimation();
    }

    @Override
    public void showShotDetail(ShotsRecyclerViewAdapter.ShotViewHolder holder,
                               @NonNull ShotVisualObject shotVisualObject) {
        ShotDetailFragment shotDetailFragment = ShotDetailFragment.newInstance(shotVisualObject);
        shotDetailFragment.setSharedElementEnterTransition(new ShotDetailsTransition());
        shotDetailFragment.setEnterTransition(new Fade());
        setExitTransition(new Fade());
        shotDetailFragment.setSharedElementReturnTransition(new ShotDetailsTransition());

        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .addSharedElement(holder.imageView, "shotImage")
                .replace(R.id.shots_fragment_container, shotDetailFragment)
                .addToBackStack(null)
                .commit();

        //TODO FT2: запоминать позицию выбранного шота и скроллить к ней в onBackPressed
    }
}
