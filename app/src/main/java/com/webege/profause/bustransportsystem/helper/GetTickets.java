package com.webege.profause.bustransportsystem.helper;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.webege.profause.bustransportsystem.model.Ticket;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Emmanuel Mensah on 9/6/2016.
 */
public class GetTickets {

    Context context;
    Callbacks.GetTicketsCallback getTicketsCallback;
    ArrayList<Ticket> tickets = new ArrayList<>();

    public GetTickets(Context context) {
        this.context = context;
    }

    public void Load(final String id, final Callbacks.GetTicketsCallback getTicketsCallback) {
        this.getTicketsCallback = getTicketsCallback;
        String url = "http://10.0.3.2/BusTransportSystem/api/destinations/" + id + ".json";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim() != null) {
                    try {
                        JSONObject jObject = new JSONObject(response.trim());
                        JSONObject jsonObject = jObject.getJSONObject("destination");
                        JSONArray jsonArray = jsonObject.getJSONArray("tickets");
                        String destination = jsonObject.getString("name");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            int id = jsonArray.getJSONObject(i).getInt("id");
                            String created = jsonArray.getJSONObject(i).getString("created");
                            String validity = jsonArray.getJSONObject(i).getString("validity");
                            String serial_number = jsonArray.getJSONObject(i).getString("serial_number");

                            tickets.add(new Ticket(id, destination, serial_number, created, validity));
                        }

                        if (getTicketsCallback != null) {
                            getTicketsCallback.onSuccess(tickets);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.d("error",e.getMessage());
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (getTicketsCallback != null) {
                    try {
                        Log.d("error", error.getMessage());
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                    getTicketsCallback.onError("An error occured try again...");
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                try {
                    params.put("details", id);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                return params;
            }
        };

        VolleySingleton.getInstance(context).addToRequestQueue(request);
    }

}
