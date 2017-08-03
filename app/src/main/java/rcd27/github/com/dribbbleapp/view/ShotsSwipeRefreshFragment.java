package rcd27.github.com.dribbbleapp.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import rcd27.github.com.dribbbleapp.R;
import rcd27.github.com.dribbbleapp.model.ShotVO;

public class ShotsSwipeRefreshFragment extends Fragment implements rcd27.github.com.dribbbleapp.view.View {
    private static final int LIST_ITEM_COUNT = 50;

    private SwipeRefreshLayout swipeRefreshLayout;

    private ListView shotsListView;
    private ShotListAdapter listAdapter;

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

        listAdapter = new ShotListAdapter(getContext());
        shotsListView.setAdapter(listAdapter);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //TODO пробросить через презентер
                //presenter.getListAndUpdate
                update();
            }
        });

        update();
    }

    //TODO след.шаг: принимать в качестве вход. элемента List<ShotVO>
    @Override
    public void update() {
        if (!swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(true);
        }
        Log.d("eventLog", "update: refreshInitiated");
        listAdapter.clear();
        listAdapter.add(new ShotVO("https://cdn.dribbble.com/users/371094/screenshots/3697595/koi.jpg",
                "Koi",
                "Против течения"));
        swipeRefreshLayout.setRefreshing(false);
    }
}
