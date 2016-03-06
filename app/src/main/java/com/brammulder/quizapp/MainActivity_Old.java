//package com.brammulder.quizapp;
//
//import android.content.Intent;
//import android.content.pm.ActivityInfo;
//import android.os.Bundle;
//import android.support.v7.app.ActionBarActivity;
//import android.util.Log;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.Button;
//
///**
// * Created by Bram on 30-5-2015.
// */
//public class MainActivity_Old {
//
//    public class MainMenu extends ActionBarActivity {
//
//        String TAG = MainMenu.class.getSimpleName();
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//            setContentView(R.layout.acitivity_main_menu);
//            getSupportActionBar().hide();
//            addListenerOnButtonFood();
//            addListenerOnButtonSettings();
//        }
//
//        @Override
//        protected void onStop() {
//            super.onStop();
//        }
//
//        @Override
//        protected void onDestroy() {
//            super.onDestroy();
//        }
//
//        @Override
//        protected void onPause() {
//            super.onPause();
//        }
//
//        @Override
//        protected void onResume() {
//            super.onResume();
//        }
//
//        @Override
//        protected void onStart() {
//            super.onStart();
//            Log.d(TAG, "Function onStart is clicked");
//        }
//
////    @Override
////    protected void onCreate(){
////        super.onCreate();
////    }
//
//        @Override
//        public boolean onCreateOptionsMenu(Menu menu) {
//            // Inflate the menu; this adds items to the action bar if it is present.
//            getMenuInflater().inflate(R.menu.menu_main, menu);
//            return true;
//        }
//
//        public boolean addListenerOnButtonFood(MenuItem item) {
//            final Button button = (Button) findViewById(R.id.food);
//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(getApplicationContext(), Questions.class);
//                    startActivity(intent);
//                }
//            });
//            Intent i;
//            switch (item.getItemId()) {
//                case R.id.settings:
//                    i = new Intent(getApplicationContext(),
//                            Settings.class);
//                    startActivity(i);
//                    return true;
////            case R.id.leaderboards:
////                i = new Intent(getApplicationContext(),
////                        //TODO .class);
////                        startActivity(i);
////                return true;
//            }
//            if((item.getItemId() == R.id.history) || (item.getItemId() == R.id.games) || (item.getItemId() == R.id.food) || (item.getItemId() == R.id.science)){
//                i = new Intent(getApplicationContext(),
//                        Questions.class);
//                switch(item.getItemId()) {
//                    case R.id.food:
//                        i.putExtra("category", "food");
//                    case R.id.history:
//                        i.putExtra("category", "history");
//                    case R.id.science:
//                        i.putExtra("category", "science");
//                    case R.id.games:
//                        i.putExtra("category", "games");
//                }
//                startActivity(i);
//            }
//            return super.onOptionsItemSelected(item);
//        }
//
//    }
//
//    public void addListenerOnButtonSettings() {
//        final Button button = (Button) findViewById(R.id.settings);
//        button.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick (View v){
//                Intent intent = new Intent(getApplicationContext(), Settings.class);
//                startActivity(intent);
//            }
//        });
//    }
//
//
//
//}
