package com.example.unit_converter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class percentage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText e1, e2;
    Spinner from,to;
    Button sub;
    Float a;
public View view;
    Database mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dig);
        sub=findViewById(R.id.ans);
        e1 = findViewById(R.id.a1);
        e2 = findViewById(R.id.a2);
        from=findViewById(R.id.dig_f);
        to=findViewById(R.id.dig_t);


        TextView t=findViewById(R.id.t1);
        String s="PERCENTAGE CONVERTER";
        t.setText(s);


        mydb=new Database(this);

        addItemsOnSpinner2();
        addListenerOnButton();
        addItemsOnSpinner1();



    }



    private void addItemsOnSpinner1() {
        ArrayAdapter<CharSequence> ad=ArrayAdapter.createFromResource(this,R.array.per, android.R.layout.simple_spinner_item);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        from.setAdapter(ad);
        from.setOnItemSelectedListener(new CustomOnItemSelectedListner());

    }

    private void addListenerOnButton() {

        sub.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if ((e1.getText().toString().matches("")) || (e1.getText().toString() == null)) {
                   // Toast.makeText(getApplicationContext(), "please enter the fill the value", Toast.LENGTH_SHORT).show();
                    e1.setError("please enter the value");
                    e1.requestFocus();
                }
                else {
                    String fr = String.valueOf(from.getSelectedItem());
                    String t = String.valueOf(to.getSelectedItem());


                    a = Float.parseFloat(e1.getText().toString());

                    if (fr == t)
                        e2.setText(e1.getText().toString());
                    else if (fr.equals("CPI") && t.equals("SPI"))
                        e2.setText(e1.getText().toString());
                    else if (t.equals("CPI") && fr.equals("SPI"))
                        e2.setText(e1.getText().toString());
                    else if (fr.equals("SPI") && t.equals("Percentage"))
                        e2.setText(Float.toString((float) (a - 0.5) * 10));
                    else if (fr.equals("CPI") && t.equals("Percentage"))
                        e2.setText(Float.toString((float) (a - 0.5) * 10));
                    else if (fr.equals("Percentage") && t.equals("CPI"))
                        e2.setText(Float.toString((float) ((float) (a / 10) + 0.5)));
                    else if (fr.equals("Percentage") && t.equals("SPI"))
                        e2.setText(Float.toString((float) ((float) (a / 10) + 0.5)));
                    else
                        Toast.makeText(percentage.this, "select valid option", Toast.LENGTH_SHORT).show();

                    boolean add = mydb.insert(e1.getText().toString(), from.getSelectedItem().toString(), e2.getText().toString(), to.getSelectedItem().toString());
                    if (add == true)
                        Toast.makeText(getApplicationContext(), "Convert", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void addItemsOnSpinner2() {

        ArrayAdapter<CharSequence> ad1=ArrayAdapter.createFromResource(this,R.array.per, android.R.layout.simple_spinner_item);
        ad1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        to.setAdapter(ad1);
        to.setOnItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Home");
        menu.add("History");
        menu.add("Clear");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getTitle() == "Home") {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        }
        if (item.getTitle() == "History")
            show(view);

        if (item.getTitle() == "Clear")
            clear();


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String s=parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), " To: "+s,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



    private class CustomOnItemSelectedListner implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String s=parent.getItemAtPosition(position).toString();
            Toast.makeText(parent.getContext(), " From: "+s,Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
    private void show(View view) {
        Integer i=1;
        Cursor res = mydb.getAllData();
        if (res.getCount() == 0)
            showalert("sorry !", "No History Found");
        else {
            StringBuffer val = new StringBuffer();
            while (res.moveToNext()) {
                val.append("No : "+i+"\n");
                val.append("Value : " + res.getString(0) + " ");
                val.append("From : " + res.getString(1) + "\n");
                val.append("Value : " + res.getString(2) + " ");
                val.append("To : " + res.getString(3) + "\n\n");
                i++;
            }
            showalert("History", val.toString());
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
        Integer del=mydb.deleteData();

        if(del>0)
            showalert(" ", "All History is Cleared");
        else
            showalert("sorry !", "No History");
    }
}
