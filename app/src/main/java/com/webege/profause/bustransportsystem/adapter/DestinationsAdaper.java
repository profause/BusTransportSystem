package com.webege.profause.bustransportsystem.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.webege.profause.bustransportsystem.R;
import com.webege.profause.bustransportsystem.helper.Callbacks;
import com.webege.profause.bustransportsystem.model.Destination;

import java.util.ArrayList;

/**
 * Created by Emmanuel Mensah on 9/4/2016.
 */
public class DestinationsAdaper extends RecyclerView.Adapter<DestinationsAdaper.DestinationsViewHolder> {

    Context context;
    LayoutInflater inflater;
    View view;
    ArrayList<Destination> destinations;
    Callbacks.ClickListener clickListener;

    public DestinationsAdaper(Context context) {
        this.context = context;
        this.inflater=LayoutInflater.from(context);
    }

    @Override
    public DestinationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = inflater.inflate(R.layout.destination_list_row, parent, false);
        DestinationsViewHolder holder = new DestinationsViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(DestinationsViewHolder holder, int position) {
        Destination current = destinations.get(position);
        holder.destination.setText(current.getName());
    }

    @Override
    public int getItemCount() {
        return destinations == null ? 0 : destinations.size();
    }

    public void Clear(){
        destinations=new ArrayList<>();
        notifyDataSetChanged();
    }

    public ArrayList<Destination> getDestinations() {
        return destinations;
    }

    public void setDestinations(ArrayList<Destination> destinations) {
        this.destinations = destinations;
        notifyDataSetChanged();
    }

    public void setClickListener(Callbacks.ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class DestinationsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView destination;

        public DestinationsViewHolder(View itemView) {
            super(itemView);
            destination = (TextView) itemView.findViewById(R.id.destination);

            destination.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) {
                clickListener.onItemClick(view, getAdapterPosition(),
                        new Destination(destinations.get(getAdapterPosition()).getId(),
                                destinations.get(getAdapterPosition()).getName()));
            }
        }
    }
}
