package com.example.festivalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private Button mNewsBttn, mCreateLineUpBttn,mMusicBttn;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    break;

                case R.id.navigation_create_lineup:
                    Intent intent= new Intent(MainActivity.this,CreateYourLineUp.class);
                    startActivity(intent);
                   break;
                case R.id.navigation_artist:
                    Intent b= new Intent(MainActivity.this, Music.class);
                    startActivity(b);

                    break;

                case R.id.news:
                    Intent c= new Intent(MainActivity.this, NewsMainPage.class);
                    startActivity(c);

                    break;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNewsBttn=findViewById(R.id.newsBttn);
        mCreateLineUpBttn=findViewById(R.id.createLineUpBttn);
        mMusicBttn=findViewById(R.id.musicBttn);

        mNewsBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(MainActivity.this,NewsMainPage.class);
                startActivity(i);
            }
        });

        mCreateLineUpBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(MainActivity.this,CreateYourLineUp.class);
                startActivity(i);
            }
        });


        mMusicBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(MainActivity.this,Music.class);
                startActivity(i);
            }
        });




        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
