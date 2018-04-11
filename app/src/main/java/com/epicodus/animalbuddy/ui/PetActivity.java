package com.epicodus.animalbuddy.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.epicodus.animalbuddy.R;
import com.epicodus.animalbuddy.models.Pet;
import com.epicodus.animalbuddy.services.PetService;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class PetActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static final String TAG = PetActivity.class.getSimpleName();

    public ArrayList<Pet> pets = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        getPets(location);
    }

    private void getPets(String location) {
        final PetService petService = new PetService();
        petService.findPets(location, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                try {
//
//                    if (response.isSuccessful()) {
////                        Log.v(TAG, jsonData);
//                        pets = petService.processResults(response);
//
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                pets = petService.processResults(response);

                PetActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        String[] petNames = new String[pets.size()];
                        for (int i = 0; i < petNames.length; i++) {
                            petNames[i] = pets.get(i).getName();
                        }

                        ArrayAdapter adapter = new ArrayAdapter(PetActivity.this,
                                android.R.layout.simple_list_item_1, petNames);

                        for (Pet pet : pets) {
                            Log.d(TAG, "Name: " + pet.getName());
                            Log.d(TAG, "Age: " + pet.getAge());
                            Log.d(TAG, "ImageUrl: " + pet.getImageUrl());
                        }

                    }
                });
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pet, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_about) {

            Intent intent = new Intent(PetActivity.this, AboutActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_search) {

            Intent intent = new Intent(PetActivity.this, SearchActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_saved) {


        } else if (id == R.id.nav_email) {


        } else if (id == R.id.nav_shelter) {

            Intent intent = new Intent(PetActivity.this, ShelterSearchActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_random) {

            Intent intent = new Intent(PetActivity.this, RandomSearchActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
