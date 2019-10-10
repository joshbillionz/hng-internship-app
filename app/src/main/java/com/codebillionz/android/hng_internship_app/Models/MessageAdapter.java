package com.codebillionz.android.hng_internship_app.Models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.codebillionz.android.hng_internship_app.R;

import java.util.List;

/**
 * Created by Erandkings
 */

public class MessageAdapter extends ArrayAdapter<Message> {

    public MessageAdapter(@NonNull Context context, int resource, @NonNull List<Message> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        return super.getView(position, convertView, parent);
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Message message = getItem(position);

        TextView textView = (TextView) listItemView.findViewById(R.id.first_text);
        textView.setText(message.getUser1());

        TextView textView1 = (TextView) listItemView.findViewById(R.id.who_sent);
        textView1.setText(message.getUser2());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.chat_img);
        if (message.hasImage()) {
            imageView.setImageResource(message.getPhotoUrl());
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }

        return listItemView;
    }
}
