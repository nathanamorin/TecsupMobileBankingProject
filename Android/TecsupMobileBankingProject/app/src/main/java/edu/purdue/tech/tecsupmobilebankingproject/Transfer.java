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
    public void transferClickEvent(View v) { //This is the "Transfer" button's click event
        //Get the position of the From Spinner and save it for later
        final Spinner combo = (Spinner) findViewById(R.id.spinnerFrom);
        Integer fromSpinner = combo.getSelectedItemPosition();

        //Get text from the Amount TextView and save it into moneyRequest
        EditText amountTextBox = (EditText) findViewById(R.id.txtAmount);
        String amountText = amountTextBox.getText().toString().trim();
        //Existence validation, make error-flavored toast.
        if (amountText.equals(""))
            Toast.makeText(getApplicationContext(), (getResources().getString(R.string.savingsBlankTextError)), Toast.LENGTH_SHORT).show();

        else
        {
            BigDecimal moneyRequest = new BigDecimal(amountText);
            BigDecimal currentBalance = new BigDecimal(0);
            if (fromSpinner == 0)
                currentBalance = new BigDecimal(100); //Make this line pull data from the Checking Account eventually.
            else if (fromSpinner == 1)
                currentBalance = new BigDecimal(100); //Make this line pull data from the Savings Account eventually.

            //Subtract the two and see if the result is negative. If it is, make more toast and stop execution of code
            if (currentBalance.subtract(moneyRequest).signum() < 0)
            {
                amountTextBox.setText("");
                Toast.makeText(getApplicationContext(), (getResources().getString(R.string.savingsInsufficientFundsError)), Toast.LENGTH_SHORT).show();
            }
            else
            {
                //Apply the transfer to the database via SQL?
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        final String[] spinnerItems = new String[]
                {
                        getResources().getString(R.string.checkingToSavingsItem),
                        getResources().getString(R.string.savingsToCheckingItem)
                };

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer);

        //Populate the two spinners
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner combo = (Spinner)findViewById(R.id.spinnerFrom);
        combo.setAdapter(adapter);

        combo.setOnItemSelectedListener(
                new OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(
                            AdapterView<?> parent, View view, int position, long id) {

                        //Get ready to put stuff in the Balance label
                        TextView txt = (TextView) findViewById(R.id.CurrentBalanceLabel);

                        if (position==0)
                        {
                            //Get the current amount of money in Checking from the database
                            //
                            txt.setText("(SQL not implemented)"); //display it
                        }
                        if (position==1)
                        {
                            //Get the current amount of money in Savings from the database
                            //
                            txt.setText("(SQL not implemented)"); //display it
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
