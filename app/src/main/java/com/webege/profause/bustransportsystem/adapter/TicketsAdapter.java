package com.webege.profause.bustransportsystem.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.webege.profause.bustransportsystem.R;
import com.webege.profause.bustransportsystem.helper.Callbacks;
import com.webege.profause.bustransportsystem.model.Ticket;

import java.util.ArrayList;

/**
 * Created by Emmanuel Mensah on 9/5/2016.
 */
public class TicketsAdapter extends RecyclerView.Adapter<TicketsAdapter.TicketListViewHolder> {

    Context context;
    LayoutInflater inflater;
    View view;
    ArrayList<Ticket> tickets;
    Callbacks.Click clickListener;

    public TicketsAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public TicketListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = inflater.inflate(R.layout.ticket_list_row, parent, false);
        TicketListViewHolder holder = new TicketListViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(TicketListViewHolder holder, int position) {
        Ticket current = tickets.get(position);
        holder.destination.setText("Destination : "+current.getDestination());
        holder.departureFrom.setText("From : "+current.getCreated());
        holder.departureTo.setText("To : "+current.getDeparture());
        holder.serialnumber.setText("Serial Number : "+current.getSerailNumber());
    }

    @Override
    public int getItemCount() {
        return tickets == null ? 0 : tickets.size();
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void setClickListener(Callbacks.Click clickListener) {
        this.clickListener = clickListener;
    }

    public void Clear() {
        tickets=null;
        notifyDataSetChanged();
    }

    class TicketListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView destination;
        TextView departureFrom;
        TextView departureTo;
        TextView serialnumber;
        TextView btnTicket;

        public TicketListViewHolder(View itemView) {
            super(itemView);

            destination = (TextView) itemView.findViewById(R.id.destination);
            departureFrom = (TextView) itemView.findViewById(R.id.departureFrom);
            departureTo = (TextView) itemView.findViewById(R.id.departureTo);
            serialnumber = (TextView) itemView.findViewById(R.id.serialnumber);
            btnTicket = (TextView) itemView.findViewById(R.id.btnTicket);

            btnTicket.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if(clickListener!=null){
                clickListener.onItemClick(view,getAdapterPosition(),
                    new Ticket(
                        tickets.get(getAdapterPosition()).getId(),
                        tickets.get(getAdapterPosition()).getDestination(),
                        tickets.get(getAdapterPosition()).getSerailNumber(),
                        tickets.get(getAdapterPosition()).getCreated(),
                        tickets.get(getAdapterPosition()).getDeparture()
                    ) );
            }
        }
    }
}
