package com.example.alumno.mobilebanking;

import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class SummaryPage extends ActionBarActivity  {

    private ListView drawerList;
    private ActionBarDrawerToggle drawerToggle;

    private String[] listaOpciones;
    private DrawerLayout drawerLayout;
    private CharSequence tituloFragmento;
    private CharSequence tituloAplicacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_page);

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, new AccSummary()).commit();


        listaOpciones = new String[] {"Account", "Transfer", "Withdrawal", "Deposit"};
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);


        String username = "";
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            username = extras.getString("username");
        }

        drawerList.setAdapter(new ArrayAdapter<String>(getSupportActionBar().getThemedContext(), android.R.layout.simple_list_item_1, listaOpciones));

        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Fragment fragment = null;

                switch (position) {
                    case 0:
                        fragment = new AccSummary();
                        break;
                    case 1:
                        fragment = new Transfer();
                        break;
                    case 2:
                        fragment = new Withdrawal();
                        break;
                    case 3:
                        fragment = new Deposit();
                        break;
                }

                FragmentManager fragmentManager = getSupportFragmentManager();

                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

                drawerList.setItemChecked(position, true);

                tituloFragmento = listaOpciones[position];
                getSupportActionBar().setTitle(tituloFragmento);

                drawerLayout.closeDrawer(drawerList);
            }
        });

        tituloFragmento = getTitle();
        tituloAplicacion = getTitle();

        drawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                R.drawable.btndrawer,
                R.string.drawer_abierto,
                R.string.drawer_cerrado) {

            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle(tituloFragmento);
                ActivityCompat.invalidateOptionsMenu(SummaryPage.this);
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle(tituloAplicacion);
                ActivityCompat.invalidateOptionsMenu(SummaryPage.this);
            }
        };

        drawerLayout.setDrawerListener(drawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_summary_page, menu);
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

        if (drawerToggle.onOptionsItemSelected(item)) {
            // Toma los eventos de selección del toggle aquí
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

}