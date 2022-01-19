package com.example.unit_converter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.security.KeyStore;
import java.util.Objects;

import static android.widget.Toast.makeText;

public class MainActivity extends AppCompatActivity {

    Button u, dig, per, ti, curr;
    Intent i;
    Database mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        u = findViewById(R.id.unit);
        dig = findViewById(R.id.digital);
        ti = findViewById(R.id.time);
        per = findViewById(R.id.percentage);
        curr = findViewById(R.id.curruncy);
        mydb = new Database(this);
        ti.setOnClickListener(this::time_cl);
        u.setOnClickListener(this::unit_cl);
        dig.setOnClickListener(this::dig_cl);
        per.setOnClickListener(this::per_cl);
        curr.setOnClickListener(this::curr_cl);
    }

    public void curr_cl(View view) {
        makeText(getApplicationContext(), "Curruncy", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, curruncy.class);
        startActivity(i);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Home");
        menu.add("Feedback");
        menu.add("About Us");
        menu.add("Contact Us");
        menu.add("History");
        menu.add("Clear");
        menu.add("Exit");
        return true;

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getTitle() == "Home") {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
        if (item.getTitle() == "About Us") {
            Intent i = new Intent(this, about.class);
            startActivity(i);
        }
        if (item.getTitle() == "Contact Us") {
            Intent i = new Intent(this, contact.class);
            startActivity(i);
        }
        if (item.getTitle() == "Feedback") {
            Intent i = new Intent(this, feedback.class);
            startActivity(i);
        }
        if (item.getTitle() == "History")
            show();
        if (item.getTitle() == "Clear")
            clear();
        if (item.getTitle()=="Exit")
            onBackPressed();

        return super.onOptionsItemSelected(item);
    }

    private void per_cl(View view) {
        makeText(getApplicationContext(), "Percentage", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, percentage.class);
        startActivity(i);


    }


    private void dig_cl(View view) {
        makeText(getApplicationContext(), "Digital", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, dig.class);
        startActivity(i);


    }

    public void time_cl(View view) {
        makeText(getApplicationContext(), "time", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, time.class);
        startActivity(i);
    }


    public void unit_cl(View view) {
        makeText(getApplicationContext(), "unit", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, unit.class);
        startActivity(i);
    }

    private void show() {
        Cursor res = mydb.getAllData();
        if (res.getCount() == 0)
            showalert("sorry !", "No Data Found");
        else {
            StringBuffer val = new StringBuffer();
            while (res.moveToNext()) {
                val.append("Value : " + res.getString(0) + " ");
                val.append("From : " + res.getString(1) + "\n");
                val.append("Value : " + res.getString(2) + " ");
                val.append("To : " + res.getString(3) + "\n\n");
            }
            showalert("record", val.toString());
        }
    }

    private void showalert(String s, String s1) {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setTitle(s);
        ad.setMessage(s1);
        ad.setCancelable(true);
        AlertDialog ab = ad.create();
        ab.show();
    }

    private void clear() {
        Integer del = mydb.deleteData();

        if (del > 0)
            showalert(" ", "All Data is Clear");
        else
            showalert("sorry !", "No History");
    }


    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit");
        builder.setMessage("Are you sure to exit the application?");
        builder.setNegativeButton(android.R.string.no, null);
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.super.onBackPressed();
            }

        }).create().show();

    }
}