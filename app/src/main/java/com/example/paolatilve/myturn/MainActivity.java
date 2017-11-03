package com.example.paolatilve.myturn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestHandle;

import java.util.List;
import java.util.concurrent.ExecutorService;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private View secondViewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        secondViewButton = (Button)findViewById(R.id.view_second);
        secondViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public synchronized void wsCall(View view){
        AsyncTestClass myClass = new AsyncTestClass();
        myClass.setUseSynchronousMode(false);



        AsyncHttpClient client = new AsyncHttpClient();
        RequestHandle requestHandle = client.get("http://services.groupkt.com/country/get/iso2code/AF", myClass);

        ExecutorService exe = client.getThreadPool();



        while (true){
            if(exe.isTerminated()){
                Toast.makeText(this, myClass.getResponseOut(), Toast.LENGTH_SHORT).show();
                break;
            }

        }


    }

    public void newWsCall(View view){

        Toast.makeText(this, "I CAN CLICK!", Toast.LENGTH_SHORT).show();


        /*/Preparar el cliente Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TicketsService service = retrofit.create(TicketsService.class);

        //LLamado al servicio
        Call<List<TicketInformation>> ticketsInfo = service.GetAllTickets();
        ticketsInfo.enqueue(new Callback<List<TicketInformation>>() {
            @Override
            public void onResponse(Call<List<TicketInformation>> call, Response<List<TicketInformation>> response) {
                final List<TicketInformation> myInfo = response.body();
                try {
                    final ListView entriesList = findViewById(R.id.entries_listview);
                    entriesList.setAdapter(new MyAdapter(myInfo, R.layout.entries,MainActivity.this){});
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }

            }

            @Override
            public void onFailure(Call<List<TicketInformation>> call, Throwable t) {
                TextView textView = findViewById(R.id.textView);
                textView.setText("failed "+t.toString());
            }
        });

       /* ticketsInfo.enqueue(new Callback<TicketsInfo>() {
            @Override
            public void onResponse(Call<TicketsInfo> call, Response<TicketsInfo> response) {
                Toast.makeText(MainActivity.this,response.body().toString(),Toast.LENGTH_LONG).show();

                /*TicketsInfo ticketInfo = response.body();
                List<TicketInformation> myInfo = ticketInfo.getTicketInformation();
                String adviser = myInfo.get(1).getAdviser();
                int ticketNumber = myInfo.get(1).getTicketNumber();

                TextView textView = (TextView)findViewById(R.id.textView);
                textView.setText("Adviser: "+ adviser +" "+ticketNumber);
            }

            @Override
            public void onFailure(Call<TicketsInfo> call, Throwable t) {
                TextView textView = (TextView)findViewById(R.id.textView);
                textView.setText("failed "+t.toString());
            }
        });*/


        //Toast.makeText(this, service.Get().toString(), Toast.LENGTH_SHORT).show();


    }


}
