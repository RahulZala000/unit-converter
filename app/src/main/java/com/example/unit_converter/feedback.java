package com.example.unit_converter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class feedback extends AppCompatActivity {


    Button b;

    RatingBar r;
    TextView n,f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        n=findViewById(R.id.d1);
        f=findViewById(R.id.editTextTextMultiLine);

        r=findViewById(R.id.ratingBar);
        b=findViewById(R.id.button);


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


    public void sub(View view) {
        if (((n.getText().toString().matches("")) || (n.getText().toString() == null)) && (f.getText().toString().matches("")) || (f.getText().toString() == null))
            Toast.makeText(getApplicationContext(),"Insert Name and give to feedback",Toast.LENGTH_SHORT).show();
        else if((f.getText().toString().matches("")) || (f.getText().toString() == null))
            Toast.makeText(getApplicationContext(),"Please Give To Feedback",Toast.LENGTH_SHORT).show();
        else if((n.getText().toString().matches("")) || (n.getText().toString() == null))
            Toast.makeText(getApplicationContext(),"Please Insert Name",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(),"Thank you "+n.getText().toString()+" for Feedback\n Ratting : "+r.getRating(),Toast.LENGTH_SHORT).show();

            n.setText("");
            f.setText("");
            r.setRating(0);
    }

    }
