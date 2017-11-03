package com.example.paolatilve.myturn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.List;

import Model.TicketInformation;
import Services.TicketsService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private SearchView searchView;
    private MyAdapter adapter;
    public static List<TicketInformation> ticketInformationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //Preparar el cliente Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://itsmyturn.getsandbox.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TicketsService service = retrofit.create(TicketsService.class);

        //LLamado al servicio
        Call<List<TicketInformation>> ticketsInfo = service.GetAllTickets();
        ticketsInfo.enqueue(new Callback<List<TicketInformation>>() {
            @Override
            public void onResponse(Call<List<TicketInformation>> call, Response<List<TicketInformation>> response) {
                ticketInformationList = response.body();
                //final List<TicketInformation> myInfo = response.body();
                final ListView entriesList = findViewById(R.id.entry_list);

                //Toast.makeText(ListActivity.this, "MArgo "+ entriesList.toString(),Toast.LENGTH_LONG).show();
                adapter =new MyAdapter(ticketInformationList,R.layout.entries,ListActivity.this);
                entriesList.setAdapter(adapter);

                searchView = findViewById(R.id.searchView);
                searchView.setOnQueryTextListener(ListActivity.this);
            }

            @Override
            public void onFailure(Call<List<TicketInformation>> call, Throwable t) {
                Toast.makeText(ListActivity.this, t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return false;
    }
}
