package com.webege.profause.bustransportsystem.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.webege.profause.bustransportsystem.R;
import com.webege.profause.bustransportsystem.adapter.DestinationsAdaper;
import com.webege.profause.bustransportsystem.helper.Callbacks;
import com.webege.profause.bustransportsystem.helper.GetDestinations;
import com.webege.profause.bustransportsystem.helper.SimpleDividerItemDecoration;
import com.webege.profause.bustransportsystem.helper.Util;
import com.webege.profause.bustransportsystem.model.Destination;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Callbacks.ClickListener, View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    RecyclerView destinationList;
    LinearLayoutManager layoutManager;

    DrawerLayout drawer;
    FloatingActionButton fab;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    SwipeRefreshLayout refresh;

    DestinationsAdaper destinationsAdapter;
    Util util;
    GetDestinations getDestinations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Initialise();
        if (savedInstanceState != null) {
            ArrayList<Destination> d = savedInstanceState.getParcelableArrayList("destinations");
            destinationsAdapter.setDestinations(d);
        } else {
            LoadDestinations();
        }

    }

    private void LoadDestinations() {
        if (util.isOnline()) {
        getDestinations.Load(new Callbacks.GetDestinationsCallback() {
            @Override
            public void onSuccess(ArrayList<Destination> destinations) {
                if (destinations != null) {
                    destinationsAdapter.setDestinations(destinations);
                    refresh.setRefreshing(false);
                }
            }

            @Override
            public void onError(String error) {
                Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
                refresh.setRefreshing(false);
            }
        });
        } else {
            Toast.makeText(MainActivity.this, "Network unavailable", Toast.LENGTH_SHORT).show();
            refresh.setRefreshing(false);
        }
    }

    public void Initialise() {
        util = new Util(this);
        destinationsAdapter = new DestinationsAdaper(this);
        getDestinations = new GetDestinations(this);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        refresh = (SwipeRefreshLayout) findViewById(R.id.refresh);
        refresh.setOnRefreshListener(this);
        destinationList = (RecyclerView) findViewById(R.id.destinationList);
        destinationList.setAdapter(destinationsAdapter);
        destinationList.setLayoutManager(layoutManager);
        destinationList.setHasFixedSize(true);
        destinationList.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        destinationList.addItemDecoration(new SimpleDividerItemDecoration(this));

        destinationsAdapter.setClickListener(this);
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
        getMenuInflater().inflate(R.menu.main, menu);
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemClick(View view, int position, Destination destination) {
        Intent i = new Intent(this,TicketsListActivity.class)
            .putExtra("destination",destination);
        startActivity(i);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("destinations", destinationsAdapter.getDestinations());

    }

    @Override
    public void onRefresh() {
        destinationsAdapter.Clear();
        LoadDestinations();
    }
}
