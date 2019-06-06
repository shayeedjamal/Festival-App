package com.example.festivalapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.festivalapp.models.CreateModel;

public class UpdateLineUp extends AppCompatActivity {
    private EditText artistOne;
    private EditText artistTwo;
    private EditText artistThree;
    private EditText artistFour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_line_up);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        artistOne = findViewById(R.id.ArtistOneAlter);
        artistTwo = findViewById(R.id.ArtistTwoAlter);
        artistThree = findViewById(R.id.ArtistThreeAlter);
        artistFour = findViewById(R.id.ArtistFourAlter);

    final CreateModel lineup = getIntent().getParcelableExtra(CreateYourLineUp.NEW_UpdateLineUp);
        artistOne.setText(lineup.getArtistOne());
        artistTwo.setText(lineup.getArtistTwo());
        artistThree.setText(lineup.getArtistThree());
        artistFour.setText(lineup.getArtistFour());

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent a = new Intent(UpdateLineUp.this, MainActivity.class);
                        startActivity(a);
                        break;
                    case R.id.navigation_create_lineup:
                        Intent b = new Intent(UpdateLineUp.this, CreateYourLineUp.class);
                        startActivity(b);
                        break;
                    case R.id.navigation_artist:
                        Intent c = new Intent(UpdateLineUp.this, Music.class);
                        startActivity(c);

                        break;
                    case R.id.news:
                        Intent d= new Intent(UpdateLineUp.this, NewsMainPage.class);
                        startActivity(d);

                        break;
                }
                return false;
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String artistOneText = artistOne.getText().toString();
                String artistTwoText = artistTwo.getText().toString();
                String artistThreeText = artistThree.getText().toString();
                String artistFourText = artistFour.getText().toString();

                lineup.setArtistOne(artistOneText);
                lineup.setArtistTwo(artistTwoText);
                lineup.setArtistThree(artistThreeText);
                lineup.setArtistFour(artistFourText);

                Intent intent = new Intent();
                intent.putExtra(CreateYourLineUp.NEW_UpdateLineUp, lineup);


                System.out.println(artistOne.getText());
                setResult(Activity.RESULT_OK, intent);

                finish();

            }
        });
    }

}
