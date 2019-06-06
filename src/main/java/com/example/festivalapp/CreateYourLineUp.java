package com.example.festivalapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.festivalapp.adapters.CreateLineUpAdapter;
import com.example.festivalapp.databaseCreateYourLineUp.CreateLineUpDatabase;
import com.example.festivalapp.models.CreateModel;

import java.util.ArrayList;
import java.util.List;

public class CreateYourLineUp extends AppCompatActivity implements RecyclerView.OnItemTouchListener {
    private RecyclerView rvCreateYourLineUp;
    private List<CreateModel> mLineUpList;
    private CreateLineUpAdapter mLineUpAdapter;
  ;
    private CreateLineUpDatabase db;
    private GestureDetector mGestureDetector;
    private CreateYourLIneUpModel mLineUpModel;

    public static final String NEW_LineUp = "LineUp";
    public static final String NEW_UpdateLineUp = "updateLineUp";
    public static final int REQUESTCODE = 1;
    public static final int REQUESTCODE2 = 2;
    private int mModifyPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_your_line_up);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mLineUpModel = ViewModelProviders.of(this).get(CreateYourLIneUpModel.class);
        mLineUpModel.getLineUp().observe(this, new Observer<List<CreateModel>>() {
            @Override
            public void onChanged(@Nullable List<CreateModel>List) {
                mLineUpList = List;
                updateUI();
            }

        });
        mLineUpList= new ArrayList<>();




        rvCreateYourLineUp = findViewById(R.id.rvLineUp);
        rvCreateYourLineUp.setLayoutManager(new LinearLayoutManager(this));
        rvCreateYourLineUp.setAdapter(new CreateLineUpAdapter(mLineUpList));
        rvCreateYourLineUp.setHasFixedSize(true);

        rvCreateYourLineUp.addOnItemTouchListener(this);
        mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                        return false;
                    }

                    //Called when a user swipes left or right on a ViewHolder
                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {

                        //Get the index corresponding to the selected position
                        int position = (viewHolder.getAdapterPosition());

                        mLineUpModel.delete(mLineUpList.get(position));
                        mLineUpList.remove(position);
                        mLineUpAdapter.notifyItemRemoved(position);
                        updateUI();
                    }
                };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(rvCreateYourLineUp);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        Intent a = new Intent(CreateYourLineUp.this, MainActivity.class);
                        startActivity(a);
                        break;
                    case R.id.navigation_create_lineup:

                        break;
                    case R.id.navigation_artist:
                        Intent b = new Intent(CreateYourLineUp.this, Music.class);
                        startActivity(b);

                        break;
                    case R.id.news:
                        Intent d= new Intent(CreateYourLineUp.this, NewsMainPage.class);
                        startActivity(d);

                        break;
                }
                return false;
            }
        });


        db = CreateLineUpDatabase.getDatabase(this);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateYourLineUp.this, AddLineUp.class);
                startActivityForResult(intent, REQUESTCODE);
                System.out.println(REQUESTCODE);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUESTCODE) {
            if (resultCode == RESULT_OK) {
                CreateModel addLineUp = data.getParcelableExtra(CreateYourLineUp.NEW_LineUp);

                mLineUpModel.insert(addLineUp);
                Toast.makeText(this, R.string.lineup_saved, Toast.LENGTH_SHORT).show();
            }else if (resultCode!=RESULT_OK){
                Toast.makeText(this, R.string.lineup_not_saved, Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == REQUESTCODE2) {
            if (resultCode == RESULT_OK) {
                CreateModel update= data.getParcelableExtra(CreateYourLineUp.NEW_UpdateLineUp);
                mLineUpModel.update(update);
                Toast.makeText(this, R.string.lineup_saved, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void updateUI() {
        if (mLineUpAdapter == null) {
            mLineUpAdapter = new CreateLineUpAdapter(mLineUpList);
            rvCreateYourLineUp.setAdapter(mLineUpAdapter);
        } else {
            mLineUpAdapter.swapList(mLineUpList);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

        int mAdapterPosition = recyclerView.getChildAdapterPosition(child);

        if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {

            Intent intent = new Intent(CreateYourLineUp.this,UpdateLineUp.class);
            mModifyPosition = mAdapterPosition;
            intent.putExtra(CreateYourLineUp.NEW_UpdateLineUp,  mLineUpList.get(mAdapterPosition));
            startActivityForResult(intent, REQUESTCODE2);

        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_delete_item) {
            mLineUpModel.deleteAllLineUps();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }
}