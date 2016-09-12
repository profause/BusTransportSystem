package com.webege.profause.bustransportsystem.helper;

import android.view.View;

import com.webege.profause.bustransportsystem.model.Destination;
import com.webege.profause.bustransportsystem.model.Ticket;

import java.util.ArrayList;

/**
 * Created by Emmanuel Mensah on 9/4/2016.
 */
public class Callbacks {

    public interface GetDestinationsCallback{
        void onSuccess(ArrayList<Destination> destinations);
        void onError(String error);
    }

    public interface ClickListener{
        void onItemClick(View view, int position, Destination destination);
    }

    public interface Click{
        void onItemClick(View view, int position, Ticket ticket);
    }

    public interface GetTicketsCallback{
        void onSuccess(ArrayList<Ticket> tickets);
        void onError(String error);
    }
}
