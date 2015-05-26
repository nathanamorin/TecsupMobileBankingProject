package edu.purdue.tech.tecsupmobilebankingproject;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class Deposit extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_deposit, menu);



        final Button btn;
        final EditText eText;
        btn = (Button) findViewById(R.id.btnDepositSubmit);
        eText = (EditText) findViewById(R.id.deposit_amount);


        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("Checking");
        spinnerArray.add("Savings");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner sItems = (Spinner) findViewById(R.id.spin_account);
        sItems.setAdapter(adapter);



        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String Saving_Account = "000001";
                String Checking_Account = "010001";

                String str = ("Deposit Amt: " + eText.getText().toString());
                Toast msg = Toast.makeText(getBaseContext(),str,Toast.LENGTH_LONG);
                msg.show();


                String selected = sItems.getSelectedItem().toString();
                if (selected.equals("Checking")) {
                    String account_str = ("Acct: Checking:" + Saving_Account);
                    Toast account_msg = Toast.makeText(getBaseContext(),account_str,Toast.LENGTH_LONG);
                    account_msg.show();
                    execute_deposit(Float.parseFloat(eText.getText().toString()),Checking_Account);
                }
                if (selected.equals("Savings")) {
                    String account_str = ("Acct: Savings:" + Checking_Account);
                    Toast account_msg = Toast.makeText(getBaseContext(),account_str,Toast.LENGTH_LONG);
                    account_msg.show();

                    execute_deposit(Float.parseFloat(eText.getText().toString()),Saving_Account);
                }


                //Begin SQL Queries


            }
        });




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



    public boolean execute_deposit(float amount, String account_num) {
        float total;

        total = (float)150.00;

        total = total + amount;

        String str = ("Total: " + Float.toString(total));
        Toast msg = Toast.makeText(getBaseContext(),str,Toast.LENGTH_LONG);
        msg.show();

        return true;
    }


private class ClienteREST extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... arg0) {

        //Log.i("MenuLateralFragmento1", "Dentro de doInBackground()");
/*
        TextView txtNombre = (TextView) getActivity().findViewById(R.id.txtNombre);


        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost post = new HttpPost("http://192.168.115.86:8080/trastienda2015_ws/rest/productos/");
            post.setHeader("content-type", "application/x-www-form-urlencoded; charset=ISO-8859-1");

            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
            nameValuePairs.add(new BasicNameValuePair("idCategoria", "1"));
            nameValuePairs.add(new BasicNameValuePair("nombre", txtNombre.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("precio", txtPrecio.getText().toString()));
            post.setEntity(new UrlEncodedFormEntity(nameValuePairs));


            HttpResponse resp = httpClient.execute(post);
            String jsontext = EntityUtils.toString(resp.getEntity());
            JSONObject objeto = new JSONObject(jsontext);
            String estado = objeto.getString("estado");
            Log.i("MenuLateralFragmento1", "------>" + estado);

            if ("CORRECTO".equals(estado)) {
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(getActivity(), "Se registró correctamente", Toast.LENGTH_LONG).show();
                    }
                });
            } else {
                getActivity().runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
                    }
                });
            }


        } catch (Exception ex) {
            Log.e("MenuLateralFragmento1", "Error: " + ex);
        }
        */
        return null;
    }

}
}