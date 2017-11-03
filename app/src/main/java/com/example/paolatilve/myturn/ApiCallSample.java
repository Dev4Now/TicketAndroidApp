package com.example.paolatilve.myturn;

import android.os.AsyncTask;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by paolatilve on 10/6/17.
 */

    public class ApiCallSample extends AsyncTask<String,String, String> {

        public String myGlobal;
        private String response;

    @Override
    protected String doInBackground(final String... params){

        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://services.groupkt.com/country/get/iso2code/AF", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                response = new String(bytes);


             /*   try {
                    JSONObject myObj = new JSONObject(response);
                    if(myObj.getInt("Status")== 200){
                        String message = myObj.getString("name");
                        myGlobal = message;

                        //Toast.makeText(MainActivity.this, "The Country is "+message, Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                myGlobal = "Failed ";
                response = "Failed";
            }
        });

        return response;
    }


    @Override
    protected void onPostExecute(String s) {
        final String something = "";
        /*  */
        super.onPostExecute(doInBackground());
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
