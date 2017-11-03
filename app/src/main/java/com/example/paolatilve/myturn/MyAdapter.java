package com.example.paolatilve.myturn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import Model.TicketInformation;

/**
 * Created by paolatilve on 10/12/17.
 */

public class MyAdapter extends BaseAdapter {

    private List<TicketInformation> entriesList;
    private List<TicketInformation> filterList;
    private int idView;
    private Context myContext;

    public MyAdapter(List<TicketInformation> entriesList, int idView, Context myContext) {
        super();
        this.entriesList = entriesList;
        this.idView = idView;
        this.myContext = myContext;

        setFilterList();
    }

    public void setFilterList()
    {
        if(filterList == null){
            filterList = new ArrayList<TicketInformation>();
            filterList.addAll(entriesList);
        }
    }


    @Override
    public int getCount() {
        return entriesList.size();
    }

    @Override
    public Object getItem(int position) {
        return entriesList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View myEntryView, ViewGroup parent) {

        View view = myEntryView;

        LayoutInflater layoutInflater = LayoutInflater.from(this.myContext);
        view = layoutInflater.inflate(idView, null);

        TicketInformation ticketInformation = entriesList.get(position);

        TextView adviser = view.findViewById(R.id.adviser);
        adviser.setText(ticketInformation.getAdviser());

        TextView currentTicket = view.findViewById(R.id.current_ticket);
        currentTicket.setText(ticketInformation.getCurrentTicket() + "");

        TextView ticketsToAttend = view.findViewById(R.id.tickets_to_attend);
        ticketsToAttend.setText(ticketInformation.getTicketsToAttend() + "");

        return view;
    }


    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        ListActivity.ticketInformationList.clear();
        if (charText.length() == 0) {
            ListActivity.ticketInformationList.addAll(filterList);
        } else {
            for (TicketInformation wp : filterList) {
                if (wp.getAdviser().toLowerCase(Locale.getDefault()).contains(charText)) {
                    ListActivity.ticketInformationList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}
