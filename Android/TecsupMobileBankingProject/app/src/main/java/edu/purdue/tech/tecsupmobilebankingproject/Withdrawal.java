package edu.purdue.tech.tecsupmobilebankingproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.content.Intent;
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

public class Withdrawal extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdrawal);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_withdrawal, menu);

        final Button btn;
        final EditText eText;
        btn = (Button) findViewById(R.id.btnWithdrawalSubmit);
        eText = (EditText) findViewById(R.id.withdrawal_amount);


        List<String> spinnerArray =  new ArrayList<String>();
        spinnerArray.add("Checking");
        spinnerArray.add("Savings");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner sItems = (Spinner) findViewById(R.id.spin_account);
        sItems.setAdapter(adapter);



        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                final String Saving_Account = "000001";
                final String Checking_Account = "010001";

                // BEGIN DIALOG BOX

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                //Yes button clicked
                                String str = ("Withdrawal Amt: " + eText.getText().toString());
                                Toast msg = Toast.makeText(getBaseContext(),str,Toast.LENGTH_LONG);
                                msg.show();

                                String selected = sItems.getSelectedItem().toString();
                                if (selected.equals("Checking")) {
                                    String account_str = ("Acct: Checking:" + Saving_Account);
                                    Toast account_msg = Toast.makeText(getBaseContext(),account_str,Toast.LENGTH_LONG);
                                    account_msg.show();

                                    execute_withdrawal(Float.parseFloat(eText.getText().toString()),Checking_Account);
                                }
                                if (selected.equals("Savings")) {
                                    String account_str = ("Acct: Savings:" + Checking_Account);
                                    Toast account_msg = Toast.makeText(getBaseContext(),account_str,Toast.LENGTH_LONG);
                                    account_msg.show();

                                    execute_withdrawal(Float.parseFloat(eText.getText().toString()),Saving_Account);
                                }
                                eText.setText("");
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(Withdrawal.this);
                builder.setMessage("Are you sure you want to Withdraw $" + eText.getText().toString()).setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();


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

    public boolean execute_withdrawal(float amount, String account_num) {
        float acc_balance = (float)100.00;

        if (amount > acc_balance)
        {
            String error = "Insufficient Funds";
            Toast error_msg = Toast.makeText(getBaseContext(),error,Toast.LENGTH_LONG);
            error_msg.show();
            return false;
        }

        acc_balance = acc_balance - amount;
        String str = Float.toString(acc_balance);
        Toast str_msg = Toast.makeText(getBaseContext(),str,Toast.LENGTH_LONG);
        str_msg.show();


        return true;
    }


    private class ClienteREST extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... arg0) {

            Log.i("ProductosBuscarREST", "Dentro de doInBackground()");


            TextView txtAccountNum = (TextView) findViewById(R.id.txtAccountNum);
            final TextView txtAmount = (TextView) findViewById(R.id.txtAmount);

            try {
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost post = new HttpPost("http://192.168.19.25:8080/TecsupMobileProject/rest/withdrawal");
                post.setHeader("content-type", "application/x-www-form-urlencoded; charset=ISO-8859-1");

                List<NameValuePair> nameValuePairs = new ArrayList<>(2);


                nameValuePairs.add(new BasicNameValuePair("txtAccountNum", txtAccountNum.getText().toString()));
                nameValuePairs.add(new BasicNameValuePair("txtAmount", txtAmount.getText().toString()));
                post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse resp = httpClient.execute(post);
                String jsontext = EntityUtils.toString(resp.getEntity());
                Log.i("Prty", "------>" + jsontext);

                JSONObject entries = new JSONObject(jsontext);

                String x = entries.getString("return");




                if(x.equals("errorPassword")){
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(Withdrawal.this, "Wrong password", Toast.LENGTH_LONG).show();
                            txtAmount.setText("");
                        }
                    });
                }


            } catch (Exception ex) {
                Log.e("ProductosBuscarREST", "Error: " + ex);
            }
            return null;
        }
    }


}
