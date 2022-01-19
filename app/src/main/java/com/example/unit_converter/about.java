package com.example.unit_converter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class about extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Home");
        menu.add("Feedback");
        menu.add("About Us");
        menu.add("Contact Us");
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getTitle() == "Home") {
            Intent i=new Intent(this,MainActivity.class);
            startActivity(i);
        }
        if (item.getTitle() == "About Us") {
            Intent i=new Intent(this,about.class);
            startActivity(i);
        }
        if (item.getTitle() == "Contact Us") {
            Intent i=new Intent(this,contact.class);
            startActivity(i);
        }
        if (item.getTitle() == "Feedback") {
            Intent i=new Intent(this,feedback.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }

}