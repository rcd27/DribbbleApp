package rcd27.github.com.dribbbleapp.view;


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

import rcd27.github.com.dribbbleapp.R;
import rcd27.github.com.dribbbleapp.model.ShotVO;

public class ShotListAdapter extends ArrayAdapter<ShotVO> {
    public ShotListAdapter(@NonNull Context context) {
        super(context, R.layout.list_item_cardview);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ShotVO shot = getItem(position);
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
                .setText(shot.description);

        return convertView;
    }
}
