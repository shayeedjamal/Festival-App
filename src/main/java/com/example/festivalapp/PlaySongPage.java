package com.example.festivalapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.festivalapp.models.Song;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class PlaySongPage extends AppCompatActivity implements View.OnClickListener {

    private static final  int REQUEST_CODE_SPEECH_INPUT=1;

    private TextView songNameTxt;

    private MediaPlayer mediaPlayer;
    private  Button button;
    private Button play, pause, stop;
   private  int pausePosition;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song_page);

        button=findViewById(R.id.voiceButton);

  button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          speak();
      }
  });

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent a = new Intent(PlaySongPage.this, MainActivity.class);
                        mediaPlayer.stop();
                        startActivity(a);
                        break;
                    case R.id.navigation_create_lineup:
                        Intent b = new Intent(PlaySongPage.this, CreateYourLineUp.class);
                        mediaPlayer.stop();
                        startActivity(b);

                        break;
                    case R.id.navigation_artist:
                        Intent c = new Intent(PlaySongPage.this, Music.class);
                        mediaPlayer.stop();
                        startActivity(c);


                        break;
                    case R.id.news:
                        Intent d= new Intent(PlaySongPage.this, NewsMainPage.class);
                        mediaPlayer.stop();

                        startActivity(d);

                        break;
                }
                return false;
            }
        });


        play = (Button) findViewById(R.id.play);
        pause = (Button) findViewById(R.id.pause);
        stop = (Button) findViewById(R.id.stop);


        songNameTxt = findViewById(R.id.songName);


        validateReceiveValuesAndStartPlaying();


        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);
    }


    private void validateReceiveValuesAndStartPlaying() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }

        final String songData = getIntent().getStringExtra("song");
        final String songFile = getIntent().getStringExtra("media");


        songNameTxt.setText(songData);
        songNameTxt.setSelected(true);
        System.out.println(songFile);
        Uri uri = Uri.parse(songFile);
        System.out.println(uri);


        mediaPlayer = mediaPlayer.create(PlaySongPage.this, uri);
        mediaPlayer.start();

    }

    //makes an intent and opens the record speech api
    public  void speak(){
        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS,true);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"voer een opdracht in");
        //start intent

        try{
            startActivityForResult(intent,REQUEST_CODE_SPEECH_INPUT);

        }catch (Exception e){
            //if there was a error
            Toast.makeText(this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

        }



    }


    //recieves voice input and handle it
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case REQUEST_CODE_SPEECH_INPUT:{
                if(resultCode==RESULT_OK&& data!=null){
                    ArrayList<String>result=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    Toast.makeText(this, String.valueOf(result), Toast.LENGTH_SHORT).show();
                    if(result.contains("stop")||result.contains("eindig")||result.contains("klaar")){
                        mediaPlayer.stop();
                    }else if (result.contains("play")||result.contains("spelen")||result.contains("speel")
                            ||result.contains("start")){
                        if(mediaPlayer==null){
                            mediaPlayer.start();
                        } else if (!mediaPlayer.isPlaying()) {

                            mediaPlayer.seekTo(pausePosition);
                            mediaPlayer.start();

                        }

                    }else if (result.contains("pauze")||result.contains("pause")||result.contains("stil")||
                    result.contains("break")){
                        mediaPlayer.pause();

                    }
                   }
                    break;


                }

            }
        }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.play:
                if (mediaPlayer == null) {

                    mediaPlayer.start();
                } else if (!mediaPlayer.isPlaying()) {

                    mediaPlayer.seekTo(pausePosition);
                    mediaPlayer.start();
                }

                break;
            case R.id.pause:
                if (mediaPlayer != null) {
                    mediaPlayer.pause();
                    pausePosition = mediaPlayer.getCurrentPosition();
                }

                break;
            case R.id.stop:
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    Intent c = new Intent(PlaySongPage.this, Music.class);
                    mediaPlayer.stop();
                    startActivity(c);
                }
                break;
        }
    }
}

