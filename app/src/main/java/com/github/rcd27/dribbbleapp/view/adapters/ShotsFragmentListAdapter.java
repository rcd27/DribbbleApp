package com.github.rcd27.dribbbleapp.view.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import com.github.rcd27.dribbbleapp.R;
import com.github.rcd27.dribbbleapp.model.objects.ShotVisualObject;

public class ShotsFragmentListAdapter extends ArrayAdapter<ShotVisualObject> {
    public ShotsFragmentListAdapter(@NonNull Context context) {
        super(context, R.layout.list_item_cardview);
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
        //TODO организовать подгрузку картинок в модели/в IO thread'e
        /*---WARNING!!!---WEB ACCESS FROM UI THREAD!!!---WARNING!!!---WEB ACCESS FROM UI THREAD!!!---*/
        Picasso
                .with(getContext())
                .load(shot.imageUrl)
                .into((ImageView) convertView.findViewById(R.id.card_view_image_view));
        /*---WARNING!!!---WEB ACCESS FROM UI THREAD!!!---WARNING!!!---WEB ACCESS FROM UI THREAD!!!---*/
        ((TextView) convertView.findViewById(R.id.card_view_text_title))
                .setText(shot.title);
        ((TextView) convertView.findViewById(R.id.card_view_text_description))
                .setText(prepareDescription(shot.description));

        return convertView;
    }

    private String prepareDescription(@NonNull String stringToPrepare) {
        String result = stringToPrepare.replace("<p>", "").replace("</p>", "");
        if (result.length() >= 96) {
            return result.substring(0, 93).concat("...");
        }
        return result;
    }
}
