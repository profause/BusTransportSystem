package com.webege.profause.bustransportsystem.model;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmObject;

/**
 * Created by Emmanuel Mensah on 9/5/2016.
 */
public class Ticket extends RealmObject implements Parcelable {
    int id;
    String destination;
    String serailNumber;
    String created;
    String departure;

    protected Ticket(Parcel in) {
        id = in.readInt();
        destination = in.readString();
        serailNumber = in.readString();
        created = in.readString();
        departure = in.readString();
    }

    public static final Creator<Ticket> CREATOR = new Creator<Ticket>() {
        @Override
        public Ticket createFromParcel(Parcel in) {
            return new Ticket(in);
        }

        @Override
        public Ticket[] newArray(int size) {
            return new Ticket[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSerailNumber() {
        return serailNumber;
    }

    public void setSerailNumber(String serailNumber) {
        this.serailNumber = serailNumber;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Ticket() {
    }

    public Ticket(int id, String destination, String serailNumber, String created, String departure) {
        this.id = id;
        this.destination = destination;
        this.serailNumber = serailNumber;
        this.created = created;
        this.departure = departure;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(destination);
        parcel.writeString(serailNumber);
        parcel.writeString(created);
        parcel.writeString(departure);
    }
}
