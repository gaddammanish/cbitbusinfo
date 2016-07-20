package com.example.gadda_000.newproj;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Bundle totalData=new Bundle();
    AutoCompleteTextView busName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        busName=(AutoCompleteTextView)findViewById(R.id.place);
        String[] autoarray=new  String[448];

        DBcontent autoentry=new DBcontent(this);
        try {
            autoentry.open();
           autoarray=autoentry.getautoString();
            autoentry.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        ArrayAdapter<String> adapter1=new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,autoarray);
        busName.setThreshold(1);
        busName.setAdapter(adapter1);



        FloatingActionButton busnamesid = (FloatingActionButton) findViewById(R.id.busnames);
        busnamesid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String typedArea=busName.getText().toString();
                busName.setText("");
                if(typedArea.equals(""))
                {
                    Toast.makeText(getApplicationContext(), "please enter area", Toast.LENGTH_LONG).show();
                }
                else
                {
                    totalData.putString("typedArea", typedArea);
                    Intent intent1 = new Intent(MainActivity.this, Main3Activity.class);
                    intent1.putExtras(totalData);
                    startActivity(intent1);
                }

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_busno) {
            Intent intent1=new Intent(MainActivity.this,MainBusnoActivity.class);
            startActivity(intent1);
        } else if (id == R.id.contact) {
            Intent intent2=new Intent(MainActivity.this,MainContactActivity.class);
            startActivity(intent2);

        } else if (id == R.id.nav_parentinfo) {
            Intent intent3=new Intent(MainActivity.this,MainParentsActivity.class);
            startActivity(intent3);

        } else if (id == R.id.nav_studentsinfo) {
            Intent intent4=new Intent(MainActivity.this,MainStudentsActivity.class);
            startActivity(intent4);

        } else if (id == R.id.nav_application) {
            Intent intent5=new Intent(MainActivity.this,MainFee1Activity.class);
            startActivity(intent5);

        } else if (id == R.id.nav_loc) {
            Intent intent5=new Intent(Intent.ACTION_VIEW);
            intent5.setData(Uri.parse("http://maps.google.com/maps?q=17.3906, 78.3219(Label Point)"));
           Intent chooser=Intent.createChooser(intent5,"Open Maps Using");
            startActivity(chooser);

        }
        else if(id==R.id.nav_complaint)
        {
                Intent intent6=new Intent(MainActivity.this,MainEmailActivity.class);
            startActivity(intent6);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }







}
