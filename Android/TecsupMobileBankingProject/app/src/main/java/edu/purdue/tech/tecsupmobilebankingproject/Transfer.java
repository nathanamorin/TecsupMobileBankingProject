package edu.purdue.tech.tecsupmobilebankingproject;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

public class Transfer extends ActionBarActivity
{
    public void transferClickEvent()
    {
        //Get text from the Amount TextView and save it into moneyRequest
        TextView amountTextBox = (EditText)findViewById(R.id.txtAmount);
        if (amountTextBox.getText().toString()=="")
        {
            //Make toast for "Please enter an amount."
        }
        else
        {
            BigDecimal moneyRequest = new BigDecimal(amountTextBox.getText().toString());

            //Subtract this with the amount of money actually in the database
            //If the result of this is negative, set "@+id/txtAmount" to "", show "There's not that much money in your account." in a Toast pop-up, and stop execution of code
            //Apply the transfer to the database via SQL?
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        final String[] spinnerItems = new String[]{"Checking", "Savings"};

        //Populate the two spinners
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner combo = (Spinner)findViewById(R.id.spinnerFrom);
        combo.setAdapter(adapter);
        final Spinner combo2 = (Spinner)findViewById(R.id.spinnerTo);
        combo2.setAdapter(adapter);

        combo.setOnItemSelectedListener(
                new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(
                            AdapterView<?> parent, View view, int position, long id) {

                    //Automagically set the spinner's position.
                        if (position==0)
                        {
                            combo2.setSelection(1);
                            //Get the current amount of money in Checking from the database and display it in "@+id/CurrentBalanceLabel"
                        }
                        if (position==1)
                        {
                            combo2.setSelection(0);
                            //Get the current amount of money in Savings from the database and display it in "@+id/CurrentBalanceLabel"
                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_transfer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) return true;
        return super.onOptionsItemSelected(item);
    }
}
