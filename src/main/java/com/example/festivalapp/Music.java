package com.example.festivalapp;

import android.Manifest;
import android.content.Intent;
import android.os.Environment;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.festivalapp.adapters.MusicListAdapter;
import com.example.festivalapp.models.News;
import com.example.festivalapp.models.Song;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Music extends AppCompatActivity {

    private String[] itemsAll;
    private RecyclerView mSongsListRecyclerView;
    private List<Song> mSongsList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);


        mSongsListRecyclerView = findViewById(R.id.recyclerViewMusic);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent a = new Intent(Music.this, MainActivity.class);
                        startActivity(a);
                        break;
                    case R.id.navigation_create_lineup:
                        Intent b = new Intent(Music.this, CreateYourLineUp.class);
                        startActivity(b);

                        break;
                    case R.id.navigation_artist:


                        break;
                    case R.id.news:
                        Intent c = new Intent(Music.this, NewsMainPage.class);
                        startActivity(c);

                        break;
                }

                return false;
            }
        });

                appExternalStorageStoragePermission();
    }

    public void appExternalStorageStoragePermission() {
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        displayAudioSongsName();


                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();

    }

    public ArrayList<File> readOnlyAudioSongs(File file) {
        ArrayList<File> arrayList = new ArrayList<>();


        File[] allFiles = file.listFiles();
        for (File individualFile : allFiles) {
            if (individualFile.isDirectory() && !individualFile.isHidden()) {
                arrayList.addAll(readOnlyAudioSongs(individualFile));

            } else {
                if (individualFile.getName().endsWith(".mp3") ||
                        individualFile.getName().endsWith(".aac") || individualFile.getName().endsWith(".wav") ||
                        individualFile.getName().endsWith(".wma")) {
                    arrayList.add(individualFile);
                }
            }
        }
        return arrayList;
    }

    private void displayAudioSongsName() {
        final List<File> audioSongs = readOnlyAudioSongs(Environment.getExternalStorageDirectory());

        System.out.println(audioSongs);
        itemsAll = new String[audioSongs.size()];
        for (int songCounter = 0; songCounter < audioSongs.size(); songCounter++) {
            File file = audioSongs.get(songCounter);

            Song songs = new Song(file, file.getName());
            mSongsList.add(songs);

        }

        mSongsListRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mSongsListRecyclerView.setAdapter(new MusicListAdapter(mSongsList, new MusicListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Song song) {
                Intent a= new Intent(Music.this,PlaySongPage.class);
                System.out.println(song.getName());
                System.out.println(song.getSong().toString());
                a.putExtra("song", song.getName());
                a.putExtra("media",song.getSong().toString());
                startActivity(a);


            }
        }));


    }


}
