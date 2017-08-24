package com.github.rcd27.dribbbleapp.shots.view;


import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.rcd27.dribbbleapp.R;
import com.github.rcd27.dribbbleapp.shots.ShotsContract;
import com.github.rcd27.dribbbleapp.shots.data.ShotVisualObject;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ShotsRecyclerViewAdapter
        extends RecyclerView.Adapter<ShotsRecyclerViewAdapter.ShotViewHolder> {

    private final Picasso picasso;
    private List<ShotVisualObject> shots;
    private final ShotsContract.Presenter presenter;

    public ShotsRecyclerViewAdapter(Picasso picasso,
                                    List<ShotVisualObject> shots,
                                    ShotsContract.Presenter presenter) {
        this.picasso = picasso;
        this.shots = shots;
        this.presenter = presenter;
    }

    @Override
    public ShotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View card = inflater.inflate(R.layout.list_item_cardview, parent, false);

        return new ShotViewHolder(card);
    }

    @Override
    public void onBindViewHolder(final ShotViewHolder viewHolder, int position) {
        ShotVisualObject currentShot = shots.get(position);
        viewHolder.id = currentShot.id;
        picasso.load(currentShot.imageUrl)
                .into(viewHolder.imageView);
        viewHolder.title.setText(currentShot.title);
        viewHolder.description.setText(Html.fromHtml(currentShot.description));

        ViewCompat.setTransitionName(viewHolder.imageView,String.valueOf(position)+"_image");
        viewHolder.imageView.setOnClickListener(view -> {
            presenter.onCardClicked(viewHolder, shots.get(position).id);
        });
    }

    @Override
    public int getItemCount() {
        return shots.size();
    }

    public void setShots(@NonNull List<ShotVisualObject> newShots) {
        this.shots = newShots;
        notifyDataSetChanged();
    }

    public class ShotViewHolder extends RecyclerView.ViewHolder {
        int id;

        ImageView imageView;
        TextView title;
        TextView description;

        public ShotViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.card_view_image_view);
            title = (TextView) itemView.findViewById(R.id.card_view_text_title);
            description = (TextView) itemView.findViewById(R.id.card_view_text_description);
        }
    }
}
