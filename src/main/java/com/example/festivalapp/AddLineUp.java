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
import android.widget.Toast;

import com.example.festivalapp.models.CreateModel;

public class AddLineUp extends AppCompatActivity {
    private EditText artistOne;
    private EditText artistTwo;
    private EditText artistThree;
    private EditText artistFour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_line_up);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        artistOne = findViewById(R.id.ArtistOneAdd);
        artistTwo = findViewById(R.id.ArtistTwoAdd);
        artistThree = findViewById(R.id.ArtistThreeAdd);
        artistFour = findViewById(R.id.ArtistFourAdd);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent a = new Intent(AddLineUp.this, MainActivity.class);
                        startActivity(a);
                        break;
                    case R.id.navigation_create_lineup:
                        Intent b = new Intent(AddLineUp.this, CreateYourLineUp.class);
                        startActivity(b);
                        break;
                    case R.id.navigation_artist:
                        Intent c = new Intent(AddLineUp.this, Music.class);
                        startActivity(c);

                        break;
                    case R.id.news:
                        Intent d= new Intent(AddLineUp.this, NewsMainPage.class);
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
                alterLineUp();


            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home);
        finish();
        return super.onOptionsItemSelected(item);
    }

    public void alterLineUp() {
        String artistOneText = artistOne.getText().toString();
        String artistTwoText = artistTwo.getText().toString();
        String artistThreeText = artistThree.getText().toString();
        String artistFourText = artistFour.getText().toString();

        if (artistOneText.isEmpty() || artistTwoText.isEmpty() || artistThreeText.isEmpty() || artistFourText.isEmpty()) {
            Toast.makeText(this, R.string.create_line_up, Toast.LENGTH_SHORT).show();
        } else {


            CreateModel data = new CreateModel(artistOneText, artistTwoText, artistThreeText, artistFourText);

            Intent intent = new Intent();
            intent.putExtra(CreateYourLineUp.NEW_LineUp, data);


            System.out.println(artistOne.getText());
            setResult(Activity.RESULT_OK, intent);

            finish();

        }
    }
}
