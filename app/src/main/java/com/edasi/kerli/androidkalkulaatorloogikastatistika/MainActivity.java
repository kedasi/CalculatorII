package com.edasi.kerli.androidkalkulaatorloogikastatistika;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private UOW uow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        uow = new UOW(getApplicationContext());
        // start with fresh database
        uow.DropCreateDatabase();
        populateListView(uow.GetOperationTypes());
        populateListView(uow.GetOperations());
        populateListView(uow.GetDailyStats());
    }


    private void populateListView(String[] arr) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item_template, arr);
        ListView lv = (ListView) findViewById(R.id.list);
        lv.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_show_operationtypes) {
            populateListView(uow.GetOperationTypes());
            return true;
        }
        if (id == R.id.action_show_operations) {
            populateListView(uow.GetOperations());
            return true;
        }
        if (id == R.id.action_show_operationstats) {
            populateListView(uow.GetDailyStats());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
