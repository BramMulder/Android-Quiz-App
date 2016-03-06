package com.brammulder.quizapp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Created by Bram on 15-6-2015.
 */
public class testClassFragment extends Fragment {

    private boolean state = true;
    private ImageButton btn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        btn = (ImageButton)view.findViewById(R.id.soundOnOff);

        //Check for change
        changeImage();

        btn.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View v) {
                        state = !state;
                        changeImage();
                    }
                }
        );
        return view;
    }

    /**
     * Changes sound-image
     */
    public void changeImage(){
        if (state == true) {
            btn.setImageResource(R.drawable.sound_on);
        } else if (state == false) {
            btn.setImageResource(R.drawable.sound_off);
        }
    }
}
