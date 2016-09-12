package com.webege.profause.bustransportsystem.view;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.webege.profause.bustransportsystem.R;
import com.webege.profause.bustransportsystem.adapter.TicketsAdapter;
import com.webege.profause.bustransportsystem.helper.Callbacks;
import com.webege.profause.bustransportsystem.helper.GetTickets;
import com.webege.profause.bustransportsystem.helper.Util;
import com.webege.profause.bustransportsystem.model.Destination;
import com.webege.profause.bustransportsystem.model.Ticket;

import java.util.ArrayList;

public class TicketsListActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, Callbacks.Click {

    TextView tvdestination;
    ProgressBar progressBar;
    ProgressDialog progressDoalog;
    RecyclerView ticketList;
    SwipeRefreshLayout refresh;

    LinearLayoutManager layoutManager;
    TicketsAdapter ticketsAdapter;
    Util util;
    GetTickets getTickets;
    Destination destination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent i = getIntent();
        if (i != null) {
            destination = i.getParcelableExtra("destination");
        }

        Initialise();
        if (savedInstanceState != null) {
            ArrayList<Ticket> l = savedInstanceState.getParcelableArrayList("tickets");
            ticketsAdapter.setTickets(l);
        } else {
            LoadTickets();
        }

    }

    private void LoadTickets() {
        if (util.isOnline()) {
            ProgressDialog();
            getTickets.Load(destination.getId() + "", new Callbacks.GetTicketsCallback() {
                @Override
                public void onSuccess(ArrayList<Ticket> tickets) {
                    ticketsAdapter.setTickets(tickets);
                    refresh.setRefreshing(false);
                    progressDoalog.dismiss();
                }

                @Override
                public void onError(String error) {
                    Toast.makeText(TicketsListActivity.this, error, Toast.LENGTH_SHORT).show();
                    refresh.setRefreshing(false);
                    progressDoalog.dismiss();

                }
            });
        } else {
            Toast.makeText(TicketsListActivity.this, "Network unavailable", Toast.LENGTH_SHORT).show();
            refresh.setRefreshing(false);
            progressDoalog.dismiss();
        }
    }

    private void Initialise() {
        util = new Util(this);
        ticketsAdapter = new TicketsAdapter(this);
        getTickets = new GetTickets(this);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        progressDoalog = new ProgressDialog(TicketsListActivity.this);

        tvdestination = (TextView) findViewById(R.id.destination);
        tvdestination.setText(destination.getName());
        ticketList = (RecyclerView) findViewById(R.id.ticketList);

        ticketList.setAdapter(ticketsAdapter);
        ticketList.setLayoutManager(layoutManager);
        ticketList.setHasFixedSize(true);
        ticketList.setRecycledViewPool(new RecyclerView.RecycledViewPool());

        refresh = (SwipeRefreshLayout) findViewById(R.id.refresh);
        refresh.setOnRefreshListener(this);
        ticketsAdapter.setClickListener(this);
    }

    @Override
    public void onRefresh() {
        ticketsAdapter.Clear();
        LoadTickets();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("tickets", ticketsAdapter.getTickets());
    }

    @Override
    public void onItemClick(View view, int position, Ticket ticket) {
        AlertDialog();
    }

    public void ProgressDialog(){
        progressDoalog.setIndeterminate(true);
        progressDoalog.setMessage("loading....");
       // progressDoalog.setTitle("ProgressDialog bar example");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.show();
    }

    public void AlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(TicketsListActivity.this);
        builder.setCancelable(true);
        builder.setTitle("Bus Transport System");
        builder.setMessage("Thanks for purchasing a ticket to "+destination.getName());
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void PurchaseTicket(int id){
        String url ="";
    }
}
