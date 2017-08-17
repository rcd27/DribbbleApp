package com.github.rcd27.dribbbleapp.shots.view;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.rcd27.dribbbleapp.R;
import com.github.rcd27.dribbbleapp.shots.data.ShotVisualObject;
import com.squareup.picasso.Picasso;

public class ShotsListAdapter extends ArrayAdapter<ShotVisualObject> {

    private final Picasso picasso;

    public ShotsListAdapter(@NonNull Context context, Picasso picasso) {
        super(context, R.layout.list_item_cardview);
        this.picasso = picasso;
    }

    //TODO FT1: будем добавлять анимацию в наш ListView
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ShotVisualObject shot = getItem(position);
        assert shot != null;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_item_cardview, parent, false);
        }

        picasso.load(shot.imageUrl)
                .into((ImageView) convertView.findViewById(R.id.card_view_image_view));
        ((TextView) convertView.findViewById(R.id.card_view_text_title))
                .setText(shot.title);
        ((TextView) convertView.findViewById(R.id.card_view_text_description))
                .setText(Html.fromHtml(shot.description));

        return convertView;
    }
}
