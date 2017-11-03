package com.example.paolatilve.myturn;

import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

/**
 * Created by paolatilve on 10/12/17.
 */

public class AsyncTestClass extends AsyncHttpResponseHandler {

    private String responseOut;

    public AsyncTestClass(){
        super();
        this.setUseSynchronousMode(true);
    }




    @Override
    public void onSuccess(int i, Header[] headers, byte[] bytes) {
        setResponseOut(new String(bytes));

        //Toast.makeText(MainActivity.this, response+" "+i, Toast.LENGTH_SHORT).show();
/*                String response = new String(bytes);

                try {
                    JSONObject myObj = new JSONObject(response);
                    if(myObj.getInt("Status")== 200){
                        String message = myObj.getString("RestResponse");
                        //myGlobal = message;

                        Toast.makeText(MainActivity.this, "The Country is "+message, Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
    }

    @Override
    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
        setResponseOut(new String(bytes));
        //Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
    }

    public String getResponseOut() {
        return responseOut;
    }

    public void setResponseOut(String responseOut) {
        this.responseOut = responseOut;
    }
}
