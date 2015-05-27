package com.example.alumno.mobilebanking;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

public class Login extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtUsername = (TextView) findViewById(R.id.txtUn);

                String str = txtUsername.getText().toString();
                if (str.equals("")) {
                    Toast pop_msg = Toast.makeText(getBaseContext(), "Must Enter the username", Toast.LENGTH_LONG);
                    pop_msg.show();
                } else {
                    new ClienteREST().execute();
                }

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    int count = 0;

    private class ClienteREST extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... arg0) {

            Log.i("ProductosBuscarREST", "Dentro de doInBackground()");


            final TextView txtUn = (TextView) findViewById(R.id.txtUn);
            final TextView txtPw = (TextView) findViewById(R.id.txtPw);

            try {
                HttpClient httpClient = new  DefaultHttpClient();
                HttpPost post = new HttpPost("http://192.168.19.25:8080/TecsupMobileProject/rest/loginUserPass");
                post.setHeader("content-type", "application/x-www-form-urlencoded; charset=ISO-8859-1");

                List<NameValuePair> nameValuePairs = new ArrayList<>(2);


                nameValuePairs.add(new BasicNameValuePair("username", txtUn.getText().toString()));
                nameValuePairs.add(new BasicNameValuePair("password", txtPw.getText().toString()));
                post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse resp = httpClient.execute(post);
                String jsontext = EntityUtils.toString(resp.getEntity());
                Log.i("Prty", "------>" + jsontext);

                JSONObject entries = new JSONObject(jsontext);

                String x = entries.getString("return");



                if (x.equals("needSecurityQuestion")){
                    String y = entries.getString("securityQuestionID");
                    String z = entries.getString("securityQuestion");

                    Intent i = new Intent(Login.this, LoginQuestions.class);
                    String name = txtUn.getText().toString();
                    String pw = txtPw.getText().toString();
                    i.putExtra("username", name);
                    i.putExtra("password", pw);
                    i.putExtra("qid", y);
                    i.putExtra("question", z);
                    startActivity(i);
                }
                if(x.equals("errorPassword")){
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(Login.this, "Wrong password", Toast.LENGTH_LONG).show();
                            count = count +1;
                            Toast.makeText(Login.this, "Failed attempt:"+count, Toast.LENGTH_LONG).show();
                            txtPw.setText("");
                            if (count > 3){
                                count = 0;
                                Toast.makeText(Login.this, "Account locked", Toast.LENGTH_LONG).show();
                                txtUn.setText("");
                            }
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


