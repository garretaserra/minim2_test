package com.sgs.mnim2_test;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class UserArrayAdapter extends ArrayAdapter<User> {
    public UserArrayAdapter(@NonNull Context context, List<User> resource) {
        super(context, 0,resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        User u = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.user_layout, parent, false);
        }
        Picasso.with(super.getContext()).load(u.getAvatar_url()).into((ImageView) convertView.findViewById(R.id.avatar_image));
        TextView et = (TextView)convertView.findViewById(R.id.username_txt);
        et.setText(u.getLogin());
        return convertView;
    }
}
