package com.webege.profause.bustransportsystem.helper;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.webege.profause.bustransportsystem.model.Destination;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Emmanuel Mensah on 9/4/2016.
 */
public class GetDestinations {
    String url = "http://10.0.3.2/BusTransportSystem/api/destinations.json";
    Context context;
    Callbacks.GetDestinationsCallback getDestinationsCallback;
    ArrayList<Destination> destinations=new ArrayList<>();

    public GetDestinations(Context context) {
        this.context = context;
    }

    public void Load(final Callbacks.GetDestinationsCallback getDestinationsCallback) {
        this.getDestinationsCallback = getDestinationsCallback;

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.trim() != null) {
                    try {
                        JSONObject jObject = new JSONObject(response.trim());
                        JSONArray jsonArray=jObject.getJSONArray("destinations");
                        for(int i=0;i<jsonArray.length();i++){
                            int id=jsonArray.getJSONObject(i).getInt("id");
                            String name=jsonArray.getJSONObject(i).getString("name");

                            destinations.add(new Destination(id,name));
                        }

                        if(getDestinationsCallback!=null){
                            getDestinationsCallback.onSuccess(destinations);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (getDestinationsCallback != null) {
                    try {
                        Log.d("error",error.getMessage());
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                    getDestinationsCallback.onError("An error occured try again...");
                }
            }
        });


        VolleySingleton.getInstance(context).addToRequestQueue(request);
    }
}
