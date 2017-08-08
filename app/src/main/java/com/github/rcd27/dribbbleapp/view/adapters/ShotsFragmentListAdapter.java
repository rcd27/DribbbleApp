package com.github.rcd27.dribbbleapp.view.adapters;


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

import com.github.rcd27.dribbbleapp.DribbbleApplication;
import com.github.rcd27.dribbbleapp.R;
import com.github.rcd27.dribbbleapp.model.objects.ShotVisualObject;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

public class ShotsFragmentListAdapter extends ArrayAdapter<ShotVisualObject> {

    @Inject
    Picasso picasso;

    public ShotsFragmentListAdapter(@NonNull Context context) {
        super(context, R.layout.list_item_cardview);
        DribbbleApplication.getInstance().getAppComponent().inject(this);
    }

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
