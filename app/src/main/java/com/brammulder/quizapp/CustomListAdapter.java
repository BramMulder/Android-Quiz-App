package com.brammulder.quizapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Bram on 12-6-2015.
 */
public class CustomListAdapter extends ArrayAdapter<String> {

    /**
     * Adapter which populates the ListView in the Leaderboard class
     * @param context
     * @param users     Arraylist with usernames from the database
     */
    public CustomListAdapter(Context context, ArrayList users) {
        super(context, R.layout.list_layout ,users);
    }

    /**
     * Adapter which populates the ListView in the Leaderboard class
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layout_inflater = LayoutInflater.from(getContext());
        View customView = layout_inflater.inflate(R.layout.list_layout, parent, false);

        String singleUserName = getItem(position);
        TextView userName = (TextView) customView.findViewById(R.id.userName);
        ImageView userImage = (ImageView) customView.findViewById(R.id.userPicture);

        userName.setText(singleUserName);
        userImage.setImageResource(R.drawable.jb_pp);
        return customView;
    }

}
