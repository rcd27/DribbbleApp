package rcd27.github.com.dribbbleapp.view;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import rcd27.github.com.dribbbleapp.R;
import rcd27.github.com.dribbbleapp.model.Cheeses;

public class ShotsSwipeRefreshFragment extends Fragment implements rcd27.github.com.dribbbleapp.view.View {
    private static final int LIST_ITEM_COUNT = 50;

    private SwipeRefreshLayout swipeRefreshLayout;

    private ListView shotsListView;
    private ArrayAdapter<String> listAdapter;

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

        //TODO сверстать элемент списка
        listAdapter = new ArrayAdapter<>(
                getActivity(),
                R.layout.list_item_cardview,
                R.id.card_view_text_description,
                Cheeses.randomList(LIST_ITEM_COUNT)
        );
        shotsListView.setAdapter(listAdapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //TODO пробросить через презентер
                //presenter.getListAndUpdate
                update();
            }
        });
    }

    //TODO след.шаг: принимать в качестве вход. элемента List<Shot>
    @Override
    public void update() {
        Log.d("eventLog", "update: refreshInitiated");
        listAdapter.clear();
        for (String cheese : Cheeses.randomList(LIST_ITEM_COUNT)) {
            listAdapter.add(cheese);
        }
        swipeRefreshLayout.setRefreshing(false);
    }
}
