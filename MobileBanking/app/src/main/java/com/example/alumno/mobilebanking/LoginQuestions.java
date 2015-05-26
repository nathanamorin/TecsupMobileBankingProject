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


public class LoginQuestions extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_questions);


        String question = "";
        Bundle extrasQ = getIntent().getExtras();
        if(extrasQ != null){
            question = extrasQ.getString("question");
        }
        TextView txtQ = (TextView) findViewById(R.id.question);
        txtQ.setText(question);




        Button btnSub = (Button) findViewById(R.id.btnSub);
        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView txtAnswer = (TextView) findViewById(R.id.txtAns);

                String str1 = txtAnswer.getText().toString();
                if (str1.equals("")) {
                    Toast pop_msg = Toast.makeText(getBaseContext(), "Must Enter the answer", Toast.LENGTH_LONG);
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
        getMenuInflater().inflate(R.menu.menu_login_questions, menu);
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


    private class ClienteREST extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... arg0) {

            Log.i("ProductosBuscarREST", "Dentro de doInBackground()");


            final TextView txtAns = (TextView) findViewById(R.id.txtAns);


            try {
                HttpClient httpClient = new DefaultHttpClient();
                HttpPost post = new HttpPost("http://192.168.19.25:8080/TecsupMobileProject/rest/loginUserPass");
                post.setHeader("content-type", "application/x-www-form-urlencoded; charset=ISO-8859-1");

                List<NameValuePair> nameValuePairs = new ArrayList<>(2);

                String username = "";
                Bundle extras = getIntent().getExtras();
                if (extras != null){
                    username = extras.getString("username");
                }
                String password = "";
                if (extras != null){
                    password = extras.getString("password");
                }
                String qid = "";
                if (extras != null){
                    qid = extras.getString("qid");
                }


                nameValuePairs.add(new BasicNameValuePair("username", username));
                nameValuePairs.add(new BasicNameValuePair("password", password));
                nameValuePairs.add(new BasicNameValuePair("securityQuestionID", qid));
                nameValuePairs.add(new BasicNameValuePair("securityAnswer", txtAns.getText().toString()));
                post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                HttpResponse resp = httpClient.execute(post);
                String jsontext = EntityUtils.toString(resp.getEntity());
                Log.i("Prty", "------>" + jsontext);

                JSONObject entries = new JSONObject(jsontext);

                String x = entries.getString("return");



                if (x.equals("success")){
                    Intent i = new Intent(LoginQuestions.this, SummaryPage.class);

                    i.putExtra("username", username);

                    startActivity(i);
                }
                if(x.equals("incorrectSQAnswer")){
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(LoginQuestions.this, "Wrong answer", Toast.LENGTH_LONG).show();
                            txtAns.setText("");
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
