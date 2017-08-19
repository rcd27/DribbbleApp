package com.github.rcd27.dribbbleapp.shots.view;


import android.support.annotation.NonNull;
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
        card.setOnClickListener(presenter::onCardClicked);

        return new ShotViewHolder(card);
    }

    @Override
    public void onBindViewHolder(ShotViewHolder holder, int position) {
        ShotVisualObject currentShot = shots.get(position);
        picasso.load(currentShot.imageUrl)
                .into(holder.imageView);
        holder.title.setText(currentShot.title);
        holder.description.setText(Html.fromHtml(currentShot.description));
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
